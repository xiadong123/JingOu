package com.jo.jingou.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.PerticiListModel;
import com.jo.jingou.utils.Constant;

import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/15.
 */
public class FenhongDetailActivity extends MyBaseActivity {

    TextView tv0, tv1, tv2;
    LinearLayout linearLayout;

    @Override
    public void initData() {
        layoutId = R.layout.activity_fenhongdetail;
    }

    @Override
    public void findViews() {
        tv0 = (TextView) findViewById(R.id.activity_fenhongdetail_tv0);
        tv1 = (TextView) findViewById(R.id.activity_fenhongdetail_tv1);
        tv2 = (TextView) findViewById(R.id.activity_fenhongdetail_tv2);
        linearLayout = (LinearLayout) findViewById(R.id.activity_fenhongdetail_linearlayout);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("分红奖励");
        leftimg3.setImageResource(R.drawable.back_w);

        showLoadingDialog();
        utilNetwork.getParticiListModel(new OkHttpClientManager.Param[]{});

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.PARTICILIST_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    PerticiListModel perticiListModel = (PerticiListModel) model;

                    tv0.setText(perticiListModel.getSum() + "");
                    tv1.setText(perticiListModel.getBalance() + "");

                    if (perticiListModel.getList().size() == 0) {
                        tv2.setText("账号管理 暂无分红账号");
                    } else {
                        tv2.setText("账号管理");
                        List<PerticiListModel.ListEntity> list = perticiListModel.getList();
                        LayoutInflater l = getLayoutInflater();
                        for (PerticiListModel.ListEntity listEntity : list) {
                            View v = getLayoutInflater().inflate(R.layout.item_fenhongdetail, null);
                            ((TextView) v.findViewById(R.id.item_fenhongdetail_tv0)).setText(listEntity.getText());
                            ((TextView) v.findViewById(R.id.item_fenhongdetail_tv1)).setText("生成时间" + listEntity.getValue());
                            linearLayout.addView(v);
                        }
                    }
                }
                break;
        }

    }
}
