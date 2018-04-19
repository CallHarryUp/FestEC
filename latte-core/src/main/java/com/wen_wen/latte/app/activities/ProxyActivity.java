package com.wen_wen.latte.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;

import com.wen_wen.latte.R;
import com.wen_wen.latte.app.delegate.LatteDelegate;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


/**
 * Created by WeLot on 2018/4/12.
 */

public abstract class ProxyActivity extends AppCompatActivity  implements ISupportActivity {
    private  final SupportActivityDelegate DELEGATE   =  new SupportActivityDelegate(this);

    public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        //指定id
        container.setId(R.id.delegate_container);
        setContentView(container);
        //加载 fragment  这是开源框架的方法
        if (savedInstanceState == null) {
            DELEGATE.loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    //垃圾回收  单activity模式
    @Override
    protected void onDestroy() {
        DELEGATE.onDestroy();
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public void onBackPressedSupport() {
        DELEGATE.onBackPressedSupport();
    }

    @Override
    public void onBackPressed() {
        DELEGATE.onBackPressed();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
