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
      //  http://apis.juhe.cn/catering/query?key=key&lng=121.538123&lat=31.677132&radius=2000
//        http://v.juhe.cn/estate/query
//        请求参数：city=%E8%8B%8F%E5%B7%9E&q=&page=1&dtype=&key=53f70d58c7add5763108dadc8bf7a824
//        请求方式：POST
//
        MyHttpParams params = new MyHttpParams();
        params.put("page", page);

        params.put("lng", "121.538123");
        params.put("lat", "31.677132");
        params.put("radius", 20000);



        params.put("key", "f896cc1c3e1e13ac44fa81bd4fc24146");


        String all_attentin_url = "query";
        MyHttpClient.post(all_attentin_url, params, handler);

    }

}
