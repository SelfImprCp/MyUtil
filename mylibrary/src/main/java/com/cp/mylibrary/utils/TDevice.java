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

	
	//////////////////////////////单位转换///////////////////////////////////////////
	
//	public static float dpToPixel(float dp) {
//		return dp * (getDisplayMetrics().densityDpi / 160F);
//	}
//	public static float pixelsToDp(float f) {
//		return f / (getDisplayMetrics().densityDpi / 160F);
//	}
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

	/**
	* 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	*/
	public static int dip2px(Context context, float dpValue) {
	final float scale = context.getResources().getDisplayMetrics().density;
	return (int) (dpValue * scale + 0.5f);
	}

	/**
	* 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	*/
	public static int px2dip(Context context, float pxValue) {
	final float scale = context.getResources().getDisplayMetrics().density;
	return (int) (pxValue / scale + 0.5f);
	}


	public static int getDefaultLoadFactor(Context context) {
		if (_loadFactor == null) {
			Integer integer = Integer.valueOf(0xf &  context
					.getResources().getConfiguration().screenLayout);
			_loadFactor = integer;
			_loadFactor = Integer.valueOf(Math.max(integer.intValue(), 1));
		}
		return _loadFactor.intValue();
	}

//	public static float getDensity() {
//		if (displayDensity == 0.0)
//			displayDensity = getDisplayMetrics().density;
//		return displayDensity;
//	}



	 ////////////////////////////////////////屏幕宽高///////////////////////////////////////////////////////


	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		((WindowManager) context.getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
				displaymetrics);
		return displaymetrics;
	}


	public static float getScreenHeight(Context context) {
		return getDisplayMetrics(context).heightPixels;
	}

