package com.cp.mylibrary.dialog;


import android.app.Activity;

import com.cp.mylibrary.R;


public class DialogHelper {
	



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
