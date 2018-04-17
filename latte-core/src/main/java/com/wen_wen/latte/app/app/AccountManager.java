package com.wen_wen.latte.app.app;

import com.wen_wen.latte.app.util.storage.LattePrefercnce;

/**
 * Created by WeLot on 2018/4/17.
 * 登录状态管理
 */

public class AccountManager {

    private enum SignTag {

        SIGN_TAG;
    }

    //设置登录状态
    public static void setSignState(boolean state) {
        LattePrefercnce.setAppFlag(SignTag.SIGN_TAG.name(), state);

    }

    private static boolean isSignIn() {

        return LattePrefercnce.getAppFlag(SignTag.SIGN_TAG.name());
    }

    //检查用户登录状态
    public static void checkAccount(IuserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }

    }


}
