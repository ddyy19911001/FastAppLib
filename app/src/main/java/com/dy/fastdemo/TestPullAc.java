package com.dy.fastdemo;

import android.widget.ImageView;

import com.dy.fastframework.picture.ImageLoadUtil;
import com.dy.fastframework.view.ZoomScrollView;

import yin.deng.superbase.activity.SuperBaseActivity;

public class TestPullAc extends SuperBaseActivity {
    private ZoomScrollView mScrollView;
    private ImageView img;
    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindViewWithId() {
        mScrollView=findViewById(R.id.sc_zoom);
        img=findViewById(R.id.iv_zoom);
    }

    @Override
    public void initFirst() {
        ImageLoadUtil.initOptions(this);
        ImageLoadUtil.loadImage(img, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578484891134&di=8269673cbe4c7e675c547596a57c5f85&imgtype=0&src=http%3A%2F%2Fdl.ppt123.net%2Fpptbj%2F51%2F20181115%2Fmzj0ghw2xo2.jpg");
    }
}
