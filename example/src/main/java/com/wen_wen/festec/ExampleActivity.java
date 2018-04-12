package com.wen_wen.festec;

import com.wen_wen.festec.example.ExampleDelegate;
import com.wen_wen.latte.app.activities.ProxyActivity;
import com.wen_wen.latte.app.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
