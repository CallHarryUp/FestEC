package com.wen_wen.latte.ec.launcher.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by wen_wen on 2014/4/15
 */

public enum EcIcons implements Icon {
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
