package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2016/12/2.
 */
public class RegisterModel extends MyBaseModel {

    /**
     * memberId : 5559
     * token : f6c6cc8a5bc241edab56ce4d5e55f73d
     */

    private int memberId;
    private String token;

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getToken() {
        return token;
    }
}
