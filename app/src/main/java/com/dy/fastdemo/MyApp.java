package com.dy.fastdemo;

import com.dy.fastframework.application.EventReceiver;
import com.dy.fastframework.application.SuperBaseApp;

public class MyApp extends SuperBaseApp {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected String setBaseUrl() {
        return "http://www.baidu.com/";
    }

    @Override
    public boolean isEnableDebugLog() {
        return true;
    }
}
