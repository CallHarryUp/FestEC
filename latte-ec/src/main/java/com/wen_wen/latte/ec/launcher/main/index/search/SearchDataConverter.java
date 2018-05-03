package com.wen_wen.latte.ec.launcher.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.wen_wen.latte.app.ui.recycler.DataConveter;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MulitipleItemEntity;
import com.wen_wen.latte.app.util.storage.LattePrefercnce;

import java.util.ArrayList;

/**
 * Created by wen_wen
 */

public class SearchDataConverter extends DataConveter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MulitipleItemEntity> convert() {
        final String jsonStr =
                LattePrefercnce.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MulitipleItemEntity entity = MulitipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MulitipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }

        return ENTITIES;
    }
}
