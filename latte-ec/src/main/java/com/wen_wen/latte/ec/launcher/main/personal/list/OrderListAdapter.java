package com.wen_wen.latte.ec.launcher.main.personal.list;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.squareup.picasso.Picasso;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MulitipleItemEntity;
import com.wen_wen.latte.app.ui.recycler.MultipleRecyclerAdapter;
import com.wen_wen.latte.app.ui.recycler.MultipleViewHolder;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.launcher.main.personal.order.OrderItemFields;
import com.wen_wen.latte.ec.launcher.main.personal.order.OrderListItemType;

import java.util.List;

/**
 * Created by WeLot on 2018/4/26.
 */

public class OrderListAdapter extends MultipleRecyclerAdapter {


    public OrderListAdapter(List<MulitipleItemEntity> data) {
        super(data);

        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MulitipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView   =  holder.getView(R.id.image_order_list);
                final AppCompatTextView  title  =  holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView  price  =  holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView  time  =  holder.getView(R.id.tv_order_list_time);
                final  String titleVal   = entity.getField(MulitipleFields.TITLE);
                final  String timeVal   = entity.getField(OrderItemFields.TIME);
                final  double priceVal   = entity.getField(OrderItemFields.PRICE);
                final String imgUrl = entity.getField(MulitipleFields.IMAGE_URL);

                title.setText(titleVal);
                price.setTag("价格："+String.valueOf(priceVal));
                time.setText("时间："+timeVal);
                Picasso.with(mContext)
                        .load(imgUrl)
                        .into(imageView);

                break;
            default:
                break;
        }

    }
}
