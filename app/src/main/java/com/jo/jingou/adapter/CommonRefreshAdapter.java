package com.jo.jingou.adapter;

import android.content.Context;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;

import java.util.List;

/**
 * Created by dfyu on 2017/4/5.
 */
public abstract class CommonRefreshAdapter<T> extends CommonBaseAdapter<T> {


    int item_layout;

    public CommonRefreshAdapter(Context context, List<T> datas, int item_layout, boolean isLoadMore) {
        super(context, datas, isLoadMore);
        this.item_layout = item_layout;
    }

    @Override
    protected abstract void convert(ViewHolder holder, T data);

    @Override
    protected int getItemLayoutId() {
        return item_layout;
    }

    public List<T> getData() {
        return mDatas;
    }
}
