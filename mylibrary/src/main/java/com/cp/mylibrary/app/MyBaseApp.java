package com.cp.mylibrary.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;


import com.cp.mylibrary.utils.ActivityManagerUtil;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


import java.io.File;
import java.io.IOException;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyBaseApp extends Application {
    private static MyBaseApp context;
    private static ActivityManagerUtil activityManager = null;
    private static final String TAG = "euler";


    /**
     * patch manager
     */
//    private PatchManager mPatchManager;
//
//



    {
        //在application文件中配置三方平台的appkey：
        PlatformConfig.setWeixin(Config.WEICHAT_APPID, Config.WEICHAT_SECRET);
        //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博
        PlatformConfig.setSinaWeibo(Config.SINA_APPID, Config.SINA_SECRET);
//        //易信
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        PlatformConfig.setAlipay("2015111700822536");
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");

    }



    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        activityManager = ActivityManagerUtil.getInstance();




//        addPatchManager();

        //application中初始化sdk，这个初始化最好放在application的程序入口中，防止意外发生：

        UMShareAPI.get(this);

        //如果您使用了新浪微博，需要在这里设置回调地址：
        com.umeng.socialize.Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";
    }

    private void addPatchManager() {
        // initialize
//        mPatchManager = new PatchManager(this);
//        mPatchManager.init("1.0");
//        Log.d(TAG, "inited.");
//
//        // load patch
//        mPatchManager.loadPatch();
//        Log.d(TAG, "apatch loaded.");
//
//        // add patch at runtime
//        try {
//            // .apatch file path
//            String patchFileString = Config.DEFAULT_SAVE_FILE_PATH + Config.APATCH_NAME;
//            mPatchManager.addPatch(patchFileString);
//            Log.d(TAG, "apatch:" + patchFileString + " added.");
//            //这里添加一个方法 ，复制补丁成功后，删除sdCard的补丁，避免每次进入程序都重新加载一次
//            File f = new File(this.getFilesDir(), Config.DEFAULT_SAVE_FILE_PATH + Config.APATCH_NAME);
//            if (f.exists()) {
//                boolean result = new File(patchFileString).delete();
//                if (!result)
//                    Log.e(TAG, patchFileString + " delete fail");
//            }
//
//
//        } catch (IOException e) {
//            Log.e(TAG, "", e);
//        }
//












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
     * @return
     */
    public ActivityManagerUtil getActivityManager() {
        return activityManager;
    }

    public static void exit() {
        activityManager.finishAllActivity();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


    }
}
