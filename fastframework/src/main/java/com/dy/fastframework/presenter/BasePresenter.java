package com.dy.fastframework.presenter;

import android.app.Application;
import android.os.Environment;

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
    public MyHttpUtils httpUtils;
    public List<HeaderParam> headers=new ArrayList<>();
    public String logTag="DYLOG";
    public BasePresenter(Application app) {
        initHttpUtils(app);
    }



    /**
     * 初始化请求工具
     * 可以重写此方法自行配置httpUtils
     * @param app
     */
    public void initHttpUtils(Application app) {
        String downloadFileDir = Environment.getExternalStorageDirectory().getPath()+"/my_okHttp_download/";
        String cacheDir = Environment.getExternalStorageDirectory().getPath()+"/my_okHttp_cache";
        httpUtils = new MyHttpUtils(app,15,true,logTag,downloadFileDir,cacheDir);
    }

    /**
     * 初始化请求工具
     * 可以重写此方法自行配置httpUtils
     * @param app
     */
    public void initHttpUtils(Application app,int timeOutSeconds) {
        String downloadFileDir = Environment.getExternalStorageDirectory().getPath()+"/my_okHttp_download/";
        String cacheDir = Environment.getExternalStorageDirectory().getPath()+"/my_okHttp_cache";
        httpUtils = new MyHttpUtils(app,timeOutSeconds,true,logTag,downloadFileDir,cacheDir);
    }


    /**
     * 自定义请求头
     * 注意：此方法只适用于继承此类后使用过此方法的类
     * @param headers
     */
    public void setCustomerHeader(List<HeaderParam> headers){
        this.headers=headers;
    }

    /**
     * 此方法在每次请求时都会被调用
     * 可在此处设置不同的headers
     */
    public void initCustomerHeader() {

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

    public  void getUseDefaultHeader(String requestUrl, HashMap<String,String> jsonObject, Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        httpUtils.sendMsgGet(requestUrl,jsonObject,convertClass,listener);
    }

    /**
     * 使用自定义的请求头进行请求
     * @param requestUrl
     * @param jsonObject
     * @param convertClass
     */
    public  void getUseCustomerHeader(String requestUrl, JsonObject jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        initCustomerHeader();
        if(headers.size()==0){
            getUseDefaultHeader(requestUrl, jsonObject, convertClass,listener);
        }else {
            httpUtils.sendMsgGet( requestUrl, headers, jsonObject, convertClass, listener);
        }
    }



    public  void getUseCustomerHeader(String requestUrl, HashMap<String,String> jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        initCustomerHeader();
        if(headers.size()==0){
            getUseDefaultHeader(requestUrl, jsonObject, convertClass,listener);
        }else {
            httpUtils.sendMsgGet( requestUrl, headers, jsonObject, convertClass, listener);
        }
    }



    public void postUseDefaultHeader(String requestUrl,JsonObject jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        httpUtils.sendMsgPost(requestUrl,jsonObject,convertClass,listener);
    }

    public void postUseDefaultHeader(String requestUrl,HashMap<String,String> jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        httpUtils.sendMsgPost(requestUrl,jsonObject,convertClass,listener);
    }

    public void postUseCustomerHeader(String requestUrl,JsonObject jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        initCustomerHeader();
        if(headers.size()==0) {
            httpUtils.sendMsgPost( requestUrl, jsonObject, convertClass, listener);
        }else{
            httpUtils.sendMsgPost(requestUrl,headers,jsonObject,convertClass,listener);
        }
    }

    public void postUseCustomerHeader(String requestUrl,HashMap<String,String> jsonObject,Class convertClass, MyHttpUtils.OnGetInfoListener listener){
        initCustomerHeader();
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
        initCustomerHeader();
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
        initCustomerHeader();
        if(headers.size()==0) {
            httpUtils.doUploadMuiltFiles(requestUrl, fileListParams , convertClass,listener);
        }else{
            httpUtils.doUploadMuiltFiles( requestUrl, headers,fileListParams, convertClass, listener);
        }
    }

}
