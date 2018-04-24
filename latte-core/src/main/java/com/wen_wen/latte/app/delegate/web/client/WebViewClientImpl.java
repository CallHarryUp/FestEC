package com.wen_wen.latte.app.delegate.web.client;

import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.delegate.IPageLoadListener;
import com.wen_wen.latte.app.delegate.web.route.Router;
import com.wen_wen.latte.app.delegate.web.WebDelegate;
import com.wen_wen.latte.app.ui.loader.LatteLoader;
import com.wen_wen.latte.app.util.log.LatteLogger;

/**
 * Created by WeLot on 2018/4/24.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANLDER = Latte.getHandler();

    public IPageLoadListener getIPageLoadListener() {
        return mIPageLoadListener;
    }

    public void setIPageLoadListener(IPageLoadListener mIPageLoadListener) {
        this.mIPageLoadListener = mIPageLoadListener;
    }

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
        return Router.getInstanse().handleWebUrl(DELEGATE, url);

    }

    //页面加载完成
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }

        HANLDER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
