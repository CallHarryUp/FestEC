package com.wen_wen.latte.app.delegate.web.event;

import android.widget.Toast;

/**
 * Created by WeLot on 2018/4/24.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),params,Toast.LENGTH_SHORT).show();
        return null;
    }
}
