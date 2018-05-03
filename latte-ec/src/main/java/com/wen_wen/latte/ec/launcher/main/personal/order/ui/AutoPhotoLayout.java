package com.wen_wen.latte.ec.launcher.main.personal.order.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.R;

import java.util.ArrayList;
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

    private static final String ICON_TEXT = "{fa-plis}";
    private float mIconSize = 0;
    private static final List<List<View>> ALL_VIEWS = new ArrayList<>();
    private static final List<Integer> LINE_HEIGHTS = new ArrayList<>();

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //从xml文件中获取参数
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.camera_flow_layout);
        mMaxNum = typedArray.getInt(R.styleable.camera_flow_layout_max_count, 1);
        mMaxLineNum = typedArray.getInt(R.styleable.camera_flow_layout_line_count, 3);
        mImageMargin = typedArray.getInt(R.styleable.camera_flow_layout_item_margin, 0);
        mIconSize = typedArray.getInt(R.styleable.camera_flow_layout_icon_size, 20);
        typedArray.recycle();




    }


}
