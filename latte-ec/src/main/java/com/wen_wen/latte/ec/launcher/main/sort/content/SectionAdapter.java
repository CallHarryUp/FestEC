package com.wen_wen.latte.ec.launcher.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.wen_wen.latte.ec.R;

import java.util.List;

/**
 * Created by WeLot on 2018/4/23.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    //头数据转化
    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    //商品数据转化
    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        //item.t 返回SectionBean类型
        final String thumb = item.t.getmGoodsThumb();
        final String name = item.t.getmGoodsName();
        final int goodsId = item.t.getmGoodsId();
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv,name);
        final AppCompatImageView  goodsImageView  =  helper.getView(R.id.iv);
        Picasso.with(mContext).load(thumb).into(goodsImageView);

    }
}
