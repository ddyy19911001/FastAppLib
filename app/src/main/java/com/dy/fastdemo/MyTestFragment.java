package com.dy.fastdemo;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.fastframework.activity.BaseRecycleViewActivity;
import com.dy.fastframework.fragment.BaseRecycleViewFragment;
import com.dy.fastframework.picture.PictureSelectUtil;
import com.dy.fastframework.presenter.BasePresenter;
import com.vise.xsnow.base.MyCallBackBind;
import com.vise.xsnow.base.MyCallBackImp;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yin.deng.normalutils.utils.ImageLoadUtil;
import yin.deng.normalutils.utils.LogUtils;
import yin.deng.normalutils.utils.NoDoubleClickListener;
import yin.deng.refreshlibrary.refresh.SmartRefreshLayout;

public class MyTestFragment extends BaseRecycleViewFragment<String,SplanshInfo> {
    private SmartRefreshLayout smRf;
    private RecyclerView rcView;

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        initVerticalRecycleView(rcView, smRf);
        initAdapter(R.layout.test_item, new BaseRecycleViewActivity.OnConvertDataLayoutListener<String>() {
            @Override
            public void onConvert(BaseViewHolder holder, String item) {
                holder.setText(R.id.tv, item);
            }
        });
        rcView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                PictureSelectUtil.chooseSiglePicNotCrop(getActivity(), 1, new OnImagePickCompleteListener() {
                    @Override
                    public void onImagePickComplete(ArrayList<ImageItem> items) {
                        LogUtils.i("获取到图片："+items.get(0).getPath());
                    }
                });
            }
        });
    }

    @Override
    public void loadDataFirst() {
        loadDataAtFirst();
    }

    @Override
    public void bindViewWithId(View view) {
        smRf = (SmartRefreshLayout)view. findViewById(R.id.smRf);
        rcView = (RecyclerView) view.findViewById(R.id.rcView);

    }

    @Override
    public void sendMsgToGetData(MyCallBackImp<SplanshInfo> callBackImp) {
        BasePresenter.requestGetWithBaseUrl("http://yapi.fhkeji.net/",
                "mock/170/api/common/startup_ad",
                null, new MyCallBackBind<SplanshInfo>(setHttpCallBack()){});
    }

    @Override
    public List<String> convertInfoDataToList(SplanshInfo obj) {
        List<String> strings=new ArrayList<>();
        strings.add("我是aaaa");
        strings.add("sdhjahjdf");
        return strings;
    }

    @Override
    public void onDataGetFialed(int errcode, String errMsg) {
        LogUtils.i("数据获取失败，错误原因:"+errMsg);
    }









}
