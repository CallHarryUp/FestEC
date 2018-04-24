package com.wen_wen.latte.app.delegate.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by WeLot on 2018/4/24.
 * 用于和原生进行交互
 */

public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");

        return null;
    }

}
