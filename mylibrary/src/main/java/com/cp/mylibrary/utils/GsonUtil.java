package com.cp.mylibrary.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

/**
 * Gson工具类
 * @author zs
 * 2015-8-8
 */
public class GsonUtil {



//	private static Gson gson = new Gson();

	private static Gson gson =  new GsonBuilder().disableHtmlEscaping().create();




//	private static Gson gson = new GsonBuilder()
//			.registerTypeAdapter(Date.class, new UtilDateSerializer())
//			.setDateFormat(DateFormat.LONG).setPrettyPrinting().create();

	/**
	 * 将JSON字符串转换成bean(特殊情况)
	 * @param
	 * @param cls
	 * @return
	 */
	public static <T> T jsonStrToBean(String jsonStr, Class<T> cls){
		if(StringUtils.isEmpty(jsonStr)){
			jsonStr = "{\"code\":-1}";
		}else if(!(jsonStr.trim().startsWith("{"))){
			jsonStr = "{\"code\":-1}";
		}
		T t = gson.fromJson(jsonStr, cls);
		return t;
	}
	
	/**
	 * 将bean转换成JSON字符串
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public static String beanTojsonStr(Object obj) {


		return gson.toJson(obj);
	}

	private static class UtilDateSerializer implements JsonSerializer<Date>,
			JsonDeserializer<Date> {

		@Override
		public JsonElement serialize(Date date, Type type,
				JsonSerializationContext context) {
			return new JsonPrimitive(date.getTime());
		}

		@Override
		public Date deserialize(JsonElement element, Type type,
				JsonDeserializationContext context) throws JsonParseException {
			return new Date(element.getAsJsonPrimitive().getAsLong());
		}

	}
	
	
	
	 /**
	  *  读取本地的json文件 ，assets 下
	  * @param context
	  * @param fileName
	  * @return
	  */
	
	public static String getJsonForFile(Context context, String fileName) {

		StringBuilder stringBuilder = new StringBuilder();
		try {
			AssetManager assetManager = context.getAssets();
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					assetManager.open(fileName)));
			String line;
			while ((line = bf.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
	
}
