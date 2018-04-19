package com.wen_wen.latte.app.bottom;

import java.util.LinkedHashMap;

/**
 * Created by WeLot on 2018/4/19.
 */

public final class ItemBuilder {
    //有序展示数据
    private final LinkedHashMap<BottonTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    //使用简单工厂的设计模式 创建  可以让参数一目了然
    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottonTabBean bean, BottomItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottonTabBean, BottomItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }


    public final LinkedHashMap<BottonTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }


}
