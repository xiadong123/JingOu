package com.jo.jingou.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.RecordPopModel;
import com.jo.jingou.model.TradingrecordModel;
import com.jo.jingou.utils.CalendarFormatUtils;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyPopupWindow;
import com.jo.jingou.utils.Util_PopupWindow;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

public class TransactionRecordActivity extends MyBaseActivity {

    private RecyclerView record_rcv;
    private SwipeRefreshLayout record_srl;

    List<String> testData = new ArrayList<>();
    List<RecordPopModel> mPopData = new ArrayList<>();

    private int TradeType = 0;  // 0：全部；1：支出；2：收益
    private CommonRefreshAdapter<TradingrecordModel.ListBean.ItemsBean> commonRefreshAdapter;
    private int pageIndex = 1;
    private List<TradingrecordModel.ListBean.ItemsBean> list = new ArrayList<>();

    @Override
    public void initData() {
        layoutId = R.layout.activity_transaction_record;
    }

    @Override
    public void findViews() {
        record_srl = (SwipeRefreshLayout) findViewById(R.id.record_srl);
        record_rcv = (RecyclerView) findViewById(R.id.record_rcv);
        record_rcv.setLayoutManager(new LinearLayoutManager(this));

        commonRefreshAdapter = new CommonRefreshAdapter(this, list, R.layout.item_record, true) {
            @Override
            protected void convert(ViewHolder holder, Object data) {

                TradingrecordModel.ListBean.ItemsBean listBean = (TradingrecordModel.ListBean.ItemsBean)
                        data;
                TextView recordDes = holder.getView(R.id.item_record_des_tv);
                TextView recordMoney = holder.getView(R.id.item_record_money_tv);
                TextView recordWeek = holder.getView(R.id.item_record_week_tv);
                TextView recordTime = holder.getView(R.id.item_record_time_tv);
                ImageView recordTypeIv = holder.getView(R.id.record_type_iv);
                String tradeDate = listBean.getTradeDate();
                if (CalendarFormatUtils.isToday(tradeDate)) {
                    recordWeek.setText("今天");
                    recordTime.setText(CalendarFormatUtils.getDate(tradeDate,
                            "yyyy-MM-dd hh:mm:ss", "hh:mm"));
                } else if (CalendarFormatUtils.dateIsYesterday(tradeDate)) {
                    recordWeek.setText("昨天");
                    recordTime.setText(CalendarFormatUtils.getDate(tradeDate,
                            "yyyy-MM-dd hh:mm:ss", "hh:mm"));
                } else {
                    recordWeek.setText(CalendarFormatUtils.date2Week(tradeDate));
                    recordTime.setText(CalendarFormatUtils.getDate(tradeDate,
                            "yyyy-MM-dd hh:mm:ss", "MM-dd"));
                }
                int type = listBean.getType();  //0支出  1收入
                if (type == 0) {
                    recordTypeIv.setImageDrawable(getResources().getDrawable(R.drawable.expenditure));
                } else if (type == 1) {
                    recordTypeIv.setImageDrawable(getResources().getDrawable(R.drawable.income));
                }
                recordMoney.setText(listBean.getMoney());
                recordDes.setText(listBean.getName());
            }
        };
//        commonRefreshAdapter.setLoadingView(R.layout.item_recyclerview_loadingview);
        commonRefreshAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                pageIndex++;
                setData(TradeType);
            }
        });
        record_rcv.setAdapter(commonRefreshAdapter);  //设置适配器 一定要在 设置监听器后面

    }

    @Override
    public void initListener() {
        record_srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                setData(TradeType);  //设置数据
            }
        });
    }

    @Override
    public void setupViews() {
        setTopview2("交易记录");
        leftimg3.setImageResource(R.drawable.back);
        righttxt.setVisibility(View.VISIBLE);
        righttxt.setText("筛选");
        righttxt.setOnClickListener(this);
        setPopData();  //设置 弹出PopWindow 的数据
        pageIndex = 1;
        showLoadingDialog();
        setData(TradeType);  //设置数据--临时

    }

    private void setPopData() {
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                RecordPopModel recordPopModel = new RecordPopModel();
                recordPopModel.setmContent("全部");
                recordPopModel.setmId(i);
                recordPopModel.setIsSelect(true);
                mPopData.add(recordPopModel);
            } else if (i == 1) {
                RecordPopModel recordPopModel = new RecordPopModel();
                recordPopModel.setmContent("我的支出");
                recordPopModel.setmId(i);
                recordPopModel.setIsSelect(false);
                mPopData.add(recordPopModel);
            } else if (i == 2) {
                RecordPopModel recordPopModel = new RecordPopModel();
                recordPopModel.setmContent("我的收益");
                recordPopModel.setmId(i);
                recordPopModel.setIsSelect(false);
                mPopData.add(recordPopModel);
            }
        }
    }

    private void setData(int TradeType) {
        utilNetwork.getTradingrecordModel(new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("TradeType", TradeType + ""), new OkHttpClientManager.Param("Page",
                pageIndex + "")
        });
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.TRADINGRECORD_ID:
                cancelLoadingDialog();
                record_srl.setRefreshing(false);
                if (status_code == 0) {
                    TradingrecordModel tradingrecordModel = (TradingrecordModel) model;
                    list = tradingrecordModel.getList().getItems();
                    if (list.size() < 10) {
//                    if (list.size() == 0) {
                        commonRefreshAdapter.setLoadEndView(R.layout.item_recyclerview_loadingendview);
                    } else {
                        commonRefreshAdapter.setLoadingView(R.layout.item_recyclerview_loadingview);
                    }

                    if (pageIndex == 1) {
                        commonRefreshAdapter.setNewData(list);
                        if (list.size() == 0 && TradeType == 0) {  //如果请求的是全部的数据 且数据返回长度为0 则说明没有数据 则关掉当前的Activity
                            showToast("暂无交易记录");
                            finish();
                        }
                    } else {
                        if (list.size() < 10) {
                            commonRefreshAdapter.setLoadEndView(R.layout.item_recyclerview_loadingendview);
                        }
                        if (list.size() != 0) {
                            commonRefreshAdapter.setLoadMoreData(list);
                        }
                    }
                } else {
                    if (pageIndex != 1) {
                        pageIndex--;
                        commonRefreshAdapter.setLoadFailedView(R.layout.item_recyclerview_loadingfailedview);
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.base_topview_right_tv:  //头部 筛选按钮的点击事件
                MyPopupWindow popupWindow = Util_PopupWindow.setRecordPopupWindow
                        (TransactionRecordActivity.this, mPopData,
                                new Util_PopupWindow.onClickCallBack() {
                                    @Override
                                    public void onPopClickCallBack(int id, int position,
                                                                   List<RecordPopModel>
                                                                           recordList) {
                                        TransactionRecordActivity.this.mPopData = recordList;
//                                getData(id);  //请求服务器  使用返回的id
                                        TradeType = id;
                                        pageIndex = 1;
                                        setData(TradeType);  //设置数据--临时
                                    }
                                });
                popupWindow.showAsDropDown(topview);
                break;
        }
    }
}
