package com.wen_wen.latte.app.delegate.web.event;

import com.wen_wen.latte.app.util.log.LatteLogger;

/**
 * Created by WeLot on 2018/4/24.
 */

public class UndefindEvent extends  Event {
    @Override
    public String execute(String params) {
        LatteLogger.d("UnderfinedEvent",params);
        return null;
    }
}
