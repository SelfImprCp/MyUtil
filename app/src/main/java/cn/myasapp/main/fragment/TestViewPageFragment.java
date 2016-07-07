package cn.myasapp.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cp.mylibrary.base.MyBaseFragment;

import cn.myasapp.R;


/**
 * 开户个人信息界面
 * Created by Jerry on 2016/6/21.
 */
public class TestViewPageFragment extends MyBaseFragment implements View.OnClickListener {



    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_test1, container, false);
        return view;
    }





}
