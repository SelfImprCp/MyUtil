package cn.myasapp.main.ui;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.base.XRefreshRecyclerViewActivity;
import com.cp.mylibrary.pullto.XRefreshView;
import com.cp.mylibrary.utils.GsonUtil;
import com.cp.mylibrary.utils.LogCp;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

import cn.myasapp.R;
import cn.myasapp.main.adapter.MainFocusAdapter;
import cn.myasapp.main.adapter.SimpleAdapter;
import cn.myasapp.main.api.TestApi;
import cn.myasapp.main.bean.MainFocus;
import cn.myasapp.main.res.MainFocusListRes;

import com.cp.mylibrary.pullto.XRefreshView.SimpleXRefreshListener;
import com.cp.mylibrary.utils.ShowToastUtil;

/**
 * Created by Jerry on 2016/7/13.
 */
public class TestRecyclerActivity extends XRefreshRecyclerViewActivity {







    @Override
    public void setRootView() {
        super.setRootView();
      setContentView(R.layout.activity_recycler_refresh_layout);

    }




    @Override
    protected SimpleAdapter getmAdapter() {
        SimpleAdapter mAdapter = new SimpleAdapter(this);
        return mAdapter;
    }
    @Override
    protected void initView() {
        super.initView();


    }




    @Override
    protected void requestData() {
        super.requestData();

        TestApi.getTestPageList(mCurrentPage + "", responseHandler);

    }

    @Override
    protected List parseList(String is) {

        MainFocusListRes res = GsonUtil.jsonStrToBean(is, MainFocusListRes.class);

        return res.getResult();
    }



}
