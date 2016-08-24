package cn.myasapp.main.ui;

import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.DateTimeUtil;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/5.
 */
public class TestDateTimeUtil extends BaseActivity {
    @BindView(id = R.id.date_time_test_title)
    private TitleBarView date_time_test_title;

    @BindView(id = R.id.date_time_test_gettimestamp, click = true)
    private TextView date_time_test_gettimestamp;


    @BindView(id = R.id.date_time_test_getTempFileName, click = true)
    private TextView date_time_test_getTempFileName;

    @BindView(id = R.id.date_time_test_getCurrentDataTime, click = true)
    private TextView date_time_test_getCurrentDataTime;

    @BindView(id = R.id.date_time_test_friendly_time, click = true)
    private TextView date_time_test_friendly_time;


    @BindView(id = R.id.date_time_test_friendly_time2, click = true)
    private TextView date_time_test_friendly_time2;


    // 测试用的一个时间戳
    private String testTimeTamp = "1467691123764";


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_date_time_util_test);
    }


    @Override
    protected void initView() {
        super.initView();

        date_time_test_title.setTitleStr("测试 DateTimeUtil");
        date_time_test_title.setTitleBackFinshActivity(this);
        //  date_time_test_title.setTitleBarHeight(250);

    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.date_time_test_gettimestamp:

                ShowToastUtil.showToast(this, "取得 当前时间戳" + DateTimeUtil.getTimesTamp());
                LogCp.i(LogCp.CP, TestDateTimeUtil.class + " 当前时间戳" + DateTimeUtil.getTimesTamp());

                break;

            case R.id.date_time_test_getTempFileName:

                ShowToastUtil.showToast(this, "使用当前时间戳拼接一个唯一的文件名" + DateTimeUtil.getTempFileName());
                break;

            case R.id.date_time_test_getCurrentDataTime:

                ShowToastUtil.showToast(this, "返回当前系统时间" + DateTimeUtil.getCurrentDataTime(DateTimeUtil.dateFormater3));
                break;


            case R.id.date_time_test_friendly_time:

                ShowToastUtil.showToast(this, "" + DateTimeUtil.friendly_time(testTimeTamp));
                break;


            case R.id.date_time_test_friendly_time2:

                ShowToastUtil.showToast(this, " " + DateTimeUtil.friendly_time2(testTimeTamp));
                break;


        }
    }
}
