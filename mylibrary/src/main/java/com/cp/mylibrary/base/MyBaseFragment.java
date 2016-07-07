package com.cp.mylibrary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kymjs.kjframe.ui.SupportFragment;

/**
 * Created by Jerry on 2016/7/7.
 */
public class MyBaseFragment  extends SupportFragment {
    protected LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;

        View view = inflaterView(inflater, container, savedInstanceState);


        //加载界面
        initView(view);
        // 处理数据
        initData();


        return view;
    }



    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    /**
     * 子类直接复写这个方法,view,就是当前界面的view
     */

    public void initView(View view) {

    }


    /**
     * 子类复写这个方法,设置当前界面的布局
     *
     * @return
     */
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return null;
    }

    /**
     * 子类直接复写这个方法,view, 对当前界面的数据进行处理
     */
    @Override
    public void initData() {

    }
}
