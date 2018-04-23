package com.wen_wen.latte.ec.launcher.detall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by WeLot on 2018/4/23.
 * 商品详情页
 */

public class GoodsDetailDelegate extends LatteDelegate {
    public static GoodsDetailDelegate create() {

        return new GoodsDetailDelegate();
    }


    @Override
    public Object setLayout() {
        return R.layout.delagate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    //进入时动画  水平进入
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
