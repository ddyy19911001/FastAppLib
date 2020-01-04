package com.dy.fastframework.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.dy.fastframework.web.MyWebView;

import yin.deng.superbase.activity.SuperBaseActivity;

/**
 * 请设置hardwareAccelerated=true---->可播放视频
 */
public abstract class BaseWebViewActivity extends SuperBaseActivity {
    public MyWebView base_webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //设置硬件加速
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        super.onCreate(savedInstanceState);
        base_webView = getBase_webView();
        base_webView.enableFullScrenVideo(this);//设置支持全屏播放
    }



    public abstract MyWebView getBase_webView();

    @Override
    public void onResume() {
        super.onResume();
        if(base_webView !=null){
            base_webView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(base_webView !=null){
            base_webView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(base_webView !=null){
            base_webView.destroy();
            base_webView =null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (base_webView !=null&&keyCode == KeyEvent.KEYCODE_BACK && base_webView.canGoBack()) {
            base_webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    @Override
    public void onConfigurationChanged(@NonNull Configuration config) {
        super.onConfigurationChanged(config);
        switch (config.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                break;
        }

    }
}
