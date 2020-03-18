package com.dy.fastdemo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.dy.fastframework.activity.BaseActivity;
import com.dy.fastframework.activity.BaseTabViewActivity;
import com.dy.fastframework.tablayout.RecyclerTabLayout;

import java.util.ArrayList;
import java.util.List;

import yin.deng.superbase.fragment.BasePagerAdapter;

public class TabTestActivity extends BaseTabViewActivity {
    private RecyclerTabLayout recyclerTabLayout;
    private ViewPager viewpager;
    private MyTestFragment ys1;
    private MyTestFragment ys2;

    @Override
    public int setLayout() {
        return  R.layout.activity_ys;
    }

    @Override
    public void bindViewWithId() {
        recyclerTabLayout = (RecyclerTabLayout) findViewById(R.id.recycler_tab_layout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

    }

    @Override
    public void initFirst() {
        initPageItem();
    }

    @Override
    public boolean setIsExitActivity() {
        return true;
    }

    @Override
    public RecyclerTabLayout setTabLayout() {
        return recyclerTabLayout;
    }

    @Override
    public ViewPager setViewPager() {
        return viewpager;
    }

    @Override
    public List<Fragment> setFragments() {
        ys1=new MyTestFragment();
        ys2=new MyTestFragment();
        MyTestFragment ys3 = new MyTestFragment();
        MyTestFragment ys4 = new MyTestFragment();
        MyTestFragment ys5 = new MyTestFragment();
        MyTestFragment ys6 = new MyTestFragment();
        Bundle bundle1=new Bundle();
        bundle1.putInt("position", 0);
        ys1.setArguments(bundle1);
        Bundle bundle2=new Bundle();
        bundle2.putInt("position", 1);
        ys2.setArguments(bundle2);
        fgs.add(ys1);
        fgs.add(ys2);
        fgs.add(ys3);
        fgs.add(ys4);
        fgs.add(ys5);
        fgs.add(ys6);
        return fgs;
    }

    @Override
    public List<String> setTitles() {
        ArrayList<String> titles = new ArrayList<String>();
        titles.add("政策1");
        titles.add("政策2");
        titles.add("政策3");
        titles.add("政策4");
        titles.add("政策5");
        titles.add("政策6");
        return titles;
    }

    @Override
    public RecyclerTabLayout.OnPageSelectedListener setOnPageSelectedListener() {
        return null;
    }


}
