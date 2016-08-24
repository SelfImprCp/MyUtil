package cn.myasapp.main.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.KeyBoardUtils;

import org.kymjs.kjframe.ui.BindView;
import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/5.
    测试 软件 盘
 */
public class TestKeyBoardUtils extends BaseActivity{





    @BindView(id = R.id.key_borad_test_title)
    private TitleBarView key_borad_test_title;


    @BindView(id = R.id.key_board_test_edit1)
    private EditText key_board_test_edit1;


    @BindView(id = R.id.key_board_test_edit2)
    private EditText key_board_test_edit2;

    @BindView(id = R.id.key_board_test_hide ,click = true)
    private TextView key_board_test_hide;


    @BindView(id = R.id.key_board_test_show ,click = true)
    private TextView key_board_test_show;







    @Override
    public void setRootView() {
        super.setRootView();

     setContentView(R.layout.activity_keyboard_test);
    }

    @Override
    protected void initView() {
        super.initView();

        key_borad_test_title.setTitleBackFinshActivity(this);
        key_borad_test_title.setTitleStr(" 软键盘测试");







    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

     switch (v.getId())
     {
         case R.id.key_board_test_show:

             KeyBoardUtils.showSoftKeyboard(key_board_test_edit1,this);
             break;
         case R.id.key_board_test_hide:
             KeyBoardUtils.hideSoftKeyboard(key_board_test_edit1,this);

             break;

     }
    }
}
