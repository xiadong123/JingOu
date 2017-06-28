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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
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
import com.jo.jingou.view.CircleImageView;
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
public class ProductDetailActivity extends MyBaseActivity {

    ProductDetailModel productDetailModel;
    //
    Toolbar toolbar;
    ImageView back, mall, massage;

    ViewPager viewPager0;

    LinearLayout viewPager0_circle_layout;

    ArrayList<BannerView> bannerViews0 = new ArrayList<>();

    TextView tv_title, tv_price, tv_sold;

    ViewPager.OnPageChangeListener onPageChangeListener0;

    LinearLayout ll1;

    TextView tv_productpara;

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

    int pop_type = 0;
    PopupWindow pop_product;
    EditText pop_number_ed;
    View.OnClickListener pop_onclicklistener;
    List<TagListView> popupWindow_TagListView_list = new ArrayList<>();
    int productNumber = 1;


    List<ProductCommentsModel.ListEntity> listEntities = new ArrayList<>();
    int dp500 = 0;

    @Override
    public void initData() {
        layoutId = R.layout.activity_productdetail;
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
        tv_productpara = (TextView) findViewById(R.id.productpara_tv);

        tabLayout = (TabLayout) findViewById(R.id.activity_productdetail_tablayout);
        viewPager1 = (ViewPager) findViewById(R.id.activity_productdetail_viewpager1);

        pager1_0 = getLayoutInflater().inflate(R.layout.item_productdetail_reply_recyclerview_web, null);
        pager1_1 = getLayoutInflater().inflate(R.layout.item_productdetail_reply_recyclerview, null);
        recyclerView0 = (RecyclerView) pager1_0.findViewById(R.id.recyclerview);
        recyclerView1 = (RecyclerView) pager1_1.findViewById(R.id.recyclerview);


        onPageChangeListener0 = new ViewPager.OnPageChangeListener() {
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

        List<String> testdata_web = new ArrayList<>();
        testdata_web.add("web_test_url");
        commonRefreshAdapter_web = new CommonRefreshAdapter<String>(this, testdata_web, R.layout.item_productdetail_reply_webview, false) {
            @Override
            protected void convert(ViewHolder holder, String data) {
                WebView webView = (WebView) holder.getConvertView();

                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                webView.getSettings().setLoadWithOverviewMode(true);


                webView.loadUrl(Constant.SERVER_HOST + "/home/Info?id=" + ProductId);
            }
        };


        commonRefreshAdapter = new CommonRefreshAdapter<ProductCommentsModel.ListEntity>(this, listEntities, R.layout.item_productdetail_reply_item, true) {

            @Override
            protected void convert(ViewHolder holder, final ProductCommentsModel.ListEntity data) {

                Glide.with(ProductDetailActivity.this)
                        .load(Constant.SERVER_HOST + data.getMemberPhoto())
                        .error(R.drawable.head_moren)
                        .placeholder(R.drawable.head_moren)
                        .into((CircleImageView) holder.getView(R.id.item_productdetail_reply_remark_head));

                if (data.getAnonymous() == 0) {
                    holder.setText(R.id.item_productdetail_reply_remark_name, data.getName());
                } else {
                    holder.setText(R.id.item_productdetail_reply_remark_name, "匿名用户");
                }
                holder.setText(R.id.item_productdetail_reply_remark_content, data.getCommentcontent());
                holder.setText(R.id.item_productdetail_reply_time_tv, data.getModifydate());
                ((RatingBar) holder.getView(R.id.popupwindow_ratedcourse_ratingbar1)).setRating(Float.parseFloat(data.getScore()));


                if (data.getModel().size() != 0) {
                    holder.getView(R.id.item_productdetail_reply_remark_ivlayout).setVisibility(View.VISIBLE);

                    ImageView iv0 = holder.getView(R.id.item_productdetail_reply_remark_iv0);
                    ImageView iv1 = holder.getView(R.id.item_productdetail_reply_remark_iv1);
                    ImageView iv2 = holder.getView(R.id.item_productdetail_reply_remark_iv2);

                    if (data.getModel().size() >= 1) {
                        iv0.setVisibility(View.VISIBLE);
                        Glide.with(ProductDetailActivity.this).load(Constant.SERVER_HOST + data.getModel().get(0).getCommentPhoto()).into(iv0);
                        holder.setOnClickListener(R.id.item_productdetail_reply_remark_iv0, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MyUtils.commonToImageListActivityByUrl(ProductDetailActivity.this, view, data.getModel().get(0).getCommentPhoto());
                            }
                        });
                    } else {
                        iv0.setVisibility(View.GONE);
                    }

                    if (data.getModel().size() >= 2) {
                        iv1.setVisibility(View.VISIBLE);
                        Glide.with(ProductDetailActivity.this).load(Constant.SERVER_HOST + data.getModel().get(1).getCommentPhoto()).into(iv1);
                        holder.setOnClickListener(R.id.item_productdetail_reply_remark_iv1, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MyUtils.commonToImageListActivityByUrl(ProductDetailActivity.this, view, data.getModel().get(1).getCommentPhoto());
                            }
                        });
                    } else {
                        iv1.setVisibility(View.GONE);
                    }

                    if (data.getModel().size() >= 3) {
                        iv2.setVisibility(View.VISIBLE);
                        Glide.with(ProductDetailActivity.this).load(Constant.SERVER_HOST + data.getModel().get(2).getCommentPhoto()).into(iv2);
                        holder.setOnClickListener(R.id.item_productdetail_reply_remark_iv2, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MyUtils.commonToImageListActivityByUrl(ProductDetailActivity.this, view, data.getModel().get(2).getCommentPhoto());
                            }
                        });
                    } else {
                        iv2.setVisibility(View.GONE);
                    }
                } else {
                    holder.getView(R.id.item_productdetail_reply_remark_ivlayout).setVisibility(View.GONE);
                }


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
                getProductCommentsData();
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
        dp500 = Utility.dip2px(ProductDetailActivity.this, 500);
        ProductId = getIntent().getStringExtra("ProductId");

        setViewpagerAndRecyclerView();
        showLoadingDialog();
        getData();
        getProductCommentsData();

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
            case R.id.activity_productdetail_floatingactionbutton:
                if (viewPager1.getCurrentItem() == 0) {
                    recyclerView0.smoothScrollToPosition(0);
                } else if (viewPager1.getCurrentItem() == 1) {
                    recyclerView1.smoothScrollToPosition(0);
                }
                break;
            case R.id.ll1:
                pop_type = 0;
                Util_PopupWindow.show(pop_product, this);
                break;
            case R.id.activity_productdetail_submit0:
                if (MyUtils.isLogin()) {
                    pop_type = 1;
                    Util_PopupWindow.show(pop_product, this);
                } else {
                    MyUtils.commonToLogin(this);
                }
                break;
            case R.id.activity_productdetail_submit1:
                if (MyUtils.isLogin()) {
                    pop_type = 2;
                    Util_PopupWindow.show(pop_product, this);
                } else {
                    MyUtils.commonToLogin(this);
                }
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


        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    if (dy0 > dp500) floatingActionButton.show();
                    else if (dy0 < dp500) floatingActionButton.hide();
                } else if (position == 1) {
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
        Util_PopupWindow.SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(
                this,
                R.layout.popupwindow_product_buy,
                null,
                new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        MyUtils.setWindowBackground(ProductDetailActivity.this, 1f);
                        String productparatitle = "";
                        for (TagListView tagListView : popupWindow_TagListView_list) {
                            if (tagListView.getHasCheckedTag() != null) {
                                productparatitle += tagListView.getHasCheckedTag().getTitle() + " ";
                            }
                        }

                        tv_productpara.setText(productparatitle);
                    }
                });

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
                        setCommonProductNumber();

                        if (productNumber <= 1) {
                            Utility.showToast(ProductDetailActivity.this, "不能再减了哦～");
                        } else {
                            productNumber--;
                            pop_number_ed.setText(productNumber + "");
                        }
                        break;
                    case R.id.plus:
                        setCommonProductNumber();

                        productNumber++;
                        pop_number_ed.setText(productNumber + "");
                        break;
                    case R.id.submit:
                        if (pop_type == 0) {

                        } else if (pop_type == 1) {
                            for (TagListView tagListView : popupWindow_TagListView_list) {
                                if (tagListView.getHasCheckedTag() == null) {
                                    showToast("请选择 " + tagListView.getTag());

                                    tagListView.setFocusable(true);
                                    tagListView.setFocusableInTouchMode(true);
                                    tagListView.requestFocus();
                                    return;
                                }
                            }

                            String productpara = "";
                            for (TagListView tagListView : popupWindow_TagListView_list) {
                                if (tagListView.getHasCheckedTag() != null) {
                                    productpara += tagListView.getHasCheckedTag().getId() + ",";
                                }
                            }

                            setCommonProductNumber();
                            getJoinData(productNumber + "", productpara);
                            showLoadingDialog();

                        } else if (pop_type == 2) {
                            for (TagListView tagListView : popupWindow_TagListView_list) {
                                if (tagListView.getHasCheckedTag() == null) {
                                    showToast("请选择 " + tagListView.getTag());

                                    tagListView.setFocusable(true);
                                    tagListView.setFocusableInTouchMode(true);
                                    tagListView.requestFocus();
                                    return;
                                }
                            }

                            String productpara = "";
                            for (TagListView tagListView : popupWindow_TagListView_list) {
                                if (tagListView.getHasCheckedTag() != null) {
                                    productpara += tagListView.getHasCheckedTag().getId() + ",";
                                }
                            }
                            productpara = productpara.trim();
                            if (productpara.endsWith(","))
                                productpara = productpara.substring(0, productpara.length() - 1);


                            setCommonProductNumber();
                            MyUtils.commonBUY(ProductDetailActivity.this, ProductId, productNumber + "", productpara, "", 11);
                        }
                        pop_product.dismiss();
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

    private void setCommonProductNumber() {
        if (!MyUtils.hasValue(pop_number_ed.getText().toString()))
            pop_number_ed.setText("1");
        productNumber = Integer.parseInt(pop_number_ed.getText().toString());
    }

    public void getData() {
        utilNetwork.getProductDetailModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("ProductId", ProductId + "")});
    }

    public void getProductCommentsData() {
        utilNetwork.getProductCommentsModel(
                new OkHttpClientManager.Param[]{
//                        new OkHttpClientManager.Param("ProductId", ProductId + ""),
//                        new OkHttpClientManager.Param("pageIndex", pageIndex + "")
                        new OkHttpClientManager.Param("ProductId", ProductId),
                        new OkHttpClientManager.Param("pageIndex", pageIndex + "")
                });
    }

    public void getJoinData(String number, String productpara) {
        utilNetwork.getJoinModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("productid", ProductId),
                        new OkHttpClientManager.Param("number", number),
                        new OkHttpClientManager.Param("productpara", productpara)
                });
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.PRODUCTDETAIL_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    productDetailModel = (ProductDetailModel) model;

                    List<View> views = new ArrayList<>();
                    views.add(pager1_0);
                    views.add(pager1_1);

                    List<String> pagerTitle = new ArrayList<>();
                    pagerTitle.add("商品详情");
                    pagerTitle.add("评价 (" + productDetailModel.getCount() + ")");

                    viewPager1.setAdapter(new ViewPagerAdapter(views, pagerTitle));
                    tabLayout.setupWithViewPager(viewPager1, false);
                    MyUtils.setIndicator(this, tabLayout, Utility.getScreenWidth(this) / 7, Utility.getScreenWidth(this) / 7);

                    setPopupWindow(productDetailModel);

                    ProductDetailModel.ListEntity listEntity = productDetailModel.getList().get(0);
                    List<ProductDetailModel.PhotolistEntity> photolistEntities = productDetailModel.getPhotolist();

                    tv_title.setText(listEntity.getMainTitle());
                    tv_price.setText("￥" + listEntity.getOriginalprice());
                    tv_sold.setText("已售" + listEntity.getSalesVolume() + "件");

                    setViewPager0(photolistEntities);

                    List<String> url = new ArrayList<>();
                    url.add(listEntity.getContents());
                    commonRefreshAdapter_web.setNewData(url);


                } else {

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
                    pageIndex--;
                    commonRefreshAdapter.setLoadFailedView(R.layout.item_recyclerview_loadingfailedview);
                }
                break;
            case Constant.JOIN_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    Utility.showToast(this, "加入购物车成功了哦～");
                } else {
                    Utility.showToast(this, model.getErrmsg());
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
