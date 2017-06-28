package com.jo.jingou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/14.
 */
public class ChangeMyPasswordActivity extends MyBaseActivity {


    EditText nowpassword_ed, newpassword_ed0, newpassword_ed1;
    View resetpassword, submit;


    @Override
    public void initData() {
        layoutId = R.layout.activity_changemypassword;


    }

    @Override
    public void findViews() {
        nowpassword_ed = (EditText) findViewById(R.id.activity_changemypassword_nowpassword);
        newpassword_ed0 = (EditText) findViewById(R.id.activity_changemypassword_newpassword0);
        newpassword_ed1 = (EditText) findViewById(R.id.activity_changemypassword_newpassword1);

        resetpassword = findViewAndSetClick(R.id.activity_changemypassword_resetpassword);
        submit = findViewAndSetClick(R.id.submit);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("修改登录密码");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_changemypassword_resetpassword:
                Intent i = new Intent(this, Login_Register_ResetPasswordActivity.class);
                i.putExtra(Constant.BYTYPE, "byresetpassword");
                startActivity(i);
                break;
            case R.id.submit:
                String nowpassword = nowpassword_ed.getText().toString().trim();
                String newpassword0 = newpassword_ed0.getText().toString().trim();
                String newpassword1 = newpassword_ed1.getText().toString().trim();

                if (!MyUtils.hasValue(nowpassword)) {
                    showToast("请填写原密码");
                    return;
                }
                if (!MyUtils.hasValue(newpassword0)) {
                    showToast("请填写新密码");
                    return;
                }
                if (!MyUtils.hasValue(newpassword0)) {
                    showToast("请确认新密码");
                    return;
                }

                if (newpassword0.length() < 6) {
                    showToast("密码最少6位");
                    return;
                }

                if (!newpassword0.equals(newpassword1)) {
                    showToast("新密码与确认密码不一致");
                    return;
                }
                utilNetwork.getModifyPasswordModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("oldpsw", nowpassword), new OkHttpClientManager.Param("newpsw", newpassword0)});
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.MODIFYPASSWORD_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("修改成功");
                    finish();
                }
                break;
        }
    }
}
