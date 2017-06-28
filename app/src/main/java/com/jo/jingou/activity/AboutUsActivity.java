package com.jo.jingou.activity;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;

import caesar.feng.framework.utils.Utility;

/**
 * Created by dfyu on 2017/5/19.
 * test
 */
public class AboutUsActivity extends MyBaseActivity {


    WebView webView;

    @Override
    public void initData() {
        layoutId = R.layout.activity_about;
    }

    @Override
    public void findViews() {
        webView = (WebView) findViewById(R.id.activity_about_webview);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("关于我们");
        Log.i("TAG", Utility.getAppVersion(this));
        webView.loadUrl("http://jingou.idea-source.net/home/About?name=" + Utility.getAppVersion(this));
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
        }

    }
}

