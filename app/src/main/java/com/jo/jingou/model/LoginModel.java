package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2016/12/2.
 */
public class LoginModel extends MyBaseModel {

    /**
     * memberId : 5559
     * token : f6c6cc8a5bc241edab56ce4d5e55f73d
     * isFatherCode : false
     * isIdCard : false
     */

    private int memberId;
    private String token;
    private String isFatherCode;
    private String isIdCard;

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIsFatherCode(String isFatherCode) {
        this.isFatherCode = isFatherCode;
    }

    public void setIsIdCard(String isIdCard) {
        this.isIdCard = isIdCard;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getToken() {
        return token;
    }

    public String getIsFatherCode() {
        return isFatherCode;
    }

    public String getIsIdCard() {
        return isIdCard;
    }
}
