package com.jo.jingou.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.activity.AccountSafeActivity;
import com.jo.jingou.activity.AfterSalesListActivity;
import com.jo.jingou.activity.ImproveInformationActivity;
import com.jo.jingou.activity.Login_Register_ResetPasswordActivity;
import com.jo.jingou.activity.MessageActivity;
import com.jo.jingou.activity.MyBusinessActivity;
import com.jo.jingou.activity.MyCreditActivity;
import com.jo.jingou.activity.MyOrderListActivity;
import com.jo.jingou.activity.MyPurseActivity;
import com.jo.jingou.activity.MyShareCodeActivity;
import com.jo.jingou.activity.SettingActivity;
import com.jo.jingou.activity.UserInfoActivity;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseFragment;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.UserInfoModel;
import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.view.CircleImageView;
import com.jo.jingou.view.dialog.SysDialogUtils;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/11/28.
 */
public class MineFragment extends MyBaseFragment {

    View userinfo_or_login_layout;
    CircleImageView headview;
    ImageView sexview;
    TextView name_tv;


    View setting_layout;
    View message_layout;
    ImageView fragment_mine_message_iv;
    View orderlist_layout;
    View orderlist_rl0, orderlist_rl1, orderlist_rl2, orderlist_rl3, orderlist_rl4;
    View myfathercode_layout;
    View mypurse_layout;
    View mycredit_layout;
    View accountsafe_layout;
    View contactmerchant_layout;
    View business;

    //public static UserInfoModel userInfoModel = null;

    @Override
    public void initData() {
        layoutId = R.layout.fragment_mine;
    }

    @Override
    public void findViews() {
        userinfo_or_login_layout = rootView.findViewById(R.id.fragment_mine_userinfo_or_login_layout);
        headview = (CircleImageView) rootView.findViewById(R.id.fragment_mine_headview);
        sexview = (ImageView) rootView.findViewById(R.id.fragment_mine_sexview);
        name_tv = (TextView) rootView.findViewById(R.id.fragment_mine_nameorlogin_tv);
        setting_layout = rootView.findViewById(R.id.fragment_mine_set);
        message_layout = rootView.findViewById(R.id.fragment_mine_message);
        fragment_mine_message_iv = (ImageView) rootView.findViewById(R.id.fragment_mine_message_iv);
        orderlist_layout = rootView.findViewById(R.id.fragment_mine_myorderlist_layout);
        orderlist_rl0 = rootView.findViewById(R.id.fragment_mine_orderlist_layout0);
        orderlist_rl1 = rootView.findViewById(R.id.fragment_mine_orderlist_layout1);
        orderlist_rl2 = rootView.findViewById(R.id.fragment_mine_orderlist_layout2);
        orderlist_rl3 = rootView.findViewById(R.id.fragment_mine_orderlist_layout3);
        orderlist_rl4 = rootView.findViewById(R.id.fragment_mine_orderlist_layout4);
        myfathercode_layout = rootView.findViewById(R.id.fragment_mine_myfathercode_layout);
        mycredit_layout = rootView.findViewById(R.id.fragment_mine_mycredit_layout);
        mypurse_layout = rootView.findViewById(R.id.fragment_mine_mypurse_layout);
        accountsafe_layout = rootView.findViewById(R.id.fragment_mine_accountsafe_layout);
        contactmerchant_layout = rootView.findViewById(R.id.fragment_mine_contactmerchant_layout);
        business = rootView.findViewById(R.id.fragment_mine_layout4);
    }

    @Override
    public void initListener() {
        userinfo_or_login_layout.setOnClickListener(this);
        setting_layout.setOnClickListener(this);
        message_layout.setOnClickListener(this);
        orderlist_layout.setOnClickListener(this);
        orderlist_rl0.setOnClickListener(this);
        orderlist_rl1.setOnClickListener(this);
        orderlist_rl2.setOnClickListener(this);
        orderlist_rl3.setOnClickListener(this);
        orderlist_rl4.setOnClickListener(this);
        myfathercode_layout.setOnClickListener(this);
        mycredit_layout.setOnClickListener(this);
        mypurse_layout.setOnClickListener(this);
        accountsafe_layout.setOnClickListener(this);
        contactmerchant_layout.setOnClickListener(this);
        business.setOnClickListener(this);
    }

    @Override
    public void setupViews() {
        upData();
    }

