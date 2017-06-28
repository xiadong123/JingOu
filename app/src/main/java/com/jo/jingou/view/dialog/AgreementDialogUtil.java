package com.jo.jingou.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jo.jingou.R;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class AgreementDialogUtil {

    public static void showDialog(Activity activity, DialogConfirm
            dialogConfirm) {

        Dialog alertDialog = new Dialog(activity, R.style.MyAgreeDialog);
//        Dialog alertDialog = new Dialog(activity);
        View view = View.inflate(alertDialog.getContext(), R.layout.dialog_agreement, null);
        ImageView sClose_iv = (ImageView) view.findViewById(R.id.close_iv);
        WebView sAgree_wb = (WebView) view.findViewById(R.id.agree_wb);
        RelativeLayout sDialog_agree_rl = (RelativeLayout) view.findViewById(R.id.dialog_agree_rl);
        alertDialog.setContentView(view);

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
