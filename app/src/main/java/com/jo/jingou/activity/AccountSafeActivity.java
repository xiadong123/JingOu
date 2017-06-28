package com.jo.jingou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

/**
 * Created by Administrator on 2016/12/11.
 */

public class AccountSafeActivity extends MyBaseActivity {

    TextView myphone0_tv, myphone1_tv, isidcard_tv;

    View myphone_layout, isidcard_layout, changepassword_layout;

    String byType = "";


    @Override
    public void initData() {
        layoutId = R.layout.activity_accountsafe;
    }

    @Override
    public void findViews() {
        setLogoutView();

        myphone0_tv = (TextView) findViewById(R.id.activity_accountsafe_myphone0);
        myphone1_tv = (TextView) findViewById(R.id.activity_accountsafe_myphone1);
        isidcard_tv = (TextView) findViewById(R.id.activity_accountsafe_isidcard_tv);

        myphone_layout = findViewAndSetClick(R.id.activity_accountsafe_myphone_layout);
        isidcard_layout = findViewAndSetClick(R.id.activity_accountsafe_isidcard_layout);
        changepassword_layout = findViewAndSetClick(R.id.activity_accountsafe_changepassword_layout);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("账户安全");
        setData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setData();
    }

    private void setData() {
        if (UserInfoLoader.getUserInfoModel() != null) {
            myphone0_tv.setText(UserInfoLoader.getUserInfoModel().getModel().getMobile());
            myphone1_tv.setText(UserInfoLoader.getUserInfoModel().getModel().getMobile());
            if (MyUtils.isIdCode()) {
                isidcard_tv.setText("已认证");
                byType = "hasIdCode";
                isidcard_layout.setOnClickListener(this);
            } else {
                isidcard_tv.setText("未认证");
                byType = "isIdCode";
                isidcard_layout.setOnClickListener(this);
            }
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_accountsafe_myphone_layout:
                MyUtils.setAndStartIntent(this, ChangeMyPhoneActivity.class);
                break;
            case R.id.activity_accountsafe_isidcard_layout:
                Intent i = new Intent(this, ImproveInformationActivity.class);
                i.putExtra(Constant.BYTYPE, byType);
                startActivity(i);
                break;
            case R.id.activity_accountsafe_changepassword_layout:
                MyUtils.setAndStartIntent(this, ChangeMyPasswordActivity.class);
                break;


        }
    }
}
