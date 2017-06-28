package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/5/11.
 */
public class Instation32Model extends MyBaseModel {

    /**
     * InstationId : 3120
     * Contents : +￥10
     * Modifydate : 2017-05-04 19:44:35
     * Ordermodel : []
     * Businessmodel : []
     * Withdrawmodel : []
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int InstationId;
        private String Contents;
        private String Modifydate;
        private List<?> Ordermodel;
        private List<?> Businessmodel;
        private List<?> Withdrawmodel;

        public void setInstationId(int InstationId) {
            this.InstationId = InstationId;
        }

        public void setContents(String Contents) {
            this.Contents = Contents;
        }

        public void setModifydate(String Modifydate) {
            this.Modifydate = Modifydate;
        }

        public void setOrdermodel(List<?> Ordermodel) {
            this.Ordermodel = Ordermodel;
        }

        public void setBusinessmodel(List<?> Businessmodel) {
            this.Businessmodel = Businessmodel;
        }

        public void setWithdrawmodel(List<?> Withdrawmodel) {
            this.Withdrawmodel = Withdrawmodel;
        }

        public int getInstationId() {
            return InstationId;
        }

        public String getContents() {
            return Contents;
        }

        public String getModifydate() {
            return Modifydate;
        }

        public List<?> getOrdermodel() {
            return Ordermodel;
        }

        public List<?> getBusinessmodel() {
            return Businessmodel;
        }

        public List<?> getWithdrawmodel() {
            return Withdrawmodel;
        }
    }
}
