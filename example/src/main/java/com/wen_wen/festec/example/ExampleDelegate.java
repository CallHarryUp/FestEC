package com.wen_wen.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.wen_wen.festec.R;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.IError;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;

/**
 * Created by WeLot on 2018/4/12.
 */

public class ExampleDelegate  extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
     //  testRestClient();
    }


    private  void  testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

}
