package com.jo.jingou.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.google.gson.Gson;
import com.jo.jingou.MyApplication;
import com.jo.jingou.activity.ImageListActivity;
import com.jo.jingou.activity.Login_Register_ResetPasswordActivity;
import com.jo.jingou.activity.MainActivity;
import com.jo.jingou.activity.OrderActivity;
import com.jo.jingou.activity.OrderCommentActivity;
import com.jo.jingou.activity.PayActivity;
import com.jo.jingou.model.Area2Model;
import com.jo.jingou.model.AreaModel;
import com.jo.jingou.model.SelectModel;
import com.jo.jingou.utils.pinyinpaixu.PinyinComparator;
import com.jo.jingou.utils.pinyinpaixu.PinyinComparator2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import caesar.feng.framework.utils.Utility;

/**
 * Created by dfyu on 2016/8/29.
 */
public class MyUtils {


    //解析地区json
    public static AreaModel formatAreaJson(Context context, Handler handler) {
        AreaModel areaModel = null;
        try {
            String str = "";
            String temp = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("area.json"),
                    "UTF-8"));
            while (br != null && null != (temp = br.readLine())) str += temp;

            Gson gson = new Gson();
            areaModel = gson.fromJson(str, AreaModel.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.obtainMessage(0).sendToTarget();
        return areaModel;
    }


    //解析地区json
    public static Area2Model formatArea2Json(Context context, Handler handler) {
        Area2Model area2Model = null;
        try {
            String str = "";
            String temp = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("area2.json"),
                    "UTF-8"));
            while (br != null && null != (temp = br.readLine())) str += temp;

            Gson gson = new Gson();
            area2Model = gson.fromJson(str, Area2Model.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.obtainMessage(0).sendToTarget();
        return area2Model;
    }


    //为NumberPicker设置数组
    public static void setDisplayedValuesForPicker(NumberPicker picker, String[] data) {
        if (data.length - 1 > picker.getMaxValue()) {
            picker.setDisplayedValues(data);
            picker.setMaxValue(data.length - 1);
        } else {
            picker.setMaxValue(data.length - 1);
            picker.setDisplayedValues(data);
        }

    }


    //通用进入评价页面方法
    public static void commonORDERCOMMENT(Activity context, String productid, String merchantNum, String imgurl,
                                          String product_name, String price, String count) {
        Intent i = new Intent(context, OrderCommentActivity.class);
        i.putExtra("productId", productid);
        i.putExtra("merchantNum", merchantNum);
        i.putExtra("imgurl", imgurl);
        i.putExtra("product_name", product_name);
        i.putExtra("price", price);
        i.putExtra("count", count);
        context.startActivity(i);
    }

    //通用进入订单页面方法
    public static void commonBUY(Activity context, String productid, String much, String productpara, String address,
                                 int requestCode) {
        Intent i = new Intent(context, OrderActivity.class);
        //i.putExtra(Constant.BYTYPE, "fromMain");
        i.putExtra("ProductId", productid);
        i.putExtra("Much", much);
        i.putExtra("ProductPara", productpara);
        i.putExtra("Address", address);
        context.startActivityForResult(i, requestCode);
    }

    //通用进入订单页面方法
    public static void commonBUY2(Activity context, String productid, String much, String productpara, String
            address, int requestCode) {
        Intent i = new Intent(context, OrderActivity.class);
        //i.putExtra(Constant.BYTYPE, "fromMain");
        i.putExtra("ProductId", productid);
        i.putExtra("Much", much);
        i.putExtra("ProductPara", productpara);
        i.putExtra("Address", address);
        i.putExtra("isfromcart", true);
        context.startActivityForResult(i, requestCode);
    }


    //通用进入付款页面方法
    public static void commonPAY(Activity context, String orderid, String ordernum, double money, long time, int
            requestCode) {
        Intent i = new Intent(context, PayActivity.class);
        i.putExtra("orderid", orderid);
        i.putExtra("ordernum", ordernum);
        i.putExtra("money", money);
        i.putExtra("time", time);
        context.startActivity(i);
    }

    //通用登出方法
    public static void onLoginOut(Context context) {
        MyApplication.setLogin_type("0");
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }


