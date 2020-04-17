package com.dy.fastframework.presenter;

import android.app.Application;
import android.os.Environment;


import com.google.gson.JsonObject;
import com.vise.xsnow.base.MyCallBackBind;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.mode.HttpHeaders;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 使用方法：
 */
public class BasePresenter {


    /**
     * get请求使用hashmap作为参数
     * @param path
     * @param params
     * @param callBackBind
     * @param <T>
     */
    public static <T>void requestGet(String path, HashMap<String,String> params, MyCallBackBind<T> callBackBind){
       ViseHttp.GET(path)
               .tag(callBackBind)
               .addParams(params)
               .request(callBackBind);
   }

    public static <T>void requestGetWithBaseUrl(String baseUrl,String path, HashMap<String,String> params, MyCallBackBind<T> callBackBind){
        ViseHttp.GET(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .addParams(params)
                .request(callBackBind);
    }

    public static <T>void requestGetWithHeaders(String path,HttpHeaders headers,HashMap<String,String> params, MyCallBackBind<T> callBackBind){
        ViseHttp.GET(path)
                .tag(callBackBind)
                .headers(headers)
                .addParams(params)
                .request(callBackBind);
    }

    public static <T>void requestGetWithHeadersAndBaseUrl(String baseUrl,String path,HttpHeaders headers,HashMap<String,String> params, MyCallBackBind<T> callBackBind){
        ViseHttp.GET(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .headers(headers)
                .addParams(params)
                .request(callBackBind);
    }


    /**
     * post请求使用hashmap作为参数
     * @param path
     * @param params
     * @param callBackBind
     * @param <T>
     */
    public static <T>void requestPost(String path, HashMap<String,String> params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .addParams(params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithBaseUrl(String baseUrl,String path, HashMap<String,String> params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .addParams(params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithHeaders(String path,HttpHeaders headers,HashMap<String,String> params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .headers(headers)
                .addParams(params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithHeadersAndBaseUrl(String baseUrl,String path,HttpHeaders headers,HashMap<String,String> params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .headers(headers)
                .addParams(params)
                .request(callBackBind);
    }


    /**
     * Post请求可以传入json字符串作为参数
     * @param path
     * @param params
     * @param callBackBind
     * @param <T>
     */
    public static <T>void requestPost(String path, String params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .setJson(params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithBaseUrl(String baseUrl,String path, String params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .setJson(params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithHeaders(String path,HttpHeaders headers, String params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .headers(headers)
                .setJson(params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithHeadersAndBaseUrl(String baseUrl,String path,HttpHeaders headers, String params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .headers(headers)
                .setJson(params)
                .request(callBackBind);
    }


    /**
     * Post请求可以传入表单数据作为参数
     * @param path
     * @param params
     * @param callBackBind
     * @param <T>
     */
    public static <T>void requestPost(String path, String key,Object params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .addForm(key,params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithBaseUrl(String baseUrl,String path, String key,Object params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .addForm(key,params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithHeaders(String path,HttpHeaders headers, String key,Object params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .headers(headers)
                .addForm(key,params)
                .request(callBackBind);
    }

    public static <T>void requestPostWithHeadersAndBaseUrl(String baseUrl,String path,HttpHeaders headers, String key,Object params, MyCallBackBind<T> callBackBind){
        ViseHttp.POST(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .headers(headers)
                .addForm(key,params)
                .request(callBackBind);
    }


    /**
     * 上传文件
     * @param path
     * @param params
     * @param callBackBind
     * @param <T>
     */
    public static <T>void uploadRequest(String path, HashMap<String,File> params, MyCallBackBind<T> callBackBind){
        ViseHttp.UPLOAD(path)
                .tag(callBackBind)
                .addFiles(params)
                .request(callBackBind);
    }

    public static <T>void uploadRequestWithBaseUrl(String baseUrl,String path, HashMap<String,File> params, MyCallBackBind<T> callBackBind){
        ViseHttp.UPLOAD(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .addFiles(params)
                .request(callBackBind);
    }

    public static <T>void uploadRequestWithHeadersAndBaseUrl(String baseUrl,String path,HttpHeaders headers, HashMap<String,File> params, MyCallBackBind<T> callBackBind){
        ViseHttp.UPLOAD(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .headers(headers)
                .addFiles(params)
                .request(callBackBind);
    }

    public static <T>void downLoadRequestWithHeadersAndBaseUrl(String baseUrl,String path,HttpHeaders headers, HashMap<String,File> params, MyCallBackBind<T> callBackBind){
        ViseHttp.UPLOAD(path)
                .tag(callBackBind)
                .baseUrl(baseUrl)
                .headers(headers)
                .addFiles(params)
                .request(callBackBind);
    }

}
