package com.cp.mylibrary.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cp.mylibrary.R;


/**
 * 创建人：  创建时间：2015-4-20 下午5:27:42 修改备注：
 * 
 * @version 1.0
 */
public class BasicDialog {

	private static BasicDialog instantce = null;
	private Dialog dialog;
	private Dialog configDialog;
	private Dialog alertDialog;

	public Dialog getDialog() {
		return dialog;
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}

	public Dialog getAlertDialog() {
		return alertDialog;
	}

	public void setAlertDialog(Dialog alertDialog) {
		this.alertDialog = alertDialog;
	}

	public Dialog getConfigDialog() {
		return configDialog;
	}

	public void setConfigDialog(Dialog configDialog) {
		this.configDialog = configDialog;
	}

	private static BasicDialog getInstances() {
		if (instantce == null) {
			instantce = new BasicDialog();
		}
		return instantce;
	}

	/**
	 * 加载框
	 */
	public static BasicDialog loadDialog(Context context, String msg) {
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

	/**
	 * 
	 * @param dialog
	 */
	public static void hideDialog(Dialog dialog) {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	/**
	 * 简单提示框
	 */

	// public static BasicDialog configSimpleDialog(Context context, String txt,
	// String btnTxt, OnClickListener itemsOnClick) {
	// Dialog cd = new AlertDialog.Builder(context).create(); // 实例化对话框布局 View
	// // dialogLayout
	// // =
	// LayoutInflater.from(context).inflate(R.layout.base_simple_dialog, null);
	// Button delBtn = (Button) dialogLayout
	// .findViewById(R.id.base_simple_dialog_btn);
	// TextView content = (TextView) dialogLayout
	// .findViewById(R.id.base_simple_dialog_txt);
	// TextView closeBtn = (TextView) dialogLayout
	// .findViewById(R.id.base_simple_dialog_close);
	// content.setText(txt);
	// delBtn.setText(btnTxt);
	// delBtn.setOnClickListener(itemsOnClick);
	// closeBtn.setOnClickListener(itemsOnClick);
	// cd.setCanceledOnTouchOutside(false); // 触摸外部不会取消 cd.show();
	// cd.setContentView(dialogLayout);
	// instantce = getInstances();
	// instantce.setConfigDialog(cd);
	// return instantce;
	// }

	/**
	 * 版本更新提示框
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param rightStr
	 * @param leftStr
	 * @param itemsOnClick
	 * @return
	 */
	public static BasicDialog versionDialog(Context context, String title,
											String msg, String rightStr, String leftStr,
											OnClickListener itemsOnClick) {
		Dialog cd = new AlertDialog.Builder(context).create();
		// 实例化对话框布局
		View dialogLayout = LayoutInflater.from(context).inflate(
				R.layout.base_version_dialog, null);
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
		if (leftStr != null) {
			cancelbtn.setText(leftStr);
		}
		Button delbtn = (Button) dialogLayout
				.findViewById(R.id.base_config_dialog_sure_btn);
		if (rightStr != null) {
			delbtn.setText(rightStr);
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

	public static BasicDialog configDialog(Context context, String title,
										   String msg, String rightStr, String leftStr,
										   OnClickListener itemsOnClick) {
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
		if (leftStr != null) {
			cancelbtn.setText(leftStr);
		}
		Button delbtn = (Button) dialogLayout
				.findViewById(R.id.base_config_dialog_sure_btn);
		if (rightStr != null) {
			delbtn.setText(rightStr);
		}
		delbtn.setOnClickListener(itemsOnClick);
		cancelbtn.setOnClickListener(itemsOnClick);
		cd.show();

		cd.setContentView(dialogLayout);
		instantce = getInstances();
		instantce.setConfigDialog(cd);
		return instantce;
	}

	public static BasicDialog EditTextDialog(Context context, String title,
											 String msg, String rightStr, String leftStr,
											 OnClickListener itemsOnClick) {
		Dialog cd = new AlertDialog.Builder(context).create();
		// 实例化对话框布局
		View dialogLayout = LayoutInflater.from(context).inflate(
				R.layout.base_config_dialog, null);
		// 设置标题
		TextView title_tv = (TextView) dialogLayout
				.findViewById(R.id.base_config_dialog_title);
		title_tv.setText(title);
		// 输入内容
		EditText msg_tv = (EditText) dialogLayout
				.findViewById(R.id.edit_config_dialog_txt);
		msg_tv.setVisibility(View.VISIBLE);
		msg_tv.setText(msg);
		Button cancelbtn = (Button) dialogLayout
				.findViewById(R.id.base_config_dialog_cannel_btn);
		if (leftStr != null) {
			cancelbtn.setText(leftStr);
		}
		Button delbtn = (Button) dialogLayout
				.findViewById(R.id.base_config_dialog_sure_btn);
		if (rightStr != null) {
			delbtn.setText(rightStr);
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
