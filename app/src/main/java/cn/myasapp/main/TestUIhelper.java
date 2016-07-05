package cn.myasapp.main;

import android.content.Context;

import com.cp.mylibrary.utils.OpenActivityUtil;

import cn.myasapp.main.ui.TestActivity;
import cn.myasapp.main.ui.TestDateTimeUtil;
import cn.myasapp.main.ui.TestFileUtil;

/**
 * Created by Jerry on 2016/7/4.
 */
public class TestUIhelper {

     public   static  void showTestActivity(Context context){
         OpenActivityUtil.getInstance().openActivity(context,TestActivity.class);

     }

    public   static  void showTestFileActivity(Context context){
        OpenActivityUtil.getInstance().openActivity(context,TestFileUtil.class);

    }


    public static void showTestDateTimeUtil(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestDateTimeUtil.class);

    }
}
