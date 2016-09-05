package com.cp.mylibrary.base;

import android.os.Handler;

import com.cp.mylibrary.R;
import com.cp.mylibrary.pullto.XRefreshView;
import com.cp.mylibrary.utils.LogCp;

/**
 *
 *  带刷新的scrollView
 *
 *   注意子类布局
 *
 *   <com.cp.mylibrary.pullto.XRefreshView xmlns:android="http://schemas.android.com/apk/res/android"

 android:id="@+id/xrefresh_custom_view"
 android:layout_width="match_parent"
 android:layout_height="match_parent"
 android:background="#fff"
 >

 *
 * Created by Jerry on 2016/7/13.
 */
public class XRefreshScrollViewActivity extends  MyBaseActivity {
    private XRefreshView xrefresh_custom_view;

    @Override
    public void setRootView() {
        super.setRootView();

     setContentView(R.layout.activity_view_refresh);
    }






    @Override
    protected void initView() {
        super.initView();
        xrefresh_custom_view  = (XRefreshView) findViewById(R.id.xrefresh_custom_view);

        xrefresh_custom_view.setAutoRefresh(false);
        xrefresh_custom_view.setPullLoadEnable(false);
        xrefresh_custom_view.setPinnedTime(1000);
        xrefresh_custom_view.setAutoLoadMore(false);
        xrefresh_custom_view.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh() {


                LogCp.i(LogCp.CP,XRefreshScrollViewActivity.class + " 来刷新了。。");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrefresh_custom_view.stopRefresh();
                    }
                }, 2000);

                onViewRefresh();



            }


        });
    }


     protected  void onViewRefresh(){};


}
