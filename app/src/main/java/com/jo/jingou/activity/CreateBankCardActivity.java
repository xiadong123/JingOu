package com.jo.jingou.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.CardListModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_Network;

import caesar.feng.framework.net.OkHttpClientManager;

public class CreateBankCardActivity extends MyBaseActivity {

    private boolean isEdit;  //判断当前状态为 编辑还是创建  true 编辑 false 创建
    private TextView submit_tv;

    private LinearLayout sContent;
    private EditText sCreate_bank_name_et;
    private EditText sCreate_bank_username_et;
    private EditText sCreate_bankname_et;
    private EditText sCreate_branchname_et;
    private RelativeLayout sSubmit_rl;
    private CardListModel.ListBean listBean;

    @Override
    public void initData() {
        layoutId = R.layout.activity_create_bank_card;
    }

    @Override
    public void findViews() {
        Intent intent = getIntent();
        isEdit = intent.getBooleanExtra("isEdit", false);
        listBean = intent.getParcelableExtra("listBean");

        sContent = (LinearLayout) findViewById(R.id.content);
        sCreate_bank_name_et = (EditText) findViewById(R.id.create_bank_name_et);
        sCreate_bank_username_et = (EditText) findViewById(R.id.create_bank_username_et);
        sCreate_bankname_et = (EditText) findViewById(R.id.create_bankname_et);
        sCreate_branchname_et = (EditText) findViewById(R.id.create_branchname_et);
        sSubmit_rl = (RelativeLayout) findViewById(R.id.submit_rl);
        submit_tv = (TextView) findViewById(R.id.submit_tv);
    }

    @Override
    public void initListener() {
        sSubmit_rl.setOnClickListener(this);
    }

    @Override
    public void setupViews() {
        if (isEdit) {
            setTopview2("修改银行卡信息");
            submit_tv.setText("完成修改");
            if (listBean != null) {
                setDefaultData();
            } else {
                finish();
                return;
            }
        } else {
            submit_tv.setText("添加");
            setTopview2("添加银行卡");
        }
        leftimg3.setImageResource(R.drawable.back);
        righttxt.setVisibility(View.GONE);

    }

    /**
     * 编辑的时候 展示上个页面传进来的数据
     */
    private void setDefaultData() {
        sCreate_bank_name_et.setText(listBean.getName());
        sCreate_bank_username_et.setText(listBean.getBankCardCode());
        sCreate_bankname_et.setText(listBean.getBank());
        sCreate_branchname_et.setText(listBean.getBankInformation());

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.submit_rl:
                commitBank();  //提交数据
                break;
        }
    }

    private void commitBank() {
        String name = sCreate_bank_name_et.getText().toString().trim();
        String bankUsername = sCreate_bank_username_et.getText().toString().trim();
        String bankName = sCreate_bankname_et.getText().toString().trim();
        String branchName = sCreate_branchname_et.getText().toString().trim();

        if (!MyUtils.hasValue(name)) {
            showToast("请填写姓名");
            return;
        }
        if (!MyUtils.hasValue(bankUsername)) {
            showToast("请填写银行帐号");
            return;
        }
        if (!MyUtils.hasValue(bankName)) {
            showToast("请填写银行卡所属银行");
            return;
        }
        if (!MyUtils.hasValue(branchName)) {
            showToast("请填写银行卡开户所在支行");
            return;
        }

        showLoadingDialog();

        if (isEdit) {  //编辑
            utilNetwork.getEditCardModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("name",
                    name + ""), new OkHttpClientManager.Param("bank",
                    bankName + ""), new OkHttpClientManager.Param("cardcode",
                    bankUsername + ""), new OkHttpClientManager.Param("infor",
                    branchName + ""), new OkHttpClientManager.Param("cardid",
                    listBean.getBankCardId() + "")});

        } else {  //添加
            utilNetwork.getAddCardModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("name",
                    name + ""), new OkHttpClientManager.Param("bank",
                    bankName + ""), new OkHttpClientManager.Param("cardcode",
                    bankUsername + ""), new OkHttpClientManager.Param("infor",
                    branchName + "")});
        }

    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.ADDCARD_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("添加成功");
                    //通知回去
                    Intent intent = new Intent();

                    setResult(BankCardActivity.BACKCREATE, intent);
                    finish();
                } else {
                    if (model != null) {
                        showToast(model.getErrmsg());
                    }
                }
                break;
            case Constant.EDITCARD_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("修改成功");
                    //通知回去
                    Intent intent = new Intent();
                    setResult(BankCardActivity.BACKCREATE, intent);
                    finish();
                } else {
                    if (model != null) {
                        showToast(model.getErrmsg());
                    }
                }

                break;
        }


    }
}
