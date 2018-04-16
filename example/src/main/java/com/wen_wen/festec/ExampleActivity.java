package com.wen_wen.festec;

import android.os.Bundle;

import com.wen_wen.latte.app.activities.ProxyActivity;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.launcher.sign.SignUpDelegate;


public class ExampleActivity extends ProxyActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final android.support.v7.app.ActionBar actionBar  =  getSupportActionBar();
        if (actionBar!=null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }
}
