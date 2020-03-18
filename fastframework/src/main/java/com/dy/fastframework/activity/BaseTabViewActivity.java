package com.dy.fastframework.activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dy.fastframework.tablayout.RecyclerTabLayout;

import java.util.ArrayList;
import java.util.List;

import yin.deng.superbase.activity.SuperBaseActivity;
import yin.deng.superbase.fragment.BasePagerAdapter;

public abstract class BaseTabViewActivity extends SuperBaseActivity {
    public RecyclerTabLayout recyclerTabLayout;
    public ViewPager viewpager;
    public List<Fragment> fgs=new ArrayList<>();
    public List<String> titles=new ArrayList<>();
    public BasePagerAdapter fragmentAdapter;

    public abstract RecyclerTabLayout setTabLayout();
    public abstract ViewPager setViewPager();
    public abstract List<Fragment> setFragments();
    public abstract List<String> setTitles();
    public abstract RecyclerTabLayout.OnPageSelectedListener setOnPageSelectedListener();
    /**
     * 直接在需要初始化的地方调用此方法即可
     */
    public void initPageItem(){
        recyclerTabLayout=setTabLayout();
        viewpager=setViewPager();
        fgs=setFragments();
        titles=setTitles();
        fragmentAdapter=new BasePagerAdapter(getSupportFragmentManager(), fgs,titles);
        viewpager.setOffscreenPageLimit(titles.size());
        viewpager.setAdapter(fragmentAdapter);
        recyclerTabLayout.setUpWithViewPager(viewpager);
        recyclerTabLayout.setOnPageSelectedListener(setOnPageSelectedListener());
    }

}
