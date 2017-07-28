package com.jo.jingou.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.adapter.ViewPagerAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfyu on 2016/12/1.
 */
public class LauncherActivity extends MyBaseActivity {

    View btn1;
    TextView tv1;
    ViewPager viewPager;
    View circlelinearlayout;
    ImageView circle0, circle1, circle2;


    int time = 3;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            tv1.setText("点击跳过(" + time + "s)");
            if (time == 0) {
                MyUtils.setAndStartIntent(LauncherActivity.this, MainActivity.class);
                finish();
            } else {
                handler.sendEmptyMessageDelayed(0, 1000);
            }

        }
    };


    @Override
    public void initData() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        layoutId = R.layout.activity_launcher;
    }

    @Override
    public void findViews() {
        btn1 = findViewAndSetClick(R.id.activity_launcher_btn1);
        tv1 = (TextView) findViewById(R.id.activity_launcher_tv1);
        viewPager = (ViewPager) findViewById(R.id.activity_launcher_viewpager);

        circlelinearlayout = findViewById(R.id.activity_launcher_circlelinearlayout);
        circle0 = (ImageView) findViewById(R.id.activity_launcher_circle0);
        circle1 = (ImageView) findViewById(R.id.activity_launcher_circle1);
        circle2 = (ImageView) findViewById(R.id.activity_launcher_circle2);


    }

    @Override
    public void initListener() {
//
//        ImageView imageView1 = new ImageView(this);
//        imageView1.setImageResource(R.drawable.bg_01);
//        ImageView imageView2 = new ImageView(this);
//        imageView2.setImageResource(R.drawable.bg_02);
//        View view = getLayoutInflater().inflate(R.layout.item_launcher_03, null);
//        view.findViewById(R.id.item_launch_03_btn1).setOnClickListener(this);
//        List<View> views = new ArrayList<>();
//        views.add(imageView1);
//        views.add(imageView2);
//        views.add(view);


//        viewPager.setAdapter(new ViewPagerAdapter(views));
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            public void onPageScrollStateChanged(int state) {
//
//            }
//
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        circle0.setImageResource(R.drawable.banner_circle_0);
//                        circle1.setImageResource(R.drawable.banner_circle_1);
//                        circle2.setImageResource(R.drawable.banner_circle_1);
//                        break;
//                    case 1:
//                        circle0.setImageResource(R.drawable.banner_circle_1);
//                        circle1.setImageResource(R.drawable.banner_circle_0);
//                        circle2.setImageResource(R.drawable.banner_circle_1);
//                        break;
//                    case 2:
//                        circle0.setImageResource(R.drawable.banner_circle_1);
//                        circle1.setImageResource(R.drawable.banner_circle_1);
//                        circle2.setImageResource(R.drawable.banner_circle_0);
//                        break;
//                }
//            }
//        });
    }

    @Override
    public void setupViews() {
        utilNetwork.getTokenModel(Constant.TOKEN_ID, null);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.item_launch_03_btn1:
            case R.id.activity_launcher_btn1:
                MyUtils.setAndStartIntent(this, MainActivity.class);
                this.finish();
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.TOKEN_ID:
                if (status_code == 0) {
//                    是否启动引导页（三个滑动页面）
//                    if ("1".equals(application.getIsFirstUse())) {
//                        btn1.setVisibility(View.VISIBLE);
//                        viewPager.setVisibility(View.GONE);
//                        circlelinearlayout.setVisibility(View.GONE);
//                        handler.sendEmptyMessageDelayed(0, 1000);
//
//                    } else if ("0".equals(application.getIsFirstUse())) {
//                        btn1.setVisibility(View.GONE);
//                        viewPager.setVisibility(View.VISIBLE);
//                        circlelinearlayout.setVisibility(View.VISIBLE);
//                    }

                    btn1.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.GONE);
                    circlelinearlayout.setVisibility(View.GONE);
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("提示");
                    builder.setMessage("网络连接失败，请检查网络设置");
                    builder.setPositiveButton("重试", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            utilNetwork.getTokenModel(Constant.TOKEN_ID, null);
                        }
                    });
                    builder.setNegativeButton("关闭app", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create().show();

                    //finish();
                }
                break;
        }

    }
}
