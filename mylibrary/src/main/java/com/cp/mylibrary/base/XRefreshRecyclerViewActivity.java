package com.cp.mylibrary.base;

import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cp.mylibrary.R;
import com.cp.mylibrary.api.MyResponseHandler;
import com.cp.mylibrary.bean.MyEntity;
import com.cp.mylibrary.pullto.XRefreshView;
import com.cp.mylibrary.pullto.recyclerview.BaseRecyclerAdapter;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *  带上拉加载，下拉刷新的Recycler 继承
 *
 * Created by Jerry on 2016/7/13.
 */
public class XRefreshRecyclerViewActivity<T extends MyEntity>  extends  MyBaseActivity{



   public  RecyclerView mRecyclerView;

    public   XRefreshView xRefreshView;


    //当前页数
    protected int mCurrentPage = 0;

    public int PAGE_SIZE = 10;


     private BaseRecyclerAdapter mAdapter;

    //数据源
    public List<T> mData = new ArrayList<T>();

   // 布局管理器
   public   LinearLayoutManager mLayoutManager;

    //解析数据
    private ParserTask mParserTask;



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_recycler_refresh);


    }


    @Override
    protected void initView() {
        super.initView();


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_xrefreshview_rv);
        xRefreshView = (XRefreshView) findViewById(R.id.recycler_xrefreshview);
        xRefreshView.setPullLoadEnable(true);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // 设置静默加载模式
        xRefreshView.setSlienceLoadMore();

        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
//        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
//		xRefreshView.setPullLoadEnable(false);
        //设置静默加载时提前加载的item个数
        xRefreshView.setPreLoadCount(4);


        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                onListViewRefresh();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);

                LogCp.i(LogCp.CP, XRefreshListViewActivity.class + "   到 加载数据 了了，， " + mCurrentPage);

                mCurrentPage++;

                requestData();

            }
        });
        mAdapter = getmAdapter();
        mRecyclerView.setAdapter(mAdapter);



        requestData();


    }


    /**
     *  执行刷新
     */
    public void onListViewRefresh()
    {
        mCurrentPage = 0;

        mAdapter.getData().clear();
        requestData();

        LogCp.i(LogCp.CP, XRefreshListViewActivity.class + " 刷新了，， " + mCurrentPage);



    }





        /**
         * 子类要复写的加载数据 的方法
         */
    protected void requestData() {
        // 第一次加载的时候 ，转圈圈
        if (mCurrentPage == 0)
            //	mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);

            //第一次加载，并且没有网络

            if (mCurrentPage == 0 && !NetWorkUtil.hasInternetConnected(this)&&mData.size()==0) {
                //设置整个界面
                //	mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);

            }
        // 不是第一次加载，并且底下有部分数据了，要把欺adapter的状态设置为网络错误
        if (mCurrentPage != 0 && !NetWorkUtil.hasInternetConnected(this)) {
           // mAdapter.setState(ListBaseAdapter.STATE_NETWORK_ERROR);
            mAdapter.notifyDataSetChanged();
        }


    }

    /**
     * 所有的子类的请求响应
     */

    public MyResponseHandler responseHandler = new MyResponseHandler() {

        @Override
        public void dataSuccess(String res) {

            LogCp.i(LogCp.CP, XRefreshRecyclerViewActivity.class + "请求来的数据 " + res);

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
    protected BaseRecyclerAdapter getmAdapter ()
    {
        return  null;
    };

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
                LogCp.i(LogCp.CP, XRefreshRecyclerViewActivity.class + "解析 出来的数据 的，值 ，，"
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




        mAdapter.addData(data);


    }







}
