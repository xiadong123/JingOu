package com.jo.jingou.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.activity.MainActivity;
import com.jo.jingou.activity.MessageActivity;
import com.jo.jingou.activity.ProductDetailActivity;
import com.jo.jingou.activity.ProductListActivity;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.adapter.ViewPagerAdapter;
import com.jo.jingou.base.MyBaseFragment;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.ProductTypeModel;
import com.jo.jingou.model.ProductTypeSonModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_MallFragment_Data;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2017/4/1.
 */
public class MallFragment extends MyBaseFragment {


    View alltype;
    TabLayout tabLayout;
    ViewPager viewpager;

    List<ProductPager> productPagers = new ArrayList<>();

    @Override
    public void initData() {
        layoutId = R.layout.fragment_mall;
    }

    @Override
    public void findViews() {

        findTopViews();
        centertxt.setVisibility(View.VISIBLE);
        rightimg.setVisibility(View.VISIBLE);
        centertxt.setText("商城");

        alltype = rootView.findViewById(R.id.fragment_mall_alltype);
        tabLayout = (TabLayout) rootView.findViewById(R.id.fragment_mall_tablayout);
        viewpager = (ViewPager) rootView.findViewById(R.id.fragment_mall_viewpager);
    }

    @Override
    public void initListener() {
        rightview.setOnClickListener(this);
        alltype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).showMallTypeLayout();
            }
        });
    }

    @Override
    public void setupViews() {
        Util_MallFragment_Data.setListener_mallFragment_data(new Util_MallFragment_Data.Listener_MallFragment_Data() {
            @Override
            public void onProductTypeSuccess(ProductTypeModel productTypeModel) {
                setPager(productTypeModel);
            }
        });

        if (Util_MallFragment_Data.getProductTypeModel() != null) {
            setPager(Util_MallFragment_Data.getProductTypeModel());
        }

        setMessageImage();
    }


    @Override
    public void upData() {
        setMessageImage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_topview_right:
                if (MyUtils.isLogin()) {
                    Intent i = new Intent(context, MessageActivity.class);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
        }
    }

    private void setPager(ProductTypeModel productTypeModel) {
        ((MainActivity) context).setGridLayout(productTypeModel);

        List<View> pager_views = new ArrayList<>();
        List<String> pager_titles = new ArrayList<>();
        List<ProductTypeModel.ListEntity> s = productTypeModel.getList();
        productPagers.clear();

        if (s.size() != 0)
            for (int i = 0; i < s.size(); i++) {
                ProductPager productPager = new ProductPager(i, s.get(i).getProductTypeId());

//            List<List<String>> pager_titles = new ArrayList<>();
//            pager_titles.add(new ArrayList<String>());
//            pager_titles.add(new ArrayList<String>());
//            pager_titles.add(new ArrayList<String>());
//            productPager.addData(pager_titles);

                pager_views.add(productPager.getPager_view());
                pager_titles.add(s.get(i).getTypeName());
                productPagers.add(productPager);
            }
        viewpager.setAdapter(new ViewPagerAdapter(pager_views, pager_titles));
        tabLayout.setupWithViewPager(viewpager);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (0 == productPagers.get(position).pager_commonRefreshAdapter.getItemCount()) {
                    productPagers.get(position).onRefresh();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getProductTypeSon(s.get(0).getProductTypeId() + "", "1");

    }

    private void getProductTypeSon(String producttypeid, String pageIndex) {
        utilNetwork.getProductListSonModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("ProductTypeId", producttypeid),
                        new OkHttpClientManager.Param("pageIndex", pageIndex)});
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        switch (network_code) {
            case Constant.PRODUCTTYPESON_ID:
                if (status_code == 0) {
                    ProductTypeSonModel productTypeSonModel = (ProductTypeSonModel) model;

                    for (ProductPager productPager : productPagers) {
                        if (productTypeSonModel.getTypeid() == productPager.type) {

                            if (productTypeSonModel.getPage() == 1) {
                                productPager.refreshData(productTypeSonModel.getList());
                            } else {
                                productPager.addData(productTypeSonModel.getList());
                            }
                            break;
                        }
                    }
                } else {

                }
                break;
        }
    }


    public void onSearchMallType(int i) {
        viewpager.setCurrentItem(i);
    }

    class ProductPager {
        int pageIndex;//页码
        int type;//类别
        View pager_view;
        SwipeRefreshLayout pager_swiperefreshlayout;
        RecyclerView pager_recyclerview;
        CommonRefreshAdapter<ProductTypeSonModel.ListEntity> pager_commonRefreshAdapter;
        List<ProductTypeSonModel.ListEntity> pager_entitys = new ArrayList<>();


        public ProductPager(int pageIndex, final int type) {
            this.pageIndex = pageIndex;
            this.type = type;
            pager_view = inflater.inflate(R.layout.item_mallfragment_pager, null);
            pager_swiperefreshlayout = (SwipeRefreshLayout) pager_view.findViewById(R.id.swiperefreshlayout);
            pager_recyclerview = (RecyclerView) pager_view.findViewById(R.id.recyclerview);

            pager_commonRefreshAdapter =
                    new CommonRefreshAdapter<ProductTypeSonModel.ListEntity>(context, pager_entitys, R.layout.item_mallfragment_productlist, false) {
                        @Override
                        protected void convert(ViewHolder holder, final ProductTypeSonModel.ListEntity data) {
                            holder.setText(R.id.title, data.getMainTitles());
                            holder.setOnClickListener(R.id.all, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(context, ProductListActivity.class);
                                    i.putExtra("title", data.getMainTitles() + "");
                                    i.putExtra("ProductTypeId", data.getModel().get(0).getProductTypeId() + "");
                                    startActivity(i);
                                }
                            });
                            List<ProductTypeSonModel.ListEntity.ModelEntity> modelEntities = data.getModel();
                            setProductHorizontalList(holder, modelEntities);
                        }
                    };
            pager_commonRefreshAdapter.setLoadingView(R.layout.item_recyclerview_loadingview);
            pager_commonRefreshAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(boolean isReload) {
                    ProductPager.this.onLoadMore();
                }
            });

            pager_recyclerview.setLayoutManager(new LinearLayoutManager(context));
            pager_recyclerview.setAdapter(pager_commonRefreshAdapter);
            pager_recyclerview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });

            pager_swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    ProductPager.this.onRefresh();
                }
            });
        }


        public void setProductHorizontalList(ViewHolder holder, List<ProductTypeSonModel.ListEntity.ModelEntity> data) {

            RecyclerView recyclerView = holder.getView(R.id.recyclerview);

            CommonRefreshAdapter<ProductTypeSonModel.ListEntity.ModelEntity> commonRefreshAdapter
                    = new CommonRefreshAdapter<ProductTypeSonModel.ListEntity.ModelEntity>(context, data, R.layout.item_mallfragment_productitem, false) {
                @Override
                protected void convert(ViewHolder holder, ProductTypeSonModel.ListEntity.ModelEntity data) {
                    Glide.with(context)
                            .load(Constant.SERVER_HOST + data.getListImgUrl())
                            .into((ImageView) holder.getView(R.id.item_mallfragment_productitem_iv));
                    holder.setText(R.id.item_mallfragment_productitem_title, data.getMainTitle());
                    holder.setText(R.id.item_mallfragment_productitem_price, "￥" + data.getOriginalprice());
                }
            };
            commonRefreshAdapter.setOnItemClickListener(new OnItemClickListener<ProductTypeSonModel.ListEntity.ModelEntity>() {
                @Override
                public void onItemClick(ViewHolder viewHolder, ProductTypeSonModel.ListEntity.ModelEntity data, int position) {
                    Intent i = new Intent(context, ProductDetailActivity.class);
                    i.putExtra("ProductId", data.getProductId() + "");
                    startActivity(i);
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(commonRefreshAdapter);
        }


        public void onRefresh() {
            pager_swiperefreshlayout.setRefreshing(true);
            pageIndex = 1;
            getProductTypeSon(type + "", pageIndex + "");
        }

        public void onLoadMore() {
            pageIndex++;
            getProductTypeSon(type + "", pageIndex + "");
        }

        public void addData(List<ProductTypeSonModel.ListEntity> pager_entitys) {
            pager_swiperefreshlayout.setRefreshing(false);
            this.pager_entitys.addAll(pager_entitys);
            pager_commonRefreshAdapter.setLoadMoreData(pager_entitys);

            if (pager_entitys.size() == 0) {
                pager_commonRefreshAdapter.setLoadEndView(R.layout.item_recyclerview_loadingendview);
            }
        }

        public void refreshData(List<ProductTypeSonModel.ListEntity> pager_entitys) {
            pager_swiperefreshlayout.setRefreshing(false);
            this.pager_entitys.clear();
            this.pager_entitys.addAll(pager_entitys);
            pager_commonRefreshAdapter.setNewData(pager_entitys);
        }

        public int getType() {
            return type;
        }

        public View getPager_view() {
            return pager_view;
        }
    }


}
