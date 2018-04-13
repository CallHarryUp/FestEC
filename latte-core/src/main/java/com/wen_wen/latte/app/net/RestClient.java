package com.wen_wen.latte.app.net;

/**
 * Created by WeLot on 2018/4/12.
 */

import com.wen_wen.latte.app.net.callback.IError;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.net.callback.Irequest;
import com.wen_wen.latte.app.net.callback.RequestCallbacks;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 设计模式：
 * 使用建造者模式
 */
public class RestClient {

    private final String URL;

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

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
    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    //创建请求方式
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    //获取实例
    private Callback<String> getRequestCallback() {

        return new RequestCallbacks(IREQUEST,
                SUCCESS,
                FAILURE,
                ERROR);
    }

    //创建请求方法
    public final void get() {

        request(HttpMethod.GET);
    }

    public final void post() {

        request(HttpMethod.POST);
    }

    public final void put() {

        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


}
