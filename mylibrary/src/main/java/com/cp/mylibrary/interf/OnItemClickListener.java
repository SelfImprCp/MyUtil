package com.cp.mylibrary.interf;

import android.view.View;
import android.view.ViewGroup;


/**
 *  配合CommonAdapter 使用，单击及长按的监听事件
 * @param <T>
 */
public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}