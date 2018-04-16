package com.wen_wen.latte.ec.launcher.launcher;

import android.icu.text.MessageFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.ui.launcher.ScrollLauncherTag;
import com.wen_wen.latte.app.util.storage.LattePrefercnce;
import com.wen_wen.latte.app.util.timer.BaseTimerTask;
import com.wen_wen.latte.app.util.timer.ITimerListener;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WeLot on 2018/4/16.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;
    private Timer mTimer;
    private int mCount = 5;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    //初始化timer
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);

    }

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }
    //是否展示滚动
    private   void  checkIsShowScroll(){
        if (!LattePrefercnce.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是都登录了APP

        }

    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    //倒计时结束
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });

    }
}
