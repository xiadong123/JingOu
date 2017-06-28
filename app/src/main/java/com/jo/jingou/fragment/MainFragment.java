package com.jo.jingou.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.activity.AngelDetailActivity;
import com.jo.jingou.activity.ImproveInformationActivity;
import com.jo.jingou.activity.Login_Register_ResetPasswordActivity;
import com.jo.jingou.activity.MainActivity;
import com.jo.jingou.activity.MessageActivity;
import com.jo.jingou.activity.MyBusinessActivity;
import com.jo.jingou.activity.MyCreditActivity;
import com.jo.jingou.activity.MyPurseActivity;
import com.jo.jingou.activity.SelectCityActivity;
import com.jo.jingou.adapter.AutomaticRollAdapter;
import com.jo.jingou.base.MyBaseFragment;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.AngelModel;
import com.jo.jingou.model.BanrndModel;
import com.jo.jingou.model.ShareModel;
import com.jo.jingou.model.loader.CityLoader;
import com.jo.jingou.model.loader.RewardMessageloader;
import com.jo.jingou.utils.BannerView;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_PopupWindow;
import com.jo.jingou.view.ADEnity;
import com.jo.jingou.view.TextViewAd;
import com.jo.jingou.view.dialog.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;
import caesar.feng.framework.utils.Utility;

/**
 * Created by dfyu on 2016/11/23.
 */
