package com.wen_wen.latte.app.delegate.web.event;

import android.widget.Toast;

/**
 * Created by WeLot on 2018/4/24.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),params,Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            getWebView().post(new Runnable() {
                @Override
                public void run() {
                    getWebView().evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
