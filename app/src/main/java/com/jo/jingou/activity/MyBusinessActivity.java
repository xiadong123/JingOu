package com.jo.jingou.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.ApplyinfoModel;
import com.jo.jingou.model.ImageBean;
import com.jo.jingou.model.UpLoadModel;
import com.jo.jingou.model.UserInfoModel;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_PopupWindow;
import com.jo.jingou.view.dialog.AgreementDialogUtil;
import com.jo.jingou.view.imageshowpickerview.ImageShowPickerBean;
import com.jo.jingou.view.imageshowpickerview.ImageShowPickerListener;
import com.jo.jingou.view.imageshowpickerview.ImageShowPickerView;
import com.jo.jingou.view.imageshowpickerview.Loader;
import com.squareup.okhttp.Request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

public class MyBusinessActivity extends MyBaseActivity {

    private LinearLayout sContent;
    private EditText sShop_name_et;
    private EditText sShop_tel_et;
    private EditText sShop_account_et;
    private RecyclerView sBrand_rcy;
    private TextView sIdentity_des_tv;
    private RecyclerView sIdentity_rcy;
    private TextView sCertificates_des_tv;
    private RecyclerView sCertificates_rcy;
    private RelativeLayout sBank_card_submit_rl;
    private TextView notice_tv;
    private TextView bank_card_submit_tv;
    private ImageView business_tel_iv;

    private ImageShowPickerView brand_ispv;
    private ImageShowPickerView identity_ispv;
    private ImageShowPickerView certificates_ispv;

    PopupWindow pop;
    private int currentType = -1;  //当前选择图片的类型  0.品牌授权书 1.身份验证 2.相关证件
    private List<ImageBean> brandList = new ArrayList<>();
    private List<Bitmap> brandListBitmap = new ArrayList<>();
    private List<ImageBean> identityList = new ArrayList<>();
    private List<Bitmap> identityListBitmap = new ArrayList<>();
    private List<ImageBean> certificatesList = new ArrayList<>();
    private List<Bitmap> certificatesListBitmap = new ArrayList<>();
    private String shopName;
    private String shopTel;
    private String shopAccount;
    private File[] myFile;
    private String[] myPhotoNames;
    private String mImgPara;
    //    private boolean beginBusiness;  //当前状态是否为成为商家  false 则为查看自己上传的信息  且只有品牌授权书的图片可以修改
    private List<Bitmap> temList;
    private ApplyinfoModel applyinfoModel;

    private int applyStatus = 2;  //0：申请中；1：已通过2：未通过；3：未申请
    private File[] myEditFile;
    private String[] myEditPhotoNames;
    private UserInfoModel userInfoModel;
    private StringBuilder editNetUrlBuilder;
    private int businessApplyId;
    private String storeMobile;
    private String alipayAccount;
    private String storeName;
    private String editNetUrlStr = "";


    @Override
    public void initData() {
        layoutId = R.layout.activity_my_business;
    }

    @Override
    public void findViews() {
        sContent = (LinearLayout) findViewById(R.id.content);
        sShop_name_et = (EditText) findViewById(R.id.shop_name_et);
        sShop_tel_et = (EditText) findViewById(R.id.shop_tel_et);
        sShop_account_et = (EditText) findViewById(R.id.shop_account_et);
        sIdentity_des_tv = (TextView) findViewById(R.id.identity_des_tv);
        sCertificates_des_tv = (TextView) findViewById(R.id.certificates_des_tv);
        sBank_card_submit_rl = (RelativeLayout) findViewById(R.id.bank_card_submit_rl);
        notice_tv = (TextView) findViewById(R.id.notice_tv);
        bank_card_submit_tv = (TextView) findViewById(R.id.bank_card_submit_tv);
        business_tel_iv = (ImageView) findViewById(R.id.business_tel_iv);

        brand_ispv = (ImageShowPickerView) findViewById(R.id.brand_ispv);
        identity_ispv = (ImageShowPickerView) findViewById(R.id.identity_ispv);
        certificates_ispv = (ImageShowPickerView) findViewById(R.id.certificates_ispv);
    }

