package com.wen_wen.latte.app.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * Created by WeLot on 2018/4/23.
 * privided  在编译阶段使用  打包时 不会使用
 */
@AutoValue
public abstract class RgbValue {


    public abstract int red();

    public abstract int green();

    public abstract int blue();


    public static RgbValue create(int red, int green, int blue) {

        return new AutoValue_RgbValue(red, green, blue);
    }

}
