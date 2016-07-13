package com.cp.mylibrary.base;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cp.mylibrary.R;
import com.cp.mylibrary.adapter.CommonAdapter;
import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.api.MyResponseHandler;
import com.cp.mylibrary.bean.MyEntity;
import com.cp.mylibrary.pullto.XRefreshView;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry on 2016/7/13.
 */
public class XRefreshRecyclerViewActivity<T extends MyEntity>  extends  MyBaseActivity{
   private   RecyclerView recyclerView;
    private CommonAdapter commonAdapter;
    private XRefreshView xRefreshView;
    private StaggeredGridLayoutManager layoutManager;

    //数据源
    public List<T> mData = new ArrayList<T>();

    //解析数据
    private ParserTask mParserTask;


    //当前页数
    protected int mCurrentPage = 1;
    public int PAGE_SIZE = 10;
    public static long lastRefreshTime;


    @Override
    public void setRootView() {
        super.setRootView();

     setContentView(R.layout.activity_recycler_refresh);
    }


    @Override
    protected void initView() {
        super.initView();

        xRefreshView = (XRefreshView) findViewById(R.id.recycler_xrefreshview);
        xRefreshView.setPullLoadEnable(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_xrefreshview_rv);
        recyclerView.setHasFixedSize(true);
        commonAdapter = getAdapter();

        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(commonAdapter);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh() {
                mCurrentPage = 1;

                mData.clear();
                requestData();

                LogCp.i(LogCp.CP, BaseListActivity.class + " 刷新了，， " + mCurrentPage);

                lastRefreshTime = xRefreshView.getLastRefreshTime();
            }

            @Override
            public void onLoadMore(boolean isSlience) {


                mCurrentPage++;
                LogCp.i(LogCp.CP, BaseListActivity.class + "   到 加载数据 了了，， " + mCurrentPage);


                requestData();


            }
        });

    }

     protected  CommonAdapter getAdapter()
     {
          return  null;
     }

    /**
     * 子类要复写的加载数据 的方法
     */
    protected void requestData() {
        // 第一次加载的时候 ，转圈圈
        if (mCurrentPage == 1)
            //	mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);

            //第一次加载，并且没有网络

            if (mCurrentPage == 1 && !NetWorkUtil.hasInternetConnected(this)&&mData.size()==0) {
                //设置整个界面
                //	mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);

            }
        // 不是第一次加载，并且底下有部分数据了，要把欺adapter的状态设置为网络错误
//        if (mCurrentPage != 1 && !NetWorkUtil.hasInternetConnected(this)) {
//            mAdapter.setState(ListBaseAdapter.STATE_NETWORK_ERROR);
//            mAdapter.notifyDataSetChanged();
//        }


    }
    private void executeParserTask(String data) {
        cancelParserTask();
        mParserTask = new ParserTask(data);
        mParserTask.execute();
    }

    private void cancelParserTask() {
        if (mParserTask != null) {
            mParserTask.cancel(true);
            mParserTask = null;
        }
    }
    class ParserTask extends AsyncTask<Void, Void, String> {

        private final String reponseData;
        private boolean parserError;

        private List<T>
                currentList = new ArrayList<T>();

        public ParserTask(String data) {
            this.reponseData = data;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                currentList = parseList(reponseData);
                LogCp.i(LogCp.CP, BaseListFragment.class + "解析 出来的数据 的，值 ，，"
                        + currentList);


            } catch (Exception e) {
                e.printStackTrace();

                parserError = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (parserError) {

                //解析出错了
                //  readCacheData(getCacheKey());


            } else {

                executeOnLoadDataSuccess(currentList);

            }
        }
    }


    protected int getPageSize() {
        return PAGE_SIZE;
    }

    protected List<T> parseList(String is) {
        return null;
    }

    /**
     * 解析出来的数据
     * @param data
     */
    protected void executeOnLoadDataSuccess(List<T> data) {





        commonAdapter.addData(data);



    }



    /**
     * 所有的子类的请求响应
     */

    public MyResponseHandler responseHandler = new MyResponseHandler() {

        @Override
        public void dataSuccess(String res) {

            LogCp.i(LogCp.CP, BaseListActivity.class + "请求来的数据 " + res);

            executeParserTask(res);
            refreshLoadMoreFinish();
        }

        @Override
        public void dataFinish() {


        }

        @Override
        public void dataFailuer(int errorNo, String strMsg) {

        }
    };



    protected void refreshLoadMoreFinish() {
        // 千万别忘了告诉控件刷新完毕了哦！
        if (xRefreshView != null) {
            xRefreshView.stopRefresh();
            xRefreshView.stopLoadMore();
        }


    }

}
