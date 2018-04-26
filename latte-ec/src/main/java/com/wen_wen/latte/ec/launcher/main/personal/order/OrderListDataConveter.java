package com.wen_wen.latte.ec.launcher.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wen_wen.latte.app.ui.recycler.DataConveter;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MulitipleItemEntity;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/26.
 */

public class OrderListDataConveter extends DataConveter {
    @Override
    public ArrayList<MulitipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJosnData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");

            final MulitipleItemEntity entity = MulitipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MulitipleFields.ID, id)
                    .setField(MulitipleFields.IMAGE_URL, thumb)
                    .setField(MulitipleFields.TITLE, title)
                    .setField(OrderItemFields.PRICE, price)
                    .setField(OrderItemFields.TIME, time)
                    .build();

            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
