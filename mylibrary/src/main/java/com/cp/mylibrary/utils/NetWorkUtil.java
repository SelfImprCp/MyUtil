package com.cp.mylibrary.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;


/**
 * 手机网络工具类，，
 *
 * @author Administrator
 */
public class NetWorkUtil {
    // 手机网络类型
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;


   /**
    *  pass
    *
    * 判断是否有网络连接，若没有，则弹出网络设置对话框，返回false
    */

    public boolean validateInternetIfNoShowDialog(Context mContext) {
        ConnectivityManager manager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            openWirelessSet(mContext);
            return false;
        } else {
            NetworkInfo[] info = manager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        openWirelessSet(mContext);
        return false;
    }


    /**
     *  pass
     *
     * 打开网络设置对话框
     */
    public void openWirelessSet(final Context mContext) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder
                .setTitle("网络设置")
                .setMessage("检查网络")
                .setPositiveButton("网络设置",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                                Intent intent = new Intent(
                                        Settings.ACTION_WIRELESS_SETTINGS);
                                mContext.startActivity(intent);
                            }
                        })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
        dialogBuilder.show();
    }

    /**
     *  pass
     *
     *
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    /**
     *  pass
     *
     * 判断是否是wifi连接
     */
    public static boolean isWifiConn(Context context) {


        return getNetworkType(context) == 1 ? true : false;

    }

    /**
     *  pass
     *
     * 判断是否有网络连接,没有返回false
     */

    public static boolean hasInternetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo network = manager.getActiveNetworkInfo();
            if (network != null && network.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 如果没有网络返回false就不再执行下一步，
     *
     * @return
     */
    public static boolean yesNext(Context context) {
        if (!NetWorkUtil.hasInternetConnected(context)) {
              ShowToastUtil.showToast(context,"没有可用的网络");
            return false;
        } else {
            return true;

        }

    }


}
