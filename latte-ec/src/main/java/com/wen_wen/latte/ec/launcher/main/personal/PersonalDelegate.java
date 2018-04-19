package com.wen_wen.latte.ec.launcher.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.ec.R;

/**
 * Created by WeLot on 2018/4/19.
 */

public class PersonalDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
