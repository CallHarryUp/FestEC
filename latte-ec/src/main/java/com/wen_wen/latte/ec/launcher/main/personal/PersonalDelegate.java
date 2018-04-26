package com.wen_wen.latte.ec.launcher.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;
import com.wen_wen.latte.ec.launcher.main.personal.list.ListAdapter;
import com.wen_wen.latte.ec.launcher.main.personal.list.ListBean;
import com.wen_wen.latte.ec.launcher.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by WeLot on 2018/4/19.
 */

public class PersonalDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_personal_setting)
    RecyclerView  mRvSettings =  null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
       //收货地址
        ListBean  address  =  new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();

        //系统设置
        ListBean  system  =  new  ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        //s数据
        final List<ListBean>  data  =  new ArrayList<>();
        data.add(address);
        data.add(system);
        //设置recycler
        final LinearLayoutManager  manager  =  new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        ListAdapter  adapter  = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);


     }
}
