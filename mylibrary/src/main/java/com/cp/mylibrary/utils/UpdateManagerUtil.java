package com.cp.mylibrary.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;

import com.cp.mylibrary.R;
import com.cp.mylibrary.api.MyResponseHandler;
import com.cp.mylibrary.dialog.BasicDialog;
import com.cp.mylibrary.dialog.DialogHelper;
import com.cp.mylibrary.dialog.WaitDialog;
import com.cp.mylibrary.interf.ICallbackResult;
import com.cp.mylibrary.res.UpdateRes;
import com.cp.mylibrary.service.DownloadService;

import java.io.File;


/**
 * 更新管理工具包
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年11月18日 下午4:21:00
 */

public abstract class UpdateManagerUtil {

    // 服务器返回 的版本信息
    // private UpdateRes mUpdate;

    private Context mContext;

    private boolean isShow = false;

//    private boolean isShowTT = false;

    private WaitDialog _waitDialog;
    private Dialog simplecDialog;
    public Class mActivity;

    /**
     * @param context
     * @param
     * @param
     */
    public UpdateManagerUtil(Context context) {
        this.mContext = context;

    }

    /**
     * 正在获取版本信息的弹框
     *
     * @param
     */
    public void setShowDialog(boolean isShow) {

        this.isShow = isShow;

    }


    /**
     * 从服务器猎取版本信息
     */
    public void checkUpdate() {
        if (isShow) {
            showCheckDialog();
        }

        //  MoFoxApi.getver(mCheckUpdateHandle);
        getServerUpdate();
    }


    /**
     * 子类要实现的方法 ，从服务器取数据
     */
    public abstract void getServerUpdate();

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
//    private MyResponseHandler mCheckUpdateHandle = new MyResponseHandler() {
//
//        @Override
//        public void dataSuccess(String res) {
//            LogCp.i(LogCp.CP, UpdateManagerUtil.class + "更新返回 ，，" + res);
//
//            UpdateRes mUpdate = GsonUtil.jsonStrToBean(res, UpdateRes.class);
//            onFinshCheck(mUpdate);
//        }
//
//        @Override
//        public void dataFinish() {
//            hideCheckDialog();
//        }
//
//        @Override
//        public void dataFailuer(int errorNo, String strMsg) {
//
//
//        }
//
//
//
//    };

    /**
     * 处理从服务器返回 的版本信息
     * 最后一个参数，是否弹出选择更新的框
     *
     * @param updateRes
     */
    public void onFinshCheck(UpdateRes updateRes, String currentVersion, boolean isDialogSelect) {
        if (haveNew(mContext, updateRes, currentVersion)) {

            if (isDialogSelect) {
                showUpdateInfoDialog(updateRes);
            } else {
                showUpdateInfo(updateRes);
            }

        } else {
            if (isShow) {
                showLatestDialog(mContext);
            }
        }
    }

    /**
     * 检查 是否有新版本
     *
     * @param updateRes
     * @return
     */
    public boolean haveNew(Context context, UpdateRes updateRes, String currentVersion) {
        if (updateRes == null) {
            return false;
        }
        boolean haveNew = false;

//        String curVersionName = getVersionNameAndVersionCode(context);
//


        LogCp.i(LogCp.CP, UpdateManagerUtil.class + "取得的版本，" + currentVersion + " 传来的版本" + updateRes.getVersion());

        if (!currentVersion.equals(updateRes.getVersion())) {
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
     * 有新版本， 不弹出是否更新的弹框
     *
     * @param updateRes
     */
    private void showUpdateInfo(final UpdateRes updateRes) {
        if (updateRes == null) {
            return;
        }

        // 启动下载新版本的服务
        openDownLoadService(mContext, updateRes.getUrl(),
                updateRes.getVersion());

        ShowToastUtil.showToast(mContext, "开始下载新版本，下载完后会自动安装");


    }

    /**
     * 有新版本，弹出是否更新的弹框
     *
     * @param updateRes
     */

    private void showUpdateInfoDialog(final UpdateRes updateRes) {
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


                        if (arg0.getId() == R.id.base_config_dialog_sure_btn)

                        {
                            // 启动下载新版本的服务
                            openDownLoadService(mContext, updateRes.getUrl(),
                                    updateRes.getVersion());

                            ShowToastUtil.showToast(mContext, "开始下载新版本，下载完后会自动安装");
                            simplecDialog.dismiss();
                        } else if (arg0.getId() == R.id.base_config_dialog_cannel_btn) {
                            simplecDialog.dismiss();
                        }


                    }
                }).getConfigDialog();

        simplecDialog.show();

    }

    /**
     * 已经 是最新版本了
     */
    private void showLatestDialog(Context context) {

        ShowToastUtil.showToast(context, "已是最新版本哦  ^_~");
    }

    /**
     *
     */
//    private void showFaileDialog() {
//        CommonDialog dialog = DialogHelper
//                .getPinterestDialogCancelable(mContext);
//        dialog.setMessage("网络异常，无法获取新版本信息");
//        dialog.setPositiveButton("", null);
//        dialog.show();
//    }

    /**
     * 取得 当前应用版本号
     *
     * @param packageName
     * @return
     */
    public static int getVersionCode(Context context, String packageName) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager()
                    .getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }


    public static void openApp(Context context, String packageName) {
        Intent mainIntent = context.getPackageManager()
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
        if (isPackageExist(context, packageName)) {
            Uri packageURI = Uri.parse("package:" + packageName);
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE,
                    packageURI);
            context.startActivity(uninstallIntent);
        }
    }

    public static boolean isPackageExist(Context context, String pckName) {
        try {
            PackageInfo pckInfo = context.getPackageManager()
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
                DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
                binder.addCallback(callback);
                binder.start();

                LogCp.i(LogCp.CP,  "   启动下载  版本   "  );


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

//    @TargetApi(11)
//    public static void recreate(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            activity.recreate();
//        }
//    }
//
//    @TargetApi(11)
//    public static void setLayerType(View view, int layerType, Paint paint) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            view.setLayerType(layerType, paint);
//        }
//    }
//
//    @TargetApi(14)
//    public static void setUiOptions(Window window, int uiOptions) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            window.setUiOptions(uiOptions);
//        }
//    }


    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionNameAndVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);


            return packageInfo.versionName + "." + packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
