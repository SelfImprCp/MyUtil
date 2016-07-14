package com.cp.mylibrary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.cp.mylibrary.R;
import com.cp.mylibrary.bean.MyEntity;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry on 2016/6/17.
 * <p/>
 *
 *   带刷新加载更多的listView 要继承的
 * 封装好的类,正常情况下无需修改,子类继承只需要复写两个方法就可以了
 */
public abstract class ListBaseAdapter<T extends MyEntity> extends BaseAdapter {
//   //没有数据 了，已加载全部
//  //  public static final int STATE_EMPTY_ITEM = 0;
//    //加载更多
//    public static final int STATE_LOAD_MORE = 1;
//    //没有更多了，已加载全部
//    public static final int STATE_NO_MORE = 2;
//    //没有任何数据
//    public static final int STATE_NO_DATA = 3;
//    public static final int STATE_LESS_ONE_PAGE = 4;
//    //网络错误
//    public static final int STATE_NETWORK_ERROR = 5;
////    public static final int STATE_OTHER = 6;
//
//    protected int state = STATE_LESS_ONE_PAGE;

//    protected int _loadmoreText;
//    protected int _loadFinishText;
//    protected int _noDateText;
    protected int mScreenWidth;

    protected LayoutInflater mInflater;


    protected Context mContext;


    protected List<T> mDatas = new ArrayList<T>();


    /**
     * 子类要复写的方法,item 就是当前 要显示的对象,在此方法中进行数据的绑定
     *
     * @param helper
     * @param item
     */
    public abstract void convert(ViewHolder helper, T item);

    /**
     * 子类要复写的方法,指定item 的布局界面
     *
     * @param
     */
    public abstract int getItemLayoutId();





    protected LayoutInflater getLayoutInflater(Context context) {
        if (mInflater == null) {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return mInflater;
    }

    public void setScreenWidth(int width) {
        mScreenWidth = width;
    }

//    public void setState(int state) {
//        this.state = state;
//    }
//
//    public int getState() {
//        return this.state;
//    }


//    public ListBaseAdapter() {
//        _loadmoreText = R.string.loading;
//        _loadFinishText = R.string.loading_no_more;
//        _noDateText = R.string.error_view_no_data;
//    }

    @Override
    public int getCount() {
//        switch (getState()) {
////            case STATE_EMPTY_ITEM:
////                return getDataSizePlus1();
//            case STATE_NETWORK_ERROR:
//            case STATE_LOAD_MORE:
//                return getDataSize();
//            case STATE_NO_DATA:
//                return 1;
//            case STATE_NO_MORE:
//                return getDataSize();
//            case STATE_LESS_ONE_PAGE:
//                return getDataSize();
//            default:
//                break;
//        }



        return getDataSize();
    }


    public int getDataSize() {
        return mDatas.size();
    }

    @Override
    public T getItem(int arg0) {
        if (mDatas.size() > arg0) {
            return mDatas.get(arg0);
        }
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void setData(ArrayList<T> data) {
        mDatas = data;
        notifyDataSetChanged();
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

    public void addItem(T obj) {
        if (mDatas != null) {
            mDatas.add(obj);
        }
        notifyDataSetChanged();
    }

    public void addItem(int pos, T obj) {
        if (mDatas != null) {
            mDatas.add(pos, obj);
        }
        notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        mDatas.remove(obj);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }



    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    @Override
    /**
     *  封装后返回的view 有两种,最后一条,通过当前的状态来显示,如加载中,已加全部,其它的就是正常的
     *
     */

    public View getView(int position, View convertView, ViewGroup parent) {





        //构造viewHolder ,
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                getItemLayoutId(), position);

        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();


    }






}
