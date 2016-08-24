package cn.myasapp.main.ui;

import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;
import cn.myasapp.main.TestUIhelper;

/**
 * Created by Jerry on 2016/7/5.
 */
public class TextNetWorkUtils extends BaseActivity {


    @BindView(id = R.id.net_work_test_title)
    private TitleBarView net_work_test_title;


    @BindView(id = R.id.net_work_test_validateInternet, click = true)

    private TextView net_work_test_validateInternet;


    @BindView(id = R.id.net_work_test_openWirelessSet, click = true)

    private TextView net_work_test_openWirelessSet;


    @BindView(id = R.id.net_work_test_getNetworkType, click = true)

    private TextView net_work_test_getNetworkType;


    @BindView(id = R.id.net_work_test_isWifiConn, click = true)

    private TextView net_work_test_isWifiConn;


    @BindView(id = R.id.net_work_test_hasInternetConnected, click = true)

    private TextView net_work_test_hasInternetConnected;


    private NetWorkUtil netWorkUtil;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_net_work_test);
    }

    @Override
    protected void initView() {
        super.initView();
        net_work_test_title.setTitleBackFinshActivity(this);
        net_work_test_title.setTitleStr(" NetWork 测试");

        netWorkUtil = new NetWorkUtil();


    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {


            case R.id.net_work_test_validateInternet:


                ShowToastUtil.showToast(this, "是否有网" + netWorkUtil.validateInternetIfNoShowDialog(this));

                break;

            case R.id.net_work_test_openWirelessSet:
                netWorkUtil.openWirelessSet(TextNetWorkUtils.this);

                break;


            case R.id.net_work_test_getNetworkType:


                ShowToastUtil.showToast(this, "网络类型" + NetWorkUtil.getNetworkType(this));


                break;

            case R.id.net_work_test_isWifiConn:
                ShowToastUtil.showToast(this, "WIFI 是否连着 " + NetWorkUtil.isWifiConn(this));


                break;


            case R.id.net_work_test_hasInternetConnected:

                ShowToastUtil.showToast(this, " 是否联网 " + NetWorkUtil.hasInternetConnected(this));

                break;


        }
    }
}
