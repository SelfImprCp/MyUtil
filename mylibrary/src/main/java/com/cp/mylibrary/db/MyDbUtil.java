package com.cp.mylibrary.db;

import android.content.Context;

import org.kymjs.kjframe.KJDB;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyDbUtil {


    public KJDB getKJDB(Context context) {
          KJDB kjdb = KJDB.create(context);

        return kjdb;

    }





}
