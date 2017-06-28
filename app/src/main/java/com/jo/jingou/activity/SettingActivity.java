package com.jo.jingou.activity;

import android.view.View;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.utils.GlideCacheUtil;
import com.jo.jingou.utils.MyUtils;

/**
 * Created by Administrator on 2016/12/11.
 */

public class SettingActivity extends MyBaseActivity {
    View userinfo;
    View addresslist;
    View cache;
    View message;
    View aboutus;

    TextView cache_tv;


    GlideCacheUtil glideCacheUtil;

    @Override
    public void initData() {
        layoutId = R.layout.activity_setting;
    }

    @Override
    public void findViews() {
        glideCacheUtil = new GlideCacheUtil();
        userinfo = findViewAndSetClick(R.id.activity_setting_userinfo_layout);
        addresslist = findViewAndSetClick(R.id.activity_setting_addresslist_layout);
        cache = findViewAndSetClick(R.id.activity_setting_cache_layout);
        message = findViewAndSetClick(R.id.activity_setting_message_layout);
        aboutus = findViewAndSetClick(R.id.activity_setting_aboutus_layout);

        cache_tv = (TextView) findViewById(R.id.activity_setting_cachesize_tv);

        setLogoutView();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("设置");
        cache_tv.setText(glideCacheUtil.getCacheSize(this));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_setting_userinfo_layout:
                MyUtils.setAndStartIntent(this, UserInfoActivity.class);
                break;
            case R.id.activity_setting_addresslist_layout:
                MyUtils.setAndStartIntent(this, AddressListActivity.class);
                break;
            case R.id.activity_setting_cache_layout:
                glideCacheUtil.clearImageAllCache(this);
                cache_tv.setText("0MB");
                break;
            case R.id.activity_setting_message_layout:
                showToast("请去系统通知中心设置");
                break;
            case R.id.activity_setting_aboutus_layout:
                MyUtils.setAndStartIntent(this, AboutUsActivity.class);
                break;
        }

    }
}
