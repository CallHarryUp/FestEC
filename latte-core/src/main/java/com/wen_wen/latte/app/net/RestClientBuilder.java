package com.wen_wen.latte.app.net;

import com.wen_wen.latte.app.net.callback.IError;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.net.callback.Irequest;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by WeLot on 2018/4/12.
 * 网络请求构建类
 */

public class RestClientBuilder {

    private String mUrl;
    private Map<String, Object> PARAMS  =  RestCreator.getParams();
    private Irequest mIrequest;
    private ISuccess mIsuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, String value) {
        if (PARAMS == null) {
            PARAMS = new WeakHashMap<>();
        }
        this.PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset =  UTF-8"), raw);

        return this;
    }

    public final RestClientBuilder onRequest(Irequest irequest) {
        this.mIrequest = irequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {

        this.mIsuccess = iSuccess;

        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {

        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {

        this.mIError = iError;
        return this;
    }

  /*  //检查
    private Map<String, Object> checkParams() {
        if (PARAMS == null) {
            return new WeakHashMap<>();
        } else {
            return PARAMS;
        }
    }
*/
    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIrequest, mIsuccess, mIFailure, mIError, mBody);
    }
}
