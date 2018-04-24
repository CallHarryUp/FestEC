package com.wen_wen.latte.ec.launcher.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.ui.recycler.MultiipleItemEntity;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by WeLot on 2018/4/19.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {
    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView;

    private ShopCartAdapter mAdapter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("")
                .loader(getContext())
                .success(this)
                .build()
                .get();

    }

    @Override
    public void OnSuccess(String response) {
        final List<MultiipleItemEntity> data = new ShopCartDataConveter()
                .setJsonData(response)
                .convert();

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ShopCartAdapter(data);
        mRecyclerView.setAdapter(mAdapter);


    }
}
