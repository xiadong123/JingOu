package com.jo.jingou.activity;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.IDCardValidate;
import com.jo.jingou.utils.MyUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/11/30.
 */
public class ImproveInformationActivity extends MyBaseActivity {

    int toBuy = 0;

    View layout1, layout2, layout2_checklayout;
    EditText fathercode_ed, name_ed, idcode_ed;
    TextView idcode_title;
    TextView tv0, tv1, tv2, tv3;
    List<TextView> tvs = new ArrayList<>();

    int myid_type = 0;

    String type = "";
    String productid = "";
    String much = "";
    TextView agreement;
    Button submit, submit2;


    @Override
    public void initData() {
        layoutId = R.layout.activity_improve_information;
    }

    @Override
    public void findViews() {
        setTopView1();
        goneTopViews();
        centertxt.setVisibility(View.VISIBLE);
        centertxt.setText("完善资料");

        layout1 = findViewById(R.id.activity_improveinformation_layout1);
        layout2 = findViewById(R.id.activity_improveinformation_layout2);
        layout2_checklayout = findViewById(R.id.activity_improveinformation_layout2_checklayout);

        fathercode_ed = (EditText) findViewById(R.id.activity_improveinformation_code);

        tv0 = (TextView) findViewAndSetClick(R.id.activity_improveinformation_tv0);
        tv1 = (TextView) findViewAndSetClick(R.id.activity_improveinformation_tv1);
        tv2 = (TextView) findViewAndSetClick(R.id.activity_improveinformation_tv2);
        tv3 = (TextView) findViewAndSetClick(R.id.activity_improveinformation_tv3);
        tvs.add(tv0);
        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);

        name_ed = (EditText) findViewById(R.id.activity_improveinformation_name);
        idcode_ed = (EditText) findViewById(R.id.activity_improveinformation_mycode);
        idcode_title = (TextView) findViewById(R.id.activity_improveinformation_mycode_title);

        agreement = (TextView) findViewById(R.id.activity_improveinformation_agreement);

        submit = findViewAndSetClick(R.id.activity_improveinformation_submit);
        submit2 = findViewAndSetClick(R.id.activity_improveinformation_submit2);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        agreement.setText(Html.fromHtml("<font color=\"#999999\">提交表示同意并接受</font><font color=\"#BD925C\">《利益天下分红商城平台用户协议》</font>"));
        toBuy = getIntent().getIntExtra("toBuy", 0);
        type = getIntent().getStringExtra(Constant.BYTYPE);
        productid = getIntent().getStringExtra("productid");
        much = getIntent().getStringExtra("much");

