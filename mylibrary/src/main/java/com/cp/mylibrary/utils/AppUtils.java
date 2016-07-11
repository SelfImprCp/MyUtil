package com.cp.mylibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 *
 * 跟App相关的辅助类
 * Created by Jerry on 2016/7/5.
 */
public class AppUtils {




    /**
     *
     * @param context
     * @return
     */
     public static String getPackageName  (Context context)
     {
          return context.getPackageName();
     }


}
