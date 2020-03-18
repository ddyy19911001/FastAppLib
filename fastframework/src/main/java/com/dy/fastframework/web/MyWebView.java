package com.dy.fastframework.web;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;


import androidx.core.util.Pair;

import com.dy.fastframework.R;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import yin.deng.normalutils.utils.LogUtils;

public class MyWebView extends WebView {
    public FrameLayout mFrameLayout;
    public Context context;
    private String homeLinkUrl;
    private ProgressView progressView;
    public String[]adLinks;
    private Set<Pair<Integer, Integer>> mFlags = null;
    //可以设置页面变化的listener
    OnWebPageChangeListener onWebPageChangeListener;
    //重写拦截Url
    OnShouldOverridUrlListener overridAndInterCeptListener;
    //重写拦截网页内部请求逻辑
    OnShouldInterCeptUrlListener interCeptUrlListener;
    Activity activity;
    public void setOnWebPageChangeListener(OnWebPageChangeListener onWebPageChangeListener) {
        this.onWebPageChangeListener = onWebPageChangeListener;
    }

    public void setOverridAndInterCeptListener(OnShouldOverridUrlListener overridAndInterCeptListener) {
        this.overridAndInterCeptListener = overridAndInterCeptListener;
    }

    public void setInterCeptUrlListener(OnShouldInterCeptUrlListener interCeptUrlListener) {
        this.interCeptUrlListener = interCeptUrlListener;
    }

    /**
     * 设置支持全屏播放
     * @param activity
     */
    public void enableFullScrenVideo(Activity activity){
       this.activity=activity;
    }

    /**
     * 设置黑名单链接
     * @param adLinks
     */
    public void setBlackLinks(String [] adLinks){
        this.adLinks=adLinks;
    }

    public MyWebView(Context context) {
        super(context);
        initDefaultSetting(context);
    }
    public MyWebView(Context var1, AttributeSet var2) {
        super(var1, var2);
        initDefaultSetting(var1);
    }

    public MyWebView(Context var1, AttributeSet var2, int var3) {
        super(var1, var2, var3);
        initDefaultSetting(var1);
    }

    public void setHomeLinkUrlToFillterAd(String homeLinkUrl){
        this.homeLinkUrl=homeLinkUrl;
    }

    public void setProgressView(ProgressView progressView) {
        this.progressView = progressView;
        this.progressView.setProgress(10);
        Log.i("设置进度条", "成功");
    }

    public void initDefaultSetting(Context context) {
        this.context=context;
        mFlags=new HashSet<>();
        WebSettings webSetting = getSettings();
        //视频自动播放
        webSetting.setMediaPlaybackRequiresUserGesture(false);
        webSetting.setSavePassword(true);
        webSetting.setSaveFormData(true);// 保存表单数据
        webSetting.setAllowUniversalAccessFromFileURLs(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setSupportZoom(true);
        webSetting.setBlockNetworkImage(false);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(false);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(context.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(context.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(context.getDir("geolocation", 0)
                .getPath());
        webSetting.setPluginState(WebSettings.PluginState.ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        CookieSyncManager.createInstance(context);
        CookieSyncManager.getInstance().sync();
        if(client!=null) {
            setWebViewClient(client);
        }
        if(chromeClient!=null) {
            setWebChromeClient(chromeClient);
        }
        if(myDownLoadListener!=null) {
            setDownloadListener(myDownLoadListener);
        }
        setClickable(true);
    }

    private WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, final String url) {
            if(overridAndInterCeptListener!=null){
                if ( urlCanLoad(url.toLowerCase()))
                {  // 加载正常网页
                    view.loadUrl(url);
                }
                else
                {  // 打开第三方应用或者下载apk等
                    overridAndInterCeptListener.onIntentOpenOtherApp(view,url);
                }
            }else {
                shouldOverrideUrlLoadingInX5(view, url);
            }
            return true;
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            if(onWebPageChangeListener!=null){
                onWebPageChangeListener.onPageStart(webView,s);
            }
            super.onPageStarted(webView, s, bitmap);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            if(onWebPageChangeListener!=null){
                onWebPageChangeListener.onPageFinished(webView,s);
            }
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler handler, SslError sslError) {
            handler.proceed();
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url){
            return shouldInterceptRequestInX5(view, url, super.shouldInterceptRequest(view, url));
        }
    };


    public WebChromeClient chromeClient=new WebChromeClient(){

        private View mCustomView;
        private CustomViewCallback mCustomViewCallback;
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            if(activity!=null&&!activity.isFinishing()) {
                Window mWindow = activity.getWindow();
                Pair<Integer, Integer> mPair = null;
                // 保存当前屏幕的状态
                if ((mWindow.getAttributes().flags & WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) == 0) {
                    mPair = new Pair<>(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 0);
                    mWindow.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    mFlags.add(mPair);
                }

                if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) && (mWindow.getAttributes().flags & WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED) == 0) {
                    mPair = new Pair<>(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, 0);
                    mWindow.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
                    mFlags.add(mPair);
                }

                if (mCustomView != null) {
                    callback.onCustomViewHidden();
                    return;
                }
                mCustomView = view;
                if(mFrameLayout==null) {
                    mFrameLayout=new FrameLayout(context);
                    mFrameLayout.setBackgroundColor(Color.BLACK);
                    FrameLayout mDecorView = (FrameLayout) activity.getWindow().getDecorView();
                    mDecorView.addView(mFrameLayout);
                }
                mFrameLayout.addView(mCustomView);
                mFrameLayout.setVisibility(VISIBLE);
                mCustomViewCallback = callback;
                setVisibility(View.GONE);
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
            if(activity!=null) {
                setVisibility(View.VISIBLE);
                if (mCustomView == null) {
                    return;
                }
                if (!mFlags.isEmpty()) {
                    for (Pair<Integer, Integer> mPair : mFlags) {
                        activity.getWindow().setFlags(mPair.second, mPair.first);
                    }
                    mFlags.clear();
                }
                mCustomView.setVisibility(View.GONE);
                if (mFrameLayout != null && mCustomView != null) {
                    mFrameLayout.removeView(mCustomView);

                }
                if (mFrameLayout != null) {
                    mFrameLayout.setVisibility(View.GONE);
                }
                mCustomViewCallback.onCustomViewHidden();
                mCustomView = null;
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }


        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);
            if(onWebPageChangeListener!=null){
                onWebPageChangeListener.onReceivedTitle(webView,s);
            }
        }

