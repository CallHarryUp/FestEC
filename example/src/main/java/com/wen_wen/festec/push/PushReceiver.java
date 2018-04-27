package com.wen_wen.festec.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.wen_wen.festec.ExampleActivity;
import com.wen_wen.latte.app.util.log.LatteLogger;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by WeLot on 2018/4/27.
 */

public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //判断极光推送的所有信息
        final Bundle bundle = intent.getExtras();
        final Set<String> keys = bundle.keySet();
        final JSONObject json = new JSONObject();
        for (String key : keys) {

            Object val = bundle.get(key);
            json.put(key, val);
        }
        LatteLogger.json("pushReceiver", json.toJSONString());
        String action = intent.getAction();
        if (action.equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
            //处理接收到的信息
            onReceivedMessage(bundle);
        } else if (action.equals(JPushInterface.ACTION_NOTIFICATION_OPENED)) {
            //打开notifaction
            onOpenNotification(context, bundle);

        }

    }

    private void onReceivedMessage(Bundle bundle) {
        final String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        final String msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID);
        final int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
        final String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        final String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        final String alert = bundle.getString(JPushInterface.EXTRA_ALERT);
    }

    private void onOpenNotification(Context context, Bundle bundle) {
        final String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        final Bundle openActivityBundle = new Bundle();
        final Intent intent = new Intent(context, ExampleActivity.class);
        intent.putExtras(openActivityBundle);
        //应用可能是在后台状态 另起一个任务栈
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ContextCompat.startActivity(context, intent, null);
    }
}
