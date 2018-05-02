package com.wen_wen.latte.ec.launcher.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.squareup.picasso.Picasso;
import com.wen_wen.latte.app.delegate.LatteDelegate;
import com.wen_wen.latte.app.net.RestClient;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.util.callback.CallbackManager;
import com.wen_wen.latte.app.util.callback.CallbackType;
import com.wen_wen.latte.app.util.callback.IGlobalCllback;
import com.wen_wen.latte.ec.R;
import com.wen_wen.latte.ec.launcher.main.personal.data.DateDialogUtil;
import com.wen_wen.latte.ec.launcher.main.personal.list.ListBean;

/**
 * Created by WeLot on 2018/4/26.
 */

public class UserProfileClickListener extends SimpleClickListener {
    private final LatteDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};


    public UserProfileClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    //数据驱动UI
    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getmId();
        switch (id) {
            case 1:
                //开始照相机  或选择图片
                DELEGATE.startCameraWithCheck();
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCllback<Uri>() {
                            @Override
                            public void executeCallback(Uri args) {
                                Log.d("111", "agrs:" + args);
                                final ImageView avatar = ((ImageView) view.findViewById(R.id.img_arrow_avatar));
                                Picasso.with(DELEGATE.getContext())
                                        .load(args.getPath())
                                        .into(avatar);
                                //上传
                                RestClient.builder()
                                        .url("")
                                        .loader(DELEGATE.getContext())
                                        .file(args.getPath())
                                        .success(new ISuccess() {
                                            @Override
                                            public void OnSuccess(String response) {
                                                //更新用户信息  然后更新本地数据库
                                                // 或者每次打开app都请求api  不保存本地数据

                                            }
                                        })
                                        .build()
                                        .upload();
                            }

                        });

                break;
            case 2:
                final LatteDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = ((TextView) view.findViewById(R.id.tv_arrow_value));
                        textView.setText(mGenders[which]);
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setmDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = ((TextView) view.findViewById(R.id.tv_arrow_value));
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setTitle("选择性别")

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
                .setSingleChoiceItems(mGenders, 0, listener)
                .show();


    }


    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
