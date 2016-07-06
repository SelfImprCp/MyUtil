package com.cp.mylibrary.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.cp.mylibrary.app.MyBaseApp;
import com.cp.mylibrary.utils.ActivityManagerUtil;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.KJActivity;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyBaseActivity extends KJActivity {

   private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActivityManagerUtil.getInstance().pushActivty(this);
        ((MyBaseApp) this.getApplication()).getActivityManager().pushActivty(
                this);

       mContext = MyBaseApp.getInstance();

        super.onCreate(savedInstanceState);


        // 注册 事件
    //    EventBus.getDefault().register(this);


    }


//    public void onEvent(BaseEvent event) {
//
//    }




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

    @Override
    protected void onDestroy() {
        ((MyBaseApp) this.getApplication()).getActivityManager().popActivity(
                this);
        mContext = null;
      //  EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


}
