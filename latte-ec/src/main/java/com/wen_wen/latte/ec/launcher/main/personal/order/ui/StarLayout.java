package com.wen_wen.latte.ec.launcher.main.personal.order.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.iconify.widget.IconTextView;
import com.wen_wen.latte.ec.R;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/5/2.
 */

public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {
    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    private static final CharSequence ICON_SELECT = "{fa-star}";
    private static final int STAR_TOTAL_COUNT = 5;
    private static final ArrayList<IconTextView> STARS = new ArrayList<>();


    public StarLayout(Context context) {
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    private void initStarIcon() {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            star.setLayoutParams(lp);
            star.setText(ICON_UN_SELECT);
            //设置id
            star.setTag(R.id.star_count, i);
            star.setTag(R.id.star_is_select, false);
            //点击事件
            star.setOnClickListener(this);
            STARS.add(star);
            this.addView(star);
        }
    }

    //点击星星
    private void selectStar(int count) {
        for (int i = 0; i <= count; i++) {
            if (i <= count) {
                IconTextView star = STARS.get(i);
                star.setText(ICON_SELECT);
                star.setTextColor(Color.RED);
                star.setTag(R.id.star_is_select, true);
            }
        }

    }

    private void unSelectStar(int count) {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            if (i >= count) {
                IconTextView star = STARS.get(i);
                star.setText(ICON_UN_SELECT);
                star.setTextColor(Color.GRAY);
                star.setTag(R.id.star_is_select, false);
            }
        }

    }
    //获取选择星星数量
    public   int  getStarCount(){
        int count  = 0;
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            IconTextView star = STARS.get(i);
            final boolean isSelect = ((Boolean) star.getTag(R.id.star_is_select));
            if (isSelect) {
                count++;
            }
        }
        return   count;
    }

    @Override
    public void onClick(View v) {
        IconTextView star = (IconTextView) v;
        //获取第几个星星
        int count = (int) star.getTag(R.id.star_count);
        //获取是否点击状态
        final boolean isSelect = ((Boolean) star.getTag(R.id.star_is_select));
        if (!isSelect) {
            selectStar(count);
        } else {
            unSelectStar(count);
        }

    }
}
