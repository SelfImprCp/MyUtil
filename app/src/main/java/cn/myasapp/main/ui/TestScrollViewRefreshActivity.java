package cn.myasapp.main.ui;

import com.cp.mylibrary.base.XRefreshScrollViewActivity;
import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.LogCp;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 *  scrollView Refresh
 */
public class TestScrollViewRefreshActivity extends XRefreshScrollViewActivity {


     @BindView( id = R.id.scrollview_refresh_test_title)
      private TitleBarView scrollview_refresh_test_title;



    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_scrollview_refresh);

    }


    @Override
    protected void initView() {
        super.initView();
        scrollview_refresh_test_title.setTitleBackFinshActivity(this);
        scrollview_refresh_test_title.setTitleStr("测试ScrollView 刷新");

    }

    @Override
    protected void onViewRefresh() {
        super.onViewRefresh();
        LogCp.i(LogCp.CP,TestScrollViewRefreshActivity.class + " 来刷新了。。");




    }
}
