package com.jo.jingou.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.R;
import com.jo.jingou.adapter.AutomaticRollAdapter;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.adapter.ViewPagerAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.ProductCommentsModel;
import com.jo.jingou.model.ProductDetailModel;
import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.utils.BannerView;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_PopupWindow;
import com.jo.jingou.view.tag.Tag;
import com.jo.jingou.view.tag.TagListView;
import com.jo.jingou.view.tag.TagView;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;
import caesar.feng.framework.utils.Utility;

/**
 * Created by dfyu on 2017/4/1.
 */
public class AngelDetailActivity extends MyBaseActivity {

    //
    Toolbar toolbar;
    ImageView back, mall, massage;

    ViewPager viewPager0;

    LinearLayout viewPager0_circle_layout;

    ArrayList<BannerView> bannerViews0 = new ArrayList<>();

    TextView tv_title, tv_price, tv_sold;


    ViewPager.OnPageChangeListener onPageChangeListener0 = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            int p = position % bannerViews0.size();
            for (BannerView bannerView : bannerViews0)
                bannerView.getmBannerCircle().setImageResource(R.drawable.banner_circle_3);
            bannerViews0.get(p).getmBannerCircle().setImageResource(R.drawable.banner_circle_2);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    LinearLayout ll1;

    //
    TabLayout tabLayout;
    ViewPager viewPager1;
    View pager1_0, pager1_1;
    RecyclerView recyclerView0, recyclerView1;
    CommonRefreshAdapter<String> commonRefreshAdapter_web;
    CommonRefreshAdapter<ProductCommentsModel.ListEntity> commonRefreshAdapter;


    //
    FloatingActionButton floatingActionButton;

    //
    View submit0, submit1;

    //
    String ProductId;
    int pageIndex = 1;

    PopupWindow pop_product;
    EditText pop_number_ed;
    View.OnClickListener pop_onclicklistener;
    List<TagListView> popupWindow_TagListView_list = new ArrayList<>();
    int productNumber = 1;

    List<ProductCommentsModel.ListEntity> listEntities = new ArrayList<>();
    int dp500 = 0;

    @Override
    public void initData() {
        layoutId = R.layout.activity_angeldetail;
    }

