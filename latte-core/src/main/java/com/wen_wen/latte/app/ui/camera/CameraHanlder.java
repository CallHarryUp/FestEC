package com.wen_wen.latte.app.ui.camera;

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

import com.blankj.utilcode.util.FileUtils;
import com.wen_wen.latte.R;
import com.wen_wen.latte.app.delegate.PermissionCheckerDelegate;
import com.wen_wen.latte.app.util.FileUtil;

import java.io.File;

/**
 * Created by WeLot on 2018/4/26.
 */
//照片处理类
public class CameraHanlder implements View.OnClickListener {
    private final AlertDialog DIALOG;
    private final PermissionCheckerDelegate DELEGATE;

    public CameraHanlder(PermissionCheckerDelegate delegate) {

        this.DELEGATE = delegate;
        DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
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

    private void takePhoto() {
        final String currentPhonoName = getPhonoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //创建临时file
        final File tempFile = new File(FileUtil.CAMERA_PHOTO_DIR, currentPhonoName);

        //判断android版本   兼容7。0及以上  正确调用相机
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());
            final Uri uri = DELEGATE.getContext().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            //将Uri路径转化为实际路径
            final File realFile = FileUtils.
                    getFileByPath(FileUtil.getRealFilePath(DELEGATE.getContext(), uri));

            final Uri realUri = Uri.fromFile(realFile);
            CameraImageBean.getInstance().setPath(realUri);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            final Uri fileUrl = Uri.fromFile(tempFile);
            CameraImageBean.getInstance().setPath(fileUrl);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUrl);


        }
       /* //创建拍照存储的图片文件
        File tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(DELEGATE.getContext(), BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            CameraImageBean.getInstance().setPath(contentUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            Uri fileUri = Uri.fromFile(tempFile);
            CameraImageBean.getInstance().setPath(fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        }*/
        DELEGATE.startActivityForResult(intent, RequestCodes.TAKE_PHOTO);

    }

    //选择图片
    private void pickPhoto() {

        final Intent intent = new Intent();
        //类型是所有的image类型
        intent.setType("image/*");
        //获取内容
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //选择器
        DELEGATE.startActivityForResult(
                Intent.createChooser(intent, "选择获取图片方式"), RequestCodes.PICK_PHOTO);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photodialog_btn_cancel) {
            //alPay(mOrderId);
            DIALOG.cancel();

        } else if (id == R.id.photodialog_btn_native) {
            pickPhoto();
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_take) {
            takePhoto();
            DIALOG.cancel();
        }
    }
}
