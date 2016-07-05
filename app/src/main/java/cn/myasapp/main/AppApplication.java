package cn.myasapp.main;

import android.app.Application;

import com.cp.mylibrary.utils.CrashHandler;

/**
 * Created by Jerry on 2016/7/5.
 */
public class AppApplication extends Application
{


    @Override
    public void onCreate() {
        super.onCreate();

        // 异常处理，不需要处理时注释掉这两句即可！
        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler
        crashHandler.init(getApplicationContext());


    }
}
