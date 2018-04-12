package com.wen_wen.latte.app;

/**
 * Created by WeLot on 2018/4/12.
 */

/**
 * 枚举类 唯一的单例 只能初始化一次
 * 线程安全的懒汉模式
 */
public enum  ConfigType {
    API_HOST,
    APPLICATION_CONTEXT,
    CONFIG_READY,
    ICON
}
