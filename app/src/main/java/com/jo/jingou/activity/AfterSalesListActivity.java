package com.jo.jingou.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.ReturnListModel;
import com.jo.jingou.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.adapter.CommonAdapter;
import caesar.feng.framework.adapter.ViewHolder;
import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2017/5/18.
 */
public class AfterSalesListActivity extends MyBaseActivity {


    SwipeRefreshLayout srl;
    ListView lv;

    List<ReturnListModel.ModelEntity> list = new ArrayList<>();
    CommonAdapter<ReturnListModel.ModelEntity> commonAdapter;


    @Override
    public void initData() {
        layoutId = R.layout.activity_aftersaleslist;
    }

    @Override
    public void findViews() {
        srl = (SwipeRefreshLayout) findViewById(R.id.activity_aftersaleslist_sw);
        lv = (ListView) findViewById(R.id.activity_aftersaleslist_lv);


        setNoData_LoadFail_ReLoadViews("没有退换货记录，感谢您对我们的支持！");

        commonAdapter = new CommonAdapter<ReturnListModel.ModelEntity>(this, list, R.layout.item_aftersaleslist_listitem) {
            @Override
            public void convert(ViewHolder viewHolder, ReturnListModel.ModelEntity modelEntity) {
                LinearLayout linearLayout = viewHolder.getView(R.id.item_aftersales_listitem_linearlayout);

                viewHolder.setText(R.id.item_aftersales_listitem_Merchantname, modelEntity.getMerchantname());
                viewHolder.setText(R.id.item_aftersales_listitem_Money, modelEntity.getMoney());
                viewHolder.setText(R.id.item_aftersales_listitem_ProductMoney, modelEntity.getProductMoney());
                switch (modelEntity.getApplication()) {
                    case 0:
                        viewHolder.setText(R.id.item_aftersales_listitem_Application, "申请中");
                        break;
                    case 1:
                        viewHolder.setText(R.id.item_aftersales_listitem_Application, "申请成功");
                        break;
                    case 2:
                        viewHolder.setText(R.id.item_aftersales_listitem_Application, "申请失败");
                        break;
                }

                View v = getLayoutInflater().inflate(R.layout.item_order_goods, null);

                ((TextView) v.findViewById(R.id.item_order_goods_title)).setText(modelEntity.getProductname());
                ((TextView) v.findViewById(R.id.item_order_goods_price)).setText("¥ " + modelEntity.getSinglePrice());
                ((TextView) v.findViewById(R.id.item_order_goods_number)).setText("x" + modelEntity.getNumber() + "");
                Glide.with(AfterSalesListActivity.this).load(Constant.SERVER_HOST + modelEntity.getListImgUrl()).into((ImageView) v.findViewById(R.id.item_order_goods_pic));
                linearLayout.removeAllViews();
                linearLayout.addView(v);
            }
        };


    }

    @Override
    public void initListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AfterSalesListActivity.this, AfterSalesDetailActivity.class);
                intent.putExtra("returnsid", list.get(i).getReturnsId() + "");
                startActivity(intent);
            }
        });
    }

    @Override
    public void setupViews() {
        setTopview2("退款/售后");

        lv.setAdapter(commonAdapter);

        srl.setRefreshing(true);
        getData();

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.reload:
                srl.setRefreshing(true);
                getData();
                break;
        }
    }

    private void getData() {
        utilNetwork.getReturnListModel(new OkHttpClientManager.Param[]{});
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);

        switch (network_code) {
            case Constant.RETURNLIST_ID:
                if (status_code == 0) {
                    loadfail.setVisibility(View.GONE);

                    srl.setRefreshing(false);

                    ReturnListModel returnListModel = (ReturnListModel) model;

                    list.clear();
                    list.addAll(returnListModel.getModel());
                    commonAdapter.notifyDataSetChanged();

                    if (list.size() == 0) {
                        nodata.setVisibility(View.VISIBLE);
                    } else {
                        nodata.setVisibility(View.GONE);
                    }

                } else {
                    loadfail.setVisibility(View.VISIBLE);
                }

                break;
        }


    }
}
