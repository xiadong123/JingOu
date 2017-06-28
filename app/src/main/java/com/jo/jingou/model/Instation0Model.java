package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/5/4.
 */
public class Instation0Model extends MyBaseModel {

    /**
     * InstationId : 3167
     * Contents :
     * Modifydate : 2017-05-11 11:27:58
     * Ordermodel : [{"OrderId":6601,"OrderNumber":"201705111127580575","ProductId":62,"MainTitle":"耐克","Subtitle":"42","Introduction":"","ListImgUrl":"/assets/uploads/20170503/2017050315424363879.jpg","Amount":0,"Comment":0,"Originalprice":"","TotalPrice":"","Modifydate":"2017-05-11 11:27:58","OrderStates":"已过期","SpecificationName":"尺码","MerchantorderId":10594}]
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

        private List<OrdermodelEntity> Ordermodel;
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

        public void setOrdermodel(List<OrdermodelEntity> Ordermodel) {
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

        public List<OrdermodelEntity> getOrdermodel() {
            return Ordermodel;
        }

        public List<?> getBusinessmodel() {
            return Businessmodel;
        }

        public List<?> getWithdrawmodel() {
            return Withdrawmodel;
        }

        public static class OrdermodelEntity {
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
