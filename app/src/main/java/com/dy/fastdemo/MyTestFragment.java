package com.dy.fastdemo;

import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.fastframework.activity.BaseRecycleViewActivity;
import com.dy.fastframework.fragment.BaseRecycleViewFragment;
import com.dy.fastframework.presenter.BasePresenter;
import com.okhttplib.HttpInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yin.deng.dyrequestutils.http.BaseHttpInfo;
import yin.deng.normalutils.utils.EventHolder;
import yin.deng.normalutils.utils.ImageLoadUtil;
import yin.deng.normalutils.utils.LogUtils;
import yin.deng.refreshlibrary.refresh.SmartRefreshLayout;

public class MyTestFragment extends BaseRecycleViewFragment<String,SplanshInfo> {
    private SmartRefreshLayout smRf;
    private RecyclerView rcView;

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initVerticalRecycleView(rcView, smRf);
        initAdapter(R.layout.test_item, new BaseRecycleViewActivity.OnConvertDataLayoutListener<String>() {
            @Override
            public void onConvert(BaseViewHolder holder, String item) {
                holder.setText(R.id.tv, item);
            }
        });
        loadDataAtFirst();
    }

    @Override
    public void bindViewWithId(View view) {
        smRf = (SmartRefreshLayout)view. findViewById(R.id.smRf);
        rcView = (RecyclerView) view.findViewById(R.id.rcView);

    }

    @Override
    public void sendMsgToGetData() {
        BasePresenter presenter=new BasePresenter(MyApp.app);
        HashMap<String,String> map=new HashMap<>();
        presenter.getUseDefaultHeader("http://yapi.fhkeji.net/mock/170/api/common/startup_ad",map,SplanshInfo.class,this);
    }

    @Override
    protected List<String> convertInfoDataToList(SplanshInfo obj, HttpInfo info) {
        List<String> strings=new ArrayList<>();
        strings.add("我是aaaa");
        strings.add("sdhjahjdf");
        return strings;
    }




    @Override
    protected void onDataGetFialed(String requestUrl, HttpInfo info) {

    }
}
