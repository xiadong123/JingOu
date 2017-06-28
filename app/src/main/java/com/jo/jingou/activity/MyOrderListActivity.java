package com.jo.jingou.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.adapter.ViewPagerAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.OrderListModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.view.ScllorTabView;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.adapter.CommonAdapter;
import caesar.feng.framework.adapter.ViewHolder;
import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/12.
 */
public class MyOrderListActivity extends MyBaseActivity {


    List<TextView> tv_list = new ArrayList<>();
    TextView
            tv0,
            tv1,
            tv2,
            tv3,
            tv4;

    ScllorTabView scllorTabView;
    ViewPager viewPager;

    List<View> pager_views = new ArrayList<>();


    View
            pageItem0,
            pageItem1,
            pageItem2,
            pageItem3,
            pageItem4;

    SwipeRefreshLayout
            swipeRefreshLayout0,
            swipeRefreshLayout1,
            swipeRefreshLayout2,
            swipeRefreshLayout3,
            swipeRefreshLayout4;

    ListView
            listView0,
            listView1,
            listView2,
            listView3,
            listView4;

    TextView
            listView0_hint,
            listView1_hint,
            listView2_hint,
            listView3_hint,
            listView4_hint;


    CommonAdapter<OrderListModel.ModelEntity>
            commonAdapter0,
            commonAdapter1,
            commonAdapter2,
            commonAdapter3,
            commonAdapter4;

    List<OrderListModel.ModelEntity>
            modellist_all = new ArrayList<>(),
            modellist_1 = new ArrayList<>(),
            modellist_2 = new ArrayList<>(),
            modellist_3 = new ArrayList<>(),
            modellist_4 = new ArrayList<>();


    @Override
    public void initData() {
        layoutId = R.layout.activity_myorderlist;
    }

    @Override
    public void findViews() {
        tv0 = findViewAndSetClick(R.id.activity_myorderlist_tv0);
        tv1 = findViewAndSetClick(R.id.activity_myorderlist_tv1);
        tv2 = findViewAndSetClick(R.id.activity_myorderlist_tv2);
        tv3 = findViewAndSetClick(R.id.activity_myorderlist_tv3);
        tv4 = findViewAndSetClick(R.id.activity_myorderlist_tv4);
        tv_list.add(tv0);
        tv_list.add(tv1);
        tv_list.add(tv2);
        tv_list.add(tv3);
        tv_list.add(tv4);

        scllorTabView = (ScllorTabView) findViewById(R.id.activity_myorderlist_scllortabview);
        viewPager = (ViewPager) findViewById(R.id.activity_myorderlist_viewpager);

        pageItem0 = getLayoutInflater().inflate(R.layout.item_myorderlist_pageritem, null);
        pageItem1 = getLayoutInflater().inflate(R.layout.item_myorderlist_pageritem, null);
        pageItem2 = getLayoutInflater().inflate(R.layout.item_myorderlist_pageritem, null);
        pageItem3 = getLayoutInflater().inflate(R.layout.item_myorderlist_pageritem, null);
        pageItem4 = getLayoutInflater().inflate(R.layout.item_myorderlist_pageritem, null);

        swipeRefreshLayout0 = (SwipeRefreshLayout) pageItem0.findViewById(R.id.item_myorderlist_pageritem_swiperefreshlayout);
        swipeRefreshLayout1 = (SwipeRefreshLayout) pageItem1.findViewById(R.id.item_myorderlist_pageritem_swiperefreshlayout);
        swipeRefreshLayout2 = (SwipeRefreshLayout) pageItem2.findViewById(R.id.item_myorderlist_pageritem_swiperefreshlayout);
        swipeRefreshLayout3 = (SwipeRefreshLayout) pageItem3.findViewById(R.id.item_myorderlist_pageritem_swiperefreshlayout);
        swipeRefreshLayout4 = (SwipeRefreshLayout) pageItem4.findViewById(R.id.item_myorderlist_pageritem_swiperefreshlayout);

        listView0 = (ListView) pageItem0.findViewById(R.id.item_myorderlist_pageritem_list);
        listView1 = (ListView) pageItem1.findViewById(R.id.item_myorderlist_pageritem_list);
        listView2 = (ListView) pageItem2.findViewById(R.id.item_myorderlist_pageritem_list);
        listView3 = (ListView) pageItem3.findViewById(R.id.item_myorderlist_pageritem_list);
        listView4 = (ListView) pageItem4.findViewById(R.id.item_myorderlist_pageritem_list);

        listView0_hint = (TextView) pageItem0.findViewById(R.id.item_myorderlist_pageritem_hint);
        listView1_hint = (TextView) pageItem1.findViewById(R.id.item_myorderlist_pageritem_hint);
        listView2_hint = (TextView) pageItem2.findViewById(R.id.item_myorderlist_pageritem_hint);
        listView3_hint = (TextView) pageItem3.findViewById(R.id.item_myorderlist_pageritem_hint);
        listView4_hint = (TextView) pageItem4.findViewById(R.id.item_myorderlist_pageritem_hint);

        commonAdapter0 = getCommonAdapter(modellist_all);
        commonAdapter1 = getCommonAdapter(modellist_1);
        commonAdapter2 = getCommonAdapter(modellist_2);
        commonAdapter3 = getCommonAdapter(modellist_3);
        commonAdapter4 = getCommonAdapter(modellist_4);

        pager_views.add(pageItem0);
        pager_views.add(pageItem1);
        pager_views.add(pageItem2);
        pager_views.add(pageItem3);
        pager_views.add(pageItem4);

    }


