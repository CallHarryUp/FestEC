package com.wen_wen.latte.app.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WeLot on 2018/4/12.
 */

public class RequestCallbacks implements Callback<String> {
    private final Irequest IREQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public RequestCallbacks(Irequest request, ISuccess success, IFailure failure, IError error) {
        this.IREQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {//请求成功
            if (call.isExecuted()) {//call执行
                if (SUCCESS != null) {
                    SUCCESS.OnSuccess(response.body());
                }
            }
        } else {//请求失败
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {//请求错误
            FAILURE.onFailure();
        }
        if (IREQUEST != null) {//请求结束
            IREQUEST.onRequestEnd();
        }


    }
}
