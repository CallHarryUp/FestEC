package com.wen_wen.latte.ec.launcher.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by WeLot on 2018/4/16.
 *  使用sharedperfrence 是不合适的，因为可以从终端取出来  明文显示的
 */

public class DatabaseManager {


    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    public DatabaseManager() {
    }

    public DatabaseManager init(Context context) {

        initDao(context);

        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {

        return Holder.INSTANCE;
    }


    private void initDao(Context context) {

        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec_db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
