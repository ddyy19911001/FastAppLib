package com.dy.fastframework.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dy.fastframework.R;
import com.dy.fastframework.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import yin.deng.normalutils.utils.ImageLoadUtil;
import yin.deng.normalutils.utils.LogUtils;
import yin.deng.superbase.activity.ScreenUtils;
import yin.deng.superbase.activity.SuperBaseActivity;
import yin.deng.superbase.fragment.BasePagerAdapter;

/**
 * 专门针对底部有多个按钮的界面设计快速布局的一个activity
 * 在需要开始执行初始化的时候调用initBottomTab();开始初始化整个界面
 */
public abstract class BaseMainActivity extends SuperBaseActivity {
    private String[] mTabText;
    //未选中icon
    private int[] mNormalIcons;
    //选中时icon
    private int[] mSelectIcons;
    //未选中icon
    private String[] mNormalIconUrls;
    //选中时icon
    private String[] mSelectIconUrls;
    private List<Fragment> mFragmentLists = new ArrayList<>();
    private LinearLayout mNavigationBar;
    public List<ViewHolder> bottomTabHolders=new ArrayList<>();
    public ViewPager mViewPager;
    private OnPageSeletctedListener onPageSeletctedListener;
    private int nowSelectedIndex=0;

    @Override
    public abstract int setLayout();
    //这个是装底部按钮的容器
    public abstract LinearLayout getNavigationBar();
    public abstract int[] getNormalIcons();
    //支持网络请求回来的图片设置
    public String[] getNormalIconsForHttpUrl(){return null;}
    public abstract int[] getSelectIcons();
    //支持网络请求回来的图片设置
    public String[] getSelectIconsForHttpUrl(){return null;}
    public abstract int getNormalTextColor();
    public abstract int getSelectedTextColor();
    public abstract String[] getTabTexts();
    public abstract List<Fragment> getFragments();
    public abstract int getDefaultSelectedIconIndex();
    public abstract ViewPager getViewPager();
    public abstract ConfigBuild getImageBuilder();
    public abstract ConfigBuild getTabTextBuilder();
    public int normalTextColor;
    public int selectedTextColor;
    //如果重写根布局不使用默认则需要重写此方法
    public  ConfigBuild getRootItemViewBuilder(){
        return null;
    }
    //根布局是否使用默认长宽和margin，如果返回true则重写getRootItemViewBuilder()方法
    public boolean isRootViewLayoutParamsUseDefault(){
        return true;
    }
    //设置包含图标和文字的根布局的布局参数
    public  void onBuilderRootViewLayoutParams(LinearLayout.LayoutParams ivLayoutParams){
        if(!isRootViewLayoutParamsUseDefault()&&getRootItemViewBuilder()!=null){
            ivLayoutParams.width = ScreenUtils.dipTopx(this, getRootItemViewBuilder().widthInDp);
            ivLayoutParams.height = ScreenUtils.dipTopx(this, getRootItemViewBuilder().heightInDp);
            ivLayoutParams.topMargin = ScreenUtils.dipTopx(this, getRootItemViewBuilder().marginTopInDp);
            ivLayoutParams.bottomMargin = ScreenUtils.dipTopx(this, getRootItemViewBuilder().marginBottomInDp);
            ivLayoutParams.leftMargin = ScreenUtils.dipTopx(this, getRootItemViewBuilder().marginLeftInDp);
            ivLayoutParams.rightMargin = ScreenUtils.dipTopx(this, getRootItemViewBuilder().marginRightInDp);
            ivLayoutParams.gravity = getRootItemViewBuilder().gravity;
            ivLayoutParams.weight = getRootItemViewBuilder().weightInDp;
        }
    }

    /**
     * 设置文字的宽度和margin
     * @param ivLayoutParams
     */
    public  void onBuilderTabNameLayoutParams(LinearLayout.LayoutParams ivLayoutParams){
        ivLayoutParams.width= ScreenUtils.dipTopx(this,getTabTextBuilder().widthInDp);
        ivLayoutParams.height=ScreenUtils.dipTopx(this,getTabTextBuilder().heightInDp);
        ivLayoutParams.topMargin=ScreenUtils.dipTopx(this,getTabTextBuilder().marginTopInDp);
        ivLayoutParams.bottomMargin=ScreenUtils.dipTopx(this,getTabTextBuilder().marginBottomInDp);
        ivLayoutParams.leftMargin=ScreenUtils.dipTopx(this,getTabTextBuilder().marginLeftInDp);
        ivLayoutParams.rightMargin=ScreenUtils.dipTopx(this,getTabTextBuilder().marginRightInDp);
        ivLayoutParams.gravity=getTabTextBuilder().gravity;
    }

