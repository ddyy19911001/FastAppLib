package com.dy.fastframework.picture;

import android.app.Activity;
import android.graphics.Color;

import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.MimeType;
import com.ypx.imagepicker.bean.SelectMode;
import com.ypx.imagepicker.bean.selectconfig.CropConfig;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;

import java.util.ArrayList;

import yin.deng.superbase.activity.SuperBaseActivity;

public class PictureSelectUtil {
    public static void chooseNinePic(Activity activity,int clums,OnImagePickCompleteListener onImagePickCompleteListener){
        ImagePicker.withMulti(new WeiChartPresenter(activity.getClass()))//指定presenter
                //设置选择的最大数
                .setMaxCount(9)
                //设置列数
                .setColumnCount(clums)
                //设置要加载的文件类型，可指定单一类型
                .mimeTypes(MimeType.ofImage())
                //设置需要过滤掉加载的文件类型
//                .filterMimeTypes(MimeType.GIF)
                .showCamera(true)//显示拍照
                .setPreview(true)//开启预览
                //大图预览时是否支持预览视频
                .setPreviewVideo(true)
                //设置视频单选
                .setVideoSinglePick(true)
                //设置图片和视频单一类型选择
                .setSinglePickImageOrVideoType(true)
                //当单选或者视频单选时，点击item直接回调，无需点击完成按钮
                .setSinglePickWithAutoComplete(true)
                .setOriginal(true)  //显示原图
                //设置单选模式，当maxCount==1时，可执行单选（下次选中会取消上一次选中）
                .setSelectMode(SelectMode.MODE_MULTI)
                //设置视频可选取的最大时长
                .setMaxVideoDuration(1200000L)
                //设置视频可选取的最小时长
                .setMinVideoDuration(60000L)
                //设置上一次操作的图片列表，下次选择时默认恢复上一次选择的状态
                .setLastImageList(null)
                //设置需要屏蔽掉的图片列表，下次选择时已屏蔽的文件不可选择
                .setShieldList(null)
                .pick(activity,onImagePickCompleteListener);
    }

    public static void chooseSiglePicNotCrop(Activity activity,int clums,OnImagePickCompleteListener onImagePickCompleteListener){
        ImagePicker.withMulti(new WeiChartPresenter(activity.getClass()))//指定presenter
                //设置选择的最大数
                //设置列数
                .setColumnCount(clums)
                //设置要加载的文件类型，可指定单一类型
                .mimeTypes(MimeType.ofImage())
                //设置需要过滤掉加载的文件类型
//                .filterMimeTypes(MimeType.GIF)
                .showCamera(true)//显示拍照
                .setPreview(true)//开启预览
                //大图预览时是否支持预览视频
                .setPreviewVideo(true)
                //设置视频单选
                .setVideoSinglePick(true)
                //设置图片和视频单一类型选择
                .setSinglePickImageOrVideoType(true)
                //当单选或者视频单选时，点击item直接回调，无需点击完成按钮
                .setSinglePickWithAutoComplete(true)
                .setOriginal(true)  //显示原图
                //设置单选模式，当maxCount==1时，可执行单选（下次选中会取消上一次选中）
                .setSelectMode(SelectMode.MODE_SINGLE)
                //设置视频可选取的最大时长
                .setMaxVideoDuration(1200000L)
                //设置视频可选取的最小时长
                .setMinVideoDuration(60000L)
                //设置上一次操作的图片列表，下次选择时默认恢复上一次选择的状态
                .setLastImageList(null)
                //设置需要屏蔽掉的图片列表，下次选择时已屏蔽的文件不可选择
                .setShieldList(null)
                .pick(activity,onImagePickCompleteListener);
    }

