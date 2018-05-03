package com.wen_wen.festec.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wen_wen.latte.app.delegate.web.event.Event;



/**
 * Created by WeLot on 2018/5/3.
 */

public class ShareEvent extends Event {
    @Override
    public String execute(String params) {

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");



        return null;
    }
}
