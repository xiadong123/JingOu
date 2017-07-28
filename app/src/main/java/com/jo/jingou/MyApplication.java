package com.jo.jingou;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.utils.Constant;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by dfyu on 2015/12/1.
 * /*
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\  =  /O
 * ____/`---'\____
 * .'  \\|     |//  `.
 * /  \\|||  :  |||//  \
 * /  _||||| -:- |||||-  \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |   |
 * \  .-\__  `-`  ___/-. /
 * ___`. .'  /--.--\  `. . __
 * ."" '<  `.___\_<|>_/___.'  >'"".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 佛祖保佑       永无BUG
 */

public class MyApplication extends Application {

    static Context context;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;


    {
        PlatformConfig.setWeixin("wxbd3e6bba8efbae73 ", "dc7dcb58f1369ea84e16dc20d49209734824727d");
        PlatformConfig.setQQZone("1105888490", "xHo9RkB8RDJDJnyk");
        PlatformConfig.setSinaWeibo("1238457414", "8f9bd71d9be08a5d7e73eb77132425ae", "http://sns.whalecloud.com/sina2/callback");
        //Config.REDIRECT_URL = "";
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        sharedPreferences = getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE); // 私有数据
        editor = sharedPreferences.edit();// 获取编辑器

        UMShareAPI.get(this);

//        Bugtags.start("bdfe328d4a72a6822d715e9f79d54cbb", this, Bugtags.BTGInvocationEventBubble);
//        UMShareAPI.get(this);

        PushAgent mPushAgent = PushAgent.getInstance(this);
//
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage msg) {

                Log.i("TAG", "msg.extra.get(\"key1\")======" + msg.extra.get("key1"));

                switch (msg.extra.get("key1")) {
                    case "1":
                        MessageLoader.setMessage(0, MessageLoader.getMessageNumber(0) + 1);
                        break;
                    case "2":
                        MessageLoader.setMessage(1, MessageLoader.getMessageNumber(1) + 1);
                        break;
                    case "3":
                        MessageLoader.setMessage(2, MessageLoader.getMessageNumber(2) + 1);
                        break;
                    case "4":
                        MessageLoader.setMessage(3, MessageLoader.getMessageNumber(3) + 1);
                        break;
                }


                Notification.Builder builder = new Notification.Builder(context);
                builder.setContentTitle(msg.title)
                        .setContentText(msg.text)
                        .setSmallIcon(getSmallIconId(context, msg))
                        .setTicker(msg.ticker)
                        .setAutoCancel(true);

                return builder.getNotification();
            }
        };
        mPushAgent.setMessageHandler(messageHandler);


//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                setDeviceToken(deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

        mPushAgent.onAppStart();


    }

    public static Context getApplication() {
        return context;
    }


    //登录状态
    // 0：未登录
    // 1：已登录
    public static String getLogin_type() {
        return getCache("login_type", "0");
    }

    public static void setLogin_type(String login_type) {
        setCache("login_type", login_type);
    }


    //接口权限token
    public static String getToken() {
        return getCache("token", "");
    }

    public static void setToken(String token) {
        setCache("token", token);
    }

    //异地登录token
    public static String getSoleToken() {
        return getCache("soletoken", "");
    }

    public static void setSoleToken(String soletoken) {
        setCache("soletoken", soletoken);
    }

    //设备token
    public static String getDeviceToken() {
        return getCache("devicetoken", "null");
    }

    public static void setDeviceToken(String devicetoken) {
        setCache("devicetoken", devicetoken);
    }


    //用户id
    public static String getUser_id() {
        return getCache("user_id", "1");
    }

    public static void setUser_id(String user_id) {
        setCache("user_id", user_id);
    }


    //是否绑定邀请码
    public static String getIsFatherCode() {
        return getCache("is_father_code", "false");
    }

    public static void setIsFatherCode(String is_father_code) {
        setCache("is_father_code", is_father_code);
    }

    //是否绑定身份证
    public static String getIsIdCode() {
        return getCache("is_id_code", "false");
    }

    public static void setIsIdCode(String is_id_code) {
        setCache("is_id_code", is_id_code);
    }


    //保存账户
    public String getUserName() {
        return getCache("UserName", "null");
    }

    public void setUserName(String UserName) {
        setCache("UserName", UserName);
    }

    //昵称
    public String getReal_name() {
        return getCache("real_name", "null");
    }

    public void setReal_name(String real_name) {
        setCache("real_name", real_name);
    }

    //头像地址
    public String getHead_pic() {
        return getCache("head_pic", "null");
    }

    public void setHead_pic(String head_pic) {
        setCache("head_pic", head_pic);
    }

    //是否有新消息
    public String getHasNewMessage() {
        return getCache("new_message", "0");
    }

    public void setHasNewMessage(String new_message) {
        setCache("new_message", new_message);
        Log.i("TAG", "getHasNewMessage === " + getHasNewMessage());

    }

    //是否首次使用
    public String getIsFirstUse() {
        return getCache("isfirstuse", "0");
    }

    public void setIsFirstUse(String isfirstuse) {
        setCache("isfirstuse", isfirstuse);
    }


    private static void setCache(String key, String text) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE); // 私有数据
        if (editor == null) editor = sharedPreferences.edit();// 获取编辑器

        editor.putString(key, text);
        editor.commit();
    }

    private static String getCache(String key, String def) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE); // 私有数据
        return sharedPreferences.getString(key, def);
    }
}
