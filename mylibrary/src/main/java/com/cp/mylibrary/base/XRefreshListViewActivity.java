package com.cp.mylibrary.base;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.widget.SwipeRefreshLayout;

import com.cp.mylibrary.R;
import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.api.MyResponseHandler;
import com.cp.mylibrary.app.Config;
import com.cp.mylibrary.bean.MyEntity;
import com.cp.mylibrary.custom.EmptyLayout;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.ShowToastUtil;


import java.util.ArrayList;
import java.util.List;


/**
 * @param <T>
 */

public class XRefreshListViewActivity<T extends MyEntity> extends MyBaseActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {


    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public static int mState = STATE_NONE;


    //解析数据
    private ParserTask mParserTask;


    //当前页数
    protected int mCurrentPage = 0;

    //列表的甜酸器
    public ListBaseAdapter mAdapter;


    //数据源所有的数据
    public List<T> mData = new ArrayList<T>();

    //
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    public EmptyLayout mErrorLayout;

    public ListView mListView;

    protected int mStoreEmptyState = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState  );
    }

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_listview_refresh);

    }


    @Override
    public void initView() {
        super.initView();


        mListView = (ListView) findViewById(R.id.listview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        mErrorLayout = (EmptyLayout) findViewById(R.id.error_layout);


        addHeadView();
        addFooterView();


         LogCp.i(LogCp.CP,XRefreshListViewActivity.class + " 为空吗？" + mSwipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentPage = 0;
                mState = STATE_REFRESH;
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                sendRequestData(true);
            }
        });


        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);


        LogCp.i(LogCp.CP,XRefreshListViewActivity.class + " 为空吗？ mErrorLayout" + mErrorLayout);




        if (mAdapter != null) {
            mListView.setAdapter(mAdapter);
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        } else {
            mAdapter = getmAdapter();
            mListView.setAdapter(mAdapter);

            if (requestDataIfViewCreated()) {
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                mState = STATE_NONE;
                sendRequestData(false);
            } else {
                mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            }

        }
        if (mStoreEmptyState != -1) {
            mErrorLayout.setErrorType(mStoreEmptyState);
        }

    }

    /**
     *
     * @param
     */
     private  void addHeadView()
     {


          if(getHeadView()!=null)
          {
              mListView.addHeaderView(getHeadView());
          }


     }
    /**
     *
     * @param
     */
    private  void addFooterView(   )
    {
        if(getFooterView()!=null)
        {
            mListView.addFooterView(getFooterView());
        }


    }

    /**
     *
     * @return
     */
     public View getHeadView()
     {

         return  null;
     }

    /**
     *
     * @return
     */
    public View getFooterView()
    {

        return  null;
    }


    @Override
    public void onDestroy() {
        mStoreEmptyState = mErrorLayout.getErrorState();
        cancelParserTask();
        super.onDestroy();
    }

    protected ListBaseAdapter getmAdapter() {
        return null;
    }



    @Override
    public void onRefresh() {
        if (mState == STATE_REFRESH) {
            return;
        }
        // 设置顶部正在刷新
        mListView.setSelection(0);
        setSwipeRefreshLoadingState();
        mCurrentPage = 0;
        mState = STATE_REFRESH;
        sendRequestData(true);
    }



    /***
     * 获取列表数据
     *
     * @param refresh
     * @return void
     * @author 火蚁 2015-2-9 下午3:16:12
     */
    protected void sendRequestData(boolean refresh) {
//        String key = getCacheKey();
//        if (isReadCacheData(refresh)) {
//            readCacheData(key);
//        } else {
        // 取新的数据



        if (NetWorkUtil.hasInternetConnected(this))
        {
            requestData();
        }else
        {
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);

        }


        // }
    }


    protected void requestData() {
    }


    protected boolean requestDataIfViewCreated() {
        return true;
    }




    // 完成刷新
    protected void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();
        mState = STATE_NONE;
    }

    /**
     * 设置顶部正在加载的状态
     */
    protected void setSwipeRefreshLoadingState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
            // 防止多次重复刷新
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    /**
     * 设置顶部加载完毕的状态
     */
    protected void setSwipeRefreshLoadedState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.setEnabled(true);
        }
    }





    /**
     * 所有的子类的请求响应
     */

    public MyResponseHandler responseHandler = new MyResponseHandler() {

        @Override
        public void dataSuccess(String res) {

            LogCp.i(LogCp.CP, XRefreshListViewActivity.class + "请求来的数据 " + res);

            executeParserTask(res);
            // refreshLoadMoreFinish();
        }

        @Override
        public void dataFinish() {


        }

        @Override
        public void dataFailuer(int errorNo, String strMsg) {

        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }



    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }
        // 数据已经全部加载，或数据为空时，或正在加载，不处理滚动事件
        if (mState == STATE_LOADMORE || mState == STATE_REFRESH) {
            return;
        }
        // 判断是否滚动到底部
        boolean scrollEnd = false;
        try {
            if (view.getPositionForView(mAdapter.getFooterView()) == view
                    .getLastVisiblePosition())
                scrollEnd = true;
        } catch (Exception e) {
            scrollEnd = false;
        }

        if (mState == STATE_NONE && scrollEnd) {
            if (mAdapter.getState() == ListBaseAdapter.STATE_LOAD_MORE
                    || mAdapter.getState() == ListBaseAdapter.STATE_NETWORK_ERROR) {
                mCurrentPage++;
                mState = STATE_LOADMORE;
                sendRequestData(false);
                mAdapter.setFooterViewLoading();
            }
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

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
                LogCp.i(LogCp.CP, XRefreshListViewActivity.class + "解析 出来的数据 的，值 ，，"
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
                executeOnLoadFinish();

            }
        }
    }


    protected int getPageSize() {
        return Config.PAGE_SIXE;
    }

    protected List<T> parseList(String is) {
        return null;
    }

    /**
     * 解析出来的数据
     *
     * @param data
     */
    protected void executeOnLoadDataSuccess(List<T> data) {



        if (data == null) {
            data = new ArrayList<T>();
        }



        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        if (mCurrentPage == 0) {
            mAdapter.clear();
        }

//        for (int i = 0; i < data.size(); i++) {
//            if (compareTo(mAdapter.getData(), data.get(i))) {
//                data.remove(i);
//                i--;
//            }
//        }
        int adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
        if ((mAdapter.getCount() + data.size()) == 0) {
            adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
        } else if (data.size() == 0
                || (data.size() < getPageSize() && mCurrentPage == 0)) {
            adapterState = ListBaseAdapter.STATE_NO_MORE;
            mAdapter.notifyDataSetChanged();
        } else {
            adapterState = ListBaseAdapter.STATE_LOAD_MORE;
        }
        mAdapter.setState(adapterState);
        mAdapter.addData(data);
        // 判断等于是因为最后有一项是listview的状态
        if (mAdapter.getCount() == 1) {

            if (needShowEmptyNoData()) {
                mErrorLayout.setErrorType(EmptyLayout.NODATA);
            } else {
                mAdapter.setState(ListBaseAdapter.STATE_EMPTY_ITEM);
                mAdapter.notifyDataSetChanged();
            }
        }

    }


    /**
     * 是否需要隐藏listview，显示无数据状态
     *
     * @author 火蚁 2015-1-27 下午6:18:59
     */
    protected boolean needShowEmptyNoData() {
        return true;
    }

}