package com.dy.fastframework.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import yin.deng.superbase.activity.ScreenUtils;

public class MySimpleTargetImage extends SimpleTarget<GlideDrawable> {
    public Context context;
    public ImageView imageView;

    public MySimpleTargetImage(Context context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    public MySimpleTargetImage(int width, int height, Context context, ImageView imageView) {
        super(width, height);
        this.context = context;
        this.imageView = imageView;
    }


    @Override
    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
        int imageWidth = resource.getIntrinsicWidth();
        int imageHeight = resource.getIntrinsicHeight();
        int width = ScreenUtils.getScreenWidth(context);//固定宽度
        //宽度固定,然后根据原始宽高比得到此固定宽度需要的高度
        int height = width * imageHeight / imageWidth;
        ViewGroup.LayoutParams para = imageView.getLayoutParams();
        para.height = height;
        para.width = width;
        imageView.setImageDrawable(resource);
    }
}
