package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class PocketdetailModel extends MyBaseModel {


    /**
     * available : 0.0
     * total : 0.0
     */

    private String available;
    private String total;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
