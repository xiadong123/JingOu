package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/4/10.
 */
public class ProductTypeSonModel extends MyBaseModel {

    /**
     * list : [{"MainTitles":"西装","Postage":null,"Postages":0,"Model":[{"ProductId":56,"ProductTypeId":6,"MainTitle":"123","ListImgUrl":"/assets/uploads/20170410/201704101518528041.jpg","Specifications":"123","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"123.00","TotalPrice":"","Merchantsum":0,"SalesVolume":100,"Contents":"&lt;p&gt;服装123&lt;/p&gt;"},{"ProductId":57,"ProductTypeId":7,"MainTitle":"女装","ListImgUrl":"/assets/uploads/20170410/2017041015423720539.jpg","Specifications":"1","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"100.00","TotalPrice":"","Merchantsum":0,"SalesVolume":30,"Contents":"&lt;p&gt;女装1111&lt;/p&gt;"},{"ProductId":58,"ProductTypeId":8,"MainTitle":"紧身衣","ListImgUrl":"/assets/uploads/20170410/2017041015440783254.jpg","Specifications":"666","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"567.00","TotalPrice":"","Merchantsum":0,"SalesVolume":10,"Contents":"&lt;p&gt;紧身衣666&lt;/p&gt;"}]},{"MainTitles":"女装","Postage":null,"Postages":0,"Model":[{"ProductId":56,"ProductTypeId":6,"MainTitle":"123","ListImgUrl":"/assets/uploads/20170410/201704101518528041.jpg","Specifications":"123","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"123.00","TotalPrice":"","Merchantsum":0,"SalesVolume":100,"Contents":"&lt;p&gt;服装123&lt;/p&gt;"},{"ProductId":57,"ProductTypeId":7,"MainTitle":"女装","ListImgUrl":"/assets/uploads/20170410/2017041015423720539.jpg","Specifications":"1","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"100.00","TotalPrice":"","Merchantsum":0,"SalesVolume":30,"Contents":"&lt;p&gt;女装1111&lt;/p&gt;"},{"ProductId":58,"ProductTypeId":8,"MainTitle":"紧身衣","ListImgUrl":"/assets/uploads/20170410/2017041015440783254.jpg","Specifications":"666","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"567.00","TotalPrice":"","Merchantsum":0,"SalesVolume":10,"Contents":"&lt;p&gt;紧身衣666&lt;/p&gt;"}]},{"MainTitles":"运动装","Postage":null,"Postages":0,"Model":[{"ProductId":56,"ProductTypeId":6,"MainTitle":"123","ListImgUrl":"/assets/uploads/20170410/201704101518528041.jpg","Specifications":"123","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"123.00","TotalPrice":"","Merchantsum":0,"SalesVolume":100,"Contents":"&lt;p&gt;服装123&lt;/p&gt;"},{"ProductId":57,"ProductTypeId":7,"MainTitle":"女装","ListImgUrl":"/assets/uploads/20170410/2017041015423720539.jpg","Specifications":"1","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"100.00","TotalPrice":"","Merchantsum":0,"SalesVolume":30,"Contents":"&lt;p&gt;女装1111&lt;/p&gt;"},{"ProductId":58,"ProductTypeId":8,"MainTitle":"紧身衣","ListImgUrl":"/assets/uploads/20170410/2017041015440783254.jpg","Specifications":"666","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"567.00","TotalPrice":"","Merchantsum":0,"SalesVolume":10,"Contents":"&lt;p&gt;紧身衣666&lt;/p&gt;"}]}]
     * typeid : 3
     * page : 1
     */

