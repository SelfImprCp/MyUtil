package cn.myasapp.main.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cp.mylibrary.banner.ADInfo;
import com.cp.mylibrary.banner.CycleViewPager;
import com.cp.mylibrary.banner.ViewFactory;
import com.cp.mylibrary.utils.ShowToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.myasapp.R;

/**
 * Created by Jerry on 2016/7/7.
 */
public class TestBanner  extends FragmentActivity{


    private TitleBarView banner_test_title;


    private FrameLayout home_fragment_top_banner_rel;


    // 广告条
    private CycleViewPager cycleViewPager;





    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState   );
        setContentView(R.layout.activity_banner);
        initView();

    }

    protected void initView() {

        banner_test_title = (TitleBarView) findViewById(R.id.banner_test_title);

        home_fragment_top_banner_rel = (FrameLayout) findViewById(R.id.home_fragment_top_banner_rel);

        banner_test_title.setTitleBackFinshActivity(this);
        banner_test_title.setTitleStr("测试 Banner");


        initViewPger();

    }



     private void initViewPger()
     {

         cycleViewPager = (CycleViewPager)   getSupportFragmentManager()
                 .findFragmentById(R.id.fragment_home_viewpager_content);

         List<ImageView> views = new ArrayList<ImageView>();


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




         // 将最后一个ImageView添加进来
         views.add(ViewFactory.getImageView(this,
                 infos.get(infos.size() - 1).getUrl(), R.layout.view_banner, 0));
         for (int i = 0; i < infos.size(); i++) {
             views.add(ViewFactory.getImageView(this, infos.get(i)
                     .getUrl(), R.layout.view_banner, 0));
         }

         // cycleViewPager.
         // 将第一个ImageView添加进来
         views.add(ViewFactory.getImageView(this,
                 infos.get(0).getUrl(), R.layout.view_banner, 0));

         // 设置循环，在调用setData方法前调用
         cycleViewPager.setCycle(true);

         // 在加载数据前设置是否循环
         cycleViewPager.setData(views, infos, mAdCycleViewListener);
         // 设置轮播
         cycleViewPager.setWheel(true);

         // 设置轮播时间，默认5000ms
         cycleViewPager.setTime(2000);
         // 设置圆点指示图标组居中显示，默认靠右
         cycleViewPager.setIndicatorCenter();



     }


    /**
     * banner 的监听 事件
     */
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {


            ShowToastUtil.showToast(TestBanner.this,"点击了：" + info.getId());

        }

    };



}
