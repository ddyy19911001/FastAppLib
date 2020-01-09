package com.dy.fastframework.application;

import android.app.Application;


import com.dy.fastframework.BuildConfig;
import com.dy.fastframework.erro.CrashHandler;
import com.google.gson.Gson;

import yin.deng.dyrequestutils.http.LogUtils;
import yin.deng.normalutils.utils.SharedPreferenceUtil;


public class SuperBaseApp extends Application {
    private static SharedPreferenceUtil util;
    @Override
    public void onCreate() {
        super.onCreate();
        if(isEnableDebugLog()) {
            initDebugMode();
        }
        CrashHandler.getInstance().init(this);
        util=new SharedPreferenceUtil(this, getApplicationInfo().packageName);
    }


    public static SharedPreferenceUtil getSharedPreferenceUtil(){
        return util;
    }


    /**
     * 是否开启debug的Log,release自动关闭log
     * @return
     */
    public boolean isEnableDebugLog(){
        return true;
    }

    /**
     * 设置日志打印，在正式环境中不显示
     */
    public void initDebugMode() {
        LogUtils.allowV= BuildConfig.DEBUG;
        LogUtils.allowD= BuildConfig.DEBUG;
        LogUtils.allowE= BuildConfig.DEBUG;
        LogUtils.allowI= BuildConfig.DEBUG;
        LogUtils.allowW= BuildConfig.DEBUG;
    }
}
