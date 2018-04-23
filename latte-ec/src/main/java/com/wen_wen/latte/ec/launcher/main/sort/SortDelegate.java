package com.wen_wen.latte.ec.launcher.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.launcher.main.sort.content.ContentDelegate;
import com.wen_wen.latte.ec.launcher.main.sort.list.VertialListDelegate;

/**
 * Created by WeLot on 2018/4/19.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    //进行初始化 只有当打开此delegate时才会加载
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VertialListDelegate listDelegate = new VertialListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示 ，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container,ContentDelegate.newInstance(1));




    }
}
