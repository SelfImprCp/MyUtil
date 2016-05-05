package cn.myasapp.utils;

import cn.mofox.client.app.AppContext;
import cn.mofox.client.base.BaseApplication;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;



 /**
  *  手机网络工具类，，
  * @author Administrator
  *
  */
public class InternetUtil {
	// 手机网络类型
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;
	 
	/**
	 * 检查 有无网络
	 */

	public static void noIntent() {
		if (!InternetUtil.hasInternetConnected())
			return;
	}
	
	/*
	 * 判断是否有网络连接，若没有，则弹出网络设置对话框，返回false
	 */
 
	public boolean validateInternet(Context mContext ) {
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
	

	/*
	 * 打开网络设置对话框
	 */
	private void openWirelessSet(final Context mContext) {

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
	 * 获取当前网络类型
	 * 
	 * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
	 */
	public static int getNetworkType() {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) AppContext
				.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
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
	 * 判断是否有网络连接,没有返回false
	 */
 
	public static boolean hasInternetConnected() {
		ConnectivityManager manager = (ConnectivityManager) BaseApplication.context()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager != null) {
			NetworkInfo network = manager.getActiveNetworkInfo();
			if (network != null && network.isConnectedOrConnecting()) {
				return true;
			}
		}
		return false;
	}

    public static boolean hasInternet() {
	boolean flag;
	if (((ConnectivityManager) BaseApplication.context().getSystemService(
		"connectivity")).getActiveNetworkInfo() != null)
	    flag = true;
	else
	    flag = false;
	return flag;
    }

	/*public static boolean hasInternet() {
		boolean flag;
		if (((ConnectivityManager) BaseApplication.context().getSystemService(
				"connectivity")).getActiveNetworkInfo() != null)
			flag = true;
		else {
			UIHelper.showToast("网络连接失败，请检查网络");
			flag = false;
		}
		return flag;
	}
	*/

}
