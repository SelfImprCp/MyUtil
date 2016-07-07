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


    public static void openSMS(Context context, String smsBody, String tel) {
        Uri uri = Uri.parse("smsto:" + tel);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsBody);
        context.startActivity(it);
    }


    public static void openDail(Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openSendMsg(Context context) {
        Uri uri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
