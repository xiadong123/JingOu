package com.jo.jingou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import caesar.feng.framework.net.OkHttpClientManager;
import caesar.feng.framework.utils.Utility;

/**
 * Created by Administrator on 2016/12/11.
 */

public class MyCredit_DuihuanActivity extends MyBaseActivity {


    TextView number_ed;

    View cancel, add;

    View submit;


    @Override
    public void initData() {
        layoutId = R.layout.activity_mycredit_duihuan;
    }

    @Override
    public void findViews() {
        number_ed = (TextView) findViewById(R.id.number);
        cancel = findViewAndSetClick(R.id.cancel);
        add = findViewAndSetClick(R.id.add);
        submit = findViewAndSetClick(R.id.submit);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("兑换分红账号");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        String number = number_ed.getText().toString().trim();
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.cancel:
                if (MyUtils.hasValue(number)) {
                    if (Integer.parseInt(number) > 0) {
                        number_ed.setText(Integer.parseInt(number) - 1 + "");
                    }
                } else {
                    number_ed.setText("0");
                }
                break;
            case R.id.add:
                if (MyUtils.hasValue(number)) {
                    number_ed.setText(Integer.parseInt(number) + 1 + "");
                } else {
                    number_ed.setText("1");
                }
                break;
            case R.id.submit:


                if (MyUtils.isLogin()) {
                    if (MyUtils.CanBuyAngle() == 0) {
                        // 购买操作
                        submit(number);
                        //MyUtils.commonBUY(AngelDetailActivity.this, ProductId, productNumber + "", "", "", 11);
                    } else if (!MyUtils.isFatherCode() && !MyUtils.isIdCode()) {
                        toBuyAngel(ImproveInformationActivity.class, "All");
                    } else if (!MyUtils.isIdCode()) {
                        toBuyAngel(ImproveInformationActivity.class, "isIdCode");
                    } else if (!MyUtils.isFatherCode()) {
                        toBuyAngel(ImproveInformationActivity.class, "isFatherCode");
                    }
                } else {
                    toBuyAngel(Login_Register_ResetPasswordActivity.class, "bylogin");
                }


                break;
        }
    }

    private void submit(String number) {
        if (MyUtils.hasValue(number) && !"0".equals(number)) {
            showLoadingDialog();
            utilNetwork.getIntegralExchangeModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("number", number)});
        } else {
            Utility.showToast(this, "请设置需要兑换的账号数量");
        }
    }

    private void toBuyAngel(Class<?> c, String type) {
        Intent i = new Intent(this, c);
        i.putExtra(Constant.BYTYPE, type);
        i.putExtra("toBuy", 0);
        i.putExtra("productid", "0");
        i.putExtra("much", "0");
        startActivity(i);
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.INTEGRALEXCHAGE_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    Utility.showToast(this, "兑换成功");
                    finish();
                } else {
                    Utility.showToast(this, model.getErrmsg());
                }
                break;
        }
    }
}
