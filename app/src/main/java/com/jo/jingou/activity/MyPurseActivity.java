package com.jo.jingou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.MyPocketModel;
import com.jo.jingou.model.UserInfoModel;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import caesar.feng.framework.net.OkHttpClientManager;


/**
 * Created by dfyu on 2016/12/14.
 */
public class MyPurseActivity extends MyBaseActivity {

    private static final int GOTOENCHASHMENT = 4001;
    public static final int BACKENCHASHMENTREFRESH = 4002;
    View submit;

    TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6;
    //activity_mypurse_merchanorder_layout
    View fenhongdetail_layout, distributiondetail_layout, partnerdetail_layout, buniessdetail_layout, merchanorder_layout,
            base_topview_right1;

    @Override
    public void initData() {
        layoutId = R.layout.activity_mypurse;
    }

    @Override
    public void findViews() {
        submit = findViewAndSetClick(R.id.submit);
        tv0 = (TextView) findViewById(R.id.activity_mypurse_tv0);
        tv1 = (TextView) findViewById(R.id.activity_mypurse_tv1);
        tv2 = (TextView) findViewById(R.id.activity_mypurse_tv2);
        tv3 = (TextView) findViewById(R.id.activity_mypurse_tv3);
        tv4 = (TextView) findViewById(R.id.activity_mypurse_tv4);
        tv5 = (TextView) findViewById(R.id.activity_mypurse_tv5);
        tv6 = (TextView) findViewById(R.id.activity_mypurse_tv6);

        base_topview_right1 = findViewAndSetClick(R.id.base_topview_right1);

        fenhongdetail_layout = findViewAndSetClick(R.id.activity_mypurse_fenhongdetail_layout);
        distributiondetail_layout = findViewAndSetClick(R.id.activity_mypurse_distributiondetail_layout);
        partnerdetail_layout = findViewAndSetClick(R.id.activity_mypurse_partnerdetail_layout);
        buniessdetail_layout = findViewAndSetClick(R.id.activity_mypurse_buniessdetail_layout);
        //未成为商家时 不展示此选项  默认隐藏 当请求完接口是 在判断是否展示出来
        merchanorder_layout = findViewAndSetClick(R.id.activity_mypurse_merchanorder_layout);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        UserInfoModel userInfoModel = UserInfoLoader.getUserInfoModel();
        if (userInfoModel == null) {
            finish();
            return;
        }
        int applyStatus = userInfoModel.getModel().getApplyStatus();

        setTopview2("我的钱包");
        leftimg3.setImageResource(R.drawable.back_w);
        righttxt.setVisibility(View.GONE);

        if (applyStatus == 1) {
            merchanorder_layout.setVisibility(View.VISIBLE);
        } else {
            merchanorder_layout.setVisibility(View.GONE);
        }

        getPurseData();  //请求服务器 抓取页面的数据
    }

    private void getPurseData() {
        showLoadingDialog();
        utilNetwork.getMypocketModel(new OkHttpClientManager.Param[]{});

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.submit:
                MyUtils.setAndStartIntentForResult(this, EnchashmentActivity.class, GOTOENCHASHMENT);
                break;
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_mypurse_distributiondetail_layout:
                gotoDetail("分销奖励", "1", "0");
                break;
            case R.id.activity_mypurse_partnerdetail_layout:
                gotoDetail("合伙人奖励", "2", "0");
                break;
            case R.id.activity_mypurse_buniessdetail_layout:
                gotoDetail("推荐商家收益", "3", "1");
                break;
            case R.id.activity_mypurse_merchanorder_layout:
                gotoDetail("商家收益", "4", "1");
                break;
            case R.id.activity_mypurse_fenhongdetail_layout:  //分红
                MyUtils.setAndStartIntent(this, FenhongDetailActivity.class);
                break;
            case R.id.base_topview_right:  //查看钱包说明
                MyUtils.setAndStartIntent(this, WalletDesActivity.class);
                break;
            case R.id.base_topview_right1:  //查看交易记录
                MyUtils.setAndStartIntent(this, TransactionRecordActivity.class);
                break;
        }
    }

    /**
     * 跳转到奖励详情
     *
     * @param title   奖励详情的title 文案
     * @param type    当前跳转页面的类型
     * @param isBonus
     */
    private void gotoDetail(String title, String type, String isBonus) {
        String[] key = new String[]{"title", "type", "isBonus"};
        String[] value = new String[]{title, type, isBonus};
        MyUtils.setAndStartIntent(this, MyPurseRewardDetail.class, key, value);
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.WITHDRAWMOINEY_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("申请提交成功");
                }
                break;
            case Constant.MYPOCKET_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    MyPocketModel myPocketModel = (MyPocketModel) model;
                    tv0.setText(myPocketModel.getTotal() + "");
                    tv1.setText(myPocketModel.getProfit() + "");
                    tv2.setText(myPocketModel.getDistribution() + "");
                    tv3.setText(myPocketModel.getPartner() + "");
                    tv4.setText(myPocketModel.getBusiness() + "");
                    tv5.setText(myPocketModel.getMerchanorder() + "");
                    tv6.setText(myPocketModel.getRefund() + "");
                } else {
                    if (model != null) {
                        showToast(model.getErrmsg());
                    }
                }

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GOTOENCHASHMENT) {
            switch (resultCode) {
                case BACKENCHASHMENTREFRESH:
                    getPurseData();  //请求服务器 抓取页面的数据
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
