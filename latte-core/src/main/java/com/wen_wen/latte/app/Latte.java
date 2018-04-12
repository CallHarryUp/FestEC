package com.wen_wen.latte.app;

/**
 * Created by wen_wen on 2018/4/12.
 */

import android.content.Context;

import java.util.WeakHashMap;

/**
 * 不 可继承  工具类  静态方法
 */
public final class Latte {
    //调用 Latte 的 init()的 时候  就是进行项目的初始化
    public static Configurator init(Context context) {

        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());

        return Configurator.getinstance();

    }

    private static WeakHashMap<String, Object> getConfigurations() {

        return Configurator.getinstance().getLatteConfigs();
    }
}
