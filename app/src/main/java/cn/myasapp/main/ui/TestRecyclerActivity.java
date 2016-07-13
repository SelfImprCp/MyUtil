package cn.myasapp.main.ui;

import com.cp.mylibrary.adapter.CommonAdapter;
import com.cp.mylibrary.base.XRefreshRecyclerViewActivity;
import com.cp.mylibrary.utils.GsonUtil;

import java.util.List;

import cn.myasapp.R;
import cn.myasapp.main.adapter.RecyclerAdapter;
import cn.myasapp.main.api.TestApi;
import cn.myasapp.main.res.MainFocusListRes;

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
    protected CommonAdapter getAdapter() {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this,mData);


        return recyclerAdapter;
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
