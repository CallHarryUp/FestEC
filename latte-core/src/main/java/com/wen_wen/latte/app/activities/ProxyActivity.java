package com.wen_wen.latte.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.wen_wen.latte.R;
import com.wen_wen.latte.app.delegate.LatteDelegate;



/**
 * Created by WeLot on 2018/4/12.
 */

public abstract class ProxyActivity extends me.yokeyword.fragmentation.SupportActivity {


    public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        //指定id
        container.setId(R.id.delegate_container);
        setContentView(container);
        //加载 fragment  这是开源框架的方法
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    //垃圾回收  单activity模式
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