    //跳转登录页
    public static void commonToLogin(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("是否前往登录？");
        builder.setTitle("提示");
        builder.setPositiveButton("前往登录", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent i = new Intent(context, Login_Register_ResetPasswordActivity.class);
                i.putExtra(Constant.BYTYPE, "bylogin");
                context.startActivity(i);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    public static int CanBuyAngle() {
        if (isLogin() && isFatherCode() && isIdCode()) {
            return 0;
        } else if (!isLogin()) {
            return 1;
        } else if (isLogin() && !isFatherCode() && !isIdCode()) {
            return 2;
        } else if (isLogin() && !isFatherCode() && isIdCode()) {
            return 3;
        } else if (isLogin() && isFatherCode() && !isIdCode()) {
            return 4;
        } else {
            return 5;
        }
    }

    //是否登录
    public static boolean isLogin() {
        return "1".equals(MyApplication.getLogin_type());
    }

    //是否绑定邀请码
    public static boolean isFatherCode() {
        return !"false".equals(MyApplication.getIsFatherCode());
    }

    //是否实名认证
    public static boolean isIdCode() {
        return !"false".equals(MyApplication.getIsIdCode());
    }


    //设置当前登录状态
    public static void setLogin(String isLogin, String soleToken, String user_Id, String IsFatherCode, String
            IsIdCode) {
        MyApplication.setLogin_type(isLogin);
        if ("1".equals(isLogin)) {
            MyApplication.setSoleToken(soleToken);
            MyApplication.setUser_id(user_Id);
            MyApplication.setIsFatherCode(IsFatherCode);
            MyApplication.setIsIdCode(IsIdCode);
        } else if ("0".equals(isLogin)) {
            MyApplication.setSoleToken("null");
            MyApplication.setUser_id("1");
            MyApplication.setIsFatherCode("false");
            MyApplication.setIsIdCode("false");
        }
    }


    // 从相册得到的url转换为SD卡中图片路径
    public static String getPath(Context context, Uri uri) {
        if (uri.getAuthority().isEmpty())
            return null;

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        return path;
    }


    //加密存储账号密码
    public static void setUnameAndPwdCacheByAES(Context context, String name, String pwd) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context
                .MODE_PRIVATE); // 私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();// 获取编辑器

        String ciphertext_name = Util_AES.encode(name);
        editor.putString("tel", ciphertext_name);

        String ciphertext_pwd = Util_AES.encode(pwd);
        editor.putString("password", ciphertext_pwd);

        editor.commit();// 提交修改
    }


