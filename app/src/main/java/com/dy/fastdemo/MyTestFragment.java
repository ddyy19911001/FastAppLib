package com.dy.fastdemo;

import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.dy.fastframework.fragment.BaseRecycleViewFragment;
import com.okhttplib.HttpInfo;

import java.util.List;

import yin.deng.normalutils.utils.EventHolder;
import yin.deng.normalutils.utils.ImageLoadUtil;
import yin.deng.normalutils.utils.LogUtils;

public class MyTestFragment extends BaseRecycleViewFragment<String> {
    private ImageView img;

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        ImageLoadUtil.initOptions(getActivity());
        ImageLoadUtil.loadImage(img, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578484891134&di=8269673cbe4c7e675c547596a57c5f85&imgtype=0&src=http%3A%2F%2Fdl.ppt123.net%2Fpptbj%2F51%2F20181115%2Fmzj0ghw2xo2.jpg");
        EventHolder.getInstance().setOnEventListener("123", new EventHolder.OnEventGetListener() {
            @Override
            public void onEventGetInBackGround(Message msg) {
                switch (msg.what){
                    case 1:
                        LogUtils.i("接收到的内容为："+msg.obj);
                        break;
                }
            }
        });
    }

    @Override
    public void bindViewWithId(View view) {
        img=view.findViewById(R.id.iv_zoom);
    }

    @Override
    public void sendMsgToGetData() {

    }

    @Override
    protected List<String> convertInfoDataToList(String requestUrl, Object info) {
        return null;
    }

    @Override
    protected void onDataGetFialed(String requestUrl, HttpInfo info) {

    }
}
