package com.dy.fastframework.util;

import android.app.Activity;
import android.view.View;

import com.dy.fastframework.R;

import me.bakumon.statuslayoutmanager.library.OnStatusChildClickListener;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

public class ActivityLoadUtil {
    private static ActivityLoadUtil activityLoadUtil;
    public static ActivityLoadUtil getInstance(){
        if(activityLoadUtil ==null){
            activityLoadUtil =new ActivityLoadUtil();
        }
        return activityLoadUtil;
    }

    private ActivityLoadUtil() {

    }


    public StatusLayoutManager useDefaultLoadLayout(View view, OnStatusChildClickListener childClickListener){
        StatusLayoutManager manager=  useCustomLayout(view, R.layout.default_loading_layout,R.layout.defualt_empty_layout,R.layout.defualt_empty_layout,R.id.re_try_bt,childClickListener);
        return manager;
    }


    /**
     * 使用原始的加载布局---相当的丑
     *  @param view  需要替换的view
     * @param loadText  加载中文字
     * @param emptyText  空布局提示文字
     * @param emptyPic   空布局显示的图片
     * @param erroImg    错误布局显示的图片
     * @param erroText   错误的提示文字
     * @param isShowRetry  是否显示重试按钮
     * @param retyButtonColor 重试按钮的颜色
     * @param backGroundColor  布局的默认颜色
     * @return
     */
    public StatusLayoutManager useOriginalLoadLayout(View view,String loadText, String emptyText, int emptyPic,
                                                    int erroImg, String erroText, boolean isShowRetry,
                                                    int retyButtonColor, int backGroundColor,OnStatusChildClickListener childClickListener){
        StatusLayoutManager manager=  new StatusLayoutManager.Builder(view)
                // 设置默认加载中布局的提示文本
                .setDefaultLoadingText(loadText)
                // 设置默认空数据布局的提示文本
                .setDefaultEmptyText(emptyText)
                // 设置默认空数据布局的图片
                .setDefaultEmptyImg(emptyPic)
                // 设置默认出错布局提示的文本
                .setDefaultErrorText(erroText)
                // 设置默认空数据布局重试按钮是否显示
                .setDefaultEmptyClickViewVisible(isShowRetry)
                // 设置默认出错布局的图片
                .setDefaultErrorImg(erroImg)
                // 设置默认出错布局重试按钮的文本
                .setDefaultErrorClickViewText("重试")
                // 设置默认出错布局重试按钮的文本颜色
                .setDefaultErrorClickViewTextColor(retyButtonColor)
                // 设置默认布局背景，包括加载中、空数据和出错布局
                .setDefaultErrorClickViewVisible(isShowRetry)
                .setDefaultLayoutsBackgroundColor(backGroundColor)
                .setOnStatusChildClickListener(childClickListener)
                .build();
        return manager;
    }

    public StatusLayoutManager useCustomLayout(View rc, int loadingLayoutRes, int emptyLayoutRes, int erroLayoutRes, int retryId,OnStatusChildClickListener onStatusChildClickListener){
        StatusLayoutManager manager= new StatusLayoutManager
                .Builder(rc)
                // 设置加载中布局
                .setLoadingLayout(loadingLayoutRes)
                // 设置空数据布局
                .setEmptyLayout(emptyLayoutRes)
                // 设置出错布局
                .setErrorLayout(erroLayoutRes)
                // 设置空数据布局重试按钮 ID
                .setEmptyClickViewID(retryId)
                // 设置出错布局重试按钮 ID
                .setErrorClickViewID(retryId)
                .setOnStatusChildClickListener(onStatusChildClickListener)
                .build();
        return manager;
    }

    public View getRootView(Activity activity){
        return activity.getWindow().getDecorView().findViewById(android.R.id.content);
    }
}
