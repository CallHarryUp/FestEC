package com.wen_wen.latte.ec.launcher.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by WeLot on 2018/4/23.
 */

public class SectionBean extends SectionEntity<SectionContentItemEntity> {
    private boolean mIsMore = false;
    private int mId = -1;


    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    /**
     * @param isHeader 是不是分组的标题部分
     * @param header
     */
    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public boolean isMore() {
        return mIsMore;
    }

    public void setIsMore(boolean isMore) {
        this.mIsMore = isMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }
}
