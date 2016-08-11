package com.cp.mylibrary.bean;



import java.io.Serializable;


/**
 * 实体类
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 *
 *
 * 如果子类是存在DB中的，不要继承这个类，子类必须写全所有的字段
 */

public   class MyEntity implements Serializable {


    /**
     *   
     */




    /**
     *  如果子类是要往DB中存储，那么子类就要再写一个id，因为存的时候 ，字段拿不到父类的
     */




}
