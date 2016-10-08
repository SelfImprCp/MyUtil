package com.cp.mylibrary.dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cp.mylibrary.R;
import com.cp.mylibrary.utils.StringUtils;


public class DialogHelper {


    private static DialogHelper instantce = null;
    private Dialog configDialog;
    private Dialog dialog;

    public Dialog getConfigDialog() {
        return configDialog;
    }

    public void setConfigDialog(Dialog configDialog) {
        this.configDialog = configDialog;
    }

    private static DialogHelper getInstances() {
        if (instantce == null) {
            instantce = new DialogHelper();
        }
        return instantce;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }


    /**
     * @param context
     * @param title
     * @param msg
     * @param rightStr
     * @param leftStr
     * @param itemsOnClick
     * @return
     */

    public static EditText edit_config_dialog_txt;

    public static EditText getDialogEditText() {
        return edit_config_dialog_txt;
    }


    /**
     * 返回一个带输入框的
     *
     * @param context
     * @param title
     * @param msg
     * @param rightStr
     * @param leftStr
     * @param itemsOnClick
     * @return
     */
    public static DialogHelper EditTextDialog(Context context, String title,
                                              String msg, String nickName, String nickNameTag, String rightStr, String leftStr,
                                              View.OnClickListener itemsOnClick) {
        Dialog cd = new AlertDialog.Builder(context).create();
        // 实例化对话框布局
        View dialogLayout = LayoutInflater.from(context).inflate(
                R.layout.base_config_dialog, null);
        // 设置标题
        TextView title_tv = (TextView) dialogLayout
                .findViewById(R.id.base_config_dialog_title);

        // 设置内容
        TextView msg_tv = (TextView) dialogLayout
                .findViewById(R.id.base_config_dialog_txt);
        if (StringUtils.isEmpty(msg)) {
            msg_tv.setVisibility(View.GONE);
        } else {
            msg_tv.setVisibility(View.VISIBLE);
            msg_tv.setText(msg);
        }
        // 昵称的提示
        TextView base_config_nickname_text = (TextView) dialogLayout
                .findViewById(R.id.base_config_nickname_text);

        if (StringUtils.isEmpty(nickNameTag)) {
            base_config_nickname_text.setVisibility(View.GONE);
        } else {
            base_config_nickname_text.setVisibility(View.VISIBLE);
            base_config_nickname_text.setText(nickNameTag);
        }


        title_tv.setText(title);
        // 输入内容
        edit_config_dialog_txt = (EditText) dialogLayout
                .findViewById(R.id.edit_config_dialog_txt);


        edit_config_dialog_txt.setVisibility(View.VISIBLE);
        edit_config_dialog_txt.setText(nickName);
        TextView cancelbtn = (TextView) dialogLayout
                .findViewById(R.id.base_config_dialog_cannel_btn_b);
        if (!StringUtils.isEmpty(leftStr)) {
            cancelbtn.setText(leftStr);
        } else {
            cancelbtn.setVisibility(View.GONE);
        }
        TextView delbtn = (TextView) dialogLayout
                .findViewById(R.id.base_config_dialog_sure_btn_b);
        if (!StringUtils.isEmpty(rightStr)) {
            delbtn.setText(rightStr);
        } else {
            delbtn.setVisibility(View.VISIBLE);
        }

        delbtn.setOnClickListener(itemsOnClick);
        cancelbtn.setOnClickListener(itemsOnClick);
        //	cd.show();
        cd.setContentView(dialogLayout);

        cd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        cd.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        instantce = getInstances();
        instantce.setConfigDialog(cd);
        return instantce;
    }


    /**
     * 确认删除提示框
     */

