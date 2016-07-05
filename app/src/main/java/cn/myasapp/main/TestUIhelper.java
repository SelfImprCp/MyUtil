package cn.myasapp.main;

import android.content.Context;

import com.cp.mylibrary.utils.OpenActivityUtil;

import cn.myasapp.main.ui.TestActivity;
import cn.myasapp.main.ui.TestDateTimeUtil;
import cn.myasapp.main.ui.TestFileUtil;
import cn.myasapp.main.ui.TestKeyBoardUtils;
import cn.myasapp.main.ui.TestObjectUtils;
import cn.myasapp.main.ui.TestScreentUtils;
import cn.myasapp.main.ui.TextNetWorkUtils;
import cn.myasapp.main.ui.TextRandomUtils;

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

    public static void showtestKeyBoardUitls(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestKeyBoardUtils.class);
    }


    public static void showTestNetWorkUitls(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TextNetWorkUtils.class);
    }



    public static void showTesObjectsUitls(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestObjectUtils.class);
    }




    public static void showTestRandomUtils(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TextRandomUtils.class);
    }

    public static void showTestScreenUtils(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestScreentUtils.class);
    }


}
