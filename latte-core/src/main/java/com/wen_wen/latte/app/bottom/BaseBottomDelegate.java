package com.wen_wen.latte.app.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

import com.wen_wen.latte.app.delegate.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by WeLot on 2018/4/19.
 */

public abstract class BaseBottomDelegate extends LatteDelegate {
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final ArrayList<BottonTabBean> TAB_BEANS = new ArrayList<>();
    private final LinkedHashMap<BottonTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickColor = Color.GRAY;

    //抽象方法  自雷必须继承
    public abstract LinkedHashMap<BottonTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClieckedColor();

    //初始化操作
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化
        mIndexDelegate = setIndexDelegate();
        if (setClieckedColor() != 0) {
            mClickColor = setClieckedColor();
        }
        //上面的抽象方法 是自雷需要继承的 然后早父类而完成相应的赋值初始化操作
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottonTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottonTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            BottonTabBean key = item.getKey();
            BottomItemDelegate value = item.getValue();

            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }

    }
}
