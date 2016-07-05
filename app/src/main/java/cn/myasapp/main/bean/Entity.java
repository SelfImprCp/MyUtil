package cn.myasapp.main.bean;



import java.io.Serializable;


/**
 * 实体类
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */

public   class Entity implements Serializable {


    /**
     *  如果子类是要往DB中存储，那么子类就要再写一个id，因为存的时候 ，字段拿不到父类的
     */

    public int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    /**
     *  如果子类是要往DB中存储，那么子类就要再写一个id，因为存的时候 ，字段拿不到父类的
     */




}
