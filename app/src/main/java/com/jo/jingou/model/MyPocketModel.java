package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class MyPocketModel extends MyBaseModel {


    /**
     * total : 406016.28
     * profit : 0.0
     * distribution : 0.0
     * partner : 0.0
     * business : 406016.28
     * merchanorder : 0.0
     * refund : 0.0
     */

    private double total;
    private double profit;
    private double distribution;
    private double partner;
    private double business;
    private double merchanorder;
    private double refund;

    public void setTotal(double total) {
        this.total = total;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setDistribution(double distribution) {
        this.distribution = distribution;
    }

    public void setPartner(double partner) {
        this.partner = partner;
    }

    public void setBusiness(double business) {
        this.business = business;
    }

    public void setMerchanorder(double merchanorder) {
        this.merchanorder = merchanorder;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public double getTotal() {
        return total;
    }

    public double getProfit() {
        return profit;
    }

    public double getDistribution() {
        return distribution;
    }

    public double getPartner() {
        return partner;
    }

    public double getBusiness() {
        return business;
    }

    public double getMerchanorder() {
        return merchanorder;
    }

    public double getRefund() {
        return refund;
    }
}
