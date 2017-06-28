package com.jo.jingou.adapter.businessAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jo.jingou.R;
import com.jo.jingou.activity.MyBusinessActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class BrandAdapter extends RecyclerView.Adapter {
    private static final int LASTPOSTION = 101;
    private static final int PHOTOPOSITION = 102;
    private MyBusinessActivity myBusinessActivity;
    private List<String> brandPhoto;

    public BrandAdapter(MyBusinessActivity myBusinessActivity) {
        this.myBusinessActivity = myBusinessActivity;
    }

    public void setData(List<String> brandPhoto) {
        this.brandPhoto = brandPhoto;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == PHOTOPOSITION) {
            view = View.inflate(myBusinessActivity, R.layout.item_business_brand_pho, null);
            return new MyBrandHolder(view);
        } else if (viewType == LASTPOSTION) {
            view = View.inflate(myBusinessActivity, R.layout.item_business_brand_last, null);
            return new LastBrandHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        MyBrandHolder myBrandHolder = (MyBrandHolder) holder;
//        if (brandPhoto.size() == 0) {
//            myBrandHolder.sBusiness_brand_photo_rl.setVisibility(View.GONE);
//            myBrandHolder.sBusiness_brand_upload_rl.setVisibility(View.VISIBLE);
//        } else if (brandPhoto.size() < 9 && brandPhoto.size() - 1 == position) {
//            myBrandHolder.sBusiness_brand_photo_rl.setVisibility(View.VISIBLE);
//            myBrandHolder.sBusiness_brand_upload_rl.setVisibility(View.VISIBLE);
//        } else {
//            myBrandHolder.sBusiness_brand_photo_rl.setVisibility(View.VISIBLE);
//            myBrandHolder.sBusiness_brand_upload_rl.setVisibility(View.GONE);
//        }

        int itemViewType = getItemViewType(position);
        if (itemViewType == PHOTOPOSITION) {
            MyBrandHolder myBrandHolder = (MyBrandHolder) holder;


        } else if (itemViewType == LASTPOSTION) {
            LastBrandHolder lastBrandHolder = (LastBrandHolder) holder;
            lastBrandHolder.sBusiness_brand_upload_rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    brandPhoto.add("kakka");
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == brandPhoto.size()) {  //最后一个
            return LASTPOSTION;
        } else {
            return PHOTOPOSITION;
        }
    }

    @Override
    public int getItemCount() {
        return brandPhoto.size() + 1;
    }

    private class MyBrandHolder extends RecyclerView.ViewHolder {
        private RelativeLayout sBusiness_brand_photo_rl;
        private ImageView sBusiness_brand_photo_iv;
        private ImageView sBusiness_brand_del_iv;

        public MyBrandHolder(View view) {
            super(view);
            sBusiness_brand_photo_rl = (RelativeLayout) view.findViewById(R.id.business_brand_photo_rl);
            sBusiness_brand_photo_iv = (ImageView) view.findViewById(R.id.business_brand_photo_iv);
            sBusiness_brand_del_iv = (ImageView) view.findViewById(R.id.business_brand_del_iv);
        }
    }

    private class LastBrandHolder extends RecyclerView.ViewHolder {
        private RelativeLayout sBusiness_brand_upload_rl;

        public LastBrandHolder(View view) {
            super(view);
            sBusiness_brand_upload_rl = (RelativeLayout) view.findViewById(R.id.business_brand_upload_rl);

        }
    }
}
