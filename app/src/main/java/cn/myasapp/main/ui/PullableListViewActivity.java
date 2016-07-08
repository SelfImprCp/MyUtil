package cn.myasapp.main.ui;

import com.cp.mylibrary.base.BaseListActivity;
import com.cp.mylibrary.utils.GsonUtil;

import java.util.List;

import cn.myasapp.R;
import cn.myasapp.main.adapter.MainFocusAdapter;
import cn.myasapp.main.api.TestApi;
import cn.myasapp.main.res.MainFocusListRes;


public class PullableListViewActivity extends BaseListActivity {



    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_listview_test);

    }


    @Override
    protected void initView() {
        super.initView();



        mAdapter = new MainFocusAdapter(this);


        content_view.setAdapter(mAdapter);
    }

    @Override
    public void initMyData() {
        super.initMyData();

        requestData( );

    }

    @Override
    protected void requestData(   ) {
        super.requestData( );

        TestApi.getTestPageList(mCurrentPage+"", responseHandler);

    }

    @Override
    protected List parseList(String is) {

        MainFocusListRes  res = GsonUtil.jsonStrToBean(is,MainFocusListRes.class);

        return res.getResult();
    }



}
