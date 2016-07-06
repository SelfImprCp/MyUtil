package com.cp.mylibrary.utils;

import android.content.Context;

import java.io.File;
import java.io.Serializable;

/**
 * 项目中用到了 ASimpleCache 开源框架，这是对此框架的一层封装
 * 
 * @author cp
 * 
 */
public class MyCache {

	// wifi缓存时间为5分钟
	private static int wifi_cache_time = 5 * 60 * 1;
	// 其他网络环境为1小时
	private static int other_cache_time = 60 * 60 * 1;

	private static ACache aCache;

	public static MyCache myCache;

	public static Context mContext;

	// 缓存文件 夹
	public static final String MCache = "MCache";

	public static MyCache getMyCache(Context context) {
		aCache = ACache.get(context);
		mContext = context;
		if (myCache == null) {
			myCache = new MyCache();
		}

		return myCache;
	}

	/**
	 * 当前网络类型是否为wifi
	 */
	// private boolean netTypeIsWifi = false;
	/**
	 * 取得 网络 类型是否为wifi
	 * 
	 * @return
	 */
	public boolean getNetTypeIsWifi() {
		boolean netTypeIsWifi = false;
		if (NetWorkUtil.getNetworkType(mContext) == NetWorkUtil.NETTYPE_WIFI) {
			netTypeIsWifi = true;
		} else {
			netTypeIsWifi = false;
		}
		return netTypeIsWifi;
	}

	/**
	 * 保存对像
	 * 
	 * @param key
	 * @param value
	 */
	public void saveObject( String key, Serializable value) {

		if (getNetTypeIsWifi( )) {
			aCache.put(key, value, wifi_cache_time);
		} else {
			aCache.put(key, value, other_cache_time);
		}

	}

	/**
	 * 读取对像
	 * 
	 * @param key
	 */
	public Object readObject(String key) {
		return aCache.getAsObject(key);

	}



	/**
	 *
	 * @param key
	 * @param value
	 */
	public void saveString( String key, String value) {
		if (getNetTypeIsWifi( )) {
			aCache.put(key, value, wifi_cache_time);
		} else {
			aCache.put(key, value, other_cache_time);
		}
	}

	/**
	 *
	 * @param key
	 */
	public String readString(String key) {
		return aCache.getAsString(key);
	}


	/**
	 * 清除所有数据
	 */
	public void clearCache() {
		aCache.clear();
	}

	/**
	 * 猎取缓存大小
	 * 
	 * @param context
	 * @return
	 */
	public String getCacheSize(Context context) {
		// String path = Environment
		// .getExternalStorageDirectory().getAbsolutePath()+
		// "/cache/ACache";
		long fileSize = 0;
		String cacheSize = "0KB";
		// 2.2版本才有将应用缓存转移到sd卡的功能
		if (TDevice.isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
			// File externalCacheDir =
			// MethodsCompat.getExternalCacheDir(context);
			File externalCacheDir = context.getCacheDir();
			File destDir = new File(externalCacheDir + "/" + MCache);
			fileSize += FileUtil.getDirSize(destDir);

		}
		if (fileSize > 0)
			cacheSize = FileUtil.formatFileSize(fileSize);
		return cacheSize;

	}

}
