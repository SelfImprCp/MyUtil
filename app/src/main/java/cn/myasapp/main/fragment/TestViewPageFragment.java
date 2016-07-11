package cn.myasapp.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.base.BaseListFragment;
import com.cp.mylibrary.interf.OnTabReselectListener;
import com.cp.mylibrary.utils.GsonUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.myasapp.R;
import cn.myasapp.main.adapter.MainFocusAdapter;
import cn.myasapp.main.api.TestApi;
import cn.myasapp.main.bean.MainFocus;
import cn.myasapp.main.event.TestEvent;
import cn.myasapp.main.res.MainFocusListRes;


/**
 * 开户个人信息界面
 * Created by Jerry on 2016/6/21.
 */
public class TestViewPageFragment extends BaseListFragment
    {

        @Override
        protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
            return inflater.inflate(R.layout.fragment_test1,container,false);
        }

        @Override
        public void initView(View view) {
            super.initView(view);

            mAdapter = new MainFocusAdapter(getActivity());


            mListView.setAdapter(mAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MainFocus mainFocus = (MainFocus) mData.get(position);
                    ShowToastUtil.showToast(getActivity(),"点击了哪一个，TestViewPageFragment ," +mainFocus.getTitle() );
                }
            });



        }

        @Override
        protected void requestData() {
            super.requestData();
            TestApi.getTestPageList(mCurrentPage+"", responseHandler);


        }


        @Override
        protected List parseList(String is) {

            MainFocusListRes  res = GsonUtil.jsonStrToBean(is,MainFocusListRes.class);

            return res.getResult();
        }






    }
