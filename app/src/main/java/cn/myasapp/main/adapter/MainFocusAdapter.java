package cn.myasapp.main.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.adapter.ViewHolder;

import java.util.ArrayList;

import cn.myasapp.R;
import cn.myasapp.main.bean.MainFocus;


/**
 * 主页我的关注
 * 
 * @author Administrator
 * 
 */

public class MainFocusAdapter extends ListBaseAdapter<MainFocus> {

	private Context mContext;

	private Dialog dialog;


	public MainFocusAdapter(  Context context) {

		this.mContext = context;

	}


	@Override
	public void convert(com.cp.mylibrary.adapter.ViewHolder helper, MainFocus item) {
 helper.setText(R.id.item_focus_text,item.getShopName());
	}

	@Override
	public int getItemLayoutId() {
		return R.layout.item_focus;
	}


}
