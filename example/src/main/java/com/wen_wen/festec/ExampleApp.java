package com.wen_wen.festec;

import android.app.Application;

import com.wen_wen.latte.app.app.Latte;

/**
 * Created by WeLot on 2018/4/12.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
