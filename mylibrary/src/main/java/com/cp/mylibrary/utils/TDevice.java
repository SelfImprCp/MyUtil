package com.cp.mylibrary.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import java.io.File;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.List;


@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class TDevice {


	public static boolean GTE_HC;
	public static boolean GTE_ICS;
	public static boolean PRE_HC;
	private static Boolean _hasBigScreen = null;
	private static Boolean _hasCamera = null;
	private static Boolean _isTablet = null;
	private static Integer _loadFactor = null;


	public static float displayDensity = 0.0F;

	static {
		GTE_ICS = Build.VERSION.SDK_INT >= 14;
		GTE_HC = Build.VERSION.SDK_INT >= 11;
		PRE_HC = Build.VERSION.SDK_INT >= 11 ? false : true;
	}

	public TDevice() {
	}


	/**
	 * 判断当前版本是否兼容目标版本的方法
	 *
	 * @param VersionCode
	 * @return
	 */
	public static boolean isMethodsCompat(int VersionCode) {
		int currentVersion = Build.VERSION.SDK_INT;
		return currentVersion >= VersionCode;
	}


	public static boolean isZhCN(Context context) {
		String lang = context.getResources()
				.getConfiguration().locale.getCountry();
		if (lang.equalsIgnoreCase("CN")) {
			return true;
		}
		return false;
	}


	/////////////////////////////应用市及安装包//////////////////////////////////////////////


	public static boolean gotoGoogleMarket(Activity activity, String pck) {
		try {
			Intent intent = new Intent();
			intent.setPackage("com.android.vending");
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("market://details?id=" + pck));
			activity.startActivity(intent);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void hideAnimatedView(View view) {
		if (PRE_HC && view != null)
			view.setPadding(view.getWidth(), 0, 0, 0);
	}


	public static void gotoMarket(Context context, String pck) {
		if (!isHaveMarket(context)) {
			ShowToastUtil.showToast(context, "你手机中没有安装应用市场！");
			return;
		}
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=" + pck));
		if (intent.resolveActivity(context.getPackageManager()) != null) {
			context.startActivity(intent);
		}
	}

	public static boolean isHaveMarket(Context context) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.APP_MARKET");
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
		return infos.size() > 0;
	}

	public static void openAppInMarket(Context context) {
		if (context != null) {
			String pckName = context.getPackageName();
			try {
				gotoMarket(context, pckName);
			} catch (Exception ex) {
				try {
					String otherMarketUri = "http://market.android.com/details?id="
							+ pckName;
					Intent intent = new Intent(Intent.ACTION_VIEW,
							Uri.parse(otherMarketUri));
					context.startActivity(intent);
				} catch (Exception e) {

				}
			}
		}
	}



	public static PackageInfo getPackageInfo(Context context, String pckName) {
		try {
			return context.getPackageManager()
					.getPackageInfo(pckName, 0);
		} catch (NameNotFoundException e) {
			LogCp.error(e.getMessage());
		}
		return null;
	}

	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			versionCode = context
					.getPackageManager()
					.getPackageInfo(context.getPackageName(),
							0).versionCode;
		} catch (NameNotFoundException ex) {
			versionCode = 0;
		}
		return versionCode;
	}



	public static void installAPK(Context context, File file) {
		if (file == null || !file.exists())
			return;
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	public static Intent getInstallApkIntent(File file) {
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		return intent;
	}

	public static String getIMEI(Context context) {
		TelephonyManager tel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tel.getDeviceId();
	}


	/**
	 * 调用系统安装了的应用分享
	 *
	 * @param context
	 * @param title
	 * @param url
	 */
	public static void showSystemShareOption(Context context,
											 final String title, final String url) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享：" + title);
		intent.putExtra(Intent.EXTRA_TEXT, title + " " + url);
		context.startActivity(Intent.createChooser(intent, "选择分享"));
	}



	/**
	 * 显示 删除线
	 *
	 * @param
	 * @param
	 * @param shouYI
	 */
	public static void showDeleteLine(TextView peizhifangan_detial_felv_befor, String shouYI) {

		peizhifangan_detial_felv_befor.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		peizhifangan_detial_felv_befor.setText(shouYI);
		peizhifangan_detial_felv_befor.getPaint().setAntiAlias(true);// 抗锯齿


	}




}
	

