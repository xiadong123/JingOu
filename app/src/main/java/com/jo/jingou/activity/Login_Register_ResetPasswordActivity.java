package com.jo.jingou.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.LoginModel;
import com.jo.jingou.model.RegisterModel;
import com.jo.jingou.model.SendCodeModel;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import java.util.HashMap;
import java.util.Map;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/11/28.
 */
public class Login_Register_ResetPasswordActivity extends MyBaseActivity {

    String bytype = "bylogin";
    String type = "login";
    int toBuy = 0;
    String productid = "";
    String much = "";

    View logo;
    EditText mobile_ed, password_ed, code_ed;
    View code_view;
    TextView sendcode_view;
    Button submit;
    View forgetpassword;
    TextView agreement;


    int time_register = 0, time_resetpassword = 0;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    time_register--;
                    setSendCodeView("register", time_register);
                    if (time_register != 0) handler.sendEmptyMessageDelayed(0, 1000);
                    break;
                case 1:
                    time_resetpassword--;
                    setSendCodeView("resetpassword", time_resetpassword);
                    if (time_resetpassword != 0) handler.sendEmptyMessageDelayed(1, 1000);
                    break;
            }
        }
    };


    @Override
    public void initData() {
        layoutId = R.layout.activity_login_register_resetpassword;
    }

    @Override
    public void findViews() {
        logo = findViewById(R.id.activity_LoginRegisterResterPassword_logo);
        mobile_ed = (EditText) findViewById(R.id.activity_LoginRegisterResterPassword_phone);
        password_ed = (EditText) findViewById(R.id.activity_LoginRegisterResterPassword_password);
        code_ed = (EditText) findViewById(R.id.activity_LoginRegisterResterPassword_code);
        code_view = findViewById(R.id.activity_LoginRegisterResterPassword_codeview);
        sendcode_view = findViewAndSetClick(R.id.activity_LoginRegisterResterPassword_sendcode);
        submit = findViewAndSetClick(R.id.activity_LoginRegisterResterPassword_submit);
        forgetpassword = findViewAndSetClick(R.id.activity_LoginRegisterResterPassword_forgetpassword);
        agreement = findViewAndSetClick(R.id.activity_LoginRegisterResterPassword_agreement);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void setupViews() {
        bytype = getIntent().getStringExtra(Constant.BYTYPE);
        toBuy = getIntent().getIntExtra("toBuy", 0);
        productid = getIntent().getStringExtra("productid");
        much = getIntent().getStringExtra("much");

        switch (bytype) {
            case "bylogin":
                login();
                break;
            case "byregister":
                register();
                break;
            case "byresetpassword":
                resetpassword();
                break;
        }
    }


    //登录页
    private void login() {
        setMainView("login", "登录", leftimg2, R.drawable.close, "注册", View.GONE, "请输入密码(6-20位英文或数字)", "登录", View.VISIBLE, View.GONE);
    }

    //忘记密码页
    private void resetpassword() {
        setMainView("resetpassword", "找回密码", leftimg3, R.drawable.back, "登录", View.VISIBLE, "请输入密码(6-20位英文或数字)", "确认重置密码", View.GONE, View.GONE);
        if ("byresetpassword".equals(type)) {
            rightview.setVisibility(View.GONE);
        } else {
            rightview.setVisibility(View.VISIBLE);
        }
        setSendCodeView("resetpassword", time_resetpassword);
    }

    //注册页
    private void register() {
        setMainView("register", "注册", leftimg2, R.drawable.close, "登录", View.VISIBLE, "请输入密码(6-20位英文或数字)", "注册", View.GONE, View.VISIBLE);
        setSendCodeView("register", time_register);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.activity_LoginRegisterResterPassword_forgetpassword:
                resetpassword();
                break;
            case R.id.base_topview_left:
                back();
                break;
            case R.id.base_topview_right:
                if ("login".equals(type)) register();
                else if ("resetpassword".equals(type)) login();
                else if ("register".equals(type)) login();
                break;

            case R.id.activity_LoginRegisterResterPassword_submit:
                if ("login".equals(type)) {
                    network_login();
                } else if ("resetpassword".equals(type)) {
                    network_resetpassword();
                } else if ("register".equals(type)) {
                    network_register();
                }
                break;

            case R.id.activity_LoginRegisterResterPassword_sendcode:
                network_sendcode();
                break;
        }
    }


    Map<String, String> map_mobileandcode_register = new HashMap();
    Map<String, String> map_mobileandcode_resetpassword = new HashMap();

    //发送验证码操作
    private void network_sendcode() {
        String mobile = mobile_ed.getText().toString().trim();
        if (MyUtils.hasValue(mobile) && mobile.length() == 11) {
            if ("register".equals(type)) {
                map_mobileandcode_register.put("mobile", mobile);

                showLoadingDialog();
                utilNetwork.getSendCode0Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "1"), new OkHttpClientManager.Param("mobile", mobile)});
            } else if ("resetpassword".equals(type)) {
                map_mobileandcode_resetpassword.put("mobile", mobile);

                showLoadingDialog();
                utilNetwork.getSendCode1Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "2"), new OkHttpClientManager.Param("mobile", mobile)});
            }
        } else if (!MyUtils.hasValue(mobile)) showToast("请输入手机号");
        else if (!(mobile.length() == 11)) showToast("请输入正确的手机号");
    }

    //注册操作
    public void network_register() {
        String mobile = mobile_ed.getText().toString().trim();
        if (!MyUtils.hasValue(mobile)) {
            showToast("请输入手机号");
            return;
        } else if (!(mobile.length() == 11)) {
            showToast("请输入正确的手机号");
            return;
        } else if (!mobile.equals(map_mobileandcode_register.get("mobile"))) {
            showToast("手机号变更，请重新获取验证码");
            return;
        }

        String code = code_ed.getText().toString().trim();
        if (!MyUtils.hasValue(code)) {
            showToast("请输入验证码");
            return;
        } else if (!code.equals(map_mobileandcode_register.get("code"))) {
            showToast("验证码错误");
            return;
        }

        String password = password_ed.getText().toString().trim();
        if (!MyUtils.hasValue(password)) {
            showToast("请输入密码");
            return;
        } else if (password.length() < 6) {
            showToast("请输入密码(6~20位英文或数字)");
            return;
        }
        showLoadingDialog();
        utilNetwork.getRegisterModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("type", "1"),
                        new OkHttpClientManager.Param("mobile", mobile),
                        new OkHttpClientManager.Param("password", password),
                        new OkHttpClientManager.Param("phoneId", MyApplication.getDeviceToken())});
    }

    //登录操作
    public void network_login() {
        String mobile = mobile_ed.getText().toString().trim();
        if (!MyUtils.hasValue(mobile)) {
            showToast("请输入手机号");
            return;
        } else if (!(mobile.length() == 11)) {
            showToast("请输入正确的手机号");
            return;
        }

        String password = password_ed.getText().toString().trim();
        if (!MyUtils.hasValue(password)) {
            showToast("请输入密码");
            return;
        } else if (password.length() < 6) {
            showToast("请输入密码(6~20位英文或数字)");
            return;
        }
        showLoadingDialog();
        utilNetwork.getLoginModel(new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("mobile", mobile),
                new OkHttpClientManager.Param("password", password),
                new OkHttpClientManager.Param("phoneId", MyApplication.getDeviceToken())});
    }

    //忘记密码
    public void network_resetpassword() {
        String mobile = mobile_ed.getText().toString().trim();
        if (!MyUtils.hasValue(mobile)) {
            showToast("请输入手机号");
            return;
        } else if (!(mobile.length() == 11)) {
            showToast("请输入正确的手机号");
            return;
        } else if (!mobile.equals(map_mobileandcode_resetpassword.get("mobile"))) {
            showToast("手机号变更，请重新获取验证码");
            return;
        }

        String code = code_ed.getText().toString().trim();
        if (!MyUtils.hasValue(code)) {
            showToast("请输入验证码");
            return;
        } else if (!code.equals(map_mobileandcode_resetpassword.get("code"))) {
            showToast("验证码错误");
            return;
        }

        String password = password_ed.getText().toString().trim();
        if (!MyUtils.hasValue(password)) {
            showToast("请输入密码");
            return;
        } else if (password.length() < 6) {
            showToast("请输入密码(6~20位英文或数字)");
            return;
        }
        showLoadingDialog();
        utilNetwork.getChangePassWordModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("mobile", mobile),
                        new OkHttpClientManager.Param("password", password)});
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.SEND_ID0:
                cancelLoadingDialog();
                if (status_code == 0) {
                    time_register = 60;
                    handler.sendEmptyMessageDelayed(0, 1000);
                    setSendCodeView("register", time_register);
                    map_mobileandcode_register.put("code", ((SendCodeModel) model).getCode());
                } else {
                    showToast(model.getErrmsg());
                }
                break;
            case Constant.SEND_ID1:
                cancelLoadingDialog();
                if (status_code == 0) {
                    time_resetpassword = 60;
                    handler.sendEmptyMessageDelayed(1, 1000);
                    setSendCodeView("resetpassword", time_resetpassword);
                    map_mobileandcode_resetpassword.put("code", ((SendCodeModel) model).getCode());
                } else {
                    showToast(model.getErrmsg());
                }
                break;
            case Constant.REGISTER_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    UserInfoLoader.setUserInfoModel(null);
                    RegisterModel registerModel = (RegisterModel) model;
                    MyUtils.setLogin("1", registerModel.getToken(), registerModel.getMemberId() + "", "false", "false");
                    if (toBuy == 0) {
//                        Intent i = new Intent(this, ImproveInformationActivity.class);
//                        i.putExtra(Constant.BYTYPE, "fromRegister");
//                        i.putExtra("toBuy", toBuy);
//                        startActivityForResult(i, 22);
                        toBuy_ImproveInformationActivity("fromRegister");
                    } else if (toBuy == 1) {
                        toBuy_ImproveInformationActivity("All");
                    }
                } else {
                    showToast(model.getErrmsg());
                }
                break;
            case Constant.LOGIN_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    UserInfoLoader.setUserInfoModel(null);
                    LoginModel registerModel = (LoginModel) model;
                    MyUtils.setLogin("1", registerModel.getToken(), registerModel.getMemberId() + "",
                            registerModel.getIsFatherCode(), registerModel.getIsIdCard());
                    if (toBuy == 0) {
                        this.finish();
                    } else if (toBuy == 1) {
                        if (MyUtils.CanBuyAngle() == 0) {
                            MyUtils.commonBUY(this, productid, much, "", "", 11);
                        } else if (MyUtils.isFatherCode()) {
                            toBuy_ImproveInformationActivity("isFatherCode");
                        } else if (MyUtils.isIdCode()) {
                            toBuy_ImproveInformationActivity("isIdCode");
                        } else {
                            toBuy_ImproveInformationActivity("All");
                        }
                    }
                    this.finish();
                }
                break;
            case Constant.CHANGEPASSWORD_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast(model.getErrmsg());
                    if ("byresetpassword".equals(bytype)) {
                        this.finish();
                    } else {
                        login();
                    }
                }
                break;
        }
    }

    private void toBuy_ImproveInformationActivity(String type) {
        Intent i = new Intent(this, ImproveInformationActivity.class);
        i.putExtra(Constant.BYTYPE, type);
        i.putExtra("toBuy", toBuy);
        i.putExtra("productid", productid);
        i.putExtra("much", much);
        //startActivityForResult(i, 22);
        startActivity(i);
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) return back();
        return super.onKeyDown(keyCode, event);
    }

    //返回时操作
    private boolean back() {
        if ("bylogin".equals(bytype)) {
            if ("login".equals(type)) {
                this.finish();
            } else if ("resetpassword".equals(type)) {
                login();
            } else if ("register".equals(type)) {
                login();
            }
        } else if ("byregister".equals(bytype)) {
            if ("login".equals(type)) {
                register();
            } else if ("resetpassword".equals(type)) {
                login();
            } else if ("register".equals(type)) {
                this.finish();
            }
        } else if ("byresetpassword".equals(type)) {
            if ("login".equals(type)) {
                register();
            } else if ("resetpassword".equals(type)) {
                this.finish();
            } else if ("register".equals(type)) {
                this.finish();
            }
        }
        return true;
    }


    //代码简化 设置发送验证码按钮
    private void setSendCodeView(String type, int time) {
        if (type.equals(this.type)) {
            if (time == 0) {
                sendcode_view.setBackgroundResource(R.drawable.fillet_textcolor0);
                sendcode_view.setText("发送验证码");
                sendcode_view.setOnClickListener(this);
            } else {
                sendcode_view.setBackgroundResource(R.drawable.fillet_gary3_0);
                sendcode_view.setText("重新发送(" + time + "s)");
                sendcode_view.setOnClickListener(null);
            }
        }
    }


    //代码简化 设置页面控件
    private void setMainView(String typeTxt,//页面标识
                             String centerTxt,//中间标题文字
                             ImageView leftView, int leftResId,//左边imgview，资源id
                             String rightTxt,//右边标题文字
                             int code_viewVis,//验证码控件是否显示
                             String password_ed_hint,//密码控件提示文字
                             String submitTxt,//提交控件提示文字
                             int forgetpasswordVis,//忘记密码按钮是否显示
                             int agreementVis//用户协议按钮是否显示
    ) {
        type = typeTxt;
        setTopView1();
        goneTopViews();
        centertxt.setVisibility(View.VISIBLE);
        centertxt.setText(centerTxt);
        leftView.setVisibility(View.VISIBLE);
        leftView.setImageResource(leftResId);
        righttxt.setVisibility(View.VISIBLE);
        righttxt.setText(rightTxt);

        code_view.setVisibility(code_viewVis);
        password_ed.setHint(password_ed_hint);
        submit.setText(submitTxt);
        forgetpassword.setVisibility(forgetpasswordVis);
        agreement.setVisibility(agreementVis);
        agreement.setText(Html.fromHtml("<font color=\"#999999\">提交表示同意并接受</font><font color=\"#BD925C\">《利益天下分红商城平台用户协议》</font>"));
    }
}
