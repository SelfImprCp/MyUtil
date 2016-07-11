package cn.myasapp.main.event;

import com.cp.mylibrary.event.BaseEvent;

/**
 * Created by Jerry on 2016/7/11.
 */
public class TestEvent extends BaseEvent {

     private String string ;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
