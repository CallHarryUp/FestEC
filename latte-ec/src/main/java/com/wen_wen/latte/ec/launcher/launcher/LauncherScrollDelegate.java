package com.wen_wen.latte.ec.launcher.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.ui.launcher.LauncherHolderCreator;
import com.wen_wen.latte.ec.R;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/16.
 * 轮播图
 */

public class LauncherScrollDelegate extends LatteDelegate {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        //初始化循环
        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setCanLoop(false);

    }

    @Override
    public Object setLayout() {
        //因为只有一个视图  所有只需要一个view
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
         initBanner();
    }
}
