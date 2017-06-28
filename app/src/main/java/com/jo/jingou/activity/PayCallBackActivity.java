package com.jo.jingou.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.utils.MyUtils;

/**
 * Created by dfyu on 2016/12/11.
 */
public class PayCallBackActivity extends MyBaseActivity {


    ImageView iv;
    TextView tv, btn1, btn2;

    boolean payok;


    @Override
    public void initData() {
        layoutId = R.layout.activity_paycallback;
    }

    @Override
    public void findViews() {
        iv = (ImageView) findViewById(R.id.activity_paycallback_iv);
        tv = findViewAndSetClick(R.id.activity_paycallback_tv);
        btn1 = findViewAndSetClick(R.id.activity_paycallback_btn1);
        btn2 = findViewAndSetClick(R.id.activity_paycallback_btn2);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {

        setTopView1();
        goneTopViews();


        leftimg3.setVisibility(View.VISIBLE);
        leftimg3.setImageResource(R.drawable.back);


        payok = getIntent().getBooleanExtra("paycallback", false);
        if (payok) {
            centertxt.setVisibility(View.VISIBLE);
            centertxt.setText("支付成功");
            iv.setImageResource(R.drawable.payok);
            tv.setText("付款成功，我们会尽快为您配送");
            btn1.setText("查看订单");
//            btn2.setText("查看订单");
        } else {
            centertxt.setText("支付失败");
            iv.setImageResource(R.drawable.failure);
            tv.setText("付款失败，查看 订单详情 >>");
            btn1.setText("继续支付");
//            btn2.setText("取消订单");
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;

            case R.id.activity_paycallback_tv:
                if (payok) {

                } else {

                }
                break;
            case R.id.activity_paycallback_btn1:
                if (payok) {
                    MyUtils.setAndStartIntent(this, MyOrderListActivity.class);
                } else {

                }
                break;
//            case R.id.activity_paycallback_btn2:
//                if (payok) {
//                    finish();
//                } else {
//
//                }
//                break;
        }


    }
}
