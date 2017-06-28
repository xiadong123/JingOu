package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2016/12/16.
 */
public class PerticiListModel extends MyBaseModel {

    /**
     * list : [{"Text":"分红账号2651","Value":"2017.03.10"}]
     * sum : 99.99
     * balance : 100.0
     */

    private double sum;
    private double balance;
    /**
     * Text : 分红账号2651
     * Value : 2017.03.10
     */

    private List<ListEntity> list;

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public double getSum() {
        return sum;
    }

    public double getBalance() {
        return balance;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String Text;
        private String Value;

        public void setText(String Text) {
            this.Text = Text;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public String getText() {
            return Text;
        }

        public String getValue() {
            return Value;
        }
    }
}
