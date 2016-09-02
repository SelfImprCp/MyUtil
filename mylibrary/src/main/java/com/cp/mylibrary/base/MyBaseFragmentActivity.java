package com.cp.mylibrary.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.cp.mylibrary.app.MyBaseApp;
import com.cp.mylibrary.event.BaseEvent;
import com.cp.mylibrary.utils.ActivityManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import de.greenrobot.event.EventBus;

/**
 * Created by Jerry on 2016/7/7.
 */
public abstract class MyBaseFragmentActivity extends FragmentActivity {
    public Context mContext;
    //为状态栏着色
    public SystemBarTintManager tintManager ;
    @Override
    public void onCreate(Bundle savedInstanceState    ) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActivityManagerUtil.getInstance().pushActivty(this);



        mContext = MyBaseApp.getInstance();
        super.onCreate(savedInstanceState   );



        //只对api19以上版本有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        //为状态栏着色
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);




        setContentView(setRootView());
        initView();



        EventBus.getDefault().register(this);

    }

    /**
     *  设置布局
     * @return
     */
    protected abstract int setRootView();

    /**
     * 初始化控件
     */
    protected abstract  void initView();

    public void onEvent(BaseEvent event) {
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



}
