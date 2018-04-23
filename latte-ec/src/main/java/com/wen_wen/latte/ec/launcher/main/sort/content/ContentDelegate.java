package com.wen_wen.latte.ec.launcher.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.R;

/**
 * Created by WeLot on 2018/4/23.
 */

public class ContentDelegate extends LatteDelegate {
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mCOntentId = -1;


    public static ContentDelegate newInstance(int contentId) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mCOntentId = arguments.getInt(ARG_CONTENT_ID);
        }
    }
    @Override
    public Object setLayout() {
        return R.layout.delagate_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
