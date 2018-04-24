package com.wen_wen.latte.ec.launcher.main.cart;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.squareup.picasso.Picasso;
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


    protected ShopCartAdapter(List<MultiipleItemEntity> data) {
        super(data);
        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultiipleItemEntity entry) {
        super.convert(holder, entry);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //取值
                final int id = entry.getField(MulitipleFields.ID);
                final String thumb = entry.getField(MulitipleFields.IMAGE_URL);
                final String title = entry.getField(ShopCartItemFields.TITLE);
                final String desc = entry.getField(ShopCartItemFields.DESC);
                final int count = entry.getField(ShopCartItemFields.COUNT);
                final double price = entry.getField(ShopCartItemFields.PRICE);
                //取出控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Picasso.with(mContext).load(thumb).into(imgThumb);

                break;
            default:
                break;
        }
    }
}
