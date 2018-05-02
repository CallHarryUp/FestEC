package com.wen_wen.latte.ec.launcher.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.wen_wen.latte.app.ui.recycler.RgbValue;
import com.wen_wen.latte.ec.R;

/**
 * Created by WeLot on 2018/4/23.
 * 泛型为需要控制的view
 */
@SuppressWarnings("unused")
public class TransluncentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速率
    private static final int SHOW_SPEED = 3;

    //定义变化颜色
    private final RgbValue RGB_VALUE = RgbValue.create(255, 124, 2);


    //实例化是必须要有构造方法的
    public TransluncentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //被依赖的view 即recyclerView
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {

        return true;
    }

    //处理具体逻辑
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout,
                               Toolbar child,
                               View target,
                               int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout,
                child,
                target,
                dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed);
        //增加滑动距离
        mDistanceY += dyConsumed;
        //toolBar的高度
        final int targetHeight = child.getBottom();
        //当滑动是，并且小于toolbar的高度的时候，调整渐变色
        if (mDistanceY > 0 && mDistanceY <= targetHeight) {
            final float scale = ((float) (mDistanceY / targetHeight));
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb(((int) alpha),RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));

        }else  if(mDistanceY>targetHeight) {
             child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }


    }
}
