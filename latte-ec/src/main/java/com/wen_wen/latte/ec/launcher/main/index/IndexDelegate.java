package com.wen_wen.latte.ec.launcher.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wen_wen.latte.app.bottom.BottomItemDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.IError;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.ui.recycler.BaseDecoration;
import com.wen_wen.latte.app.ui.recycler.MulitipleItemEntity;
import com.wen_wen.latte.app.ui.refresh.PagingBean;
import com.wen_wen.latte.app.ui.refresh.RefreshHanlder;
import com.wen_wen.latte.app.util.callback.CallbackManager;
import com.wen_wen.latte.app.util.callback.CallbackType;
import com.wen_wen.latte.app.util.callback.IGlobalCllback;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.R2;
import com.wen_wen.latte.ec.launcher.main.EcBottomDelegate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WeLot on 2018/4/19.
 */

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;
    @BindView(R2.id.icon_index_scan)
    ImageView mIconScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHanlder mRefreshHanlder;

    //二维码扫描
    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithChreck(this.getParentDelegate());
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        // initRefreshLayout();
        mRefreshHanlder = new RefreshHanlder(mRefreshLayout, mRecyclerView, new IndexDataConverter(), new PagingBean());
        RestClient.builder()
                .url("userInfo/getJson/")
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {
                        IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);
                        ArrayList<MulitipleItemEntity> list = converter.convert();
                        //String image = list.get(1).getField(MulitipleFields.IMAGE_URL);

                        //Log.d("111","image:"+image);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d("111", "失败");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d("111", "error :" + msg);
                    }
                })
                .build()
                .get();

       /* String json = FileUtil.getRawFile(R.raw.text);
        IndexDataConverter  converter  =  new IndexDataConverter();
        converter.setJsonData(json);
        ArrayList<MulitipleItemEntity> list = converter.convert();
        String image = list.get(1).getField(MulitipleFields.IMAGE_URL);
*/
        //   Log.d("111","image:"+json);

        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCllback<String>() {
                    @Override
                    public void executeCallback(String qrcode) {
                        Toast.makeText(getContext(),qrcode,Toast.LENGTH_SHORT).show();

                    }
                });

    }

    //初始化swipeRegresh
    private void initRefreshLayout() {

        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        //球由小变大 起始高度 终止高度
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    //初始化recyclerview布局
    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        //点击事件  寻找父delegate，如果不 ，那么底部bottombar不会消失
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        //mRefreshHanlder.firstPage("index.php");
        // initRecyclerView();
    }

    @Override
    public Object setLayout() {
        return R.layout.deleate_index;
    }


}
