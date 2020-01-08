package com.dy.fastframwork;

import android.view.View;
import android.widget.TextView;

import com.dy.fastframework.fragment.BaseRecycleViewFragment;
import com.dy.fastframework.picture.PictureSelectUtil;
import com.okhttplib.HttpInfo;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.data.OnPickerCompleteListener;

import java.util.ArrayList;
import java.util.List;

import yin.deng.normalutils.utils.LogUtils;
import yin.deng.normalutils.utils.NoDoubleClickListener;
import yin.deng.normalutils.utils.PicassoUtils;

public class MyTestFragment extends BaseRecycleViewFragment<String> {
    private TextView tvTest;
    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        tvTest.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                PictureSelectUtil.chooseSigleHeadImgCrop(getActivity(),3, new OnPickerCompleteListener<ArrayList<ImageItem>>() {
                    @Override
                    public ArrayList<ImageItem> onTransit(ArrayList<ImageItem> items) {
                        return items;
                    }

                    @Override
                    public void onPickComplete(ArrayList<ImageItem> imageItems) {
                        LogUtils.w("选择了："+imageItems.size()+"个图片\n"+"路径地址："+imageItems.get(0).getPath());
                    }
                });
            }
        });
    }

    @Override
    public void bindViewWithId(View view) {
        tvTest = (TextView) view.findViewById(R.id.tv_test);

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
