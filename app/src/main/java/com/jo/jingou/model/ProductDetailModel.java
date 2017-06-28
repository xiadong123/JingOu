package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/4/12.
 */
public class ProductDetailModel extends MyBaseModel {


    /**
     * Id : 22
     * Name : 尺码
     * Model : [{"ProductparameterId":19,"ProductparameterName":"M"}]
     */

    private List<List2Entity> list2;
    /**
     * ProductId : 60
     * ProductTypeId : 0
     * MainTitle : 九牧王西服
     * ListImgUrl : /assets/uploads/20170410/201704101853057632.png
     * Specifications : 123
     * Subtitle :
     * Introduction :
     * Amount : 0
     * Originalprice : 200.00
     * TotalPrice : null
     * Merchantsum : 0.0
     * SalesVolume : 30
     * Contents : &lt;p&gt;九牧王西服&lt;/p&gt;
     */

    private List<ListEntity> list;
    /**
     * ProductUpLoadId : 20
     * Url : /assets/uploads/20170410/2017041018543021239.jpg
     */

    private List<PhotolistEntity> photolist;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setList2(List<List2Entity> list2) {
        this.list2 = list2;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public void setPhotolist(List<PhotolistEntity> photolist) {
        this.photolist = photolist;
    }

    public List<List2Entity> getList2() {
        return list2;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public List<PhotolistEntity> getPhotolist() {
        return photolist;
    }

    public static class List2Entity {
        private int Id;
        private String Name;
        /**
         * ProductparameterId : 19
         * ProductparameterName : M
         */

        private List<ModelEntity> Model;

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setModel(List<ModelEntity> Model) {
            this.Model = Model;
        }

        public int getId() {
            return Id;
        }

        public String getName() {
            return Name;
        }

        public List<ModelEntity> getModel() {
            return Model;
        }

        public static class ModelEntity {
            private int ProductparameterId;
            private String ProductparameterName;

            public void setProductparameterId(int ProductparameterId) {
                this.ProductparameterId = ProductparameterId;
            }

            public void setProductparameterName(String ProductparameterName) {
                this.ProductparameterName = ProductparameterName;
            }

            public int getProductparameterId() {
                return ProductparameterId;
            }

            public String getProductparameterName() {
                return ProductparameterName;
            }
        }
    }

    public static class ListEntity {
        private int ProductId;
        private int ProductTypeId;
        private String MainTitle;
        private String ListImgUrl;
        private String Specifications;
        private String Subtitle;
        private String Introduction;
        private int Amount;
        private String Originalprice;
        private Object TotalPrice;
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

        public void setTotalPrice(Object TotalPrice) {
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

        public Object getTotalPrice() {
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

    public static class PhotolistEntity {
        private int ProductUpLoadId;
        private String Url;

        public void setProductUpLoadId(int ProductUpLoadId) {
            this.ProductUpLoadId = ProductUpLoadId;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public int getProductUpLoadId() {
            return ProductUpLoadId;
        }

        public String getUrl() {
            return Url;
        }
    }
}
