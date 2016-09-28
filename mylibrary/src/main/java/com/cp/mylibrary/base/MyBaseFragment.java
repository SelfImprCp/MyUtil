package com.cp.mylibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cp.mylibrary.app.MyBaseApp;
import com.cp.mylibrary.event.BaseEvent;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.SupportFragment;

import de.greenrobot.event.EventBus;


/**
 * Created by Jerry on 2016/7/7.
 */
public class MyBaseFragment extends SupportFragment {
    protected LayoutInflater mInflater;
    public Context mContext;

    public View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);


        if (!NetWorkUtil.hasInternetConnected(getActivity())) {

            ShowToastUtil.showToast(getActivity(), "请检查网络");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;

        mContext = MyBaseApp.getInstance();
//
//        if (view == null) {
//
//
//
//
//        }


        view = inflaterView(inflater, container, savedInstanceState);


        //加载界面
        initView(view);
        // 处理数据
        initData();


        //http://blog.csdn.net/hack8/article/details/25432503
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null)
            parent.removeView(view);

        return view;
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        //     EventBus.getDefault().unregister(this);

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public void onEvent(BaseEvent event) {
    }
}
