package com.jo.jingou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.InstationCountModel;
import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2017/4/24.
 */
public class MessageActivity extends MyBaseActivity {


    View message0, message1, message2, message3;
    TextView message0_number_tv, message1_number_tv, message2_number_tv, message3_number_tv;
    int message0_number = 0, message1_number = 0, message2_number = 0, message3_number = 0;


    @Override
    public void initData() {
        layoutId = R.layout.activity_message;
    }

    @Override
    public void findViews() {
        message0 = findViewAndSetClick(R.id.activity_message_ll0);
        message1 = findViewAndSetClick(R.id.activity_message_ll1);
        message2 = findViewAndSetClick(R.id.activity_message_ll2);
        message3 = findViewAndSetClick(R.id.activity_message_ll3);

        message0_number_tv = (TextView) findViewById(R.id.activity_message_ll0_number);
        message1_number_tv = (TextView) findViewById(R.id.activity_message_ll1_number);
        message2_number_tv = (TextView) findViewById(R.id.activity_message_ll2_number);
        message3_number_tv = (TextView) findViewById(R.id.activity_message_ll3_number);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("消息");
        showLoadingDialog();
        utilNetwork.getInstationCountModel(new OkHttpClientManager.Param[]{});
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        utilNetwork.getInstationCountModel(new OkHttpClientManager.Param[]{});
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        Intent i = new Intent(this, MessageCommonListActicity.class);

        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_message_ll0:
                i.putExtra("message_type", 0);
                startActivity(i);
                break;
            case R.id.activity_message_ll1:
                i.putExtra("message_type", 1);
                startActivity(i);
                break;
            case R.id.activity_message_ll2:
                i.putExtra("message_type", 2);
                startActivity(i);
                break;
            case R.id.activity_message_ll3:
                MyUtils.setAndStartIntent(this, MessageBonusListActicity.class);
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.INSTATIONCOUNT_ID:
                cancelLoadingDialog();
                if (status_code == 0) {

                    InstationCountModel instationCountModel = (InstationCountModel) model;

                    message0_number = instationCountModel.getCount1();
                    message1_number = instationCountModel.getCount2();
                    message2_number = instationCountModel.getCount3();
                    message3_number = instationCountModel.getCount4();
                    setMessageNumber();
                    MessageLoader.setMessage(message0_number, message1_number, message2_number, message3_number);
                }
                break;
        }
    }

    private void setMessageNumber() {
        if (message0_number == 0) {
            message0_number_tv.setVisibility(View.GONE);
        } else {
            message0_number_tv.setVisibility(View.VISIBLE);
            message0_number_tv.setText(message0_number + "");
        }
        if (message1_number == 0) {
            message1_number_tv.setVisibility(View.GONE);
        } else {
            message1_number_tv.setVisibility(View.VISIBLE);
            message1_number_tv.setText(message1_number + "");
        }
        if (message2_number == 0) {
            message2_number_tv.setVisibility(View.GONE);
        } else {
            message2_number_tv.setVisibility(View.VISIBLE);
            message2_number_tv.setText(message2_number + "");
        }
        if (message3_number == 0) {
            message3_number_tv.setVisibility(View.GONE);
        } else {
            message3_number_tv.setVisibility(View.VISIBLE);
            message3_number_tv.setText(message3_number + "");
        }
    }
}
