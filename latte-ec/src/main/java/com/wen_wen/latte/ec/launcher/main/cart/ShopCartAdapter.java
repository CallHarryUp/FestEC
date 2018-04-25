package com.wen_wen.latte.ec.launcher.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.squareup.picasso.Picasso;
import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MultiipleItemEntity;
import com.wen_wen.latte.app.ui.recycler.MultipleRecyclerAdapter;
import com.wen_wen.latte.app.ui.recycler.MultipleViewHolder;
import com.wen_wen.latte.ec.R;

import java.util.List;

/**
 * Created by WeLot on 2018/4/24.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {
    private boolean mIsSelectedAll = false;

    private ICartItemListener mCartItemListener;

    private double mTotalPrice = 0.00;


    protected ShopCartAdapter(List<MultiipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultiipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice += total;

        }
        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);

    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setCartItemListener(ICartItemListener mCartItemListener) {
        this.mCartItemListener = mCartItemListener;
    }

    public double getmTotalPrice() {

        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultiipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //取值
                final int id = entity.getField(MulitipleFields.ID);
                final String thumb = entity.getField(MulitipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                //final boolean isSelected = entry.getField(ShopCartItemFields.IS_SELECTED);
                //取出控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Picasso.with(mContext)
                        .load(thumb)
                        .into(imgThumb);
                // 在左侧按钮渲染之前改变状态
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);

                //根据数据状态 显示左侧按钮
                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧按钮点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);

                        }
                    }
                });

                //添加加减事件   服务器塔里可能较大 单式可能最大限度的保持数据同步
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        final int currentPrice = entity.getField(ShopCartItemFields.PRICE);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            //请求服务器减1
                            RestClient.builder()
                                    .url(" ")
                                    .loader(mContext)
                                    .params("count", String.valueOf(currentCount))//形式有服务器指定具体的外呼数据类型
                                    .success(new ISuccess() {
                                        @Override
                                        public void OnSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mCartItemListener != null) {
                                                mTotalPrice = mTotalPrice - currentPrice;
                                                final double itemTotal = countNum * currentPrice;
                                                mCartItemListener.onItemClick(itemTotal);
                                            }

                                        }
                                    })
                                    .build()
                                    .post();

                        }
                    }
                });
                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        final int currentPrice = entity.getField(ShopCartItemFields.PRICE);
                        RestClient.builder()
                                .url("")
                                .params("count", String.valueOf(currentCount))
                                .success(new ISuccess() {
                                    @Override
                                    public void OnSuccess(String response) {
                                        int countNum = Integer.parseInt(tvCount.getText().toString());
                                        countNum++;
                                        tvCount.setText(String.valueOf(countNum));
                                        if (mCartItemListener != null) {
                                            mTotalPrice = mTotalPrice + currentPrice;
                                            final double itemTotal = countNum * currentPrice;
                                            mCartItemListener.onItemClick(itemTotal);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                break;
            default:
                break;
        }
    }
}
