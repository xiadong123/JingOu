package com.jo.jingou.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.activity.Agreement;
import com.jo.jingou.activity.Login_Register_ResetPasswordActivity;
import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_Network;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import caesar.feng.framework.BaseActivity;
import caesar.feng.framework.utils.Utility;


/**
 * s
 * Created by dfyu on 2015/11/4.
 */
public abstract class MyBaseActivity extends BaseActivity implements MessageLoader.OnMessageChangeListener {
    protected static MyApplication application;
    protected Util_Network utilNetwork;
    protected BroadcastReceiver broadcastReceiver;
    protected ProgressDialog loading_dialog;
    protected PopupWindow loginout_popupwindow;
    protected int imageSwitch = 0;
    protected String image_path;

    //TopView的子view
    protected View topview, centerview, leftview, rightview;
    protected ImageView leftimg1, leftimg2, leftimg3, rightimg, centerimg;
    protected TextView lefttxt, righttxt, centertxt;
    protected View leftlayout;
    protected TextView leftlayout_tv;

    //nodata_loadfail_reload的子view
    protected TextView nodata;
    protected View loadfail;
    protected View reload;

    public static boolean needCompletePhoto = false;

    protected void initViews() {
        this.initData();
        if (this.layoutId != -10086) {
            this.setContentView(this.layoutId);
            commonSetting();
            this.findViews();
            this.initListener();
            this.setupViews();
        } else {
            Utility.showToast(this, "layoutId 未初始化");
        }
    }

