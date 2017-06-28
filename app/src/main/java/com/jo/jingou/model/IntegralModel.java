package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/4/20.
 */
public class IntegralModel extends MyBaseModel {


    /**
     * integral : 3100
     * list : [{"Type":1,"Integra":"100","Title":"积分兑换","ModifyDate":"2017-04-20 00:00:00"},{"Type":0,"Integra":"200","Title":"购物增加","ModifyDate":"2017-04-20 00:00:00"},{"Type":0,"Integra":"3000","Title":"购物增加","ModifyDate":"2017-04-20 00:00:00"}]
     */

    private int integral;
    /**
     * Type : 1
     * Integra : 100
     * Title : 积分兑换
     * ModifyDate : 2017-04-20 00:00:00
     */

    private List<ListEntity> list;

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getIntegral() {
        return integral;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int Type;
        private String Integra;
        private String Title;
        private String ModifyDate;

        public void setType(int Type) {
            this.Type = Type;
        }

        public void setIntegra(String Integra) {
            this.Integra = Integra;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public void setModifyDate(String ModifyDate) {
            this.ModifyDate = ModifyDate;
        }

        public int getType() {
            return Type;
        }

        public String getIntegra() {
            return Integra;
        }

        public String getTitle() {
            return Title;
        }

        public String getModifyDate() {
            return ModifyDate;
        }
    }
}
