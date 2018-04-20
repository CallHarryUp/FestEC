package com.wen_wen.latte.ec.launcher.main.index;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wen_wen.latte.app.ui.recycler.DataConveter;
import com.wen_wen.latte.app.ui.recycler.ItemType;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MulitipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeLot on 2018/4/20.
 */

public class IndexDataConverter extends DataConveter {
    @Override
    public ArrayList<MulitipleItemEntity> convert() {
        //处理数据
        JSONArray dataArray = JSON.parseObject(getJosnData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            String imageUrl = data.getString("imageUrl");
            String text = data.getString("text");
            String id = data.getString("goodsId");
            String spanSize = data.getString("spanSize");
            JSONArray banners = data.getJSONArray("banners");

            Log.d("111","imageUrl:"+imageUrl);

            List<String> bannersImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //banner的初始化
                int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    String banner = banners.getString(j);
                    bannersImages.add(banner);//轮播图集合
                }

            }
            MulitipleItemEntity entity   =  MulitipleItemEntity.builder()
                    .setField(MulitipleFields.ITEM_TYPE,type)
                    .setField(MulitipleFields.SPAN_SIZE,spanSize)
                    .setField(MulitipleFields.ID,id)
                    .setField(MulitipleFields.TEXT,text)
                    .setField(MulitipleFields.IMAGE_URL,imageUrl)
                    .setField(MulitipleFields.BANNERS,bannersImages)
                    .build();

            ENTITIES.add(entity);

        }

        return ENTITIES;
    }
}
