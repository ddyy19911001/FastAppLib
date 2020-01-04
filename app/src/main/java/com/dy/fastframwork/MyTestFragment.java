package com.dy.fastframwork;

import android.view.View;

import com.dy.fastframework.fragment.BaseRecycleViewFragment;
import com.okhttplib.HttpInfo;

import java.util.List;

public class MyTestFragment extends BaseRecycleViewFragment<String> {

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    public void bindViewWithId(View view) {

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
