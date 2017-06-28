package com.jo.jingou.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
 * Created by dfyu on 2016/12/16.
 */
public class OrderCommentActivity extends MyBaseActivity {

    PopupWindow pop;

    View submit;

    ImageView iv0;
    TextView tv_title, tv_price, tv_num;

    LinearLayout iv_layout;
    List<BitmapAndView> bitmapAndViews = new ArrayList<>();


    View iv3_layout;
    ImageView iv3;
    EditText editText;

    RatingBar ratingBar0, ratingBar1, ratingBar2;

    String anonymous = "0";

    String productid, merchantNum, imgurl, product_name, price, count;

    @Override
    public void initData() {
        layoutId = R.layout.activity_order_comment;
    }

    @Override
    public void findViews() {

        iv_layout = (LinearLayout) findViewById(R.id.activity_ordr_comment_iv_layout);

        iv0 = (ImageView) findViewById(R.id.item_ordercomment_pic);
        tv_title = (TextView) findViewById(R.id.item_ordercomment_title);
        tv_price = (TextView) findViewById(R.id.item_ordercomment_price);
        tv_num = (TextView) findViewById(R.id.item_ordercomment_number);
        submit = findViewAndSetClick(R.id.item_ordercomment_tv1);

        iv3_layout = findViewAndSetClick(R.id.activity_order_comment_iv3_layout);
        iv3 = (ImageView) findViewById(R.id.activity_order_comment_iv3);
        editText = (EditText) findViewById(R.id.activity_order_comment_ed0);
        ratingBar0 = (RatingBar) findViewById(R.id.activity_order_comment_rb0);
        ratingBar1 = (RatingBar) findViewById(R.id.activity_order_comment_rb1);
        ratingBar2 = (RatingBar) findViewById(R.id.activity_order_comment_rb2);

        pop = Util_PopupWindow.setPopupWindow_CutImg(this, "评价图片");

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("发表评价");
        View v = getLayoutInflater().inflate(R.layout.item_ordercomment_image, null);

        bitmapAndViews.add(new BitmapAndView(v));
        iv_layout.addView(v);
        productid = getIntent().getStringExtra("productId");
        merchantNum = getIntent().getStringExtra("merchantNum");
        imgurl = getIntent().getStringExtra("imgurl");
        product_name = getIntent().getStringExtra("product_name");
        price = getIntent().getStringExtra("price");
        count = getIntent().getStringExtra("count");

        Glide.with(this).load(Constant.SERVER_HOST + imgurl).into(iv0);
        tv_title.setText(product_name);
        tv_price.setText("¥ " + price);
        tv_num.setText("x" + count);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.item_ordercomment_tv1:
//                if (!MyUtils.hasValue(editText.getText().toString().trim())) {
//                    showToast("请输入评价内容");
//                    return;
//                }

                File[] files;
                String[] names;
                List<File> filelist = new ArrayList<>();
                List<String> namelist = new ArrayList<>();

                for (int i = 0; i < bitmapAndViews.size(); i++) {
                    try {
                        if (bitmapAndViews.get(i).getBitmap() != null) {
                            File file = MyUtils.saveFile(bitmapAndViews.get(i).getBitmap(), "pingjia" + i + ".jpg");
                            filelist.add(file);
                            namelist.add("pingjia" + i + ".jpg");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                files = filelist.toArray(new File[filelist.size()]);
                names = namelist.toArray(new String[namelist.size()]);

                if (files.length == 0) {
                    OkHttpClientManager.postAsyn(
                            Constant.COMMENT + MyApplication.getToken() + "," + MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                            new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("productid", productid),
                                    new OkHttpClientManager.Param("merchantNum", merchantNum),
                                    new OkHttpClientManager.Param("commentcontent", editText.getText().toString()),
                                    new OkHttpClientManager.Param("score", ratingBar0.getRating() + "," + ratingBar1.getRating() + "," + ratingBar2.getRating()),
                                    new OkHttpClientManager.Param("anonymous", anonymous)},
                            new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    cancelLoadingDialog();
                                }

                                @Override
                                public void onResponse(MyBaseModel model) {
                                    cancelLoadingDialog();
                                    if (model.getErrcode() == 0) {
                                        showToast("评价成功");
                                        OrderCommentActivity.this.finish();
                                    } else showToast(model.getErrmsg());
                                }
                            }, "cnupload");
                } else {

                    OkHttpClientManager.getUploadDelegate().postAsyn(
                            Constant.COMMENT + MyApplication.getToken() + "," + MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                            names, files,
                            new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("productid", productid),
                                    new OkHttpClientManager.Param("merchantNum", merchantNum),
                                    new OkHttpClientManager.Param("commentcontent", editText.getText().toString()),
                                    new OkHttpClientManager.Param("score", ratingBar0.getRating() + "," + ratingBar1.getRating() + "," + ratingBar2.getRating()),
                                    new OkHttpClientManager.Param("anonymous", anonymous)},
                            new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                                @Override
                                public void onError(Request request, Exception e) {
                                    cancelLoadingDialog();
                                }

                                @Override
                                public void onResponse(MyBaseModel model) {
                                    cancelLoadingDialog();
                                    if (model.getErrcode() == 0) {
                                        showToast("评价成功");
                                        OrderCommentActivity.this.finish();
                                    } else showToast(model.getErrmsg());
                                }
                            }, "cnupload");

                }

                break;

            case R.id.activity_order_comment_iv3_layout:
                if ("0".equals(anonymous)) {
                    iv3.setImageResource(R.drawable.add);
                    anonymous = "1";
                } else {
                    iv3.setImageResource(R.drawable.addr);
                    anonymous = "0";
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
                    if (MyUtils.getPermission(OrderCommentActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
                        cache_bitmap = BitmapAndView.this;
                        Util_PopupWindow.show(pop, OrderCommentActivity.this);
                    }
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
