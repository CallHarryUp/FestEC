package com.wen_wen.latte.ec.launcher.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.ec.R;

/**
 * Created by WeLot on 2018/4/19.
 */

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.deleate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
