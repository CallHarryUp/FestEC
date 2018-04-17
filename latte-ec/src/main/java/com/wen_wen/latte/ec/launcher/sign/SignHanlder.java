package com.wen_wen.latte.ec.launcher.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wen_wen.latte.app.app.AccountManager;
import com.wen_wen.latte.ec.launcher.database.DatabaseManager;
import com.wen_wen.latte.ec.launcher.database.UserProfile;


/**
 * Created by WeLot on 2018/4/17.
 */

public class SignHanlder {
    private static void setUserProfile(String respone) {
        UserProfile profile = null;
        if (!respone.isEmpty()) {
            final JSONObject profileJson = JSON.parseObject(respone).getJSONObject("data");

            long userId = profileJson.getLong("userId");
            String name = profileJson.getString("name");
            String avatar = profileJson.getString("avatar");
            String gender = profileJson.getString("gender");
            String address = profileJson.getString("address");
            profile = new UserProfile(userId, name, avatar, gender, address);
            DatabaseManager.getInstance().getDao().insert(profile);

        }

    }

    public static void onSingnUp(String respone, ISignListener listener) {
        setUserProfile(respone);
        //保存注册登录成功  状态
        AccountManager.setSignState(true);
        listener.onSignUpSuccess();

    }


    public static void onSingnIn(String respone, ISignListener listener) {
        setUserProfile(respone);
        //保存注册登录成功  状态
        AccountManager.setSignState(true);
        listener.onSignInSuccess();

    }


}
