package com.wen_wen.latte.app.delegate.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.wen_wen.latte.app.delegate.web.WebDelegate;

/**
 * Created by WeLot on 2018/4/24.
 * 抽象每一个具体的事件
 */

public abstract class Event implements IEvent {

    private Context mContext;
    private String mAction = null;
    private WebDelegate mDelegate;
    private String mUrl = null;

    private WebView  mWebView;

    public WebView getWebView() {
        return mWebView;
    }

    public void setWebView(WebView mWebView) {
        this.mWebView = mWebView;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
