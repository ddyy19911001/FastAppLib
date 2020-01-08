package com.dy.fastframwork;




import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.dy.fastframework.activity.BaseTabViewActivity;
import com.dy.fastframework.picture.PictureSelectUtil;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.data.OnPickerCompleteListener;

import java.util.ArrayList;

import yin.deng.normalutils.utils.LogUtils;


public class MainViewActivity extends BaseTabViewActivity {
    private SmartTabLayout hmTabLayout;
    private ViewPager viewpager;
    private String[] tableNames;
    @Override
    public int setLayout() {
        return R.layout.default_tablayout_activity;
    }

    @Override
    public void bindViewWithId() {
        hmTabLayout = (SmartTabLayout) findViewById(R.id.hm_tab_layout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

    }


    @Override
    public boolean setIsExitActivity() {
        return true;
    }


    @Override
    public TableBuilder builderTable() {
        tableNames=new String[]{"测试1","测试2","测试3","测试4","测试5","测试6"};
       return TableBuilder.getInstance().getDefaultBuilder(this, tableNames, MyTestFragment.class, hmTabLayout, viewpager);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.w("选择---onCreate");
    }

    @Override
    public void initFirst() {
        LogUtils.w("执行选择");
    }




}
