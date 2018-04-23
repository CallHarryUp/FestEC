package com.wen_wen.latte.ec.launcher.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.ui.recycler.MultiipleItemEntity;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;
import com.wen_wen.latte.ec.launcher.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by WeLot on 2018/4/23.
 */

public class VertialListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_vertical_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);

    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    //数据处理
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("userInfo/getJson")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {
                        final List<MultiipleItemEntity> data = new VerticalListDataConverter()
                                .setJsonData(response).convert();
                        Log.d("111","data size :"+data.size());
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);

                        // Log.d("111","data:"+response.toString());
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d("111","失败");
                    }
                })
                .build()
                .get();
    }
}