    @Override
    public void initListener() {
        sBank_card_submit_rl.setOnClickListener(this);
    }

    @Override
    public void setupViews() {
        userInfoModel = UserInfoLoader.getUserInfoModel();
        if (userInfoModel == null) {
            finish();
            return;
        }
        applyStatus = userInfoModel.getModel().getApplyStatus();

        leftimg3.setImageResource(R.drawable.back);

        setStatus();
    }

    private void setStatus() {
        if (applyStatus == 2 || applyStatus == 3) {  //当前页面状态为  成为商家 未开始 或者审核未通过
            setTopview2("成为商家");
            showAgreementDialog();  //展示成为商家的用户协议
            setBrandData(); //设置品牌授权书的 适配器
            setIdentityData(); //设置身份验证的 适配器
            setCertificatesData(); //设置相关证件的 适配器
            setIdentityDes();  //设置身份验证的介绍文字颜色
            setCertificatesDes();  //设置相关证件的介绍的文字颜色
        } else if (applyStatus == 0) {  //审核中
            setTopview2("成为商家");
            sBank_card_submit_rl.setEnabled(false);
            bank_card_submit_tv.setText("审核中");
            getapplyinfo();  // 请求接口 获取商家申请详情
        } else if (applyStatus == 1) {  //查看的状态 审核通过
            setTopview2("我的店铺");
            righttxt.setVisibility(View.VISIBLE);
            righttxt.setText("保存");
            righttxt.setTextColor(getResources().getColor(R.color.gary7));
            getapplyinfo();  // 请求接口 获取商家申请详情
        }
    }

