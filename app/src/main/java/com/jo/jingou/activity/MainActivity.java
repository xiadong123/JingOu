package com.jo.jingou.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.fragment.CartFragment;
import com.jo.jingou.fragment.IndustryFragment;
import com.jo.jingou.fragment.MainFragment;
import com.jo.jingou.fragment.MallFragment;
import com.jo.jingou.fragment.MineFragment;
import com.jo.jingou.model.InstationCountModel;
import com.jo.jingou.model.ProductTypeModel;
import com.jo.jingou.model.UserInfoModel;
import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_MallFragment_Data;
import com.umeng.socialize.UMShareAPI;

import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;
import caesar.feng.framework.utils.Utility;

/**
 * Created by dfyu on 2016/11/23.
 */
public class MainActivity extends MyBaseActivity {


    //顶部功能按钮

    //中部Fragment
    MainFragment mMainFragment;
    MallFragment mMallFragment;
    IndustryFragment mIndustryFragment;
    CartFragment mCartFragment;
    MineFragment mMineFragment;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction;

    //底部切换按钮
    View mBView0, mBView1, mBView2, mBView3, mBView4;
    ImageView mBImageView0, mBImageView1, mBImageView2, mBImageView3, mBImageView4;
    TextView mBTextView0, mBTextView1, mBTextView2, mBTextView3, mBTextView4;
    ImageView[] mBImageViews;
    TextView[] mBTextViews;
    int[] mBottomViewCheck = new int[]{R.drawable.home, R.drawable.mall_01, R.drawable.buy_01, R.drawable.hangye_01,
            R.drawable.mine_01};
    int[] mBottomViewUnCheck = new int[]{R.drawable.home_01, R.drawable.mall, R.drawable.buy, R.drawable.hangye, R
            .drawable.mine};
    int[] mBottomViewTextColor = new int[]{R.color.textcolor0, R.color.gary5};


    //

    //商城全部分类
    View mMallView_t;
    View mMallView_b;
    View mMallLinearLayout;
    GridLayout mMallGridLayout;


    @Override
    public void initData() {
        layoutId = R.layout.activity_main;
    }

