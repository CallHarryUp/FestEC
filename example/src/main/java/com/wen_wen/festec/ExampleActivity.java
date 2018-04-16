package com.wen_wen.festec;

import com.wen_wen.latte.app.activities.ProxyActivity;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.launcher.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }
}
