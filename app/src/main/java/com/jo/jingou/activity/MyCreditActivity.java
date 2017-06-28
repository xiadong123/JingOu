package com.jo.jingou.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.IntegralModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by Administrator on 2017/4/20.
 */

public class MyCreditActivity extends MyBaseActivity {


    TextView all_intergal;
    View duihuan;
    RecyclerView recyclerView;
    CommonRefreshAdapter<IntegralModel.ListEntity> commonRefreshAdapter;
    int page = 1;

    @Override
    public void initData() {
        layoutId = R.layout.activity_mycredit;
    }

    @Override
    public void findViews() {

        all_intergal = (TextView) findViewById(R.id.all_intergal);
        duihuan = findViewAndSetClick(R.id.activity_mycredit_duihuan);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        commonRefreshAdapter = new CommonRefreshAdapter<IntegralModel.ListEntity>(this, new ArrayList<IntegralModel.ListEntity>(), R.layout.item_mycredit_son, true) {
            @Override
            protected void convert(ViewHolder holder, IntegralModel.ListEntity data) {
                holder.setText(R.id.title, data.getTitle());
                holder.setText(R.id.time, data.getModifyDate());
                if (data.getType() == 0) {
                    holder.setText(R.id.intergal, "+" + data.getIntegra());
                } else if (data.getType() == 1) {
                    holder.setText(R.id.intergal, "-" + data.getIntegra());
                }

            }
        };

        commonRefreshAdapter.setLoadingView(R.layout.item_recyclerview_loadingview);
        commonRefreshAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                page++;
                getData();
            }
        });
    }

    @Override
    public void initListener() {
        leftview.setOnClickListener(this);
    }

    @Override
    public void setupViews() {

        setTopview2("我的积分");
        leftimg3.setImageResource(R.drawable.back_w);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commonRefreshAdapter);

        getData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_mycredit_duihuan:
                MyUtils.setAndStartIntent(this, MyCredit_DuihuanActivity.class);
                break;
        }
    }

    public void getData() {
        showLoadingDialog();
        utilNetwork.getIntegralModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("page", page + "")});
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.INTEGRAL_ID:
                cancelLoadingDialog();
                if (status_code == 0) {

                    all_intergal.setText(((IntegralModel) model).getIntegral() + "");

                    List<IntegralModel.ListEntity> listEntities = ((IntegralModel) model).getList();

                    if (page != 1)
                        commonRefreshAdapter.setData(listEntities);
                    else if (page == 1)
                        commonRefreshAdapter.setNewData(listEntities);

                    if (listEntities.size() < 8) {
                        commonRefreshAdapter.setLoadEndView(R.layout.item_recyclerview_loadingendview);
                    }
                } else {

                }
                break;
        }
    }
}
