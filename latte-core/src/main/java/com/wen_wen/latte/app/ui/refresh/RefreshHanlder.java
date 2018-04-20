package com.wen_wen.latte.app.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.ISuccess;

/**
 * Created by WeLot on 2018/4/19.
 */

public class RefreshHanlder implements SwipeRefreshLayout.OnRefreshListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHanlder(SwipeRefreshLayout refreshLayout) {
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }
    private   void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                 REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);//2秒延时
    }
    //首页数据
    public   void firstPage(String  url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();

    }
}