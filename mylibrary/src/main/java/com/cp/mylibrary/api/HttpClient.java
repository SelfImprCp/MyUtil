package com.cp.mylibrary.api;

import com.cp.mylibrary.utils.LogCp;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;

/**
 * Created by Jerry on 2016/7/6.
 */
public class HttpClient {

   // public   static String HOST = "www.ailibuli.cn";


    public  static   String API_URL = "";

    public static KJHttp client;

//    public HttpClient() {
//    }



    /**
     *
     */
    public static void initHttp(String host,String suffix) {
        API_URL =  "https://" + host + "/"+suffix+"/%s";

        // 初始化网络请求
        client   = new KJHttp( );


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


        LogCp.d(LogCp.CP, HttpClient.class + " 请求方式：GET 无参"

        );

        LogCp.d(LogCp.CP, HttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

        );

    }

    /**
     *
     * @param partUrl
     * @param params
     * @param handler
     */

    public static void get(String partUrl, HttpParams params,
                           MyResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), params, handler);


        LogCp.d(LogCp.CP, HttpClient.class + " 请求方式：GET 有参"

        );
        LogCp.d(LogCp.CP, HttpClient.class + " 请求参数 ："
                + params.getUrlParams().toString()

        );
        LogCp.d(LogCp.CP, HttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

        );

    }

    /**
     * @param partUrl
     * @param params
     * @param handler
     */
    public static void post(String partUrl, HttpParams params,
                            MyResponseHandler handler) {

        client.post(getAbsoluteApiUrl(partUrl)  , params, handler);

        LogCp.d(LogCp.CP, HttpClient.class + " 请求方式：POST"

        );
        LogCp.d(LogCp.CP, HttpClient.class + " 请求参数 ："
                + params.getUrlParams().toString()

        );
        LogCp.d(LogCp.CP, HttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

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


        HttpParams params = new HttpParams();


        //这里传递json字符串，(JSONObject可以调用toString方法转换)
        LogCp.i(LogCp.CP, HttpClient.class + "上传的 json :" + jsonStr);
        params.putJsonParams(jsonStr);


        client.jsonPost(url, params, responseHandler);


    }



    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL, partUrl);
        //  Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }


    public static String getApiUrl() {
        return API_URL;
    }











}
