package com.wen_wen.festec;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wen_wen.latte.app.activities.ProxyActivity;
import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.ui.launcher.IlauncherListener;
import com.wen_wen.latte.app.ui.launcher.OnLauncherFinishTag;
import com.wen_wen.latte.ec.launcher.launcher.LauncherDelegate;
import com.wen_wen.latte.ec.launcher.main.EcBottomDelegate;
import com.wen_wen.latte.ec.launcher.sign.ISignListener;
import com.wen_wen.latte.ec.launcher.sign.SignInDelegate;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

/**
 * 1、判断是不是第一次启动app
 * 2、判断用户是都登录
 * 3、如果用户登录进入主界面
 * 4、用户没登录每次倒计时页面结束 进入登录页面
 */


public class ExampleActivity extends ProxyActivity implements ISignListener, IlauncherListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //添加上下文
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);

    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SINNED:
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                Log.d("111", "开始执行");
                Toast.makeText(getApplicationContext(), "启动结束 ，用户登录", Toast.LENGTH_SHORT).show();
                break;
            case NOT_SIGNED:
                Toast.makeText(getApplicationContext(), "启动结束 ，用户没登录", Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onPause(this);
    }
}
