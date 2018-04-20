package com.wen_wen.latte.app.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.squareup.picasso.Picasso;

/**
 * Created by WeLot on 2018/4/20.
 */

public class ImageHolder implements Holder<String> {
    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Picasso.with(context)
                .load(data)
                .centerCrop()
                .into(mImageView);
    }
}
