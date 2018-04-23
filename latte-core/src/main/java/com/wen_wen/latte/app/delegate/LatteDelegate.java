package com.wen_wen.latte.app.delegate;

/**
 * Created by WeLot on 2018/4/12.
 */

public abstract class LatteDelegate extends  PermissionCheckerDelegate{
    //查找父delegate
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
