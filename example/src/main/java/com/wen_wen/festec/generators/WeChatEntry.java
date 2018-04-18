package com.wen_wen.festec.generators;

import com.example.annoations.EntryGenerator;
import com.wen_wen.latte.app.wechat.templates.WXEntryTemplate;

/**
 * Created by WeLot on 2018/4/18.
 */
@EntryGenerator(
        packageName = "com.wen_wen.festec",
        entryTemplete = WXEntryTemplate.class

)
public interface WeChatEntry {
}
