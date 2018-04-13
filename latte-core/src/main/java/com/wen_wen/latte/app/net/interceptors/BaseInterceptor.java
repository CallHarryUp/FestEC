package com.wen_wen.latte.app.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by WeLot on 2018/4/13.
 * 1/模拟服务器  需要获取参数
 */

public abstract class BaseInterceptor implements Interceptor {

    // 有序集合  获取url中的参数  :结果 有序排列的url参数段
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {

        final HttpUrl url = chain.request().url();
        //获取请求个数
        int size = url.querySize();
        //创建集合
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    //通过key值 获取value
    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    //从post中的请求体中获取
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            //通过 角标 获取name 和 vaule   并且顺序正确
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;

    }

    //根据具体值 获取
    protected String getBodyParameters(Chain chain, String key) {

        return getBodyParameters(chain).get(key);
    }


}
