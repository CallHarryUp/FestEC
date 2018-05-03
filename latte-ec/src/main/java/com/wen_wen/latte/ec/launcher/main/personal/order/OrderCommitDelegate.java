package com.wen_wen.latte.ec.launcher.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.util.callback.CallbackManager;
import com.wen_wen.latte.app.util.callback.CallbackType;
import com.wen_wen.latte.app.util.callback.IGlobalCllback;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;
import com.wen_wen.latte.ec.launcher.main.personal.order.ui.AutoPhotoLayout;
import com.wen_wen.latte.ec.launcher.main.personal.order.ui.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WeLot on 2018/5/2.
 */

public class OrderCommitDelegate extends LatteDelegate {
    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout;
    @BindView(R2.id.top_tv_comment_commit)
    AppCompatTextView mTvSubmit;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout;


    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), mStarLayout.getStarCount() + "个星星数量", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCllback<Uri>() {
                    @Override
                    public void executeCallback(Uri uri) {
                        mAutoPhotoLayout.onCropTarget(uri);
                    }
                });
    }
}
