package com.jo.jingou.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.AliPayModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.PayResult;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/7.
 *
 */
public class PayActivity extends MyBaseActivity {


    View zhifubao_layout, weixin_layout;
    ImageView zhifubao_check, weixin_check;

    TextView time_tv;
    TextView money_tv;
    View submit;

    String payway = "zhifubao";

    String orderid = "";
    String ordernum = "";
    double money;
    long time;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");


    int SDK_PAY_FLAG = 0, TIME_FLAG = 1;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SDK_PAY_FLAG) {
//                // 此处可以更新UI
//                zhifuDialog.cancel();
                //        payforbean = (PayforBean) response.body();

                PayResult result = new PayResult((String) msg.obj);
                // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                Log.i("TAG", "result.getResultStatus()========" + result.getResultStatus());
                switch (result.getResultStatus()) {
                    case "9000":
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        startToPayCallBackActivity(true);
                        break;
                    case "8000":
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case "6001":
                        // 取消订单
                        Toast.makeText(PayActivity.this, "用户已取消", Toast.LENGTH_SHORT).show();
                        break;
                    case "4000":
                        // 取消订单
                        Toast.makeText(PayActivity.this, "订单支付失败", Toast.LENGTH_SHORT).show();
                        startToPayCallBackActivity(false);
                        break;
                    case "6002":
                        // 取消订单
                        Toast.makeText(PayActivity.this, "网络连接出错", Toast.LENGTH_SHORT).show();
                        startToPayCallBackActivity(false);
                        break;
                    default:
                        // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                        Toast.makeText(PayActivity.this, "支付宝支付失败", Toast.LENGTH_SHORT).show();
                        startToPayCallBackActivity(false);
                }
            } else if (msg.what == TIME_FLAG) {
                long t = (30 * 60 * 1000) - (System.currentTimeMillis() - time);

                Log.i("TAG", "t====" + t);
                Log.i("TAG", "System.currentTimeMillis()====" + System.currentTimeMillis());
                Log.i("TAG", "time====" + time);

                if (t > 0) {
                    String nowtime = simpleDateFormat.format(t);
                    time_tv.setText(nowtime);
                    sendEmptyMessageDelayed(TIME_FLAG, 1000);
                } else {
                    time_tv.setText("订单已过期");
                }


            }
        }
    };

    @Override
    public void initData() {
        layoutId = R.layout.activity_pay;
    }

    @Override
    public void findViews() {
        zhifubao_layout = findViewAndSetClick(R.id.activity_pay_zhifubao_layout);
        weixin_layout = findViewAndSetClick(R.id.activity_pay_weixin_layout);
        zhifubao_check = (ImageView) findViewById(R.id.activity_pay_zhifubao_check);
        weixin_check = (ImageView) findViewById(R.id.activity_pay_weixin_check);

        time_tv = (TextView) findViewById(R.id.activity_pay_time);
        money_tv = (TextView) findViewById(R.id.activity_pay_money);
        submit = findViewAndSetClick(R.id.activity_pay_submit);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopView1();
        goneTopViews();
        centertxt.setVisibility(View.VISIBLE);
        centertxt.setText("支付中心");
        leftimg2.setVisibility(View.VISIBLE);
        leftimg2.setImageResource(R.drawable.close);

        orderid = getIntent().getStringExtra("orderid");
        ordernum = getIntent().getStringExtra("ordernum");
        money = getIntent().getDoubleExtra("money", 0);
        time = getIntent().getLongExtra("time", 0);
        mHandler.sendEmptyMessage(TIME_FLAG);
        money_tv.setText("￥" + money);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_pay_zhifubao_layout:
                setPayway("zhifubao");
                break;
            case R.id.activity_pay_weixin_layout:
                setPayway("weixin");
                break;
            case R.id.activity_pay_submit:
                if ("zhifubao".equals(payway)) {
                    showLoadingDialog("正在生成订单...", false);
                    utilNetwork.getAliPayModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("out_trade_no", ordernum)});
                } else {
                    final IWXAPI msgApi = WXAPIFactory.createWXAPI(PayActivity.this, null);
                    // 将该app注册到微信
                    msgApi.registerApp("wxbd3e6bba8efbae73");
                }

                showLoadingDialog();
                utilNetwork.getAddCeshiAlipayModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("out_trade_no", ordernum)});
                break;
        }
    }


    public void setPayway(String payway) {
        this.payway = payway;
        zhifubao_check.setImageResource(R.drawable.unselected);
        weixin_check.setImageResource(R.drawable.unselected);

        switch (payway) {
            case "zhifubao":
                zhifubao_check.setImageResource(R.drawable.selected);
                break;
            case "weixin":
                weixin_check.setImageResource(R.drawable.selected);
                break;
        }
    }

    private void onAliPayCallback(final String info) {
        final String orderInfo = info; // 订单信息
        Runnable payRunnable = new Runnable() {
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Log.i("TAG", "info=====" + info);
                String result = alipay.pay(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    public void startToPayCallBackActivity(boolean s) {
        Intent i = new Intent(this, PayCallBackActivity.class);
        if (s) {
            i.putExtra("orderid", orderid);
            i.putExtra("paycallback", true);
            startActivity(i);
            finish();
        } else {
            i.putExtra("orderid", orderid);
            i.putExtra("paycallback", false);
            startActivity(i);
        }

    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.ALIPAY_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    String info = ((AliPayModel) model).getData();
                    onAliPayCallback(info);
                }
                break;

            case Constant.ADDCESHIALIPAY_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    startToPayCallBackActivity(true);
                }
                break;
        }
    }
}
