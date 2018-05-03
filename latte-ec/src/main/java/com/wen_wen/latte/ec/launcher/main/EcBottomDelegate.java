package com.wen_wen.latte.ec.launcher.main;

import android.graphics.Color;
import android.util.Log;

import com.wen_wen.latte.app.bottom.BaseBottomDelegate;
import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.app.bottom.BottomTabBean;
import com.wen_wen.latte.app.bottom.ItemBuilder;
import com.wen_wen.latte.ec.launcher.main.index.IndexDelegate;

import java.util.LinkedHashMap;

/**
 * Created by WeLot on 2018/4/19.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "首页"), new IndexDelegate());
       // items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        //items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
       // items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        //items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        Log.d("111","添加结束");
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
