package com.wen_wen.latte.ec.launcher.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by WeLot on 2018/4/23.
 */

public class ContentDelegate extends LatteDelegate {
    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView;
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mCOntentId = -1;

    private List<SectionBean> mData;


    public static ContentDelegate newInstance(int contentId) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(bundle);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mCOntentId = arguments.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delagate_content;
    }

    //初始化数据
    private void initData() {
        RestClient.builder()
                .url(" ?contentId  = " + mCOntentId)
                .success(new ISuccess() {


                    @Override
                    public void OnSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter  =  new SectionAdapter(R.layout.item_section_content,
                                R.layout.item_section_header,mData);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        })
                .build()
                .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
       // initData();

    }
}
