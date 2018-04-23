package com.wen_wen.latte.ec.launcher.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeLot on 2018/4/23.
 */

public class SectionDataConverter {

    final List<SectionBean> convert(String json) {
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            Integer id = data.getInteger("id");
            String title = data.getString("section");
            //添加title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setIsMore(true);
            dataList.add(sectionTitleBean);
            //商品内容循环
            final JSONArray goods = data.getJSONArray("goods");

            int goodsSize = goods.size();
            for (int j = 0; j < goodsSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);

                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");
                //向实体类中添加内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setmGoodsId(goodsId);
                itemEntity.setmGoodsName(goodsName);
                itemEntity.setmGoodsThumb(goodsThumb);
                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束

        }
        //Section循环结束
        return dataList;
    }


}
