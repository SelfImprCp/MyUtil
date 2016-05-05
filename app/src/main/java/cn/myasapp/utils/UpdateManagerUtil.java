package cn.myasapp.utils;

import java.io.File;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;

import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;


/**
 * 更新管理工具包
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年11月18日 下午4:21:00
 * 
 */

public class UpdateManagerUtil {

	// 服务器返回 的版本信息
	// private UpdateRes mUpdate;

	private Context mContext;

	private boolean isShow = false;

	private boolean isShowTT = false;

	private WaitDialog _waitDialog;
	private Dialog simplecDialog;

	/**
	 * 
	 * @param context
	 * @param isShow
	 *            是否显示 正在获取版本信息的弹框
	 * @param isShowTT
	 */
	public UpdateManagerUtil(Context context, boolean isShow, boolean isShowTT) {
		this.mContext = context;
		this.isShow = isShow;
		this.isShowTT = isShowTT;
	}

	/**
	 * 从服务器猎取版本信息
	 */
	public void checkUpdate() {
		if (isShow) {
			showCheckDialog();
		}
		MoFoxApi.getver(mCheckUpdateHandle);

	}

	/**
	 * 是否显示 正在猎取 的弹框
	 */
	private void showCheckDialog() {
		if (_waitDialog == null) {
			_waitDialog = DialogHelper.getWaitDialog((Activity) mContext,
					"正在获取新版本信息...");
		}
		_waitDialog.show();
	}

	/**
	  *  
	  */
	private AsyncHttpResponseHandler mCheckUpdateHandle = new AsyncHttpResponseHandler() {

		public void onSuccess(String arg0) {

			LogCp.i(LogCp.CP, UpdateManagerUtil.class + "更新返回 ，，" + arg0);

			UpdateRes mUpdate = GsonUtil.jsonStrToBean(arg0, UpdateRes.class);
			onFinshCheck(mUpdate);

		};

		public void onFailure(Throwable arg0) {
			arg0.printStackTrace();

			if (isShowTT) {
				UIHelper.showToast("已是最新版本哦  ^_~ ");
			}

		};

		public void onFinish() {
			hideCheckDialog();

		};

	};

	/**
	 * 处理从服务器返回 的版本信息
	 * 
	 * @param updateRes
	 */
	private void onFinshCheck(UpdateRes updateRes) {
		if (haveNew(updateRes)) {
			showUpdateInfo(updateRes);
		} else {
			if (isShow) {
				showLatestDialog();
			}
		}
	}

	/**
	 * 检查 是否有新版本
	 * 
	 * @param updateRes
	 * @return
	 */
	public boolean haveNew(UpdateRes updateRes) {
		if (updateRes == null) {
			return false;
		}
		boolean haveNew = false;

		String curVersionName = getVersionName();

		if (!curVersionName.equals(updateRes.getVersion())) {
			haveNew = true;
		}

		return haveNew;
	}

	/**
	 * 隐藏更新的弹框
	 */
	private void hideCheckDialog() {
		if (_waitDialog != null) {
			_waitDialog.dismiss();
		}
	}

	/**
	 * 有新版本，弹出是否更新的弹框
	 * 
	 * @param updateRes
	 */