    /**
     * 设置图片的宽度和margin
     * @param ivLayoutParams
     */
    public void onBuilderImageViewLayoutParams(LinearLayout.LayoutParams ivLayoutParams){
        ivLayoutParams.width= ScreenUtils.dipTopx(this,getImageBuilder().widthInDp);
        ivLayoutParams.height=ScreenUtils.dipTopx(this,getImageBuilder().heightInDp);
        ivLayoutParams.topMargin=ScreenUtils.dipTopx(this,getImageBuilder().marginTopInDp);
        ivLayoutParams.bottomMargin=ScreenUtils.dipTopx(this,getImageBuilder().marginBottomInDp);
        ivLayoutParams.leftMargin=ScreenUtils.dipTopx(this,getImageBuilder().marginLeftInDp);
        ivLayoutParams.rightMargin=ScreenUtils.dipTopx(this,getImageBuilder().marginRightInDp);
        ivLayoutParams.gravity=getImageBuilder().gravity;
    }



    @Override
    public void bindViewWithId() {

    }
    @Override
    public abstract void initFirst();
    public void initBottomTab(){
        mNavigationBar=getNavigationBar();
        mNormalIcons=getNormalIcons();
        mSelectIcons=getSelectIcons();
        mNormalIconUrls=getNormalIconsForHttpUrl();
        mSelectIconUrls=getSelectIconsForHttpUrl();
        mTabText=getTabTexts();
        mFragmentLists=getFragments();
        if(getNoScrollViewPager()==null) {
            mViewPager = getViewPager();
        }else{
            mViewPager= getNoScrollViewPager();
        }
        normalTextColor=getNormalTextColor();
        selectedTextColor=getSelectedTextColor();
        initTabs();
    }



    /**
     * 如果不需要滑动，则重写此方法
     * @return
     */
    public NoScrollViewPager getNoScrollViewPager(){
        return null;
    }


