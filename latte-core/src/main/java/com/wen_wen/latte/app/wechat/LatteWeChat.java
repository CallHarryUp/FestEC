package com.wen_wen.latte.app.wechat;


import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wen_wen.latte.app.app.ConfigKeys;
import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.wechat.callbacks.IWeChatSingnCallBack;

/**
 * Created by WeLot on 2018/4/18.
 * 调用微信登录接口，确认登入之后 回调activity
 */

public class LatteWeChat {
    //存储app_Id  app_secret
    static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    //微信支付 微信登录用
    private final IWXAPI WXAPI;


    private IWeChatSingnCallBack mSignInCallback;

    public static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {

        return Holder.INSTANCE;
    }

    //初始化微信api
    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);

    }

    public final IWXAPI getWXAPI() {

        return WXAPI;
    }

    //赋值
    public LatteWeChat onSignSuccess(IWeChatSingnCallBack callBack) {
        this.mSignInCallback = callBack;
        return this;
    }


    public IWeChatSingnCallBack getmSignInCallback() {
        return mSignInCallback;
    }

    //向微信客户端发送请求
    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }


}
