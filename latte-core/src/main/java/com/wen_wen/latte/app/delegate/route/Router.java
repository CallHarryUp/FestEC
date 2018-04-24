package com.wen_wen.latte.app.delegate.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.delegate.web.WebDelegate;
import com.wen_wen.latte.app.delegate.web.WebDelegateImpl;

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
        //判断有没有父delegate
        final LatteDelegate parentDelegate = delegate.getParentDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        if (parentDelegate == null) {//父delegate 是空，由传进来的delegate执行跳转
            delegate.getSupportDelegate().start(webDelegate);
        } else {//不是空有父delegate执行跳转
            parentDelegate.getSupportDelegate().start(webDelegate);
        }
        return true;
    }


    //调用电话界面
    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }


}
