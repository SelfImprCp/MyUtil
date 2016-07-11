package cn.myasapp.main.ui;

import android.view.View;
import android.widget.AdapterView;

import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.base.BaseListActivity;
import com.cp.mylibrary.utils.GsonUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import java.util.List;

import cn.myasapp.R;
import cn.myasapp.main.adapter.MainFocusAdapter;
import cn.myasapp.main.api.TestApi;
import cn.myasapp.main.bean.MainFocus;
import cn.myasapp.main.res.MainFocusListRes;


public class PullableListViewActivity extends BaseListActivity {


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_listview_test);

    }

    @Override
    protected ListBaseAdapter getmAdapter() {
        MainFocusAdapter mAdapter = new MainFocusAdapter(this);
        return mAdapter;
    }

    @Override
    protected void initView() {
        super.initView();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainFocus mainFocus = (MainFocus) mData.get(position);
                ShowToastUtil.showToast(PullableListViewActivity.this, "点击了哪一个，" + mainFocus.getTitle());
            }
        });


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
