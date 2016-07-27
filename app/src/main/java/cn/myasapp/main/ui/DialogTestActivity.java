package cn.myasapp.main.ui;

import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.dialog.DialogHelper;

import com.cp.mylibrary.pullto.XRefreshView;
import com.cp.mylibrary.utils.LogCp;
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

    @BindView(id = R.id.refresh_view_dialog)
    private XRefreshView refresh_view_dialog;





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


        refresh_view_dialog.setXRefreshViewListener(myPullToListner);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.dialog_wait:



 // DialogHelper.getWaitDialog(this,"加载中").show();

                //    DialogHelper.getCancelableWaitDialog(this,"加载中").show();

                //     DialogHelper.getPinterestDialog(this);
//
//                simplecDialog = DialogHelper.configDialog(DialogTestActivity.this,
//                        "温馨提示", "确认删除", "删除", "消",
//                        new View.OnClickListener() {
//
//                            @Override
//                            public void onClick(View arg0) {
//                                switch (arg0.getId()) {
//                                    case R.id.base_config_dialog_sure_btn_b:
//
//                                        simplecDialog.dismiss();
//
//                                        break;
//                                    case R.id.base_config_dialog_cannel_btn_b:
//                                        simplecDialog.dismiss();
//
//                                        break;
//                                    default:
//                                        break;
//                                }
//
//                            }
//                        }).getConfigDialog();
//                simplecDialog.show();


//                simplecDialog = DialogHelper.EditTextDialog(this, "delete ", null, " 不超过", "confim ", "ttest", new View.OnClickListener() {
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


    public XRefreshView.XRefreshViewListener myPullToListner = new XRefreshView.XRefreshViewListener() {


        @Override
        public void onRefresh() {

            LogCp.i(LogCp.CP, DialogTestActivity.class + " 刷新了，， "    );


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refresh_view_dialog.stopRefresh();
                }
            }, 2000);



        }

        @Override
        public void onLoadMore(boolean isSilence) {

        }

        @Override
        public void onRelease(float direction) {

        }

        @Override
        public void onHeaderMove(double offset, int offsetY) {

        }


    };

}
