package com.jo.jingou.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.activity.MessageActivity;
import com.jo.jingou.activity.OrderActivity;
import com.jo.jingou.activity.PayActivity;
import com.jo.jingou.activity.ProductDetailActivity;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.base.MyBaseFragment;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.CartModel;
import com.jo.jingou.model.ShopNumberModel;
import com.jo.jingou.model.ShoppingDModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.view.MyHScrollView;
import com.othershe.baseadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;
import caesar.feng.framework.utils.Utility;

/**
 * Created by dfyu on 2017/4/14.
 */
public class CartFragment extends MyBaseFragment {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    View price_layout;
    TextView price_tv;

    ImageView allselect;
    TextView submit, del;

    TextView havenot_hint;
    View loadfail;
    View reload;

    CommonRefreshAdapter<CartModel.ListEntity> commonRefreshAdapter;

    boolean onEdit;


    @Override
    public void initData() {
        layoutId = R.layout.fragment_cart;
    }

    @Override
    public void findViews() {
        findTopViews();

        price_layout = rootView.findViewById(R.id.fragment_cart_price_linearlayout);
        price_tv = (TextView) rootView.findViewById(R.id.fragment_cart_price_tv);
        allselect = (ImageView) rootView.findViewById(R.id.fragment_cart_allselect);
        submit = (TextView) rootView.findViewById(R.id.fragment_cart_submit);
        del = (TextView) rootView.findViewById(R.id.fragment_cart_del);

        havenot_hint = (TextView) rootView.findViewById(R.id.nodata);
        loadfail = rootView.findViewById(R.id.loadfail);
        reload = rootView.findViewById(R.id.reload);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.fragment_cart_SwipeRefreshLayout);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_cart_recyclerview);

        commonRefreshAdapter = new CommonRefreshAdapter<CartModel.ListEntity>(context, new ArrayList<CartModel
                .ListEntity>(), R.layout.item_cartfragment, false) {
            @Override
            protected void convert(ViewHolder holder, final CartModel.ListEntity data) {
                setView(holder, data);
            }

            //设置店铺
            private void setView(ViewHolder holder, final CartModel.ListEntity data) {
                holder.setText(R.id.item_cartfragment_ll0_title, data.getMainTitles());
                ImageView iv_select = holder.getView(R.id.item_cartfragment_ll0_select);
                for (CartModel.ListEntity.ModelEntity modelEntity : data.getModel()) {
                    if (!modelEntity.hasChecked()) {
                        iv_select.setImageResource(R.drawable.unselected);
                        break;
                    } else {
                        iv_select.setImageResource(R.drawable.selected);
                    }
                }

                iv_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data.setAllChecked(!data.hasAllChecked());
                        notifyData();
                    }
                });

                setSon(holder, data);
            }

            //设置店铺下每个商品
            private void setSon(ViewHolder holder, CartModel.ListEntity data) {
                LinearLayout linearLayout = holder.getView(R.id.item_cartfragment_ll1);
                linearLayout.removeAllViews();

                for (final CartModel.ListEntity.ModelEntity modelEntity : data.getModel()) {
                    View son = inflater.inflate(R.layout.item_cartfragment_son, null);
                    ((TextView) son.findViewById(R.id.item_cartfragment_son_title)).setText(modelEntity.getMainTitle());
                    ((TextView) son.findViewById(R.id.item_cartfragment_son_price)).setText("￥" + modelEntity
                            .getOriginalprice());
                    if (MyUtils.hasValue(modelEntity.getSpecificationName()))
                        ((TextView) son.findViewById(R.id.item_cartfragment_son_specifications)).setText(modelEntity
                                .getSpecificationName() + ":" + modelEntity.getSpecifications());

                    Glide.with(context).load(Constant.SERVER_HOST + modelEntity.getListImgUrl()).into((ImageView) son
                            .findViewById(R.id.item_cartfragment_son_iv1));

                    son.findViewById(R.id.item_cartfragment_son_iv1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(context, ProductDetailActivity.class);
                            i.putExtra("ProductId", modelEntity.getProductId() + "");
                            startActivity(i);
                        }
                    });


                    setSonHScrollView(modelEntity, son);
                    setSonSelectView(modelEntity, son);
                    setSonNumberView(modelEntity, son);

                    linearLayout.addView(son);

                    LinearLayout ll0 = (LinearLayout) son.findViewById(R.id.item_cartfragment_son_ll0);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll0.getLayoutParams();
                    layoutParams.width = Utility.getScreenWidth(context);
                    ll0.setLayoutParams(layoutParams);
                }
            }

            //设置选择功能
            private void setSonSelectView(final CartModel.ListEntity.ModelEntity modelEntity, View son) {
                ImageView iv_son_select = (ImageView) son.findViewById(R.id.item_cartfragment_son_iv0);
                if (modelEntity.hasChecked())
                    iv_son_select.setImageResource(R.drawable.selected);
                else
                    iv_son_select.setImageResource(R.drawable.unselected);

                iv_son_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        modelEntity.setIsChecked(!modelEntity.hasChecked());
                        notifyData();
                    }
                });
            }

            //设置侧滑功能
            private void setSonHScrollView(final CartModel.ListEntity.ModelEntity modelEntity, View son) {
                MyHScrollView hScrollView = (MyHScrollView) son.findViewById(R.id.item_cartfragment_son_hscrollview);

                if (modelEntity.hasOpened()) hScrollView.scroll(ScrollView.FOCUS_RIGHT);
                else hScrollView.scroll(ScrollView.FOCUS_LEFT);


                hScrollView.setScrollZeroPoint(Utility.dip2px(context, 10));

                hScrollView.setOnScrollListener(new MyHScrollView.OnScrollListener() {
                    @Override
                    public void onScroll(int focus) {
                        if (focus == ScrollView.FOCUS_RIGHT) modelEntity.setIsOpened(true);
                        else if (focus == ScrollView.FOCUS_LEFT) modelEntity.setIsOpened(false);
                    }
                });

                View del = son.findViewById(R.id.item_cartfragment_son_del);

                if (onEdit) del.setVisibility(View.GONE);
                else del.setVisibility(View.VISIBLE);

                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        setAndShowDelDialog(
                                modelEntity.getMainTitle() + " x" + modelEntity.getAmount(),
                                modelEntity.getProductId() + "",
                                MyUtils.hasValue(modelEntity.getParaid()) ? modelEntity.getParaid() : "");
                    }
                });
            }

            //设置产品数量功能
            private void setSonNumberView(final CartModel.ListEntity.ModelEntity modelEntity, View son) {
                View minus = son.findViewById(R.id.item_cartfragment_son_minus);
                TextView number = (TextView) son.findViewById(R.id.item_cartfragment_son_number);
                View plus = son.findViewById(R.id.item_cartfragment_son_plus);

                number.setText(modelEntity.getAmount() + "");

//                if (onEdit) {
//                    minus.setOnClickListener(null);
//                    number.setOnClickListener(null);
//                    plus.setOnClickListener(null);
//                }
//                else
                {
                    minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (modelEntity.getAmount() > 1) {
                                modelEntity.setAmount(modelEntity.getAmount() - 1);

                                changeProductNumber(modelEntity);
                                // notifyData();
                            } else Utility.showToast(context, "不能再减了哦");
                        }
                    });

                    plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            modelEntity.setAmount(modelEntity.getAmount() + 1);
                            changeProductNumber(modelEntity);
                            //notifyData();
                        }
                    });
                }


            }

            private void changeProductNumber(CartModel.ListEntity.ModelEntity modelEntity) {
                utilNetwork.getShopNumberModel(
                        new OkHttpClientManager.Param[]{
                                new OkHttpClientManager.Param("productid", modelEntity.getProductId() + ""),
                                new OkHttpClientManager.Param("number", modelEntity.getAmount() + ""),
                                new OkHttpClientManager.Param("productpara",
                                        MyUtils.hasValue(modelEntity.getParaid()) ? modelEntity.getParaid() : "")});
            }

        };
    }


    @Override
    public void initListener() {
        allselect.setOnClickListener(this);
        submit.setOnClickListener(this);
        del.setOnClickListener(this);
        reload.setOnClickListener(this);

        rightview.setOnClickListener(this);
        righttxt1.setOnClickListener(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

    }


    @Override
    public void setupViews() {

        centertxt.setVisibility(View.VISIBLE);
        rightimg.setVisibility(View.VISIBLE);
        righttxt1.setVisibility(View.VISIBLE);
        centertxt.setText("购物车");

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(commonRefreshAdapter);

        upData();
    }

    @Override
    public void upData() {
        if (rootView != null)
            if (MyUtils.isLogin()) {
                getData();
                havenot_hint.setVisibility(View.GONE);
            } else {
                commonRefreshAdapter.setNewData(new ArrayList<CartModel.ListEntity>());
                notifyData();
            }


        setMessageImage();
    }

    @Override
    public void onClick(View v) {
        List<CartModel.ListEntity> listEntities = getListEntities();
        List<CartModel.ListEntity.ModelEntity> modelEntities = getAllSelectModel(listEntities);
        String txt = "", ProductId = "", productNumber = "", productpara = "",ordernum = "";


        switch (v.getId()) {
            case R.id.base_topview_right:
                if (MyUtils.isLogin()) {
                    Intent i = new Intent(context, MessageActivity.class);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.base_topview_right1_tv:
                if (onEdit) righttxt1.setText("编辑");
                else righttxt1.setText("完成");
                onEdit = !onEdit;
                setAllOpened(getListEntities(), false);
                notifyData();
                break;
            case R.id.fragment_cart_allselect:
                setAllSelect(getListEntities(), !hasAllSelect(listEntities));
                notifyData();
                break;
            case R.id.reload:
                swipeRefreshLayout.setRefreshing(true);
                getData();
                break;
            case R.id.fragment_cart_submit:

                if (modelEntities.size() == 0) {
                    Utility.showToast(context, "还没选择要结算的商品哦～");
                    return;
                }

                for (CartModel.ListEntity.ModelEntity modelEntity : modelEntities) {
                    ProductId += modelEntity.getProductId() + ",";
                    productNumber += modelEntity.getAmount() + ",";
                    productpara += modelEntity.getParaid() + ",";
                }
                ProductId = MyUtils.subStringEnd(ProductId, ",");
                productNumber = MyUtils.subStringEnd(productNumber, ",");
                productpara = MyUtils.subStringEnd(productpara, ",");


                MyUtils.commonBUY2((Activity) context, ProductId, "", productpara, "", 11);

                break;
            case R.id.fragment_cart_del:
                if (modelEntities.size() == 0) {
                    Utility.showToast(context, "还没选择要删除的商品哦～");
                    return;
                }


                for (CartModel.ListEntity.ModelEntity modelEntity : modelEntities) {
                    txt += modelEntity.getMainTitle() + " x" + modelEntity.getAmount() + "\n";
                    ProductId += modelEntity.getProductId() + ",";
                    productpara += modelEntity.getParaid() + ",";
                }
                ProductId = MyUtils.subStringEnd(ProductId, ",");
                productpara = MyUtils.subStringEnd(productpara, ",");

                setAndShowDelDialog(txt, ProductId, productpara);
                break;
        }

    }


    private void getData() {
        if (MyUtils.isLogin())
            utilNetwork.getProductCartModel(
                    new OkHttpClientManager.Param[]{});
        else {
            swipeRefreshLayout.setRefreshing(false);
            showToast("请去登录");
        }
    }


    //设置并显示删除时弹出的dialog
    private void setAndShowDelDialog(String txt, final String productid, final String para) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否删除？\n" + txt);
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                swipeRefreshLayout.setRefreshing(true);
                utilNetwork.getShoppingDModel(
                        new OkHttpClientManager.Param[]{
                                new OkHttpClientManager.Param("productid", productid),
                                new OkHttpClientManager.Param("productpara", para)});

            }

        });
        builder.setNegativeButton("点错了", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        //builder.setCancelable(false);
        builder.create().show();

    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);

        Log.i("TAG", "status_code======" + status_code);
        Log.i("TAG", "network_code======" + network_code);

        switch (network_code) {
            case Constant.PRODUCTCART_ID:
                swipeRefreshLayout.setRefreshing(false);
                if (status_code == 0) {
                    loadfail.setVisibility(View.GONE);
                    righttxt1.setOnClickListener(this);
                    commonRefreshAdapter.setNewData(((CartModel) model).getList());
                    notifyData();
                    if (getListEntities().size() == 0) {
                        havenot_hint.setVisibility(View.VISIBLE);
                        havenot_hint.setText("(ㄒoㄒ)...\n购物车空空如也，\n剁手党在呼唤你～");
                    } else {
                        havenot_hint.setVisibility(View.GONE);
                    }
                } else {
                    righttxt1.setOnClickListener(null);
                    commonRefreshAdapter.setNewData(new ArrayList<CartModel.ListEntity>());
                    havenot_hint.setVisibility(View.GONE);
                    loadfail.setVisibility(View.VISIBLE);
                }
                break;
            case Constant.SHOPNUMBER_ID:
                if (status_code == 0) {
                    ShopNumberModel shopNumberModel = (ShopNumberModel) model;

                    for (CartModel.ListEntity listEntity : getListEntities())
                        for (CartModel.ListEntity.ModelEntity modelEntity : listEntity.getModel())
                            if (modelEntity.getProductId() == shopNumberModel.getProductid()
                                    && modelEntity.getParaid() == shopNumberModel.getPara()) {
                                modelEntity.setAmount(shopNumberModel.getAmount());
                                break;
                            }
                    notifyData();

                } else {
                    showToast(model.getErrmsg());
                }
                break;
            case Constant.SHOPPINGD_ID:
                swipeRefreshLayout.setRefreshing(false);
                if (status_code == 0) {

                    ShoppingDModel shoppingDModel = (ShoppingDModel) model;

                    String[] productids = shoppingDModel.getProductid().split(",");
                    String[] proparas = shoppingDModel.getPropara().split(",");

                    for (CartModel.ListEntity listEntity : getListEntities()) {
                        ArrayList<CartModel.ListEntity.ModelEntity> modelEntities = new ArrayList<>();

                        for (CartModel.ListEntity.ModelEntity modelEntity : listEntity.getModel()) {
                            for (int i = 0; i < productids.length; i++) {
                                if ((modelEntity.getProductId() + "").equals(productids[i]) && modelEntity.getParaid
                                        ().equals(proparas[i]))
                                    modelEntities.add(modelEntity);

                            }
                        }

                        listEntity.getModel().removeAll(modelEntities);
                    }
                    notifyData();

                } else {
                    showToast(model.getErrmsg());
                }
                break;

        }
    }


    private void notifyData() {
        List<CartModel.ListEntity> listEntities = getListEntities();

        //判断该店家下是否还有商品，如无则删除该商家
        for (CartModel.ListEntity listEntity : listEntities)
            if (listEntity.getModel().size() == 0) listEntities.remove(listEntity);

        commonRefreshAdapter.notifyDataSetChanged();

        if (!MyUtils.isLogin()) {
            havenot_hint.setVisibility(View.VISIBLE);
            havenot_hint.setText("(ㄒoㄒ)...\n您还没有登录，\n无法查看购物车内容～");
        } else if (listEntities.size() == 0) {
            havenot_hint.setVisibility(View.VISIBLE);
            havenot_hint.setText("(ㄒoㄒ)...\n购物车空空如也，\n剁手党在呼唤你～");
        }

        //设置全选按钮
        if (hasAllSelect(listEntities)) allselect.setImageResource(R.drawable.selected);
        else allselect.setImageResource(R.drawable.unselected);

        //设置合计价格，及选中商品数量
        double allprice = 0;
        int allselect = 0;
        for (CartModel.ListEntity.ModelEntity modelEntity : getAllSelectModel(listEntities)) {
            allprice += Double.parseDouble(modelEntity.getOriginalprice().replace("￥", "")) * modelEntity.getAmount();
            allselect++;
        }
        price_tv.setText(allprice + "");
        submit.setText("结算  (" + allselect + ")");


        //设置显示的view，价格layout，结算&删除layout
        if (onEdit) {
            submit.setVisibility(View.INVISIBLE);
            del.setVisibility(View.VISIBLE);
            price_layout.setVisibility(View.INVISIBLE);
        } else {
            submit.setVisibility(View.VISIBLE);
            del.setVisibility(View.INVISIBLE);
            price_layout.setVisibility(View.VISIBLE);
        }
    }

    //获取已使用的数据
    private List<CartModel.ListEntity> getListEntities() {
        return commonRefreshAdapter.getData();
    }


    //是否全部选择
    private boolean hasAllSelect(List<CartModel.ListEntity> listEntities) {
        if (listEntities.size() == 0) return false;

        for (CartModel.ListEntity listEntity : listEntities)
            if (!listEntity.hasAllChecked()) return false;

        return true;
    }

    //设置全选与否
    private void setAllSelect(List<CartModel.ListEntity> listEntities, boolean isSelect) {
        for (CartModel.ListEntity listEntity : listEntities) {
            listEntity.setAllChecked(isSelect);
        }
    }

    //获取全部选择的商品
    private List<CartModel.ListEntity.ModelEntity> getAllSelectModel(List<CartModel.ListEntity> listEntities) {
        List<CartModel.ListEntity.ModelEntity> modelEntities = new ArrayList<>();
        for (CartModel.ListEntity listEntity : listEntities) {
            for (CartModel.ListEntity.ModelEntity modelEntity : listEntity.getModel()) {
                if (modelEntity.hasChecked()) modelEntities.add(modelEntity);
            }
        }
        return modelEntities;
    }

    //设置全部产品侧滑状态
    private void setAllOpened(List<CartModel.ListEntity> listEntities, boolean isOpened) {
        for (CartModel.ListEntity listEntity : listEntities) {
            listEntity.setAllOpened(isOpened);
        }
    }


}
