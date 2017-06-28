package com.jo.jingou.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.SelectListModel;
import com.jo.jingou.model.SelectModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;
import caesar.feng.framework.utils.Utility;

/**
 * Created by dfyu on 2017/4/10.
 */
public class ProductListActivity extends MyBaseActivity {


    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    CommonRefreshAdapter<SelectListModel.ListEntity> commonRefreshAdapter;


    View search0;
    TextView search0_tv;

    View search1;
    TextView search1_tv;
    ImageView search1_up, search1_down;

    View search2;
    TextView search2_tv;
    ImageView search2_up, search2_down;

    View search3;


    View loadfail;
    View reload;
    View hint;


    DrawerLayout drawerLayout;
    LinearLayout criteria;
    EditText criteria_price1, criteria_price2;
    View criteria_son;
    TextView criteria_son_title, criteria_son_submit;
    RecyclerView criteria_son_recyclerview;
    LinearLayout criteria_son_linearlayout;
    Animation criteria_son_show, criteria_son_dissmiss;

    View criteria_reset, criteria_submit;

    View drawer_load, drawer_loading, drawer_loadfail;
    View drawer_reload;


    String title, ProductTypeId;
    int pageIndex = 1;
    int sortBy = 0;
    String canShu = "";
    String price1 = "", price2 = "";

    List<SelectListModel.ListEntity> listEntities = new ArrayList<>();
    SelectModel selectModel = new SelectModel();


    @Override
    public void initData() {
        layoutId = R.layout.activity_productlist;
    }

