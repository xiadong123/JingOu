package com.jo.jingou.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.SendCodeModel;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/14.
 */
public class ChangeMyPhoneActivity extends MyBaseActivity {


    TextView nowphone_tv, sendcode0_tv, sendcode1_tv;

    EditText code0_ed, newphone_ed, code1_ed;
    View submit;

    String nowphone;

    PhoneAndCodeModel model_now, model_new;


    int time0 = 0, time1 = 0;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    time0--;
                    if (time0 >= 0) {
                        sendcode0_tv.setText("重新发送(" + time0 + "s)");
                        handler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        sendcode0_tv.setText("发送验证码");
                        sendcode0_tv.setBackgroundResource(R.drawable.fillet_textcolor0);
                        sendcode0_tv.setOnClickListener(ChangeMyPhoneActivity.this);
                    }

                    break;
                case 1:
                    time1--;
                    if (time1 >= 0) {
                        sendcode1_tv.setText("重新发送(" + time1 + "s)");
                        handler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        sendcode1_tv.setText("发送验证码");
                        sendcode1_tv.setBackgroundResource(R.drawable.fillet_textcolor0);
                        sendcode1_tv.setOnClickListener(ChangeMyPhoneActivity.this);
                    }
                    break;
            }
        }
    };


    @Override
    public void initData() {
        layoutId = R.layout.activity_changemyphone;
    }

    @Override
    public void findViews() {
        nowphone_tv = (TextView) findViewById(R.id.activity_changemyphone_nowphone_tv);
        code0_ed = (EditText) findViewById(R.id.activity_changemyphone_code0_ed);
        newphone_ed = (EditText) findViewById(R.id.activity_changemyphone_newphone_ed);
        code1_ed = (EditText) findViewById(R.id.activity_changemyphone_code1_ed);
        submit = findViewAndSetClick(R.id.submit);
        sendcode0_tv = findViewAndSetClick(R.id.activity_changemyphone_sendcode0);
        sendcode1_tv = findViewAndSetClick(R.id.activity_changemyphone_sendcode1);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("修改手机号");
        if (UserInfoLoader.getUserInfoModel() != null) {
            nowphone = UserInfoLoader.getUserInfoModel().getModel().getMobile() + "";
            nowphone_tv.setText(nowphone);
            model_now = new PhoneAndCodeModel(nowphone, "");
            model_new = new PhoneAndCodeModel("", "");

        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_changemyphone_sendcode0:
                showLoadingDialog();
                utilNetwork.getSendCode1Model(new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("type", "2"),
                        new OkHttpClientManager.Param("mobile", model_now.getPhone())});
                break;
            case R.id.activity_changemyphone_sendcode1:
                String newphone = newphone_ed.getText().toString().trim();
                if (MyUtils.hasValue(newphone)) {
                    if (newphone.length() != 11) {
                        showToast("请输入正确的11位手机号");
                    } else if (newphone.equals(model_now.getPhone())) {
                        showToast("新手机号和原手机号相同");
                    } else {
                        model_new.setPhone(newphone);
                        showLoadingDialog();
                        utilNetwork.getSendCode0Model(new OkHttpClientManager.Param[]{
                                new OkHttpClientManager.Param("type", "1"),
                                new OkHttpClientManager.Param("mobile", newphone)});
                    }
                } else {
                    showToast("请输入新的手机号");
                }
                break;

            case R.id.submit:
                String code0 = code0_ed.getText().toString().trim();
                String newphone_submit = newphone_ed.getText().toString().trim();
                String code1 = code1_ed.getText().toString().trim();

                if (!MyUtils.hasValue(code0)) {
                    showToast("请输入验证码");
                    return;
                }

                if (!MyUtils.hasValue(newphone_submit)) {
                    showToast("请输入新的手机号");
                    return;
                }

                if (!MyUtils.hasValue(code1)) {
                    showToast("请输入验证码");
                    return;
                }

                if (!code0.equals(model_now.getCode())) {
                    showToast("原手机验证码错误");
                    return;
                }
                if (!newphone_submit.equals(model_new.getPhone())) {
                    showToast("新手机更换请重新获取验证码");
                    return;
                }

                if (!code1.equals(model_new.getCode())) {
                    showToast("新手机验证码错误");
                    return;
                }

                showLoadingDialog();
                utilNetwork.getChangeMobileModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("newMobile", newphone_submit)});
                break;
        }
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.SEND_ID1:
                cancelLoadingDialog();
                if (status_code == 0) {
                    SendCodeModel sendCodeModel0 = (SendCodeModel) model;
                    model_now.setCode(sendCodeModel0.getCode());
                    time0 = 60;
                    sendcode0_tv.setText("重新发送(60s)");
                    sendcode0_tv.setBackgroundResource(R.drawable.fillet_gary3_0);
                    sendcode0_tv.setOnClickListener(null);
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
                break;
            case Constant.SEND_ID0:
                cancelLoadingDialog();
                if (status_code == 0) {
                    SendCodeModel sendCodeModel0 = (SendCodeModel) model;
                    model_new.setCode(sendCodeModel0.getCode());
                    time1 = 60;
                    sendcode1_tv.setText("重新发送(60s)");
                    sendcode1_tv.setBackgroundResource(R.drawable.fillet_gary3_0);
                    sendcode1_tv.setOnClickListener(null);
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
                break;
            case Constant.CHANGEMOBILE_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast(model.getErrmsg());
                    UserInfoLoader.getUserInfoModel().getModel().setMobile(model_new.getPhone());
                    finish();
                }
                break;
        }
    }

    class PhoneAndCodeModel {
        String phone, code;

        public PhoneAndCodeModel(String phone, String code) {
            this.phone = phone;
            this.code = code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCode() {
            return code;
        }

        public String getPhone() {
            return phone;
        }
    }

}
