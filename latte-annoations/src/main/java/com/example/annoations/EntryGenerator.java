package com.example.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by WeLot on 2018/4/17.
 * 传入包名  以及微信所需要的模板代码
 */
@Target(ElementType.TYPE)//注解是用在类上面的
@Retention(RetentionPolicy.SOURCE)//在源码阶段处理此注解  对性能没有影响
public @interface EntryGenerator {
    //包名
    String packageName();

    Class<?> entryTemplete();

}
