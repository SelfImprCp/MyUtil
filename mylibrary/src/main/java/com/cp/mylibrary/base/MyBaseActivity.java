package com.cp.mylibrary.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cp.mylibrary.app.MyBaseApp;

import com.cp.mylibrary.event.BaseEvent;
import com.cp.mylibrary.utils.ActivityManagerUtil;
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



}