        @Override
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if(progressView!=null&&i<100&&i>10){
                progressView.setVisibility(VISIBLE);
                progressView.setProgress(i);
            }else{
                if(progressView!=null){
                    progressView.setVisibility(GONE);
                    progressView.setProgress(10);
                }
            }
        }
    };


    public DownloadListener myDownLoadListener=new DownloadListener() {
        @Override
        public void onDownloadStart(final String url, final String userAgent, final String contentDisposition, final String mimetype, final long contentLength) {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("系统提示");
            builder.setMessage("是否打开浏览器下载该文件？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Log.e("X5WebView", "--- onDownloadStart ---" + " url = " + url + ", userAgent = " + userAgent + ", " +
                            "contentDisposition = " + contentDisposition + ", mimetype = " + mimetype + ", contentLength = " + contentLength);
                    downloadByBrowser(url);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    };

    /**
     * 处理各种intent
     * @param view
     * @param url
     */
    public void shouldOverrideUrlLoadingInX5(WebView view, final String url) {
        if ( urlCanLoad(url.toLowerCase()))
        {  // 加载正常网页
            view.loadUrl(url);
        }
        else
        {  // 打开第三方应用或者下载apk等
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("系统提示");
            builder.setMessage("此网页请求打开第三方应用，是否允许？");
            builder.setPositiveButton("允许打开", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    startThirdpartyApp(url);
                }
            });
            builder.setNegativeButton("拒绝打开", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }


    /**
     * 处理拦截广告
     * @param view
     * @param url
     * @param webResourceResponse
     * @return
     */
    public WebResourceResponse shouldInterceptRequestInX5(WebView view, String url, WebResourceResponse webResourceResponse) {
        url= url.toLowerCase();
        if(!TextUtils.isEmpty(homeLinkUrl) &&!url.contains(homeLinkUrl)){
            if(!ADFilterTool.hasAd(url,adLinks)){
                if(interCeptUrlListener!=null){
                    return interCeptUrlListener.onShouldInterCeptUrl(view,url);
                }else {
                    return webResourceResponse;
                }
            }else{
                return new WebResourceResponse(null,null,null);
            }
        }else{
            if(interCeptUrlListener!=null){
                return interCeptUrlListener.onShouldInterCeptUrl(view,url);
            }else {
                return webResourceResponse;
            }
        }
    }


    public interface OnWebPageChangeListener{
        void onReceivedTitle(WebView webView, String s);
        void onPageStart(WebView webView, String s);
        void onPageFinished(WebView webView, String s);
    }

    public interface OnShouldOverridUrlListener{
        void onIntentOpenOtherApp(WebView webView, String url);
    }

    public interface OnShouldInterCeptUrlListener{
        WebResourceResponse onShouldInterCeptUrl(WebView webView, String url);
    }

    /**
     * 列举正常情况下能正常加载的网页url
     * @param url
     * @return
     */
    public boolean urlCanLoad(String url)
    {
        return url.startsWith("http://") || url.startsWith("https://") ||
                url.startsWith("ftp://") || url.startsWith("file://");
    }


    /**
     * 打开第三方app。如果没安装则跳转到应用市场
     * @param url
     */
    public void startThirdpartyApp(String url)
    {
        try {
            Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME); // 注释1
            context.startActivity(intent);
        }
        catch (Exception e)
        {
            Log.e("X5WebView", e.getMessage());
//			Toast.makeText(context,"无法打开该应用！", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 通过浏览器下载
     * @param url
     */
    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public static class ADFilterTool{
        public static boolean hasAd(String url, String[]adUrls){
            if(adUrls==null||adUrls.length==0){
                return false;
            }
            for(String adUrl :adUrls){
                if(url.contains(adUrl)){
                    LogUtils.e("当前链接："+url+"\n含有广告链接："+adUrl);
                    return true;
                }
            }
            return false;
        }
    }


}
