package com.jo.jingou.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_PopupWindow;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2017/3/24.
 */
public class AddAfterSalesActivity extends MyBaseActivity {


    View view_state, view_reason, view_price;
    TextView tv_reasonname, tv_remarkname;
    TextView tv_type, tv_state, tv_reason;
    EditText ed_remark;
    TextView tv_price;
    View submit;


    LinearLayout iv_layout;
    List<BitmapAndView> bitmapAndViews = new ArrayList<>();

    PopupWindow pop_receiving_state, pop_return_reason;

    String productid, productpara, num, money, orderid, type;
    int state;

    @Override
    public void initData() {
        layoutId = R.layout.activity_addaftersales;
    }

    @Override
    public void findViews() {

        view_state = findViewAndSetClick(R.id.activity_aftersalesdetail_layout1);
        view_reason = findViewAndSetClick(R.id.activity_aftersalesdetail_layout2);
        view_price = findViewById(R.id.activity_aftersalesdetail_layout3);

        tv_reasonname = (TextView) findViewById(R.id.activity_aftersalesdetail_tv2);
        tv_remarkname = (TextView) findViewById(R.id.activity_aftersalesdetail_tv4);

        tv_type = (TextView) findViewById(R.id.activity_aftersalesdetail_tv00);
        tv_state = (TextView) findViewById(R.id.activity_aftersalesdetail_tv11);
        tv_reason = (TextView) findViewById(R.id.activity_aftersalesdetail_tv22);
        tv_price = (TextView) findViewById(R.id.activity_aftersalesdetail_tv33);

        ed_remark = (EditText) findViewById(R.id.activity_aftersalesdetail_ed);

        iv_layout = (LinearLayout) findViewById(R.id.activity_aftersalesdetail_iv_layout);

        submit = findViewAndSetClick(R.id.submit);

        pop_receiving_state = Util_PopupWindow.setPopupWindow_ReceivingState(this, new Util_PopupWindow.OnCallBack_int_string() {
            @Override
            public void onSexCallBack(int sextype, String sex) {
                state = sextype;
                tv_state.setText(sex);
            }
        }, "收货状态");

//        pop_return_reason = Util_PopupWindow.setPopupWindow_ReturnReason(this, new Util_PopupWindow.OnEdittextCallBack() {
//            @Override
//            public void OnEdittextCallBack(String txt) {
//                tv_reason.setText(txt);
//            }
//        }, "退货理由");

        pop = Util_PopupWindow.setPopupWindow_CutImg(this, "售后图片");

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("申请售后");
        productid = getIntent().getStringExtra("productid");
        productpara = getIntent().getStringExtra("productpara");
        num = getIntent().getStringExtra("num");
        money = getIntent().getStringExtra("money");
        orderid = getIntent().getStringExtra("orderid");
        type = getIntent().getStringExtra("type");

        if (type.equals("0")) {
            tv_type.setText("退款退货");
        } else if (type.equals("1")) {
            tv_type.setText("退款（无需退货）");
        } else if (type.equals("2")) {
            tv_type.setText("换货");
            tv_state.setText("已收货");
            state = 1;

            view_state.setOnClickListener(null);

            tv_reasonname.setText("换货原因");
            tv_remarkname.setText("换货说明");

            view_price.setVisibility(View.GONE);
        }


        if (!type.equals("2")) {
            pop_return_reason = Util_PopupWindow.setPopupWindow_ReturnReason(this, new Util_PopupWindow.OnEdittextCallBack() {
                @Override
                public void OnEdittextCallBack(String txt) {
                    tv_reason.setText(txt);
                }
            }, "退货理由");
        } else {
            pop_return_reason = Util_PopupWindow.setPopupWindow_ReturnReason2(this, new Util_PopupWindow.OnEdittextCallBack() {
                @Override
                public void OnEdittextCallBack(String txt) {
                    tv_reason.setText(txt);
                }
            }, "换货理由");
        }


        tv_price.setText(money);
        View v = getLayoutInflater().inflate(R.layout.item_ordercomment_image, null);

        bitmapAndViews.add(new BitmapAndView(v));
        iv_layout.addView(v);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_aftersalesdetail_layout1:
                Util_PopupWindow.show(pop_receiving_state, this);
                break;
            case R.id.activity_aftersalesdetail_layout2:
                Util_PopupWindow.show(pop_return_reason, this);
                break;
            case R.id.submit:
                showToast("提交");
                submit();
                break;
        }
    }

    private void submit() {

        if (!MyUtils.hasValue(tv_state.getText().toString())) {
            showToast("请选择收货状态");
            return;
        }
        if (!MyUtils.hasValue(tv_reason.getText().toString())) {
            showToast("请选择退款原因");
            return;
        }
        if (!MyUtils.hasValue(ed_remark.getText().toString())) {
            showToast("请输入退款说明");
            return;
        }

//        showLoadingDialog();
//        utilNetwork.getReturnsModel(new OkHttpClientManager.Param[]{
//                new OkHttpClientManager.Param("productid", productid),
//                new OkHttpClientManager.Param("productpara", productpara),
//                new OkHttpClientManager.Param("num", num),
//                new OkHttpClientManager.Param("type", type),
//                new OkHttpClientManager.Param("Receiving", state + ""),
//                new OkHttpClientManager.Param("reason", tv_reason.getText().toString()),
//                new OkHttpClientManager.Param("money", money),
//                new OkHttpClientManager.Param("explain", ed_remark.getText().toString()),
//                new OkHttpClientManager.Param("orderid", orderid)
//        });


        File[] files;
        String[] names;
        List<File> filelist = new ArrayList<>();
        List<String> namelist = new ArrayList<>();

        for (int i = 0; i < bitmapAndViews.size(); i++) {
            try {
                if (bitmapAndViews.get(i).getBitmap() != null) {
                    File file = MyUtils.saveFile(bitmapAndViews.get(i).getBitmap(), "tuihuanhuoshenqing" + i + ".jpg");
                    filelist.add(file);
                    namelist.add("tuihuanhuoshenqing" + i + ".jpg");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        files = filelist.toArray(new File[filelist.size()]);
        names = namelist.toArray(new String[namelist.size()]);

        if (files.length == 0) {
            OkHttpClientManager.postAsyn(
                    Constant.RETURNS + MyApplication.getToken() + "," + MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                    new OkHttpClientManager.Param[]{
                            new OkHttpClientManager.Param("productid", productid),
                            new OkHttpClientManager.Param("productpara", productpara),
                            new OkHttpClientManager.Param("num", num),
                            new OkHttpClientManager.Param("type", type),
                            new OkHttpClientManager.Param("Receiving", state + ""),
                            new OkHttpClientManager.Param("reason", tv_reason.getText().toString()),
                            new OkHttpClientManager.Param("money", money),
                            new OkHttpClientManager.Param("explain", ed_remark.getText().toString()),
                            new OkHttpClientManager.Param("orderid", orderid)
                    },
                    new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            cancelLoadingDialog();
                        }

                        @Override
                        public void onResponse(MyBaseModel model) {
                            cancelLoadingDialog();
                            if (model.getErrcode() == 0) {
                                showToast("申请提交成功");
                                AddAfterSalesActivity.this.finish();
                            } else showToast(model.getErrmsg());
                        }
                    }, "cnupload");
        } else {
            OkHttpClientManager.getUploadDelegate().postAsyn(
                    Constant.RETURNS + MyApplication.getToken() + "," + MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                    names, files,
                    new OkHttpClientManager.Param[]{
                            new OkHttpClientManager.Param("productid", productid),
                            new OkHttpClientManager.Param("productpara", productpara),
                            new OkHttpClientManager.Param("num", num),
                            new OkHttpClientManager.Param("type", type),
                            new OkHttpClientManager.Param("Receiving", state + ""),
                            new OkHttpClientManager.Param("reason", tv_reason.getText().toString()),
                            new OkHttpClientManager.Param("money", money),
                            new OkHttpClientManager.Param("explain", ed_remark.getText().toString()),
                            new OkHttpClientManager.Param("orderid", orderid)
                    },
                    new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            cancelLoadingDialog();
                        }

                        @Override
                        public void onResponse(MyBaseModel model) {
                            cancelLoadingDialog();
                            if (model.getErrcode() == 0) {
                                showToast("申请提交成功");
                                AddAfterSalesActivity.this.finish();
                            } else showToast(model.getErrmsg());
                        }
                    }, "cnupload");
        }
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.RETURNS_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("提交申请成功");
                    finish();
                } else {
                    showToast(model.getErrmsg());
                }
                break;
        }
    }


    @Override
    public void onImageCutCallBack(Bitmap bitmap) {
        super.onImageCutCallBack(bitmap);
        cache_bitmap.setBitmap(bitmap);
        cache_bitmap.setCloseVisiblity(View.VISIBLE);

        if (iv_layout.getChildCount() < 3) {
            View v = getLayoutInflater().inflate(R.layout.item_ordercomment_image, null);
            bitmapAndViews.add(new BitmapAndView(v));
            iv_layout.addView(v);
        }
    }


    PopupWindow pop;
    BitmapAndView cache_bitmap;

    class BitmapAndView {
        View view;
        ImageView iv, close;
        Bitmap bitmap;

        public BitmapAndView(View v) {
            view = v;
            iv = (ImageView) view.findViewById(R.id.item_ordercomment_iv);
            iv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    cache_bitmap = BitmapAndView.this;
                    Util_PopupWindow.show(pop, AddAfterSalesActivity.this);
                }
            });
            close = (ImageView) view.findViewById(R.id.item_ordercomment_close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bitmap = null;
                    iv.setImageResource(R.drawable.upload);
                    close.setVisibility(View.GONE);
                }
            });
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            iv.setImageBitmap(bitmap);

        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setCloseVisiblity(int v) {
            close.setVisibility(v);
        }
    }

}
