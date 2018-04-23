package com.wen_wen.latte.app.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by WeLot on 2018/4/23.
 */

public class BaseDecoration extends DividerItemDecoration {

    //传入线的颜色 以及线的大小
    private BaseDecoration(@ColorInt int color, int size) {

        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {

        return new BaseDecoration(color, size);

    }
}
