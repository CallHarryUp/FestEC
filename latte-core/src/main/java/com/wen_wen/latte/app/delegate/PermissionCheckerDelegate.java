package com.wen_wen.latte.app.delegate;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.wen_wen.latte.app.ui.camera.CameraImageBean;
import com.wen_wen.latte.app.ui.camera.LatteCamera;
import com.wen_wen.latte.app.ui.camera.RequestCodes;
import com.wen_wen.latte.app.ui.sanner.ScannerDelegate;
import com.wen_wen.latte.app.util.callback.CallbackManager;
import com.wen_wen.latte.app.util.callback.CallbackType;
import com.wen_wen.latte.app.util.callback.IGlobalCllback;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by WeLot on 2018/4/12.
 * 没有吊起第三方库裁剪功能(后续完成)
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDegelate {

    //不是直接调用方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        LatteCamera.start(this);
    }

    //这个是真正调用的方法
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithCheck(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
            , @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.d("111", "resquestCode:" + requestCode);

            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                   /* //剪切：原始路径 剪切玩之后需要放置的路径
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400)
                            .start(getActivity(), this);
                    Log.d("111", "执行剪裁");*/
                    final IGlobalCllback<Uri> take_callback = CallbackManager
                            .getInstance()
                            .getCallback(CallbackType.ON_CROP);

                    if (take_callback != null) {
                        take_callback.executeCallback(resultUri);
                    }
                    break;
                case RequestCodes.PICK_PHOTO:

                    if (data != null) {
                        //从相册选择之后的原路径
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有一个路径存放剪裁过的图片  要存放的新路径
                        // final String pickCropPath = LatteCamera.createCropFile().getPath();
                       /* UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);
                        Log.d("111", "执行剪裁");*/
                        final IGlobalCllback<Uri> callback = CallbackManager
                                .getInstance()
                                .getCallback(CallbackType.ON_CROP);
                        if (callback != null) {
                            callback.executeCallback(pickPath);
                        }
                    }
                    break;
                case RequestCodes.CROP_PHOTO:

                   /* Log.d("111","剪裁图片");
                    //对剪裁图片的处理
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的数据进行处理
                    final IGlobalCllback<Uri> callback = CallbackManager
                            .getInstance()
                            .getCallback(CallbackType.ON_CROP);
                    if (callback!=null) {
                        callback.executeCallback(cropUri);
                    }*/
                    break;
                case RequestCodes.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    //扫描二维码(不直接调用)
    @NeedsPermission(Manifest.permission.CAMERA)
    void startScan(BaseDegelate degelate) {
        degelate.getSupportDelegate().startForResult(new ScannerDelegate(), RequestCodes.SCAN);

    }

    public void startScanWithChreck(BaseDegelate degelate) {
        PermissionCheckerDelegatePermissionsDispatcher.startScanWithCheck(this, degelate);
    }


    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        Toast.makeText(getContext(), "永久使用权限", Toast.LENGTH_SHORT).cancel();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {

        showRationaleDialog(request);

    }

    private void showRationaleDialog(final PermissionRequest request) {

        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }
/*
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.SCAN) {
            if (data != null) {
                String qrCode = data.getString("SCAN_RESULT");
                IGlobalCllback<String> callback = CallbackManager.getInstance()
                        .getCallback(CallbackType.ON_SCAN);

                if (callback != null) {
                    callback.executeCallback(qrCode);
                }

            }
        }
    }*/
}