    @Override
    public void findViews() {
        toolbar = (Toolbar) findViewById(R.id.activity_productdetail_toolbar);
        back = findViewAndSetClick(R.id.activity_productdetail_back);
        mall = findViewAndSetClick(R.id.activity_productdetail_mall);
        massage = findViewAndSetClick(R.id.activity_productdetail_massage);

        viewPager0 = (ViewPager) findViewById(R.id.activity_productdetail_viewpager0);
        viewPager0_circle_layout = (LinearLayout) findViewById(R.id.activity_productdetail_indexlayout0);

        tv_title = (TextView) findViewById(R.id.activity_productdetail_title);
        tv_price = (TextView) findViewById(R.id.activity_productdetail_price);
        tv_sold = (TextView) findViewById(R.id.activity_productdetail_sold);

        ll1 = findViewAndSetClick(R.id.ll1);

        tabLayout = (TabLayout) findViewById(R.id.activity_productdetail_tablayout);
        viewPager1 = (ViewPager) findViewById(R.id.activity_productdetail_viewpager1);

        pager1_0 = getLayoutInflater().inflate(R.layout.item_productdetail_reply_recyclerview_web, null);
        pager1_1 = getLayoutInflater().inflate(R.layout.item_productdetail_reply_recyclerview, null);
        recyclerView0 = (RecyclerView) pager1_0.findViewById(R.id.recyclerview);
        recyclerView1 = (RecyclerView) pager1_1.findViewById(R.id.recyclerview);

        List<String> testdata_web = new ArrayList<>();
        testdata_web.add("AAA");
        commonRefreshAdapter_web = new CommonRefreshAdapter<String>(this, testdata_web, R.layout.item_productdetail_reply_webview, false) {
            @Override
            protected void convert(ViewHolder holder, String data) {
                WebView webView = (WebView) holder.getConvertView();
                webView.loadUrl(Constant.SERVER_HOST + "/home/Info?id=" + ProductId);
            }
        };


        commonRefreshAdapter = new CommonRefreshAdapter<ProductCommentsModel.ListEntity>(this, listEntities, R.layout.item_productdetail_reply_item, true) {
            @Override
            protected void convert(ViewHolder holder, ProductCommentsModel.ListEntity data) {
                holder.setText(R.id.item_productdetail_reply_remark_name, data.getName());
                holder.setText(R.id.item_productdetail_reply_remark_content, data.getCommentcontent());
                holder.setText(R.id.item_productdetail_reply_time_tv, data.getModifydate());

                if (MyUtils.hasValue(data.getReply())) {
                    holder.getView(R.id.item_productdetail_reply_businessreply_ll).setVisibility(View.VISIBLE);
                    holder.setText(R.id.item_productdetail_reply_businessreply_tv, "商家回复：" + data.getReply());
                } else {
                    holder.getView(R.id.item_productdetail_reply_businessreply_ll).setVisibility(View.GONE);
                }
            }
        };


        commonRefreshAdapter.setLoadingView(R.layout.item_recyclerview_loadingview);

        commonRefreshAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                pageIndex++;
                //getProductCommentsData();
            }
        });

        floatingActionButton = findViewAndSetClick(R.id.activity_productdetail_floatingactionbutton);

        submit0 = findViewAndSetClick(R.id.activity_productdetail_submit0);
        submit1 = findViewAndSetClick(R.id.activity_productdetail_submit1);

    }

    @Override
    public void initListener() {
    }

    @Override
    public void setupViews() {
        setTopView();
        dp500 = Utility.dip2px(AngelDetailActivity.this, 500);
        ProductId = getIntent().getStringExtra("ProductId");

        setViewpagerAndRecyclerView();
        showLoadingDialog();
        getData();
        //getProductCommentsData();

        floatingActionButton.hide();
        setMessageImage();
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent i;
        switch (view.getId()) {
            case R.id.activity_productdetail_back:
                finish();
                break;
            case R.id.activity_productdetail_mall:
                i = new Intent(this, MainActivity.class);
                i.putExtra("forcart", true);
                startActivity(i);
                break;
            case R.id.activity_productdetail_massage:
                i = new Intent(this, MessageActivity.class);
                startActivity(i);
                break;
            case R.id.ll1:
                Util_PopupWindow.show(pop_product, this);
                break;
            case R.id.activity_productdetail_floatingactionbutton:
                if (p == 0) {
                    recyclerView0.smoothScrollToPosition(0);
                } else if (p == 1) {
                    recyclerView1.smoothScrollToPosition(0);
                }
                break;
            case R.id.activity_productdetail_submit0:
                Util_PopupWindow.show(pop_product, this);
                break;
            case R.id.activity_productdetail_submit1:
                Util_PopupWindow.show(pop_product, this);
                break;

        }
    }

    private void setTopView() {

    }

    boolean first = true;

    private void setViewPager0(List<ProductDetailModel.PhotolistEntity> bannerlist) {
        ArrayList<View> views = new ArrayList<>();
        bannerViews0.clear();
        viewPager0_circle_layout.removeAllViews();

        if (bannerlist.size() > 1) {

            for (ProductDetailModel.PhotolistEntity entity : bannerlist) {
                View banner = getLayoutInflater().inflate(R.layout.item_banner_3, null);
                ImageView banner_iv = (ImageView) banner.findViewById(R.id.item_banner_3_iv);
                Glide.with(this).load(Constant.SERVER_HOST + entity.getUrl()).into(banner_iv);


                View circle = getLayoutInflater().inflate(R.layout.item_banner_circle, null);
                ImageView circle_iv = (ImageView) circle.findViewById(R.id.item_banner_circle_iv);
                if (first) {
                    circle_iv.setImageResource(R.drawable.banner_circle_2);
                    first = false;
                } else {
                    circle_iv.setImageResource(R.drawable.banner_circle_3);
                }

                views.add(banner);
                bannerViews0.add(new BannerView(banner, banner_iv, circle_iv));
                viewPager0_circle_layout.addView(circle);
            }
            viewPager0.setAdapter(new AutomaticRollAdapter(views));

            viewPager0.addOnPageChangeListener(onPageChangeListener0);
        } else {
            View banner = getLayoutInflater().inflate(R.layout.item_banner_3, null);
            ImageView banner_iv = (ImageView) banner.findViewById(R.id.item_banner_3_iv);
            Glide.with(this).load(Constant.SERVER_HOST + bannerlist.get(0).getUrl()).into(banner_iv);

//            View circle = getLayoutInflater().inflate(R.layout.item_banner_circle, null);
//            ImageView circle_iv = (ImageView) circle.findViewById(R.id.item_banner_circle_iv);
//            circle_iv.setImageResource(R.drawable.banner_circle_3);

            views.add(banner);
            //bannerViews0.add(new BannerView(banner, banner_iv, circle_iv));
            //viewPager0_circle_layout.addView(circle);

            viewPager0.setAdapter(new ViewPagerAdapter(views));


        }
    }

    int p = 0;
    int dy0 = 0, dy1 = 0;

    private void setViewpagerAndRecyclerView() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        Log.i("TAG", "typedValue.string====" + typedValue.string);
        Log.i("TAG", "typedValue.coerceToString()====" + typedValue.coerceToString());
        Log.i("TAG", "typedValue.resourceId====" + typedValue.resourceId);
        Log.i("TAG", "typedValue.data====" + typedValue.data);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewPager1.getLayoutParams();
        layoutParams.height =
                Utility.getScreenHeight(this) -
                        Utility.dip2px(this, 48) -
                        Utility.dip2px(this, 42) -
                        Utility.dip2px(this, Float.parseFloat((typedValue.coerceToString() + "").replace("dip", ""))) -
                        MyUtils.getStatusBarHeight(this);
        Log.i("TAG", "layoutParams.height====" + layoutParams.height);
        Log.i("TAG", "Utility.getScreenHeight(this)====" + Utility.getScreenHeight(this));
        Log.i("TAG", "toolbar.getHeight()====" + toolbar.getHeight());
        Log.i("TAG", "MyUtils.getStatusBarHeight(this)====" + MyUtils.getStatusBarHeight(this));

        viewPager1.setLayoutParams(layoutParams);

        recyclerView0.setLayoutManager(new LinearLayoutManager(this));
        recyclerView0.setAdapter(commonRefreshAdapter_web);
        recyclerView0.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                dy0 += dy;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (dy0 > dp500) floatingActionButton.show();
                    else if (dy0 < dp500) floatingActionButton.hide();
                }
            }
        });


        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(commonRefreshAdapter);
        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                dy1 += dy;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (dy1 > dp500) floatingActionButton.show();
                    else if (dy1 < dp500) floatingActionButton.hide();
                }
            }
        });


        List<View> views = new ArrayList<>();
        views.add(pager1_0);
        //views.add(pager1_1);

        List<String> pagerTitle = new ArrayList<>();
        pagerTitle.add("商品详情");
        // pagerTitle.add("评价 (365)");

        viewPager1.setAdapter(new ViewPagerAdapter(views, pagerTitle));
        tabLayout.setupWithViewPager(viewPager1, false);
        MyUtils.setIndicator(this, tabLayout, Utility.getScreenWidth(this) / 7, Utility.getScreenWidth(this) / 7);

        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                p = position;

                if (p == 0) {
                    if (dy0 > dp500) floatingActionButton.show();
                    else if (dy0 < dp500) floatingActionButton.hide();
                } else if (p == 1) {
                    if (dy1 > dp500) floatingActionButton.show();
                    else if (dy1 < dp500) floatingActionButton.hide();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void setPopupWindow(ProductDetailModel productDetailModel) {
        Util_PopupWindow.SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(this, R.layout.popupwindow_product_buy, null, null);

        View contentView = setPopModel.getContentView();


        pop_number_ed = (EditText) contentView.findViewById(R.id.number);

        pop_onclicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.dismiss:
                        pop_product.dismiss();
                        break;
                    case R.id.minus:
                        productNumber = Integer.parseInt(pop_number_ed.getText().toString());
                        if (productNumber - 1 < 1) {
                            Utility.showToast(AngelDetailActivity.this, "不能再减了哦～");
                        } else {
                            productNumber--;
                            pop_number_ed.setText(productNumber + "");
                        }
                        break;
                    case R.id.plus:
                        productNumber = Integer.parseInt(pop_number_ed.getText().toString());
                        productNumber++;
                        pop_number_ed.setText(productNumber + "");
                        break;
                    case R.id.submit:
                        for (TagListView tagListView : popupWindow_TagListView_list) {
                            if (tagListView.getHasCheckedTag() == null) {
                                showToast("请选择 " + tagListView.getTag());

                                tagListView.setFocusable(true);
                                tagListView.setFocusableInTouchMode(true);
                                tagListView.requestFocus();
                                return;
                            }
                        }


                        if (MyUtils.isLogin()) {
                            if (MyUtils.CanBuyAngle() == 0) {
                                // 购买操作
                                MyUtils.commonBUY(AngelDetailActivity.this, ProductId, productNumber + "", "", "", 11);
                            } else if (!MyUtils.isFatherCode() && !MyUtils.isIdCode()) {
                                toBuyAngel(ImproveInformationActivity.class, "All");
                            } else if (!MyUtils.isIdCode()) {
                                toBuyAngel(ImproveInformationActivity.class, "isIdCode");
                            } else if (!MyUtils.isFatherCode()) {
                                toBuyAngel(ImproveInformationActivity.class, "isFatherCode");
                            }
                        } else {
                            toBuyAngel(Login_Register_ResetPasswordActivity.class, "bylogin");
                        }

                        break;
                }
            }
        };

        Glide.with(this).load(Constant.SERVER_HOST + productDetailModel.getList().get(0).getListImgUrl()).into((ImageView) contentView.findViewById(R.id.pic));
        ((TextView) contentView.findViewById(R.id.title)).setText(productDetailModel.getList().get(0).getMainTitle());
        ((TextView) contentView.findViewById(R.id.price)).setText("￥" + productDetailModel.getList().get(0).getOriginalprice());

        contentView.findViewById(R.id.dismiss).setOnClickListener(pop_onclicklistener);
        contentView.findViewById(R.id.minus).setOnClickListener(pop_onclicklistener);
        contentView.findViewById(R.id.plus).setOnClickListener(pop_onclicklistener);
        contentView.findViewById(R.id.submit).setOnClickListener(pop_onclicklistener);


        LinearLayout linearlayout = (LinearLayout) contentView.findViewById(R.id.linearlayout);
        List<ProductDetailModel.List2Entity> list2Entities = productDetailModel.getList2();
        for (ProductDetailModel.List2Entity list2Entity : list2Entities) {
            View v = getLayoutInflater().inflate(R.layout.item_popupwindow_product_buy, null);
            ((TextView) v.findViewById(R.id.title_tv)).setText(list2Entity.getName());

            List<Tag> tags = new ArrayList<>();
            for (ProductDetailModel.List2Entity.ModelEntity modelEntity : list2Entity.getModel()) {
                Tag tag = new Tag(modelEntity.getProductparameterId(), modelEntity.getProductparameterName());
                tags.add(tag);
            }

            final TagListView tagListView = (TagListView) v.findViewById(R.id.taglistview);
            tagListView.setTag(list2Entity.getName());
            tagListView.setTags(tags, true);

            tagListView.setOnTagCheckedChangedListener(new TagListView.OnTagCheckedChangedListener() {
                @Override
                public void onTagCheckedChanged(TagView tagView, Tag tag) {

                    if (tag.isChecked() == true) {
                        for (Tag tag1 : tagListView.getTags()) {
                            if (tag1 != tag)
                                ((TagView) tagListView.findViewWithTag(tag1)).setChecked(false);
                        }
                    }
                }
            });
            popupWindow_TagListView_list.add(tagListView);
            linearlayout.addView(v);
        }

        pop_product = setPopModel.getPopupWindow();
        pop_product.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    private void toBuyAngel(Class<?> c, String type) {
        Intent i = new Intent(this, c);
        i.putExtra(Constant.BYTYPE, type);
        i.putExtra("toBuy", 1);
        i.putExtra("productid", ProductId);
        i.putExtra("much", "1");
        startActivity(i);
    }

    public void getData() {
        utilNetwork.getProductDetailModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("ProductId", ProductId + "")});
    }

    public void getProductCommentsData() {
        utilNetwork.getProductCommentsModel(
                new OkHttpClientManager.Param[]{
//                        new OkHttpClientManager.Param("ProductId", ProductId + ""),
//                        new OkHttpClientManager.Param("pageIndex", pageIndex + "")
                        new OkHttpClientManager.Param("ProductId", ProductId + ""),
                        new OkHttpClientManager.Param("pageIndex", pageIndex + "")
                });

    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.PRODUCTDETAIL_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    ProductDetailModel productDetailModel = (ProductDetailModel) model;
                    setPopupWindow(productDetailModel);

                    ProductDetailModel.ListEntity listEntity = productDetailModel.getList().get(0);
                    List<ProductDetailModel.PhotolistEntity> photolistEntities = productDetailModel.getPhotolist();

                    tv_title.setText(listEntity.getMainTitle());
                    tv_price.setText("￥" + listEntity.getOriginalprice());
                    tv_sold.setText("已售" + listEntity.getSalesVolume() + "件");

                    setViewPager0(photolistEntities);

                    List<String> urls = new ArrayList<>();
                    urls.add(listEntity.getContents());
                    commonRefreshAdapter_web.setNewData(urls);

                } else {
                    finish();
                }
                break;
            case Constant.PRODUCTCOMMENTS_ID:
                if (status_code == 0) {
                    ProductCommentsModel productCommentsModel = (ProductCommentsModel) model;
                    //listEntities.addAll(productCommentsModel.getList());
                    commonRefreshAdapter.notifyDataSetChanged();
                    commonRefreshAdapter.setLoadMoreData(productCommentsModel.getList());

                    if (productCommentsModel.getList().size() == 0) {
                        commonRefreshAdapter.setLoadEndView(R.layout.item_recyclerview_loadingendview);
                    }
                } else {
                    commonRefreshAdapter.setLoadFailedView(R.layout.item_recyclerview_loadingfailedview);

                }
                break;
        }
    }

    public void setMessageImage() {
        if (MyUtils.isLogin()) {
            if (MessageLoader.getMessageNumberAll() == 0) {
                massage.setImageResource(R.drawable.message_gw1);
            } else {
                massage.setImageResource(R.drawable.message_gw);
            }
        } else {
            massage.setImageResource(R.drawable.message_gw);
        }
    }

}
