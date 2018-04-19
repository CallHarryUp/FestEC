package com.wen_wen.latte.app.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.wen_wen.latte.R;
import com.wen_wen.latte.app.delegate.LatteDelegate;

/**
 * Created by WeLot on 2018/4/19.
 * 基于每一个字fragment的父类
 * 点击back键两次退出
 */

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {
    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;
    //fragment 问题：每次回到页面的时候 需要重新requestFocus 一次
    //
    @Override
    public void onResume() {
        super.onResume();
        View  rootView  =  getView();
        if (rootView!=null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }

            return true;
        }

        return false;
    }
}
