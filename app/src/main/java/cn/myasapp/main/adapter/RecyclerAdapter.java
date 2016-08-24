package cn.myasapp.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cp.mylibrary.adapter.CommonAdapter;
import com.cp.mylibrary.adapter.ViewHolder;
import com.cp.mylibrary.pullto.recyclerview.BaseRecyclerAdapter;

import java.util.List;

import cn.myasapp.main.R;
import cn.myasapp.main.bean.MainFocus;

/**
 * Created by Jerry on 2016/7/13.
 *
 *
 */
public class RecyclerAdapter extends CommonAdapter<MainFocus> {


    public RecyclerAdapter(Context context, List<MainFocus> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder holder, MainFocus mainFocus, int postion) {
        holder.setText(R.id.item_focus_text, mainFocus.getName());

    }



    @Override
    public int getItemLayoutId() {
        return R.layout.item_focus;
    }





}
