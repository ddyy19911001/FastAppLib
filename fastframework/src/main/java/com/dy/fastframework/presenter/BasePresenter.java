package com.dy.fastframework.presenter;

import android.app.Application;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yin.deng.dyrequestutils.http.FileListParams;
import yin.deng.dyrequestutils.http.HeaderParam;
import yin.deng.dyrequestutils.http.MyHttpUtils;

/**
 * 使用方法：
 * 1.BasePresenter.getInstance().init();---->在App初始化时调用一次
 * 2.BasePresneter.getInstance().setListener();--->在各个activity或者fragment中调用设置数据监听
 * 3.BasePresneter.getInstance().get()/post();--->直接进行请求
 * setCustomerHeader可自定义请求头
 */
public class BasePresenter {
    public static BasePresenter basePresenter;
    public MyHttpUtils httpUtils;
    private List<HeaderParam> headers=new ArrayList<>();


    public BasePresenter(Application app) {
        httpUtils = new MyHttpUtils(app);
    }


    /**
     * 自定义请求头
     * @param headers
     */
    public void setCustomerHeader(List<HeaderParam> headers){
        this.headers=headers;
    }

    /**
     * 使用默认的请求头进行请求
     * 可能没有设置token
     * @param requestUrl
     * @param jsonObject
     * @param convertClass
     */
    public  void getUseDefaultHeader(String requestUrl, JsonObject jsonObject, Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        httpUtils.sendMsgGet(requestUrl,jsonObject,convertClass,listener);
    }

    /**
     * 使用自定义的请求头进行请求
     * @param requestUrl
     * @param jsonObject
     * @param convertClass
     */
    public  void getUseCustomerHeader(String requestUrl, JsonObject jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        if(headers.size()==0){
            getUseDefaultHeader(requestUrl, jsonObject, convertClass,listener);
        }else {
            httpUtils.sendMsgGet( requestUrl, headers, jsonObject, convertClass, listener);
        }
    }

    public void postUseDefaultHeader(String requestUrl,JsonObject jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        httpUtils.sendMsgPost(requestUrl,jsonObject,convertClass,listener);
    }

    public void postUseCustomerHeader(String requestUrl,JsonObject jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        if(headers.size()==0) {
            httpUtils.sendMsgPost( requestUrl, jsonObject, convertClass, listener);
        }else{
            httpUtils.sendMsgPost(requestUrl,headers,jsonObject,convertClass,listener);
        }
    }

    public void uploadUseDefaultHeader(String requestUrl, String fileName, File file, Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        httpUtils.doUploadSingleFile(requestUrl, fileName,file , convertClass,listener);
    }

    public void uploadUseCustomerHeader(String requestUrl, String fileName, File file, Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        if(headers.size()==0) {
            httpUtils.doUploadSingleFile( requestUrl, fileName, file, convertClass, listener);
        }else{
            httpUtils.doUploadSingleFile( requestUrl, headers,fileName, file, convertClass, listener);
        }
    }

    public void uploadMuiltDefaultHeader(String requestUrl, FileListParams fileListParams, Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        httpUtils.doUploadMuiltFiles(requestUrl, fileListParams , convertClass,listener);
    }

    public void uploadMuiltUseCustomerHeader(String requestUrl,  FileListParams fileListParams, Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        if(headers.size()==0) {
            httpUtils.doUploadMuiltFiles(requestUrl, fileListParams , convertClass,listener);
        }else{
            httpUtils.doUploadMuiltFiles( requestUrl, headers,fileListParams, convertClass, listener);
        }
    }

}
