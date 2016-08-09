package com.cp.mylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cp.mylibrary.interf.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *不带下拉刷新，上拉加载的recyclerView要继承的
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends

	 RecyclerView.Adapter<ViewHolder>
{



	protected Context mContext;
 	protected List<T> mDatas;
	protected LayoutInflater mInflater;

  private OnItemClickListener onItemClickListener;


	/**
	 *
	 * @param onItemClickListener
     */
	public void setOnItemClickListener(OnItemClickListener onItemClickListener)
	{
		this.onItemClickListener = onItemClickListener;
	}

	public CommonAdapter(Context context, List<T> datas)
	{
		mContext = context;
		mInflater = LayoutInflater.from(context);

		mDatas = datas;
	}

	@Override
	public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
	{
		ViewHolder viewHolder = ViewHolder.get(mContext, null, parent, getItemLayoutId(), -1);
	 	setListener(parent, viewHolder, viewType);
		return viewHolder;
	}

	protected int getPosition(RecyclerView.ViewHolder viewHolder)
	{
		return viewHolder.getAdapterPosition();
	}

	protected boolean isEnabled(int viewType)
	{
		return true;
	}




	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		holder.updatePosition(position);
		convert(holder, mDatas.get(position),position);

	}

	/**
	 *  子类必须复写，
	 * @param holder
	 * @param t
     */
	public abstract void convert(ViewHolder holder, T t,int postion);

	/**
	 *
	 * @return
     */
	public abstract int  getItemLayoutId();




	protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType)
	{
		if (!isEnabled(viewType)) return;
		viewHolder.getConvertView().setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (onItemClickListener != null)
				{
					int position = getPosition(viewHolder);
					onItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
				}
			}
		});


		viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener()
		{
			@Override
			public boolean onLongClick(View v)
			{
				if (onItemClickListener != null)
				{
					int position = getPosition(viewHolder);
					return onItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
				}
				return false;
			}
		});
	}


	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	public List<T> getData() {
		return mDatas == null ? (mDatas = new ArrayList<T>()) : mDatas;
	}

	public void addData(List<T> data) {
		if (mDatas != null && data != null && !data.isEmpty()) {
			mDatas.addAll(data);
		}
		notifyDataSetChanged();
	}

}
