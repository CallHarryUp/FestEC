package com.wen_wen.latte.ec.launcher.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wen_wen.latte.app.ui.recycler.DataConveter;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MulitipleItemEntity;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/24.
 */

public class ShopCartDataConveter extends DataConveter {
    @Override
    public ArrayList<MulitipleItemEntity> convert() {

        final ArrayList<MulitipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJosnData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MulitipleItemEntity entity = MulitipleItemEntity.builder()
                    .setField(MulitipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MulitipleFields.ID, id)
                    .setField(MulitipleFields.IMAGE_URL, thumb)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.COUNT, count)
                    .setField(ShopCartItemFields.PRICE, price)
                    .setField(ShopCartItemFields.IS_SELECTED,false)
                    .setField(ShopCartItemFields.POSITION, i)
                    .build();

            dataList.add(entity);

        }
        return dataList;
    }
}
