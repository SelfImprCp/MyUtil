package cn.myasapp.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.cp.mylibrary.adapter.ViewHolder;
import com.cp.mylibrary.interf.OnItemClickListener;
import com.cp.mylibrary.pullto.recyclerview.BaseRecyclerAdapter;

import cn.myasapp.main.R;
import cn.myasapp.main.bean.MainFocus;


/**
 *   带下拉刷新，上拉加载的recyclerView要继承的
 */
public class RefreshRecyclerAdapter extends BaseRecyclerAdapter<MainFocus> {

    public RefreshRecyclerAdapter(Context context  ) {
        super(context   );
    }


    @Override
    public int getItemLayoutId() {
        return R.layout.item_focus;
    }

    @Override
    public void convert(ViewHolder holder, MainFocus mainFocus) {
        holder.setText(R.id.item_focus_text, mainFocus.getName());

    }



}