    private void initTabs() {
        if(mSelectIconUrls!=null&&mNormalIconUrls!=null){
            if (mNavigationBar != null &&
                    mTabText != null &&
                    mTabText.length > 0 &&
                    mNormalIconUrls.length > 0 &&
                    mNormalIconUrls.length == mTabText.length &&
                    mSelectIconUrls != null &&
                    mSelectIconUrls.length > 0 &&
                    mSelectIconUrls.length == mNormalIconUrls.length &&
                    mFragmentLists != null && mFragmentLists.size() > 0 &&
                    mFragmentLists.size() == mTabText.length) {
                bottomTabHolders.clear();
                mNavigationBar.removeAllViews();
                //符合要求
                for (int i = 0; i < mTabText.length; i++) {
                    View itemView = View.inflate(this, R.layout.default_navigation_item, null);
                    ViewHolder holder = new ViewHolder(itemView);
                    bottomTabHolders.add(holder);
                    initHolder(holder, i);
                    mNavigationBar.addView(itemView);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) itemView.getLayoutParams();
                    params.weight = 1;
                    onBuilderRootViewLayoutParams(params);
                    itemView.setLayoutParams(params);
                }
                BasePagerAdapter pagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), mFragmentLists);
                mViewPager.setOffscreenPageLimit(mTabText.length / 2 + 1);
                mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        showUiWhenPageSelected(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                mViewPager.setAdapter(pagerAdapter);
                setSelectPage(getDefaultSelectedIconIndex());
            } else {
                //不符合要求
                LogUtils.e("参数未设置或不符合要求，无法构建");
            }
        }else {
            if (mNavigationBar != null &&
                    mTabText != null &&
                    mTabText.length > 0 &&
                    mNormalIcons != null &&
                    mNormalIcons.length > 0 &&
                    mNormalIcons.length == mTabText.length &&
                    mSelectIcons != null &&
                    mSelectIcons.length > 0 &&
                    mSelectIcons.length == mNormalIcons.length &&
                    mFragmentLists != null && mFragmentLists.size() > 0 &&
                    mFragmentLists.size() == mTabText.length) {
                bottomTabHolders.clear();
                mNavigationBar.removeAllViews();
                //符合要求
                for (int i = 0; i < mTabText.length; i++) {
                    View itemView = View.inflate(this, R.layout.default_navigation_item, null);
                    ViewHolder holder = new ViewHolder(itemView);
                    bottomTabHolders.add(holder);
                    initHolder(holder, i);
                    mNavigationBar.addView(itemView);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) itemView.getLayoutParams();
                    params.weight = 1;
                    onBuilderRootViewLayoutParams(params);
                    itemView.setLayoutParams(params);
                }
                BasePagerAdapter pagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), mFragmentLists);
                mViewPager.setOffscreenPageLimit(mTabText.length / 2 + 1);
                mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        showUiWhenPageSelected(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                mViewPager.setAdapter(pagerAdapter);
                setSelectPage(getDefaultSelectedIconIndex());
            } else {
                //不符合要求
                LogUtils.e("参数未设置或不符合要求，无法构建");
            }
        }
    }


    public static class ConfigBuild{
        private int gravity;
        private float widthInDp;
        private float heightInDp;
        private float marginLeftInDp;
        private float marginRightInDp;
        private float marginTopInDp;
        private float marginBottomInDp;
        private float marginInDp;
        private int weightInDp;
        private static ConfigBuild configBuild;
        private ConfigBuild(){}
        public static ConfigBuild getInstance(){
            configBuild=new ConfigBuild();
            return configBuild;
        }

        public ConfigBuild setWidthInDp(float params){
            this.widthInDp=params;
            return configBuild;
        }
        public ConfigBuild setHeightInDp(float params){
            this.heightInDp=params;
            return configBuild;
        }

        public ConfigBuild setMarginLeftInDp(float params){
            this.marginLeftInDp=params;
            return configBuild;
        }

        public ConfigBuild setMarginRightInDp(float params){
            this.marginRightInDp=params;
            return configBuild;
        }
        public ConfigBuild setMarginTopInDp(float params){
            this.marginTopInDp=params;
            return configBuild;
        }
        public ConfigBuild setMarginBottomInDp(float params){
            this.marginBottomInDp=params;
            return configBuild;
        }
        public ConfigBuild setMarginInDp(float params){
            this.marginInDp=params;
            return configBuild;
        }

        public ConfigBuild setGravityInDp(int gravity){
            this.gravity=gravity;
            return configBuild;
        }

        public ConfigBuild setWeight(int params){
            this.weightInDp=params;
            return configBuild;
        }

    }




    public void showUiWhenPageSelected(int position) {
        bottomTabHolders.get(position).ivIcon.setImageResource(mSelectIcons[position]);
        bottomTabHolders.get(position).tvTabName.setText(mTabText[position]);
        setSelectPage(position);
        if(onPageSeletctedListener!=null){
            onPageSeletctedListener.onPageSeleceted(position);
        }
    }


    public interface OnPageSeletctedListener{
        void onPageSeleceted(int position);
    }

    public void setOnPageSeletctedListener(OnPageSeletctedListener onPageSeletctedListener) {
        this.onPageSeletctedListener = onPageSeletctedListener;
    }

    /**
     * 此处用于设置图片和文字
     * @param holder
     * @param i
     */
    public void initHolder(ViewHolder holder, final int i) {
        if(getNormalIconsForHttpUrl()!=null){
            ImageLoadUtil.loadImage(holder.ivIcon, getNormalIconsForHttpUrl()[i]);
        }else {
            holder.ivIcon.setImageResource(mNormalIcons[i]);
        }
        if(normalTextColor!=0) {
            holder.tvTabName.setTextColor(normalTextColor);
        }
        LinearLayout.LayoutParams ivLayoutParams= (LinearLayout.LayoutParams) holder.ivIcon.getLayoutParams();
        onBuilderImageViewLayoutParams(ivLayoutParams);
        LinearLayout.LayoutParams tvLayoutParams= (LinearLayout.LayoutParams) holder.tvTabName.getLayoutParams();
        holder.tvTabName.setText(mTabText[i]);
        onBuilderTabNameLayoutParams(tvLayoutParams);
        holder.ivIcon.setLayoutParams(ivLayoutParams);
        holder.tvTabName.setLayoutParams(tvLayoutParams);
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nowSelectedIndex!=i){
                    setSelectPage(i);
                }
            }
        });
    }


    /**
     * 获取当前选中的item的index
     * @return
     */
    public int getNowSelectPageIndex() {
        return nowSelectedIndex;
    }




    /**
     * 设置选中的页面
     * @param i
     */
    public void setSelectPage(int i) {
        clearAllImageAndTextColor();
        nowSelectedIndex=i;
        mViewPager.setCurrentItem(i);
        if(mSelectIconUrls!=null){
            ImageLoadUtil.loadImage(bottomTabHolders.get(i).ivIcon,mSelectIconUrls[i]);
            if (selectedTextColor != 0) {
                bottomTabHolders.get(i).tvTabName.setTextColor(selectedTextColor);
            }
        }else {
            bottomTabHolders.get(i).ivIcon.setImageResource(mSelectIcons[i]);
            if (selectedTextColor != 0) {
                bottomTabHolders.get(i).tvTabName.setTextColor(selectedTextColor);
            }
        }
    }

    /**
     * 清空所有选中效果
     */
    public void clearAllImageAndTextColor() {
        for(int i=0;i<bottomTabHolders.size();i++){
            if(normalTextColor!=0) {
                bottomTabHolders.get(i).tvTabName.setTextColor(normalTextColor);
            }
            if(getNormalIconsForHttpUrl()!=null){
                ImageLoadUtil.loadImage(bottomTabHolders.get(i).ivIcon, getNormalIconsForHttpUrl()[i]);
            }else {
                bottomTabHolders.get(i).ivIcon.setImageResource(getNormalIcons()[i]);
            }
        }
    }

    public class ViewHolder{
        public LinearLayout llItem;
        public ImageView ivIcon;
        public TextView tvTabName;
        public ViewHolder(View view) {
            ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            tvTabName = (TextView) view.findViewById(R.id.tv_tab_name);
            llItem = (LinearLayout) view.findViewById(R.id.ll_item);
        }
    }
}
