package com.jo.jingou.activity;

import android.support.v4.view.ViewCompat;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.view.ZoomImageView;

/**
 * Created by dfyu on 2017/5/2.
 */
public class ImageListActivity extends MyBaseActivity {
    public static final String EXTRA_PARAM_ID = "0";
    public static final String VIEW_NAME_HEADER_IMAGE = "1";
    ZoomImageView imageView;


    @Override
    public void initData() {
        layoutId = R.layout.activity_imagelist;
    }

    @Override
    public void findViews() {

        imageView = (ZoomImageView) findViewById(R.id.imageview);

        switch (getIntent().getStringExtra("imgtype")) {
            case "imgurl":
                Glide.with(this).load(Constant.SERVER_HOST + getIntent().getStringExtra("imgurl")).into(imageView);
                break;
            case "mybussiness":
                Glide.with(this).load(getIntent().getStringExtra("imgurl")).into(imageView);
//                Glide.with(this).load("file://storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1491099382621.jpg")
//                        .into
//                        (imageView);
                break;
        }

        ViewCompat.setTransitionName(findViewById(R.id.imageview), VIEW_NAME_HEADER_IMAGE);
    }

    @Override
    public void initListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void setupViews() {

    }


}
