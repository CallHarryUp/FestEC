package com.wen_wen.latte.ec.launcher.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.ui.recycler.ItemType;
import com.wen_wen.latte.app.ui.recycler.MulitipleFields;
import com.wen_wen.latte.app.ui.recycler.MultiipleItemEntity;
import com.wen_wen.latte.app.ui.recycler.MultipleRecyclerAdapter;
import com.wen_wen.latte.app.ui.recycler.MultipleViewHolder;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.launcher.main.sort.SortDelegate;
import com.wen_wen.latte.ec.launcher.main.sort.content.ContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by WeLot on 2018/4/23.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {
    //传入delegate 控制左右的delegate的关联关系
    private final SortDelegate DELEGATE;
    //上一个位置
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultiipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);


    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultiipleItemEntity entry) {
        super.convert(holder, entry);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entry.getField(MulitipleFields.TEXT);
                final boolean isClicked = entry.getField(MulitipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                //点击切换状态
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MulitipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);
                            //更新选中的item
                            entry.setField(MulitipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;
                            //内容的id
                            final int currentId = getData().get(currentPosition).getField(MulitipleFields.ID);
                            showContent(currentId);
                        }

                    }
                });
                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    //显示右侧content
    private void showContent(int contentId) {
        //每次点击创建新的delegate
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);

    }

    private void switchContent(ContentDelegate delegate) {
        final LatteDelegate contentDelegate = SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);

        }

    }

}
