package com.dy.fastframework.application;

import android.app.Application;
import android.content.Context;


import com.dy.fastframework.BuildConfig;
import com.dy.fastframework.R;
import com.dy.fastframework.erro.CrashHandler;
import com.google.gson.Gson;

import yin.deng.dyrequestutils.http.LogUtils;
import yin.deng.normalutils.utils.ImageLoadUtil;
import yin.deng.normalutils.utils.SharedPreferenceUtil;
import yin.deng.refreshlibrary.refresh.SmartRefreshLayout;
import yin.deng.refreshlibrary.refresh.api.DefaultRefreshFooterCreater;
import yin.deng.refreshlibrary.refresh.api.DefaultRefreshHeaderCreater;
import yin.deng.refreshlibrary.refresh.api.RefreshFooter;
import yin.deng.refreshlibrary.refresh.api.RefreshHeader;
import yin.deng.refreshlibrary.refresh.api.RefreshLayout;
import yin.deng.refreshlibrary.refresh.viewimpl.MyFooterView;
import yin.deng.refreshlibrary.refresh.viewimpl.MyHeaderView;


public abstract class SuperBaseApp extends Application {
    private static SharedPreferenceUtil util;
    @Override
    public void onCreate() {
        super.onCreate();
        initDebugMode(isEnableDebugLog());
        if(isEnableDebugLog()){
            EventReceiver.getInstance().register();
        }
        initImgLoadSetting();
        initRefreshHeadAndFooter();
        CrashHandler.getInstance().init(this);
        util=new SharedPreferenceUtil(this, getApplicationInfo().packageName);
    }

    /**
     * 可以指定全局header和footer
     */
    public static void initRefreshHeadAndFooter(){
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
                @Override
                public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                    layout.setPrimaryColorsId(R.color.normal_bg, R.color.normal_4a);//全局设置主题颜色
                    MyHeaderView head = new MyHeaderView(context);
                    return head;//指定为经典Header，默认是 贝塞尔雷达Header
                }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
                @Override
                public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                    //指定为经典Footer，默认是 BallPulseFooter
                    layout.setPrimaryColorsId(R.color.normal_bg, R.color.normal_4a);//全局设置主题颜色
                    MyFooterView footer = new MyFooterView(context);
                    return footer;
                }
        });
    }


    /**
     * 图片加载器初始化
     */
    protected  void initImgLoadSetting(){
        ImageLoadUtil.initOptions(this, R.drawable.ic_default_erro_img,R.drawable.ic_default_erro_img);
    }


    public static SharedPreferenceUtil getSharedPreferenceUtil(){
        return util;
    }


    /**
     * 是否开启debug的Log,release自动关闭log
     * @return
     */
    public abstract boolean isEnableDebugLog();

    /**
     * 设置日志打印，在正式环境中不显示
     */
    public void initDebugMode(boolean isDebug) {
        LogUtils.allowV= isDebug;
        LogUtils.allowD= isDebug;
        LogUtils.allowE= isDebug;
        LogUtils.allowI= isDebug;
        LogUtils.allowW= isDebug;
    }
}
