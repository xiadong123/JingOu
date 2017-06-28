package com.jo.jingou.activity;

import android.view.View;
import android.widget.PopupWindow;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.model.RecordPopModel;
import com.jo.jingou.utils.Util_PopupWindow;

import java.util.List;

public class WalletDesActivity extends MyBaseActivity {

    @Override
    public void initData() {
        layoutId = R.layout.activity_wallet_des;
    }

    @Override
    public void findViews() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("使用说明");
        leftimg3.setImageResource(R.drawable.back);
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
}
