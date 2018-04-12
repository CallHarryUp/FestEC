package com.wen_wen.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wen_wen.festec.R;
import com.wen_wen.latte.app.delegate.LatteDelegate;

/**
 * Created by WeLot on 2018/4/12.
 */

public class ExampleDelegate  extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
