package com.wen_wen.latte.app.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wen_wen.latte.app.delegate.web.route.RouteKeys;
import com.wen_wen.latte.app.delegate.web.route.Router;
import com.wen_wen.latte.app.delegate.web.chromeClient.WebChromeClientImpl;
import com.wen_wen.latte.app.delegate.web.client.WebViewClientImpl;

/**
 * Created by WeLot on 2018/4/24.
 * 提供用户入口
 */

public class WebDelegateImpl extends WebDelegate {

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }



    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl()!=null) {
            //用原生的方式模拟web跳转并进行页面加载
            Router.getInstanse().loadPage(this,getUrl());

        }
    }

    @Override
    public IWebViewinitializer setInitializer() {
        return this;
    }


    //初始化webview 进行相关属性设置
    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().create(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl  client  =  new WebViewClientImpl(this);

        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
