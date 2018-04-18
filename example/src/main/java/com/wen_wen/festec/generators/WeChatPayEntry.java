package com.wen_wen.festec.generators;

import com.example.annoations.PayEntryGenerator;
import com.wen_wen.latte.app.wechat.WXPayEntryTemplate;

/**
 * Created by WeLot on 2018/4/18.
 */
@PayEntryGenerator(
        packageName = "com.wen_wen.festec",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
