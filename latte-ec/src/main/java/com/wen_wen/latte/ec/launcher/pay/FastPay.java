package com.wen_wen.latte.ec.launcher.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.R;

/**
 * Created by WeLot on 2018/4/25.
 */

public class FastPay implements View.OnClickListener {

    //设置支付回调监听
    private IAlPayResultListener mIAlPayResultListener;

    private Activity mActivity;

    private AlertDialog mDialog = null;

    public FastPay(LatteDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(LatteDelegate delegate) {
        return new FastPay(delegate);
    }

    public void beginPayDialog() {
        mDialog.show();
        Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
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
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);

        }
    }
    //libary 中不能使用switch  没有常量
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dialog_pay_alpay) {
            mDialog.cancel();

        } else if (id == R.id.btn_dialog_pay_wechat) {
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();
        }

    }
}
