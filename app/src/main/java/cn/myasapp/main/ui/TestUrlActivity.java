package cn.myasapp.main.ui;

import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.SpannableStringUitl;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/12.
 */
public class TestUrlActivity extends BaseActivity {



    @BindView(id = R.id.url_test_title)
    private TitleBarView url_test_title;





    @BindView(id = R.id.url_test_textview)
    private TextView url_test_textview;


    @BindView(id = R.id.url_test_backcolor)
    private TextView url_test_backcolor;






    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_url_test);



    }

    @Override
    protected void initView() {
        super.initView();


        url_test_title.setTitleBackFinshActivity(this);
        url_test_title.setTitleStr("测试 url ");



        SpannableStringUitl.handleText(url_test_textview,"这是一个网页： http://blog.csdn.net/knighttools/article/details/41310733")
        ;


        url_test_backcolor.setText("给文本设置一个背景色");
 SpannableStringUitl.addUnderLineSpan(url_test_backcolor);







    }
}
