package com.wen_wen.latte.ec.launcher.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.launcher.main.personal.list.ListBean;

/**
 * Created by WeLot on 2018/4/27.
 */

public class PersionalClickListener extends SimpleClickListener {
    private final LatteDelegate DELEGATE;

    public PersionalClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = ((ListBean) baseQuickAdapter.getData().get(position));
        int id = bean.getmId();
        switch (id) {
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getmDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}