package com.wen_wen.latte.app.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.delegate.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by WeLot on 2018/4/24.
 */

public abstract class WebDelegate extends LatteDelegate implements IWebViewinitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;

    private LatteDelegate mTopDelegate;

    public WebDelegate() {
    }

    public abstract IWebViewinitializer setInitializer();

    //一fragment作为每一个页面的载体
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();

    }

    //初始化webview
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewinitializer initializer = setInitializer();
            // webview在xml中声明 会造成内存泄漏
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<WebView>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                //webview和原生进行交互
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), "latte");
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer  is  null");
            }
        }
    }

    //设置顶级delegate
    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }

    public LatteDelegate getmTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }

        return mTopDelegate;

    }


    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("webView   is  null");
        }
        return mIsWebViewAvailable ? mWebView : null;

    }

    public String getUrl() {

        if (mUrl == null) {
            throw new NullPointerException(" url is  null");
        }

        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView = null;
        }
    }


}
