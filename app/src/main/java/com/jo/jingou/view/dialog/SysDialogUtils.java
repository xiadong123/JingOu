package com.jo.jingou.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class SysDialogUtils {

    //设置并显示删除时弹出的dialog
    public static void setAndShowDialog(Activity activity, String content, String confirmText, String cancelText,
                                        final DialogConfirm
                                                dialogConfirm) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("提示");
        builder.setMessage(content);
        builder.setPositiveButton(confirmText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogConfirm.confirm();
            }
        });
        builder.setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //builder.setCancelable(false);
        builder.create().show();
    }


    public interface DialogConfirm {
        void confirm();
    }

}