    public void commonSetting() {
        this.application = getMyApplication();
        if (getActionBar() != null) getActionBar().hide();

        this.utilNetwork = new Util_Network(this) {
            protected <T extends MyBaseModel> void request(int code1, int code2, T model) {
                super.request(code1, code2, model);
                if (code1 == Constant.NETWORKERROR) {
                    showToast("网络请求失败！");
                } else if (code1 == Constant.LOGININVAILD) {

                    dialog();
                    //Util_PopupWindow.show(loginout_popupwindow, MyBaseActivity.this);
                } else if (code1 != Constant.NETWORKSUCCESS) {
                    showToast(model.getErrmsg());
                }
                onNetworkCallBack(code1, code2, model);
            }
        };

        this.broadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
            }
        };

        MessageLoader.addListener(this);

        this.loading_dialog = new ProgressDialog(this);
        loading_dialog.setTitle("");
        loading_dialog.setMessage("加载中，请稍后...");
        loading_dialog.setIndeterminate(true);
        loading_dialog.setCancelable(true);
        loading_dialog.setOnCancelListener(null);
        findTopViews();
    }

    public MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }

    public void findTopViews() {
        topview = findViewById(R.id.topview);
        centerview = findViewById(R.id.base_topview_center);
        leftview = findViewById(R.id.base_topview_left);
        rightview = findViewById(R.id.base_topview_right);
        leftimg1 = (ImageView) findViewById(R.id.base_topview_left_img1);
        leftimg2 = (ImageView) findViewById(R.id.base_topview_left_img2);
        leftimg3 = (ImageView) findViewById(R.id.base_topview_left_img3);
        rightimg = (ImageView) findViewById(R.id.base_topview_right_img);
        centerimg = (ImageView) findViewById(R.id.base_topview_center_img);
        centertxt = (TextView) findViewById(R.id.base_topview_center_tv);
        lefttxt = (TextView) findViewById(R.id.base_topview_left_tv);
        righttxt = (TextView) findViewById(R.id.base_topview_right_tv);
        leftlayout = findViewById(R.id.base_topview_left_layout1);
        leftlayout_tv = (TextView) findViewById(R.id.base_topview_left_layout1_tv);
        goneTopViews();
    }

    protected void goneTopViews() {
        MyUtils.setViewsVorG(View.GONE, leftimg1, leftimg2, leftimg3, rightimg, centerimg, centertxt, lefttxt,
                righttxt, leftlayout);
    }

    protected void setTopView1() {
        leftview.setVisibility(View.VISIBLE);
        centerview.setVisibility(View.VISIBLE);
        rightview.setVisibility(View.VISIBLE);
        leftview.setOnClickListener(this);
        rightview.setOnClickListener(this);

    }

    protected void setTopview2(String title) {
        setTopView1();
        goneTopViews();
        centertxt.setVisibility(View.VISIBLE);
        centertxt.setText(title);
        leftimg3.setVisibility(View.VISIBLE);
        leftimg3.setImageResource(R.drawable.back);
    }

    protected void setNoData_LoadFail_ReLoadViews(String hint) {
        nodata = (TextView) findViewById(R.id.nodata);
        loadfail = findViewById(R.id.loadfail);
        reload = findViewById(R.id.reload);

        if (hasNoData_LoadFail_ReLoadViews()) {

            if (MyUtils.hasValue(hint)) {
                nodata.setText(hint);
            } else {
                nodata.setText("未获取到相关数据");
            }
            reload.setOnClickListener(this);

            nodata.setVisibility(View.GONE);
            loadfail.setVisibility(View.GONE);
        }
    }


    protected boolean hasNoData_LoadFail_ReLoadViews() {
        if (nodata != null && loadfail != null && reload != null) {
            return true;
        } else {
            Log.i("TAG", "未获取到空数据，加载失败，重新加载等相关控件！！！");
            return false;
        }
    }


    protected void setViewOnLoadFail() {
        nodata.setVisibility(View.GONE);
        loadfail.setVisibility(View.VISIBLE);
    }

    protected void setViewOnNoData() {
        nodata.setVisibility(View.VISIBLE);
        loadfail.setVisibility(View.GONE);
    }

    protected void setViewOnLoadSuccessHadData() {
        nodata.setVisibility(View.GONE);
        loadfail.setVisibility(View.GONE);
    }


    protected void setNoData_LoadFail_ReLoadViews(View view, String hint, View.OnClickListener onClickListener) {
        TextView nodata = (TextView) view.findViewById(R.id.nodata);
        View loadfail = view.findViewById(R.id.loadfail);
        View reload = view.findViewById(R.id.reload);

        if (hasNoData_LoadFail_ReLoadViews(nodata, loadfail, reload)) {

            if (MyUtils.hasValue(hint)) {
                nodata.setText(hint);
            } else {
                nodata.setText("未获取到相关数据");
            }
            reload.setOnClickListener(onClickListener);

            nodata.setVisibility(View.GONE);
            loadfail.setVisibility(View.GONE);
        }
    }


    protected boolean hasNoData_LoadFail_ReLoadViews(View... views) {
        for (View v : views) {
            if (v == null) {
                Log.i("TAG", "未获取到空数据，加载失败，重新加载等相关控件！！！");
                return false;
            }
        }
        return true;
    }

    protected void setViewOnLoadFail(View view) {
        view.findViewById(R.id.nodata).setVisibility(View.GONE);
        view.findViewById(R.id.loadfail).setVisibility(View.VISIBLE);
    }

    protected void setViewOnNoData(View view) {
        view.findViewById(R.id.nodata).setVisibility(View.VISIBLE);
        view.findViewById(R.id.loadfail).setVisibility(View.GONE);
    }

    protected void setViewOnLoadSuccessHadData(View view) {
        view.findViewById(R.id.nodata).setVisibility(View.GONE);
        view.findViewById(R.id.loadfail).setVisibility(View.GONE);
    }


    protected void setLogoutView() {
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.onLoginOut(MyBaseActivity.this);
            }
        });
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyBaseActivity.this);
        builder.setMessage("该账号已在其他移动端设备登录，如非本人操作请修改登录密码！");
        builder.setTitle("提示");
        builder.setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent i = new Intent(MyBaseActivity.this, Login_Register_ResetPasswordActivity.class);
                i.putExtra(Constant.BYTYPE, "bylogin");
                i.putExtra("toBuy", 0);
                startActivity(i);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MyUtils.onLoginOut(MyBaseActivity.this);
            }
        });

        builder.setCancelable(false);
        builder.create().show();
    }

    /**
     * 更新intent
     */
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
//    protected void onResume() {
//        super.onResume();
//        //注：回调 1
//        Bugtags.onResume(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //注：回调 2
//        Bugtags.onPause(this);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        //注：回调 3
//        Bugtags.onDispatchTouchEvent(this, event);
//        return super.dispatchTouchEvent(event);
//    }


    protected void onDestroy() {
        try {
            if (broadcastReceiver != null) unregisterReceiver(broadcastReceiver);
            if (Util.isOnMainThread()) Glide.get(this).clearMemory();
            MessageLoader.removeListener(this);
        } catch (Exception e) {
        }
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.IMGCALLBACK:
                if (imageSwitch == 1) { // 接收相机回调
                    if (!needCompletePhoto) {
                        getCropImagePhoto(Uri.fromFile(new File(image_path)), Constant.IMGCALLBACKCUT, "头像");
                    } else {
                        onCompleteImageCallBack(image_path);
                    }
                } else if (imageSwitch == 2) { // 接收相册回调
                    if (data != null) {
                        String currentFilePath = MyUtils.getPath(MyBaseActivity.this, data.getData()) + "";
                        if (!currentFilePath.isEmpty())
                            getCropImagePhoto(Uri.fromFile(new File(currentFilePath)), Constant.IMGCALLBACKCUT, "头像");
                        else
                            showToast("未在存储卡中找到这个文件");
                    }

                    if (data != null) {
                        Log.i("TAG", "data.setData() =========" + data.getData());

                        if ((data.getData() + "").startsWith("file://")) {
                            if (!needCompletePhoto) {
                                getCropImagePhoto(Uri.fromFile(new File((data.getData() + "").substring(7))), Constant
                                        .IMGCALLBACKCUT, "头像");
                            } else {
                                onCompleteImageCallBack((data.getData() + "").substring(7));
                            }

                        } else {
                            String currentFilePath = MyUtils.getPath(MyBaseActivity.this, data.getData()) + "";
                            File file = new File(currentFilePath);
                            Log.e("onActivityResult", file.exists() + "--");
                            if (!currentFilePath.isEmpty())
                                if (!needCompletePhoto) {
                                    getCropImagePhoto(Uri.fromFile(new File(currentFilePath)), Constant.IMGCALLBACKCUT,
                                            "头像");
                                } else {
                                    onCompleteImageCallBack(currentFilePath);
                                }
                            else
                                showToast("未在存储卡中找到这个文件");
                        }
                    }
                }
                break;
            case Constant.IMGCALLBACKCUT:
                Log.i("TAG", "onIMGCALLBACKCUT!!!!!!!!!!!!!!");
                if (data != null) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    onImageCutCallBack(bitmap);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 图片回调剪切
     *
     * @param muri
     */
    public void getCropImagePhoto(Uri muri, int code, String type) {
        Intent intent = new Intent();
        File file = null;
        intent.setAction("com.android.camera.action.CROP");
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  //如果版本大于7.0
                file = new File(new URI(muri.toString()));
                muri = FileProvider.getUriForFile(MyApplication.getApplication(), "com.jo.jingou.provider", file);
                //通过FileProvider创建一个content类型的Uri
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            // mUri是已经选择的图片Uri
            intent.setDataAndType(muri, "image/*");
            intent.putExtra("crop", "true");
            if ("头像".equals(type)) {
                intent.putExtra("aspectX", 1);// 裁剪框比例
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 500);// 输出图片大小
                intent.putExtra("outputY", 500);
            }
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            startActivityForResult(intent, code);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setPath(String path) {
        this.image_path = path;
    }

    public void imgSwitch(int imageSwitch) {
        this.imageSwitch = imageSwitch;
    }

    public void onImageCutCallBack(Bitmap bitmap) {
        Log.i("TAG", "onImageCutCallBack!!!!!!!!!!!!!!" + (bitmap == null));
    }

    public void onCompleteImageCallBack(String mUri) {
        Log.i("TAG", "onCompleteImageCutCallBack!!!!!!!!!!!!!!" + (mUri == null));
    }

    protected boolean isLogin() {
        if (application.getLogin_type().equals("1")) {
            return true;
        } else {
            showToast("请登录");
            return false;
        }
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

    private static Toast mToast;

    public static void showToast(String str) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getApplication(), str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }


    /**
     * findview and setonclick
     *
     * @param id
     * @return
     */
    protected <V extends View> V findViewAndSetClick(int id) {
        View view = findViewById(id);
        view.setOnClickListener(this);
        return (V) view;
    }


    /**
     * 打开软键盘
     */
    protected void openKeyboard(Handler mHandler, int s) {
        mHandler.postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, s);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.activity_LoginRegisterResterPassword_agreement:
                Intent intent = new Intent(this, Agreement.class);
                startActivity(intent);
                break;


        }
    }

    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
    }

    @Override
    public void onMessageChanged(boolean hasNewMessage, int newMessageNumber) {

    }
}
