package com.wen_wen.latte.ec.launcher.main.personal.data;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by WeLot on 2018/4/26.
 */

public class DateDialogUtil {

    public interface IDateListener {
        void onDateChange(String date);
    }

    private IDateListener mDateListener = null;

    public void setmDateListener(IDateListener listener) {
        this.mDateListener = listener;
    }

    public void showDialog(Context context) {
        final LinearLayout ll = new LinearLayout(context);
        //日期选择控件
        final DatePicker picker = new DatePicker(context);
        //获取布局参数
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

        ll.setLayoutParams(lp);
        //初始化日期选择时间
        picker.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //格式化日期
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                final String data = format.format(calendar.getTime());
                if (mDateListener != null) {
                    mDateListener.onDateChange(data);
                }
            }
        });
        ll.addView(picker);
        //显示对话框
        new AlertDialog.Builder(context)
               // .setTitle("选择日期")
                .setView(ll)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
