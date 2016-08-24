package cn.myasapp.main.ui;

import android.view.View;
import android.widget.AdapterView;

import com.cp.mylibrary.adapter.ListBaseAdapter;

 
import com.cp.mylibrary.base.XRefreshListViewActivity;
import com.cp.mylibrary.utils.GsonUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import java.util.List;

import cn.myasapp.main.R;
import cn.myasapp.main.adapter.MainFocusAdapter;
import cn.myasapp.main.api.TestApi;
import cn.myasapp.main.bean.MainFocus;
import cn.myasapp.main.res.MainFocusListRes;


/***
 *  注意布局文件中的inclode
 *
 */

public class TestListViewRefreshActivity extends XRefreshListViewActivity {


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
    public void initView() {
        super.initView();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainFocus mainFocus = (MainFocus) mAdapter.getData().get(position);
                ShowToastUtil.showToast(TestListViewRefreshActivity.this, "点击了哪一个，" + mainFocus.getName());
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
