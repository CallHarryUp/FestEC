package com.wen_wen.latte.ec.launcher.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.IError;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.wechat.LatteWeChat;
import com.wen_wen.latte.app.wechat.callbacks.IWeChatSingnCallBack;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WeLot on 2018/4/16.
 */

public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onclickSingnIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("sign_in")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void OnSuccess(String response) {
                            SignHanlder.onSingnUp(response, mISignListener);
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
                    .post();
        }


        //虚拟返回默认返回成功
        SignHanlder.onSingnIn("", mISignListener);


    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
        Toast.makeText(getContext(),"点击微信",Toast.LENGTH_SHORT).show();
        LatteWeChat.getInstance().onSignSuccess(new IWeChatSingnCallBack() {
            @Override
            public void onSignInSuccess(String uesrInfo) {

            }
        }).signIn();

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }


    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱格式错误");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("轻至少填写6位密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