    @Override
    public void upData() {
        if (rootView != null)
            if (UserInfoLoader.getUserInfoModel() == null)
                if (MyUtils.isLogin()) {
                    sexview.setVisibility(View.VISIBLE);
                    showLoadingDialog();
                    utilNetwork.getUserInfoModel(new OkHttpClientManager.Param[]{});
                } else {
                    sexview.setVisibility(View.GONE);
                    name_tv.setText("登录/注册");
                }
            else setWindow();

        setMessageImage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_mine_set:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, SettingActivity.class);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_message:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, MessageActivity.class);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_userinfo_or_login_layout:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, UserInfoActivity.class);
                } else {
                    Intent i = new Intent(context, Login_Register_ResetPasswordActivity.class);
                    i.putExtra(Constant.BYTYPE, "bylogin");
                    context.startActivity(i);
                }
                break;

            case R.id.fragment_mine_myorderlist_layout:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, MyOrderListActivity.class);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_orderlist_layout0:
                if (MyUtils.isLogin()) {
                    Intent i = new Intent(context, MyOrderListActivity.class);
                    i.putExtra("num", 1);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_orderlist_layout1:
                if (MyUtils.isLogin()) {
                    Intent i = new Intent(context, MyOrderListActivity.class);
                    i.putExtra("num", 2);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_orderlist_layout2:
                if (MyUtils.isLogin()) {
                    Intent i = new Intent(context, MyOrderListActivity.class);
                    i.putExtra("num", 3);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_orderlist_layout3:
                if (MyUtils.isLogin()) {
                    Intent i = new Intent(context, MyOrderListActivity.class);
                    i.putExtra("num", 4);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_orderlist_layout4:
                if (MyUtils.isLogin()) {
                    Intent i = new Intent(context, AfterSalesListActivity.class);
                    //i.putExtra("num", 4);
                    startActivity(i);
                    //showToast("您没有提交过退款/退货申请哦～");
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_accountsafe_layout:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, AccountSafeActivity.class);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_mypurse_layout:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, MyPurseActivity.class);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_myfathercode_layout:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, MyShareCodeActivity.class);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_contactmerchant_layout:
                MyUtils.callPhone((MyBaseActivity) context, "4009986698");
                break;
            case R.id.fragment_mine_mycredit_layout:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, MyCreditActivity.class);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_mine_layout4:
                if (MyUtils.isLogin()) {
                    if (!("false".equals(MyApplication.getIsIdCode()))) {  //说明 用户已经失明认证过了
                        Intent intent = new Intent(context, MyBusinessActivity.class);
                        startActivity(intent);
                    } else {
                        String content = "还未实名认证，只有实名认证后才可以成为商家";
                        String confirmText = "立即前往";
//                        DialogUtils.showTelDialog(getmActivity(), content, confirmText, new DialogUtils.DialogConfirm
//                                () {
//                            @Override
//                            public void confirm() {
//                                Intent i = new Intent(getmActivity(), ImproveInformationActivity.class);
//                                i.putExtra(Constant.BYTYPE, "isIdCode");
//                                startActivity(i);
//                            }
//                        });

                        SysDialogUtils.setAndShowDialog(getmActivity(), content, confirmText, "取消",
                                new SysDialogUtils.DialogConfirm() {
                                    @Override
                                    public void confirm() {
                                        Intent i = new Intent(getmActivity(), ImproveInformationActivity.class);
                                        i.putExtra(Constant.BYTYPE, "isIdCode");
                                        startActivity(i);
                                    }
                                });
                    }
                } else {
                    showToast("请去登录");
                }
                break;


        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.GETMODEL_ID:
                cancelLoadingDialog();

                if (status_code == 0) {
                    UserInfoLoader.setUserInfoModel((UserInfoModel) model);
                    setWindow();
                }
                break;
        }
    }

    private void setWindow() {
        if (MyUtils.isLogin()) {
            if (MyUtils.hasValue(UserInfoLoader.getUserInfoModel().getModel().getName())) {
                name_tv.setText(UserInfoLoader.getUserInfoModel().getModel().getName());
            } else {
                name_tv.setText("未设置昵称");
            }

            if (MyUtils.hasValue(UserInfoLoader.getUserInfoModel().getModel().getListImgUrl())) {
                Glide.with(context).load(Constant.SERVER_HOST + UserInfoLoader.getUserInfoModel().getModel().getListImgUrl()).into
                        (headview);
            } else {
                headview.setImageResource(R.drawable.myhead);
            }
            if (UserInfoLoader.getUserInfoModel().getModel().getSex() == 0) {
                sexview.setImageResource(R.drawable.male);
            } else {
                sexview.setImageResource(R.drawable.female);
            }

        } else {
            headview.setImageResource(R.drawable.circle);
            sexview.setVisibility(View.GONE);
            name_tv.setText("登录/注册");
        }
    }


    @Override
    public void setMessageImage() {
        if (rootView != null)
            if (MyUtils.isLogin()) {
                if (MessageLoader.getMessageNumberAll() == 0) {
                    fragment_mine_message_iv.setImageResource(R.drawable.message_w);
                } else {
                    fragment_mine_message_iv.setImageResource(R.drawable.message_01);
                }
            } else {
                fragment_mine_message_iv.setImageResource(R.drawable.message_w);
            }
    }
}
