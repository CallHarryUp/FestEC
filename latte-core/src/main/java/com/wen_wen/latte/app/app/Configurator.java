package com.wen_wen.latte.app.app;

import java.util.WeakHashMap;

/**
 * Created by WeLot on 2018/4/12.
 */
//配置文件存储 和 获取
public class Configurator {
    //键值对 不使用的时候 就会回收
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    public Configurator() {
        //配置开始 但是配置没有完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getinstance() {

        return Holder.INSTANCE;
    }

    final WeakHashMap<String, Object> getLatteConfigs() {

        return LATTE_CONFIGS;
    }

    //  静态内部类 单例模式的初始化
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();

    }

    // 配置完成
    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    //
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //检测配置项有没有完成
    private void checkConfigration() {

        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration   is  notReady, call COnfigue");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfigration();
        return (T) LATTE_CONFIGS.get(key);
    }


}
