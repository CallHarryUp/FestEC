package com.wen_wen.latte.app.ui.recycler;

import java.util.LinkedHashMap;

/**
 * Created by WeLot on 2018/4/20.
 */

public class MultipleEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        //先清除之前的额数据
        FIELDS.clear();
    }

    //设置类型
    public final MultipleEntityBuilder setItemType(int itemType) {

        FIELDS.put(MulitipleFields.ITEM_TYPE, itemType);
        return this;
    }

    //设置数据
    public final MultipleEntityBuilder setField(Object key, Object value) {

        FIELDS.put(key, value);
        return this;
    }

    //设置所有的数据
    public final MultipleEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultiipleItemEntity build() {

        return new MultiipleItemEntity(FIELDS);
    }


}
