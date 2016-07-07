package cn.myasapp.main.api;

import com.cp.mylibrary.api.MyHttpClient;
import com.cp.mylibrary.api.MyHttpParams;
import com.cp.mylibrary.api.MyResponseHandler;

/**
 * Created by Jerry on 2016/7/7.
 */
public class TestApi {



    /**
     * 动态广场
     *
     * @param page
     * @param cityId
     * @param handler
     */
    public static void getMainFocusList(String url ,String page, String cityId,
                                        MyResponseHandler handler)

    {


        MyHttpParams params = new MyHttpParams();
        params.put("page", page);
        params.put("cityId", cityId);
        String all_attentin_url = url;
        MyHttpClient.post(all_attentin_url, params, handler);

    }

}
