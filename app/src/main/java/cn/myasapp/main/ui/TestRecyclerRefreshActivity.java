package cn.myasapp.main.ui;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;


import com.cp.mylibrary.base.XRefreshRecyclerViewActivity;
import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.interf.OnItemClickListener;
import com.cp.mylibrary.utils.GsonUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import java.util.List;
import cn.myasapp.main.R;
import cn.myasapp.main.adapter.RefreshRecyclerAdapter;
import cn.myasapp.main.api.TestApi;
import cn.myasapp.main.bean.MainFocus;
import cn.myasapp.main.res.MainFocusListRes;

/**
 * Created by Jerry on 2016/7/13.
 */
public class TestRecyclerRefreshActivity extends XRefreshRecyclerViewActivity {


 @BindView( id = R.id.recycler_refresh_title)
  private TitleBarView recycler_refresh_title;





    @Override
    public void setRootView() {
        super.setRootView();
      setContentView(R.layout.activity_recycler_refresh_layout);

    }


    @Override
    protected void initView() {
        super.initView();



        recycler_refresh_title .setTitleBackFinshActivity(this);
        recycler_refresh_title.setTitleStr("测试recycler 的上拉加载，下拉刷新");

//        mLayoutManager = new GridLayoutManager(this,3);

        StaggeredGridLayoutManager     mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);


        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected RefreshRecyclerAdapter getmAdapter() {
        RefreshRecyclerAdapter mAdapter = new RefreshRecyclerAdapter(this);
        mAdapter.setOnItemClickListener(onItemClickListener);
        return mAdapter;
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


    /**
     *  item 的单击事件
     */
    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(ViewGroup parent, View view, Object o, int position) {

            MainFocus mainFocus = (MainFocus) o;

            ShowToastUtil .showToast(TestRecyclerRefreshActivity.this,"点击了哪一个。，" + mainFocus.getName());
        }

        @Override
        public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
            return false;
        }
    };



}
