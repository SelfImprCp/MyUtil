package com.cp.mylibrary.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.cp.mylibrary.app.MyBaseApp;

import com.cp.mylibrary.event.BaseEvent;
import com.cp.mylibrary.utils.ActivityManagerUtil;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.ShowToastUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;


import org.kymjs.kjframe.KJActivity;

import de.greenrobot.event.EventBus;


/**
 * Created by Jerry on 2016/7/6.
 */
public class MyBaseActivity extends KJActivity {

   public Context mContext;
    //为状态栏着色
  public SystemBarTintManager tintManager ;


    private static final int REQUECT_CODE_SDCARD = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActivityManagerUtil.getInstance().pushActivty(this);



       mContext = MyBaseApp.getInstance();



        super.onCreate(savedInstanceState);






        //只对api19以上版本有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        //为状态栏着色
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);


        EventBus.getDefault().register(this);


    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }





    @Override
    protected void onDestroy() {

        ((MyBaseApp) this.getApplication()).getActivityManager().finishActivity(
                this);
        mContext = null;

        super.onDestroy();



   //     EventBus.getDefault().unregister(this);
    }

    public void onEvent(BaseEvent event) {
    }




    @Override
    public void initWidget() {
        super.initWidget();

        initView();
    }

    @Override
    public void initData() {
        super.initData();

        initMyData();
    }

    /**
     * 子类复写，初始化UI
     */
    protected void initView() {
        if (!NetWorkUtil.hasInternetConnected(this)) {

            ShowToastUtil.showToast(this,"请检查网络");
        }


        getPromission();


    }


    @Override
    public void setRootView() {

    }

    /**
     * 子类复写，初始化数据
     */
    public void initMyData() {
    }


    @Override
    protected void onStart() {

        super.onStart();
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    /**
     * 检查权限
     */
    private  void getPromission()
     {


          // 读取sdcard 权限
         if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(MyBaseActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

             LogCp.i(LogCp.CP, " 已经有了读取sdcard 的权限!");
         } else {
             //do not have permission
             LogCp.i(LogCp.CP, "  没有 读取sdcard 的权限!");
             // Should we show an explanation?
             if (ActivityCompat.shouldShowRequestPermissionRationale(MyBaseActivity.this,
                     Manifest.permission.READ_EXTERNAL_STORAGE)) {


             } else {


             }
         }



         // 写入sdcard 权限
         if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(MyBaseActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

             LogCp.i(LogCp.CP, " 已经有了写入sdcard 的权限!");
         } else {
             //do not have permission
             LogCp.i(LogCp.CP, "  没有 写入sdcard 的权限!");
             // Should we show an explanation?
             if (ActivityCompat.shouldShowRequestPermissionRationale(MyBaseActivity.this,
                     Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


             } else {


             }
         }

     }



}
