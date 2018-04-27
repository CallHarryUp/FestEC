package com.wen_wen.latte.app.util.callback;

/**
 * Created by WeLot on 2018/4/27.
 */
//泛型接口
public interface IGlobalCllback<T> {

    void executeCallback(T args);
}
