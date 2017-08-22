package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by Administrator on 2017/8/11.
 */

public class ApkModel extends MyBaseModel {


    /**
     * list : {"Id":1,"VerNum":"2.2.2","remark":"http://www.lytx8888.com/assets/APP/app-release.apk","AddDate":"2017-08-08 15:15:31","ModifyDate":"2017-08-08 15:15:31"}
     */

    private ListBean list;

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * Id : 1
         * VerNum : 2.2.2
         * remark : http://www.lytx8888.com/assets/APP/app-release.apk
         * AddDate : 2017-08-08 15:15:31
         * ModifyDate : 2017-08-08 15:15:31
         */

        private int Id;
        private String VerNum;
        private String remark;
        private String AddDate;
        private String ModifyDate;

        @Override
        public String toString() {
            return "ListBean{" +
                    "Id=" + Id +
                    ", VerNum='" + VerNum + '\'' +
                    ", remark='" + remark + '\'' +
                    ", AddDate='" + AddDate + '\'' +
                    ", ModifyDate='" + ModifyDate + '\'' +
                    '}';
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getVerNum() {
            return VerNum;
        }

        public void setVerNum(String VerNum) {
            this.VerNum = VerNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAddDate() {
            return AddDate;
        }

        public void setAddDate(String AddDate) {
            this.AddDate = AddDate;
        }

        public String getModifyDate() {
            return ModifyDate;
        }

        public void setModifyDate(String ModifyDate) {
            this.ModifyDate = ModifyDate;
        }
    }
}
