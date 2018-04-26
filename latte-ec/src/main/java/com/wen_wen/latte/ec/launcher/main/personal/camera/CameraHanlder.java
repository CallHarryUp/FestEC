package com.wen_wen.latte.ec.launcher.main.personal.camera;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wen_wen.latte.app.delegate.PermissionCheckerDelegate;
import com.wen_wen.latte.app.util.FileUtil;
import com.wen_wen.latte.ec.R;

import java.io.File;

/**
 * Created by WeLot on 2018/4/26.
 */
//照片处理类
public class CameraHanlder implements View.OnClickListener {
    private final AlertDialog DIALOG;
    private final PermissionCheckerDelegate DELEGATE;

    public CameraHanlder(AlertDialog dialog, PermissionCheckerDelegate delegate) {
        this.DIALOG = dialog;
        this.DELEGATE = delegate;
    }

    final void beginCameraDialog() {
        DIALOG.show();
        Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            //添加动画
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            //设置透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            //获取原有属性
            final WindowManager.LayoutParams params = window.getAttributes();
            //调整属性
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);
            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);

        }
    }

    private String getPhonoName() {

        return FileUtil.getFileNameByTime("IMG", "jpg");
    }

    private void takePhone() {
        final String currentPhonoName = getPhonoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //创建临时file
        final File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR,currentPhonoName);

        //判断android版本   兼容7。0及以上
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
            final ContentValues  contentValues   =  new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA,tempFile.getPath());
            final Uri  uri  =  DELEGATE.getContext().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
            //将路径转化为实际路径
          //  final  File  realFile  =  FileUtil.getRealFilePath(DELEGATE.getContext(),uri);


        }

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photodialog_btn_cancel) {
            //alPay(mOrderId);
            DIALOG.cancel();

        } else if (id == R.id.photodialog_btn_native) {
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_take) {
            DIALOG.cancel();
        }
    }
}
