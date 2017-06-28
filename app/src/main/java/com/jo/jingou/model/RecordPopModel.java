package com.jo.jingou.model;

/**
 * 我的钱包 记录 筛选 展示列表的model
 * Created by Administrator on 2017/5/3 0003.
 */
public class RecordPopModel {
    private String mContent;
    private int mId;
    private boolean isSelect;

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
