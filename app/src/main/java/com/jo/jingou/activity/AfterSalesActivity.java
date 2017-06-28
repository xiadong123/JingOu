package com.jo.jingou.activity;

import android.content.Intent;
import android.view.View;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.utils.MyUtils;

/**
 * Created by dfyu on 2017/3/24.
 */
public class AfterSalesActivity extends MyBaseActivity {


    View layout0;
    View layout1;
    View layout2;

    String productid, num, money, orderid, return_type;

    @Override
    public void initData() {
        layoutId = R.layout.activity_aftersales;
    }

    @Override
    public void findViews() {
        layout0 = findViewAndSetClick(R.id.activity_aftersales_layout0);
        layout1 = findViewAndSetClick(R.id.activity_aftersales_layout1);
        layout2 = findViewAndSetClick(R.id.activity_aftersales_layout2);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("申请售后");
//        productid = getIntent().getStringExtra("productid");
//        num = getIntent().getStringExtra("num");
//        money = getIntent().getStringExtra("money");
//        orderid = getIntent().getStringExtra("orderid");
        return_type = getIntent().getStringExtra("return_type");


        if (MyUtils.hasValue(return_type))
            if (return_type.equals("0")) {
                layout0.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
            } else if (return_type.equals("1")) {
                layout2.setVisibility(View.GONE);
            } else if (return_type.equals("2")) {
                layout0.setVisibility(View.GONE);
                layout1.setVisibility(View.GONE);
            }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent i = getIntent();
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_aftersales_layout0:
                i.setClass(this, AddAfterSalesActivity.class);
                i.putExtra("type", "0");
                startActivity(i);
                break;
            case R.id.activity_aftersales_layout1:
                i.setClass(this, AddAfterSalesActivity.class);
                i.putExtra("type", "1");
                startActivity(i);
                break;
            case R.id.activity_aftersales_layout2:
                i.setClass(this, AddAfterSalesActivity.class);
                i.putExtra("type", "2");
                startActivity(i);
                break;
        }
    }
}
