package com.dy.fastframework.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dy.fastframework.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import yin.deng.superbase.activity.SuperBaseActivity;
import yin.deng.superbase.fragment.ViewPagerSuperBaseFragment;
import yin.deng.tablibrary.TabUtils;

public abstract class BaseTabViewFragment extends ViewPagerSuperBaseFragment {
    public TabUtils utils;
    public Class fragmentClass;//? extends fragment(in androidx)
    private int normalColor;
    private int selectedColor;
    private String[] names;
    private ViewPager tabViewPager;
    private SmartTabLayout hmTabLayout;
    public int tabSize=18;
    private OnPageSelectedListener onPageSelectedListener;
    private List<Bundle> bundles=new ArrayList<>();
    public TabUtils tabUtils;
    public Fragment currentShowFragment;
    public int currentSelectedPosition;

    public interface OnPageSelectedListener{
        void onPageSelected(int position);
    }


    /**
     * 请调用TableBuilder设置tab参数进行构造
     */
    public abstract TableBuilder builderTable();

    /**
     * 直接创建tableLayout
     */
    public void initTableLayout(){
        TableBuilder builder = builderTable();
        if(builder!=null) {
            tabUtils =builder.createTable(this);
        }
    }

    /**
     * 构造着模式创建TabLayout
     */
    public static class TableBuilder{
        private static TableBuilder builder;
        private List<Bundle> bundles=new ArrayList<>();
        private Class fragmentClass;//? extends fragment(in androidx)
        private int normalColor;
        private int selectedColor;
        private String[] names;
        private ViewPager viewPager;
        private SmartTabLayout hmTabLayout;
        private int tabSize=16;
        private OnPageSelectedListener onPageSelectedListener;
        private TableBuilder() {
        }

        public static TableBuilder getInstance(){
            builder=new TableBuilder();
            return builder;
        }

        public TableBuilder getDefaultBuilder(Activity activity,String[]names,Class fragmentClass,SmartTabLayout tabLayout,ViewPager viewPager){
            normalColor=activity.getResources().getColor(R.color.normal_gray);
            selectedColor=activity.getResources().getColor(R.color.select_color);
            this.names=names;
            this.fragmentClass=fragmentClass;
            this.viewPager=viewPager;
            this.hmTabLayout=tabLayout;
            return builder;
        }


        /**
         * 最后创建tabLayout并返回TableUtils
         * @param activity
         * @return
         */
        private TabUtils createTable(BaseTabViewFragment activity){
            return activity.initTabParams(
                    builder.bundles,
                    builder.fragmentClass,
                    builder.normalColor,
                    builder.selectedColor,
                    builder.names,
                    builder.viewPager,
                    builder.hmTabLayout,
                    builder.tabSize,
                    builder.onPageSelectedListener);
        }

        public TableBuilder bundles(List<Bundle> bundles) {
            this.bundles = bundles;
            return builder;
        }

        public TableBuilder onPageSelectedListener(OnPageSelectedListener onPageSelectedListener) {
            this.onPageSelectedListener = onPageSelectedListener;
            return builder;
        }

        public TableBuilder fragmentClass(Class fragmentClass) {
            this.fragmentClass = fragmentClass;
            return builder;
        }


        public TableBuilder normalColor(int normalColor) {
            this.normalColor = normalColor;
            return builder;
        }


        public TableBuilder selectedColor(int selectedColor) {
            this.selectedColor = selectedColor;
            return builder;
        }


        public TableBuilder tableNames(String[] names) {
            this.names = names;
            return builder;
        }


        public TableBuilder viewPager(ViewPager viewPager) {
            this.viewPager = viewPager;
            return builder;
        }


        public TableBuilder tabLayout(SmartTabLayout hmTabLayout) {
            this.hmTabLayout = hmTabLayout;
            return builder;
        }


        public TableBuilder tabSize(int tabSize) {
            this.tabSize = tabSize;
            return builder;
        }
    }

    public TabUtils initTabParams(List<Bundle> bundles,Class fragmentClass, int normalColor, int selectedColor, String[] tableNames, ViewPager viewPager, SmartTabLayout tabLayout, int tableTextSize,OnPageSelectedListener onPageSelectedListener){
        this.bundles=bundles;
        this.fragmentClass=fragmentClass;
        this.normalColor=normalColor;
        this.selectedColor=selectedColor;
        this.names=tableNames;
        this.tabViewPager =viewPager;
        this.hmTabLayout=tabLayout;
        this.tabSize=tableTextSize;
        this.onPageSelectedListener=onPageSelectedListener;
        return makeTableLayout();
    }

    public TabUtils makeTableLayout() {
        if(normalColor!=0&&selectedColor!=0) {
            utils = new TabUtils(fragmentClass, normalColor, selectedColor);
        }else{
            utils=new TabUtils(fragmentClass, getResources().getColor(R.color.normal_gray), getResources().getColor(R.color.select_color));
        }
        if(bundles.size()>0&&bundles.size()==names.length) {
            utils.initPageFg(bundles, hmTabLayout, tabViewPager, getActivity(), names, getFragmentManager(), R.layout.hm_custom_tab_text, R.id.tv_tab);
        }else{
            utils.initPageFg(hmTabLayout, tabViewPager, getActivity(), names, getFragmentManager(), R.layout.hm_custom_tab_text, R.id.tv_tab);
        }
        for(int i=0;i<utils.tvs.size();i++) {
            utils.tvs.get(i).setTextSize(TypedValue.COMPLEX_UNIT_SP, tabSize);
        }
        if(selectedColor==0) {
            utils.tvs.get(0).setTextColor(getResources().getColor(R.color.select_color));
        }else{
            utils.tvs.get(0).setTextColor(selectedColor);
        }
        hmTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<utils.tvs.size();i++){
                    if(normalColor==0||selectedColor==0) {
                        utils.tvs.get(i).setTextColor(getResources().getColor(R.color.normal_gray));
                    }else{
                        utils.tvs.get(i).setTextColor(normalColor);
                    }
                }
                if(normalColor==0||selectedColor==0) {
                    utils.tvs.get(position).setTextColor(getResources().getColor(R.color.select_color));
                }else{
                    utils.tvs.get(position).setTextColor(selectedColor);
                }
                if(onPageSelectedListener!=null){
                    onPageSelectedListener.onPageSelected(position);
                }
                currentShowFragment=utils.pageAdapter.getPage(position);
                currentSelectedPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return utils;
    }



}
