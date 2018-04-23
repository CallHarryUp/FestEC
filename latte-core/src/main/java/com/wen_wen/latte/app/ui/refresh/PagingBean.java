package com.wen_wen.latte.app.ui.refresh;

/**
 * Created by WeLot on 2018/4/23.
 * 存储分页数据
 */

public final class PagingBean {
    //当前是第几页
    private int mPageIndex;
    //总数据条数
    private int mTotal;
    //一页显示几条数据
    private int mPageSize;
    //当前显示了几条数据
    private int mCurrentCount;
    //加载延时
    private int mDelayed;

    public int getmPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getmTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getmPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getmCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getmDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    //第一次加载第一页
    PagingBean addIndex() {
        mPageIndex++;
        return this;
    }
}
