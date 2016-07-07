package com.cp.mylibrary.app;

import android.app.Application;

import com.cp.mylibrary.utils.ActivityManagerUtil;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyBaseApp  extends Application {
    private static MyBaseApp context;
    private static ActivityManagerUtil activityManager = null;


    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        activityManager = ActivityManagerUtil.getInstance();





    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static MyBaseApp getInstance() {
        return context;
    }


    /**
     *
     * @return
     */
    public ActivityManagerUtil getActivityManager() {
        return activityManager;
    }

    public static void exit() {
        activityManager.finishAllActivity();
    }



}
