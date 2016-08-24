package cn.myasapp.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.cp.mylibrary.base.MyBaseFragment;
import cn.myasapp.main.R;

/**
 * 开户个人信息界面
 * Created by Jerry on 2016/6/21.
 */
public class TestViewPageFragment2 extends MyBaseFragment implements View.OnClickListener {


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_test2, container, false);
        return view;
    }




}
