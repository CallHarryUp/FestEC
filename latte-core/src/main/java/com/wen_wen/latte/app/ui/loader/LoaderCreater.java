package com.wen_wen.latte.app.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by WeLot on 2018/4/13.
 * 通过缓存的方式 提高效率 loadingView的效率
 */

public final class LoaderCreater {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        //判断 map中 有没有  有直接使用没有 添加到map中
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);

            LOADING_MAP.put(type, indicator);
        }

        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));

        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();

            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }

        drawableClassName.append(name);//生成了具体的view  通过反射的方法
        //通过名字 返回具体的类
        final Class<?> drawableClass;
        try {
            drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
