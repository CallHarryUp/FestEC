package com.wen_wen.latte.app.delegate.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wen_wen.latte.app.delegate.web.route.Router;
import com.wen_wen.latte.app.delegate.web.WebDelegate;
import com.wen_wen.latte.app.util.log.LatteLogger;

/**
 * Created by WeLot on 2018/4/24.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /*@Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }*/

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        LatteLogger.d("shouldOverrideUrlLoading", url);
        // url将由原生接管
        return Router.getInstanse().handleWebUrl(DELEGATE,url);

    }
}
