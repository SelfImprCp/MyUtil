package com.cp.mylibrary.base;

import android.os.AsyncTask;
import android.widget.AbsListView;
import android.widget.ListView;

import com.cp.mylibrary.R;
import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.api.MyResponseHandler;
import com.cp.mylibrary.bean.MyEntity;
import com.cp.mylibrary.custom.EmptyLayout;
import com.cp.mylibrary.pullto.PullToRefreshLayout;
import com.cp.mylibrary.utils.GsonUtil;
import com.cp.mylibrary.utils.LogCp;

import org.kymjs.kjframe.ui.BindView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry on 2016/7/8.
 */
public class BaseListActivity<T extends MyEntity> extends MyBaseActivity implements AbsListView.OnScrollListener {


    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public static int mState = STATE_NONE;







    private ParserTask mParserTask;


    protected int mCurrentPage = 1;

      public PullToRefreshLayout refresh_view;

     public ListView content_view;

    public ListBaseAdapter mAdapter;


    public List<T> mData;

    public int PAGE_SIZE = 10;



    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_listview);

    }

    @Override
    protected void initView() {
        super.initView();

        refresh_view = (PullToRefreshLayout)findViewById(R.id.refresh_view);
        content_view = (ListView)findViewById(R.id.content_view);

        refresh_view.setOnRefreshListener(myPullToListner);
        content_view.setOnScrollListener(this);
    }

    public PullToRefreshLayout.OnRefreshListener myPullToListner = new PullToRefreshLayout.OnRefreshListener() {


        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            mCurrentPage = 1;

            mData.clear();
            requestData( );

            LogCp.i(LogCp.CP, BaseListActivity.class + " 刷新了，， " + mCurrentPage);


        }

//        @Override
//        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
//
//        }
    };

    protected void requestData(   ) {


    }


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
        if(refresh_view!=null)
        {
            refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        }


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

        public ParserTask(String data) {
            this.reponseData = data;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                mData = parseList(reponseData);
                LogCp.i(LogCp.CP, BaseListFragment.class + "解析 出来的数据 的，值 ，，"
                        + mData);


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

                executeOnLoadDataSuccess(mData);

            }
        }
    }

    protected void executeOnLoadDataSuccess(List<T> data) {


         //加载完成，设置状态
        mState = STATE_NONE;

        //这里要判断 还有没有更多数据


        if (data == null) {
            data = new ArrayList<T>();
        }


     //   mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        if (mCurrentPage == 1) {
            mAdapter.clear();
        }

        LogCp.i(LogCp.CP,
                BaseListActivity.class + "比较前有多少数据 ，， ，，" + data.size());

        for (int i = 0; i < data.size(); i++) {
//            if (compareTo(mAdapter.getData(), data.get(i))) {
//
//
//                // data.remove(i);
//
//                i--;
//            }
        }

        LogCp.i(LogCp.CP, BaseListActivity.class + "比较后有多少数据， ，，" + data.size());



        int adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
        if ((mAdapter.getCount() + data.size()) == 0) {
            adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
        } else if (data.size() == 0
                || (data.size() < getPageSize() && mCurrentPage == 1)) {
            adapterState = ListBaseAdapter.STATE_NO_MORE;
            mAdapter.notifyDataSetChanged();
        } else {
            adapterState = ListBaseAdapter.STATE_LOAD_MORE;
        }
        mAdapter.setState(adapterState);
        mAdapter.addData(data);
        // 判断等于是因为最后有一项是listview的状态
        if (mAdapter.getCount() == 1) {

//            if (needShowEmptyNoData()) {
//                mErrorLayout.setErrorType(EmptyLayout.NODATA);
//            } else {
                mAdapter.setState(ListBaseAdapter.STATE_EMPTY_ITEM);
                mAdapter.notifyDataSetChanged();
          //  }
        }




    }


    protected int getPageSize() {
        return PAGE_SIZE;
    }

    protected List<T> parseList(String is) {
        return null;
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

        LogCp.i(LogCp.CP, BaseListActivity.class + "   到底了，， " + scrollEnd);



        if(scrollEnd)
        {
            mCurrentPage++;
            //设置状态正在加载更多，避免多次加栽
            mState = STATE_LOADMORE;
            requestData( );
            LogCp.i(LogCp.CP, BaseListActivity.class + "  加载更多了，， " + mCurrentPage);

        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


}
