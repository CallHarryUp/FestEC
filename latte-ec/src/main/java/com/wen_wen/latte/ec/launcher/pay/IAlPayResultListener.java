package com.wen_wen.latte.ec.launcher.pay;

/**
 * Created by WeLot on 2018/4/25.
 * alipay 支付回调
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
