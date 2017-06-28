package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2017/4/19.
 */
public class CartModel extends MyBaseModel {


    /**
     * List : [{"MainTitles":"啦啦啦","Postage":null,"Postages":0,"Model":[{"ProductId":60,"ProductTypeId":0,"MainTitle":"九牧王西服","ListImgUrl":"/assets/uploads/20170410/201704101853057632.png","Specifications":null,"Subtitle":null,"Introduction":null,"Amount":1,"Originalprice":"￥200.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null},{"ProductId":61,"ProductTypeId":0,"MainTitle":"达芙妮","ListImgUrl":"/assets/uploads/20170410/201704101855422052.jpg","Specifications":null,"Subtitle":null,"Introduction":null,"Amount":1,"Originalprice":"￥120.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null}]},{"MainTitles":"嘁嘁嘁","Postage":null,"Postages":0,"Model":[{"ProductId":62,"ProductTypeId":0,"MainTitle":"耐克","ListImgUrl":"/assets/uploads/20170410/2017041018573575248.jpg","Specifications":null,"Subtitle":null,"Introduction":null,"Amount":1,"Originalprice":"￥789.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null},{"ProductId":63,"ProductTypeId":0,"MainTitle":"阿迪达斯休闲运动服","ListImgUrl":"/assets/uploads/20170410/2017041018592518266.jpg","Specifications":null,"Subtitle":null,"Introduction":null,"Amount":1,"Originalprice":"￥963.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null}]},{"MainTitles":"啦啦啦2","Postage":null,"Postages":0,"Model":[{"ProductId":59,"ProductTypeId":0,"MainTitle":"西装1","ListImgUrl":"/assets/uploads/20170410/2017041018504641618.jpg","Specifications":null,"Subtitle":null,"Introduction":null,"Amount":1,"Originalprice":"￥100.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null}]}]
     * Total : 2172.00
     */

    private String Total;
    /**
     * MainTitles : 啦啦啦
     * Postage : null
     * Postages : 0.0
     * Model : [{"ProductId":60,"ProductTypeId":0,"MainTitle":"九牧王西服","ListImgUrl":"/assets/uploads/20170410/201704101853057632.png","Specifications":null,"Subtitle":null,"Introduction":null,"Amount":1,"Originalprice":"￥200.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null},{"ProductId":61,"ProductTypeId":0,"MainTitle":"达芙妮","ListImgUrl":"/assets/uploads/20170410/201704101855422052.jpg","Specifications":null,"Subtitle":null,"Introduction":null,"Amount":1,"Originalprice":"￥120.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null}]
     */

    private java.util.List<ListEntity> List;

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public void setList(java.util.List<ListEntity> List) {
        this.List = List;
    }

    public String getTotal() {
        return Total;
    }

    public java.util.List<ListEntity> getList() {
        return List;
    }


    public static class ListEntity {
        private String MainTitles;
        private Object Postage;
        private double Postages;
        /**
         * ProductId : 60
         * ProductTypeId : 0
         * MainTitle : 九牧王西服
         * ListImgUrl : /assets/uploads/20170410/201704101853057632.png
         * Specifications : null
         * Subtitle : null
         * Introduction : null
         * Amount : 1
         * Originalprice : ￥200.00
         * TotalPrice : null
         * Merchantsum : 0.0
         * SalesVolume : 0
         * Contents : null
         */

        private java.util.List<ModelEntity> Model;

        public void setMainTitles(String MainTitles) {
            this.MainTitles = MainTitles;
        }

        public void setPostage(Object Postage) {
            this.Postage = Postage;
        }

        public void setPostages(double Postages) {
            this.Postages = Postages;
        }

        public void setModel(java.util.List<ModelEntity> Model) {
            this.Model = Model;
        }

        public void setAllChecked(boolean isChecked) {
            for (ModelEntity modelEntity : Model) modelEntity.setIsChecked(isChecked);
        }

        public void setAllOpened(boolean isOpened) {
            for (ModelEntity modelEntity : Model) modelEntity.setIsOpened(isOpened);
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

        public java.util.List<ModelEntity> getModel() {
            return Model;
        }

        public boolean hasAllChecked() {
            for (ModelEntity modelEntity : Model)
                if (!modelEntity.isChecked)
                    return false;
            return true;
        }

        public static class ModelEntity {
            private int ProductId;
            private int ProductTypeId;
            private String MainTitle;
            private String ListImgUrl;
            private String Specifications;
            private String SpecificationName;
            private Object Subtitle;
            private Object Introduction;
            private int Amount;
            private String Originalprice;
            private Object TotalPrice;
            private double Merchantsum;
            private int SalesVolume;
            private Object Contents;
            private boolean isChecked;
            private boolean isOpened;
            private String Paraid;


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

            public void setSpecificationName(String SpecificationName) {
                this.SpecificationName = SpecificationName;
            }

            public void setSubtitle(Object Subtitle) {
                this.Subtitle = Subtitle;
            }

            public void setIntroduction(Object Introduction) {
                this.Introduction = Introduction;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }

            public void setOriginalprice(String Originalprice) {
                this.Originalprice = Originalprice;
            }

            public void setTotalPrice(Object TotalPrice) {
                this.TotalPrice = TotalPrice;
            }

            public void setMerchantsum(double Merchantsum) {
                this.Merchantsum = Merchantsum;
            }

            public void setSalesVolume(int SalesVolume) {
                this.SalesVolume = SalesVolume;
            }

            public void setContents(Object Contents) {
                this.Contents = Contents;
            }

            public void setIsChecked(boolean isChecked) {
                this.isChecked = isChecked;
            }

            public void setIsOpened(boolean isOpened) {
                this.isOpened = isOpened;
            }

            public void setParaid(String paraid) {
                this.Paraid = paraid;
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

            public String getSpecificationName() {
                return SpecificationName;
            }

            public Object getSubtitle() {
                return Subtitle;
            }

            public Object getIntroduction() {
                return Introduction;
            }

            public int getAmount() {
                return Amount;
            }

            public String getOriginalprice() {
                return Originalprice;
            }

            public Object getTotalPrice() {
                return TotalPrice;
            }

            public double getMerchantsum() {
                return Merchantsum;
            }

            public int getSalesVolume() {
                return SalesVolume;
            }

            public Object getContents() {
                return Contents;
            }

            public String getParaid() {
                return Paraid;
            }

            public boolean hasChecked() {
                return isChecked;
            }

            public boolean hasOpened() {
                return isOpened;
            }
        }
    }
}