	private void showUpdateInfo(final UpdateRes updateRes) {
		if (updateRes == null) {
			return;
		}
		/*
		 * CommonDialog dialog = DialogHelper
		 * .getPinterestDialogCancelable(mContext); dialog.setTitle("");
		 * dialog.setMessage(mUpdate.getDesc()); dialog.setNegativeButton("取消",
		 * null); dialog.setPositiveButton("更新版本", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * 
		 * UIHelper.openDownLoadService(mContext, mUpdate.getUrl(),
		 * mUpdate.getVersion()); dialog.dismiss();
		 * 
		 * } }); dialog.show();
		 */

		simplecDialog = BasicDialog.versionDialog(mContext, "发现新版本",
				updateRes.getDesc(), "立即更新", "下次再说", new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						switch (arg0.getId()) {
						case R.id.base_config_dialog_sure_btn:
							// 启动下载新版本的服务
							openDownLoadService(mContext, updateRes.getUrl(),
									updateRes.getVersion());

							UIHelper.showToast("开始下载新版本，下载完后会自动安装");
							simplecDialog.dismiss();
							break;
						case R.id.base_config_dialog_cannel_btn:
							simplecDialog.dismiss();

							break;

						default:
							break;
						}

					}
				}).getConfigDialog();

		simplecDialog.show();

	}

	/**
	 * 已经 是最新版本了
	 */
	private void showLatestDialog() {
		// CommonDialog dialog = DialogHelper
		// .getPinterestDialogCancelable(mContext);
		// dialog.setMessage("已经是最新版本了");
		// dialog.setPositiveButton("", null);

		// dialog.show();

		UIHelper.showToast("已是最新版本哦  ^_~");
	}

	/**
	  *  
	  */
	private void showFaileDialog() {
		CommonDialog dialog = DialogHelper
				.getPinterestDialogCancelable(mContext);
		dialog.setMessage("网络异常，无法获取新版本信息");
		dialog.setPositiveButton("", null);
		dialog.show();
	}

	/**
	 * 取得 当前应用版本号
	 * 
	 * @param packageName
	 * @return
	 */
	public static int getVersionCode(String packageName) {
		int versionCode = 0;
		try {
			versionCode = BaseApplication.context().getPackageManager()
					.getPackageInfo(packageName, 0).versionCode;
		} catch (NameNotFoundException ex) {
			versionCode = 0;
		}
		return versionCode;
	}

	/**
	 * 取得当前版本名
	 * 
	 * @return
	 */
	public static String getVersionName() {
		String name = "";
		int versionCode = 0;

		try {
			name = BaseApplication
					.context()
					.getPackageManager()
					.getPackageInfo(BaseApplication.context().getPackageName(),
							0).versionName;

			versionCode = BaseApplication
					.context()
					.getPackageManager()
					.getPackageInfo(BaseApplication.context().getPackageName(),
							0).versionCode;
		} catch (NameNotFoundException ex) {
			name = "";
		}
		return name + "." + versionCode;
	}

	public static void openApp(Context context, String packageName) {
		Intent mainIntent = BaseApplication.context().getPackageManager()
				.getLaunchIntentForPackage(packageName);
		if (mainIntent == null) {
			mainIntent = new Intent(packageName);
		} else {
			LogCp.log("Action:" + mainIntent.getAction());
		}
		context.startActivity(mainIntent);
	}

	public static boolean openAppActivity(Context context, String packageName,
			String activityName) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		ComponentName cn = new ComponentName(packageName, activityName);
		intent.setComponent(cn);
		try {
			context.startActivity(intent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void uninstallApk(Context context, String packageName) {
		if (isPackageExist(packageName)) {
			Uri packageURI = Uri.parse("package:" + packageName);
			Intent uninstallIntent = new Intent(Intent.ACTION_DELETE,
					packageURI);
			context.startActivity(uninstallIntent);
		}
	}

	public static boolean isPackageExist(String pckName) {
		try {
			PackageInfo pckInfo = BaseApplication.context().getPackageManager()
					.getPackageInfo(pckName, 0);
			if (pckInfo != null)
				return true;
		} catch (NameNotFoundException e) {
			LogCp.error(e.getMessage());
		}
		return false;
	}

	// =========================版本更新===============================================

	public static void openDownLoadService(Context context, String downurl,
			String tilte) {
		final ICallbackResult callback = new ICallbackResult() {

			@Override
			public void OnBackResult(Object s) {
			}
		};
		ServiceConnection conn = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				DownloadBinder binder = (DownloadBinder) service;
				binder.addCallback(callback);
				binder.start();

			}
		};
		Intent intent = new Intent(context, DownloadService.class);
		intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, downurl);
		intent.putExtra(DownloadService.BUNDLE_KEY_TITLE, tilte);
		context.startService(intent);
		context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}

	// ///////////////////////////////////////
	// Android各版本的兼容方法////////////////////////////////////////
	@TargetApi(5)
	public static void overridePendingTransition(Activity activity,
			int enter_anim, int exit_anim) {
		activity.overridePendingTransition(enter_anim, exit_anim);
	}

	@TargetApi(7)
	public static Bitmap getThumbnail(ContentResolver cr, long origId,
			int kind, Options options) {
		return MediaStore.Images.Thumbnails.getThumbnail(cr, origId, kind,
				options);
	}

	@TargetApi(8)
	public static File getExternalCacheDir(Context context) {

		// // return context.getExternalCacheDir(); API level 8
		//
		// // e.g. "<sdcard>/Android/data/<package_name>/cache/"
		// final File extCacheDir = new
		// File(Environment.getExternalStorageDirectory(),
		// "/Android/data/" + context.getApplicationInfo().packageName +
		// "/cache/");
		// extCacheDir.mkdirs();
		// return extCacheDir;

		return context.getExternalCacheDir();
	}

	@TargetApi(11)
	public static void recreate(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			activity.recreate();
		}
	}

	@TargetApi(11)
	public static void setLayerType(View view, int layerType, Paint paint) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			view.setLayerType(layerType, paint);
		}
	}

	@TargetApi(14)
	public static void setUiOptions(Window window, int uiOptions) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			window.setUiOptions(uiOptions);
		}
	}

}
