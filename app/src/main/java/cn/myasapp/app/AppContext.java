package cn.myasapp.app;


import android.app.Application;

import cn.myasapp.utils.ActivityManagerUtil;

public class AppContext extends Application {

	private static AppContext context;
	private static ActivityManagerUtil activityManager = null;


	@Override
	public void onCreate() {
		super.onCreate();
		activityManager = ActivityManagerUtil.getInstance();
		context = this;
		

	}

	/**
	 * 获得当前app运行的AppContext
	 *
	 * @return
	 */
	public static AppContext getInstance() {
		return context;
	}



}
