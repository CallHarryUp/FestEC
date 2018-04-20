package com.wen_wen.festec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.net.interceptors.DebugInterceptor;
import com.wen_wen.latte.ec.launcher.database.DatabaseManager;
import com.wen_wen.latte.ec.launcher.icon.FontEcModule;

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
                .withWeChatAppSecret("")
                .configure();

        //初始化数据库
        DatabaseManager.getInstance().init(this);
    }

    //数据库查看工具
    private void initStetho() {


    }
}
