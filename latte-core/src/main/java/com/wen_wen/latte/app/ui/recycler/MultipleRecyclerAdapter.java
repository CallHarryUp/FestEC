package com.wen_wen.latte.app.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.squareup.picasso.Picasso;
import com.wen_wen.latte.R;
import com.wen_wen.latte.app.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeLot on 2018/4/20.
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<
        MulitipleItemEntity, MultipleViewHolder> implements
        BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {
    //确保初始化一次Banner，防止重复item加载
    private boolean mIsInitBanner = false;


    protected MultipleRecyclerAdapter(List<MulitipleItemEntity> data) {
        super(data);
        init();
    }

    //简单工厂
    public static MultipleRecyclerAdapter create(List<MulitipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConveter conveter) {

        return new MultipleRecyclerAdapter(conveter.convert());
    }

    //初始化布局
    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_mulitiple_text);
        addItemType(ItemType.TEXT, R.layout.item_mulitiple_image);
        addItemType(ItemType.TEXT, R.layout.item_mulitiple_image_text);
        addItemType(ItemType.TEXT, R.layout.item_mulitiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();//打开加载会有动画效果
        //多次执行执行动画
        isFirstOnly(false);

    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MulitipleItemEntity entry) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannersImages;

        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entry.getField(MulitipleFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entry.getField(MulitipleFields.IMAGE_URL);
                Picasso.with(mContext)
                        .load(imageUrl)
                        .centerCrop()
                        .into(((ImageView) holder.getView(R.id.img_single)));
                break;
            case ItemType.TEXT_IMAGE:
                text = entry.getField(MulitipleFields.TEXT);
                imageUrl = entry.getField(MulitipleFields.IMAGE_URL);
                Picasso.with(mContext)
                        .load(imageUrl)
                        .centerCrop()
                        .into(((ImageView) holder.getView(R.id.img_mulitiple)));
                holder.setText(R.id.tv_mulitiple, text);

                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannersImages = entry.getField(MulitipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannersImages, this);
                    mIsInitBanner = true;
                }

                break;
            default:
                break;
        }


    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MulitipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
