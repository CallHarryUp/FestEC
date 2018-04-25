package com.wen_wen.latte.app.wechat.templates;

import android.widget.Toast;

import com.wen_wen.latte.app.wechat.BaseWXPayEntryActivity;

/**
 * Created by WeLot on 2018/4/18.
 * 微信支付界面
 */

public class WXPayEntryTemplate extends BaseWXPayEntryActivity {


    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        finish();//界面会在前端显示，销毁界面
        overridePendingTransition(0,0);//不使用动画

    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        finish();//界面会在前端显示，销毁界面
        overridePendingTransition(0,0);//不使用动画

    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        finish();//界面会在前端显示，销毁界面
        overridePendingTransition(0,0);//不使用动画

    }
}