    @Override
    public void findViews() {

        mBView0 = findViewAndSetClick(R.id.activity_main_bottomview_view0);
        mBView1 = findViewAndSetClick(R.id.activity_main_bottomview_view1);
        mBView2 = findViewAndSetClick(R.id.activity_main_bottomview_view2);
        mBView3 = findViewAndSetClick(R.id.activity_main_bottomview_view3);
        mBView4 = findViewAndSetClick(R.id.activity_main_bottomview_view4);

        mBImageView0 = (ImageView) findViewById(R.id.activity_main_bottomview_view0_iv);
        mBImageView1 = (ImageView) findViewById(R.id.activity_main_bottomview_view1_iv);
        mBImageView2 = (ImageView) findViewById(R.id.activity_main_bottomview_view2_iv);
        mBImageView3 = (ImageView) findViewById(R.id.activity_main_bottomview_view3_iv);
        mBImageView4 = (ImageView) findViewById(R.id.activity_main_bottomview_view4_iv);

        mBTextView0 = (TextView) findViewById(R.id.activity_main_bottomview_view0_tv);
        mBTextView1 = (TextView) findViewById(R.id.activity_main_bottomview_view1_tv);
        mBTextView2 = (TextView) findViewById(R.id.activity_main_bottomview_view2_tv);
        mBTextView3 = (TextView) findViewById(R.id.activity_main_bottomview_view3_tv);
        mBTextView4 = (TextView) findViewById(R.id.activity_main_bottomview_view4_tv);

        mBImageViews = new ImageView[]{mBImageView0, mBImageView1, mBImageView2, mBImageView3, mBImageView4};
        mBTextViews = new TextView[]{mBTextView0, mBTextView1, mBTextView2, mBTextView3, mBTextView4};


        mMallView_t = findViewById(R.id.activity_main_malltype_t);
        mMallView_b = findViewAndSetClick(R.id.activity_main_malltype_v);
        mMallLinearLayout = findViewAndSetClick(R.id.activity_main_malltype_ll);
        mMallGridLayout = (GridLayout) findViewById(R.id.activity_main_malltype_gl);

        mMainFragment = new MainFragment();
        mMallFragment = new MallFragment();
        mIndustryFragment = new IndustryFragment();
        mCartFragment = new CartFragment();
        mMineFragment = new MineFragment();

//        transaction.add(mMainFragment, "main");
//        transaction.add(mMineFragment, "mine");

        setFragment(0);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        application.setIsFirstUse("1");
        //提前获取商城列表数据
        Util_MallFragment_Data.getProductTypeModel();
        //setGridLayout();
        MyUtils.getPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
        if (MyUtils.isLogin()) {
            utilNetwork.getUserInfoModel(new OkHttpClientManager.Param[]{});
            utilNetwork.getInstationCountModel(new OkHttpClientManager.Param[]{});
        }

        MessageLoader.addListener(new MessageLoader.OnMessageChangeListener() {
            @Override
            public void onMessageChanged(boolean hasNewMessage, int newMessageNumber) {
                mMainFragment.setMessageImage();
                mMallFragment.setMessageImage();
                mIndustryFragment.setMessageImage();
                mCartFragment.setMessageImage();
                mMineFragment.setMessageImage();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mMainFragment.upData();
        mMallFragment.upData();
        mIndustryFragment.upData();
        mCartFragment.upData();
        mMineFragment.upData();
        if (MyUtils.isLogin()) {
            utilNetwork.getUserInfoModel(new OkHttpClientManager.Param[]{});
            utilNetwork.getInstationCountModel(new OkHttpClientManager.Param[]{});
        }

        Log.i("TAG", "setPager!!!!!!!!!!!" + getIntent().getBooleanExtra("forcart", false));
        if (getIntent().getBooleanExtra("forcart", false)) {
            setPager(2);
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                //Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("是否退出利益天下？");
                builder.setTitle("提示");
                builder.setPositiveButton("再看看", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("狠心退出", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                        System.exit(0);
                    }
                });
               // builder.setCancelable(false);
                builder.create().show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.activity_main_bottomview_view0:
                setPager(0);
                break;
            case R.id.activity_main_bottomview_view1:
                setPager(1);
                break;
            case R.id.activity_main_bottomview_view2:
                setPager(2);
                break;
            case R.id.activity_main_bottomview_view3:
                setPager(3);
                break;
            case R.id.activity_main_bottomview_view4:
                setPager(4);
                break;

            case R.id.activity_main_malltype_v:
            case R.id.activity_main_malltype_ll:
                dismissMallTypeLayout();
                break;
        }
    }

    public void setPager(int page) {
        setBottomView(page);
        setFragment(page);
    }

    public void setFragment(int page) {
        transaction = fm.beginTransaction();
        switch (page) {
            case 0:
                transaction.replace(R.id.activity_main_fragmentlayout, mMainFragment);
                break;
            case 1:
                transaction.replace(R.id.activity_main_fragmentlayout, mMallFragment);
                break;
            case 2:
                transaction.replace(R.id.activity_main_fragmentlayout, mCartFragment);
                break;
            case 3:
                transaction.replace(R.id.activity_main_fragmentlayout, mIndustryFragment);
                break;
            case 4:
                transaction.replace(R.id.activity_main_fragmentlayout, mMineFragment);
                break;
        }
        transaction.commit();
    }


    public void setBottomView(int page) {
        for (int i = 0; i < 5; i++) {
            if (i == page) {
                mBImageViews[i].setImageResource(mBottomViewCheck[i]);
                mBTextViews[i].setTextColor(ContextCompat.getColor(this, mBottomViewTextColor[0]));
            } else {
                mBImageViews[i].setImageResource(mBottomViewUnCheck[i]);
                mBTextViews[i].setTextColor(ContextCompat.getColor(this, mBottomViewTextColor[1]));
            }
        }
    }


    public void setGridLayout(ProductTypeModel productTypeModel) {
        List<ProductTypeModel.ListEntity> listEntities = productTypeModel.getList();

        for (int i = 0; i < listEntities.size(); i++) {
            View v = getLayoutInflater().inflate(R.layout.item_mainactivity_malltype, null);
            ((TextView) v.findViewById(R.id.textview)).setText(listEntities.get(i).getTypeName());
            final int index = i;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismissMallTypeLayout();

                    mMallFragment.onSearchMallType(index);
                }
            });

            mMallGridLayout.addView(v);
            GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) v.getLayoutParams();
            layoutParams.width = Utility.getScreenWidth(this) / 4;
            v.setLayoutParams(layoutParams);
        }
    }

    public void showMallTypeLayout() {
        //mMallView_t.setVisibility(View.VISIBLE);
        mMallView_b.setVisibility(View.VISIBLE);
        mMallLinearLayout.setVisibility(View.VISIBLE);
        mMallGridLayout.setVisibility(View.VISIBLE);
    }

    public void dismissMallTypeLayout() {
        //mMallView_t.setVisibility(View.GONE);
        mMallView_b.setVisibility(View.GONE);
        mMallLinearLayout.setVisibility(View.GONE);
        mMallGridLayout.setVisibility(View.GONE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.GETMODEL_ID:
                if (status_code == 0) {
                    UserInfoLoader.setUserInfoModel((UserInfoModel) model);
                }
                break;

            case Constant.INSTATIONCOUNT_ID:
                cancelLoadingDialog();
                if (status_code == 0) {

                    InstationCountModel instationCountModel = (InstationCountModel) model;

                    int message0_number = instationCountModel.getCount1();
                    int message1_number = instationCountModel.getCount2();
                    int message2_number = instationCountModel.getCount3();
                    int message3_number = instationCountModel.getCount4();

                    MessageLoader.setMessage(message0_number, message1_number, message2_number, message3_number);
                }
                break;
        }
    }


}
