package com.cp.mylibrary.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cp.mylibrary.R;
import com.cp.mylibrary.utils.NetWorkUtil;


/**
 *  没有网络 的自定义控件
 */
public class NoIntentView extends RelativeLayout {

 
	
   private LinearLayout shop_two_view_nointent_lin ;
 
	public NoIntentView(Context context) {
		super(context);
		 
		initView(context);
	}

	public NoIntentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public NoIntentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	private void initView(final Context context) {
		View.inflate(context, R.layout.no_intent_layout, this);
		 
		shop_two_view_nointent_lin = (LinearLayout)findViewById(R.id.shop_two_view_nointent_lin);
		
		shop_two_view_nointent_lin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				 NetWorkUtil netWorkUtil = new NetWorkUtil();

				netWorkUtil.openWirelessSet(context);


			}
		});
	}
	
	 
	 

}
