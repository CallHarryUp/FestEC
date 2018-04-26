package com.wen_wen.latte.ec.launcher.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wen_wen.latte.ec.R;

import java.util.List;

/**
 * Created by WeLot on 2018/4/26.
 *
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_value, item.getmValue());
                break;
            default:
                break;
        }
    }
}
