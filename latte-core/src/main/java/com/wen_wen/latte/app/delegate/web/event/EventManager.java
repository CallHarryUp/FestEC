package com.wen_wen.latte.app.delegate.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by WeLot on 2018/4/24.
 * 时间管理类
 */

public class EventManager {
    private static final HashMap<String, Event> EVENTS = new HashMap<>();


    private EventManager() {
    }

    private static class Holder {

        private static final EventManager INSTAMCE = new EventManager();
    }

    public static EventManager getInstance() {

        return Holder.INSTAMCE;
    }

    //添加事件
    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefindEvent();
        }
        return event;
    }

}
