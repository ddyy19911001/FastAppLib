package com.dy.fastdemo;

import android.view.View;
import android.widget.ImageView;

import com.dy.fastframework.application.SuperBaseApp;
import com.dy.fastframework.picture.PictureSelectUtil;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;

import java.util.ArrayList;

import yin.deng.normalutils.utils.DataHolder;
import yin.deng.normalutils.utils.ImageLoadUtil;
import yin.deng.normalutils.utils.LogUtils;
import yin.deng.normalutils.utils.NoDoubleClickListener;
import yin.deng.superbase.activity.SuperBaseActivity;

public class TestPullAc extends SuperBaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindViewWithId() {
    }

    @Override
    public void initFirst() {

    }

    public void open(View v){
        LogUtils.i("点击");
        PictureSelectUtil.chooseMuiltPic(this,20, 3, new OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(ArrayList<ImageItem> items) {
                LogUtils.i("获取到图片："+items.get(0).getPath());
            }
        });
    }
}
