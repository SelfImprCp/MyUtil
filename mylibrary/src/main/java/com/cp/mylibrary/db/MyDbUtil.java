package com.cp.mylibrary.db;

import org.kymjs.kjframe.KJDB;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyDbUtil {

    public KJDB kjdb = KJDB.create();

    public KJDB getKJDB() {
        return kjdb;

    }


}