//	public static float getScreenWidth(Context context){
//		return getDisplayMetrics(context).widthPixels;
//	}

	public static int[] getRealScreenSize(Activity activity) {
		int[] size = new int[2];
		int screenWidth = 0, screenHeight = 0;
		WindowManager w = activity.getWindowManager();
		Display d = w.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		d.getMetrics(metrics);
		// since SDK_INT = 1;
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
			try {
				screenWidth = (Integer) Display.class.getMethod("getRawWidth")
						.invoke(d);
				screenHeight = (Integer) Display.class
						.getMethod("getRawHeight").invoke(d);
			} catch (Exception ignored) {
			}
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 17)
			try {
				Point realSize = new Point();
				Display.class.getMethod("getRealSize", Point.class).invoke(d,
						realSize);
				screenWidth = realSize.x;
				screenHeight = realSize.y;
			} catch (Exception ignored) {
			}
		size[0] = screenWidth;
		size[1] = screenHeight;
		return size;
	}






	/**
	 * 获取屏幕的宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕的高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 获取屏幕的宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		int mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;// 获取屏幕分辨率宽度

		Log.d("tag", "mScreenWidth" + mScreenWidth);
		return mScreenWidth;

	}

	/**
	 * 获取屏幕的高度
	 *
	 * @param context
	 * @return
	 */
	public static int getHeightPixels(Context context) {
		int mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;// 获取屏幕分辨率宽度

		return mScreenHeight;
	}










	 ////////////////////////////软件盘//////////////////////////////////////
	/**
	 *
	 * @param view
	 */
	public static void hideSoftKeyboard(View view,Context context) {
		if (view == null)
			return;
		((InputMethodManager)  context.getSystemService(
				Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
				view.getWindowToken(), 0);
	}
   /**
    *
    * @param view
    */
	public static void showSoftKeyboard(View view,Context context) {
		((InputMethodManager)  context.getSystemService(
				Context.INPUT_METHOD_SERVICE)).showSoftInput(view,
				InputMethodManager.SHOW_FORCED);
	}



		/**
		 * 关闭键盘
		 */
		 public static void closeInput(Activity cotnext) {
			InputMethodManager inputMethodManager = (InputMethodManager)cotnext. getSystemService(Context.INPUT_METHOD_SERVICE);
			if (inputMethodManager != null && ( cotnext.getCurrentFocus() != null)) {
				inputMethodManager.hideSoftInputFromWindow(cotnext.getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}


	////////////////////////////////横竖屏/////////////////////////////////////
	public static boolean isLandscape(Context context) {
		boolean flag;
		if ( context.getResources().getConfiguration().orientation == 2)
			flag = true;
		else
			flag = false;
		return flag;
	}

	public static boolean isPortrait(Context context) {
		boolean flag = true;
		if ( context.getResources().getConfiguration().orientation != 1)
			flag = false;
		return flag;
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
			ShowToastUtil.showToast(context,"你手机中没有安装应用市场！");
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



	////////////////////////////////设置，取消全屏/////////////////////////////////////////////
	public static void setFullScreen(Activity activity) {
		WindowManager.LayoutParams params = activity.getWindow()
				.getAttributes();
		params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		activity.getWindow().setAttributes(params);
		activity.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}

	public static void cancelFullScreen(Activity activity) {
		WindowManager.LayoutParams params = activity.getWindow()
				.getAttributes();
		params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
		activity.getWindow().setAttributes(params);
		activity.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}

	public static PackageInfo getPackageInfo(Context context,String pckName) {
		try {
			return  context.getPackageManager()
					.getPackageInfo(pckName, 0);
		} catch (NameNotFoundException e) {
			LogCp.error(e.getMessage());
		}
		return null;
	}

	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			versionCode =  context
					.getPackageManager()
					.getPackageInfo( context.getPackageName(),
							0).versionCode;
		} catch (NameNotFoundException ex) {
			versionCode = 0;
		}
		return versionCode;
	}


//
//	public static boolean isScreenOn() {
//		PowerManager pm = (PowerManager)  AppContext.getInstance()
//				.getSystemService(Context.POWER_SERVICE);
//		return pm.isScreenOn();
//	}

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

	public static void openDial(Context context, String number) {
		Uri uri = Uri.parse("tel:" + number);
		Intent it = new Intent(Intent.ACTION_DIAL, uri);
		context.startActivity(it);
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

//	public static void openCamera(Context context) {
//		Intent intent = new Intent(); // 调用照相机
//		intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
//		intent.setFlags(0x34c40000);
//		context.startActivity(intent);
//	}

	public static String getIMEI(Context context) {
		TelephonyManager tel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tel.getDeviceId();
	}

//	public static String getPhoneType() {
//		return Build.MODEL;
//	}

//	public static boolean isWifiOpen() {
//		boolean isWifiConnect = false;
//		ConnectivityManager cm = (ConnectivityManager)  AppContext.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
//		// check the networkInfos numbers
//		NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
//		for (int i = 0; i < networkInfos.length; i++) {
//			if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
//				if (networkInfos[i].getType() == ConnectivityManager.TYPE_MOBILE) {
//					isWifiConnect = false;
//				}
//				if (networkInfos[i].getType() == ConnectivityManager.TYPE_WIFI) {
//					isWifiConnect = true;
//				}
//			}
//		}
//		return isWifiConnect;
//	}



	@SuppressWarnings("deprecation")
//	public static void copyTextToBoard(String string) {
//		if (TextUtils.isEmpty(string))
//			return;
//		ClipboardManager clip = (ClipboardManager) AppContext.getInstance()
//				.getSystemService(Context.CLIPBOARD_SERVICE);
//		clip.setText(string);
//		UIHelper.showToast(R.string.copy_success);
//	}

	/**
	 * 发送邮件
	 *
	 * @param context
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param emails
	 *            邮件地址
	 */
	public static void sendEmail(Context context, String subject,
			String content, String... emails) {
		try {
			Intent intent = new Intent(Intent.ACTION_SEND);
			// 模拟器
			// intent.setType("text/plain");
			intent.setType("message/rfc822"); // 真机
			intent.putExtra(Intent.EXTRA_EMAIL, emails);
			intent.putExtra(Intent.EXTRA_SUBJECT, subject);
			intent.putExtra(Intent.EXTRA_TEXT, content);
			context.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}
	}

//	public static int getStatuBarHeight() {
//		Class<?> c = null;
//		Object obj = null;
//		Field field = null;
//		int x = 0, sbar = 38;// 默认为38，貌似大部分是这样的
//		try {
//			c = Class.forName("com.android.internal.R$dimen");
//			obj = c.newInstance();
//			field = c.getField("status_bar_height");
//			x = Integer.parseInt(field.get(obj).toString());
//			sbar =  AppContext.getInstance().getResources()
//					.getDimensionPixelSize(x);
//
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		return sbar;
//	}

//	public static int getActionBarHeight(Context context) {
//		int actionBarHeight = 0;
//		TypedValue tv = new TypedValue();
//		if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,
//				tv, true))
//			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
//					context.getResources().getDisplayMetrics());
//
//		/*
//		 * if (actionBarHeight == 0 &&
//		 * context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true))
//		 * { actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
//		 * context.getResources().getDisplayMetrics()); }
//		 */
//
//		return actionBarHeight;
//	}
//
//	public static boolean hasStatusBar(Activity activity) {
//		WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
//		if ((attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
//			return false;
//		} else {
//			return true;
//		}
//	}

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
	 * 检查手机存储卡
	 * 
	 * @param mContext
	 */
	public void checkMemoryCard(final Context mContext) {
		if (!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			new AlertDialog.Builder(mContext)
					.setTitle("检测内存卡")
					.setMessage("请检查内存卡")
					.setPositiveButton("设置",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									Intent intent = new Intent(
											Settings.ACTION_SETTINGS);
									mContext.startActivity(intent);
								}
							})
					.setNegativeButton("退出",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();

								}
							}).create().show();

		}

	}

}
