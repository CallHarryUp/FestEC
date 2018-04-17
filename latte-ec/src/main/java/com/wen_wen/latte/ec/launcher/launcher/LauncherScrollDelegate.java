package com.wen_wen.latte.ec.launcher.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.wen_wen.latte.app.app.AccountManager;
import com.wen_wen.latte.app.app.IuserChecker;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.ui.launcher.IlauncherListener;
import com.wen_wen.latte.app.ui.launcher.LauncherHolderCreator;
import com.wen_wen.latte.app.ui.launcher.OnLauncherFinishTag;
import com.wen_wen.latte.app.ui.launcher.ScrollLauncherTag;
import com.wen_wen.latte.app.util.storage.LattePrefercnce;
import com.wen_wen.latte.ec.R;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/16.
 * 轮播图
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private IlauncherListener mILauncherListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IlauncherListener) {
            mILauncherListener = (IlauncherListener) activity;
        }
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        //初始化循环
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
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

    /**
     * 判断是不是第一次启动
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1) {//最后一张图片
            LattePrefercnce.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);

            Toast.makeText(getContext(), "点击最后一张", Toast.LENGTH_SHORT).show();
            //检查用户是否登录
            AccountManager.checkAccount(new IuserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SINNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });


        }
    }
}
