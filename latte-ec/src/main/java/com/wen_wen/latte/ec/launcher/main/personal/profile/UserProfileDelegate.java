package com.wen_wen.latte.ec.launcher.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;
import com.wen_wen.latte.ec.launcher.main.personal.list.ListAdapter;
import com.wen_wen.latte.ec.launcher.main.personal.list.ListBean;
import com.wen_wen.latte.ec.launcher.main.personal.list.ListItemType;
import com.wen_wen.latte.ec.launcher.main.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by WeLot on 2018/4/26.
 * 个人信息
 */

public class UserProfileDelegate extends LatteDelegate{
    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //头像
        ListBean imgge = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();

        //姓名
        ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new NameDelegate())
                .setText("姓名")
                .setVaule("未设置姓名")
                .build();

        //性别
        ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setVaule("未设置性别")
                .build();
        //生日
        ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setVaule("未设置生日")
                .build();

        //数据
        final List<ListBean> data = new ArrayList<>();
        data.add(imgge);
        data.add(name);
        data.add(gender);
        data.add(birth);

        //设置recycler
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
