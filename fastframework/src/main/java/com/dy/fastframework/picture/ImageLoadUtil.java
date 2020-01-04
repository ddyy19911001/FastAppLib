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



    private static void checkInit(){
        if(mContext==null){
            throw new IllegalStateException("请先初始化图片加载器（ImageLoadUtil.initOptions(context)!）");
        }
    }

    public static void initOptions(Context context){
        mContext=context;
    }

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
        checkInit();
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }
    }

    public static void loadImage(ImageView view,int picUrl){
        checkInit();
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }
    }

    public static void loadImageThumb(ImageView view,int picUrl){
        checkInit();
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }
    }

    public static void loadImageThumb(ImageView view,String picUrl){
        checkInit();
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }
    }

    public static void loadImageThumb(ImageView view,String picUrl,int placeholder){
        checkInit();
        if(placeholder!=0){
            mPlaceHolder=placeholder;
            mErroHolder=placeholder;
        }
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }
    }

    public static void loadImageThumb(ImageView view,String picUrl,int placeholder,int erroHolder){
        checkInit();
        if(placeholder!=0){
            mPlaceHolder=placeholder;
            mErroHolder=placeholder;
        }
        if(erroHolder!=0){
            mErroHolder=erroHolder;
        }
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(new MySimpleTargetImage(mContext, view));
        }
    }


    public static void loadImage(ImageView view,int picUrl,int placeHolder,int erroHolder){
        checkInit();
        if(placeHolder!=0&&erroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(placeHolder).error(erroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(placeHolder==0&&erroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else if(placeHolder==0&&erroHolder!=0){
            Glide.with(mContext).load(picUrl).error(erroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }
    }

    public static void loadImage(ImageView view,int picUrl,int placeHolder){
        checkInit();
        if(placeHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(placeHolder).error(placeHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }else{
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new MySimpleTargetImage(mContext, view));
        }
    }



}
