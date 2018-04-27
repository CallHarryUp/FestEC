package com.wen_wen.latte.app.util.callback;

import java.util.WeakHashMap;

/**
 * Created by WeLot on 2018/4/27.
 */

public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCllback> CALLBACKS = new WeakHashMap<>();

    private static class Holder {

        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {

        return Holder.INSTANCE;
    }


    public CallbackManager addCallback(Object tag, IGlobalCllback callback) {

        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCllback getCallback(Object tag) {

        return CALLBACKS.get(tag);

    }


}
