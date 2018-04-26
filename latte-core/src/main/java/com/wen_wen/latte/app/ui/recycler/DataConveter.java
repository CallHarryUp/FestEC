package com.wen_wen.latte.app.ui.recycler;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/20.
 * 数据解析约束基类
 */

public abstract class DataConveter {

    protected final ArrayList<MulitipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsondata = null;

    public abstract ArrayList<MulitipleItemEntity> convert();

    public DataConveter setJsonData(String json) {

        this.mJsondata = json;
        return this;
    }

    protected String getJosnData() {

        if (mJsondata == null || mJsondata.isEmpty()) {
            throw new NullPointerException("DATA  IS  NULL");
        }
        Log.d("111","jsonData:"+mJsondata);
        return mJsondata;
    }

    public void clearData(){
        ENTITIES.clear();
    }

}