    //解密取出账号密码
    public static HashMap<String, String> getUnameAndPwdCacheByAES(Context context) {
        String username, password;
        HashMap<String, String> map = null;
        SharedPreferences sPreferences = context.getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context
                .MODE_PRIVATE);
        if (!"".equals(sPreferences.getString("tel", ""))
                && !"".equals(sPreferences.getString("password", ""))) {
            username = Util_AES.decode(sPreferences.getString("tel", ""));
            password = Util_AES.decode(sPreferences.getString("password", ""));
            map = new HashMap<String, String>();
            map.put("username", username);
            map.put("password", password);
            return map;
        } else {
            return map;
        }
    }


    //注册广播接受器
    public static void setMyBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, String[] actions) {
        IntentFilter filter = new IntentFilter();
        for (String action : actions) filter.addAction(action);
        context.registerReceiver(broadcastReceiver, filter);
    }


    //发送广播
    public static void sendMyBroadcast(Context context, String action) {
        Intent i = new Intent();
        i.setPackage("com.icollege.world");
        i.setAction(action);
        context.sendBroadcast(i);
    }


    //发送广播
    public static void sendMyBroadcast(Context context, String action, String[] keys, String[] values) {
        Intent i = new Intent();
        i.setAction(action);
        for (int j = 0; j < keys.length; j++) i.putExtra(keys[j], values[j]);
        context.sendBroadcast(i);
    }


    //简单跳转
    public static void setAndStartIntent(Context context, Class<?> toClass) {
        Intent i = new Intent(context, toClass);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(i);
    }

    //简单跳转forResult
    public static void setAndStartIntentForResult(Activity context, Class<?> toClass, int requestCode) {
        Intent i = new Intent(context, toClass);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivityForResult(i, requestCode);
    }

    //简单跳转 加参数
    public static void setAndStartIntent(Context context, Class<?> toClass, String[] keys, String[] values) {
        Intent i = new Intent(context, toClass);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        for (int j = 0; j < keys.length; j++) i.putExtra(keys[j], values[j]);
        context.startActivity(i);
    }


    //设置多个view显示或隐藏
    public static void setViewsVorG(int VorG, View... views) {
        if (views.length != 0) for (View v : views) if (v != null) v.setVisibility(VorG);
    }


    //系统状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    public static Context getContext() {
        return MyApplication.getApplication();
    }

    //判断是否空值
    public static boolean hasValue(String text) {

        return text != null && text.length() > 0 && !"null".equals(text);
    }


    public static void setWindowBackground(Activity context, float alpha) {
        Window window = context.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = alpha;
        window.setAttributes(lp);
    }


    public static void callPhone(Activity context, String phonenumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phonenumber);
        intent.setData(data);
        context.startActivity(intent);
    }


    public static Typeface getTypeface(Activity context) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fortemt.ttf");
        return typeFace;
    }


    /**
     * 隐藏系统UI
     */
    @SuppressLint("NewApi")
    public static void hideSystemUI(View view) {
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 显示系统UI
     */
    @SuppressLint("NewApi")
    public static void showSystemUI(View view) {
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static File saveFile(Bitmap bm, String fileName) throws IOException {
        String path = Utility.getSDPath() + "/" + Constant.PACKAGENAME + "/";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }


    /**
     * 检测网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
     */

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (hasValue(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    //设置TabLayout Matgin 或者Padding
    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams
                    .MATCH_PARENT, 1);
            params.leftMargin = leftDip;
            params.rightMargin = rightDip;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    // 按拼音排序
    public static List<SelectModel.ListEntity.ModelEntity> comparatorByPinyin(List<SelectModel.ListEntity
            .ModelEntity> list) {
        Util_CharacterParser characterParser = Util_CharacterParser.getInstance();
        List<SelectModel.ListEntity.ModelEntity> SourceDateList = new ArrayList<>();
        PinyinComparator pinyinComparator = new PinyinComparator();

        for (SelectModel.ListEntity.ModelEntity modelEntity : list) {
            String t = modelEntity.getProductparameterName().substring(0, 1);
            modelEntity.setSortLetters(characterParser.convert(t));
            SourceDateList.add(modelEntity);
        }
        Collections.sort(SourceDateList, pinyinComparator);
        return SourceDateList;
    }


    // 按拼音排序2
    public static List<Area2Model.AreaListEntity> comparatorByPinyin2(List<Area2Model.AreaListEntity> list) {
        PinyinComparator2 pinyinComparator2 = new PinyinComparator2();
        Collections.sort(list, pinyinComparator2);
        return list;
    }

    // 截去末尾的字符“，”
    public static String subStringEnd(String txt, String end) {
        if (hasValue(txt) && txt.endsWith(end)) ;
        txt = txt.substring(0, txt.length() - end.length());
        return txt;
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = MyApplication.getApplication().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    //图片activity通用跳转
    public static void commonToImageListActivityByUrl(Activity context, View view, String imgurl) {
        Intent intent = new Intent(context, ImageListActivity.class);
        //传入选中的Item对应的ID
        intent.putExtra(ImageListActivity.EXTRA_PARAM_ID, view.getId());
        intent.putExtra("imgtype", "imgurl");
        intent.putExtra("imgurl", imgurl);

        //主要的语句
        //通过makeSceneTransitionAnimation传入多个Pair
        //每个Pair将一个当前Activity的View和目标Activity中的一个Key绑定起来
        //在目标Activity中会调用这个Key
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context,
                new Pair<View, String>(view,
                        ImageListActivity.VIEW_NAME_HEADER_IMAGE));

        // ActivityCompat是android支持库中用来适应不同android版本的
        ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
    }

    //图片activity通用跳转
    public static void commonToImageListActivityByNoHostUrl(Activity context, View view, String imgurl) {
        Intent intent = new Intent(context, ImageListActivity.class);
        //传入选中的Item对应的ID
        intent.putExtra(ImageListActivity.EXTRA_PARAM_ID, view.getId());
        intent.putExtra("imgtype", "mybussiness");
        intent.putExtra("imgurl", imgurl);

        //主要的语句
        //通过makeSceneTransitionAnimation传入多个Pair
        //每个Pair将一个当前Activity的View和目标Activity中的一个Key绑定起来
        //在目标Activity中会调用这个Key
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context,
                new Pair<View, String>(view,
                        ImageListActivity.VIEW_NAME_HEADER_IMAGE));

        // ActivityCompat是android支持库中用来适应不同android版本的
        ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
    }

    //获取权限
    public static boolean getPermission(Activity activity, String... strings) {
        ArrayList<String> permissionlist = new ArrayList<>();
        String[] permissions;

        for (String permission : strings) {
            if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                permissionlist.add(permission);
            }
        }

        permissions = permissionlist.toArray(new String[permissionlist.size()]);
        if (permissions != null && permissions.length != 0) {
            ActivityCompat.requestPermissions(activity, permissions, 0);
            Utility.showToast(activity, "缺少相应权限，请去系统设置内添加～");
            return false;
        } else {
            return true;
        }
    }


}
