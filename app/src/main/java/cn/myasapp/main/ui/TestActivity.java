package cn.myasapp.main.ui;

import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.cp.mylibrary.utils.ShowToastUtil;
import com.cp.mylibrary.utils.UpdateManagerUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;
import cn.myasapp.main.TestUIhelper;
import cn.myasapp.main.bean.UserBean;
import cn.myasapp.main.event.TestEvent;


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

    @BindView(id = R.id.image_load_test, click = true)
    private TextView image_load_test;

    @BindView(id = R.id.banner_test, click = true)
    private TextView banner_test;

    @BindView(id = R.id.webview_test, click = true)
    private TextView webview_test;

    @BindView(id = R.id.viewpage_test, click = true)
    private TextView viewpage_test;
    @BindView(id = R.id.pullto_test, click = true)
    private TextView pullto_test;



    @BindView(id = R.id.dialog_test, click = true)
    private TextView dialog_test;


    @BindView(id = R.id.url_test, click = true)
    private TextView url_test;

    @BindView(id = R.id.pickview_test, click = true)
    private TextView pickview_test;

    @BindView(id = R.id.xrefreshview_scrollview_test, click = true)
    private TextView xrefreshview_listview_test;

    @BindView(id = R.id.xrefreshview_receylie_test, click = true)
    private TextView xrefreshview_receylie_test;







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

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
//                        new UpdateManagerUtil(TestActivity.this)
//                                .checkUpdate();
                    }
                }, 2000);

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

            case R.id.image_load_test:

                TestUIhelper.showImageLoad(this);

                break;

            case R.id.banner_test:

                TestUIhelper.showBanner(this);

                break;

            case R.id.webview_test:

                TestUIhelper.showWebView(this);

                break;



            case R.id.pullto_test:

                TestUIhelper.showPullableListViewActivity(this);

                break;


            case R.id.viewpage_test:

             TestUIhelper.showTextViewPageFragment(this);

//                MainFocusListRes res = new  MainFocusListRes();
//
//                List<MainFocus> result = new ArrayList<MainFocus>();
//
//                 for (int i = 0;i<10;i++)
//
//                 {
//
//                     MainFocus  mainFocus = new MainFocus();
//                     mainFocus.setId(i);
//                     mainFocus.setShopName(i+"爱理");
//
//                     result.add(mainFocus);
//
//                 }
//                res.setResult(result);
//                res.setCode(0);
//                res.setMsg("成功");
//                String gson = GsonUtil.beanTojsonStr(res);
//
//                LogCp.i(LogCp.CP,TestActivity.class + "生成的json：" + gson);

                break;



            case R.id.dialog_test:

                TestUIhelper.   showDialogTestActivity(this);

                break;


            case R.id.url_test:

                TestUIhelper.   showUrlTestActivity(this);

                break;

            case R.id.pickview_test:

                TestUIhelper.showTestPickerView(this);
                break;



            case R.id.xrefreshview_scrollview_test:

             TestUIhelper.showTestScrollViewRefreshActivity(this);
                break;



            case R.id.xrefreshview_receylie_test:

                TestUIhelper.showXRefreshListViewActivity(this);
                break;








        }

    }





}
