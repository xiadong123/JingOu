package com.jo.jingou.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.UpLoadModel;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_PopupWindow;
import com.jo.jingou.view.CircleImageView;
import com.squareup.okhttp.Request;

import java.io.File;

import caesar.feng.framework.net.OkHttpClientManager;
import caesar.feng.framework.utils.Utility;

/**
 * Created by Administrator on 2016/12/11.
 */

public class UserInfoActivity extends MyBaseActivity {

    View head_layout, name_layout, sex_layout, birthday_layout, email_layout;
    CircleImageView head;
    TextView name, sex, birthday, email;

    PopupWindow pop;


    @Override
    public void initData() {
        layoutId = R.layout.activity_userinfo;
    }

    @Override
    public void findViews() {
        head_layout = findViewAndSetClick(R.id.activity_userinfo_head_layout);
        name_layout = findViewAndSetClick(R.id.activity_userinfo_name_layout);
        sex_layout = findViewAndSetClick(R.id.activity_userinfo_sex_layout);
        birthday_layout = findViewAndSetClick(R.id.activity_userinfo_birthday_layout);
        email_layout = findViewAndSetClick(R.id.activity_userinfo_email_layout);

        head = (CircleImageView) findViewById(R.id.activity_userinfo_head);
        name = (TextView) findViewById(R.id.activity_userinfo_name_tv);
        sex = (TextView) findViewById(R.id.activity_userinfo_sex_tv);
        birthday = (TextView) findViewById(R.id.activity_userinfo_birthday_tv);
        email = (TextView) findViewById(R.id.activity_userinfo_email_tv);

        setLogoutView();

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("个人信息");


        if (UserInfoLoader.getUserInfoModel() != null) {

            if (MyUtils.hasValue(UserInfoLoader.getUserInfoModel().getModel().getListImgUrl())) {
                Glide.with(this).load(Constant.SERVER_HOST + UserInfoLoader.getUserInfoModel().getModel().getListImgUrl()).into(head);
            } else {
                head.setImageResource(R.drawable.myhead);
            }


            if (MyUtils.hasValue(UserInfoLoader.getUserInfoModel().getModel().getName())) {
                name.setText(UserInfoLoader.getUserInfoModel().getModel().getName() + "");
            } else {
                name.setText("");
            }

            if (UserInfoLoader.getUserInfoModel().getModel().getSex() == 0)
                sex.setText("男");
            else
                sex.setText("女");

            if (MyUtils.hasValue(UserInfoLoader.getUserInfoModel().getModel().getBirthday())) {
                birthday.setText(UserInfoLoader.getUserInfoModel().getModel().getBirthday().replace("00:00:00", "") + "");
            } else {
                birthday.setText("");
            }

            if (MyUtils.hasValue(UserInfoLoader.getUserInfoModel().getModel().getEmail())) {
                email.setText(UserInfoLoader.getUserInfoModel().getModel().getEmail() + "");
            } else {
                email.setText("");
            }
        } else {
            Utility.showToast(this, "未获取到个人信息，请稍后重试");
            finish();
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.activity_userinfo_head_layout:
                if (MyUtils.getPermission(UserInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
                    pop = Util_PopupWindow.setPopupWindow_CutImg(this, "头像");
                    Util_PopupWindow.show(pop, this);
                }
                break;
            case R.id.activity_userinfo_name_layout:
                pop = Util_PopupWindow.setCommonPopupWindow_Edittext(this, "", new Util_PopupWindow.OnEdittextCallBack() {
                    public void OnEdittextCallBack(String txt) {
                        if (MyUtils.hasValue(txt)) {
                            showLoadingDialog();
                            utilNetwork.getChangeNameModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("name", txt)});
                            name.setText(txt);
                        } else {
                            showToast("未输入姓名");
                        }
                    }
                }, "姓名");
                Util_PopupWindow.show(pop, this);
                break;
            case R.id.activity_userinfo_sex_layout:
                pop = Util_PopupWindow.setPopupWindow_Sex(this, new Util_PopupWindow.OnCallBack_int_string() {
                    public void onSexCallBack(int sextype, String sextxt) {
                        showLoadingDialog();
                        utilNetwork.getChangeSexModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("Sex", sextype + "")});
                        sex.setText(sextxt);
                    }
                }, "性别");
                Util_PopupWindow.show(pop, this);
                break;
            case R.id.activity_userinfo_birthday_layout:
                pop = Util_PopupWindow.setPopupWindow_DatePicker(this, new Util_PopupWindow.OnDateCallBack() {
                    public void onDateCallBack(String date) {
                        showLoadingDialog();
                        utilNetwork.getChangeBirthdayModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("birthday", date)});
                        birthday.setText(date);
                    }
                }, "日期");
                Util_PopupWindow.show(pop, this);
                break;
            case R.id.activity_userinfo_email_layout:
                pop = Util_PopupWindow.setCommonPopupWindow_Edittext(this, "", new Util_PopupWindow.OnEdittextCallBack() {
                    @Override
                    public void OnEdittextCallBack(String txt) {
                        if (MyUtils.hasValue(txt)) {
                            if (Utility.isEmail(txt)) {
                                showLoadingDialog();
                                utilNetwork.getChangeEmailModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("email", txt)});
                                email.setText(txt);
                            } else {
                                showToast("请输入正确的邮箱");
                            }
                        } else {
                            showToast("未输入邮箱");
                        }
                    }
                }, "邮箱");
                Util_PopupWindow.show(pop, this);
                break;
        }
    }


    @Override
    public void onImageCutCallBack(Bitmap bitmap) {
        super.onImageCutCallBack(bitmap);
        head.setImageDrawable(new BitmapDrawable(bitmap));
        try {
            File file = MyUtils.saveFile(bitmap, "myhead.jpg");
            showLoadingDialog();
            OkHttpClientManager.getUploadDelegate().postAsyn(
                    Constant.CNUPLOAD + MyApplication.getToken() + "," + MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                    "myhead.jpg", file,
                    new OkHttpClientManager.Param[]{},
                    new OkHttpClientManager.ResultCallback<UpLoadModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            cancelLoadingDialog();
                        }

                        @Override
                        public void onResponse(UpLoadModel model) {
                            cancelLoadingDialog();
                            if (model.getErrcode() == 0)
                                UserInfoLoader.getUserInfoModel().getModel().setListImgUrl(model.getFile());
                            else showToast(model.getErrmsg());
                        }
                    }, "cnupload");
        } catch (Exception e) {

        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.CHANGENAME_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    UserInfoLoader.getUserInfoModel().getModel().setName(name.getText().toString() + "");
                }
                break;
            case Constant.CHANGESEX_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    if ("男".equals(sex.getText().toString())) {
                        UserInfoLoader.getUserInfoModel().getModel().setSex(0);
                    } else {
                        UserInfoLoader.getUserInfoModel().getModel().setSex(1);
                    }
                }
                break;
            case Constant.CHANGEBIRTHDAY_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    UserInfoLoader.getUserInfoModel().getModel().setBirthday(birthday.getText().toString() + "");
                }
                break;
            case Constant.CHANGEEMAIL_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    UserInfoLoader.getUserInfoModel().getModel().setEmail(email.getText().toString() + "");
                }
                break;
        }
    }
}
