package com.wen_wen.latte.ec.launcher.main.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.wen_wen.latte.app.delegate.LatteDelegate;

/**
 * Created by WeLot on 2018/4/26.
 */

public class ListBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl;
    private String mText;
    private String mValue;
    private int mId = 0;
    private LatteDelegate mDelegate;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener;

    public ListBean(int mItemType, String mImageUrl, String mText, String mValue, int mId, LatteDelegate mDelegate, CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmText() {
        if (mText == null) {
            return "";
        }
        return mText;
    }

    public String getmValue() {
        if (mValue == null) {
            return "";
        }
        return mValue;
    }

    public int getmId() {
        return mId;
    }

    public LatteDelegate getmDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getmOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static final class Builder {
        private int id = 0;
        private int itemType = 0;
        private String imageUrl;
        private String text;
        private String vaule;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        private LatteDelegate delegate;


        public Builder setId(int id) {
            this.id = id;
            return this;

        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setVaule(String vaule) {
            this.vaule = vaule;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }


        //build方法
        public ListBean build() {

            return new ListBean(itemType, imageUrl, text, vaule, id, delegate, onCheckedChangeListener);

        }
    }
}
