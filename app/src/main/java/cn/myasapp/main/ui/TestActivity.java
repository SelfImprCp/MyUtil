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


        }

    }
}
