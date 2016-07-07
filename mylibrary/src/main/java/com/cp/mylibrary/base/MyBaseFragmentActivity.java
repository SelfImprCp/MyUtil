package com.cp.mylibrary.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Jerry on 2016/7/7.
 */
public abstract class MyBaseFragmentActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState    ) {
        super.onCreate(savedInstanceState   );

        setContentView(setRootView());
        initView();

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



}
