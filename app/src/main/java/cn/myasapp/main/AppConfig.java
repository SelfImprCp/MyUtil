package cn.myasapp.main;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 下午5:29:00
 */
public class AppConfig {



public static final String SUFFIX = "catering";
//
//
   public final static String HOST = "apis.juhe.cn";

    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();

        }
        return appConfig;
    }



    public static final int PAGE_SIZE = 10;// 默认分页大小



    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment
            .getExternalStorageDirectory() +

            File.separator + "AiLiBuLi" + File.separator + "ailibuliimg" + File.separator;

    // 默认存放文件下载的路径
    public final static String DEFAULT_SAVE_FILE_PATH =

            Environment.getExternalStorageDirectory() + File.separator + "AiLiBuLi"
                    + File.separator + "download" + File.separator;


}
