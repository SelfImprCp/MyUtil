package cn.myasapp.main.ui;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.utils.AppUtils;
import com.cp.mylibrary.utils.FileUtil;
import com.cp.mylibrary.utils.KeyBoardUtils;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.OpenActivityUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.R;
import cn.myasapp.main.TestUIhelper;
import cn.myasapp.main.bean.UserBean;


/**
 * Created by Jerry on 2016/6/24.
 */
public class TestActivity extends BaseActivity {


    private Dialog dialog;


    @BindView(id = R.id.test_app_utils, click = true)
    private TextView test_app_utils;


    @BindView(id = R.id.test_date_time_util, click = true)
    private TextView test_date_time_util;


    @BindView(id = R.id.file_test, click = true)
    private TextView file_test;


    @BindView(id = R.id.key_board_test, click = true)
    private TextView key_board_test;


    @BindView(id = R.id.object_utils_test, click = true)
    private TextView object_utils_test;

    @BindView(id = R.id.random_utils_test, click = true)
    private TextView random_utils_test;



    @BindView(id = R.id.screen_utils_test, click = true)
    private TextView screen_utils_test;



    @BindView(id = R.id.cache_utils_test, click = true)
    private TextView cache_utils_test;

    @BindView(id = R.id.two_codes_test, click = true)
    private TextView two_codes_test;
    @BindView(id = R.id.create_two_codes_test, click = true)
    private TextView create_two_codes_test;





    @Override
    public void setRootView() {

        setContentView(R.layout.activity_test);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            // 测试AppUtils
            case R.id.test_app_utils:

                ShowToastUtil.showToast(this, "AppName " + AppUtils.getAppName(this) + " app versionName " + AppUtils.getVersionName(this));

                break;

            //DateTimeUtil 测试
            case R.id.test_date_time_util:
                TestUIhelper.showTestDateTimeUtil(this);
                break;

            // 测试  文件
            case R.id.file_test:
                TestUIhelper.showTestFileActivity(this);
                break;


            // KeyBoardUtils 测试
            case R.id.key_board_test:

                TestUIhelper.showtestKeyBoardUitls(this);

                break;
            //NetWorkUtil 测试
            case R.id.net_work_test:
                TestUIhelper.showTestNetWorkUitls(this);
                break;

            // 测试 ObjectUtils
            case R.id.object_utils_test:
                TestUIhelper.showTesObjectsUitls(this);

                break;

             // 测试 RandomUtils
            case R.id.random_utils_test:

                 TestUIhelper.showTestRandomUtils(this);
                break;

           // 测试 screen
            case R.id.screen_utils_test:
                TestUIhelper.showTestScreenUtils(this);
                        break;


             // 测试异常保存
            case R.id.cache_utils_test:
                UserBean user = null;
                user.getName();



                break;

            case R.id.two_codes_test:

                 TestUIhelper.showTwoCode(this);
                break;



            case R.id.create_two_codes_test:

                TestUIhelper.showCreateTwoCode(this);

                break;

        }

    }
}
