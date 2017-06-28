package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/5/11.
 */
public class Instation2Model extends MyBaseModel {


    /**
     * InstationId : 3194
     * Contents : 开店审核失败/r/n
     * Modifydate : 2017-05-15 18:19:49
     * Ordermodel : []
     * Businessmodel : [{"MemberId":2061,"BusinessApplyId":5,"StoreMobile":"11111111111","AlipayAccount":"1231231231","MerchantName":"111","State":"未通过","Modifydate":"2017-05-15 18:05:37"}]
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
        private List<OrdermodelEntity> Ordermodel;
        /**
         * MemberId : 2061
         * BusinessApplyId : 5
         * StoreMobile : 11111111111
         * AlipayAccount : 1231231231
         * MerchantName : 111
         * State : 未通过
         * Modifydate : 2017-05-15 18:05:37
         */

        private List<BusinessmodelEntity> Businessmodel;
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

        public void setOrdermodel(List<OrdermodelEntity> Ordermodel) {
            this.Ordermodel = Ordermodel;
        }

        public void setBusinessmodel(List<BusinessmodelEntity> Businessmodel) {
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

        public List<OrdermodelEntity> getOrdermodel() {
            return Ordermodel;
        }

        public List<BusinessmodelEntity> getBusinessmodel() {
            return Businessmodel;
        }

        public List<?> getWithdrawmodel() {
            return Withdrawmodel;
        }

        public static class BusinessmodelEntity {

            /**
             * MemberId : 2061
             * BusinessApplyId : 6
             * StoreMobile : 8788.8875
             * AlipayAccount : 4w56ww7w7w8
             * MerchantName : 国庆节快乐
             * State : 已通过
             * Remark :
             * Modifydate : 2017-05-15 18:21:07
             * Account : [{"Account":"13701657342","Password":"123456"}]
             */

            private int MemberId;
            private int BusinessApplyId;
            private String StoreMobile;
            private String AlipayAccount;
            private String MerchantName;
            private String State;
            private String Remark;
            private String Modifydate;
            /**
             * Account : 13701657342
             * Password : 123456
             */

            private List<AccountEntity> Account;

            public void setMemberId(int MemberId) {
                this.MemberId = MemberId;
            }

            public void setBusinessApplyId(int BusinessApplyId) {
                this.BusinessApplyId = BusinessApplyId;
            }

            public void setStoreMobile(String StoreMobile) {
                this.StoreMobile = StoreMobile;
            }

            public void setAlipayAccount(String AlipayAccount) {
                this.AlipayAccount = AlipayAccount;
            }

            public void setMerchantName(String MerchantName) {
                this.MerchantName = MerchantName;
            }

            public void setState(String State) {
                this.State = State;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public void setModifydate(String Modifydate) {
                this.Modifydate = Modifydate;
            }

            public void setAccount(List<AccountEntity> Account) {
                this.Account = Account;
            }

            public int getMemberId() {
                return MemberId;
            }

            public int getBusinessApplyId() {
                return BusinessApplyId;
            }

            public String getStoreMobile() {
                return StoreMobile;
            }

            public String getAlipayAccount() {
                return AlipayAccount;
            }

            public String getMerchantName() {
                return MerchantName;
            }

            public String getState() {
                return State;
            }

            public String getRemark() {
                return Remark;
            }

            public String getModifydate() {
                return Modifydate;
            }

            public List<AccountEntity> getAccount() {
                return Account;
            }

            public static class AccountEntity {
                private String Account;
                private String Password;

                public void setAccount(String Account) {
                    this.Account = Account;
                }

                public void setPassword(String Password) {
                    this.Password = Password;
                }

                public String getAccount() {
                    return Account;
                }

                public String getPassword() {
                    return Password;
                }
            }
        }


        public static class OrdermodelEntity {

            /**
             * OrderId : 6601
             * OrderNumber : 201705111127580575
             * ProductId : 62
             * MainTitle : 耐克
             * Subtitle : 42
             * Introduction :
             * ListImgUrl : /assets/uploads/20170503/2017050315424363879.jpg
             * Amount : 0
             * Comment : 0
             * Originalprice :
             * TotalPrice :
             * Modifydate : 2017-05-11 11:27:58
             * OrderStates : 已过期
             * SpecificationName : 尺码
             * MerchantorderId : 10594
             */

            private int OrderId;
            private String OrderNumber;
            private int ProductId;
            private String MainTitle;
            private String Subtitle;
            private String Introduction;
            private String ListImgUrl;
            private int Amount;
            private int Comment;
            private String Originalprice;
            private String TotalPrice;
            private String Modifydate;
            private String OrderStates;
            private String SpecificationName;
            private int MerchantorderId;
            private String Remark;
            private String State;

            public String getState() {
                return State;
            }

            public void setState(String state) {
                State = state;
            }

            public void setRemark(String remark) {
                Remark = remark;
            }

            public String getRemark() {
                return Remark;
            }

            public void setOrderId(int OrderId) {
                this.OrderId = OrderId;
            }

            public void setOrderNumber(String OrderNumber) {
                this.OrderNumber = OrderNumber;
            }

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public void setMainTitle(String MainTitle) {
                this.MainTitle = MainTitle;
            }

            public void setSubtitle(String Subtitle) {
                this.Subtitle = Subtitle;
            }

            public void setIntroduction(String Introduction) {
                this.Introduction = Introduction;
            }

            public void setListImgUrl(String ListImgUrl) {
                this.ListImgUrl = ListImgUrl;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }

            public void setComment(int Comment) {
                this.Comment = Comment;
            }

            public void setOriginalprice(String Originalprice) {
                this.Originalprice = Originalprice;
            }

            public void setTotalPrice(String TotalPrice) {
                this.TotalPrice = TotalPrice;
            }

            public void setModifydate(String Modifydate) {
                this.Modifydate = Modifydate;
            }

            public void setOrderStates(String OrderStates) {
                this.OrderStates = OrderStates;
            }

            public void setSpecificationName(String SpecificationName) {
                this.SpecificationName = SpecificationName;
            }

            public void setMerchantorderId(int MerchantorderId) {
                this.MerchantorderId = MerchantorderId;
            }

            public int getOrderId() {
                return OrderId;
            }

            public String getOrderNumber() {
                return OrderNumber;
            }

            public int getProductId() {
                return ProductId;
            }

            public String getMainTitle() {
                return MainTitle;
            }

            public String getSubtitle() {
                return Subtitle;
            }

            public String getIntroduction() {
                return Introduction;
            }

            public String getListImgUrl() {
                return ListImgUrl;
            }

            public int getAmount() {
                return Amount;
            }

            public int getComment() {
                return Comment;
            }

            public String getOriginalprice() {
                return Originalprice;
            }

            public String getTotalPrice() {
                return TotalPrice;
            }

            public String getModifydate() {
                return Modifydate;
            }

            public String getOrderStates() {
                return OrderStates;
            }

            public String getSpecificationName() {
                return SpecificationName;
            }

            public int getMerchantorderId() {
                return MerchantorderId;
            }
        }
    }
}
