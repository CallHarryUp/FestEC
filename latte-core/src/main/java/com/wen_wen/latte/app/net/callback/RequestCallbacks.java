package com.wen_wen.latte.app.net.callback;

import android.os.Handler;

import com.wen_wen.latte.app.ui.LatteLoader;
import com.wen_wen.latte.app.ui.LoaderStyle;

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
    //在callback中取消显示 loading
    private final LoaderStyle LOADER_STYLE;

    //将hanlder声明为static final 类型 避免内存泄漏
    private static final Handler HANLDER = new Handler();


    public RequestCallbacks(Irequest request, ISuccess success, IFailure failure, IError error, LoaderStyle style) {
        this.IREQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
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
        stopLoading();


    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {//请求错误
            FAILURE.onFailure();
        }
        if (IREQUEST != null) {//请求结束
            IREQUEST.onRequestEnd();
        }
        stopLoading();


    }

     private   void stopLoading(){
         // 在请求结束之后  取消loading  为了展示清楚 加入延时
         if (LOADER_STYLE != null) {
             HANLDER.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     LatteLoader.stopLoading();
                 }
             }, 1000);
         }
     }
}
