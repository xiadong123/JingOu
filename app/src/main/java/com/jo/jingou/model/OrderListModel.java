package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dfyu on 2016/12/12.
 */
public class OrderListModel extends MyBaseModel implements Serializable {


    /**
     * MerchantId : 2667
     * MerchantorderId : 10104
     * MerchantNum : 201703211749010747
     * MainTitles : 静欧平台
     * Piece : 1
     * Payable : 600.00
     * OrderState : 0
     * Modelm : [{"OrderId":6155,"OrderNumber":"201703211749010747","ProductId":54,"MainTitle":"洛阳醇","Subtitle":"洛阳醇","Introduction":"","ListImgUrl":"/assets/uploads/20170213/2017021313253367656.jpg","Amount":1,"Comment":0,"Originalprice":"600.00","TotalPrice":"600.00"}]
     */

    private List<ModelEntity> model;

    public void setModel(List<ModelEntity> model) {
        this.model = model;
    }

    public List<ModelEntity> getModel() {
        return model;
    }

    public static class ModelEntity implements Serializable {
        private int MerchantId;
        private int MerchantorderId;
        private String MerchantNum;
        private String Modifydate;
        private String MainTitles;
        private int Piece;
        private String Payable;
        private int OrderState;
        /**
         * OrderId : 6155
         * OrderNumber : 201703211749010747
         * ProductId : 54
         * MainTitle : 洛阳醇
         * Subtitle : 洛阳醇
         * Introduction :
         * ListImgUrl : /assets/uploads/20170213/2017021313253367656.jpg
         * Amount : 1
         * Comment : 0
         * Originalprice : 600.00
         * TotalPrice : 600.00
         */

        private List<ModelmEntity> Modelm;

        public String getModifydate() {
            return Modifydate;
        }

        public void setModifydate(String modifydate) {
            Modifydate = modifydate;
        }

        public void setMerchantId(int MerchantId) {
            this.MerchantId = MerchantId;
        }

        public void setMerchantorderId(int MerchantorderId) {
            this.MerchantorderId = MerchantorderId;
        }

        public void setMerchantNum(String MerchantNum) {
            this.MerchantNum = MerchantNum;
        }

        public void setMainTitles(String MainTitles) {
            this.MainTitles = MainTitles;
        }

        public void setPiece(int Piece) {
            this.Piece = Piece;
        }

        public void setPayable(String Payable) {
            this.Payable = Payable;
        }

        public void setOrderState(int OrderState) {
            this.OrderState = OrderState;
        }

        public void setModelm(List<ModelmEntity> Modelm) {
            this.Modelm = Modelm;
        }

        public int getMerchantId() {
            return MerchantId;
        }

        public int getMerchantorderId() {
            return MerchantorderId;
        }

        public String getMerchantNum() {
            return MerchantNum;
        }

        public String getMainTitles() {
            return MainTitles;
        }

        public int getPiece() {
            return Piece;
        }

        public String getPayable() {
            return Payable;
        }

        public int getOrderState() {
            return OrderState;
        }

        public List<ModelmEntity> getModelm() {
            return Modelm;
        }

        public static class ModelmEntity implements Serializable {
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
            private String Paraid;
            private String Modifydate;

            public String getModifydate() {
                return Modifydate;
            }

            public void setModifydate(String modifydate) {
                Modifydate = modifydate;
            }

            public void setParaid(String paraid) {
                Paraid = paraid;
            }

            public String getParaid() {
                return Paraid;
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
        }
    }
}
