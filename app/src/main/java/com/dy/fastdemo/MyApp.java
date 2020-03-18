package com.dy.fastdemo;

import com.dy.fastframework.application.EventReceiver;
import com.dy.fastframework.application.SuperBaseApp;

public class MyApp extends SuperBaseApp {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public boolean isEnableDebugLog() {
        return true;
    }
}
