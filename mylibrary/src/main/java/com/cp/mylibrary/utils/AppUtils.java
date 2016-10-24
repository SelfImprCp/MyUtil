package com.cp.mylibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.cp.mylibrary.base.MyBaseActivity;

/**
 *
 * 跟App相关的辅助类
 * Created by Jerry on 2016/7/5.
 */
public class AppUtils {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE =2;



    /**
     *
     * @param context
     * @return
     */
     public static String getPackageName  (Context context)
     {
          return context.getPackageName();
     }




    /**
     * 检查权限
     */
    public static   void getPromission(Activity context)
    {
        LogCp.i(LogCp.CP,    AppUtils .class + "  来取权限!");




//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//        }
//


        // 读取sdcard 权限
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            LogCp.i(LogCp.CP, " 已经有了读取sdcard 的权限!");
        } else {
            //do not have permission
            LogCp.i(LogCp.CP, "  没有 读取sdcard 的权限!");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {


            } else {

                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }



        // 写入sdcard 权限
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            LogCp.i(LogCp.CP, " 已经有了写入sdcard 的权限!");
        } else {
            //do not have permission
            LogCp.i(LogCp.CP, "  没有 写入sdcard 的权限!");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


            } else {


                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);



            }
        }

    }




}
