package com.wen_wen.latte.app.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by WeLot on 2018/4/20.
 */

public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }




    public static MultipleViewHolder create(View view) {

        return new MultipleViewHolder(view);
    }


}
