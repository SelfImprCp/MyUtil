//package com.cp.mylibrary.base;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AbsListView;
//import android.widget.ListView;
//
//import com.cp.mylibrary.R;
//import com.cp.mylibrary.adapter.ListBaseAdapter;
//import com.cp.mylibrary.api.MyResponseHandler;
//import com.cp.mylibrary.bean.MyEntity;
//import com.cp.mylibrary.custom.EmptyLayout;
//import com.cp.mylibrary.pullto.PullToRefreshLayout;
//import com.cp.mylibrary.utils.LogCp;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Jerry on 2016/7/8.
// */
//public class BaseListFragment<T extends MyEntity> extends MyBaseFragment   {
//
//
//    public static final int STATE_NONE = 0;
//    public static final int STATE_REFRESH = 1;
//    public static final int STATE_LOADMORE = 2;
//    public static final int STATE_NOMORE = 3;
//    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
//    public static int mState = STATE_NONE;
//
//
//    //解析数据
//    private ParserTask mParserTask;
//
//
//    //当前页数
//    protected int mCurrentPage = 1;
//    //由新的控件
//    public PullToRefreshLayout refresh_view;
//    //
//    public ListView mListView;
//    //列表的甜酸器
//    public ListBaseAdapter mAdapter;
//
//    //数据源
//    public List<T> mData = new ArrayList<T>();
//
//    public int PAGE_SIZE = 10;
//
//    // 没有数据 的情况
//    public EmptyLayout mErrorLayout;
//
//
//    @Override
//    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
//        return inflater.inflate(R.layout.activity_listview_pull,container,false);
//    }
//
//
//    @Override
//    public void initView(View view) {
//        super.initView(view);
//        refresh_view = (PullToRefreshLayout) view.findViewById(R.id.refresh_view_t);
//        mListView = (ListView) view.findViewById(R.id.content_view);
//
//
//
//        refresh_view.setOnRefreshListener(myPullToListner);
//        mListView.setOnScrollListener(this);
//
//
//        mErrorLayout = (EmptyLayout)view.findViewById(R.id.error_layout);
//
//
//        // 界面初始完了，加载数据
//        requestData( );
//
//
//    }
//
//
//
//
//
//
//
//
//    public PullToRefreshLayout.OnRefreshListener myPullToListner = new PullToRefreshLayout.OnRefreshListener() {
//
//
//        @Override
//        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
//            mCurrentPage = 1;
//
//            mData.clear();
//            requestData();
//
//            LogCp.i(LogCp.CP, BaseListActivity.class + " 刷新了，， " + mCurrentPage);
//
//
//        }
//
////        @Override
////        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
////
////        }
//    };
//
//    /**
//     * 子类要复写的加载数据 的方法
//     */
//    protected void requestData() {
//        // if(mData.size()==0)
//        //   mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
//
//    }
//
//
//    /**
//     *  所有的子类的请求响应
//     */
//
//    public MyResponseHandler responseHandler = new MyResponseHandler() {
//
//        @Override
//        public void dataSuccess(String res) {
//
//            LogCp.i(LogCp.CP, BaseListActivity.class + "请求来的数据 " + res);
//
//            executeParserTask(res);
//            refreshLoadMoreFinish();
//        }
//
//        @Override
//        public void dataFinish() {
//
//
//        }
//
//        @Override
//        public void dataFailuer(int errorNo, String strMsg) {
//
//        }
//    };
//
//
//    protected void refreshLoadMoreFinish() {
//        // 千万别忘了告诉控件刷新完毕了哦！
//        if (refresh_view != null) {
//            refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
//            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
//        }
//
//
//    }
//
//
//    private void executeParserTask(String data) {
//        cancelParserTask();
//        mParserTask = new ParserTask(data);
//        mParserTask.execute();
//    }
//
//    private void cancelParserTask() {
//        if (mParserTask != null) {
//            mParserTask.cancel(true);
//            mParserTask = null;
//        }
//    }
//
//    class ParserTask extends AsyncTask<Void, Void, String> {
//
//        private final String reponseData;
//        private boolean parserError;
//
//        public ParserTask(String data) {
//            this.reponseData = data;
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            try {
//                mData = parseList(reponseData);
//                LogCp.i(LogCp.CP, BaseListActivity.class + "解析 出来的数据 的，值 ，，"
//                        + mData);
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//                parserError = true;
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (parserError) {
//
//                //解析出错了
//                //  readCacheData(getCacheKey());
//            } else {
//
//                executeOnLoadDataSuccess(mData);
//
//            }
//        }
//    }
//
//    protected void executeOnLoadDataSuccess(List<T> data) {
//
//
//        //加载完成，设置状态
//        mState = STATE_NONE;
//
//        //这里要判断 还有没有更多数据
//
//
//        if (data == null) {
//            data = new ArrayList<T>();
//        }
//
//
//        //   mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
//        if (mState == STATE_REFRESH)
//            mAdapter.clear();
//
//
//        int adapterState = ListBaseAdapter.STATE_NO_DATA;
//        if ((mAdapter.getCount() + data.size()) == 0) {
//            adapterState = ListBaseAdapter.STATE_NO_DATA;
//        } else if (data.size() == 0
//                || (data.size() < getPageSize() && mCurrentPage == 1)) {
//            adapterState = ListBaseAdapter.STATE_NO_MORE;
//            mAdapter.notifyDataSetChanged();
//        } else {
//            adapterState = ListBaseAdapter.STATE_LOAD_MORE;
//        }
//        mAdapter.setState(adapterState);
//        mAdapter.addData(data);
//        // 判断等于是因为最后有一项是listview的状态
//        if (mAdapter.getCount() == 1) {
//
//            if (needShowEmptyNoData()) {
//                mErrorLayout.setErrorType(EmptyLayout.NODATA);
//            } else {
//                mAdapter.setState(ListBaseAdapter.STATE_NO_DATA);
//                mAdapter.notifyDataSetChanged();
//            }
//        }
//
//
//    }
//
//    /**
//     * 是否需要隐藏listview，显示无数据状态
//     *
//     * @author 火蚁 2015-1-27 下午6:18:59
//     *
//     */
//    protected boolean needShowEmptyNoData() {
//        return true;
//    }
//
//    protected int getPageSize() {
//        return PAGE_SIZE;
//    }
//
//    protected List<T> parseList(String is) {
//        return null;
//    }
//
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        if (mAdapter == null || mAdapter.getCount() == 0) {
//            return;
//        }
//
//        // 数据已经全部加载，或数据为空时，或正在加载，不处理滚动事件
//        if (mState == STATE_LOADMORE || mState == STATE_REFRESH) {
//            return;
//        }
//
//        // 判断是否滚动到底部
//        boolean scrollEnd = false;
//        try {
//            if (view.getPositionForView(mAdapter.getFooterView()) == view
//                    .getLastVisiblePosition())
//                scrollEnd = true;
//        } catch (Exception e) {
//            scrollEnd = false;
//        }
//
//        LogCp.i(LogCp.CP, BaseListActivity.class + "   到底了，， " + scrollEnd);
//
//
//        if (mState == STATE_NONE && scrollEnd) {
//            if (mAdapter.getState() == ListBaseAdapter.STATE_LOAD_MORE
//                    || mAdapter.getState() == ListBaseAdapter.STATE_NETWORK_ERROR) {
//                mCurrentPage++;
//                LogCp.i(LogCp.CP, BaseListActivity.class + "   到 加载数据 了了，， " + mCurrentPage);
//
//                mState = STATE_LOADMORE;
//                requestData();
//                mAdapter.setFooterViewLoading("");
//            }
//        }
//
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//    }
//
//
//}
