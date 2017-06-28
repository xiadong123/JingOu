package com.jo.jingou.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.Instation0Model;
import com.jo.jingou.model.Instation1Model;
import com.jo.jingou.model.Instation2Model;
import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.othershe.baseadapter.ViewHolder;

import java.util.ArrayList;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2017/4/24.
 */
public class MessageCommonListActicity extends MyBaseActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    CommonRefreshAdapter commonRefreshAdapter0, commonRefreshAdapter1, commonRefreshAdapter2;

    int message_type = 0;


    @Override
    public void initData() {
        layoutId = R.layout.activity_message_commonlist;
    }

    @Override
    public void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_messagecommonlist_swiperefreshlayout);
        recyclerView = (RecyclerView) findViewById(R.id.activity_messagecommonlist_recyclerview);

        commonRefreshAdapter0 = new CommonRefreshAdapter<Instation0Model.ListEntity>(
                this, new ArrayList<Instation0Model.ListEntity>(),
                R.layout.item_message_order, false) {
            @Override
            protected void convert(ViewHolder holder, final Instation0Model.ListEntity data) {

                holder.setText(R.id.item_message_order_date, data.getModifydate());

                String title = "";
                switch (data.getOrdermodel().get(0).getOrderStates()) {
                    case "0":
                        title = "订单待付款";
                        break;
                    case "1":
                        title = "订单已付款";
                        break;
                    case "2":
                        title = "订单已发货";
                        break;
                    case "3":
                        title = "订单交易成功";
                        break;
                    case "4":
                        title = "订单已取消";
                        break;
                    case "5":
                        title = "订单已过期";
                        break;
                    case "6":
                        title = "订单已评价";
                        break;
                    case "8":
                        title = "订单退换货";
                        break;
                }


                holder.setText(R.id.item_message_order_states, title);
                holder.setText(R.id.item_message_order_title, data.getOrdermodel().get(0).getMainTitle());
                holder.setText(R.id.item_message_order_id, "订单编号：" + data.getOrdermodel().get(0).getOrderNumber());
                Glide.with(MessageCommonListActicity.this)
                        .load(Constant.SERVER_HOST + data.getOrdermodel().get(0).getListImgUrl())
                        .into((ImageView) holder.getView(R.id.item_message_order_pic));
                holder.setOnClickListener(R.id.item_message_order_content, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //utilNetwork.getReadModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("messageid", data.getInstationId() + "")});

                        Intent i = new Intent(MessageCommonListActicity.this, OrderDetailActivity.class);
                        i.putExtra("merchantoorderid", data.getOrdermodel().get(0).getMerchantorderId() + "");
                        startActivity(i);
                    }
                });

            }
        };

        commonRefreshAdapter1 = new CommonRefreshAdapter<Instation1Model.ListEntity>(this, new ArrayList<Instation1Model.ListEntity>(), R.layout.item_message_enchashment, false) {
            @Override
            protected void convert(ViewHolder holder, Instation1Model.ListEntity data) {
                holder.setText(R.id.item_message_enchashment_content, data.getContents());
                holder.setText(R.id.item_message_enchashment_date0, data.getModifydate());
                holder.setText(R.id.item_message_enchashment_date1, data.getModifydate());
                holder.setText(R.id.item_message_enchashment_date3, data.getModifydate());

                if (data.getWithdrawmodel().size() != 0) {
                    holder.setText(R.id.item_message_enchashment_date2, data.getWithdrawmodel().get(0).getAddDate());
                    holder.setText(R.id.item_message_enchashment_bank, data.getWithdrawmodel().get(0).getBank());
                    holder.setText(R.id.item_message_enchashment_price, data.getWithdrawmodel().get(0).getMoney() + "");
                }
            }
        };

        commonRefreshAdapter2 = new CommonRefreshAdapter<Instation2Model.ListEntity>(this, new ArrayList<Instation2Model.ListEntity>(), R.layout.item_message_system, false) {
            @Override
            protected void convert(ViewHolder holder, final Instation2Model.ListEntity data) {

                holder.setText(R.id.item_message_system_title, "系统消息");
                holder.setText(R.id.item_message_system_date, data.getModifydate());
                holder.setText(R.id.item_message_system_content, data.getContents() + "");

//                if (i0 != 0) {
//
//                    holder.setText(R.id.item_message_system_title, data.getContents());
//
//                    if (data.getContents().endsWith("开店审核通过")) {
//                        holder.setText(R.id.item_message_system_content, "您的开店审核已通过验证，请您用我们给您的账号密码登录相应网站管理您的店铺。");
//                        holder.setOnClickListener(R.id.item_message_system_layout, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent i = new Intent(MessageCommonListActicity.this, MyBussiness_SuccessActivity.class);
//                                i.putExtra("Account", data.getBusinessmodel().get(0).getAccount().get(0).getAccount());
//                                i.putExtra("Password", data.getBusinessmodel().get(0).getAccount().get(0).getPassword());
//                                startActivity(i);
//                            }
//                        });
//                    } else {
//                        holder.setText(R.id.item_message_system_content, data.getBusinessmodel().get(0).getRemark() + "");
//                        holder.setOnClickListener(R.id.item_message_system_layout, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                MyUtils.setAndStartIntent(MessageCommonListActicity.this, MyBusinessActivity.class);
//                            }
//                        });
//                    }
//
//
//                } else if (i1 != 0) {
//                    holder.setText(R.id.item_message_system_title, data.getOrdermodel().get(0).getOrderStates());
//                    holder.setText(R.id.item_message_system_content, "您有一笔订单交易未成功");
//                    holder.setOnClickListener(R.id.item_message_system_layout, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent i = new Intent(MessageCommonListActicity.this, OrderDetailActivity.class);
//                            i.putExtra("merchantoorderid", data.getOrdermodel().get(0).getMerchantorderId() + "");
//                            startActivity(i);
//                        }
//                    });
//
//                }

                int i0 = data.getBusinessmodel().size();
                if (i0 != 0)
                    if (data.getBusinessmodel().get(0).getState().endsWith("开店审核通过")) {
                        holder.setText(R.id.item_message_system_title, "开店审核通过");
                        holder.setText(R.id.item_message_system_content, data.getBusinessmodel().get(0).getRemark() + "");
                        holder.setOnClickListener(R.id.item_message_system_layout, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(MessageCommonListActicity.this, MyBussiness_SuccessActivity.class);
                                i.putExtra("Account", data.getBusinessmodel().get(0).getAccount().get(0).getAccount() + "");
                                i.putExtra("Password", data.getBusinessmodel().get(0).getAccount().get(0).getPassword() + "");
                                startActivity(i);
                            }
                        });
                    } else {
                        holder.setText(R.id.item_message_system_title, "开店审核失败");
                        holder.setText(R.id.item_message_system_content, data.getBusinessmodel().get(0).getRemark() + "");
                        holder.setOnClickListener(R.id.item_message_system_layout, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MyUtils.setAndStartIntent(MessageCommonListActicity.this, MyBusinessActivity.class);
                            }
                        });
                    }
            }
        };
    }

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    @Override
    public void setupViews() {
        message_type = getIntent().getIntExtra("message_type", 0);
        switch (message_type) {
            case 0:
                setTopview2("订单消息");
                break;
            case 1:
                setTopview2("提现通知");
                break;
            case 2:
                setTopview2("系统消息");
                break;
        }

        setNoData_LoadFail_ReLoadViews("没有搜索到这类消息～");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setRefreshing(true);
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
                getData();
                break;
        }
    }


    public void getData() {
        switch (message_type) {
            case 0:
                utilNetwork.getInstation0Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "1")});
                break;
            case 1:
                utilNetwork.getInstation1Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "2")});
                break;
            case 2:
                utilNetwork.getInstation2Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "3")});
                break;
        }
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.INSTATION_ID0:
                swipeRefreshLayout.setRefreshing(false);
                if (status_code == 0) {
                    recyclerView.setAdapter(commonRefreshAdapter0);
                    Instation0Model instation0Model = (Instation0Model) model;
                    commonRefreshAdapter0.setNewData(instation0Model.getList());
                    commonRefreshAdapter0.notifyDataSetChanged();

                    setReadMessage("1", 0, 0);

                    if (instation0Model.getList().size() == 0) {
                        setViewOnNoData();
                    } else {
                        setViewOnLoadSuccessHadData();
                    }
                } else {
                    setViewOnLoadFail();
                }
                break;
            case Constant.INSTATION_ID1:
                swipeRefreshLayout.setRefreshing(false);
                if (status_code == 0) {
                    recyclerView.setAdapter(commonRefreshAdapter1);
                    Instation1Model instation1Model = (Instation1Model) model;
                    commonRefreshAdapter1.setNewData(instation1Model.getList());
                    commonRefreshAdapter1.notifyDataSetChanged();
                    setReadMessage("2", 1, 0);

                    if (instation1Model.getList().size() == 0) {
                        setViewOnNoData();
                    } else {
                        setViewOnLoadSuccessHadData();
                    }
                } else {
                    setViewOnLoadFail();
                }
                break;
            case Constant.INSTATION_ID2:
                swipeRefreshLayout.setRefreshing(false);
                if (status_code == 0) {
                    recyclerView.setAdapter(commonRefreshAdapter2);
                    Instation2Model instation2Model = (Instation2Model) model;
                    commonRefreshAdapter2.setNewData(instation2Model.getList());
                    commonRefreshAdapter2.notifyDataSetChanged();
                    setReadMessage("3", 2, 0);

                    if (instation2Model.getList().size() == 0) {
                        setViewOnNoData();
                    } else {
                        setViewOnLoadSuccessHadData();
                    }
                } else {
                    setViewOnLoadFail();
                }
                break;
        }
    }


    //设置消息已读
    private void setReadMessage(String type, int index, int number) {
        utilNetwork.getReadModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("Type", type), new OkHttpClientManager.Param("messageid", "0")});
        MessageLoader.setMessage(index, number);
    }
}
