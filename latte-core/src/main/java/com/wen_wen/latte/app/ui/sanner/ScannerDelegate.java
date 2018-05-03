package com.wen_wen.latte.app.ui.sanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.util.callback.CallbackManager;
import com.wen_wen.latte.app.util.callback.CallbackType;
import com.wen_wen.latte.app.util.callback.IGlobalCllback;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by WeLot on 2018/5/3.
 */

public class ScannerDelegate extends LatteDelegate implements ZBarScannerView.ResultHandler {
    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        //回调监听
        mScanView.setResultHandler(this);

    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    //处理结果
    @Override
    public void handleResult(Result result) {
        Bundle bundle = new Bundle();
        bundle.putString("SCAN_RESULT", result.getContents());
        IGlobalCllback<String> callback = CallbackManager.getInstance()
                .getCallback(CallbackType.ON_SCAN);

        if (callback != null) {
            callback.executeCallback(result.getContents());
        }
        // setFragmentResult(RequestCodes.SCAN, bundle);
        getSupportDelegate().pop();
    }
}
