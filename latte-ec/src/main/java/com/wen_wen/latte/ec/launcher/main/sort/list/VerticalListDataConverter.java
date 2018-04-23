package com.wen_wen.latte.ec.launcher.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wen_wen.latte.app.ui.recycler.DataConveter;
import com.wen_wen.latte.app.ui.recycler.ItemType;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MultiipleItemEntity;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/23.
 */

public final class VerticalListDataConverter extends DataConveter {


    @Override
    public ArrayList<MultiipleItemEntity> convert() {
        //解析数据
        final ArrayList<MultiipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJosnData())
                .getJSONObject("data")
                .getJSONArray("list");

        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(1);
            final int id = data.getInteger("id");
            final String  name =data.getString("name");
            final  MultiipleItemEntity  entity   =  MultiipleItemEntity.builder()
                    .setField(MulitipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MulitipleFields.ID,id)
                    .setField(MulitipleFields.TEXT,name)
                    .setField(MulitipleFields.TAG,false)
                    .build();

            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MulitipleFields.TAG,true);


        }
        return dataList;
    }
}
