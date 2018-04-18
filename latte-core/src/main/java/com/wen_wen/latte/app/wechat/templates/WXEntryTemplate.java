package com.wen_wen.latte.app.wechat.templates;

import com.wen_wen.latte.app.wechat.BaseWXEntryActivity;
import com.wen_wen.latte.app.wechat.LatteWeChat;

/**
 * Created by WeLot on 2018/4/18.
 * 登录完成之后 会回到一个微信的页面 这是我们不想看到的
 * 解决办法：
 * 1.将activity 设置成透明的
 * 2.onResume  :  finish()   不需要动画
 */

public class WXEntryTemplate extends BaseWXEntryActivity {


    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);//不需要动画
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getmSignInCallback().onSignInSuccess(userInfo);
    }
}
