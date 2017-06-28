package com.jo.jingou.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfyu on 2016/12/1.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<String> pagerTitle = new ArrayList<>();
    private List<View> list;

    public ViewPagerAdapter(List<View> list) {
        this.list = list;
    }

    public ViewPagerAdapter(List<View> list, List<String> pagerTitle) {
        this.pagerTitle = pagerTitle;
        this.list = list;
    }

    @Override
    public int getCount() {

        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitle.get(position);
    }
}