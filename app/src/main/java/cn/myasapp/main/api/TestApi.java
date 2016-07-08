package cn.myasapp.main.api;

import com.cp.mylibrary.api.MyHttpClient;
import com.cp.mylibrary.api.MyHttpParams;
import com.cp.mylibrary.api.MyResponseHandler;

import cn.myasapp.main.AppConfig;

/**
 * Created by Jerry on 2016/7/7.
 */
public class TestApi {



    /**
     *  测试 分页接口
     *
     * @param page
     * @param
     * @param handler
     */
    public static void getTestPageList( String page,
                                        MyResponseHandler handler)

    {

//        http://v.juhe.cn/estate/query
//        请求参数：city=%E8%8B%8F%E5%B7%9E&q=&page=1&dtype=&key=53f70d58c7add5763108dadc8bf7a824
//        请求方式：POST
//
        MyHttpParams params = new MyHttpParams();
        params.put("page", page);
        params.put("city", "苏州");
        params.put("key", "53f70d58c7add5763108dadc8bf7a824");


        String all_attentin_url = "query";
        MyHttpClient.post(all_attentin_url, params, handler);

    }

}
