package com.wen_wen.latte.ec.launcher.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wen_wen.latte.ec.launcher.database.DatabaseManager;
import com.wen_wen.latte.ec.launcher.database.UserProfile;



/**
 * Created by WeLot on 2018/4/17.
 */

public class SignHanlder {

    public static void onSingnUp(String respone) {
        final JSONObject profileJson = JSON.parseObject(respone).getJSONObject("data");

        long userId = profileJson.getLong("userId");
        String name = profileJson.getString("name");
        String avatar = profileJson.getString("avatar");
        String gender = profileJson.getString("gender");
        String address = profileJson.getString("address");

        UserProfile profile = new UserProfile(userId, name, avatar, gender, address);

        DatabaseManager.getInstance().getDao().insert(profile);
    }

}
