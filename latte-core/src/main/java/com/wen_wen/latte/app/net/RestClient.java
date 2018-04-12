package com.wen_wen.latte.app.net;

/**
 * Created by WeLot on 2018/4/12.
 */

import com.wen_wen.latte.app.net.callback.IError;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.net.callback.Irequest;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * 设计模式：
 * 使用建造者模式
 */
public class RestClient {

    private final String URL;

    private static final WeakHashMap<String, Object> PARAMS  = RestCreator.getParams();

    private final Irequest IREQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public RestClient(String url, Map<String, Object> params,
                      Irequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.IREQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    //创建建造者
    public   static   RestClientBuilder  builder(){

        return   new RestClientBuilder();
    }
}
