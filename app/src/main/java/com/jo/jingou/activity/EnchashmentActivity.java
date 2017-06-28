package com.jo.jingou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.MoneyFaceModel;
import com.jo.jingou.model.UserInfoModel;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_PopupWindow;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2017/3/24.
 */
public class EnchashmentActivity extends MyBaseActivity {

    private static final int GOTOBANKCARD = 3004;
    public static final int BACKBANKCARD = 3005;
    public static final int BACKBANKCARDHASDATA = 3006;
    private UserInfoModel.ModelEntity userInfoModel;
    View layout0;
    TextView tv0, cardum, bankName, branchname, name, changeCard, tel;
    EditText ed3;
    PopupWindow pop_enchasment;
    View submit;

    int type = 1;

    @Override
    public void initData() {
        layoutId = R.layout.activity_enchashment;
    }

    @Override
    public void findViews() {
        layout0 = findViewAndSetClick(R.id.activity_enchashment_layout0);
        tv0 = (TextView) findViewById(R.id.activity_enchashment_layout0_tv0);
        ed3 = (EditText) findViewById(R.id.activity_enchashment_price_ed);
        cardum = (TextView) findViewById(R.id.activity_enchashment_cardnum_tv1);
        bankName = (TextView) findViewById(R.id.activity_enchashment_bankname_tv);
        branchname = (TextView) findViewById(R.id.activity_enchashment_branchname_tv);
        name = (TextView) findViewById(R.id.activity_enchashment_name_tv);
        changeCard = (TextView) findViewById(R.id.activity_enchashment_changecard_tv);
        tel = (TextView) findViewById(R.id.activity_enchashment_tel_tv1);
        submit = findViewAndSetClick(R.id.submit);

        userInfoModel = UserInfoLoader.getUserInfoModel().getModel();
        if (userInfoModel.getApplyStatus() == 1) {
            pop_enchasment = Util_PopupWindow.setPopupWindow_Enchashment2(this, new Util_PopupWindow.OnCallBack_int_string() {
                @Override
                public void onSexCallBack(int sextype, String sex) {
                    type = sextype;
                    tv0.setText(sex);
                }
            }, "提现");
        } else {
            pop_enchasment = Util_PopupWindow.setPopupWindow_Enchashment(this, new Util_PopupWindow.OnCallBack_int_string() {
                @Override
                public void onSexCallBack(int sextype, String sex) {
                    type = sextype;
                    tv0.setText(sex);
                }
            }, "提现");
        }
    }

    @Override
    public void initListener() {
        changeCard.setOnClickListener(this);
    }

    @Override
    public void setupViews() {
        setTopview2("账号提现");
        getMoneyFace();  //请求服务器 获取到提现界面的数据 (展示)
    }

    /**
     * 请求服务器 获取到提现界面的数据 (展示)
     */
    private void getMoneyFace() {
        showLoadingDialog();
        utilNetwork.getMoneyFaceModel(new OkHttpClientManager.Param[]{});
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_enchashment_layout0:
                Util_PopupWindow.show(pop_enchasment, this);
                break;
            case R.id.submit:
                submit();
                break;
            case R.id.activity_enchashment_changecard_tv:
                Intent intent = new Intent(this, BankCardActivity.class);
                startActivityForResult(intent, GOTOBANKCARD);
                break;
        }
    }

    private void submit() {
        if (!MyUtils.hasValue(cardum.getText().toString().trim())) {
            showToast("请选择银行卡");
            return;
        }

        if (!MyUtils.hasValue(bankName.getText().toString().trim())) {
            showToast("请选择银行卡");
            return;
        }

        if (!MyUtils.hasValue(branchname.getText().toString().trim())) {
            showToast("请选择银行卡");
            return;
        }

        if (!MyUtils.hasValue(ed3.getText().toString().trim())) {
            showToast("请输入提现金额");
            return;
        }


        try {
            if (Double.parseDouble(ed3.getText().toString().trim()) <= 0) {
                showToast("提现金额必须大于0");
                return;
            }
        } catch (Exception e) {
            showToast("请输入正确的金额");
            return;
        }


        showLoadingDialog();
        utilNetwork.getWithDrawMoneyModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("money", ed3.getText().toString().trim()),
                        new OkHttpClientManager.Param("type", type + ""),
                        new OkHttpClientManager.Param("bankCard", cardum.getText().toString().trim()),
                        new OkHttpClientManager.Param("bank", bankName.getText().toString().trim()),
                        new OkHttpClientManager.Param("bankSon", branchname.getText().toString().trim())
                });
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T
            model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.WITHDRAWMOINEY_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("提现申请提交成功");
                    Intent intent = new Intent();
                    setResult(MyPurseActivity.BACKENCHASHMENTREFRESH, intent);
                    finish();
                } else {
                    if (model != null)
                        showToast(model.getErrmsg());
                }
                break;
            case Constant.MONEYFACE_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    MoneyFaceModel moneyFaceModel = (MoneyFaceModel) model;
                    setCardData(moneyFaceModel);  //数据请求成功
                } else {
                    if (model != null)
                        showToast(model.getErrmsg());
                }
                break;
        }
    }

    private void setCardData(MoneyFaceModel moneyFaceModel) {
        if (moneyFaceModel.getList().size() > 0) {
            MoneyFaceModel.ListBean listBean = moneyFaceModel.getList().get(0);
            tel.setText(listBean.getTel());
            cardum.setText(listBean.getBankCardCode());
            bankName.setText(listBean.getBank());
            branchname.setText(listBean.getBankInformation());
            name.setText(listBean.getMember());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOTOBANKCARD) {
            switch (resultCode) {
                case BACKBANKCARD:
                    getMoneyFace();
                    break;
                case BACKBANKCARDHASDATA:
                    String mCardum = data.getStringExtra("cardum");
                    String mBankName = data.getStringExtra("bankName");
                    String mBranchname = data.getStringExtra("branchname");
                    if ("".equals(tel.getText().toString())) {
                        tel.setText(userInfoModel.getMobile() + "");
                    }
                    cardum.setText(mCardum);
                    bankName.setText(mBankName);
                    branchname.setText(mBranchname);

                    break;
            }

        }


    }
}
