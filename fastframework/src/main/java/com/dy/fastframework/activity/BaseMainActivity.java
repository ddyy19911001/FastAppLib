package com.dy.fastframework.activity;

import android.view.ViewGroup;

import com.dy.fastframework.R;

import java.util.ArrayList;
import java.util.List;

import yin.deng.superbase.activity.SuperBaseActivity;
import yin.deng.superbase.fragment.ViewPagerSuperBaseFragment;

public abstract class BaseMainActivity extends SuperBaseActivity {
    private String[] mTabText;
    //未选中icon
    private int[] mNormalIcons;
    //选中时icon
    private int[] mSelectIcons;

    private List<ViewPagerSuperBaseFragment> mFragmentLists = new ArrayList<>();
    private ViewGroup mNavigationBar;

    //这个是装底部按钮的容器
    public abstract ViewGroup getNavigationBar();

    public abstract int[] getNormalIcons();
    public abstract int[] getSelectIcons();
    public abstract String[] getTabTexts();
    public abstract List<ViewPagerSuperBaseFragment> getFragments();

    public void initBottomTab(){
        mNavigationBar=getNavigationBar();
        mNormalIcons=getNormalIcons();
        mSelectIcons=getSelectIcons();
        mTabText=getTabTexts();
        mFragmentLists=getFragments();

    }
}
