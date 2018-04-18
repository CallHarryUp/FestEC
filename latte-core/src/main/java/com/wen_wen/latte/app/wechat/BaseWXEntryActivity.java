package com.wen_wen.latte.app.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.IError;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;

/**
 * Created by WeLot on 2018/4/18.
 * 1.第一步获取一个url
 * 2.拿着这个url 请求 access_token  和 openId
 * 3.获取url  请求用户的真正信息 如：昵称 头像
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    //用户登陆成功回调
    protected abstract void onSignInSuccess(String userInfo);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oath2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        getAuth(authUrl.toString());


    }

    private void getAuth(String anthUrl) {
        RestClient.builder()
                .url(anthUrl)
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {
                        //获取 access_token  openId
                        final JSONObject authObj = JSON.parseObject(response);
                        String accessToken = authObj.getString("access_token");
                        String openId = authObj.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");
                        getUserInfo(userInfoUrl.toString());


                    }
                })
                .build()
                .get();
    }

    private void getUserInfo(String userInfoUrl) {
        RestClient
                .builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {
                        onSignInSuccess(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
