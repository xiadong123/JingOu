package com.jo.jingou.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.activity.Agreement;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class AgreementDialogUtil {

    private static TextView agree_tv;
    private static TextView agree_tv11;

    public static void showDialog(Activity activity, DialogConfirm
            dialogConfirm) {

        Dialog alertDialog = new Dialog(activity, R.style.MyAgreeDialog);

        View view = View.inflate(alertDialog.getContext(), R.layout.dialog_agreement, null);
        ImageView sClose_iv = (ImageView) view.findViewById(R.id.close_iv);
//        WebView sAgree_wb = (WebView) view.findViewById(R.id.agree_wb);
        agree_tv = (TextView) view.findViewById(R.id.agree_tv);
        agree_tv11 = (TextView) view.findViewById(R.id.agree_tv11);
        RelativeLayout sDialog_agree_rl = (RelativeLayout) view.findViewById(R.id.dialog_agree_rl);
        alertDialog.setContentView(view);
        //读取本地文本
//        sAgree_wb.getSettings().setJavaScriptEnabled(true);
//        sAgree_wb.getSettings().setDefaultTextEncodingName("utf-8");
//        sAgree_wb.loadUrl("file:///android_assets/a.txt");
        agree_tv.setText("利益天下平台服务协议");
        agree_tv11.setText("一、总则 1.1、 用户在使用静欧商贸服务前，应当仔细阅读《静欧商贸用户协议》（以下简称“本协议”）的全部内容，" +
                "对于本协议中的加重字体、斜体、下划线、颜色标记等条款需重点阅读、理解。 1.2、 用户应当同意本协议的全部条款并按照页面上的提示完成全部的注册程序。" +
                "用户在进行注册程序过程中点击“注册”按钮即表示用户与百度糯米达成以下协议，完全接受本协议项下的全部条款，本协议即在用户与百度糯米之间产生法律效力，" +
                "对双方均具有法律约束力。 1.3、 用户注册成功后，将获得自行注册的用户账号和自行设定的密码在静欧商贸的使用权限，该用户帐号和密码由用户负责保管；" +
                "用户应当对以其用户帐号进行的所有活动和事件负法律责任。 1.4、 静欧商贸用户协议、各个频道单项服务条款、全部活动规则及公告、声明等，" +
                "均为本协议不可分割的组成部分（以下统称协议）。静欧商贸有权对协议进行不定期修订、更新。修改后的协议一经公布即有效替代原有协议，" +
                "新协议公布时会以适当方式通知用户，用户可随时查询最新协议。用户在使用百度糯米提供的各项服务之前，" +
                "应仔细阅读本协议及本协议不可分割的各项内容。用户在使用相关服务时,应关注并遵守其所适用的相关条款。" +
                "用户如不同意本服务协议及/或对其的修改，可以主动取消或停止使用百度糯米提供的服务；用户一旦使用百度糯米服务，" +
                "即视为用户已了解并完全同意本协议及其他服务条款中的各项内容，" +
                "包括百度糯米对本协议及其他服务条款所做的任何修改协议及/或协议的修改将在用户与百度糯米之并凭电子凭证消费的商业活动。");


        sDialog_agree_rl.setOnClickListener(new MyAgreementDialogClickListener(activity, alertDialog, dialogConfirm));
        sClose_iv.setOnClickListener(new MyAgreementDialogClickListener(activity, alertDialog, dialogConfirm));

        if (!activity.isFinishing()) {
            alertDialog.show();
        }
    }

    /**
     * 自定义dialog  的 点击事件
     */
    private static class MyAgreementDialogClickListener implements View.OnClickListener {

        private Activity activity;
        private DialogConfirm dialogConfirm;
        private Dialog alertDialog;

        public MyAgreementDialogClickListener(Activity activity, Dialog alertDialog, DialogConfirm dialogConfirm) {
            this.activity = activity;
            this.dialogConfirm = dialogConfirm;
            this.alertDialog = alertDialog;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_agree_rl:  //确定

                    if (!activity.isFinishing()) {
                        alertDialog.dismiss();
                    }
                    break;
                case R.id.close_iv:  //取消

                    if (!activity.isFinishing()) {
                        alertDialog.dismiss();
                    }
                    activity.finish();
                    break;
            }
        }
    }

    public interface DialogConfirm {
        void confirm();

    }

}
