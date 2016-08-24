package cn.myasapp.main.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.cp.mylibrary.banner.ADInfo;
import com.cp.mylibrary.banner.BannerInitUtil;
import com.cp.mylibrary.banner.CycleViewPager;
import com.cp.mylibrary.base.MyBaseFragmentActivity;
import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.utils.ShowToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/7/7.
 * <p>
 * Banner 的用法，
 */
public class TestBanner extends MyBaseFragmentActivity {


    private TitleBarView banner_test_title;


    // 广告条
    private CycleViewPager cycleViewPager;


    @Override
    protected int setRootView() {
        return  R.layout.activity_banner;

    }

    protected void initView() {

        banner_test_title = (TitleBarView) findViewById(R.id.banner_test_title);

        banner_test_title.setTitleBackFinshActivity(this);
        banner_test_title.setTitleStr("测试 Banner");


        initViewPger();

    }


    private void initViewPger() {

        //初始化控件
        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_home_viewpager_content);



        //数据源
        List<ADInfo> infos = new ArrayList<ADInfo>();

        ADInfo adInfo = new ADInfo();
        adInfo.setId("1");
        adInfo.setUrl("http://img1.3lian.com/img013/v4/81/d/70.jpg");

        ADInfo adInfo2 = new ADInfo();
        adInfo2.setId("2");
        adInfo2.setUrl("http://img1.3lian.com/img013/v4/81/d/66.jpg");

        ADInfo adInfo3 = new ADInfo();
        adInfo3.setId("3");
        adInfo3.setUrl("http://pic.35pic.com/normal/07/50/56/11136018_160251082391_2.jpg");


        infos.add(adInfo);
        infos.add(adInfo2);
        infos.add(adInfo3);






   // 填充数据

        BannerInitUtil bannerInitUtil = new BannerInitUtil();
        bannerInitUtil.initBannerViewPager(this, true, 2000, infos, cycleViewPager, mAdCycleViewListener);


    }


    /**
     * banner 的监听 事件
     */
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {


            ShowToastUtil.showToast(TestBanner.this, "点击了：" + info.getId());

        }

    };


}
