package com.dy.fastframework.picture;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.dy.fastframework.R;
import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.adapter.PickerItemAdapter;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.selectconfig.BaseSelectConfig;
import com.ypx.imagepicker.data.ICameraExecutor;
import com.ypx.imagepicker.data.IReloadExecutor;
import com.ypx.imagepicker.data.ProgressSceneEnum;
import com.ypx.imagepicker.presenter.IPickerPresenter;
import com.ypx.imagepicker.utils.PViewSizeUtils;
import com.ypx.imagepicker.views.PickerUiConfig;
import com.ypx.imagepicker.views.PickerUiProvider;
import com.ypx.imagepicker.views.base.PickerFolderItemView;
import com.ypx.imagepicker.views.base.PickerItemView;
import com.ypx.imagepicker.views.base.SingleCropControllerView;
import com.ypx.imagepicker.views.wx.WXFolderItemView;
import com.ypx.imagepicker.views.wx.WXItemView;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import yin.deng.normalutils.utils.ImageLoadUtil;
import yin.deng.superbase.activity.LogUtils;
import yin.deng.superbase.activity.SuperBaseActivity;

public class WeiChartPresenter implements IPickerPresenter, Serializable {
    Class targetActivity;
    public boolean isChoosePicture;
    public WeiChartPresenter(Class activity,boolean isChoosePicture) {
        this.isChoosePicture=isChoosePicture;
        targetActivity=activity;
    }

    @Override
    public void displayImage(View view, ImageItem item, int size, boolean isThumbnail) {
        ImageLoadUtil.initOptions(view.getContext());
        if (isThumbnail) {
            ImageLoadUtil.loadImageThumb((ImageView) view, item.path);
        } else {
            Glide.with(view.getContext()).load(item.path).apply(new RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888)).into((ImageView) view);
        }
    }

    @Override
    public PickerUiConfig getUiConfig(Context context) {
        PickerUiConfig uiConfig = new PickerUiConfig();
        //设置是否显示标题栏
        uiConfig.setShowStatusBar(true);
        //设置标题栏颜色
        uiConfig.setStatusBarColor(Color.parseColor("#F5F5F5"));
        //设置选择器背景
        uiConfig.setPickerBackgroundColor(Color.BLACK);
        //设置单图剪裁背景色
        uiConfig.setSingleCropBackgroundColor(Color.BLACK);
        //设置预览页面背景色
        uiConfig.setPreviewBackgroundColor(Color.BLACK);
        //设置选择器文件夹打开方向
        uiConfig.setFolderListOpenDirection(PickerUiConfig.DIRECTION_BOTTOM);
        //设置文件夹列表距离顶部/底部边距
        uiConfig.setFolderListOpenMaxMargin(0);
        //设置小红书剪裁区域的背景色
        uiConfig.setCropViewBackgroundColor(Color.BLACK);

        if (context != null) {
            uiConfig.setFolderListOpenMaxMargin(PViewSizeUtils.dp(context, 100));
        }

        uiConfig.setPickerUiProvider(new PickerUiProvider() {
            @Override
            public PickerItemView getItemView(Context context) {
                WXItemView itemView = (WXItemView) super.getItemView(context);
                itemView.setBackgroundColor(Color.parseColor("#303030"));
                return itemView;
            }

            @Override
            public PickerFolderItemView getFolderItemView(Context context) {
                WXFolderItemView itemView = new WXFolderItemView(context);
                itemView.setIndicatorColor(Color.parseColor("#303030"));
                return itemView;
            }

            @Override
            public SingleCropControllerView getSingleCropControllerView(Context context) {
                return super.getSingleCropControllerView(context);
            }
        });
        return uiConfig;
    }


    /**
     * 提示
     *
     * @param context 上下文
     * @param msg     提示文本
     */
    @Override
    public void tip(Context context, String msg) {
        if (context == null) {
            return;
        }

        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 选择超过数量限制提示
     *
     * @param context  上下文
     * @param maxCount 最大数量
     */
    @Override
    public void overMaxCountTip(Context context, int maxCount) {
        tip(context, "最多选择" + maxCount + "个文件");
    }

    @Override
    public DialogInterface showProgressDialog(@Nullable Activity activity, ProgressSceneEnum progressSceneEnum) {
        return ProgressDialog.show(activity, null, progressSceneEnum == ProgressSceneEnum.crop ? "正在剪裁..." : "正在加载...");
    }


    @Override
    public boolean interceptPickerCompleteClick(Activity activity, ArrayList<ImageItem> selectedList, BaseSelectConfig selectConfig) {
        LogUtils.i( "拦截了完成按钮点击" + selectedList.size());
        return false;
    }

    @Override
    public boolean interceptPickerCancel(final Activity activity, ArrayList<ImageItem> selectedList) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        activity.finish();
        return true;
    }

    @Override
    public boolean interceptItemClick(@Nullable Activity activity, ImageItem imageItem,
                                      ArrayList<ImageItem> selectImageList,
                                      ArrayList<ImageItem> allSetImageList,
                                      BaseSelectConfig selectConfig,
                                      PickerItemAdapter adapter,
                                      @Nullable IReloadExecutor reloadExecutor) {

        return false;
    }

    @Override
    public boolean interceptCameraClick(@Nullable final Activity activity, final ICameraExecutor takePhoto) {
        if (activity == null || activity.isDestroyed()) {
            return false;
        }
        if (isChoosePicture) {
            takePhoto.takePhoto();
        } else {
            takePhoto.takeVideo();
        }
        return true;
    }


//    @Override
//    public PickConstants getPickConstants(Context context) {
//        return new PickConstants(context);
//    }
}