        if ("fromRegister".equals(type)) {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            leftimg2.setVisibility(View.VISIBLE);
            leftimg2.setImageResource(R.drawable.close);
            righttxt.setVisibility(View.VISIBLE);
            righttxt.setText("跳过");
            submit.setVisibility(View.VISIBLE);
            submit2.setVisibility(View.GONE);
        } else if ("All".equals(type)) {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            leftimg3.setVisibility(View.VISIBLE);
            leftimg3.setImageResource(R.drawable.back);
            submit.setVisibility(View.GONE);
            submit2.setVisibility(View.VISIBLE);
        } else if ("isFatherCode".equals(type)) {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            leftimg3.setVisibility(View.VISIBLE);
            leftimg3.setImageResource(R.drawable.back);
            submit.setVisibility(View.GONE);
            submit2.setVisibility(View.VISIBLE);
            layout2_checklayout.setVisibility(View.VISIBLE);
        } else if ("isIdCode".equals(type)) {
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            leftimg3.setVisibility(View.VISIBLE);
            leftimg3.setImageResource(R.drawable.back);
            submit.setVisibility(View.GONE);
            submit2.setVisibility(View.VISIBLE);
        } else if ("hasIdCode".equals(type)) {
            centertxt.setText("实名认证");
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            leftimg3.setVisibility(View.VISIBLE);
            leftimg3.setImageResource(R.drawable.back);
            submit.setVisibility(View.GONE);
            submit2.setVisibility(View.GONE);
            agreement.setVisibility(View.GONE);
            layout2_checklayout.setVisibility(View.GONE);

            name_ed.setText(UserInfoLoader.getUserInfoModel().getModel().getRealName());
            idcode_ed.setText(UserInfoLoader.getUserInfoModel().getModel().getIdCard());

            String idtitle = "";
            switch (UserInfoLoader.getUserInfoModel().getModel().getCardType()) {
                case 0:
                    idtitle = "身份证号";
                    break;
                case 1:
                    idtitle = "护照号";
                    break;
                case 2:
                    idtitle = "台胞证号";
                    break;
                case 3:
                    idtitle = "港澳通行证号";
                    break;
            }
            idcode_title.setText(idtitle);

            name_ed.setFocusable(false);
            name_ed.setEnabled(false);
            idcode_ed.setFocusable(false);
            idcode_ed.setEnabled(false);
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.activity_improveinformation_submit:
            case R.id.activity_improveinformation_submit2:
                switch (type) {
                    case "fromRegister":
                    case "All":
                        if (hasFatherCode() && hasIdCode()) {
                            showLoadingDialog();
                            utilNetwork.getAllBindModel(new OkHttpClientManager.Param[]{
                                    new OkHttpClientManager.Param("fatherCode", fathercode_ed.getText().toString().trim()),
                                    new OkHttpClientManager.Param("name", name_ed.getText().toString().trim()),
                                    new OkHttpClientManager.Param("idcard", idcode_ed.getText().toString().trim()),
                                    new OkHttpClientManager.Param("type", myid_type + "")});
                        }
                        break;
                    case "isFatherCode":
                        if (hasFatherCode()) {
                            showLoadingDialog();
                            utilNetwork.getMemberBindModel(new OkHttpClientManager.Param[]{
                                    new OkHttpClientManager.Param("fatherCode", fathercode_ed.getText().toString().trim())});
                        }

                        break;
                    case "isIdCode":
                        if (hasIdCode()) {
                            showLoadingDialog();
                            utilNetwork.getIdCardBindModel(new OkHttpClientManager.Param[]{
                                    new OkHttpClientManager.Param("name", name_ed.getText().toString().trim()),
                                    new OkHttpClientManager.Param("idcard", idcode_ed.getText().toString().trim()),
                                    new OkHttpClientManager.Param("type", myid_type + "")});
                        }
                        break;
                }
                break;

            case R.id.base_topview_left:
                this.finish();
                break;
            case R.id.base_topview_right:
                MyUtils.setAndStartIntent(this, MainActivity.class);
                this.finish();
                break;
            case R.id.activity_improveinformation_tv0:
                myid_type = 0;
                idcode_title.setText("身份证号");
                onMyidTypeChanged();
                break;
            case R.id.activity_improveinformation_tv1:
                myid_type = 1;
                idcode_title.setText("护照号");
                onMyidTypeChanged();
                break;
            case R.id.activity_improveinformation_tv2:
                myid_type = 2;
                idcode_title.setText("台胞证号");
                onMyidTypeChanged();
                break;
            case R.id.activity_improveinformation_tv3:
                myid_type = 3;
                idcode_title.setText("港澳通行证号");
                onMyidTypeChanged();
                break;

        }
    }


    /**
     * 是否填写邀请码
     *
     * @return
     */
    private boolean hasFatherCode() {
        if (MyUtils.hasValue(fathercode_ed.getText().toString().trim())) {
            return true;
        } else {
            showToast("请输入邀请码");
            return false;
        }
    }

    /**
     * 是否填写实名认证信息
     *
     * @return
     */
    private boolean hasIdCode() {
        boolean b = true;

        try {
            if (!MyUtils.hasValue(name_ed.getText().toString().trim())) {
                showToast("请输入姓名");
                b = false;
            } else if (!MyUtils.hasValue(idcode_ed.getText().toString().trim())) {
                showToast("请输入身份证号");
                b = false;
            } else if (myid_type == 0 && MyUtils.hasValue(IDCardValidate.IDCardValidate(idcode_ed.getText().toString().trim()).trim())) {
                showToast(IDCardValidate.IDCardValidate(idcode_ed.getText().toString().trim()));
                b = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Log.i("TAG", "IDCardValidate=======" + IDCardValidate.IDCardValidate(idcode_ed.getText().toString().trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }


    private void onMyidTypeChanged() {
        for (TextView tv : tvs) {
            tv.setBackgroundResource(R.drawable.fillet_e6e6e6_1dp_4dp);
            tv.setTextColor(getResources().getColor(R.color.gary6));
        }
        tvs.get(myid_type).setBackgroundResource(R.drawable.fillet_b92c2c_4dp);
        tvs.get(myid_type).setTextColor(getResources().getColor(R.color.white));


    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.ALLBIND_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    MyApplication.setIsFatherCode("true");
                    MyApplication.setIsIdCode("true");

                    if (UserInfoLoader.getUserInfoModel() != null) {
                        UserInfoLoader.getUserInfoModel().getModel().setRealName(name_ed.getText().toString().trim());
                        UserInfoLoader.getUserInfoModel().getModel().setIdCard(idcode_ed.getText().toString().trim());
                        UserInfoLoader.getUserInfoModel().getModel().setCardType(myid_type);
                    }

                    if (toBuy == 1)
                        MyUtils.commonBUY(this, productid, much, "", "", 11);
                    this.finish();
                }
                break;
            case Constant.MEMBERBIND_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    MyApplication.setIsFatherCode("true");
                    if (toBuy == 1)
                        MyUtils.commonBUY(this, productid, much, "", "", 11);
                    this.finish();
                }
                break;
            case Constant.IDCARDBIND_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    MyApplication.setIsIdCode("true");
                    UserInfoLoader.getUserInfoModel().getModel().setRealName(name_ed.getText().toString().trim());
                    UserInfoLoader.getUserInfoModel().getModel().setIdCard(idcode_ed.getText().toString().trim());

                    if (toBuy == 1)
                        MyUtils.commonBUY(this, productid, much, "", "", 11);
                    this.finish();
                }
                break;
        }
    }

}
