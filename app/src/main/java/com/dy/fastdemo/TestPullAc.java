package com.dy.fastdemo;

import android.view.View;
import android.widget.ImageView;

import com.dy.fastframework.application.SuperBaseApp;

import java.util.ArrayList;

import yin.deng.normalutils.utils.DataHolder;
import yin.deng.normalutils.utils.ImageLoadUtil;
import yin.deng.superbase.activity.LogUtils;
import yin.deng.superbase.activity.SuperBaseActivity;

public class TestPullAc extends SuperBaseActivity {
    private ImageView img;
    private View button;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindViewWithId() {
        ArrayList<String> likes = new ArrayList<String>();
        likes.add("足球");
        likes.add("蓝球");
        likes.add("电脑");
        final UserInfo info=new UserInfo("我我",30,"男",likes);
        img=findViewById(R.id.iv_zoom);
        button=findViewById(R.id.bt);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 LogUtils.i("用户信息："+ SuperBaseApp.getSharedPreferenceUtil().getObj("user", UserInfo.class));
                 SuperBaseApp.getSharedPreferenceUtil().saveObject("user", info);
            }
        });
    }

    @Override
    public void initFirst() {
        ImageLoadUtil.initOptions(this);
        ImageLoadUtil.loadImage(img, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578484891134&di=8269673cbe4c7e675c547596a57c5f85&imgtype=0&src=http%3A%2F%2Fdl.ppt123.net%2Fpptbj%2F51%2F20181115%2Fmzj0ghw2xo2.jpg");
    }
}
