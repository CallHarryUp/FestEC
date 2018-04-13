package com.wen_wen.latte.app.app;

/**
 * Created by wen_wen on 2018/4/12.
 */

import android.content.Context;
import android.os.Handler;

/**
 * 不 可继承  工具类  静态方法
 */
public final class Latte {
  /*  //调用 Latte 的 init()的 时候  就是进行项目的初始化
    public static Configurator init(Context context) {

        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());

        return Configurator.getinstance();

    }

    public static WeakHashMap<String, Object> getConfigurations() {

        return Configurator.getinstance().getLatteConfigs();
    }
*/
  public static Configurator init(Context context) {
      Configurator.getInstance()
              .getLatteConfigs()
              .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
      return Configurator.getInstance();
  }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static void test(){
    }

}
