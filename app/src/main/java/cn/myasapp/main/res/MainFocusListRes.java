package cn.myasapp.main.res;

import com.cp.mylibrary.res.Response;

import java.util.List;

import cn.myasapp.main.bean.MainFocus;


/**
 * 主页的关注 返回
 *
 * @author Administrator
 */
public class MainFocusListRes extends Response {


    private List<MainFocus> result;

    public List<MainFocus> getResult() {
        return result;
    }

    public void setResult(List<MainFocus> result) {
        this.result = result;
    }
}