    /**
     * 设置 提示文案 并改变部分文案的字体颜色 &改变 底部按钮的文案
     */
    private void setNoticeTv() {
        notice_tv.setText("注：如要修改本页信息，请联系客服电话：" + userInfoModel.getServicetel());
        String noticeTv = notice_tv.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(noticeTv);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan whiteSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));
        builder.setSpan(whiteSpan, 19, noticeTv.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        notice_tv.setText(builder);

        //改变底部按钮的文案
        bank_card_submit_tv.setText("联系客服");
        business_tel_iv.setVisibility(View.VISIBLE);
    }

    /**
     * 展示用户协议的dialog
     */
    private void showAgreementDialog() {
        AgreementDialogUtil.showDialog(this, new AgreementDialogUtil.DialogConfirm() {
            @Override
            public void confirm() {

            }
        });
    }

    /**
     * 请求接口 获取商家申请详情
     */
    private void getapplyinfo() {
        showLoadingDialog();
        utilNetwork.getApplyinfoModel(new OkHttpClientManager.Param[]{});
    }

    /**
     * 设置相关证件的介绍的文字颜色
     */
    private void setCertificatesDes() {
        SpannableStringBuilder builder = new SpannableStringBuilder(sCertificates_des_tv.getText().toString());
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.gary6));

        builder.setSpan(redSpan, 3, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sCertificates_des_tv.setText(builder);
    }

    /**
     * 设置身份验证的介绍文字颜色
     * <p/>
     * 请上传身份证正面、身份证背面、手持身份证半身照三张照片，并且照片中身份证上的文字要清晰可见。
     */
    private void setIdentityDes() {
        SpannableStringBuilder builder = new SpannableStringBuilder(sIdentity_des_tv.getText().toString());
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.gary6));
        ForegroundColorSpan whiteSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));

        builder.setSpan(redSpan, 3, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(whiteSpan, 23, 27, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sIdentity_des_tv.setText(builder);
    }

    /**
     * 设置相关证件的 适配器
     */
    private void setCertificatesData() {

        if (applyStatus == 0 || applyStatus == 1) {  //审核通过或者审核中
            certificates_ispv.setShowDel(false);
            certificates_ispv.setIsShowAddPic(false);
        } else {  //未开始状态和审核失败状态
            certificates_ispv.setShowDel(true);
            certificates_ispv.setIsShowAddPic(true);
        }

        certificates_ispv.setImageLoaderInterface(new Loader());
        certificates_ispv.setNewData(certificatesList);

        certificates_ispv.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {  //点击添加图片的监听
                boolean permission = MyUtils.getPermission(MyBusinessActivity.this, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
                if (permission) {
                    MyBaseActivity.needCompletePhoto = true;
                    currentType = 2;
                    pop = Util_PopupWindow.setPopupWindow_CutImg(MyBusinessActivity.this, "相关证件");
                    Util_PopupWindow.show(pop, MyBusinessActivity.this);
                }
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum, View view) {
                //图片的点击事件
                goToImageList(view, certificatesList.get(position).getImageShowPickerUrl());
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {  //删除 按钮的点击事件监听
                certificatesList.remove(position);
                certificatesListBitmap.remove(position);
            }
        });
        certificates_ispv.show();
    }

    private void goToImageList(View view, String imageShowPickerUrl) {
        MyUtils.commonToImageListActivityByNoHostUrl(MyBusinessActivity.this, view, imageShowPickerUrl);

//        //图片的点击事件
//        Intent intent = new Intent(MyBusinessActivity.this, ImageListActivity.class);
//        //传入选中的Item对应的ID
//        intent.putExtra(ImageListActivity.EXTRA_PARAM_ID, view.getId());
//
//        //主要的语句
//        //通过makeSceneTransitionAnimation传入多个Pair
//        //每个Pair将一个当前Activity的View和目标Activity中的一个Key绑定起来
//        //在目标Activity中会调用这个Key
//        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                MyBusinessActivity.this,
//                new Pair<View, String>(view,
//                        ImageListActivity.VIEW_NAME_HEADER_IMAGE));
//        // ActivityCompat是android支持库中用来适应不同android版本的
//        ActivityCompat.startActivity(MyBusinessActivity.this, intent, activityOptions.toBundle());
    }

    /**
     * 设置身份验证的 适配器
     */
    private void setIdentityData() {

        if (applyStatus == 0 || applyStatus == 1) {  //审核通过或者审核中
            identity_ispv.setShowDel(false);
            identity_ispv.setIsShowAddPic(false);
        } else {  //未开始状态和审核失败状态
            identity_ispv.setShowDel(true);
            identity_ispv.setIsShowAddPic(true);
        }

        identity_ispv.setImageLoaderInterface(new Loader());
        identity_ispv.setNewData(identityList);
        identity_ispv.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {  //点击添加图片的监听
                boolean permission = MyUtils.getPermission(MyBusinessActivity.this, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
                Log.e("addOnClickListener", permission + "--");
                if (permission) {
                    currentType = 1;
                    MyBaseActivity.needCompletePhoto = true;
                    pop = Util_PopupWindow.setPopupWindow_CutImg(MyBusinessActivity.this, "身份验证");
                    Util_PopupWindow.show(pop, MyBusinessActivity.this);
                }
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum, View view) {
                //图片的点击事件
                goToImageList(view, identityList.get(position).getImageShowPickerUrl());
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {  //删除 按钮的点击事件监听
                identityList.remove(position);
                identityListBitmap.remove(position);
            }
        });
        identity_ispv.show();
    }

    /**
     * 设置品牌授权书的 适配器
     */
    private void setBrandData() {

        if (applyStatus == 0) {  //审核中
            brand_ispv.setShowDel(false);
            brand_ispv.setIsShowAddPic(false);
        } else {  //未开始状态和审核失败状态
            brand_ispv.setShowDel(true);
            brand_ispv.setIsShowAddPic(true);
        }

        brand_ispv.setImageLoaderInterface(new Loader());
        brand_ispv.setNewData(brandList);
        brand_ispv.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {  //点击添加图片的监听
                boolean permission = MyUtils.getPermission(MyBusinessActivity.this, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
                if (permission) {
                    currentType = 0;
                    MyBaseActivity.needCompletePhoto = true;
                    pop = Util_PopupWindow.setPopupWindow_CutImg(MyBusinessActivity.this, "品牌授权书");
                    Util_PopupWindow.show(pop, MyBusinessActivity.this);
                }
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum, View view) {
                //图片的点击事件
                goToImageList(view, brandList.get(position).getImageShowPickerUrl());
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {  //删除 按钮的点击事件监听
                brandList.remove(position);
                if (applyStatus != 1) { //当前状态不为已通过 地址只有本地图片 url和bitmap的脚标 对应的为一样的
                    //地址中包含网络图片和本地图片
                    brandListBitmap.remove(position);
                }
            }
        });
        brand_ispv.show();
    }

    @Override
    public void onCompleteImageCallBack(String mUri) {
        super.onCompleteImageCallBack(mUri);
        Log.e("onCompleteImageCallBack", mUri + "--");
        MyBaseActivity.needCompletePhoto = false;
        //当前选择图片的类型   currentType  0.品牌授权书 1.身份验证 2.相关证件
        switch (currentType) {
            case 0:
                //在listview或recyclerview才会使用这个brandList.add(),其他情况都不用
                brandList.add(new ImageBean(mUri));
                brand_ispv.addData(new ImageBean(mUri));
                if (applyStatus != 1) {  //当前状态不为已通过  已通过状态 的这个图片不需要转换为bitmap了 全部用url处理
                    Glide.with(MyApplication.getApplication()).load(mUri).asBitmap().into(new SimpleTarget
                            <Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            brandListBitmap.add(resource);
//                        temList.add(resource);
                        }
                    }); //方法中设置asBitmap可以设置回调类型
                }
                break;
            case 1:
                identityList.add(new ImageBean(mUri));
                identity_ispv.addData(new ImageBean(mUri));
                Glide.with(MyApplication.getApplication()).load(mUri).asBitmap().into(new SimpleTarget
                        <Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        identityListBitmap.add(resource);
//                        temList.add(resource);
                    }
                }); //方法中设置asBitmap可以设置回调类型
                break;
            case 2:
                certificatesList.add(new ImageBean(mUri));
                certificates_ispv.addData(new ImageBean(mUri));
                Glide.with(MyApplication.getApplication()).load(mUri).asBitmap().into(new SimpleTarget
                        <Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        certificatesListBitmap.add(resource);
//                        temList.add(resource);
                    }
                }); //方法中设置asBitmap可以设置回调类型
                break;
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.bank_card_submit_rl:
                if (applyStatus == 3 || applyStatus == 2) {  //未开始状态和审核失败状态
                    boolean isComplete = checkData();  //检查 用户填写的信息是否完整
                    if (isComplete) {  //填写完整了
                        uploadData();  //上传用户填写的数据到服务器
                    }
                } else if (applyStatus == 1) {  //审核通过
                    if (userInfoModel.getServicetel() == null) {
                        showToast("客服电话有误");
                    } else {
                        MyUtils.callPhone(this, userInfoModel.getServicetel());
                    }
                }
                break;
            case R.id.base_topview_right:  //保存我的店铺的信息
                if (applyStatus == 1) {  //当申请已通过时 才可以点击这个按钮  (这个按钮隐藏的时候也能够点击)
                    rightview.setVisibility(View.VISIBLE);
                    //逻辑 走编辑的接口 在这个接口里面处理图片的时候 需要区分当前图片是本地图片还是网络图片
                    //如果是本地图片 就保存下来 如果是网络图片 就拼成一个字符串上传给服务器
                    //这里不是用brandListBitmap 直接处理Url   brandList
                    //网络的Url在brandList 和本地的url都在
                    uploadEditData();  //上传数据 成为商家审核通过了  修改品牌授权书的图片
                }
                break;
        }
    }

    /**
     * 上传数据 成为商家审核通过了  修改品牌授权书的图片
     */
    private void uploadEditData() {
        try {
            showLoadingDialog();
            setEditBitmap();  //处理 编辑的图片
            if (myEditFile.length > 0) {
                OkHttpClientManager.getUploadDelegate().postAsyn(
                        Constant.EDITAPPLY + MyApplication.getToken() + "," + MyApplication.getSoleToken() + "," +
                                MyApplication.getUser_id(),
                        myEditPhotoNames, myEditFile,
                        new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("BusinessApplyId", businessApplyId
                                + "")
                                , new
                                OkHttpClientManager.Param("Mobile", storeMobile),
                                new OkHttpClientManager.Param("Account", alipayAccount), new OkHttpClientManager.Param
                                ("StoreName", storeName), new OkHttpClientManager.Param
                                ("ImgPara", mImgPara), new OkHttpClientManager.Param
                                ("UploadId", editNetUrlStr)},
                        new OkHttpClientManager.ResultCallback<UpLoadModel>() {
                            @Override
                            public void onError(Request request, Exception e) {
                                cancelLoadingDialog();
                                e.printStackTrace();
                                showToast("修改失败");
                            }

                            @Override
                            public void onResponse(UpLoadModel model) {
                                cancelLoadingDialog();
                                if (model.getErrcode() == 0) {
                                    UserInfoLoader.getUserInfoModel().getModel().setApplyStatus(0);
                                    showToast("修改成功");
                                    finish();
                                } else {
                                    showToast(model.getErrmsg());
                                }
                            }
                        }, "cnupload");
            } else {
                utilNetwork.getEditapplyModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param
                        ("BusinessApplyId", businessApplyId + ""), new OkHttpClientManager.Param("Mobile", storeMobile),
                        new OkHttpClientManager.Param("Account", alipayAccount), new OkHttpClientManager.Param
                        ("StoreName", storeName), new OkHttpClientManager.Param
                        ("ImgPara", mImgPara), new OkHttpClientManager.Param
                        ("UploadId", editNetUrlStr)});
            }
        } catch (Exception e) {
            cancelLoadingDialog();
            showToast("修改失败");
            e.printStackTrace();
        }
    }

    /**
     * 处理 编辑的图片
     */
    private void setEditBitmap() {
//        brandList
        List<ImageBean> editNetUrl = new ArrayList<>();
        List<String> editLocalUrl = new ArrayList<>();

        for (int i = 0; i < brandList.size(); i++) {
            String imageShowPickerUrl = brandList.get(i).getImageShowPickerUrl();
            String id = brandList.get(i).getId();
            if (imageShowPickerUrl.contains("http://")) {  //说明此图片为网络图片
                ImageBean imageBean = new ImageBean(imageShowPickerUrl);
                imageBean.setId(id);
                editNetUrl.add(imageBean);
            } else {  //本地图片
                editLocalUrl.add(imageShowPickerUrl);
            }
        }

        myEditFile = new File[editLocalUrl.size()];  //编辑的 本地图片File集合 用来上传数据
        myEditPhotoNames = new String[editLocalUrl.size()];  //编辑的 本地图片的名字 用来上传数据使用
        for (int i = 0; i < editLocalUrl.size(); i++) {
            String myPhotoName = "busniessEdit" + i + System.currentTimeMillis() + ".jpg";
            File file = new File(editLocalUrl.get(i));
            myEditFile[i] = file;
            myEditPhotoNames[i] = myPhotoName;
        }

        //设置 上传到服务器的 图片参数  方便后台区分
        mImgPara = "1-" + myEditFile.length + ",2-0,3-0";

        editNetUrlBuilder = new StringBuilder();  //未改变的图片的id 用,拼接的字符串
        for (int i = 0; i < editNetUrl.size(); i++) {
            editNetUrlBuilder.append(editNetUrl.get(i).getId() + ",");
        }
        for (int i = 0; i < identityList.size(); i++) {
            editNetUrlBuilder.append(identityList.get(i).getId() + ",");
        }
        for (int i = 0; i < certificatesList.size(); i++) {
            editNetUrlBuilder.append(certificatesList.get(i).getId() + ",");
        }
        String substring = editNetUrlBuilder.toString().substring(editNetUrlBuilder.length() - 1, editNetUrlBuilder
                .length());
        if (",".equals(substring)) {
            editNetUrlStr = editNetUrlBuilder.substring(0, editNetUrlBuilder.length() - 1);
        } else {
            editNetUrlStr = editNetUrlBuilder.toString();
        }
    }

    /**
     * 上传用户填写的数据到服务器
     */
    private void uploadData() {
        try {
            showLoadingDialog();
            setBrandBitmap();  //处理 图片
            OkHttpClientManager.getUploadDelegate().postAsyn(
                    Constant.APPLYMERCHANT + MyApplication.getToken() + "," + MyApplication.getSoleToken() + "," +
                            MyApplication.getUser_id(),
                    myPhotoNames, myFile,
                    new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("StoreName", shopName), new
                            OkHttpClientManager.Param("Mobile", shopTel),
                            new OkHttpClientManager.Param("Account", shopAccount), new OkHttpClientManager.Param
                            ("ImgPara", mImgPara)},
                    new OkHttpClientManager.ResultCallback<UpLoadModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            cancelLoadingDialog();
                        }

                        @Override
                        public void onResponse(UpLoadModel model) {
                            cancelLoadingDialog();
                            if (model.getErrcode() == 0) {
                                UserInfoLoader.getUserInfoModel().getModel().setApplyStatus(0);
                                showToast("申请成功");
                                finish();
                            } else {
                                showToast(model.getErrmsg());
                            }
                        }
                    }, "cnupload");
        } catch (Exception e) {

        }
    }

    /**
     * 处理 图片 方便上传到服务器
     */
    private void setBrandBitmap() {
        temList = new ArrayList<>();
        integrationBitmip(brandListBitmap);  //把三个集合中的Bitmap整合到一个里面 方便些
        integrationBitmip(identityListBitmap);  //把三个集合中的Bitmap整合到一个里面 方便些
        integrationBitmip(certificatesListBitmap);  //把三个集合中的Bitmap整合到一个里面 方便些
        //设置 上传到服务器的 图片参数  方便后台区分
        mImgPara = "1-" + brandList.size() + ",2-" + identityList.size() + ",3-" + certificatesList.size();

        myFile = new File[temList.size()];
        myPhotoNames = new String[temList.size()];
        for (int i = 0; i < temList.size(); i++) {
            String myPhotoName = "busniess" + i + System.currentTimeMillis() + ".jpg";
            try {
                myFile[i] = MyUtils.saveFile(temList.get(i), myPhotoName);
                myPhotoNames[i] = myPhotoName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把三个集合中的Bitmap整合到一个里面
     *
     * @param myListBitmap
     */
    private void integrationBitmip(List<Bitmap> myListBitmap) {
        for (int i = 0; i < myListBitmap.size(); i++) {
            temList.add(myListBitmap.get(i));
        }
    }

    /**
     * 检查 用户填写的信息是否完整
     *
     * @return
     */
    private boolean checkData() {
        shopName = sShop_name_et.getText().toString().trim();
        if (!MyUtils.hasValue(shopName)) {
            showToast("请填写店铺名称");
            return false;
        }
        shopTel = sShop_tel_et.getText().toString().trim();
        if (!MyUtils.hasValue(shopTel)) {
            showToast("请填写联系电话");
            return false;
        }
        shopAccount = sShop_account_et.getText().toString().trim();
        if (!MyUtils.hasValue(shopAccount)) {
            showToast("请填写公司账户");
            return false;
        }
        if (brandList == null || brandList.size() == 0) {
            showToast("请上传品牌授权书证件照");
            return false;
        }
        if (identityList == null || identityList.size() < 3) {
            showToast("请上传三张身份验证证件照");
            return false;
        }


        if (certificatesList == null || certificatesList.size() == 0) {
            showToast("请上传相关证件证件照");
            return false;
        }
        return true;
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.APPLYINFO_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    applyinfoModel = (ApplyinfoModel) model;
                    UserInfoLoader.getUserInfoModel().getModel().setApplyStatus(applyinfoModel.getModel()
                            .getApplyState());
                    applyStatus = applyinfoModel.getModel().getApplyState();
                    //----------先将数据处理好 返回的图片url 放到集合中

                    if (applyStatus == 0) {  //审核中
                        rightview.setVisibility(View.GONE);
                        setTopview2("成为商家");
                        sBank_card_submit_rl.setEnabled(false);
                        bank_card_submit_tv.setText("审核中");
                        setApplyInfoData(applyinfoModel);  //将服务器返回来的数据 展示到界面上 并处理服务器返回的三串图片
                    } else if (applyStatus == 1) {  //查看的状态 审核通过
                        setTopview2("我的店铺");
                        rightview.setVisibility(View.VISIBLE);
                        righttxt.setVisibility(View.VISIBLE);
                        rightview.setOnClickListener(this);
                        righttxt.setText("保存");
                        righttxt.setTextColor(getResources().getColor(R.color.gary7));
                        setNoticeTv();
                        setApplyInfoData(applyinfoModel);  //将服务器返回来的数据 展示到界面上 并处理服务器返回的三串图片
                    }
                    setIdentityDes();  //设置身份验证的介绍文字颜色
                    setCertificatesDes();  //设置相关证件的介绍的文字颜色
                    setBrandData(); //设置品牌授权书的 适配器
                    setIdentityData(); //设置身份验证的 适配器
                    setCertificatesData(); //设置相关证件的 适配器
                } else {
                    if (model != null)
                        showToast(model.getErrmsg());
                }
                break;
            case Constant.EDITAPPLY_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    UserInfoLoader.getUserInfoModel().getModel().setApplyStatus(0);
                    showToast("修改成功");
                    finish();
                } else {
                    if (model != null) {
                        showToast(model.getErrmsg());
                    }
                }

                break;
        }
    }

    /**
     * 将服务器返回来的数据 展示到界面上 并处理服务器返回的三串图片
     *
     * @param applyinfoModel
     */
    private void setApplyInfoData(ApplyinfoModel applyinfoModel) {
        ApplyinfoModel.ModelBean model = applyinfoModel.getModel();
        //获取到图片id 设置到ImageBean里面去 编辑的时候 上传需要的
        List<ApplyinfoModel.ModelBean.UploadBean> upload = model.getUpload();

        businessApplyId = model.getBusinessApplyId();
        storeMobile = model.getStoreMobile();
        alipayAccount = model.getAlipayAccount();
        storeName = model.getStoreName();

        for (int i = 0; i < upload.size(); i++) {
            ApplyinfoModel.ModelBean.UploadBean uploadBean = upload.get(i);
            int type = uploadBean.getType();
            if (type == 0) {  //0:品牌图；1：身份证图；2：许可证图
                setPhotoList(uploadBean, brandList);
            } else if (type == 1) {
                setPhotoList(uploadBean, identityList);
            } else if (type == 2) {
                setPhotoList(uploadBean, certificatesList);
            }
        }
        setApplyText(model);  //设置基本信息 审核通过 查看我的店铺中使用
    }

    /**
     * 将服务器返回的图片URL 和id　放到对应的集合中
     *
     * @param uploadBean
     * @param brandList
     */
    private void setPhotoList(ApplyinfoModel.ModelBean.UploadBean uploadBean, List<ImageBean> brandList) {
        ImageBean imageBean = new ImageBean(uploadBean.getUrl());
        imageBean.setId(uploadBean.getUploadId() + "");
        brandList.add(imageBean);
    }

    /**
     * 设置基本信息 审核通过 查看我的店铺中使用
     *
     * @param model
     */
    private void setApplyText(ApplyinfoModel.ModelBean model) {
        sShop_name_et.setText(model.getStoreName());
        sShop_tel_et.setText(model.getStoreMobile());
        sShop_account_et.setText(model.getAlipayAccount());

        sShop_name_et.setEnabled(false);
        sShop_tel_et.setEnabled(false);
        sShop_account_et.setEnabled(false);

        sShop_name_et.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        sShop_tel_et.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        sShop_account_et.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
    }

    @Override
    protected void onDestroy() {
        recycleBitmap(brandListBitmap);
        recycleBitmap(identityListBitmap);
        recycleBitmap(certificatesListBitmap);
        super.onDestroy();
    }

    /**
     * @param brandListBitmap
     */
    private void recycleBitmap(List<Bitmap> brandListBitmap) {
        for (int i = 0; i < brandListBitmap.size(); i++) {
            brandListBitmap.get(i).recycle();
        }

    }
}
