package com.wen_wen.latte.app.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.ui.recycler.DataConveter;
import com.wen_wen.latte.app.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by WeLot on 2018/4/19.
 */

public class RefreshHanlder implements SwipeRefreshLayout.OnRefreshListener ,BaseQuickAdapter.RequestLoadMoreListener{
    private final SwipeRefreshLayout REFRESH_LAYOUT;

    //做刷新准备
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConveter COVERTER;


    public RefreshHanlder(SwipeRefreshLayout refreshLayout,
                          RecyclerView recyclerView, DataConveter conveter, PagingBean
                                  pagingBean) {
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
        this.RECYCLERVIEW = recyclerView;
        this.COVERTER = conveter;
        this.BEAN = pagingBean;
    }

    //简单工厂
    public static RefreshHanlder creatre(SwipeRefreshLayout refreshLayout,
                                         RecyclerView recyclerView, DataConveter conveter) {

        return new RefreshHanlder(refreshLayout, recyclerView, conveter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);//2秒延时
    }

    //首页数据
    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {
                        final JSONObject  object  = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置adapter
                        mAdapter  =  MultipleRecyclerAdapter.create(COVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHanlder.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
