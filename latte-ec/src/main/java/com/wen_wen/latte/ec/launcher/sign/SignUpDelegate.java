package com.wen_wen.latte.ec.launcher.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WeLot on 2018/4/16.
 * 1.帮顶视图
 */

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            /*RestClient.builder()
                    .url("sign_up")
                    .success(new ISuccess() {
                        @Override
                        public void OnSuccess(String response) {

                        }
                    })
                    .build().post();*/
            Toast.makeText(getContext(),"验证通过",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    //校验输入
    private boolean checkForm() {
        String name = mName.getText().toString();
        String email = mEmail.getText().toString();
        String phone = mPhone.getText().toString();
        String password = mPassword.getText().toString();
        String rePassowrd = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            isPass = false;
            mName.setError("请输入姓名");
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱地址错误！");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassowrd.isEmpty() || rePassowrd.length() < 6 || !(rePassowrd.equals(password))) {
            mRePassword.setError("两次输入的密码不一致");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }
        return isPass;


    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
