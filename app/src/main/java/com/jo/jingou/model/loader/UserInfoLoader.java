package com.jo.jingou.model.loader;

import com.jo.jingou.model.UserInfoModel;

/**
 * Created by dfyu on 2017/5/3.
 */
public class UserInfoLoader {

    public static UserInfoModel userInfoModel;

    public static void setUserInfoModel(UserInfoModel userInfoModel) {
        UserInfoLoader.userInfoModel = userInfoModel;
    }

    public static UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }
}
