package com.dy.fastdemo;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dy.fastframework.activity.BaseMainActivity;
import com.dy.fastframework.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import yin.deng.normalutils.utils.EventHolder;
import yin.deng.normalutils.utils.LogUtils;

public class TestMainAc extends BaseMainActivity {
    private ViewPager vp;
    private LinearLayout llNavigation;
    @Override
    public int setLayout() {
        return R.layout.test_main_ac;
    }

    @Override
    public void bindViewWithId() {
        vp = (ViewPager) findViewById(R.id.vp);
        llNavigation = (LinearLayout) findViewById(R.id.ll_navigation);

    }

    @Override
    public LinearLayout getNavigationBar() {
        return llNavigation;
    }

    @Override
    public int[] getNormalIcons() {
        return new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    }

    @Override
    public int[] getSelectIcons() {
        return new int[]{R.mipmap.icon,R.mipmap.icon,R.mipmap.icon};
    }

    @Override
    public int getNormalTextColor() {
        return 0;
    }

    @Override
    public int getSelectedTextColor() {
        return 0;
    }

    @Override
    public String[] getTabTexts() {
        return new String[]{"首页","发现","我的"};
    }

    @Override
    public List<Fragment> getFragments() {
        MyTestFragment fragment1=new MyTestFragment();
        MyTestFragment fragment2=new MyTestFragment();
        MyTestFragment fragment3=new MyTestFragment();
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        return fragments;
    }

    @Override
    public int getDefaultSelectedIconIndex() {
        return 0;
    }

    @Override
    public ViewPager getViewPager() {
        return vp;
    }

    @Override
    public NoScrollViewPager getNoScrollViewPager() {
        return null;
    }



    @Override
    public ConfigBuild getImageBuilder() {
        return  ConfigBuild.getInstance()
                .setWidthInDp(25f)
                .setGravityInDp(Gravity.CENTER)
                .setHeightInDp(25f)
                .setMarginLeftInDp(10f)
                .setMarginRightInDp(10f)
                .setMarginBottomInDp(2f)
                .setMarginTopInDp(5f);
    }

    @Override
    public ConfigBuild getTabTextBuilder() {
        return  ConfigBuild.getInstance()
                .setWidthInDp(LinearLayout.LayoutParams.WRAP_CONTENT)
                .setGravityInDp(Gravity.CENTER)
                .setHeightInDp(LinearLayout.LayoutParams.WRAP_CONTENT)
                .setMarginLeftInDp(0)
                .setMarginRightInDp(0);
    }

    @Override
    public void initFirst() {
        initBottomTab();
        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.w("发送消息了");
                EventHolder.getInstance().post(1, "我是要发送的内容");
            }
        });
        EventHolder.getInstance().post(1, "我是要发送的内容");
    }
}
