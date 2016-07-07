package cn.myasapp.main.bean;

import com.cp.mylibrary.bean.MyEntity;

/**
 * Created by Jerry on 2016/7/5.
 */
public class UserBean extends MyEntity {

      private String name ;

     private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
