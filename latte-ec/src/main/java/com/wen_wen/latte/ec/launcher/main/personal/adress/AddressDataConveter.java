package com.wen_wen.latte.ec.launcher.main.personal.adress;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wen_wen.latte.app.ui.recycler.DataConveter;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MulitipleItemEntity;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/27.
 */

public class AddressDataConveter extends DataConveter {
    @Override
    public ArrayList<MulitipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJosnData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MulitipleItemEntity entity = MulitipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MulitipleFields.ID, id)
                    .setField(MulitipleFields.NAME, name)
                    .setField(MulitipleFields.TAG, isDefault)
                    .setField(AddressItemFields.ADDRESS, address)
                    .setField(AddressItemFields.PHONE, phone)
                    .build();
            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
