package com.wen_wen.latte.app.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.wen_wen.latte.app.delegate.web.event.Event;
import com.wen_wen.latte.app.delegate.web.event.EventManager;

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

    //处理事件  添加注解，android4.4之后必须添加注解不然认为不可用
    @JavascriptInterface
    public String event(String params) {

        final String action = JSON.parseObject(params).getString("action");
        //创建事件
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }

}
