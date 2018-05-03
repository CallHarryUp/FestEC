package com.wen_wen.latte.ec.launcher.main.personal.order.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
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

    //防止多次的测量和布局过程
    private boolean mIsOnceInitOnMeasure = false;

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


    //加号按钮
    private void initAddIcon() {
        mIconAdd = new IconTextView(getContext());
        mIconAdd.setText(ICON_TEXT);
        mIconAdd.setGravity(Gravity.CENTER);
        mIconAdd.setBackgroundColor(Color.WHITE);
        mIconAdd.setTextSize(mIconSize);
        mIconAdd.setBackgroundResource(R.drawable.border_text);
        //点击事件
        mIconAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.startCameraWithCheck();
            }
        });
        this.addView(mIconAdd);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initAddIcon();
        mTargetDialog = new AlertDialog.Builder(getContext()).create();

    }

    //测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWdith = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //wrap_content
        int width = 0;
        int height = 0;
        //记录每一行的宽度与高度
        int lineWidth = 0;
        int lineHeight = 0;
        //得到内部元素个数
        int cCount = getChildCount();
        //循环
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(1);
            //测量子view的宽 高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //搭配layoutParams
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //子view占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            //子view占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //换行
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                //对比得到最大宽度
                width = Math.max(width, lineWidth);
                //重置lineWidth
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                //未换行  叠加行宽
                lineWidth += childWidth;
                //得到当前最大高度
                lineHeight = Math.max(lineHeight, childHeight);

            }
            //最后一个字控件
            if (i == cCount - 1) {
                width = Math.max(lineHeight, width);
                //判断是否超过最大拍照限制
                height += lineHeight;


            }

        }
        setMeasuredDimension(
                modeWdith == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()

        );
        //设置一行所有图片的宽高
        final int imageSideLen = sizeWidth / mMaxNum;
        //只初始化一次
        if (!mIsOnceInitOnMeasure) {
            mParams = new LayoutParams(imageSideLen, imageSideLen);
            mIsOnceInitOnMeasure = true;
        }

    }
}
