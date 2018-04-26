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

/**
 * Created by WeLot on 2018/4/20.
 */

public class IndexDataConverter extends DataConveter {
    @Override
    public ArrayList<MulitipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJosnData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("goodsId");
            Log.d("111","id:"+id);
            final String imageUrl = data.getString("imgUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");

            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MulitipleItemEntity entity = MulitipleItemEntity.builder()
                    .setField(MulitipleFields.ITEM_TYPE,type)
                    .setField(MulitipleFields.SPAN_SIZE,spanSize)
                    .setField(MulitipleFields.ID,id)
                    .setField(MulitipleFields.TEXT,text)
                    .setField(MulitipleFields.IMAGE_URL,imageUrl)
                    .setField(MulitipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);

        }

        return ENTITIES;
    }
}
