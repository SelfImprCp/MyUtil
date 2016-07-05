package com.cp.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Jerry on 2016/7/5.
 * 软键盘相关辅助类
 */
public class KeyBoardUtils {



    /**
     * 打卡软键盘
     *
     * @param mEditText
     *            输入框
     * @param mContext
     *            上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText
     *            输入框
     * @param mContext
     *            上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
    ////////////////////////////软件盘//////////////////////////////////////
    /**
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view, Context context) {
        if (view == null)
            return;
        ((InputMethodManager)  context.getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }
    /**
     *
     * @param view
     */
    public static void showSoftKeyboard(View view,Context context) {
        ((InputMethodManager)  context.getSystemService(
                Context.INPUT_METHOD_SERVICE)).showSoftInput(view,
                InputMethodManager.SHOW_FORCED);
    }



    /**
     * 关闭键盘
     */
    public static void closeInput(Activity cotnext) {
        InputMethodManager inputMethodManager = (InputMethodManager)cotnext. getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && ( cotnext.getCurrentFocus() != null)) {
            inputMethodManager.hideSoftInputFromWindow(cotnext.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
