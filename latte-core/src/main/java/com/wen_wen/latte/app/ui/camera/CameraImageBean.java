package com.wen_wen.latte.app.ui.camera;

import android.net.Uri;

/**
 * Created by WeLot on 2018/4/26.
 */
//存储中间值
public final class CameraImageBean {

    private Uri  mPath   ;

    private  static   final  CameraImageBean  INSTANCE =  new CameraImageBean();

    public   static   CameraImageBean  getInstance(){
        return    INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
