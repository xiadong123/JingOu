package com.jo.jingou.base;

/**
 * Created by dfyu on 2016/8/29.
 */
public class MyBaseModel {
    /**
     * errcode : 0
     * errmsg : 请求成功
     */

    private int errcode = -2;
    private String errmsg;

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
