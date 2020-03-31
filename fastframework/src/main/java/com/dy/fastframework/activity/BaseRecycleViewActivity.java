package com.dy.fastframework.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.fastframework.util.ActivityLoadUtil;
import com.dy.fastframework.util.ImageAutoLoadScrollListener;

import java.util.ArrayList;
import java.util.List;

import me.bakumon.statuslayoutmanager.library.OnStatusChildClickListener;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;
import yin.deng.dyrequestutils.http.LogUtils;
import yin.deng.dyrequestutils.http.MyHttpUtils;
import yin.deng.dyrequestutils.okhttplib.HttpInfo;
import yin.deng.refreshlibrary.refresh.SmartRefreshLayout;
import yin.deng.refreshlibrary.refresh.api.RefreshLayout;
import yin.deng.refreshlibrary.refresh.help.MyQuckAdapter;
import yin.deng.refreshlibrary.refresh.listener.OnLoadmoreListener;
import yin.deng.refreshlibrary.refresh.listener.OnRefreshListener;
import yin.deng.superbase.activity.SuperBaseActivity;

/**
 * recycleView界面的快速开发
 * @param <T> 列表数据（是list里面的数据）
 * @param <V> 服务器返回数据（服务器返回的包含list的整体类）
 * 注意：init里面要初始化initRecycle()/initAdapter()-----最后在需要的地方调用loadDataAtFirst()请求数据
 */
