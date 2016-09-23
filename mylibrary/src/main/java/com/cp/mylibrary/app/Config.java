package com.cp.mylibrary.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by Jerry on 2016/8/17.
 */
public class Config {
     public static int PAGE_SIXE = 20;

     public static final String WEICHAT_APPID = "wx744cc51b4beacc83";
     public static final String WEICHAT_SECRET = "85b3190f2c55026312cea022e465e05a";

     // 默认存放文件下载的路径
     public final static String DEFAULT_SAVE_FILE_PATH = Environment
             .getExternalStorageDirectory()
             + File.separator
             + "MyCp"
             + File.separator + "download" + File.separator;
}
