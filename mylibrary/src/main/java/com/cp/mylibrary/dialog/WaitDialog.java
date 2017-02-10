package com.cp.mylibrary.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cp.mylibrary.R;
import com.github.ybq.android.spinkit.style.Wave;


public class WaitDialog extends Dialog {

	private TextView _messageTv;
	private  Context mContext;
	private ProgressBar waiting_pb;
	private static Boolean _isTablet = null;

	public WaitDialog(Context context) {
		super(context);
		mContext = context;
		init(context);

	}

	public WaitDialog(Context context, int defStyle) {
		super(context, defStyle);
		mContext= context;
		init(context);
	}

	protected WaitDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener listener) {
		super(context, cancelable, listener);
		mContext= context;
		init(context);
	}

	public static boolean dismiss(WaitDialog dialog) {
		if (dialog != null) {
			dialog.dismiss();
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.dismiss();
	}


	public static boolean hide(WaitDialog dialog) {
		if (dialog != null) {
			dialog.hide();
			return false;
		} else {
			return true;
		}
	}

	private void init(Context context) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
		_messageTv = (TextView) view.findViewById(R.id.waiting_tv);

		waiting_pb = (ProgressBar)view.findViewById(R.id.waiting_pb);
		Wave doubleBounce = new Wave();
		waiting_pb.setIndeterminateDrawable(doubleBounce);


		setContentView(view);
	}



	public static boolean show(WaitDialog waitdialog) {
		boolean flag;
		if (waitdialog != null) {
			waitdialog.show();
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if ( isTablet()) {
//			int i = (int) DensityUtils.dp2px(mContext,360F);
//			if (i < ScreenUtils.getScreenWidth(mContext)) {
//				WindowManager.LayoutParams params = getWindow()
//						.getAttributes();
//				params.width = i;
//				getWindow().setAttributes(params);
//			}
		}
	}


	public   boolean isTablet() {
		if (_isTablet == null) {
			boolean flag;
			if ((0xf & mContext.getResources()
					.getConfiguration().screenLayout) >= 3)
				flag = true;
			else
				flag = false;
			_isTablet = Boolean.valueOf(flag);
		}
		return _isTablet.booleanValue();
	}


	public void setMessage(int message) {
		_messageTv.setText(message);
	}

	public void setMessage(String message) {
		_messageTv.setText(message);
	}
	
	public void hideMessage() {
	    _messageTv.setVisibility(View.GONE);
	}
}
