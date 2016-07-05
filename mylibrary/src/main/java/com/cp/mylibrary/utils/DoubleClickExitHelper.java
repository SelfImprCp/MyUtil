package com.cp.mylibrary.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;




/***
 * 双击退出
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2015年1月5日 下午7:07:44
 * 
 * 
 *          用法，写在mainActivity 中
 * 
 * 
 * 
 *          监听返回--是否退出程序
 * @Override public boolean onKeyDown(int keyCode, KeyEvent event) { if (keyCode
 *           == KeyEvent.KEYCODE_BACK) { // 是否退出应用 if
 *           (AppContext.get(DoubleClickExitHelper.KEY_DOUBLE_CLICK_EXIT, true))
 *           {
 * 
 *           // mPushAgent.disable(mUnregisterCallback);
 * 
 *           return mDoubleClickExit.onKeyDown(keyCode, event);
 * 
 *           } } return super.onKeyDown(keyCode, event); }
 * 
 */
public class DoubleClickExitHelper {

	private final Activity mActivity;

	private boolean isOnKeyBacking;
	private Handler mHandler;
	private Toast mBackToast;
	// 是否双击退出
	public static final String KEY_DOUBLE_CLICK_EXIT = "KEY_DOUBLE_CLICK_EXIT";

	public DoubleClickExitHelper(Activity activity) {
		mActivity = activity;
		mHandler = new Handler(Looper.getMainLooper());
	}

	/**
	 * Activity onKeyDown事件
	 * */
	public boolean onKeyDown(int keyCode, KeyEvent event ,String strContent) {
		if (keyCode != KeyEvent.KEYCODE_BACK) {
			return false;
		}
		if (isOnKeyBacking) {
			mHandler.removeCallbacks(onBackTimeRunnable);
			if (mBackToast != null) {
				mBackToast.cancel();
			}
			// 退出
			AppExit(mActivity);
			return true;
		} else {
			isOnKeyBacking = true;
			if (mBackToast == null) {
			 mBackToast = Toast.makeText(mActivity, strContent, 2000);
			}
			mBackToast.show();

			//  UIHelper.showToast("再次单击退出MoFox");
			mHandler.postDelayed(onBackTimeRunnable, 2000);
			return true;
		}
	}



	private Runnable onBackTimeRunnable = new Runnable() {

		@Override
		public void run() {
			isOnKeyBacking = false;
			if (mBackToast != null) {
				mBackToast.cancel();
			}
		}
	};

	/*
	 * 停止服务并结束所有的Activity退出应用
	 */

	public void isExit(final Context mContext) {
//		new AlertDialog.Builder(mContext).setTitle("确定退出吗?")
//				.setNeutralButton("确定", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// stopService();
//						AppExit(mContext);
//						AppContext.exit();
//					}
//				})
//				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.cancel();
//					}
//				}).show();

	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			ActivityManagerUtil.getInstance().finishAllActivity();
			// 杀死该应用进程
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		} catch (Exception e) {
		}
	}

}
