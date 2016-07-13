package cn.myasapp.main.adapter;

import android.content.Context;

import com.cp.mylibrary.adapter.CommonAdapter;
import com.cp.mylibrary.adapter.ViewHolder;

import java.util.List;

import cn.myasapp.R;
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
    public void convert(ViewHolder holder, MainFocus mainFocus) {
        holder.setText(R.id.item_focus_text, mainFocus.getName());

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_focus;
    }
}
