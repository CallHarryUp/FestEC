package com.wen_wen.latte.ec.launcher.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.app.delegate.web.WebDelegateImpl;
import com.wen_wen.latte.ec.R;

/**
 * Created by WeLot on 2018/4/19.
 */

public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("http://www.baidu.com");

        getSupportDelegate().loadRootFragment(R.id.web_discover_container, delegate);
    }
}
