package com.wen_wen.latte.app.util.timer;

import java.util.TimerTask;

/**
 * Created by WeLot on 2018/4/16.
 */

public class BaseTimerTask extends TimerTask {
//timer需要一个回调  倒计时结束之后 我们应该怎么做

    private  ITimerListener  mITimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null) {
            mITimerListener.onTimer();
        }

    }
}
