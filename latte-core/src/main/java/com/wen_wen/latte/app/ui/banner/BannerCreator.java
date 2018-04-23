package com.wen_wen.latte.app.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.wen_wen.latte.R;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/20.
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner
            , ArrayList<String> banners, OnItemClickListener listener) {
        convenientBanner
                .setPages(new HolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setOnItemClickListener(listener)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);


    }

}
