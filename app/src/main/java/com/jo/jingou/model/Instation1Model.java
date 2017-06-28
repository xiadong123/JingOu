package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/5/11.
 */
public class Instation1Model extends MyBaseModel {


    /**
     * InstationId : 3171
     * Contents : 0
     * Modifydate : 2017-05-11 11:35:23
     * Ordermodel : []
     * Businessmodel : []
     * Withdrawmodel : [{"WithdrawId":1354,"Money":0,"Bank":"中国银行","Modifydate":"2017-05-11 11:35:23","AddDate":"2017-05-11 11:35:23"}]
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
        /**
         * WithdrawId : 1354
         * Money : 0.0
         * Bank : 中国银行
         * Modifydate : 2017-05-11 11:35:23
         * AddDate : 2017-05-11 11:35:23
         */

        private List<WithdrawmodelEntity> Withdrawmodel;

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

        public void setWithdrawmodel(List<WithdrawmodelEntity> Withdrawmodel) {
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

        public List<WithdrawmodelEntity> getWithdrawmodel() {
            return Withdrawmodel;
        }

        public static class WithdrawmodelEntity {
            private int WithdrawId;
            private double Money;
            private String Bank;
            private String Modifydate;
            private String AddDate;

            public void setWithdrawId(int WithdrawId) {
                this.WithdrawId = WithdrawId;
            }

            public void setMoney(double Money) {
                this.Money = Money;
            }

            public void setBank(String Bank) {
                this.Bank = Bank;
            }

            public void setModifydate(String Modifydate) {
                this.Modifydate = Modifydate;
            }

            public void setAddDate(String AddDate) {
                this.AddDate = AddDate;
            }

            public int getWithdrawId() {
                return WithdrawId;
            }

            public double getMoney() {
                return Money;
            }

            public String getBank() {
                return Bank;
            }

            public String getModifydate() {
                return Modifydate;
            }

            public String getAddDate() {
                return AddDate;
            }
        }
    }
}
