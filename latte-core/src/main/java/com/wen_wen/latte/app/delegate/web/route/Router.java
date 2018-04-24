package com.wen_wen.latte.app.delegate.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.delegate.web.WebDelegate;

/**
 * Created by WeLot on 2018/4/24.
 */

public class Router {
    public Router() {
    }

    private static class Holder {

        private static final Router INSTANCE = new Router();
    }

    public static Router getInstanse() {
        return Holder.INSTANCE;
    }

    //处理Url  内容拦截
    public final boolean handleWebUrl(WebDelegate delegate, String url) {

        //如果电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        LatteDelegate topDelegate = delegate.getmTopDelegate();


        /*//判断有没有父delegate
        final LatteDelegate parentDelegate = delegate.getParentDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        if (parentDelegate == null) {//父delegate 是空，由传进来的delegate执行跳转
            delegate.getSupportDelegate().start(webDelegate);
        } else {//不是空有父delegate执行跳转
            parentDelegate.getSupportDelegate().start(webDelegate);
        }*/

        topDelegate.getSupportDelegate().start(delegate);
        return true;
    }

    //进行web页面load
    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException(" webView   is  null");
        }

    }

    //本地页面load
    private void loadLocalPage(WebView webView, String url) {

        loadWebPage(webView, "file:///android_asset/" + url);

    }

    public void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }


    //调用电话界面
    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }


}
