package com.wen_wen.festec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wen_wen.festec.R;
import com.wen_wen.festec.event.ShareEvent;
import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.net.interceptors.DebugInterceptor;
import com.wen_wen.latte.app.util.callback.CallbackManager;
import com.wen_wen.latte.app.util.callback.CallbackType;
import com.wen_wen.latte.app.util.callback.IGlobalCllback;
import com.wen_wen.latte.ec.launcher.database.DatabaseManager;
import com.wen_wen.latte.ec.launcher.icon.FontEcModule;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by WeLot on 2018/4/12.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Latte.init(this)
                .withApiHost("http://192.168.1.141:8080/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withInterceptor(new DebugInterceptor("index", R.raw.text))
                .withWeChatAppId("")
                .withWebEvent("share",new ShareEvent())
                .withWeChatAppSecret("")
                .configure();

        //初始化数据库
        DatabaseManager.getInstance().init(this);


        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //使用接口实现依赖倒转
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCllback() {
                    @Override
                    public void executeCallback(Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            //重新进行极光推送初始化
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCllback() {
                    @Override
                    public void executeCallback(Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());

                        }
                    }
                });

        //初始化分享
       // MobSDK.init(Latte.getApplicationContext(), "2592777093f71", "7f023fc19e66528dbd43c5883261935c");
    }

    //数据库查看工具
    private void initStetho() {


    }
}
