package com.jo.jingou.base;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.activity.Login_Register_ResetPasswordActivity;
import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_Network;

import caesar.feng.framework.interfaces.MyFrame;


public abstract class MyBaseFragment extends Fragment implements OnClickListener, MyFrame, MessageLoader.OnMessageChangeListener {

    protected int layoutId = -10086;

    public LayoutInflater inflater;
    public MyApplication application;
    public Context context;
    protected Util_Network utilNetwork;
    protected ProgressDialog loading_dialog;
    protected PopupWindow loginout_popupwindow;

    public BroadcastReceiver broadcastReceiver;

    //缓存view
    public View rootView;
    public Boolean hasInitData = false;

    private MyBaseActivity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.initData();
        if (rootView == null && this.layoutId != -10086) {
            rootView = inflater.inflate(layoutId, null);
            commonSetting(inflater);
            findViews();
            initListener();
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!hasInitData) {
            setupViews();
            hasInitData = true;
        }
        //upData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MyBaseActivity) {
            this.mActivity = (MyBaseActivity) context;
        }
    }

    public MyBaseActivity getmActivity() {
        return mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if ((ViewGroup) rootView.getParent() != null)
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        MessageLoader.removeListener(this);
    }

    public abstract void upData();

    public abstract void onClick(View v);

    @Override
    public void onMessageChanged(boolean hasNewMessage, int newMessageNumber) {

    }

    public void commonSetting(LayoutInflater inflater) {
        this.inflater = inflater;
        this.application = getMyApplication();
        this.context = getActivity();


        this.utilNetwork = new Util_Network(context) {
            protected <T extends MyBaseModel> void request(int code1, int code2, T model) {
                super.request(code1, code2, model);
                if (code1 == Constant.NETWORKERROR) {
                    showToast("网络请求失败！");
                } else if (code1 == Constant.LOGININVAILD) {
                    dialog();
                } else if (code1 != Constant.NETWORKSUCCESS) {
                    showToast(model.getErrmsg());
                }
                onNetworkCallBack(code1, code2, model);
            }
        };

        MessageLoader.addListener(this);

        this.loading_dialog = new ProgressDialog(context);
        loading_dialog.setTitle("");
        loading_dialog.setMessage("加载中，请稍后...");
        loading_dialog.setIndeterminate(true);
        loading_dialog.setCancelable(true);
        loading_dialog.setOnCancelListener(null);


    }

    //TopView的子view
    protected View topview, centerview, leftview, rightview, rightview1;
    protected ImageView leftimg1, leftimg2, leftimg3, rightimg, centerimg;
    protected TextView lefttxt, righttxt, centertxt, righttxt1;
    protected View leftlayout;
    protected TextView leftlayout_tv;


    public void findTopViews() {
        topview = rootView.findViewById(R.id.topview);
        centerview = rootView.findViewById(R.id.base_topview_center);
        leftview = rootView.findViewById(R.id.base_topview_left);
        rightview = rootView.findViewById(R.id.base_topview_right);
        rightview1 = rootView.findViewById(R.id.base_topview_right1);


        leftimg1 = (ImageView) rootView.findViewById(R.id.base_topview_left_img1);
        leftimg2 = (ImageView) rootView.findViewById(R.id.base_topview_left_img2);
        leftimg3 = (ImageView) rootView.findViewById(R.id.base_topview_left_img3);
        rightimg = (ImageView) rootView.findViewById(R.id.base_topview_right_img);
        centerimg = (ImageView) rootView.findViewById(R.id.base_topview_center_img);
        centertxt = (TextView) rootView.findViewById(R.id.base_topview_center_tv);
        lefttxt = (TextView) rootView.findViewById(R.id.base_topview_left_tv);
        righttxt = (TextView) rootView.findViewById(R.id.base_topview_right_tv);
        righttxt1 = (TextView) rootView.findViewById(R.id.base_topview_right1_tv);
        leftlayout = rootView.findViewById(R.id.base_topview_left_layout1);
        leftlayout_tv = (TextView) rootView.findViewById(R.id.base_topview_left_layout1_tv);
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("该账号已在其他移动端设备登录，如非本人操作请修改登录密码！");
        builder.setTitle("提示");
        builder.setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent i = new Intent(context, Login_Register_ResetPasswordActivity.class);
                i.putExtra(Constant.BYTYPE, "bylogin");
                i.putExtra("toBuy", 0);
                startActivity(i);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MyUtils.onLoginOut(context);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }


    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
    }

    public MyApplication getMyApplication() {
        return (MyApplication) (this.getActivity().getApplication());
    }


    public void showLoadingDialog(String txt, boolean canback) {
        if (this.loading_dialog != null && !this.loading_dialog.isShowing()) {
            if (!canback) {
                this.loading_dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (i == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                            return true;
                        else return false;
                    }
                });
                this.loading_dialog.setCancelable(false);
            }
            this.loading_dialog.setMessage(txt);
            this.loading_dialog.show();
        }
    }

    public void showLoadingDialog() {
        if (this.loading_dialog != null && !this.loading_dialog.isShowing())
            this.loading_dialog.show();
    }

    public void cancelLoadingDialog() {
        if (this.loading_dialog != null && this.loading_dialog.isShowing())
            this.loading_dialog.cancel();
    }

    public void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


    public void setMessageImage() {
        if (rootView != null)
            if (MyUtils.isLogin()) {
                if (MessageLoader.getMessageNumberAll() == 0) {
                    rightimg.setImageResource(R.drawable.message);
                } else {
                    rightimg.setImageResource(R.drawable.messagenew);
                }
            } else {
                rightimg.setImageResource(R.drawable.message);
            }
    }

}
