package com.cp.mylibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.cp.mylibrary.app.MyBaseApp;

/**
 * Created by Jerry on 2016/7/5.
 * 常用单位转换的辅助类
 */
public class DensityUtils {

    private static Boolean _isTablet = null;

    private DensityUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     *
     * @param context
     * @param
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }



    public static boolean isTablet() {
        if (_isTablet == null) {
            boolean flag;
            if ((0xf & MyBaseApp.getInstance().getResources()
                    .getConfiguration().screenLayout) >= 3)
                flag = true;
            else
                flag = false;
            _isTablet = Boolean.valueOf(flag);
        }
        return _isTablet.booleanValue();
    }

    public static float getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public static float getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }
    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) MyBaseApp.getInstance().getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }



    /**
     * 通过手机分辨率来算出fragment的高度
     * @param height
     * @param width
     * @return
     */
    public static int getFragmentHeight(float height, float width) {
        int ttHeight = 800;

        if (width <= 720 && height <= 1280) {
            ttHeight = 800;
        } else if (width <= 800 && height <= 1280) {
            ttHeight = 900;

        } else if (width <= 1080 && height <= 1920) {
            ttHeight = 1100;
        }
        return ttHeight;


    }



    /**
     * sp转px
     *
     * @param context
     * @param
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

}
