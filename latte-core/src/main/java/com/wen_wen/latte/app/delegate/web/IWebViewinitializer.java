package com.wen_wen.latte.app.delegate.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by WeLot on 2018/4/24.
 */

public interface IWebViewinitializer {
    //初始化webview
    WebView initWebView(WebView  webView);

    //初始化 client  针对浏览器本身行为控制
    WebViewClient initWebViewClient();

    //针对内部页面的控制
    WebChromeClient initWebChromeClient();
}
