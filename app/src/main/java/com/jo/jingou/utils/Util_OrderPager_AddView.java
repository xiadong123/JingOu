package com.jo.jingou.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.activity.OrderActivity;
import com.jo.jingou.model.AngelBuyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfyu on 2016/12/1.
 */
public class Util_OrderPager_AddView {


    public static List<View> addView(final Activity activity, List<AngelBuyModel.PrpductEntity> lists) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        List<View> views = new ArrayList<>();

        for (AngelBuyModel.PrpductEntity entity : lists) {
            View business_view = layoutInflater.inflate(R.layout.item_order_business, null);

            ((TextView) business_view.findViewById(R.id.item_order_business_alltitle)).setText("订单由 " + entity.getMainTitles() + " 配送");

            ((TextView) business_view.findViewById(R.id.item_order_business_allpostage)).setText(entity.getPostage());

            final TextView remark_tv = (TextView) business_view.findViewById(R.id.item_order_business_remark_tv);


            business_view.findViewById(R.id.item_order_business_remark_layout).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String deftxt = remark_tv.getText().toString();
                    PopupWindow pop = Util_PopupWindow.setCommonPopupWindow_Edittext((OrderActivity) activity, deftxt, new Util_PopupWindow.OnEdittextCallBack() {
                        public void OnEdittextCallBack(String txt) {
                            remark_tv.setText(txt);
                        }
                    }, "订单备注");
                    Util_PopupWindow.show(pop, activity);
                }
            });


            LinearLayout linearLayout = (LinearLayout) business_view.findViewById(R.id.item_order_business_linearlayout);
            double totalprice = 0;
            int amount = 0;


            for (AngelBuyModel.PrpductEntity.ModelEntity modelEntity : entity.getModel()) {
                View goods_view = layoutInflater.inflate(R.layout.item_order_goods, null);
                ((TextView) goods_view.findViewById(R.id.item_order_goods_title)).setText(modelEntity.getMainTitle());
                if (MyUtils.hasValue(modelEntity.getSpecificationName()))
                    ((TextView) goods_view.findViewById(R.id.item_order_goods_para)).setText(modelEntity.getSpecificationName() + ":" + modelEntity.getSpecifications());
                ((TextView) goods_view.findViewById(R.id.item_order_goods_price)).setText("¥ " + modelEntity.getOriginalprice());
                ((TextView) goods_view.findViewById(R.id.item_order_goods_number)).setText("x" + modelEntity.getAmount());
                Glide.with(activity).load(Constant.SERVER_HOST + modelEntity.getListImgUrl()).into((ImageView) goods_view.findViewById(R.id.item_order_goods_pic));
                linearLayout.addView(goods_view, 1);

                amount += modelEntity.getAmount();
                totalprice += Double.parseDouble(modelEntity.getTotalPrice());
            }
            ((TextView) business_view.findViewById(R.id.item_order_business_allnumber)).setText("共" + amount + "件");
            ((TextView) business_view.findViewById(R.id.item_order_business_allprice)).setText("小计：￥" + totalprice);
            views.add(business_view);

        }
        return views;
    }
}
