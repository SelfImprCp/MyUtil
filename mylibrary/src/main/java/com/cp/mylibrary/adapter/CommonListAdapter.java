//package com.cp.mylibrary.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import com.cp.mylibrary.interf.OnItemClickListener;
//
//import java.util.List;
//
///**
// *
// *
// *
// *
// * @param <T>
// */
//public abstract class CommonListAdapter<T> extends BaseAdapter<ViewHolder>
//{
//
//
//
//	protected Context mContext;
// 	protected List<T> mDatas;
//	protected LayoutInflater mInflater;
//
//  private OnItemClickListener onItemClickListener;
//
//
//	/**
//	 *
//	 * @param onItemClickListener
//     */
//	public void setOnItemClickListener(OnItemClickListener onItemClickListener)
//	{
//		this.onItemClickListener = onItemClickListener;
//	}
//
//	public CommonListAdapter(Context context, List<T> datas)
//	{
//		mContext = context;
//		mInflater = LayoutInflater.from(context);
//
//		mDatas = datas;
//	}
//
//	@Override
//	public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
//	{
//		ViewHolder viewHolder = ViewHolder.get(mContext, null, parent, getItemLayoutId(), -1);
//	 	setListener(parent, viewHolder, viewType);
//		return viewHolder;
//	}
//
//	protected int getPosition(RecyclerView.ViewHolder viewHolder)
//	{
//		return viewHolder.getAdapterPosition();
//	}
//
//	protected boolean isEnabled(int viewType)
//	{
//		return true;
//	}
//
//
//	@Override
//	public void onBindViewHolder(ViewHolder holder, int position)
//	{
//		holder.updatePosition(position);
//		convert(holder, mDatas.get(position));
//
//	}
//
//	/**
//	 *  子类必须复写，
//	 * @param holder
//	 * @param t
//     */
//	public abstract void convert(ViewHolder holder, T t);
//
//	/**
//	 *
//	 * @return
//     */
//	public abstract int  getItemLayoutId();
//
//	protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType)
//	{
//		if (!isEnabled(viewType)) return;
//		viewHolder.getConvertView().setOnClickListener(new View.OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				if (onItemClickListener != null)
//				{
//					int position = getPosition(viewHolder);
//					onItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
//				}
//			}
//		});
//
//
//		viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener()
//		{
//			@Override
//			public boolean onLongClick(View v)
//			{
//				if (onItemClickListener != null)
//				{
//					int position = getPosition(viewHolder);
//					return onItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
//				}
//				return false;
//			}
//		});
//	}
//
//	@Override
//	public int getItemCount()
//	{
//		return mDatas.size();
//	}
//}
