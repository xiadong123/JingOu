package com.jo.jingou.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;
import com.jo.jingou.MyApplication;
import com.jo.jingou.R;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.model.AreaModel;
import com.jo.jingou.model.RecordPopModel;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import caesar.feng.framework.utils.Utility;


/**
 * Created by dfyu on 2016/3/16.
 */
public class Util_PopupWindow {

    private static Context context;
    private static Activity activity;
    private static ProgressDialog dialog;
    public static  IWXAPI api;

    private static int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private static int mTargetSceneCricle = SendMessageToWX.Req.WXSceneTimeline;
    public static <T extends MyBaseActivity> PopupWindow setCityPopupWindow(T t, AreaModel a,
                                                                            final OnCityCallBack
                                                                                    onCityCallBack, String text) {


        final View cancel, ok;
        final NumberPicker numberPicker0, numberPicker1, numberPicker2;
        String address, city, area;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_city_picker, null, null);
        context = t;
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);
        cancel = contentView.findViewById(R.id.title_cancle);
        ok = contentView.findViewById(R.id.title_ensure);

        numberPicker0 = (NumberPicker) contentView.findViewById(R.id.popupwindow_city_picker0);
        numberPicker1 = (NumberPicker) contentView.findViewById(R.id.popupwindow_city_picker1);
        numberPicker2 = (NumberPicker) contentView.findViewById(R.id.popupwindow_city_picker2);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
                onCityCallBack.onCityCallBack(
                        numberPicker0.getDisplayedValues()[numberPicker0.getValue()],
                        numberPicker1.getDisplayedValues()[numberPicker1.getValue()],
                        numberPicker2.getDisplayedValues()[numberPicker2.getValue()]);

            }
        });


        AreaModel areaModel = a;

        final List<AreaModel.AddressEntity> addresses = areaModel.getAddress();
        String[] addressNames = new String[addresses.size()];
        for (int i = 0; i < addresses.size(); i++)
            addressNames[i] = addresses.get(i).getName();
        MyUtils.setDisplayedValuesForPicker(numberPicker0, addressNames);

        List<AreaModel.AddressEntity.CityEntity> citys = addresses.get(0).getCity();
        String[] citynames = new String[citys.size()];
        for (int j = 0; j < citys.size(); j++)
            citynames[j] = citys.get(j).getName();
        MyUtils.setDisplayedValuesForPicker(numberPicker1, citynames);

        List<String> areas = citys.get(0).getArea();
        String[] areanames = new String[areas.size()];
        for (int k = 0; k < areas.size(); k++)
            areanames[k] = areas.get(k);
        MyUtils.setDisplayedValuesForPicker(numberPicker2, areanames);

        numberPicker0.setWrapSelectorWheel(false);
        numberPicker0.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        numberPicker0.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                //当pick0滑动时 重置picker1的数组
                List<AreaModel.AddressEntity.CityEntity> citys = addresses.get(i1).getCity();
                String[] citynames = new String[citys.size()];
                for (int j = 0; j < citys.size(); j++)
                    citynames[j] = citys.get(j).getName();
                MyUtils.setDisplayedValuesForPicker(numberPicker1, citynames);

                //当pick0滑动时 同步重置picker2的数组
                List<String> areas = citys.get(numberPicker1.getValue()).getArea();
                String[] areanames = new String[areas.size()];
                for (int k = 0; k < areas.size(); k++)
                    areanames[k] = areas.get(k);
                MyUtils.setDisplayedValuesForPicker(numberPicker2, areanames);
            }
        });

        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                List<String> areas = addresses.get(numberPicker0.getValue()).getCity().get(i1)
                        .getArea();
                String[] areanames = new String[areas.size()];
                for (int k = 0; k < areas.size(); k++)
                    areanames[k] = areas.get(k);
                MyUtils.setDisplayedValuesForPicker(numberPicker2, areanames);
            }
        });


        return setPopModel.getPopupWindow();
    }


    //分享Pop1
    public static <T extends MyBaseActivity> PopupWindow setCommonPopupWindow_Share1(final T t, final String url, String text) {
        View wx, wxc, sina, qq, cancel;
        activity = t;
        dialog = new ProgressDialog(t);
        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_share, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);
        wx = contentView.findViewById(R.id.popupwindow_share1_wx);
        wxc = contentView.findViewById(R.id.popupwindow_share1_wxc);
        sina = contentView.findViewById(R.id.popupwindow_share1_sina);
        qq = contentView.findViewById(R.id.popupwindow_share1_qq);
        cancel = contentView.findViewById(R.id.popupwindow_share1_cancel);
        final UMImage image = new UMImage(t, R.drawable.ic_launcher);
        wx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setPopModel.getPopupWindow().dismiss();

                api = WXAPIFactory.createWXAPI(activity, "wxbd3e6bba8efbae73");
                api.registerApp("wxbd3e6bba8efbae73");

                //初始化一个WXWebpageObject对象填写 url
                WXWebpageObject webpage  = new WXWebpageObject();
                webpage.webpageUrl = url;

                //用WXWebpageObject对象初始化一个WXMediaMessage 对象 填写标题 描述
                WXMediaMessage msg = new WXMediaMessage(webpage);

                msg.title ="利益天下";
                msg.description = "分享得邀请码";
                Bitmap thumb = BitmapFactory.decodeResource(activity.getResources() , R.drawable.ic_launcher);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumb.compress(Bitmap.CompressFormat.PNG, 100, baos);
                msg.thumbData = baos.toByteArray();

                //构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                //段用于唯一标识符
                req.message = msg;
                req.scene = mTargetScene;

                api.sendReq(req);

            }
        });

        wxc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setPopModel.getPopupWindow().dismiss();

                api = WXAPIFactory.createWXAPI(activity, "wxbd3e6bba8efbae73");
                api.registerApp("wxbd3e6bba8efbae73");

                //初始化一个WXWebpageObject对象填写 url
                WXWebpageObject webpage  = new WXWebpageObject();
                webpage.webpageUrl = url;

                //用WXWebpageObject对象初始化一个WXMediaMessage 对象 填写标题 描述
                WXMediaMessage msg = new WXMediaMessage(webpage);

                msg.title ="利益天下";
                msg.description = "分享得邀请码";
                Bitmap thumb = BitmapFactory.decodeResource(activity.getResources() , R.drawable.ic_launcher);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumb.compress(Bitmap.CompressFormat.PNG, 100, baos);
                msg.thumbData = baos.toByteArray();

                //构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                //段用于唯一标识符
                req.message = msg;
                req.scene = mTargetSceneCricle;

                api.sendReq(req);


            }
        });


        sina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setPopModel.getPopupWindow().dismiss();
                new ShareAction(t).setPlatform(SHARE_MEDIA.SINA)
                        .withMedia(image)
                        .withText("利益天下")
                        .withText("分享邀请码")
                        .withMedia(new UMWeb(url))
                        .share();

            }
        });

        qq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setPopModel.getPopupWindow().dismiss();
                new ShareAction(t).setPlatform(SHARE_MEDIA.QQ)
                        .withMedia(image)
                        .withText("利益天下")
                        .withText("分享邀请码")
                        .withMedia(new UMWeb(url))
                        .share();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setPopModel.getPopupWindow().dismiss();
            }
        });

        return setPopModel.getPopupWindow();
    }

    private static UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

            System.out.println("platform = " + platform.toString());
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(context, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable tt) {
            Toast.makeText(context, "失败" + tt.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(context, "取消了", Toast.LENGTH_LONG).show();

        }
    };


    static File PHOTO_DIR = null;

    public static <T extends MyBaseActivity> PopupWindow setPopupWindow_CutImg(final T t,
                                                                               String text) {
        TextView bt_0, bt_1, bt_2;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_cutimg, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);


        bt_0 = (TextView) contentView.findViewById(R.id.popupwindow_button0);
        bt_1 = (TextView) contentView.findViewById(R.id.popupwindow_button1);
        bt_2 = (TextView) contentView.findViewById(R.id.popupwindow_button2);

        // 初始化图片保存路径
        String photo_dir = AbFileUtil.getFullImageDownPathDir();
        Log.i("TAG", "photo_dir=" + photo_dir);
        if (AbStrUtil.isEmpty(photo_dir)) {
            Utility.showToast(t, "存储卡不存在");
        } else {
            PHOTO_DIR = new File(photo_dir);
        }
        bt_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t.imgSwitch(1);
                String mFileName = System.currentTimeMillis() + ".jpg";
                File mCurrentPhotoFile = new File(PHOTO_DIR, mFileName);
                t.setPath(mCurrentPhotoFile.getPath());
                Intent mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 调用前置摄像头
                mIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  //如果版本大于7.0
                    //通过FileProvider创建一个content类型的Uri
                    Uri imageUri = FileProvider.getUriForFile(MyApplication.getApplication(),
                            "com.jo.jingou.provider", mCurrentPhotoFile);
                    mIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    mIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
                } else {
                    mIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(mCurrentPhotoFile));
                }
                t.startActivityForResult(mIntent, Constant.IMGCALLBACK);
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                t.imgSwitch(2);
                Intent mIntent;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    mIntent = new Intent(Intent.ACTION_PICK);
                    mIntent.setType("image/*");
                } else {
                    mIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    mIntent.addCategory(Intent.CATEGORY_OPENABLE);
                    mIntent.setType("image/*");
                }
                t.startActivityForResult(mIntent, Constant.IMGCALLBACK);
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                t.imgSwitch(0);
                setPopModel.getPopupWindow().dismiss();
            }
        });

        return setPopModel.getPopupWindow();
    }


    public static <T extends MyBaseActivity> PopupWindow setPopupWindow_Sex(final T t,
                                                                            final
                                                                            OnCallBack_int_string
                                                                                    onCallBackIntstring,
                                                                            String text) {
        TextView bt_0, bt_1, bt_2;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_sex, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);


        bt_0 = (TextView) contentView.findViewById(R.id.popupwindow_button0);
        bt_1 = (TextView) contentView.findViewById(R.id.popupwindow_button1);
        bt_2 = (TextView) contentView.findViewById(R.id.popupwindow_button2);

        bt_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(0, "男");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(1, "女");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                setPopModel.getPopupWindow().dismiss();
            }
        });
        return setPopModel.getPopupWindow();
    }

    public static <T extends MyBaseActivity> PopupWindow setPopupWindow_Enchashment(final T t,
                                                                                    final
                                                                                    OnCallBack_int_string
                                                                                            onCallBackIntstring,
                                                                                    String text) {
        TextView bt_0, bt_1, bt_2, bt_3, bt_4, bt_cancel;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_enchasment, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);


        bt_0 = (TextView) contentView.findViewById(R.id.popupwindow_button0);
        bt_1 = (TextView) contentView.findViewById(R.id.popupwindow_button1);
        bt_2 = (TextView) contentView.findViewById(R.id.popupwindow_button2);
        bt_3 = (TextView) contentView.findViewById(R.id.popupwindow_button3);
        bt_4 = (TextView) contentView.findViewById(R.id.popupwindow_button4);
        bt_cancel = (TextView) contentView.findViewById(R.id.popupwindow_cancel);

        bt_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(1, "提现分红奖励");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(3, "提现分销奖励");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(4, "提现合伙人奖励");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBackIntstring.onSexCallBack(5, "提现推荐商家收益");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBackIntstring.onSexCallBack(2, "提现退款金额");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
            }
        });

        return setPopModel.getPopupWindow();
    }

    public static <T extends MyBaseActivity> PopupWindow setPopupWindow_Enchashment2(final T t,
                                                                                     final
                                                                                     OnCallBack_int_string
                                                                                             onCallBackIntstring,
                                                                                     String text) {
        TextView bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_cancel;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_enchasment2, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);


        bt_0 = (TextView) contentView.findViewById(R.id.popupwindow_button0);
        bt_1 = (TextView) contentView.findViewById(R.id.popupwindow_button1);
        bt_2 = (TextView) contentView.findViewById(R.id.popupwindow_button2);
        bt_3 = (TextView) contentView.findViewById(R.id.popupwindow_button3);
        bt_4 = (TextView) contentView.findViewById(R.id.popupwindow_button4);
        bt_5 = (TextView) contentView.findViewById(R.id.popupwindow_button5);

        bt_cancel = (TextView) contentView.findViewById(R.id.popupwindow_cancel);

        bt_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(1, "提现分红奖励");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(3, "提现分销奖励");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(4, "提现合伙人奖励");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBackIntstring.onSexCallBack(5, "提现推荐商家收益");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBackIntstring.onSexCallBack(6, "提现商家收益");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBackIntstring.onSexCallBack(2, "提现订单退款金额");
                setPopModel.getPopupWindow().dismiss();
            }
        });


        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
            }
        });

        return setPopModel.getPopupWindow();
    }


    public static <T extends MyBaseActivity> PopupWindow setPopupWindow_ReceivingState(final T t,
                                                                                       final
                                                                                       OnCallBack_int_string
                                                                                               onCallBackIntstring,
                                                                                       String text) {
        TextView bt_0, bt_1, bt_2;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_receiving_state, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);


        bt_0 = (TextView) contentView.findViewById(R.id.popupwindow_button0);
        bt_1 = (TextView) contentView.findViewById(R.id.popupwindow_button1);
        bt_2 = (TextView) contentView.findViewById(R.id.popupwindow_button2);

        bt_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(1, "已收货");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onCallBackIntstring.onSexCallBack(2, "未收货");
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setPopModel.getPopupWindow().dismiss();
            }
        });
        return setPopModel.getPopupWindow();
    }

    public static <T extends MyBaseActivity> PopupWindow setPopupWindow_ReturnReason(final T t,
                                                                                     final
                                                                                     OnEdittextCallBack
                                                                                             onEdittextCallBack,
                                                                                     String text) {

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_return_reason, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.popupwindow_button0:
                        onEdittextCallBack.OnEdittextCallBack("7天无理由退换货");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button1:
                        onEdittextCallBack.OnEdittextCallBack("不喜欢/不想要了");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button2:
                        onEdittextCallBack.OnEdittextCallBack("做工瑕疵问题");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button3:
                        onEdittextCallBack.OnEdittextCallBack("商品有气味问题");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button4:
                        onEdittextCallBack.OnEdittextCallBack("材质与商品描述不符");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button5:
                        onEdittextCallBack.OnEdittextCallBack("少件/漏发");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button6:
                        onEdittextCallBack.OnEdittextCallBack("商品破损/污渍");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button7:
                        onEdittextCallBack.OnEdittextCallBack("发票问题");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button8:
                        onEdittextCallBack.OnEdittextCallBack("其它");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button_cancel:
                        setPopModel.getPopupWindow().dismiss();
                        break;
                }
            }
        };

        contentView.findViewById(R.id.popupwindow_button0).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button1).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button2).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button3).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button4).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button5).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button6).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button7).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button8).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button_cancel).setOnClickListener
                (onClickListener);

        return setPopModel.getPopupWindow();
    }


    public static <T extends MyBaseActivity> PopupWindow setPopupWindow_ReturnReason2(final T t,
                                                                                      final
                                                                                      OnEdittextCallBack
                                                                                              onEdittextCallBack,
                                                                                      String text) {

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_return_reason2, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.popupwindow_button0:
                        onEdittextCallBack.OnEdittextCallBack("做工瑕疵问题");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button1:
                        onEdittextCallBack.OnEdittextCallBack("商品有气味问题");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button2:
                        onEdittextCallBack.OnEdittextCallBack("商品破损/污渍");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button3:
                        onEdittextCallBack.OnEdittextCallBack("商品与订单不符");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button4:
                        onEdittextCallBack.OnEdittextCallBack("其它");
                        setPopModel.getPopupWindow().dismiss();
                        break;
                    case R.id.popupwindow_button_cancel:
                        setPopModel.getPopupWindow().dismiss();
                        break;
                }
            }
        };

        contentView.findViewById(R.id.popupwindow_button0).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button1).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button2).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button3).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button4).setOnClickListener(onClickListener);
        contentView.findViewById(R.id.popupwindow_button_cancel).setOnClickListener
                (onClickListener);

        return setPopModel.getPopupWindow();
    }


    public static <T extends MyBaseActivity> PopupWindow setPopupWindow_DatePicker(final T t,
                                                                                   final
                                                                                   OnDateCallBack
                                                                                           onDateCallBack, String
                                                                                           text) {

        final View cancel, ok;
        final DatePicker datePicker;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_date_picker, null, null);
        View contentView = setPopModel.getContentView();
        contentView.setTag(text);

        cancel = contentView.findViewById(R.id.title_cancle);
        ok = contentView.findViewById(R.id.title_ensure);
        datePicker = (DatePicker) contentView.findViewById(R.id.popupwindow_datepicker);

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
                onDateCallBack.onDateCallBack(datePicker.getYear() + "-" + (datePicker.getMonth()
                        + 1) + "-" + datePicker.getDayOfMonth());
            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
        return setPopModel.getPopupWindow();
    }


    public static <T extends MyBaseActivity> PopupWindow setCommonPopupWindow_Edittext(final T t,
                                                                                       String deftxt, final
                                                                                       OnEdittextCallBack
                                                                                               onEdittextCallBack,
                                                                                       String text) {
        final View bt_cancel, bt_ok;
        final EditText ed_content;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_edittext, null, null);
        View contentView = setPopModel.getContentView();

        contentView.setTag(text);

        ed_content = (EditText) contentView.findViewById(R.id.popupwindow_edittext1);
        ed_content.setText(deftxt);
        bt_cancel = contentView.findViewById(R.id.title_cancle);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
            }
        });
        bt_ok = contentView.findViewById(R.id.title_ensure);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
                onEdittextCallBack.OnEdittextCallBack(ed_content.getText().toString());
            }
        });
        return setPopModel.getPopupWindow();
    }

    public static <T extends MyBaseActivity> PopupWindow setCommonPopupWindow_Edittext2(final T t, String deftxt,
                                                                                        String hint, int inputtype,
                                                                                        final OnEdittextCallBack
                                                                                                onEdittextCallBack,
                                                                                        String text) {
        final View bt_cancel, bt_ok;
        final EditText ed_content;

        final SetPopModel setPopModel = Util_PopupWindow.setBasePopupWindow(t, R.layout
                .popupwindow_edittext, null, null);
        View contentView = setPopModel.getContentView();

        contentView.setTag(text);

        ed_content = (EditText) contentView.findViewById(R.id.popupwindow_edittext1);
        ed_content.setText(deftxt);
        ed_content.setHint(hint);
        ed_content.setInputType(inputtype);

        bt_cancel = contentView.findViewById(R.id.title_cancle);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
            }
        });

        bt_ok = contentView.findViewById(R.id.title_ensure);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setPopModel.getPopupWindow().dismiss();
                onEdittextCallBack.OnEdittextCallBack(ed_content.getText().toString());
            }
        });
        return setPopModel.getPopupWindow();
    }

    public static <T extends MyBaseActivity> MyPopupWindow setRecordPopupWindow(final T t,
                                                                                final
                                                                                List<RecordPopModel> recordList,
                                                                                final onClickCallBack onClickCallBack) {
        final SetLiPopModel setPopModel = Util_PopupWindow.setBasePopupWindowLi(t, R.layout
                .record_pop, null, null);
        View contentView = setPopModel.getContentView();

        RecyclerView record_pop_rcv = (RecyclerView) contentView.findViewById(R.id.record_pop_rcv);
        record_pop_rcv.setLayoutManager(new LinearLayoutManager(t, LinearLayoutManager.VERTICAL,
                false));

        CommonRefreshAdapter commonRefreshAdapter = new CommonRefreshAdapter(t, recordList, R
                .layout.item_record_pop, false) {
            @Override
            protected void convert(ViewHolder holder, Object data) {
                RecordPopModel recordPopModel = (RecordPopModel) data;
                TextView item_record_pop_tv = holder.getView(R.id.item_record_pop_tv);
                item_record_pop_tv.setText(recordPopModel.getmContent());

                if (recordPopModel.isSelect()) {
                    item_record_pop_tv.setTextColor(t.getResources().getColor(R.color.red));
                } else {
                    item_record_pop_tv.setTextColor(t.getResources().getColor(R.color.gary5));
                }
            }
        };
        record_pop_rcv.setAdapter(commonRefreshAdapter);

        commonRefreshAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, Object data, int position) {
                for (int i = 0; i < recordList.size(); i++) {
                    if (i == position) {
                        recordList.get(i).setIsSelect(true);
                    } else {
                        recordList.get(i).setIsSelect(false);
                    }
                }
                onClickCallBack.onPopClickCallBack(recordList.get(position).getmId(), position,
                        recordList);
                setPopModel.getPopupWindow().dismiss();
            }
        });
        return setPopModel.getPopupWindow();
    }


    //popupwindow通用设置
    public static SetLiPopModel setBasePopupWindowLi(final Activity context, int id, View
            .OnTouchListener onTouchListener, PopupWindow.OnDismissListener onDismissListener) {
        View contentView = LayoutInflater.from(context).inflate(id, null);
        MyPopupWindow popupWindow = new MyPopupWindow(contentView, ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        final SetLiPopModel model = new SetLiPopModel(popupWindow, contentView);

        //model.getPopupWindow().setAnimationStyle(R.anim.popup_showanim);
        model.getPopupWindow().setAnimationStyle(R.style.popwin_anim_style_li);
        model.getPopupWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_ADJUST_RESIZE);
        model.getPopupWindow().setOutsideTouchable(true);
        model.getPopupWindow().setFocusable(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.argb(0, 0, 0, 0));
        model.getPopupWindow().setBackgroundDrawable(colorDrawable);

        if (onTouchListener != null) {
            model.getContentView().setOnTouchListener(onTouchListener);
        } else {
            model.getContentView().setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    model.getPopupWindow().dismiss();
                    return false;
                }
            });
        }

        if (onDismissListener != null) {
            model.getPopupWindow().setOnDismissListener(onDismissListener);
        } else {
            model.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
                public void onDismiss() {
                    MyUtils.setWindowBackground(context, 1f);
                }
            });
        }
        return model;
    }


    //popupwindow通用设置
    public static SetPopModel setBasePopupWindow(final Activity context, int id, View
            .OnTouchListener onTouchListener, PopupWindow.OnDismissListener onDismissListener) {
        View contentView = LayoutInflater.from(context).inflate(id, null);
        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        final SetPopModel model = new SetPopModel(popupWindow, contentView);

        //model.getPopupWindow().setAnimationStyle(R.anim.popup_showanim);
        model.getPopupWindow().setAnimationStyle(R.style.popwin_anim_style);
        model.getPopupWindow().setSoftInputMode(WindowManager.LayoutParams
                .SOFT_INPUT_ADJUST_RESIZE);
        model.getPopupWindow().setOutsideTouchable(true);
        model.getPopupWindow().setFocusable(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.argb(0, 0, 0, 0));
        model.getPopupWindow().setBackgroundDrawable(colorDrawable);

        if (onTouchListener != null) {
            model.getContentView().setOnTouchListener(onTouchListener);
        } else {
            model.getContentView().setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    model.getPopupWindow().dismiss();
                    return false;
                }
            });
        }

        if (onDismissListener != null) {
            model.getPopupWindow().setOnDismissListener(onDismissListener);
        } else {
            model.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
                public void onDismiss() {
                    MyUtils.setWindowBackground(context, 1f);
                }
            });
        }
        return model;
    }

    public static void show(PopupWindow pop, Activity context) {
        MyUtils.setWindowBackground(context, 0.4f);
        pop.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }


    public static class SetPopModel {
        PopupWindow PopupWindow;
        View contentView;
        int tag = 0;


        public SetPopModel(PopupWindow popupWindow, View contentView) {
            PopupWindow = popupWindow;
            this.contentView = contentView;
        }

        public PopupWindow getPopupWindow() {
            return PopupWindow;
        }

        public void setPopupWindow(PopupWindow popupWindow) {
            PopupWindow = popupWindow;
        }

        public View getContentView() {
            return contentView;
        }

        public void setContentView(View contentView) {
            this.contentView = contentView;
        }
    }

    public static class SetLiPopModel {
        MyPopupWindow PopupWindow;
        View contentView;
        int tag = 0;

        public SetLiPopModel(MyPopupWindow popupWindow, View contentView) {
            PopupWindow = popupWindow;
            this.contentView = contentView;
        }

        public MyPopupWindow getPopupWindow() {
            return PopupWindow;
        }

        public void setPopupWindow(MyPopupWindow popupWindow) {
            PopupWindow = popupWindow;
        }

        public View getContentView() {
            return contentView;
        }

        public void setContentView(View contentView) {
            this.contentView = contentView;
        }
    }

    public interface onClickCallBack {
        public void onPopClickCallBack(int id, int position, List<RecordPopModel> recordList);
    }


    public interface OnCityCallBack {
        public void onCityCallBack(String address, String city, String area);
    }

    public interface OnEdittextCallBack {
        public void OnEdittextCallBack(String txt);
    }

    public interface OnCallBack_int_string {
        public void onSexCallBack(int sextype, String sex);
    }

    public interface OnDateCallBack {
        public void onDateCallBack(String date);
    }
    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * @param isShareFriend true 分享到朋友，false分享到朋友圈
     */
    private static void share2Wx(boolean isShareFriend) {
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        WXImageObject imgObj = new WXImageObject(bitmap);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        bitmap.recycle();

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = isShareFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }


}
