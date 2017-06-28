package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2017/4/21.
 */
public class ShopNumberModel extends MyBaseModel {


    /**
     * productid : 61
     * amount : 3
     * propara : 5
     * para : 36
     * paraname : 尺码
     */

    private int productid;
    private int amount;
    private String propara;
    private String para;
    private String paraname;

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPropara(String propara) {
        this.propara = propara;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public void setParaname(String paraname) {
        this.paraname = paraname;
    }

    public int getProductid() {
        return productid;
    }

    public int getAmount() {
        return amount;
    }

    public String getPropara() {
        return propara;
    }

    public String getPara() {
        return para;
    }

    public String getParaname() {
        return paraname;
    }
}
