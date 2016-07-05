package com.cp.mylibrary.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by Jerry on 2016/7/4.
 */
public class OpenActivityUtil {


    private static String lastToast = "";
    private static long lastToastTime;
    public static OpenActivityUtil uiHelper;

    public static OpenActivityUtil getInstance() {
        if (uiHelper == null)
            uiHelper = new OpenActivityUtil();
        return uiHelper;
    }

    /**
     * 拨打电话
     *
     * @param context
     */
    public static void showTellPhone(Context context, String phone) {
        // Intent intent = new Intent(Intent.ACTION_CALL,
        // Uri.parse("tel:" + phone));
        // context.startActivity(intent);

        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 显示设置网络
     */

    public static void showSetIntent(Context context) {
        // TODO Auto-generated method stub
        Intent intent = null;
        // 判断手机系统的版本 即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(
                    android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings",
                    "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
    }









    // ===============================以下代码勿改动======================================//

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    public static void openActivity(Context context, Class<?> pClass) {
        openActivity(context, pClass, null);
    }

    public static void openActivityC(Context context, Class<?> pClass) {
        openActivity(context, pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    public static void openActivity(Context context, Class<?> pClass,
                                    Bundle pBundle) {
        Intent intent = new Intent(context, pClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
    }

    /**
     * 通过Action启动Activity
     *
     * @param pAction
     */
    protected void openActivity(Context cotnext, String pAction) {
        openActivity(cotnext, pAction, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param pAction
     * @param pBundle
     */
    protected void openActivity(Context cotnext, String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        cotnext.startActivity(intent);
    }





}
