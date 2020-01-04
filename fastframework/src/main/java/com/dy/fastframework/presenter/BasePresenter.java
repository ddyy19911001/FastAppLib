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
    public String baseUrl;
    public MyHttpUtils.OnGetInfoListener listener;
    private List<HeaderParam> headers=new ArrayList<>();

    public BasePresenter() {
    }

    /**
     * 在App初始化时调用
     * @param baseUrl
     * @param application
     * @return
     */
    public BasePresenter init(String baseUrl, Application application){
        if(httpUtils==null) {
            httpUtils = new MyHttpUtils(application);
        }
        this.baseUrl=baseUrl;
        return basePresenter;
    }

    public static BasePresenter getInstance(){
        if(basePresenter==null){
            basePresenter=new BasePresenter();
        }
        return basePresenter;
    }

    /**
     * 自定义请求头
     * @param headers
     */
    public void setCustomerHeader(List<HeaderParam> headers){
        this.headers=headers;
    }

    public BasePresenter setInfoGetListener(MyHttpUtils.OnGetInfoListener infoGetListener){
        this.listener=infoGetListener;
        return basePresenter;
    }


    /**
     * 使用默认的请求头进行请求
     * 可能没有设置token
     * @param methodName
     * @param jsonObject
     * @param convertClass
     */
    public  void getUseDefaultHeader(String methodName, JsonObject jsonObject,Class convertClass){
        httpUtils.sendMsgGet(baseUrl+methodName,jsonObject,convertClass,listener);
    }

    /**
     * 使用自定义的请求头进行请求
     * @param methodName
     * @param jsonObject
     * @param convertClass
     */
    public  void getUseCustomerHeader(String methodName, JsonObject jsonObject,Class convertClass){
        if(headers.size()==0){
            getUseDefaultHeader(baseUrl+methodName, jsonObject, convertClass);
        }else {
            httpUtils.sendMsgGet(baseUrl + methodName, headers, jsonObject, convertClass, listener);
        }
    }

    public void postUseDefaultHeader(String methodName,JsonObject jsonObject,Class convertClass){
        httpUtils.sendMsgPost(baseUrl+methodName,jsonObject,convertClass,listener);
    }

    public void postUseCustomerHeader(String methodName,JsonObject jsonObject,Class convertClass){
        if(headers.size()==0) {
            httpUtils.sendMsgPost(baseUrl + methodName, jsonObject, convertClass, listener);
        }else{
            httpUtils.sendMsgPost(baseUrl+methodName,headers,jsonObject,convertClass,listener);
        }
    }

    public void uploadUseDefaultHeader(String methodName, String fileName, File file, Class convertClass){
        httpUtils.doUploadSingleFile(baseUrl+methodName, fileName,file , convertClass,listener);
    }

    public void uploadUseCustomerHeader(String methodName, String fileName, File file, Class convertClass){
        if(headers.size()==0) {
            httpUtils.doUploadSingleFile(baseUrl + methodName, fileName, file, convertClass, listener);
        }else{
            httpUtils.doUploadSingleFile(baseUrl + methodName, headers,fileName, file, convertClass, listener);
        }
    }

    public void uploadMuiltDefaultHeader(String methodName, FileListParams fileListParams, Class convertClass){
        httpUtils.doUploadMuiltFiles(baseUrl+methodName, fileListParams , convertClass,listener);
    }

    public void uploadMuiltUseCustomerHeader(String methodName,  FileListParams fileListParams, Class convertClass){
        if(headers.size()==0) {
            httpUtils.doUploadMuiltFiles(baseUrl+methodName, fileListParams , convertClass,listener);
        }else{
            httpUtils.doUploadMuiltFiles(baseUrl + methodName, headers,fileListParams, convertClass, listener);
        }
    }

}
