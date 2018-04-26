package com.wen_wen.latte.ec.launcher.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.ui.recycler.MulitipleItemEntity;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;
import com.wen_wen.latte.ec.launcher.main.personal.PersonalDelegate;
import com.wen_wen.latte.ec.launcher.main.personal.list.OrderListAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by WeLot on 2018/4/26.
 */

public class OrderListDelegate extends LatteDelegate {
    private String mType = null;
    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list")
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {
                        //加载recycler
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MulitipleItemEntity> data =
                                new OrderListDataConveter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);

                    }
                })
                .build()
                .get();
    }
}
