package com.wen_wen.latte.ec.launcher.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.ui.recycler.MultiipleItemEntity;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WeLot on 2018/4/19.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {
    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView;

    private ShopCartAdapter mAdapter;
    @BindView(R2.id.icon_cart_select_all)
    IconTextView mIconSelectAll = null;
    //当前选中的item的数量(购物车数量标记)
    private int mCurrnetCount = 0;
    //总共item的数量
    private int mTotalCount = 0;


    //private int mIconSelectedCount = 0;

    //全选  根据view里面的tag进行事件绑定
    @OnClick(R2.id.icon_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {//没有选中
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新显示状态
            // mAdapter.notifyDataSetChanged();//改变的是entity的值
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            //更新recyclerView的状态
            //  mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    //删除
    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        //取出所有数据
        final List<MultiipleItemEntity> data = mAdapter.getData();
        //删除的数据
        List<MultiipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultiipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {//将删除的数据加入到集合中
                deleteEntities.add(entity);
            }

        }
        for (MultiipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosiiton = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosiiton > mCurrnetCount - 1) {
                removePosition = entityPosiiton - (mTotalCount - mCurrnetCount);
            } else {
                removePosition = entityPosiiton;
            }
            if (removePosition<=mAdapter.getItemCount()) {
                //第三方库中
                mAdapter.remove(removePosition);
                mCurrnetCount = mAdapter.getItemCount();
                // 更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }

        }
    }

    //清空
    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //初始化时 设置tag 不然 报错
        mIconSelectAll.setTag(0);

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