    @Override
    public void initListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scllorTabView.setOffset(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                setTv(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        swipeRefreshLayout0.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                utilNetwork.getMemOrderModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "7")});
            }
        });
        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                utilNetwork.getMemOrderModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "7")});
            }
        });
        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                utilNetwork.getMemOrderModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "7")});
            }
        });
        swipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                utilNetwork.getMemOrderModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "7")});
            }
        });
        swipeRefreshLayout4.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                utilNetwork.getMemOrderModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "7")});
            }
        });

        listView0.setAdapter(commonAdapter0);
        listView1.setAdapter(commonAdapter1);
        listView2.setAdapter(commonAdapter2);
        listView3.setAdapter(commonAdapter3);
        listView4.setAdapter(commonAdapter4);


    }


    @Override
    public void setupViews() {
        int num = getIntent().getIntExtra("num", 0);
        setTopview2("我的订单");

        scllorTabView.setTabNum(5);
        scllorTabView.setCurrentNum(num);

        viewPager.setAdapter(new ViewPagerAdapter(pager_views));

        viewPager.setCurrentItem(num);

        showLoadingDialog();
        utilNetwork.getMemOrderModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "7")});
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        showLoadingDialog();
        utilNetwork.getMemOrderModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("type", "7")});
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_myorderlist_tv0:
                onTvClick(0);
                break;
            case R.id.activity_myorderlist_tv1:
                onTvClick(1);
                break;
            case R.id.activity_myorderlist_tv2:
                onTvClick(2);
                break;
            case R.id.activity_myorderlist_tv3:
                onTvClick(3);
                break;
            case R.id.activity_myorderlist_tv4:
                onTvClick(4);
                break;
        }
    }

    private void onTvClick(int i) {
        viewPager.setCurrentItem(i);
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.MEMORDER_ID:
                cancelLoadingDialog();
                swipeRefreshLayout0.setRefreshing(false);
                swipeRefreshLayout1.setRefreshing(false);
                swipeRefreshLayout2.setRefreshing(false);
                swipeRefreshLayout3.setRefreshing(false);
                swipeRefreshLayout4.setRefreshing(false);

                if (status_code == 0) {
                    modellist_all.clear();
                    OrderListModel orderlistmodel = (OrderListModel) model;
                    List<OrderListModel.ModelEntity> modelEntitys = orderlistmodel.getModel();

                    for (OrderListModel.ModelEntity modelEntity : modelEntitys) {
                        modellist_all.add(modelEntity);
                    }

                    setModelList(modellist_all);
                }
                break;
            case Constant.ORDERTYPE_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("订单已取消");
                    modellist_all.get(modellist_all.indexOf(cacheEntity)).setOrderState(4);
                    setModelList(modellist_all);
                }
                break;

            case Constant.CONFIRM_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("订单交易成功");
                    modellist_all.get(modellist_all.indexOf(cacheEntity)).setOrderState(3);
                    setModelList(modellist_all);
                }
                break;
            case Constant.OREDETELE_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("删除订单成功");
                    modellist_all.remove(cacheEntity);
                    setModelList(modellist_all);
                }
                break;


        }
    }

    private void setTv(int position) {
        for (int i = 0; i < tv_list.size(); i++)
            if (position == i) tv_list.get(i).setTextColor(getResources().getColor(R.color.red2));
            else tv_list.get(i).setTextColor(getResources().getColor(R.color.gary5));
    }


    //刷新数据集！！！！！！！！！！！！！！！
    private void setModelList(List<OrderListModel.ModelEntity> modellist) {
        modellist_1.clear();
        modellist_2.clear();
        modellist_3.clear();
        modellist_4.clear();

        for (OrderListModel.ModelEntity modelOrderEntity : modellist) {
            if (modelOrderEntity.getOrderState() == 0) {
                modellist_1.add(modelOrderEntity);
            } else if (modelOrderEntity.getOrderState() == 1) {
                modellist_2.add(modelOrderEntity);
            } else if (modelOrderEntity.getOrderState() == 2) {
                modellist_3.add(modelOrderEntity);
            } else if (modelOrderEntity.getOrderState() == 3) {
                modellist_4.add(modelOrderEntity);
            }
        }

        if (modellist.size() == 0) listView0_hint.setVisibility(View.VISIBLE);
        else listView0_hint.setVisibility(View.GONE);
        if (modellist_1.size() == 0) listView1_hint.setVisibility(View.VISIBLE);
        else listView1_hint.setVisibility(View.GONE);
        if (modellist_2.size() == 0) listView2_hint.setVisibility(View.VISIBLE);
        else listView2_hint.setVisibility(View.GONE);
        if (modellist_3.size() == 0) listView3_hint.setVisibility(View.VISIBLE);
        else listView3_hint.setVisibility(View.GONE);
        if (modellist_4.size() == 0) listView4_hint.setVisibility(View.VISIBLE);
        else listView4_hint.setVisibility(View.GONE);


        commonAdapter0.setList(modellist);
        commonAdapter0.notifyDataSetChanged();
        commonAdapter1.setList(modellist_1);
        commonAdapter1.notifyDataSetChanged();
        commonAdapter2.setList(modellist_2);
        commonAdapter2.notifyDataSetChanged();
        commonAdapter3.setList(modellist_3);
        commonAdapter3.notifyDataSetChanged();
        commonAdapter4.setList(modellist_4);
        commonAdapter4.notifyDataSetChanged();
    }

    OrderListModel.ModelEntity cacheEntity;

    @NonNull
    private CommonAdapter<OrderListModel.ModelEntity> getCommonAdapter(List<OrderListModel.ModelEntity> modellist_all) {
        return new CommonAdapter<OrderListModel.ModelEntity>(this, modellist_all, R.layout.item_myorderlist_listitem) {
            public void convert(ViewHolder viewHolder, final OrderListModel.ModelEntity s) {

                viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        // MyUtils.setAndStartIntent(MyOrderListActivity.this, OrderDetailActivity.class);
                        Intent i = new Intent(MyOrderListActivity.this, OrderDetailActivity.class);
                        i.putExtra("merchantoorderid", s.getMerchantorderId() + "");
                        i.putExtra("maintitles", s.getMainTitles() + "");
                        startActivity(i);
                    }
                });

                viewHolder.setText(R.id.item_myorderlist_listitem_title, s.getMainTitles());
                viewHolder.setText(R.id.item_myorderlist_listitem_allprice, "总计：￥" + s.getPayable() + "（含运费）");

                LinearLayout linearlayout = viewHolder.getView(R.id.item_myorderlist_listitem_linearlayout);
                linearlayout.removeAllViews();
                List<OrderListModel.ModelEntity.ModelmEntity> ms = s.getModelm();

                int amount = 0;
                for (OrderListModel.ModelEntity.ModelmEntity modelmEntity : ms) {
                    View v = getLayoutInflater().inflate(R.layout.item_order_goods, null);
                    ((TextView) v.findViewById(R.id.item_order_goods_title)).setText(modelmEntity.getMainTitle());
                    ((TextView) v.findViewById(R.id.item_order_goods_price)).setText("¥ " + modelmEntity.getOriginalprice());
                    ((TextView) v.findViewById(R.id.item_order_goods_number)).setText("x" + modelmEntity.getAmount());
                    Glide.with(MyOrderListActivity.this).load(Constant.SERVER_HOST + modelmEntity.getListImgUrl()).into((ImageView) v.findViewById(R.id.item_order_goods_pic));
                    linearlayout.addView(v);
                    amount += modelmEntity.getAmount();
                }

                viewHolder.setText(R.id.item_myorderlist_listitem_allnumber, "共" + amount + "件");

                if (s.getOrderState() == 0) {
                    setModelEntityState(viewHolder, s,
                            "待付款",
                            View.VISIBLE, View.VISIBLE, "取消订单", "立即支付", getOrderTypeOnClickListener(s), getToPayOnClickListener(s));
                } else if (s.getOrderState() == 1) {
                    setModelEntityState(viewHolder, s,
                            "已付款",
                            View.VISIBLE, View.GONE, "申请售后", "", getAfterSalesOnClickListener(s, "0"), null);
                } else if (s.getOrderState() == 2) {
                    setModelEntityState(viewHolder, s,
                            "已发货",
                            View.VISIBLE, View.VISIBLE, "申请售后", "确认收货", getAfterSalesOnClickListener(s, "1"), getConfirmOnClickListener(s));
                } else if (s.getOrderState() == 3) {
                    setModelEntityState(viewHolder, s,
                            "交易成功",
                            View.VISIBLE, View.VISIBLE, "申请售后", "立即评价", getAfterSalesOnClickListener(s, "2"), getToOrderCommentActivityOnClickListener(s));
                } else if (s.getOrderState() == 4) {
                    setModelEntityState(viewHolder, s,
                            "交易关闭",
                            View.VISIBLE, View.GONE, "删除订单", "", getOreDeteleOnClickListener(s), null);
                } else if (s.getOrderState() == 5) {
                    setModelEntityState(viewHolder, s,
                            "订单过期",
                            View.VISIBLE, View.GONE, "删除订单", "", getOreDeteleOnClickListener(s), null);
                } else if (s.getOrderState() == 6) {
                    setModelEntityState(viewHolder, s,
                            "交易成功",
                            View.VISIBLE, View.VISIBLE, "删除订单", "申请售后", getOreDeteleOnClickListener(s), getAfterSalesOnClickListener(s, "2"));
                } else if (s.getOrderState() == 8) {
                    setModelEntityState(viewHolder, s,
                            "退换货申请中",
                            View.GONE, View.GONE, "", "", null, null);
                }
            }
        };
    }


    private void setModelEntityState(ViewHolder viewHolder, OrderListModel.ModelEntity s,
                                     String state,
                                     int button0Vis, int button1Vis,
                                     String tv0_txt, String tv1_txt,
                                     View.OnClickListener OnClickListener0, View.OnClickListener OnClickListener1) {
        viewHolder.setText(R.id.item_myorderlist_listitem_state, state);
        TextView tv0 = viewHolder.getView(R.id.item_myorderlist_listitem_tv0);
        TextView tv1 = viewHolder.getView(R.id.item_myorderlist_listitem_tv1);
        tv0.setVisibility(button0Vis);
        tv1.setVisibility(button1Vis);
        tv0.setText(tv0_txt);
        tv1.setText(tv1_txt);
        tv0.setOnClickListener(OnClickListener0);
        tv1.setOnClickListener(OnClickListener1);
    }


    //获取删除订单的onclicklistener
    @NonNull
    private View.OnClickListener getOreDeteleOnClickListener(final OrderListModel.ModelEntity s) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                utilNetwork.getOreDeteleModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("merchantorderId", s.getMerchantorderId() + "")});
                cacheEntity = s;
            }
        };
    }

    //获取取消订单的onclicklistener
    @NonNull
    private View.OnClickListener getOrderTypeOnClickListener(final OrderListModel.ModelEntity s) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                showLoadingDialog();
                utilNetwork.getOrderType(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("merchantorderid", s.getMerchantorderId() + "")});
                cacheEntity = s;
            }
        };
    }

    //获取确认收货的onclicklistener
    @NonNull
    private View.OnClickListener getConfirmOnClickListener(final OrderListModel.ModelEntity s) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                showLoadingDialog();
                utilNetwork.getConfirmModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("merchantorderid", s.getMerchantorderId() + "")});
                cacheEntity = s;
            }
        };
    }

    //获取评价的onclicklistener
    @NonNull
    private View.OnClickListener getToOrderCommentActivityOnClickListener(final OrderListModel.ModelEntity s) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                MyUtils.commonORDERCOMMENT(MyOrderListActivity.this,
                        s.getModelm().get(0).getProductId() + "",
                        s.getMerchantNum(),
                        s.getModelm().get(0).getListImgUrl(),
                        s.getModelm().get(0).getMainTitle(),
                        s.getModelm().get(0).getTotalPrice(),
                        s.getModelm().get(0).getAmount() + "");
            }
        };
    }

    //获取申请售后的onclicklistener
    @NonNull
    private View.OnClickListener getAfterSalesOnClickListener(final OrderListModel.ModelEntity s, final String return_type) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                OrderListModel.ModelEntity.ModelmEntity e = s.getModelm().get(0);
                Intent i = new Intent(MyOrderListActivity.this, AfterSalesActivity.class);
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

    //获取立即支付onclicklistener
    @NonNull
    private View.OnClickListener getToPayOnClickListener(final OrderListModel.ModelEntity s) {
        return new View.OnClickListener() {
            public void onClick(View view) {
                MyUtils.commonPAY(
                        MyOrderListActivity.this,
                        s.getMerchantorderId() + "",
                        s.getMerchantNum(),
                        Double.parseDouble(s.getPayable()),
                        Long.parseLong(s.getModelm().get(0).getModifydate()) * 1000, 0);
            }
        };
    }


}
