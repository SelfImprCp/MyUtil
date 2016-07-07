package com.cp.mylibrary.banner;

import android.content.Context;
import android.widget.ImageView;

import com.cp.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry on 2016/7/7.
 */
public class BannerInitUtil {



    List<ImageView> views = new ArrayList<ImageView>();


    /**
     *
     * @param context
     * @param isWheel
     * @param time
     * @param infos
     * @param cycleViewPager
     * @param mAdCycleViewListener
     */
 public void initBannerViewPager(Context context,boolean isWheel,int time, List<ADInfo> infos,CycleViewPager cycleViewPager, CycleViewPager.ImageCycleViewListener mAdCycleViewListener)
 {

     // 将最后一个ImageView添加进来
     views.add(ViewFactory.getImageView(context,
             infos.get(infos.size() - 1).getUrl(), R.layout.view_banner, 0));
     for (int i = 0; i < infos.size(); i++) {
         views.add(ViewFactory.getImageView(context, infos.get(i)
                 .getUrl(), R.layout.view_banner, 0));
     }

     // cycleViewPager.
     // 将第一个ImageView添加进来
     views.add(ViewFactory.getImageView(context,
             infos.get(0).getUrl(), R.layout.view_banner, 0));

     // 设置循环，在调用setData方法前调用
     cycleViewPager.setCycle(true);

     // 在加载数据前设置是否循环
     cycleViewPager.setData(views, infos, mAdCycleViewListener);
     // 设置轮播
     cycleViewPager.setWheel(isWheel);

     // 设置轮播时间，默认5000ms
     cycleViewPager.setTime(time);
     // 设置圆点指示图标组居中显示，默认靠右
     cycleViewPager.setIndicatorCenter();

 }




}
