package cn.myasapp.main.ui;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.DensityUtils;
import com.cp.mylibrary.utils.ScreenUtils;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/5.
 * test Screen
 */
public class TestScreentUtils extends BaseActivity {


    @BindView(id = R.id.screen_test_title)
    private TitleBarView screen_test_title;

    @BindView(id = R.id.screen_test_getScreenWidth, click = true)
    private TextView screen_test_getScreenWidth;

    @BindView(id = R.id.screen_test_getScreenHeight, click = true)
    private TextView screen_test_getScreenHeight;

    @BindView(id = R.id.screen_test_getStatusHeight, click = true)
    private TextView screen_test_getStatusHeight;

    @BindView(id = R.id.screen_test_snapShotWithStatusBar, click = true)
    private TextView screen_test_snapShotWithStatusBar;

    @BindView(id = R.id.screen_test_snapShotWithoutStatusBar, click = true)
    private TextView screen_test_snapShotWithoutStatusBar;


    @BindView(id = R.id.screen_test_setFullScreen, click = true)
    private TextView screen_test_setFullScreen;




    @BindView(id = R.id.screen_test_cancelFullScreen, click = true)
    private TextView screen_test_cancelFullScreen;






    @BindView(id = R.id.screen_test_imageview)
    private ImageView screen_test_imageview;

    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_test_screent);
    }


    @Override
    protected void initView() {
        super.initView();

        screen_test_title.setTitleBackFinshActivity(this);
        screen_test_title.setTitleStr(" screen 测试");


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.screen_test_getScreenWidth:

                ShowToastUtil.showToast(this, " 屏幕宽度 ：" + ScreenUtils.getScreenWidth(this)  +
                        " 转换下单位 ：" + DensityUtils.px2dp(this,26));

                break;

            case R.id.screen_test_getScreenHeight:
                ShowToastUtil.showToast(this, " 屏幕高度 ：" + ScreenUtils.getScreenHeight(this));

                break;
            case R.id.screen_test_getStatusHeight:
                ShowToastUtil.showToast(this, " 状态栏高度 ：" + ScreenUtils.getStatusHeight(this));

                break;
            case R.id.screen_test_snapShotWithStatusBar:
                Bitmap bitmap = ScreenUtils.snapShotWithStatusBar(this);
                screen_test_imageview.setImageBitmap(bitmap);

                break;
            case R.id.screen_test_snapShotWithoutStatusBar:

                Bitmap bitmap2 = ScreenUtils.snapShotWithoutStatusBar(this);
                screen_test_imageview.setImageBitmap(bitmap2);

                break;

            case R.id.screen_test_setFullScreen:
                 ScreenUtils.setFullScreen(this);

                break;

            case R.id.screen_test_cancelFullScreen:
                ScreenUtils.cancelFullScreen(this);

                break;

        }
    }
}