    private int typeid;
    private int page;
    /**
     * MainTitles : 西装
     * Postage : null
     * Postages : 0.0
     * Model : [{"ProductId":56,"ProductTypeId":6,"MainTitle":"123","ListImgUrl":"/assets/uploads/20170410/201704101518528041.jpg","Specifications":"123","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"123.00","TotalPrice":"","Merchantsum":0,"SalesVolume":100,"Contents":"&lt;p&gt;服装123&lt;/p&gt;"},{"ProductId":57,"ProductTypeId":7,"MainTitle":"女装","ListImgUrl":"/assets/uploads/20170410/2017041015423720539.jpg","Specifications":"1","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"100.00","TotalPrice":"","Merchantsum":0,"SalesVolume":30,"Contents":"&lt;p&gt;女装1111&lt;/p&gt;"},{"ProductId":58,"ProductTypeId":8,"MainTitle":"紧身衣","ListImgUrl":"/assets/uploads/20170410/2017041015440783254.jpg","Specifications":"666","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"567.00","TotalPrice":"","Merchantsum":0,"SalesVolume":10,"Contents":"&lt;p&gt;紧身衣666&lt;/p&gt;"}]
     */

    private List<ListEntity> list;

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getTypeid() {
        return typeid;
    }

    public int getPage() {
        return page;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String MainTitles;
        private Object Postage;
        private double Postages;
        /**
         * ProductId : 56
         * ProductTypeId : 6
         * MainTitle : 123
         * ListImgUrl : /assets/uploads/20170410/201704101518528041.jpg
         * Specifications : 123
         * Subtitle :
         * Introduction :
         * Amount : 0
         * Originalprice : 123.00
         * TotalPrice :
         * Merchantsum : 0.0
         * SalesVolume : 100
         * Contents : &lt;p&gt;服装123&lt;/p&gt;
         */

        private List<ModelEntity> Model;

        public void setMainTitles(String MainTitles) {
            this.MainTitles = MainTitles;
        }

        public void setPostage(Object Postage) {
            this.Postage = Postage;
        }

        public void setPostages(double Postages) {
            this.Postages = Postages;
        }

        public void setModel(List<ModelEntity> Model) {
            this.Model = Model;
        }

        public String getMainTitles() {
            return MainTitles;
        }

        public Object getPostage() {
            return Postage;
        }

        public double getPostages() {
            return Postages;
        }

        public List<ModelEntity> getModel() {
            return Model;
        }

        public static class ModelEntity {
            private int ProductId;
            private int ProductTypeId;
            private String MainTitle;
            private String ListImgUrl;
            private String Specifications;
            private String Subtitle;
            private String Introduction;
            private int Amount;
            private String Originalprice;
            private String TotalPrice;
            private double Merchantsum;
            private int SalesVolume;
            private String Contents;

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public void setProductTypeId(int ProductTypeId) {
                this.ProductTypeId = ProductTypeId;
            }

            public void setMainTitle(String MainTitle) {
                this.MainTitle = MainTitle;
            }

            public void setListImgUrl(String ListImgUrl) {
                this.ListImgUrl = ListImgUrl;
            }

            public void setSpecifications(String Specifications) {
                this.Specifications = Specifications;
            }

            public void setSubtitle(String Subtitle) {
                this.Subtitle = Subtitle;
            }

            public void setIntroduction(String Introduction) {
                this.Introduction = Introduction;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }

            public void setOriginalprice(String Originalprice) {
                this.Originalprice = Originalprice;
            }

            public void setTotalPrice(String TotalPrice) {
                this.TotalPrice = TotalPrice;
            }

            public void setMerchantsum(double Merchantsum) {
                this.Merchantsum = Merchantsum;
            }

            public void setSalesVolume(int SalesVolume) {
                this.SalesVolume = SalesVolume;
            }

            public void setContents(String Contents) {
                this.Contents = Contents;
            }

            public int getProductId() {
                return ProductId;
            }

            public int getProductTypeId() {
                return ProductTypeId;
            }

            public String getMainTitle() {
                return MainTitle;
            }

            public String getListImgUrl() {
                return ListImgUrl;
            }

            public String getSpecifications() {
                return Specifications;
            }

            public String getSubtitle() {
                return Subtitle;
            }

            public String getIntroduction() {
                return Introduction;
            }

            public int getAmount() {
                return Amount;
            }

            public String getOriginalprice() {
                return Originalprice;
            }

            public String getTotalPrice() {
                return TotalPrice;
            }

            public double getMerchantsum() {
                return Merchantsum;
            }

            public int getSalesVolume() {
                return SalesVolume;
            }

            public String getContents() {
                return Contents;
            }
        }
    }
}
