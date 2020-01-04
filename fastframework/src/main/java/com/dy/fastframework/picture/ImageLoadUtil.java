package com.dy.fastframework.picture;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class ImageLoadUtil {
    public static int mPlaceHolder;
    public static int mErroHolder;
    public static int mHeadHolder;
    public static Context mContext;

    public static void initOptions(Context context,int placeHolder){
        mPlaceHolder=placeHolder;
        mErroHolder=placeHolder;
        mContext=context;
    }

    public static void initOptions(Context context,int placeHolder,int ErroHolder){
        mPlaceHolder=placeHolder;
        mErroHolder=ErroHolder;
        mContext=context;
    }

    public static void initOptions(Context context,int placeHolder,int ErroHolder,int headHolder){
        mPlaceHolder=placeHolder;
        mErroHolder=ErroHolder;
        mHeadHolder=headHolder;
        mContext=context;
    }

    /**
     *初始化配置之后使用
     * @param view
     * @param picUrl
     */
    public static void loadImage(ImageView view,String picUrl){
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }
    }

    public static void loadImage(ImageView view,int picUrl){
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }
    }


    public static void loadImage(ImageView view,int picUrl,int placeHolder,int erroHolder){
        if(placeHolder!=0&&erroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(placeHolder).error(erroHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(placeHolder==0&&erroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(placeHolder==0&&erroHolder!=0){
            Glide.with(mContext).load(picUrl).error(erroHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }
    }

    public static void loadImage(ImageView view,int picUrl,int placeHolder){
        if(placeHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(placeHolder).error(placeHolder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new MySimpleTargetImage(mContext, view));
        }
    }



}
