package com.wen_wen.latte.app.bottom;

/**
 * Created by WeLot on 2018/4/19.
 * 底部图标的bean类
 *
 * 好处：将变量声明为final在并发编程中 安全
 */

public class BottonTabBean {
    private   final  CharSequence  ICON;
    private  final   CharSequence TITLE;

    public BottonTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
