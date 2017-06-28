package com.jo.jingou.activity;

import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.ShareModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.Util_PopupWindow;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/15.
 */
public class MyShareCodeActivity extends MyBaseActivity {


    TextView code_tv;
    View submit;
    PopupWindow pop_share;


    @Override
    public void initData() {
        layoutId = R.layout.activity_mysharecode;
    }

    @Override
    public void findViews() {
        code_tv = (TextView) findViewById(R.id.activity_mysharecode_code_tv);
        submit = findViewAndSetClick(R.id.submit);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        showLoadingDialog();
        utilNetwork.getShareModel(new OkHttpClientManager.Param[]{});

        setTopview2("我的邀请码");
        leftimg3.setImageResource(R.drawable.back_w);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.submit:
                Util_PopupWindow.show(pop_share, this);
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);

        switch (network_code) {
            case Constant.SHARE_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    ShareModel shareModel = (ShareModel) model;
                    code_tv.setText(shareModel.getMeCode());
                    pop_share = Util_PopupWindow.setCommonPopupWindow_Share1(this, shareModel.getInvitation(), "分享");
                } else {
                    showToast(model.getErrmsg());
                }
                break;
        }

    }
}
