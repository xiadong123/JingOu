package com.jo.jingou.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.AddressModel;
import com.jo.jingou.model.AngelBuyModel;
import com.jo.jingou.model.OrderModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_OrderPager_AddView;

import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/11/28.
 */
public class OrderActivity extends MyBaseActivity {

    LinearLayout centerlayout;
    //收货地址
    View hasaddress_layout, notaddress_layout;
    TextView hasaddress_name, hasaddress_phone, hasaddress_address;

    //总金额  运费  总金额（含运费）
    TextView allprice_tv, allpostages_tv, priceandpostages_tv;

    //View activity_order_submit
    Button submit;

    String productid = "";
    String much = "";
    String memberPlaceId = "";
    String productpara = "";
    String ordernum = "";
    String orderid = "";
    boolean isfromcart = false;
    double money;


    @Override
    public void initData() {
        layoutId = R.layout.activity_order;
    }

    @Override
    public void findViews() {
        centerlayout = (LinearLayout) findViewById(R.id.activity_order_centerview);
        hasaddress_layout = findViewAndSetClick(R.id.activity_order_hasaddress);
        notaddress_layout = findViewAndSetClick(R.id.activity_order_notaddress);
        hasaddress_name = (TextView) findViewById(R.id.activity_order_name);
        hasaddress_phone = (TextView) findViewById(R.id.activity_order_phone);
        hasaddress_address = (TextView) findViewById(R.id.activity_order_address);

        allprice_tv = (TextView) findViewById(R.id.activity_order_allprice);
        allpostages_tv = (TextView) findViewById(R.id.activity_order_allpostages);
        priceandpostages_tv = (TextView) findViewById(R.id.activity_order_priceandpostages);
        submit = findViewAndSetClick(R.id.activity_order_submit);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("订单结算");
        productid = getIntent().getStringExtra("ProductId");
        much = getIntent().getStringExtra("Much");
        productpara = getIntent().getStringExtra("ProductPara") + "";
        isfromcart = getIntent().getBooleanExtra("isfromcart", false);

        showLoadingDialog();


        if (!isfromcart)
            utilNetwork.getAngelBuyModel(
                    new OkHttpClientManager.Param[]{
                            new OkHttpClientManager.Param("productid", productid),
                            new OkHttpClientManager.Param("much", much),
                            new OkHttpClientManager.Param("productpara", productpara)});
        else
            utilNetwork.getSettlementModel(
                    new OkHttpClientManager.Param[]{
                            new OkHttpClientManager.Param("productid", productid),
                            new OkHttpClientManager.Param("much", much),
                            new OkHttpClientManager.Param("productpara", productpara)});


    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent i;
        switch (view.getId()) {
            case R.id.base_topview_left:
                this.finish();
                break;

            case R.id.activity_order_hasaddress:
                i = new Intent(this, AddressListActivity.class);
                startActivityForResult(i, 0);
                break;
            case R.id.activity_order_notaddress:
                i = new Intent(this, AddAddressActivity.class);
                startActivityForResult(i, 0);
                break;
            case R.id.activity_order_submit:
                if (!MyUtils.hasValue(memberPlaceId)) {
                    showToast("请填写收货地址");
                    return;
                }

                int count = centerlayout.getChildCount();
                String remark = "";
                for (int j = 0; j < count; j++) {
                    String r = ((TextView) centerlayout.getChildAt(j).findViewById(R.id.item_order_business_remark_tv)).getText().toString();
                    remark += r + "&";
                }

//                if (remark.endsWith(","))
//                    remark = remark.substring(0, remark.length() - 1);


                showLoadingDialog("正在提交订单，请稍后", false);
                utilNetwork.getOrderModel(
                        new OkHttpClientManager.Param[]{
                                new OkHttpClientManager.Param("productid", productid),
                                new OkHttpClientManager.Param("much", much),
                                new OkHttpClientManager.Param("productpara", productpara),
                                new OkHttpClientManager.Param("memberPlaceId", memberPlaceId),
                                new OkHttpClientManager.Param("remarks", remark)});
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.ANGELBUY_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    AngelBuyModel angelBuyModel = (AngelBuyModel) model;

                    //设置收货地址
                    if (angelBuyModel.getMemberplace().size() == 0) {
                        notaddress_layout.setVisibility(View.VISIBLE);
                        hasaddress_layout.setVisibility(View.GONE);
                    } else {
                        notaddress_layout.setVisibility(View.GONE);
                        hasaddress_layout.setVisibility(View.VISIBLE);
                        AngelBuyModel.MemberplaceEntity place = angelBuyModel.getMemberplace().get(0);
                        memberPlaceId = place.getMemberPlaceId() + "";
                        hasaddress_name.setText(place.getName());
                        hasaddress_phone.setText("手机：" + place.getTel());
                        hasaddress_address.setText("收货地址：" + place.getAddress());
                    }

                    //设置商品详情
                    List<AngelBuyModel.PrpductEntity> PrpductEntitys = angelBuyModel.getPrpduct();
                    List<View> views = Util_OrderPager_AddView.addView(this, PrpductEntitys);
                    centerlayout.removeAllViews();
                    for (View v : views)
                        centerlayout.addView(v);

                    //设置总金额及运费
                    allprice_tv.setText(angelBuyModel.getLast());
                    allpostages_tv.setText(angelBuyModel.getPostages() + "");
                    priceandpostages_tv.setText(angelBuyModel.getMoney());

                } else {
                    if (model != null) {
                        showToast(model.getErrmsg());
                    }
                    finish();
                }
                break;
            case Constant.ORDER_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    submit.setText("去支付");
                    OrderModel orderModel = (OrderModel) model;
                    orderid = orderModel.getOrderid() + "";
                    ordernum = orderModel.getOerdernum() + "";
                    money = orderModel.getMoney();
                    MyUtils.commonPAY(this, orderid, ordernum, money, System.currentTimeMillis(), 0);
                    this.finish();
                } else {
                    showToast(model.getErrmsg());
                }
                break;

            case Constant.ADDRESS_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    AddressModel addressModel = (AddressModel) model;
                    List<AddressModel.ModelPlacesEntity> places = addressModel.getModelPlaces();
                    for (AddressModel.ModelPlacesEntity entity : places) {
                        if ((entity.getMemberPlaceId() + "").equals(memberPlaceId)) {
                            memberPlaceId = entity.getMemberPlaceId() + "";
                            hasaddress_name.setText(entity.getName());
                            hasaddress_phone.setText("手机：" + entity.getTel());
                            hasaddress_address.setText("收货地址：" + entity.getAddress());
                        }
                    }
                } else {

                }
                break;
        }
    }

    boolean hascheckaddress;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            if (data != null) {
                String type = data.getStringExtra("type");
                if ("checkaddress".equals(type)) {
                    hascheckaddress = true;
                    memberPlaceId = data.getIntExtra("memberplaceid", 0) + "";
                }
            }
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (!hascheckaddress) {
            showLoadingDialog();
            utilNetwork.getAngelBuyModel(new OkHttpClientManager.Param[]{
                    new OkHttpClientManager.Param("productid", productid),
                    new OkHttpClientManager.Param("much", much),
                    new OkHttpClientManager.Param("productpara", productpara)});
        } else {
            showLoadingDialog();
            utilNetwork.getAddressModel(new OkHttpClientManager.Param[]{});
        }
    }
}
