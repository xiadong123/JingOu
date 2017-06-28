package com.jo.jingou.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.OrdersModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/13.
 */
public class OrderDetailActivity extends MyBaseActivity {


    String merchantoorderid = "";

    TextView
            order_state_tv, order_id_tv, order_time_tv,
            my_name_tv, my_phone_tv, my_address_tv,
            order_title_tv, order_number_tv, order_price_tv, order_payway_tv, order_postage_tv, order_remark_tv,
            order_allprice_tv, order_postageprice_tv, order_priceandpostage_tv,
            order_yundanbianbao_tv, order_chengyunlaiyuan_tv;

    View contactmerchant;

    LinearLayout orderEntity_linearlayout;

    TextView tv0, tv1;

    View time_layout;
    TextView time_daojishi;


    OrdersModel.ModelEntity modelEntity;
    String MerchantTel = "";


    long time;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
    int TIME_FLAG = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TIME_FLAG) {
                long t = (30 * 60 * 1000) - (System.currentTimeMillis() - time);

                Log.i("TAG", "t====" + t);
                Log.i("TAG", "System.currentTimeMillis()====" + System.currentTimeMillis());
                Log.i("TAG", "time====" + time);

                if (t > 0) {
                    String nowtime = simpleDateFormat.format(t);
                    time_daojishi.setText(nowtime);
                    sendEmptyMessageDelayed(TIME_FLAG, 1000);
                } else {
                    time_daojishi.setText("订单已过期");
                }
            }
        }
    };


    @Override
    public void initData() {
        layoutId = R.layout.activity_orderdetail;
    }

    @Override
    public void findViews() {
        //
        order_state_tv = (TextView) findViewById(R.id.activity_orderdetail_state);
        order_id_tv = (TextView) findViewById(R.id.activity_orderdetail_orderid);
        order_time_tv = (TextView) findViewById(R.id.activity_orderdetail_ordertime);

        //
        my_name_tv = (TextView) findViewById(R.id.activity_orderdetail_myname);
        my_phone_tv = (TextView) findViewById(R.id.activity_orderdetail_myphone);
        my_address_tv = (TextView) findViewById(R.id.activity_orderdetail_myaddress);

        //
        order_title_tv = (TextView) findViewById(R.id.item_orderdetail_alltitle);
        order_number_tv = (TextView) findViewById(R.id.item_orderdetail_allnumber);
        order_price_tv = (TextView) findViewById(R.id.item_orderdetail_price);
        order_payway_tv = (TextView) findViewById(R.id.activity_orderdetail_payway);
        order_postage_tv = (TextView) findViewById(R.id.item_orderdetail_postage);
        order_remark_tv = (TextView) findViewById(R.id.item_orderdetail_remark_tv);

        //
        order_allprice_tv = (TextView) findViewById(R.id.activity_order_allprice);
        order_postageprice_tv = (TextView) findViewById(R.id.activity_order_allpostages);
        order_priceandpostage_tv = (TextView) findViewById(R.id.activity_orderdetail_priceandpostages);

        //
        order_yundanbianbao_tv = (TextView) findViewById(R.id.activity_orderdetail_yundanbianhao);
        order_chengyunlaiyuan_tv = (TextView) findViewById(R.id.activity_orderdetail_chengyunlaiyuan);

        contactmerchant = findViewAndSetClick(R.id.activity_orderdetail_contactmerchant);

        orderEntity_linearlayout = (LinearLayout) findViewById(R.id.item_orderdetail_linear_linearlayout);

        tv0 = (TextView) findViewById(R.id.item_orderdetail_tv0);
        tv1 = (TextView) findViewById(R.id.item_orderdetail_tv1);
        time_layout = findViewById(R.id.item_orderdetail_timelayout);
        time_daojishi = (TextView) findViewById(R.id.item_orderdetail_time);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("订单详情");
        merchantoorderid = getIntent().getStringExtra("merchantoorderid");
        order_title_tv.setText("订单由 " + getIntent().getStringExtra("maintitles") + " 配送");

        showLoadingDialog();
        utilNetwork.getOrdersModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("merchantorderid", merchantoorderid)});


