package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2017/5/9.
 */
public class ShoppingDModel extends MyBaseModel {

    /**
     * propara : 4,
     * para : 37,
     * paraname : 尺码,
     */

    private String propara;
    private String para;
    private String paraname;
    /**
     * productid : 59
     */

    private String productid;

    public void setPropara(String propara) {
        this.propara = propara;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public void setParaname(String paraname) {
        this.paraname = paraname;
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

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductid() {
        return productid;
    }
}
