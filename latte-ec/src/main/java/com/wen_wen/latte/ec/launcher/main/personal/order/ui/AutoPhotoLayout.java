package com.wen_wen.latte.ec.launcher.main.personal.order.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.wen_wen.latte.app.delegate.LatteDelegate;

import java.util.List;

/**
 * Created by WeLot on 2018/5/2.
 * 照片选择控件
 */

public class AutoPhotoLayout extends LinearLayoutCompat {
    private int mCurrentNum = 0;
    private int mMaxNum = 0;
    private int mMaxLineNum = 0;
    private IconTextView mIconAdd;
    private LayoutParams mParams;
    //删除额图片id
    private int mDeleteId = 0;
    private AppCompatImageView mTargetImageView;
    //图片间隙
    private int mImageMargin = 0;
    private LatteDelegate mDelegate;

    private List<View> mLineViews;
    private AlertDialog mTargetDialog;



    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