public abstract class BaseRecycleViewActivity<T,V> extends SuperBaseActivity implements
        OnRefreshListener, OnLoadmoreListener, MyHttpUtils.OnGetInfoListener, OnStatusChildClickListener {
    public List<T> mDatas=new ArrayList<>();
    public MyQuckAdapter<T> mAdapter;
    public RecyclerView mRecycleView;
    public boolean FRefresh=true;
    public int adapterLayout;
    //分页加载时使用
    public int page;
    //分页的默认页数
    public int defaultPage=0;
    public OnConvertDataLayoutListener onConvertDataLayoutListener;
    public SmartRefreshLayout refreshLayout;
    public View headView;
    public View footView;
    public StatusLayoutManager statusLayoutManager;
    public int finishLoadMoreDelay=100;
    public int finishRefreshDelay=250;
    public int finishEdLoadMoreShowDelay=0;

    /**
     * 使用方法：1.initRecycle  2.initAdapter(是否设置default page)  3.sendMsgToGetData  4.onAdapterSetOver
     * @param rc
     * @param smartRefreshLayout
     */
    public void initVerticalRecycleView(RecyclerView rc, SmartRefreshLayout smartRefreshLayout){
        mRecycleView=rc;
        this.refreshLayout=smartRefreshLayout;
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        setRefreshLoadMoreListener();
    }

    @Override
    public float getSizeInDp() {
        return 400;
    }

    public void initVerticalRecycleView(RecyclerView rc, int spanCount, SmartRefreshLayout smartRefreshLayout){
        mRecycleView=rc;
        this.refreshLayout=smartRefreshLayout;
        GridLayoutManager layoutManager=new GridLayoutManager(this, spanCount);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        setRefreshLoadMoreListener();
    }

    public void initHorizantalRecycleView(RecyclerView rc,SmartRefreshLayout smartRefreshLayout){
        mRecycleView=rc;
        this.refreshLayout=smartRefreshLayout;
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRecycleView.setLayoutManager(layoutManager);
        setRefreshLoadMoreListener();
    }

    public void initHorizantalRecycleView(RecyclerView rc,int spanCount,SmartRefreshLayout smartRefreshLayout){
        mRecycleView=rc;
        this.refreshLayout=smartRefreshLayout;
        GridLayoutManager layoutManager=new GridLayoutManager(this, spanCount);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRecycleView.setLayoutManager(layoutManager);
        setRefreshLoadMoreListener();
    }


    public void initAdapter(int adapterLayout, OnConvertDataLayoutListener listener){
        this.adapterLayout=adapterLayout;
        this.onConvertDataLayoutListener=listener;
    }


    @Override
    public abstract int setLayout();

    @Override
    public abstract void bindViewWithId();

    @Override
    public abstract void initFirst();


    /**
     * LinerLayout
     * @param rc
     * @param smartRefreshLayout
     */
    public void autoInitRecylceAndAdapter(RecyclerView rc, SmartRefreshLayout smartRefreshLayout,int adapterLayout,OnConvertDataLayoutListener listener){
        initVerticalRecycleView(rc, smartRefreshLayout);
        initAdapter(adapterLayout,listener);
    }


    /**
     * GridLayout
     * @param rc
     * @param smartRefreshLayout
     * @param clum
     */
    public void autoInitRecylceAndAdapter(RecyclerView rc,int clum, SmartRefreshLayout smartRefreshLayout,int adapterLayout,OnConvertDataLayoutListener listener){
        initVerticalRecycleView(rc,clum, smartRefreshLayout);
        initAdapter(adapterLayout,listener);
    }

    /**
     * 单次请求的方法
     * 远程数据：
     * 只需请求服务器获取数据，使用BasePresenter.getInstance().get()/post()
     * 本地数据：
     * 只需调用showLocalDataList()传入要加载的list集合即可。
     */
    public abstract void sendMsgToGetData();

    /**
     * 如果只需要显示本地数据，则在sendMsgToGetData方法中调用showLocalDataList即可
     */
    public void showLocalDataList(List<T> localDatas){
        onDataGeted(localDatas, FRefresh);
    }

    /**
     * 将获取到的数据转为list集合
     * @param requestUrl
     * @param info
     * @return
     */
    protected abstract List<T> convertInfoDataToList(V requestUrl, HttpInfo info);

    /**
     * 数据请求失败
     * @param requestUrl
     * @param info
     */
    protected abstract void onDataGetFialed(String requestUrl, HttpInfo info);



    /**
     * 设置默认分页
     *
     *
     * @param page
     */
    public void setDefaultPage(int page){
        this.defaultPage=page;
        this.page=defaultPage;
    }


    /**
     * 设置刷新或加载更多的监听
     */
    public void setRefreshLoadMoreListener() {
        if(refreshLayout!=null){
            refreshLayout.setOnRefreshListener(this);
            refreshLayout.setOnLoadmoreListener(this);
            setLoadingLayout(refreshLayout);
        }
        onInitOver();
        if(isNeedNotLoadImgOnScroll()){
            mRecycleView.setOnScrollListener(new ImageAutoLoadScrollListener(this));
        }
    }


    /**
     * 是否在滑动过程中不加载图片
     * @return
     */
    public boolean isNeedNotLoadImgOnScroll(){
        return true;
    }


    public void onInitOver(){

    }

    /**
     * 是否需要显示加载中，空布局和加载出错等布局
     * @return
     */
    public boolean needShowLoadingLayout(){
        return true;
    }

    /**
     * 是否需要显示加载中，空布局和加载出错等布局
     * @return
     */
    public boolean needShowEmptyLayout(){
        return true;
    }

    /**
     * 可以重写更改要替换的view
     * @param view
     */
    public void setLoadingLayout(View view) {
        statusLayoutManager=ActivityLoadUtil.getInstance().useDefaultLoadLayout(view,this);
        if(needShowLoadingLayout()){
            statusLayoutManager.showLoadingLayout();
        }
    }


    /**
     * 首次加载数据时调用此方法
     */
    public void loadDataAtFirst(){
        mDatas.clear();
        page=defaultPage;
        FRefresh=true;
        //当出现刷新转圈的时候回调
        LogUtils.i("刷新数据");
        sendMsgToGetData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page=defaultPage;
        FRefresh=true;
        //当出现刷新转圈的时候回调
        LogUtils.i("刷新数据");
        sendMsgToGetData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        FRefresh=false;
        //当出现加载更多的时候回调
        LogUtils.i("加载更多数据");
        sendMsgToGetData();
    }



    /**
     * 设置头部
     * @param headView
     */
    public void addHeadView(View headView){
        this.headView=headView;
    }


    /**
     * 设置脚部
     * @param footView
     */
    public void addFootView(View footView){
        this.footView=footView;
    }


    @Override
    public void onInfoGet(Object info, HttpInfo httpInfo) {
        V obj= (V) info;
        List<T> listData = convertInfoDataToList(obj, httpInfo);
        onDataGeted(listData, FRefresh);
    }

    /**
     * 使用BasePresenter调用get或post之后，数据会回调在这里
     * @param requestUrl
     * @param info
     */





    @Override
    public  void onFailed(String requestUrl, HttpInfo info){
        onDataGetFialed(requestUrl,info);
    }



    /**
     * 在刷新或是加载更多时将获取到的新数据传入
     * 仅仅时处理数据内容，后面自行调用setAdapter()
     */
    public void onDataGeted(List<T> list,boolean FRefresh){
        if(list==null){
            list=new ArrayList<T>();
        }
        if(FRefresh){
            //刷新完成
            if(refreshLayout!=null){
                refreshLayout.finishRefresh(finishRefreshDelay,true);
            }
            mDatas.clear();
        }else{
            if(list.size()==0){
                refreshLayout.setLoadmoreFinished(true);
                refreshLayout.finishLoadmore(finishEdLoadMoreShowDelay,true);
                page--;
                if(page<=defaultPage){
                    page=defaultPage;
                }
            }else {
                //加载更多完成
                if (refreshLayout != null) {
                    refreshLayout.finishLoadmore(finishLoadMoreDelay, true);
                }
            }
        }
        mDatas.addAll(list);
        if(needShowEmptyLayout()&&mDatas.size()==0){
            statusLayoutManager.showEmptyLayout();
            LogUtils.i("显示空布局");
        }else{
            if(needShowLoadingLayout()||needShowEmptyLayout()){
                //数据加载完成后要关闭加载中的布局
                statusLayoutManager.showSuccessLayout();
                LogUtils.i("显示正常布局");
            }
        }
        setAdapter();
    }

    public void setAdapter(){
        if(mRecycleView==null){
            throw new IllegalArgumentException("请先传入recycleView（执行initRecycleview）");
        }
        if(adapterLayout==0){
            throw new IllegalArgumentException("请先传入adapter的布局（执行initAdapter方法）");
        }
        if(mAdapter==null){
            mAdapter=new MyQuckAdapter<T>(adapterLayout,mDatas,this) {
                @Override
                protected void convert(BaseViewHolder helper, T item) {
                    if(onConvertDataLayoutListener!=null){
                        onConvertDataLayoutListener.onConvert(helper, item);
                    }
                }
            };
            if(headView!=null){
                mAdapter.addHeaderView(headView);
            }
            if(footView!=null){
                mAdapter.addFooterView(footView);
            }
            mRecycleView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
        onAdapterSetOver();
    }


    /**
     * 在adapter设置完成或notify完成后开始调用
     */
    public void onAdapterSetOver(){

    }

    public interface OnConvertDataLayoutListener<T>{
        void onConvert(BaseViewHolder holder, T item);
    }




    /**
     * 空数据布局子 View 被点击
     *
     * @param view 被点击的 View
     */
    @Override
    public void onEmptyChildClick(View view){
        loadDataAtFirst();
    }

    /**
     * 出错布局子 View 被点击
     *
     * @param view 被点击的 View
     */
    @Override
    public void onErrorChildClick(View view){
        loadDataAtFirst();
    }

    /**
     * 自定义状态布局布局子 View 被点击
     *
     * @param view 被点击的 View
     */
    @Override
    public void onCustomerChildClick(View view){
        loadDataAtFirst();
    }
}
