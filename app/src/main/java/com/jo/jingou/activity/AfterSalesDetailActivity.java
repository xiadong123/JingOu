package com.jo.jingou.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.ReturnDetailModel;
import com.jo.jingou.utils.Constant;

import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2017/5/18.
 */
public class AfterSalesDetailActivity extends MyBaseActivity {

    TextView application_tv, money, modifydate, type, receiving, reason, money1, explain;
    ImageView iv0, iv1, iv2;

    View money_layout, money1_layout;
    TextView activity_aftersalesdetail_tv1_name,
            activity_aftersalesdetail_Reason_name,
            activity_aftersalesdetail_Explain_name;


    @Override
    public void initData() {
        layoutId = R.layout.activity_aftersalesdetail;
    }

    @Override
    public void findViews() {

        application_tv = (TextView) findViewById(R.id.activity_aftersalesdetail_Application);
        money = (TextView) findViewById(R.id.activity_aftersalesdetail_Money);
        modifydate = (TextView) findViewById(R.id.activity_aftersalesdetail_tv1);
        type = (TextView) findViewById(R.id.activity_aftersalesdetail_Type);
        receiving = (TextView) findViewById(R.id.activity_aftersalesdetail_Receiving);
        reason = (TextView) findViewById(R.id.activity_aftersalesdetail_Reason);
        money1 = (TextView) findViewById(R.id.activity_aftersalesdetail_Money1);
        explain = (TextView) findViewById(R.id.activity_aftersalesdetail_Explain);

        iv0 = findViewAndSetClick(R.id.activity_aftersalesdetail_iv0);
        iv1 = findViewAndSetClick(R.id.activity_aftersalesdetail_iv1);
        iv2 = findViewAndSetClick(R.id.activity_aftersalesdetail_iv2);


        money_layout = findViewById(R.id.activity_aftersalesdetail_Money_layout);
        money1_layout = findViewById(R.id.activity_aftersalesdetail_Money1_layout);

        activity_aftersalesdetail_tv1_name = (TextView) findViewById(R.id.activity_aftersalesdetail_tv1_name);
        activity_aftersalesdetail_Reason_name = (TextView) findViewById(R.id.activity_aftersalesdetail_Reason_name);
        activity_aftersalesdetail_Explain_name = (TextView) findViewById(R.id.activity_aftersalesdetail_Explain_name);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("退换货详情");

        showLoadingDialog();
        utilNetwork.getReturnDetailModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("returnsid", getIntent().getStringExtra("returnsid"))});

    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_aftersalesdetail_iv0:
                break;
            case R.id.activity_aftersalesdetail_iv1:
                break;
            case R.id.activity_aftersalesdetail_iv2:
                break;
        }
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);

        switch (network_code) {
            case Constant.RETURNDETAIL_ID:
                if (status_code == 0) {
                    cancelLoadingDialog();
                    ReturnDetailModel returnDetailModel = (ReturnDetailModel) model;
                    switch (returnDetailModel.getModel().get(0).getApplication()) {
                        case 0:
                            application_tv.setText("申请中");
                            break;
                        case 1:
                            application_tv.setText("申请成功");
                            break;
                        case 2:
                            application_tv.setText("申请失败");
                            break;
                    }
                    money.setText("￥" + returnDetailModel.getModel().get(0).getMoney());
                    modifydate.setText(returnDetailModel.getModel().get(0).getModifyDate());
                    switch (returnDetailModel.getModel().get(0).getType()) {
                        case 0:
                            type.setText("退款退货");
                            break;
                        case 1:
                            type.setText("退款（无需退货）");
                            break;
                        case 2:
                            type.setText("换货");
                            break;
                    }

                    switch (returnDetailModel.getModel().get(0).getReceiving()) {
                        case 0:
                            receiving.setText("未收货");
                            break;
                        case 1:
                            receiving.setText("已收货");
                            break;
                    }

                    reason.setText(returnDetailModel.getModel().get(0).getReason());
                    money1.setText("￥" + returnDetailModel.getModel().get(0).getMoney());
                    explain.setText(returnDetailModel.getModel().get(0).getExplain());

                    List<ReturnDetailModel.ModelphotoEntity> modelphotoEntityList = returnDetailModel.getModelphoto();


                    switch (modelphotoEntityList.size()) {
                        case 1:
                            Glide.with(this).load(Constant.SERVER_HOST + modelphotoEntityList.get(0).getUrl()).into(iv0);
                            break;
                        case 2:
                            Glide.with(this).load(Constant.SERVER_HOST + modelphotoEntityList.get(0).getUrl()).into(iv0);
                            Glide.with(this).load(Constant.SERVER_HOST + modelphotoEntityList.get(1).getUrl()).into(iv1);
                            break;
                        case 3:
                            Glide.with(this).load(Constant.SERVER_HOST + modelphotoEntityList.get(0).getUrl()).into(iv0);
                            Glide.with(this).load(Constant.SERVER_HOST + modelphotoEntityList.get(1).getUrl()).into(iv1);
                            Glide.with(this).load(Constant.SERVER_HOST + modelphotoEntityList.get(2).getUrl()).into(iv2);
                            break;
                    }


                    if (returnDetailModel.getModel().get(0).getType() == 2) {
                        money_layout.setVisibility(View.GONE);
                        money1_layout.setVisibility(View.GONE);
                        activity_aftersalesdetail_tv1_name.setText("换货时间：");
                        activity_aftersalesdetail_Reason_name.setText("换货原因：");
                        activity_aftersalesdetail_Explain_name.setText("换货说明：");

                    }


                } else {


                }
                break;
        }

    }
}
