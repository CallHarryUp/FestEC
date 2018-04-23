package com.wen_wen.latte.app.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by WeLot on 2018/4/20.
 */

public class MultiipleItemEntity implements MultiItemEntity {
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUENE = new ReferenceQueue<>();

    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERCNCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUENE);

    public MultiipleItemEntity(LinkedHashMap<Object, Object> fileds) {
        FIELDS_REFERCNCE.get().putAll(fileds);
    }

    public static MultipleEntityBuilder builder() {

        return new MultipleEntityBuilder();
    }


    //控制每一个item的样式
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERCNCE.get().get(MulitipleFields.ITEM_TYPE);
    }

    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERCNCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {

        return FIELDS_REFERCNCE.get();
    }

    public final MultiipleItemEntity setField(Object key,Object value){
        FIELDS_REFERCNCE.get().put(key,value);
        return this;
    }


}
