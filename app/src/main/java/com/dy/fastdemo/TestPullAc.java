package com.dy.fastdemo;

import android.view.View;
import android.widget.ImageView;

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
        img=findViewById(R.id.iv_zoom);
        button=findViewById(R.id.bt);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object object= DataHolder.getInstance().getData("1");
                if(object==null){
                    LogUtils.i("数据为：" + "空的");
                }else {
                    LogUtils.i("数据为：" + object.toString());
                }
                DataHolder.getInstance().saveData("1", "我是之前存入的数据");
            }
        });
    }

    @Override
    public void initFirst() {
        ImageLoadUtil.initOptions(this);
        ImageLoadUtil.loadImage(img, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578484891134&di=8269673cbe4c7e675c547596a57c5f85&imgtype=0&src=http%3A%2F%2Fdl.ppt123.net%2Fpptbj%2F51%2F20181115%2Fmzj0ghw2xo2.jpg");
    }
}
