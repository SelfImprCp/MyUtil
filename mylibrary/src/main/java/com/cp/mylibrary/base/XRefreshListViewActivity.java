package com.cp.mylibrary.base;

import android.os.AsyncTask;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.cp.mylibrary.R;
import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.api.MyResponseHandler;
import com.cp.mylibrary.bean.MyEntity;
import com.cp.mylibrary.pullto.XRefreshView;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @param <T>
 */

public class XRefreshListViewActivity<T extends MyEntity> extends MyBaseActivity {


	public static final int STATE_NONE = 0;
	public static final int STATE_REFRESH = 1;
	public static final int STATE_LOADMORE = 2;
	public static final int STATE_NOMORE = 3;
	public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
	public static int mState = STATE_NONE;



	public ListView mListView;

	//解析数据
	private ParserTask mParserTask;


	//当前页数
	protected int mCurrentPage = 1;

	//列表的甜酸器
	private ListBaseAdapter mAdapter;

	public int PAGE_SIZE = 10;

	//数据源
	public List<T> mData = new ArrayList<T>();

	private XRefreshView refreshView;

	public static long lastRefreshTime;

	@Override
	public void setRootView() {

		setContentView(R.layout.activity_listview_refresh);

	}


	@Override
	public void initView() {
		super.initView();


		mListView = (ListView) findViewById(R.id.lv_refresh);
		refreshView = (XRefreshView) findViewById(R.id.custom_view);

		mAdapter = getmAdapter();

		LogCp.i(LogCp.CP, XRefreshListViewActivity.class + " 哪个为空了，mAdapter， " + mAdapter + " ,,mListView  " + mListView + " refreshView " + refreshView);

		mListView.setAdapter(mAdapter);

		// 设置是否可以下拉刷新
		refreshView.setPullRefreshEnable(true);
		// 设置是否可以上拉加载
		refreshView.setPullLoadEnable(true);
		// 设置上次刷新的时间
		refreshView.restoreLastRefreshTime(lastRefreshTime);
		// 设置时候可以自动刷新
		refreshView.setAutoRefresh(false);
//		refreshView.setOnBottomLoadMoreTime(new OnBottomLoadMoreTime() {
//			@Override
//			public boolean isBottom() {
//				return false;
//			}
//		});

		requestData();


		refreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

			@Override
			public void onRefresh() {


				mCurrentPage = 1;

				mData.clear();
				requestData();

				LogCp.i(LogCp.CP, XRefreshListViewActivity.class + " 刷新了，， " + mCurrentPage);

						lastRefreshTime = refreshView.getLastRefreshTime();

			}

			@Override
			public void onLoadMore(boolean isSlience) {


					LogCp.i(LogCp.CP , XRefreshListViewActivity.class + "执行到加载更多");



					mCurrentPage++;
					LogCp.i(LogCp.CP, XRefreshListViewActivity.class + "   到 加载数据 了了，， " + mCurrentPage);

					mState = STATE_LOADMORE;
					requestData();



			}

			@Override
			public void onRelease(float direction) {
				super.onRelease(direction);
				if (direction > 0) {
					//	toast("下拉");
				} else {
					//	toast("上拉");
				}
			}
		});
		refreshView.setOnAbsListViewScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				LogCp.i(LogCp.CP , XRefreshListViewActivity.class + " onScrollStateChanged");


			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
								 int visibleItemCount, int totalItemCount) {

				LogCp.i(LogCp.CP , XRefreshListViewActivity.class + " onScroll");


			}
		});


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
		if (mCurrentPage != 1 && !NetWorkUtil.hasInternetConnected(this)) {

			mAdapter.notifyDataSetChanged();
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
		if (refreshView != null) {
			refreshView.stopRefresh();
			refreshView.stopLoadMore();
		}


	}
	protected ListBaseAdapter getmAdapter ()
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



		//加载完成，设置状态
		mState = STATE_NONE;

		if (mState == STATE_REFRESH)
			mAdapter.clear();


		mAdapter.addData(data);



	}


}