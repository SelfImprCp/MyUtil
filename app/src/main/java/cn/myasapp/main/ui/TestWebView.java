package cn.myasapp.main.ui;

import com.cp.mylibrary.custom.ProgressWebView;
import com.cp.mylibrary.custom.TitleBarView;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/7.
 *  测试 仿微信中加载网页时带线行进度条的WebView的实现
 *
 */
public class TestWebView extends BaseActivity {

    @BindView(id = R.id.webview_test_title)
    private TitleBarView webview_test_title;
    @BindView(id = R.id.webview_test_view)
    private ProgressWebView webview_test_view;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_webview);
    }

    @Override
    protected void initView() {
        super.initView();
        webview_test_title.setTitleBackFinshActivity(this);
        webview_test_title.setTitleStr("微信中加载网页时带线行进度条的WebView的实现");

        String url = "http://blog.csdn.net/finddreams/article/details/44172639/";
        webview_test_view.loadUrl(url);

    }
}
