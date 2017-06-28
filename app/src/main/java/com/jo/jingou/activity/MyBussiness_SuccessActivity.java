package com.jo.jingou.activity;

import android.content.Context;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;

/**
 * Created by dfyu on 2017/5/17.
 */
public class MyBussiness_SuccessActivity extends MyBaseActivity {


    TextView tv0, tv1, tv2;

    @Override
    public void initData() {
        layoutId = R.layout.activity_mybusiness_success;
    }

    @Override
    public void findViews() {
        tv0 = (TextView) findViewById(R.id.activity_mybusiness_success_tv0);
        tv1 = (TextView) findViewById(R.id.activity_mybusiness_success_tv1);
        tv2 = (TextView) findViewAndSetClick(R.id.activity_mybusiness_success_tv2);
    }

    @Override
    public void initListener() {
        tv0.setText(getIntent().getStringExtra("Account"));
        tv1.setText(getIntent().getStringExtra("Password"));
    }

    @Override
    public void setupViews() {
        setTopview2("开店信息");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_mybusiness_success_tv2:
                onClickCopy(tv2.getText().toString());
                break;
        }
    }

    public void onClickCopy(String txt) {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(txt);
        Toast.makeText(this, "复制到剪切板", Toast.LENGTH_LONG).show();
    }
}
