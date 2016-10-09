package com.cp.mylibrary.app;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.cp.mylibrary.utils.ActivityManagerUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyBaseApp  extends Application {
    private static MyBaseApp context;
    private static ActivityManagerUtil activityManager = null;
    private static final String TAG = "euler";

    private static final String APATCH_PATH = "/out.apatch";

    private static final String DIR = "apatch";

    /**
     * patch manager
     */
     private PatchManager mPatchManager;
//
//

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        activityManager = ActivityManagerUtil.getInstance();


       addPatchManager();


    }

     private  void addPatchManager()
     {
         // initialize
         mPatchManager = new PatchManager(this);
         mPatchManager.init("1.0");
         Log.d(TAG, "inited.");

         // load patch
         mPatchManager.loadPatch();
         Log.d(TAG, "apatch loaded.");

         // add patch at runtime
         try {
             // .apatch file path
             String patchFileString = Environment.getExternalStorageDirectory()
                     .getAbsolutePath() + APATCH_PATH;
             mPatchManager.addPatch(patchFileString);
             Log.d(TAG, "apatch:" + patchFileString + " added.");
             //这里添加一个方法 ，复制补丁成功后，删除sdCard的补丁，避免每次进入程序都重新加载一次
             File f = new File(this.getFilesDir(), DIR + APATCH_PATH);
             if (f.exists()) {
                 boolean result = new File(patchFileString).delete();
                 if (!result)
                     Log.e(TAG, patchFileString + " delete fail");
             }


         } catch (IOException e) {
             Log.e(TAG, "", e);
         }

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
