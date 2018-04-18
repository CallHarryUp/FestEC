package com.wen_wen.festec.generators;

import com.example.annoations.AppRegisterGenerator;
import com.wen_wen.latte.app.wechat.templates.AppRegisterTemplate;

/**
 * Created by WeLot on 2018/4/18.
 */
@AppRegisterGenerator(
        packageName = "com.wen_wen.festec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