    public static void chooseSiglePicCrop(Activity activity,int clums,OnImagePickCompleteListener onImagePickCompleteListener){
        ImagePicker.withMulti(new WeiChartPresenter(activity.getClass()))//指定presenter
                //设置选择的最大数
                //设置列数
                .setColumnCount(clums)
                //设置要加载的文件类型，可指定单一类型
                .mimeTypes(MimeType.ofImage())
                //设置需要过滤掉加载的文件类型
//                .filterMimeTypes(MimeType.GIF)
                .showCamera(true)//显示拍照
                .setPreview(true)//开启预览
                //大图预览时是否支持预览视频
                .setPreviewVideo(true)
                //设置视频单选
                .setVideoSinglePick(true)
                //设置图片和视频单一类型选择
                .setSinglePickImageOrVideoType(true)
                //当单选或者视频单选时，点击item直接回调，无需点击完成按钮
                .setSinglePickWithAutoComplete(true)
                .setOriginal(true)  //显示原图
                //设置单选模式，当maxCount==1时，可执行单选（下次选中会取消上一次选中）
                .setSelectMode(SelectMode.MODE_SINGLE)
                //设置视频可选取的最大时长
                .setMaxVideoDuration(1200000L)
                //设置视频可选取的最小时长
                .setMinVideoDuration(60000L)
                //设置上一次操作的图片列表，下次选择时默认恢复上一次选择的状态
                .setLastImageList(null)
                //设置需要屏蔽掉的图片列表，下次选择时已屏蔽的文件不可选择
                .setShieldList(null)
                //设置剪裁比例
                .setCropRatio(1,1)
                //设置剪裁框间距，单位px
                .cropRectMinMargin(50)
                //是否圆形剪裁，圆形剪裁时，setCropRatio无效
                //设置剪裁模式，留白或充满  CropConfig.STYLE_GAP 或 CropConfig.STYLE_FILL
                .cropStyle(CropConfig.STYLE_FILL)
                //设置留白模式下生成的图片背景色，支持透明背景
                .cropGapBackgroundColor(Color.TRANSPARENT)
                .crop(activity, onImagePickCompleteListener);
    }

    public static void chooseSigleHeadImgCrop(Activity activity,int clums,OnImagePickCompleteListener onImagePickCompleteListener){
        ImagePicker.withMulti(new WeiChartPresenter(activity.getClass()))//指定presenter
                //设置选择的最大数
                //设置列数
                .setColumnCount(clums)
                //设置要加载的文件类型，可指定单一类型
                .mimeTypes(MimeType.ofImage())
                //设置需要过滤掉加载的文件类型
//                .filterMimeTypes(MimeType.GIF)
                .showCamera(true)//显示拍照
                .setPreview(true)//开启预览
                //大图预览时是否支持预览视频
                .setPreviewVideo(true)
                //设置视频单选
                .setVideoSinglePick(true)
                //设置图片和视频单一类型选择
                .setSinglePickImageOrVideoType(true)
                //当单选或者视频单选时，点击item直接回调，无需点击完成按钮
                .setSinglePickWithAutoComplete(true)
                .setOriginal(true)  //显示原图
                //设置单选模式，当maxCount==1时，可执行单选（下次选中会取消上一次选中）
                .setSelectMode(SelectMode.MODE_SINGLE)
                //设置视频可选取的最大时长
                .setMaxVideoDuration(1200000L)
                //设置视频可选取的最小时长
                .setMinVideoDuration(60000L)
                //设置上一次操作的图片列表，下次选择时默认恢复上一次选择的状态
                .setLastImageList(null)
                //设置需要屏蔽掉的图片列表，下次选择时已屏蔽的文件不可选择
                .setShieldList(null)
                //设置剪裁比例
                .setCropRatio(1,1)
                //设置剪裁框间距，单位px
                .cropRectMinMargin(50)
                //是否圆形剪裁，圆形剪裁时，setCropRatio无效
                //设置剪裁模式，留白或充满  CropConfig.STYLE_GAP 或 CropConfig.STYLE_FILL
                .cropStyle(CropConfig.STYLE_FILL)
                .cropAsCircle()
                //设置留白模式下生成的图片背景色，支持透明背景
                .cropGapBackgroundColor(Color.TRANSPARENT)
                .crop(activity, onImagePickCompleteListener);
    }
}
