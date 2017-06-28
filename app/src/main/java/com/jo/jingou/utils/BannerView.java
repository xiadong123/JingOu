package com.jo.jingou.utils;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by dfyu on 2017/4/6.
 */
public class BannerView {

    View mBannerView;
    ImageView mBannerIv, mBannerCircle;

    public BannerView(View mBannerView, ImageView mBannerIv, ImageView mBannerCircle) {
        this.mBannerView = mBannerView;
        this.mBannerIv = mBannerIv;
        this.mBannerCircle = mBannerCircle;
    }

    public void setmBannerView(View mBannerView) {
        this.mBannerView = mBannerView;
    }

    public void setmBannerCircle(ImageView mBannerCircle) {
        this.mBannerCircle = mBannerCircle;
    }

    public void setmBannerIv(ImageView mBannerIv) {
        this.mBannerIv = mBannerIv;
    }

    public View getmBannerView() {
        return mBannerView;
    }

    public ImageView getmBannerIv() {
        return mBannerIv;
    }

    public ImageView getmBannerCircle() {
        return mBannerCircle;
    }
}
