package com.wen_wen.latte.ec.launcher.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;

import butterknife.BindView;

/**
 * Created by WeLot on 2018/4/19.
 */

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    @Override
    public Object setLayout() {
        return R.layout.deleate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
