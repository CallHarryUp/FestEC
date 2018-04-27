package com.wen_wen.latte.app.ui.camera;

import android.net.Uri;

import com.wen_wen.latte.app.delegate.PermissionCheckerDelegate;
import com.wen_wen.latte.app.util.FileUtil;

/**
 * Created by WeLot on 2018/4/26.
 */
//照相机调用类
public class LatteCamera {

    public static Uri createCropFile() {

        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "JPOG")).getPath());
    }


    public static void start(PermissionCheckerDelegate delegate) {

        new CameraHanlder(delegate).beginCameraDialog();
    }

}
