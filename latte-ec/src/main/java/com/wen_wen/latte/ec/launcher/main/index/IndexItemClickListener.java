package com.wen_wen.latte.ec.launcher.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.launcher.detall.GoodsDetailDelegate;

/**
 * Created by WeLot on 2018/4/23.
 */

public class IndexItemClickListener extends SimpleClickListener {

    //需要传一个delegate实例
    public final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    //简单工厂
    public static SimpleClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }
    //处理点击事件  商品详细页面
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
           final GoodsDetailDelegate   delegate  = GoodsDetailDelegate.create();
          DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
