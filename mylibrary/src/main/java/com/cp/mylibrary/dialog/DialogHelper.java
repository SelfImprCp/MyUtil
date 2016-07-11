package com.cp.mylibrary.dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cp.mylibrary.R;
import com.cp.mylibrary.utils.StringUtils;


public class DialogHelper {


	private static DialogHelper instantce = null;
	private Dialog configDialog;

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

	/**
	 * 返回一个加载中的对话框
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
	/**
	 * @param context
	 * @param title
	 * @param msg
	 * @param rightStr
	 * @param leftStr
	 * @param itemsOnClick
	 * @return
	 */

	public static EditText msg_tv;

	public static EditText getDialogEditText() {
		return msg_tv;
	}


	/**
	 * 返回一个带输入框的
	 * @param context
	 * @param title
	 * @param msg
	 * @param rightStr
	 * @param leftStr
	 * @param itemsOnClick
     * @return
     */
	public static DialogHelper EditTextDialog(Context context, String title,
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
		// 输入内容
		msg_tv = (EditText) dialogLayout
				.findViewById(R.id.edit_config_dialog_txt);


		msg_tv.setVisibility(View.VISIBLE);
		msg_tv.setText(msg);
		Button cancelbtn = (Button) dialogLayout
				.findViewById(R.id.base_config_dialog_cannel_btn);
		if (!StringUtils.isEmpty(leftStr)) {
			cancelbtn.setText(leftStr);
		} else {
			cancelbtn.setVisibility(View.GONE);
		}
		Button delbtn = (Button) dialogLayout
				.findViewById(R.id.base_config_dialog_sure_btn);
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
		Button cancelbtn = (Button) dialogLayout
				.findViewById(R.id.base_config_dialog_cannel_btn);
		if (!StringUtils.isEmpty(leftStr)) {
			cancelbtn.setText(leftStr);
		} else {
			cancelbtn.setVisibility(View.GONE);
		}
		Button delbtn = (Button) dialogLayout
				.findViewById(R.id.base_config_dialog_sure_btn);
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


}
