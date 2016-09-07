package com.cp.mylibrary.api;

import com.cp.mylibrary.utils.LogCp;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyHttpClient {

   // public   static String HOST = "www.ailibuli.cn";


    public  static   String API_URL = "";

    public static KJHttp client;

public static String Cookie = "";


    /**
     *
     */
    public static void initHttp(String host)     {
        API_URL =  "http://" + host + "/";

        // 初始化网络请求
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.TIMEOUT = 5000*5;


        client   = new KJHttp(  );
 client.setConfig(httpConfig);



    }



    public static KJHttp getHttpClient() {
        return client;
    }



     //////////////////////////////////////////////////////////////////////

    /**
     *
     * @param partUrl
     * @param handler
     */

    public static void get(String partUrl, MyResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);


        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求方式：GET 无参"

        );

        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

        );

    }

    /**
     *
     * @param partUrl
     * @param params
     * @param handler
     */

    public static void get(String partUrl, MyHttpParams params,
                           MyResponseHandler handler) {
        params.putHeaders("cookie",Cookie);

        client.get(getAbsoluteApiUrl(partUrl), params, handler);


        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求方式：GET 有参"

        );
        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求参数 ："
                + params.getUrlParams().toString()

        );
        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

        );

    }

    /**
     * @param partUrl
     * @param params
     * @param handler
     */
    public static void post(String partUrl, MyHttpParams params,
                            MyResponseHandler handler) {
        params.putHeaders("cookie",Cookie);


        client.post(getAbsoluteApiUrl(partUrl)  , params, handler);

        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求方式：POST"

        );
        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求参数 ："
                + params.getUrlParams().toString()

        );
        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求头  ："
                +  params.getHeaders()

        );

        LogCp.d(LogCp.CP, MyHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

        );



    }



    /**
     * post 向服务器提交 json
     *
     * @param url
     * @param jsonStr
     * @param responseHandler
     */
    public static void postJsonUpServer(String url,
                                        String jsonStr, MyResponseHandler responseHandler) {


        MyHttpParams params = new MyHttpParams();


        //这里传递json字符串，(JSONObject可以调用toString方法转换)
        LogCp.i(LogCp.CP, MyHttpClient.class + "上传的 json :" + jsonStr);
        params.putJsonParams(jsonStr);


        client.jsonPost(url, params, responseHandler);


    }



    public static String getAbsoluteApiUrl(String partUrl) {
     //   String url = String.format(API_URL, partUrl);
        String url = API_URL + partUrl;
        //  Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }


    public static String getApiUrl() {
        return API_URL;
    }



}
