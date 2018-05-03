package com.wen_wen.latte.ec.launcher.main.personal.order.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.joanzapata.iconify.widget.IconTextView;
import com.squareup.picasso.Picasso;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeLot on 2018/5/2.
 * 照片选择控件
 */

public class AutoPhotoLayout extends LinearLayoutCompat {
    //添加图片数量
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
    //一行的view集合
    private List<View> mLineViews;
    private AlertDialog mTargetDialog;

    private static final String ICON_TEXT = "{fa-plus}";
    private float mIconSize = 0;
    private static final List<List<View>> ALL_VIEWS = new ArrayList<>();
    private static final List<Integer> LINE_HEIGHTS = new ArrayList<>();

    //防止多次的测量和布局过程
    private boolean mIsOnceInitOnMeasure = false;
    private boolean mHasInitOnLayout = false;

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
        mIconSize = typedArray.getDimension(R.styleable.camera_flow_layout_icon_size, 20);
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
        mTargetDialog.show();
        final Window window = mTargetDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_image_click_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            //添加参数
            window.setAttributes(params);
            window.findViewById(R.id.dialog_image_clicked_btn_delete).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //得到图片
                    final AppCompatImageView deleteImageView = ((AppCompatImageView) findViewById(mDeleteId));
                    //设置图片逐渐消失的动画
                    final AlphaAnimation animation = new AlphaAnimation(1, 0);
                    animation.setDuration(500);
                    //不重复
                    animation.setRepeatCount(0);
                    animation.setFillAfter(true);
                    //不等待
                    animation.setStartOffset(0);
                    deleteImageView.startAnimation(animation);
                    animation.start();
                    AutoPhotoLayout.this.removeView(deleteImageView);
                    mCurrentNum -= 1;
                    //当数目达到上限时 隐藏添加那妞 不足时显示
                    if (mCurrentNum > mMaxNum) {
                        mIconAdd.setVisibility(VISIBLE);
                    }
                    mTargetDialog.cancel();
                }
            });
            window.findViewById(R.id.dialog_image_clicked_btn_undetermined).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTargetDialog.cancel();
                }
            });

            window.findViewById(R.id.dialog_image_clicked_btn_cancel).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTargetDialog.cancel();
                }
            });
        }

    }

    public final void setDelegate(LatteDelegate delegate) {
        this.mDelegate = delegate;
    }


    public final void onCropTarget(Uri uri) {
        createNewImageView();
        Picasso.with(mDelegate.getContext())
                .load(uri)
                .into(mTargetImageView);


    }

    //创建新的 imageview
    private void createNewImageView() {
        mTargetImageView = new AppCompatImageView(getContext());
        mTargetImageView.setId(mCurrentNum);
        mTargetImageView.setLayoutParams(mParams);
        mTargetImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取眼删除的图片的id
                mDeleteId = v.getId();
                mTargetDialog.show();

            }
        });
        //添加子view的时候传入位置
        this.addView(mTargetImageView, mCurrentNum);
        mCurrentNum++;
        //当添加数目大于maxNum时  自动隐藏添加按钮
        if (mCurrentNum >= mMaxNum) {
            mIconAdd.setVisibility(GONE);
        }

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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        ALL_VIEWS.clear();
        LINE_HEIGHTS.clear();
        //当前viewGroup 的宽度
        final int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;

        if (!mHasInitOnLayout) {
            mLineViews = new ArrayList<>();
            mHasInitOnLayout = true;
        }
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            //如果需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin >
                    width - getPaddingLeft() - getPaddingRight()) {
                //记录lineHeight
                LINE_HEIGHTS.add(lineHeight);
                //记录当前一行的views
                ALL_VIEWS.add(mLineViews);
                //重置宽和高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                //重置view的集合
                mLineViews.clear();

            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, lineHeight + lp.topMargin + lp.bottomMargin);
            //添加view
            mLineViews.add(child);

        }
        //最后一行
        LINE_HEIGHTS.add(lineHeight);
        ALL_VIEWS.add(mLineViews);
        //设置子view的位置
        int left = getPaddingLeft();
        int top = getPaddingTop();
        //行数
        final int lineNum = ALL_VIEWS.size();
        for (int i = 0; i < lineNum; i++) {
            //当前行所有的view
            mLineViews = ALL_VIEWS.get(i);
            //每一行的高
            lineHeight = LINE_HEIGHTS.get(i);
            //循环每一行元素的集合
            int size = mLineViews.size();
            for (int j = 0; j < size; j++) {
                View child = mLineViews.get(j);
                //判断child的状态
                if (child.getVisibility() == GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                //设置子view的边距
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = child.getMeasuredWidth() - mImageMargin;
                int bc = tc + child.getMeasuredHeight();
                //为子view进行布局
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            //每换一行   高度增加
            top += lineHeight;


        }
        mIconAdd.setLayoutParams(mParams);
        //要进行重新布局
        mHasInitOnLayout = false;


    }
}