    @Override
    public void findViews() {
        search0 = findViewAndSetClick(R.id.activity_productlist_search0);
        search0_tv = (TextView) findViewById(R.id.activity_productlist_search0_tv);

        search1 = findViewAndSetClick(R.id.activity_productlist_search1);
        search1_tv = (TextView) findViewById(R.id.activity_productlist_search1_tv);
        search1_up = (ImageView) findViewById(R.id.activity_productlist_search1_up);
        search1_down = (ImageView) findViewById(R.id.activity_productlist_search1_down);

        search2 = findViewAndSetClick(R.id.activity_productlist_search2);
        search2_tv = (TextView) findViewById(R.id.activity_productlist_search2_tv);
        search2_up = (ImageView) findViewById(R.id.activity_productlist_search2_up);
        search2_down = (ImageView) findViewById(R.id.activity_productlist_search2_down);

        search3 = findViewAndSetClick(R.id.activity_productlist_search3);

        loadfail = findViewById(R.id.activity_productlist_loadfail);
        reload = findViewAndSetClick(R.id.activity_productlist_reload);

        hint = findViewById(R.id.activity_productlist_havenot_hint);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        criteria = (LinearLayout) findViewById(R.id.criteria);

        criteria_price1 = (EditText) findViewById(R.id.criteria_price1);
        criteria_price2 = (EditText) findViewById(R.id.criteria_price2);

        criteria_son = findViewById(R.id.criteria_son);
        criteria_son_title = (TextView) findViewById(R.id.criteria_son_title);
        criteria_son_submit = findViewAndSetClick(R.id.criteria_son_submit);
        criteria_son_recyclerview = (RecyclerView) findViewById(R.id.criteria_son_recyclerview);
        criteria_son_linearlayout = (LinearLayout) findViewById(R.id.criteria_son_ll);


        drawer_load = findViewById(R.id.right_drawer_load);
        drawer_loading = findViewById(R.id.right_drawer_loading);
        drawer_loadfail = findViewById(R.id.right_drawer_loadfail);
        drawer_reload = findViewAndSetClick(R.id.right_drawer_reload);


        criteria_reset = findViewAndSetClick(R.id.criteria_reset);
        criteria_submit = findViewAndSetClick(R.id.criteria_submit);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        criteria_son_show = AnimationUtils.loadAnimation(this, R.anim.righttoleft_upanim_x);
        criteria_son_dissmiss = AnimationUtils.loadAnimation(this, R.anim.righttoleft_downanim_x);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        title = getIntent().getStringExtra("title");
        ProductTypeId = getIntent().getStringExtra("ProductTypeId");
        setTopview2(title);

        setSwipeRefreshLayout();
        setRecyclerView();

        swipeRefreshLayout.setRefreshing(true);
        getData();
        getSelectData();
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_productlist_search0:
                sortBy = 0;
                setSearchBar(0);
                swipeRefreshLayout.setRefreshing(true);
                pageIndex = 1;
                getData();
                break;
            case R.id.activity_productlist_search1:
                if (sortBy == 1) {
                    sortBy = 4;
                    setSearchBar(4);
                } else if (sortBy == 4) {
                    sortBy = 1;
                    setSearchBar(1);
                } else {
                    sortBy = 1;
                    setSearchBar(1);
                }
                swipeRefreshLayout.setRefreshing(true);
                pageIndex = 1;
                getData();
                break;
            case R.id.activity_productlist_search2:
                if (sortBy == 2) {
                    sortBy = 3;
                    setSearchBar(3);
                } else if (sortBy == 3) {
                    sortBy = 2;
                    setSearchBar(2);
                } else {
                    sortBy = 2;
                    setSearchBar(2);
                }
                swipeRefreshLayout.setRefreshing(true);
                pageIndex = 1;
                getData();
                break;
            case R.id.activity_productlist_search3:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.criteria_son_submit:
                dismissCriteriaSon();
                break;
            case R.id.criteria_reset:
                for (SelectModel.ListEntity listEntity : selectModel.getList()) {
                    for (SelectModel.ListEntity.ModelEntity modelEntity : listEntity.getModel()) {
                        modelEntity.isChecked = false;
                    }
                }
                criteria_price1.setText("");
                criteria_price2.setText("");

                canShu = "";
                price1 = "";
                price2 = "";


                setDrawerLayout();
                break;
            case R.id.criteria_submit:
                price1 = criteria_price1.getText().toString().trim() + "";
                price2 = criteria_price2.getText().toString().trim() + "";


                if (MyUtils.hasValue(price1) && MyUtils.hasValue(price2)) {
                    if (Integer.parseInt(price1) > Integer.parseInt(price2)) {
                        Utility.showToast(ProductListActivity.this, "亲，最低价必须小于最高价哦～");
                        return;
                    }
                }

                canShu = "";
                for (SelectModel.ListEntity listEntity : selectModel.getList()) {
                    for (SelectModel.ListEntity.ModelEntity modelEntity : listEntity.getModel()) {
                        if (modelEntity.isChecked) {
                            canShu += modelEntity.getProductparameterId() + ",";
                        }
                    }
                }
                if (canShu.endsWith(","))
                    canShu = canShu.substring(0, canShu.length() - 1);
                drawerLayout.closeDrawer(Gravity.RIGHT);
                swipeRefreshLayout.setRefreshing(true);
                getData();
                break;

            case R.id.right_drawer_reload:
                getSelectData();
                break;
            case R.id.activity_productlist_reload:
                swipeRefreshLayout.setRefreshing(true);
                getData();
                break;
        }
    }


    public void setSearchBar(int sortBy) {

        search0_tv.setTextColor(getResources().getColor(R.color.gary5));
        search1_tv.setTextColor(getResources().getColor(R.color.gary5));
        search2_tv.setTextColor(getResources().getColor(R.color.gary5));
        search1_up.setVisibility(View.INVISIBLE);
        search1_down.setVisibility(View.INVISIBLE);
        search2_up.setVisibility(View.INVISIBLE);
        search2_down.setVisibility(View.INVISIBLE);

        switch (sortBy) {
            case 0:
                search0_tv.setTextColor(getResources().getColor(R.color.red2));
                break;
            case 1:
                search1_tv.setTextColor(getResources().getColor(R.color.red2));
                search1_up.setVisibility(View.VISIBLE);
                search1_down.setVisibility(View.VISIBLE);
                search1_up.setImageResource(R.drawable.search_up_g);
                search1_down.setImageResource(R.drawable.search_down_r);
                break;
            case 2:
                search2_tv.setTextColor(getResources().getColor(R.color.red2));
                search2_up.setVisibility(View.VISIBLE);
                search2_down.setVisibility(View.VISIBLE);
                search2_up.setImageResource(R.drawable.search_up_g);
                search2_down.setImageResource(R.drawable.search_down_r);
                break;
            case 3:
                search2_tv.setTextColor(getResources().getColor(R.color.red2));
                search2_up.setVisibility(View.VISIBLE);
                search2_down.setVisibility(View.VISIBLE);
                search2_up.setImageResource(R.drawable.search_up_r);
                search2_down.setImageResource(R.drawable.search_down_g);
                break;
            case 4:
                search1_tv.setTextColor(getResources().getColor(R.color.red2));
                search1_up.setVisibility(View.VISIBLE);
                search1_down.setVisibility(View.VISIBLE);
                search1_up.setImageResource(R.drawable.search_up_r);
                search1_down.setImageResource(R.drawable.search_down_g);
                break;
        }
    }


    private void setSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                getData();
            }
        });
    }

    private void setRecyclerView() {
        final int iv_h = (Utility.getScreenWidth(this) - Utility.dip2px(this, 10)) / 2;

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        commonRefreshAdapter = new CommonRefreshAdapter<SelectListModel.ListEntity>(this, listEntities, R.layout.item_productlist_productitem, false) {
            @Override
            protected void convert(ViewHolder holder, SelectListModel.ListEntity data) {
                Log.i("TAG", "holder.getLayoutPosition()==============" + holder.getLayoutPosition());
                if (holder.getLayoutPosition() % 2 == 0) {
                    holder.getView(R.id.divider_left).setVisibility(View.GONE);
                    holder.getView(R.id.divider_right).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.divider_left).setVisibility(View.VISIBLE);
                    holder.getView(R.id.divider_right).setVisibility(View.GONE);
                }

                ImageView iv = holder.getView(R.id.item_productlist_productitem_iv);
                Glide.with(ProductListActivity.this)
                        .load(Constant.SERVER_HOST + data.getListImgUrl())
                        .into(iv);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) iv.getLayoutParams();
                layoutParams.height = iv_h;
                iv.setLayoutParams(layoutParams);

                holder.setText(R.id.item_productlist_productitem_title, data.getMainTitle());
                holder.setText(R.id.item_productlist_productitem_price, "￥" + data.getOriginalprice());

            }
        };

        commonRefreshAdapter.setOnItemClickListener(new OnItemClickListener<SelectListModel.ListEntity>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, SelectListModel.ListEntity data, int position) {
                Intent i = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                i.putExtra("ProductId", data.getProductId() + "");
                startActivity(i);
            }
        });

        recyclerView.setAdapter(commonRefreshAdapter);

    }

    private void setDrawerLayout() {

        criteria.removeViews(1, criteria.getChildCount() - 1);
        final List<SelectModel.ListEntity> listEntities = selectModel.getList();


        for (int i = 0; i < listEntities.size(); i++) {
            final int index = i;
            final SelectModel.ListEntity listEntity = listEntities.get(i);
            List<SelectModel.ListEntity.ModelEntity> modelEntities = listEntity.getModel();

            //获取view
            View v = getLayoutInflater().inflate(R.layout.item_productlist_search, null);
            //--------------------------------------

            //设置点击事件
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCriteriaSon(index);
                    showCriteriaSon();
                }
            });
            //--------------------------------------

            //设置名字
            ((TextView) v.findViewById(R.id.title)).setText(listEntity.getName());
            //--------------------------------------

            //设置已选择的文字
            String hasCheckedTxt = "";
            for (SelectModel.ListEntity.ModelEntity ModelEntity : modelEntities) {
                if (ModelEntity.isChecked)
                    hasCheckedTxt += ModelEntity.getProductparameterName() + ",";
            }
            if (hasCheckedTxt.endsWith(","))
                hasCheckedTxt = hasCheckedTxt.substring(0, hasCheckedTxt.length() - 1);

            ((TextView) v.findViewById(R.id.hasChecked)).setText(hasCheckedTxt);
            //--------------------------------------

            criteria.addView(v);

        }
    }

    private void setCriteriaSon(final int criteriaSonindex) {
        final SelectModel.ListEntity listEntity = selectModel.getList().get(criteriaSonindex);

        criteria_son_title.setText(listEntity.getName());
        criteria_son_linearlayout.removeAllViews();

        criteria_son_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        criteria_son_recyclerview.setAdapter(
                new CommonRefreshAdapter<SelectModel.ListEntity.ModelEntity>(this, listEntity.getModel(), R.layout.item_2text, false) {
                    @Override
                    protected void convert(final ViewHolder holder, final SelectModel.ListEntity.ModelEntity data) {
                        final int position = holder.getLayoutPosition();

                        if (position != 0 && data.getSortLetters().endsWith(listEntity.getModel().get(position - 1).getSortLetters())) {
                            holder.getView(R.id.item_textview_0).setVisibility(View.GONE);
                        } else {
                            holder.getView(R.id.item_textview_0).setVisibility(View.VISIBLE);

//                            View v = getLayoutInflater().inflate(R.layout.item_text, null);
//                            ((TextView) v.findViewById(R.id.text)).setText(data.getSortLetters());
//                            criteria_son_linearlayout.addView(v);
                        }


                        holder.setText(R.id.item_textview_0, data.getSortLetters());
                        holder.setText(R.id.item_textview_1, data.getProductparameterName());

                        if (data.isChecked)
                            holder.getView(R.id.item_check_iv).setVisibility(View.VISIBLE);
                        else
                            holder.getView(R.id.item_check_iv).setVisibility(View.GONE);

                        holder.getView(R.id.item_ll_1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (data.isChecked) {
                                    data.isChecked = false;
                                    holder.getView(R.id.item_check_iv).setVisibility(View.GONE);
                                } else {
                                    data.isChecked = true;
                                    holder.getView(R.id.item_check_iv).setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }
                });
    }

    private void showCriteriaSon() {
        criteria_son.startAnimation(criteria_son_show);
        criteria_son.setVisibility(View.VISIBLE);
    }

    private void dismissCriteriaSon() {
        setDrawerLayout();
        criteria_son.startAnimation(criteria_son_dissmiss);
        criteria_son.setVisibility(View.GONE);
    }


    private void setDrawLoad(int v0, int v1, int v2) {
        drawer_load.setVisibility(v0);
        drawer_loading.setVisibility(v1);
        drawer_loadfail.setVisibility(v2);
    }

    public void getData() {
        utilNetwork.getSelectListModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("ProductTypeId", ProductTypeId),
                        new OkHttpClientManager.Param("pageIndex", pageIndex + ""),
                        new OkHttpClientManager.Param("sortBy", sortBy + ""),
                        new OkHttpClientManager.Param("canShu", canShu),
                        new OkHttpClientManager.Param("price1", price1),
                        new OkHttpClientManager.Param("price2", price2)});
    }

    private void getSelectData() {
        setDrawLoad(View.VISIBLE, View.VISIBLE, View.GONE);
        utilNetwork.getSelectModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("ProductTypeId", ProductTypeId)});
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.SELECTLIST_ID:
                cancelLoadingDialog();
                swipeRefreshLayout.setRefreshing(false);
                if (status_code == 0) {
                    loadfail.setVisibility(View.GONE);

                    SelectListModel selectListModel = (SelectListModel) model;

                    if (pageIndex == 1) listEntities.clear();
                    listEntities.addAll(selectListModel.getList());

                    if (listEntities.size() == 0) {
                        hint.setVisibility(View.VISIBLE);
                    } else {
                        hint.setVisibility(View.GONE);
                    }

                    commonRefreshAdapter.notifyDataSetChanged();

                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setTitle("提示");
//                    builder.setMessage("网络状况不好哦～请点击重试！");
//                    builder.setNegativeButton("重试", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            swipeRefreshLayout.setRefreshing(true);
//                            getData();
//                        }
//                    });
//                    builder.show();

                    loadfail.setVisibility(View.VISIBLE);
                }
                break;
            case Constant.SELECT_ID:
                if (status_code == 0) {
                    setDrawLoad(View.GONE, View.GONE, View.GONE);
                    selectModel = (SelectModel) model;
                    for (int i = 0; i < selectModel.getList().size(); i++) {
                        List<SelectModel.ListEntity.ModelEntity> modelEntities = selectModel.getList().get(i).getModel();
                        selectModel.getList().get(i).setModel(MyUtils.comparatorByPinyin(modelEntities));
                    }
                    setDrawerLayout();
                } else {
                    setDrawLoad(View.VISIBLE, View.GONE, View.VISIBLE);
                }

                break;
        }
    }

}
