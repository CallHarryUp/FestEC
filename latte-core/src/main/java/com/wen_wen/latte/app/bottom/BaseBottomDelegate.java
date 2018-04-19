package com.wen_wen.latte.app.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.wen_wen.latte.R;
import com.wen_wen.latte.R2;
import com.wen_wen.latte.app.delegate.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by WeLot on 2018/4/19.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final ArrayList<BottonTabBean> TAB_BEANS = new ArrayList<>();
    private final LinkedHashMap<BottonTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickColor = Color.RED;
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottombar;

    //抽象方法  子类继承
    public abstract LinkedHashMap<BottonTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();


    @Override
    public Object setLayout() {
        return R.layout.delagate_bottom;
    }

    @ColorInt
    public abstract int setClieckedColor();

    //初始化操作
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化
        mIndexDelegate = setIndexDelegate();
        if (setClieckedColor() != 0) {
            mClickColor = setClieckedColor();
        }
        //上面的抽象方法 是自雷需要继承的 然后早父类而完成相应的赋值初始化操作
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottonTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottonTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            BottonTabBean key = item.getKey();
            BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_text_layout, mBottombar);

            RelativeLayout item = (RelativeLayout) mBottombar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            BottonTabBean bottomTabBean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bottomTabBean.getIcon());
            itemTitle.setText(bottomTabBean.getTitle());
            //如果是我们展示的delegate
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
            //转换数组
            final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);

            loadMultipleRootFragment(R.id.bottom_delagate_container, mIndexDelegate, delegateArray);


        }
    }

    //将其他tab重置
    private void resetColor() {
        int count = mBottombar.getChildCount();
        for (int i = 0; i < count; i++) {
            RelativeLayout item = (RelativeLayout) this.mBottombar.getChildAt(i);
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            itemIcon.setTextColor(Color.GRAY);
            AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }


    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        resetColor();
        RelativeLayout item = (RelativeLayout) v;
        IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickColor);
        AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickColor);
        //第一个需要显示  第二个 需要隐藏
        showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //重置位置
        mCurrentDelegate = tag;
    }
}
