package com.jo.jingou.fragment;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;

import com.jo.jingou.R;
import com.jo.jingou.activity.MessageActivity;
import com.jo.jingou.base.MyBaseFragment;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

/**
 * Created by dfyu on 2017/5/19.
 */
public class IndustryFragment extends MyBaseFragment {


    WebView webView;

    @Override
    public void initData() {
        layoutId = R.layout.fragment_industry;
    }

    @Override
    public void findViews() {
        findTopViews();
        webView = (WebView) rootView.findViewById(R.id.fragment_industry_webview);
        webView.loadUrl(Constant.SERVER_HOST + "/home/Icon");
    }


    @Override
    public void initListener() {
        rightview.setOnClickListener(this);
    }


    @Override
    public void setupViews() {
        centertxt.setVisibility(View.VISIBLE);
        centertxt.setText("行业");

        upData();
    }

    @Override
    public void upData() {
        setMessageImage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_topview_right:
                if (MyUtils.isLogin()) {
                    Intent i = new Intent(context, MessageActivity.class);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
        }
    }


}
