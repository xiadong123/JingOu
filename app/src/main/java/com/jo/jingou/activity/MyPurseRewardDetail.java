package com.jo.jingou.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.PocketdetailModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import caesar.feng.framework.net.OkHttpClientManager;

public class MyPurseRewardDetail extends MyBaseActivity {

    private String title;
    private String type;

    private TextView activity_mypurse_tv1;
    private TextView activity_mypurse_tv2;

    private TextView activity_mypurse_title1;
    private TextView activity_mypurse_title2;
    private String isBonus;

    @Override
    public void initData() {
        layoutId = R.layout.activity_my_purse_reward_detail;
    }

    @Override
    public void findViews() {
        Intent intent = getIntent();

        isBonus = intent.getStringExtra("isBonus");
        title = intent.getStringExtra("title");
        type = intent.getStringExtra("type");

        activity_mypurse_tv1 = (TextView) findViewById(R.id.activity_mypurse_tv1);
        activity_mypurse_tv2 = (TextView) findViewById(R.id.activity_mypurse_tv2);
        activity_mypurse_title1 = (TextView) findViewById(R.id.activity_mypurse_title1);
        activity_mypurse_title2 = (TextView) findViewById(R.id.activity_mypurse_title2);

        if ("1".equals(isBonus)) {
            activity_mypurse_title1.setText("预计收益(元)");
            activity_mypurse_title2.setText("可提现金额(元)");
        } else {
            activity_mypurse_title1.setText(title + "余额(元)");
            activity_mypurse_title2.setText("累计" + title + "收益(元)");
        }
        getPocketdetail();
    }

    /**
     * 请求 钱包详情 的数据
     */
    private void getPocketdetail() {

        showLoadingDialog();
        utilNetwork.getPocketdetailModel(new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("Type", type + "")
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        if (MyUtils.hasValue(title))
            setTopview2(title);
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

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T
            model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.POCKETDETAIL_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    PocketdetailModel pocketdetailModel = (PocketdetailModel) model;
                    Log.e("onNetworkCallBack", pocketdetailModel.getAvailable() + "--");
                    activity_mypurse_tv1.setText(pocketdetailModel.getAvailable() + "");
                    activity_mypurse_tv2.setText(pocketdetailModel.getTotal() + "");

                } else {
                    showToast(model.getErrmsg());
                }
                break;
        }


    }
}