//        order_id_tv.setText("订单编号："+modelOrderEntity.getMerchantorderId());
//        order_time_tv.setText("下单时间："+"时间未返回");
//
//        my_name_tv.setText(modelOrderEntity.get);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLoadingDialog();
        utilNetwork.getOrdersModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("merchantorderid", merchantoorderid)});
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_orderdetail_contactmerchant:
                MyUtils.callPhone(this, MerchantTel);
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.ORDERS_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    modelEntity = ((OrdersModel) model).getModel();

                    MerchantTel = modelEntity.getMerchantTel();

                    order_id_tv.setText("订单编号：" + modelEntity.getMerchantNum());
                    order_time_tv.setText("下单时间：" + modelEntity.getTime());
                    my_name_tv.setText(modelEntity.getName());
                    my_phone_tv.setText("手机：" + modelEntity.getTel());
                    my_address_tv.setText("收货地址：" + modelEntity.getAdr());

                    order_number_tv.setText("共" + modelEntity.getPiece() + "件");

                    order_price_tv.setText("小计：￥" + modelEntity.getTotalPrice());
                    order_payway_tv.setText("支付宝");
                    //order_postage_tv.setText(modelEntity.getPostage());
                    order_postage_tv.setText("快递");
                    order_remark_tv.setText(modelEntity.getRemarks().endsWith(",") ? "" : modelEntity.getRemarks());

                    order_allprice_tv.setText(modelEntity.getTotalPrice());
                    order_postageprice_tv.setText(modelEntity.getPostage());
                    order_priceandpostage_tv.setText(modelEntity.getPayable());

                    order_yundanbianbao_tv.setText(modelEntity.getCouriernumber());
                    order_chengyunlaiyuan_tv.setText(modelEntity.getExpress());


                    LayoutInflater inflate = getLayoutInflater();

                    orderEntity_linearlayout.removeAllViews();
                    List<OrdersModel.ModelEntity.OrderModelEntity> orderModelEntitys = modelEntity.getOrderModel();
                    for (OrdersModel.ModelEntity.OrderModelEntity entity : orderModelEntitys) {
                        View v = inflate.inflate(R.layout.item_order_goods, null);
                        ((TextView) v.findViewById(R.id.item_order_goods_title)).setText(entity.getMainTitle());
                        ((TextView) v.findViewById(R.id.item_order_goods_price)).setText("¥ " + entity.getOriginalprice());
                        ((TextView) v.findViewById(R.id.item_order_goods_number)).setText("x" + entity.getAmount());
                        Glide.with(OrderDetailActivity.this).load(Constant.SERVER_HOST + entity.getListImgUrl()).into((ImageView) v.findViewById(R.id.item_order_goods_pic));
                        orderEntity_linearlayout.addView(v);
                    }

                    time = Long.parseLong(modelEntity.getOrderModel().get(0).getModifydate()) * 1000;
                    mHandler.sendEmptyMessage(TIME_FLAG);

                    setModelState();
                }
                break;
            case Constant.ORDERTYPE_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    modelEntity.setOrderState(4);
                    setModelState();
                }
                break;

            case Constant.CONFIRM_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    modelEntity.setOrderState(3);
                    setModelState();
                }
                break;
            case Constant.OREDETELE_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("删除成功");
                    finish();
                }
                break;

        }
    }

    private void setModelState() {
        int state = modelEntity.getOrderState();
        switch (state) {
            case 0:
                setModelEntityState(
                        "待付款",
                        View.VISIBLE,
                        View.VISIBLE, View.VISIBLE,
                        "取消订单", "立即支付",
                        getOrderTypeOnClickListener(), getToPayOnClickListener());
                break;
            case 1:
                setModelEntityState(
                        "已付款",
                        View.GONE,
                        View.VISIBLE, View.GONE,
                        "申请售后", "",
                        getAfterSalesOnClickListener("0"), null);
                break;
            case 2:
                setModelEntityState(
                        "已发货",
                        View.GONE,
                        View.VISIBLE, View.VISIBLE,
                        "申请售后", "确认收货",
                        getAfterSalesOnClickListener("1"), getConfirmModelOnClickListener());

                break;
            case 3:
                setModelEntityState(
                        "交易成功",
                        View.GONE,
                        View.VISIBLE, View.VISIBLE,
                        "申请售后", "立即评价",
                        getAfterSalesOnClickListener("2"), getToOrderCommentOnClickListener());
                break;
            case 4:
                setModelEntityState(
                        "已取消",
                        View.GONE,
                        View.VISIBLE, View.GONE,
                        "删除订单", "",
                        getOreDeteleOnClickListener(), null);
                break;
            case 5:
                setModelEntityState(
                        "已过期",
                        View.GONE,
                        View.VISIBLE, View.GONE,
                        "删除订单", "",
                        getOreDeteleOnClickListener(), null);
                break;
            case 6:
                setModelEntityState(
                        "已评价",
                        View.GONE,
                        View.VISIBLE, View.VISIBLE,
                        "删除订单", "申请售后",
                        getOreDeteleOnClickListener(), getAfterSalesOnClickListener("2"));
                break;
            case 8:
                setModelEntityState(
                        "退换货申请中",
                        View.GONE,
                        View.GONE, View.GONE,
                        "删除订单", "申请售后",
                        getOreDeteleOnClickListener(), getAfterSalesOnClickListener("all"));
                break;
        }
    }


    private void setModelEntityState(String state,
                                     int timelayoutVis,
                                     int button0Vis, int button1Vis,
                                     String tv0_txt, String tv1_txt,
                                     View.OnClickListener OnClickListener0, View.OnClickListener OnClickListener1) {
        order_state_tv.setText(state);
        time_layout.setVisibility(timelayoutVis);
        tv0.setVisibility(button0Vis);
        tv1.setVisibility(button1Vis);
        tv0.setText(tv0_txt);
        tv1.setText(tv1_txt);
        tv0.setOnClickListener(OnClickListener0);
        tv1.setOnClickListener(OnClickListener1);
    }


    //获取删除订单的onclicklistener
    @NonNull
    private View.OnClickListener getOreDeteleOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                utilNetwork.getOreDeteleModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("merchantorderid", merchantoorderid)});
            }
        };
    }

    //获取确认收货onclicklistener
    @NonNull
    private View.OnClickListener getConfirmModelOnClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                showLoadingDialog();
                utilNetwork.getConfirmModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("merchantorderid", merchantoorderid)});
            }
        };
    }

    //获取申请售后订单onclicklistener
    @NonNull
    private View.OnClickListener getAfterSalesOnClickListener(final String return_type) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                OrdersModel.ModelEntity.OrderModelEntity e = modelEntity.getOrderModel().get(0);
                Intent i = new Intent(OrderDetailActivity.this, AfterSalesActivity.class);
                i.putExtra("productid", e.getProductId() + "");
                i.putExtra("productpara", e.getParaid());
                i.putExtra("num", e.getAmount() + "");
                i.putExtra("money", e.getOriginalprice() + "");
                i.putExtra("orderid", e.getOrderId() + "");
                i.putExtra("return_type", return_type);
                startActivity(i);
            }
        };
    }

    //获取取消订单onclicklistener
    @NonNull
    private View.OnClickListener getOrderTypeOnClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                showLoadingDialog();
                utilNetwork.getOrderType(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("merchantorderid", merchantoorderid)});
            }
        };
    }

    //获取立即评价onclicklistener
    @NonNull
    private View.OnClickListener getToOrderCommentOnClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                MyUtils.commonORDERCOMMENT(OrderDetailActivity.this,
                        modelEntity.getOrderModel().get(0).getProductId() + "",
                        modelEntity.getMerchantNum() + "",
                        modelEntity.getOrderModel().get(0).getListImgUrl(),
                        modelEntity.getOrderModel().get(0).getMainTitle(),
                        modelEntity.getOrderModel().get(0).getOriginalprice(),
                        modelEntity.getOrderModel().get(0).getAmount() + "");
            }
        };
    }

    //获取立即支付onclicklistener
    @NonNull
    private View.OnClickListener getToPayOnClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                MyUtils.commonPAY(
                        OrderDetailActivity.this,
                        modelEntity.getMerchantorderId() + "",
                        modelEntity.getMerchantNum() + "",
                        Double.parseDouble(modelEntity.getTotalPrice()),
                        Long.parseLong(modelEntity.getOrderModel().get(0).getModifydate()) * 1000,
                        0);
            }
        };
    }


}
