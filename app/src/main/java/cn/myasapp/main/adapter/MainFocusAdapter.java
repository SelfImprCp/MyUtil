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


import com.cp.mylibrary.adapter.CommonAdapter;
import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.adapter.ViewHolder;
import com.cp.mylibrary.utils.LogCp;

import java.util.ArrayList;
import java.util.List;


import cn.myasapp.main.R;
import cn.myasapp.main.bean.MainFocus;


/**
 * 主页我的关注
 *
 * @author Administrator
 */

public class MainFocusAdapter extends ListBaseAdapter<MainFocus> {


 public MainFocusAdapter (Context context )
 {
     mContext = context;

 }



    @Override
    public void convert(ViewHolder helper, MainFocus item, int postion) {
        helper.setText(R.id.item_focus_text, item.getName());

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_focus;
    }


}
