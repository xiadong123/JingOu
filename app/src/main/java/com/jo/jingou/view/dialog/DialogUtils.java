package com.jo.jingou.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jo.jingou.R;


/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class DialogUtils {

    public static void showTelDialog(Activity activity, String content, String confirmText, DialogConfirm
            dialogConfirm) {

        Dialog alertDialog = new Dialog(activity, R.style.MyDialog);

        View view = View.inflate(alertDialog.getContext(), R.layout.dialog_project_save_draft, null);
        RelativeLayout sProject_save_draft_confirm = (RelativeLayout) view.findViewById(R.id
                .project_save_draft_confirm);
        RelativeLayout sProject_save_draft_cancel = (RelativeLayout) view.findViewById(R.id.project_save_draft_cancel);
        TextView sProject_save_draft_confirm_tv = (TextView) view.findViewById(R.id.project_save_draft_confirm_tv);

        TextView sDialog_title_tv = (TextView) view.findViewById(R.id.dialog_title_tv);
        sProject_save_draft_confirm_tv.setText(confirmText);
        sDialog_title_tv.setText(content);
        alertDialog.setContentView(view);

        sProject_save_draft_confirm.setOnClickListener(new MyDialogClickListener(activity, alertDialog, dialogConfirm));
        sProject_save_draft_cancel.setOnClickListener(new MyDialogClickListener(activity, alertDialog, dialogConfirm));

        if (!activity.isFinishing()) {
            alertDialog.show();
        }
    }

    /**
     * 自定义dialog  的 点击事件
     */
    private static class MyDialogClickListener implements View.OnClickListener {

        private Activity activity;
        private DialogConfirm dialogConfirm;
        private Dialog alertDialog;

        public MyDialogClickListener(Activity activity, Dialog alertDialog, DialogConfirm dialogConfirm) {
            this.activity = activity;
            this.dialogConfirm = dialogConfirm;
            this.alertDialog = alertDialog;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.project_save_draft_confirm:  //确定

                    dialogConfirm.confirm();
                    if (!activity.isFinishing()) {
                        alertDialog.dismiss();
                    }
                    break;
                case R.id.project_save_draft_cancel:  //取消

                    if (!activity.isFinishing()) {
                        alertDialog.dismiss();
                    }
                    break;
            }
        }
    }

    public interface DialogConfirm {
        void confirm();
    }

}
