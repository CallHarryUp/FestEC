package com.wen_wen.latte.app.ui.loader;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;
import com.wen_wen.latte.R;

import java.util.ArrayList;

/**
 * Created by WeLot on 2018/4/13.
 * 显示loading 类
 * 使用dialog进行显示loading
 */

public class LatteLoader {
    //缩放比例
    private static final int LOADER_SIZE_SCALE = 8;
    //偏移量
    private static final int LOADER_OFFSET_SCALE = 10;
    //创建l管理oading的集合
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    //创建默认的加载样式
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();


    //重载 传入枚举类型
    public static void showLoading(Context context, Enum<LoaderStyle> style) {
        showLoading(context, style.name());
    }


    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        //创建loadingview
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreater.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);//加入到根视图


        //控制一下宽和高
        int deviceWidth;
        int deviceHeight;
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        deviceWidth = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;

        final Window dialogWindow = dialog.getWindow();
        //获取布局参数 进行参数的修改 使dialog居中显示
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        //添加
        LOADERS.add(dialog);
        //默认显示
        dialog.show();


    }

    //重载
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    //停止加载
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();//取消  会执行 cancel的回调
                    //  dialog.dismiss();//只是消失
                }
            }
        }
    }


}
