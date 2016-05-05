package cn.myasapp.cache;

import java.io.Serializable;

import android.content.Context;

import cn.myasapp.utils.InternetUtil;

/**
 * 项目中用到了 ASimpleCache 开源框架，这是对此框架的一层封装
 * 
 * @author cp
 * 
 */
public class MyCache {

	// wifi缓存时间为5分钟
	private static int wifi_cache_time = 1 * 60 * 1;
	// 其他网络环境为1小时
	private static int other_cache_time = 60 * 60 * 1;

	private static ACache aCache;

	public static MyCache myCache;

	public static MyCache getMyCache(Context context) {
		aCache = ACache.get(context);

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
		if (InternetUtil.getNetworkType() == InternetUtil.NETTYPE_WIFI) {
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
	public void saveObject(String key, Serializable value) {

		if (getNetTypeIsWifi()) {
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

}