public class MainFragment extends MyBaseFragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    TextViewAd textViewAd;

    ViewPager viewPager1, viewPager2;
    LinearLayout viewPager1_circle_layout, viewPager2_circle_layout;//小圆点layout
    View.OnTouchListener onTouchListener;//该Lisntener是为解决 SwipeRefreshLayout和ViewPager滑动手势冲突 而设置的
    ViewPager.OnPageChangeListener onPageChangeListener1, onPageChangeListener2;
    ArrayList<BannerView> bannerViews1 = new ArrayList<>();
    ArrayList<BannerView> bannerViews2 = new ArrayList<>();
    boolean is_banrnl_refresh, is_angel_refresh;

    View loginRegisterLayout, loginView, registerView;

    View mall, mybusiness, mypurse, mycredit;

    View share;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://启动自动轮播
                    viewPager1.setCurrentItem(viewPager1.getCurrentItem() + 1);
                    handler.sendEmptyMessageDelayed(0, 3000);
                    break;
            }
        }
    };


    @Override
    public void initData() {
        layoutId = R.layout.fragment_main;
    }

    @Override
    public void findViews() {
        findTopViews();
        leftlayout.setVisibility(View.VISIBLE);
        centerimg.setVisibility(View.VISIBLE);
        rightimg.setVisibility(View.VISIBLE);


        textViewAd = (TextViewAd) rootView.findViewById(R.id.fragment_main_textViewAd);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.fragment_main_SwipeRefreshLayout);
        viewPager1 = (ViewPager) rootView.findViewById(R.id.fragment_main_viewpager1);
        viewPager2 = (ViewPager) rootView.findViewById(R.id.fragment_main_viewpager2);
        viewPager1_circle_layout = (LinearLayout) rootView.findViewById(R.id.fragment_main_indexlayout1);
        viewPager2_circle_layout = (LinearLayout) rootView.findViewById(R.id.fragment_main_indexlayout2);

        loginRegisterLayout = rootView.findViewById(R.id.fragment_main_loginregisterlayout);
        loginView = rootView.findViewById(R.id.fragment_main_loginbtn);
        registerView = rootView.findViewById(R.id.fragment_main_registerbtn);

        mall = rootView.findViewById(R.id.fragment_main_layout0);
        mybusiness = rootView.findViewById(R.id.fragment_main_layout1);
        mypurse = rootView.findViewById(R.id.fragment_main_mypurse_layout);
        mycredit = rootView.findViewById(R.id.fragment_main_layout3);

        share = rootView.findViewById(R.id.fragment_main_share);

        setListener();
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mSwipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mSwipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        };


        onPageChangeListener1 = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int p = position % bannerViews1.size();
                for (BannerView bannerView : bannerViews1)
                    bannerView.getmBannerCircle().setImageResource(R.drawable.banner_circle_1);
                bannerViews1.get(p).getmBannerCircle().setImageResource(R.drawable.banner_circle_0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };


        onPageChangeListener2 = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int p = position % bannerViews2.size();
                for (BannerView bannerView : bannerViews2)
                    bannerView.getmBannerCircle().setImageResource(R.drawable.banner_circle_3);
                bannerViews2.get(p).getmBannerCircle().setImageResource(R.drawable.banner_circle_2);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };


        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                is_banrnl_refresh = false;
                is_angel_refresh = false;
                utilNetwork.getHomeBanrndModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "3")});
                utilNetwork.getHomeAngelModel(new OkHttpClientManager.Param[]{});
            }
        };
    }

    @Override
    public void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        viewPager1.setOnTouchListener(onTouchListener);
        viewPager2.setOnTouchListener(onTouchListener);
        viewPager1.addOnPageChangeListener(onPageChangeListener1);
        viewPager2.addOnPageChangeListener(onPageChangeListener2);
        loginView.setOnClickListener(this);
        registerView.setOnClickListener(this);
        mall.setOnClickListener(this);
        mybusiness.setOnClickListener(this);
        mypurse.setOnClickListener(this);
        mycredit.setOnClickListener(this);
        share.setOnClickListener(this);

        leftview.setOnClickListener(this);
        rightview.setOnClickListener(this);
    }

    @Override
    public void setupViews() {
        Log.i("TAG", "MyUtils.isLogin===" + MyUtils.isLogin());

        leftlayout_tv.setText(CityLoader.getCity());

        if (MyUtils.isLogin())
            loginRegisterLayout.setVisibility(View.GONE);
        else loginRegisterLayout.setVisibility(View.VISIBLE);

        mSwipeRefreshLayout.setRefreshing(true);
        is_banrnl_refresh = false;
        is_angel_refresh = false;
        utilNetwork.getHomeBanrndModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "3")});
        utilNetwork.getHomeAngelModel(new OkHttpClientManager.Param[]{});
        handler.sendEmptyMessageDelayed(0, 3000);

        setTextViewAd(RewardMessageloader.getRewardMessage());
        setMessageImage();
    }


    private void setTextViewAd(List<String> messages) {
        Log.i("TAG", "setTextViewAd!!!!!!!!!!!!");

        List<ADEnity> adEnities = new ArrayList<>();
        for (String message : messages) {
            ADEnity adEnity = new ADEnity(message, "", "url");
            adEnities.add(adEnity);
        }

        textViewAd.setmTexts(adEnities);
        textViewAd.invalidate();
    }


    //设置第一个轮播图--广告
    private void setViewPager1(List<BanrndModel.ListEntity> bannerlist) {
        ArrayList<View> views = new ArrayList<>();
        bannerViews1.clear();
        viewPager1_circle_layout.removeAllViews();

        for (BanrndModel.ListEntity entity : bannerlist) {
            View banner = inflater.inflate(R.layout.item_banner_1, null);
            ImageView banner_iv = (ImageView) banner.findViewById(R.id.item_banner_1_iv);
            Glide.with(context).load(entity.getValue()).into(banner_iv);

            View circle = inflater.inflate(R.layout.item_banner_circle, null);
            ImageView circle_iv = (ImageView) circle.findViewById(R.id.item_banner_circle_iv);
            circle_iv.setImageResource(R.drawable.banner_circle_1);

            views.add(banner);
            bannerViews1.add(new BannerView(banner, banner_iv, circle_iv));
            viewPager1_circle_layout.addView(circle);
        }
        viewPager1.setAdapter(new AutomaticRollAdapter(views));
        viewPager1.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utility.getScreenWidth(context) * 558 / 1000));
    }

    //设置第二个轮播图--小天使产品
    private void setViewPager2(List<AngelModel.ListEntity> angellist) {
        ArrayList<View> views = new ArrayList<>();
        bannerViews2.clear();
        viewPager2_circle_layout.removeAllViews();

        for (final AngelModel.ListEntity entity : angellist) {
            Log.i("TAG", "AngelModel.ListEntity setOnClickListener");

            View banner = inflater.inflate(R.layout.item_banner_2, null);
            banner.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Log.i("TAG", "AngelModel.ListEntity setOnClick");
                    Intent i = new Intent(context, AngelDetailActivity.class);
                    i.putExtra("ProductId", entity.getProductId() + "");
                    startActivity(i);
                }
            });

            ImageView banner_iv = (ImageView) banner.findViewById(R.id.item_banner_2_iv);
            Glide.with(context).load(Constant.SERVER_HOST + entity.getListImgUrl()).into(banner_iv);

            View circle = inflater.inflate(R.layout.item_banner_circle, null);
            ImageView circle_iv = (ImageView) circle.findViewById(R.id.item_banner_circle_iv);
            circle_iv.setImageResource(R.drawable.banner_circle_3);

            views.add(banner);
            bannerViews2.add(new BannerView(banner, banner_iv, circle_iv));
            viewPager2_circle_layout.addView(circle);
        }

        viewPager2.setAdapter(new AutomaticRollAdapter(views));
        viewPager2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utility.getScreenWidth(context) * 558 / 1000));
    }


    @Override
    public void upData() {
        if (rootView != null) {
            if (MyUtils.isLogin()) {
                loginRegisterLayout.setVisibility(View.GONE);
            } else {
                loginRegisterLayout.setVisibility(View.VISIBLE);
            }
            leftlayout_tv.setText(CityLoader.getCity());
        }
        setMessageImage();
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.base_topview_left:
                MyUtils.setAndStartIntent(context, SelectCityActivity.class);
                break;
            case R.id.base_topview_right:
                if (MyUtils.isLogin()) {
                    i = new Intent(context, MessageActivity.class);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_main_loginbtn:
                i = new Intent(context, Login_Register_ResetPasswordActivity.class);
                i.putExtra(Constant.BYTYPE, "bylogin");
                i.putExtra("toBuy", 0);
                startActivity(i);
                break;
            case R.id.fragment_main_registerbtn:
                i = new Intent(context, Login_Register_ResetPasswordActivity.class);
                i.putExtra(Constant.BYTYPE, "byregister");
                i.putExtra("toBuy", 0);
                startActivity(i);
                break;
            case R.id.fragment_main_layout0:
                ((MainActivity) context).setPager(1);
                break;
            case R.id.fragment_main_layout1:
                if (MyUtils.isLogin()) {
                    if (!("false".equals(MyApplication.getIsIdCode()))) {  //说明 用户已经失明认证过了
                        Intent intent = new Intent(context, MyBusinessActivity.class);
                        startActivity(intent);
                    } else {
                        String content = "还未实名认证，只有实名认证后才可以成为商家";
                        String confirmText = "立即前往";
                        DialogUtils.showTelDialog(getmActivity(), content, confirmText, new DialogUtils.DialogConfirm() {
                            @Override
                            public void confirm() {
                                Intent i = new Intent(getmActivity(), ImproveInformationActivity.class);
                                i.putExtra(Constant.BYTYPE, "isIdCode");
                                startActivity(i);
                            }
                        });
                    }
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_main_mypurse_layout:
                if (MyUtils.isLogin()) {
                    i = new Intent(context, MyPurseActivity.class);
                    startActivity(i);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_main_layout3:
                if (MyUtils.isLogin()) {
                    MyUtils.setAndStartIntent(context, MyCreditActivity.class);
                } else {
                    showToast("请去登录");
                }
                break;
            case R.id.fragment_main_share:
                if (MyUtils.isLogin()) {
                    utilNetwork.getShareModel(new OkHttpClientManager.Param[]{});
                } else {
                    MyUtils.commonToLogin(context);
                }
                break;
        }

    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.HOMEANGEL_ID:
                is_angel_refresh = true;
                if (is_banrnl_refresh && is_angel_refresh)
                    mSwipeRefreshLayout.setRefreshing(false);
                if (status_code == 0) {
                    AngelModel angelModel = (AngelModel) model;
                    List<AngelModel.ListEntity> entitys = angelModel.getList();
                    setViewPager2(entitys);
                    viewPager2.setCurrentItem(entitys.size() * 50);
                }
                break;

            case Constant.HOMEBANRND_ID:
                is_banrnl_refresh = true;
                if (is_banrnl_refresh && is_angel_refresh)
                    mSwipeRefreshLayout.setRefreshing(false);
                if (status_code == 0) {
                    BanrndModel angelModel = (BanrndModel) model;
                    List<BanrndModel.ListEntity> entitys = angelModel.getList();
                    setViewPager1(entitys);
                    viewPager1.setCurrentItem(entitys.size() * 50);
                }
                break;
            case Constant.SHARE_ID:
                if (status_code == 0) {
                    ShareModel shareModel = (ShareModel) model;
                    Util_PopupWindow.show(Util_PopupWindow.setCommonPopupWindow_Share1((MainActivity) context, shareModel.getInvitation(), "分享"), (MainActivity) context);
                }
                break;
        }
    }

}
