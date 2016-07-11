package cn.myasapp.main.ui;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.cp.mylibrary.dialog.DialogHelper;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.R;

/**
 * Created by Jerry on 2016/6/28.
 * <p>
 * 测试对话框
 */
public class DialogTestActivity extends BaseActivity {


    private Dialog simplecDialog;


    @BindView(id = R.id.dialog_test_title)
    private TitleBarView dialog_test_title;


    @BindView(id = R.id.dialog_wait, click = true)
    private TextView dialog_wait;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_test_dialog);
    }

    @Override
    protected void initView() {
        super.initView();

        dialog_test_title.setTitleStr("测试对话框");
        dialog_test_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.dialog_wait:



              //   DialogHelper.getWaitDialog(this,"加载中").show();

                //    DialogHelper.getCancelableWaitDialog(this,"加载中").show();

                //     DialogHelper.getPinterestDialog(this);

                simplecDialog = DialogHelper.configDialog(DialogTestActivity.this,
                        "温馨提示", "确认删除", "ccc", "ccc",
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {
                                switch (arg0.getId()) {
                                    case R.id.base_config_dialog_sure_btn:

                                        simplecDialog.dismiss();

                                        break;
                                    case R.id.base_config_dialog_cannel_btn:
                                        simplecDialog.dismiss();

                                        break;
                                    default:
                                        break;
                                }

                            }
                        }).getConfigDialog();
                simplecDialog.show();


//                simplecDialog = DialogHelper.EditTextDialog(this, "delete ", " msg ,", "confim ", "ttest", new View.OnClickListener() {
//
//
//                    @Override
//                    public void onClick(View v) {
//
//                        switch (v.getId()) {
//                            case R.id.base_config_dialog_sure_btn:
//
//                                simplecDialog.dismiss();
//
//                                EditText editText = DialogHelper.getDialogEditText();
//                                String str = editText.getText().toString();
//                                ShowToastUtil.showToast(DialogTestActivity.this," ,," + str);
//
//                                break;
//                            case R.id.base_config_dialog_cannel_btn:
//                                simplecDialog.dismiss();
//
//                                break;
//                            default:
//                                break;
//                        }
//
//                    }
//                }  ).getConfigDialog();
//
//                simplecDialog.show();





                break;
        }
    }


}