    public static DialogHelper configDialog(Context context, String title,
                                            String msg, String rightStr, String leftStr,
                                            View.OnClickListener itemsOnClick) {
        Dialog cd = new AlertDialog.Builder(context).create();
        // 实例化对话框布局
        View dialogLayout = LayoutInflater.from(context).inflate(
                R.layout.base_config_dialog, null);
        // 设置标题
        TextView title_tv = (TextView) dialogLayout
                .findViewById(R.id.base_config_dialog_title);

        title_tv.setText(title);
        // 设置内容
        TextView msg_tv = (TextView) dialogLayout
                .findViewById(R.id.base_config_dialog_txt);
        msg_tv.setVisibility(View.VISIBLE);
        msg_tv.setText(msg);
        TextView cancelbtn = (TextView) dialogLayout
                .findViewById(R.id.base_config_dialog_cannel_btn_b);
        if (!StringUtils.isEmpty(leftStr)) {
            cancelbtn.setText(leftStr);
        } else {
            cancelbtn.setVisibility(View.GONE);
        }
        TextView delbtn = (TextView) dialogLayout
                .findViewById(R.id.base_config_dialog_sure_btn_b);
        if (!StringUtils.isEmpty(rightStr)) {
            delbtn.setText(rightStr);
        } else {
            delbtn.setVisibility(View.VISIBLE);
        }
        delbtn.setOnClickListener(itemsOnClick);
        cancelbtn.setOnClickListener(itemsOnClick);
        cd.show();

        cd.setContentView(dialogLayout);
        instantce = getInstances();
        instantce.setConfigDialog(cd);
        return instantce;
    }

    /**
     * 加载框
     */
    public static DialogHelper loadDialog(Context context, String msg) {
        Dialog d = new AlertDialog.Builder(context).create();
        // 实例化对话框布局
        View dialogLayout = LayoutInflater.from(context).inflate(
                R.layout.base_load_dialog, null);
        // 设置标题的内容
        TextView title = (TextView) dialogLayout
                .findViewById(R.id.base_load_txt);
        title.setText(msg);
        // requestFeature() show之后才有window
        d.setCanceledOnTouchOutside(false); // 触摸外部不会取消
        // d.setCancelable(false);//返回键不会取消
        d.show();
        d.setContentView(dialogLayout);
        instantce = getInstances();
        instantce.setDialog(d);
        return instantce;
    }


    public static Dialog getEditTextDialogs(Context mContext, String title, String msg) {
        EditTextDialog dialog = new EditTextDialog(mContext, title, msg);


        return dialog;


    }


    public static Dialog getInputPassDialog(Context mContext, String title, String tagOne, String tagTwo, String hint) {
        InputPassDialog dialog = new InputPassDialog(mContext, title, tagOne, tagTwo, hint);


        return dialog;


    }


    /**
     * 返回一个 提示框
     */
    public static DialogHelper getTiShiDialog(Context context, String title,
                                              String msg, String rightStr,
                                              View.OnClickListener itemsOnClick) {


        Dialog cd = new AlertDialog.Builder(context).create();
        // 实例化对话框布局
        View dialogLayout = LayoutInflater.from(context).inflate(
                R.layout.base_tishi_dialog, null);
        // 设置标题
        TextView title_tv = (TextView) dialogLayout
                .findViewById(R.id.base_tishi_dialog_title);

        title_tv.setText(title);
        // 设置内容
        TextView msg_tv = (TextView) dialogLayout
                .findViewById(R.id.base_tishi_dialog_txt);
        msg_tv.setVisibility(View.VISIBLE);
        msg_tv.setText(msg);


        TextView delbtn = (TextView) dialogLayout
                .findViewById(R.id.base_tishi_dialog_sure_btn_b);
        if (!StringUtils.isEmpty(rightStr)) {
            delbtn.setText(rightStr);
        } else {
            delbtn.setVisibility(View.VISIBLE);
        }
        delbtn.setOnClickListener(itemsOnClick);

        cd.show();

        cd.setContentView(dialogLayout);
        instantce = getInstances();
        instantce.setConfigDialog(cd);
        return instantce;


    }


    /**
     * 返回一个加载中的对话框
     *
     * @param activity
     * @param message
     * @return
     */
    public static WaitDialog getWaitDialog(Activity activity, String message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.dialog_waiting);
            dialog.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }


}
