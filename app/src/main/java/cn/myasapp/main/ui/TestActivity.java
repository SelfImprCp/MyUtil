package cn.myasapp.main.ui;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.utils.AppUtils;
import com.cp.mylibrary.utils.FileUtil;
import com.cp.mylibrary.utils.OpenActivityUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.R;
import cn.myasapp.main.TestUIhelper;


/**
 * Created by Jerry on 2016/6/24.
 *
 *
 */
public class TestActivity extends BaseActivity {


    private Dialog dialog;



    @BindView(id = R.id.test_app_utils, click = true)
    private TextView test_app_utils;


    @BindView(id = R.id.test_date_time_util, click = true)
    private TextView test_date_time_util;


    @BindView(id = R.id.https_test, click = true)
    private TextView https_test;


    @BindView(id = R.id.file_test, click = true)
    private TextView file_test;





    @BindView(id = R.id.dialog_test, click = true)
    private TextView dialog_test;


    @BindView(id = R.id.viewpage_fragment_test, click = true)
    private TextView viewpage_fragment_test;



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

                //

  ShowToastUtil.showToast(this,"AppName " + AppUtils.getAppName(this) + " app versionName " + AppUtils.getVersionName(this));

                break;



            case R.id.test_date_time_util:

                TestUIhelper.showTestDateTimeUtil(this);


                 break;

//
//            case R.id.https_test:
//
//
//                dialog = DialogHelper.loadDialog(this, "提交中...")
//                        .getDialog();
//                dialog.show();
//
//
//                MyAPI.Login("13725312913", "123456", new MyResponseHandler() {
//
//
//                    @Override
//                    public void dataSuccess(String res) {
//                        LogCp.i(LogCp.CP, TestActivity.class + "返回 的 dd 0" + res);
//
//
//                    }
//
//                    @Override
//                    public void dataFinish() {
//                        dialog.dismiss();
//
//                    }
//
//                    @Override
//                    public void dataFailuer(int errorNo, String strMsg) {
//                        LogCp.i(LogCp.CP, TestActivity.class + " 加载出错 " + errorNo + " 错了，" + strMsg);
//
//
//                    }
//
//
//                });
//
//
//                break;
//
//
            // 测试  文件
            case R.id.file_test:


                TestUIhelper.showTestFileActivity(this);



                break;




//            case R.id.dialog_test:
//
//
//                 UIHelper.showTestDialog(this);
//
//                break;
//
//
//            case R.id.viewpage_fragment_test:
//
//                 UIHelper.showTextViewPageFragment(this);
//                break;
//
//
//
    }

  }
}
