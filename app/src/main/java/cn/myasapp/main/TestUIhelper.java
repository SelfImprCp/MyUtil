package cn.myasapp.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cp.mylibrary.utils.OpenActivityUtil;

import cn.myasapp.main.domian.SimpleBackPage;

import cn.myasapp.main.ui.DialogTestActivity;
import cn.myasapp.main.ui.TestListViewRefreshActivity;
import cn.myasapp.main.ui.SimpleBackActivity;
import cn.myasapp.main.ui.TestActivity;
import cn.myasapp.main.ui.TestBanner;
import cn.myasapp.main.ui.TestCreateTwoCode;
import cn.myasapp.main.ui.TestDateTimeUtil;
import cn.myasapp.main.ui.TestFileUtil;
import cn.myasapp.main.ui.TestImageLoad;
import cn.myasapp.main.ui.TestKeyBoardUtils;
import cn.myasapp.main.ui.TestObjectUtils;
import cn.myasapp.main.ui.TestPickerView;
import cn.myasapp.main.ui.TestRecyclerRefreshActivity;
import cn.myasapp.main.ui.TestScreentUtils;
import cn.myasapp.main.ui.TestScrollViewRefreshActivity;
import cn.myasapp.main.ui.TestTwoCode;
import cn.myasapp.main.ui.TestWebView;
import cn.myasapp.main.ui.TextNetWorkUtils;
import cn.myasapp.main.ui.TextRandomUtils;
import cn.myasapp.main.ui.TestUrlActivity;


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
    public static void showTwoCode(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestTwoCode.class);
    }

    public static void showCreateTwoCode(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestCreateTwoCode.class);
    }
    public static void showImageLoad(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestImageLoad.class);
    }

    public static void showBanner(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestBanner.class);
    }
    public static void showWebView(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestWebView.class);
    }


    public static void showPullableListViewActivity(Context context) {
       OpenActivityUtil.getInstance().openActivity(context,TestListViewRefreshActivity.class);
    }


    public static void showDialogTestActivity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,DialogTestActivity.class);
    }

    public static void showUrlTestActivity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestUrlActivity.class);
    }


    public static void showTestPickerView(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestPickerView.class);
    }

    public static void showTestScrollViewRefreshActivity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,TestScrollViewRefreshActivity.class);
    }


    public static void showXRefreshListViewActivity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context,    TestRecyclerRefreshActivity
                .class);
    }




    /**
     *  test view
     * @param context
     */
    public static void showTextViewPageFragment(Context context   ) {
        showSimpleBack(context, SimpleBackPage.VIEW_PAGE);


    }



    // ===============================以下代码勿改动======================================//



    /**
     *
     * @param context
     * @param page
     */
    public static void showSimpleBack(Context context, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    /**
     *
     * @param context
     * @param page
     * @param args
     */

    public static void showSimpleBack(Context context, SimpleBackPage page,
                                      Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }



}
