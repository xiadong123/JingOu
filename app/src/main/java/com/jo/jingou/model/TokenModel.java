package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2016/12/2.
 */
public class TokenModel extends MyBaseModel {


    /**
     * access_token : 6f1661dbf0d6448a8d1c1f9cfd25fdb6
     * expires_in : 86400
     */

    private String access_token;
    private int expires_in;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }
}
