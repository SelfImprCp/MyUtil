package cn.myasapp.main;

import android.app.Application;

import com.cp.mylibrary.api.MyHttpClient;
import com.cp.mylibrary.app.MyBaseApp;
import com.cp.mylibrary.utils.CrashHandler;
import com.cp.mylibrary.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/5.
 */
public class AppApplication extends MyBaseApp
{


    @Override
    public void onCreate() {
        super.onCreate();

        // 异常处理，不需要处理时注释掉这两句即可！
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        // 注册crashHandler
//        crashHandler.init(getApplicationContext());

        // 配置图片

        ImageLoaderUtils.configImageLoader(this,R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher,AppConfig.DEFAULT_SAVE_IMAGE_PATH);
        // 初始化网络 请求
//        MyHttpClient.initHttp(AppConfig.HOST,AppConfig.SUFFIX);
//        MyHttpClient.Cookie = "我的cookie ";
//


    }
}
