package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/5/18.
 */
public class ReturnListModel extends MyBaseModel {


    /**
     * ReturnsId : 4
     * ProductId : 61
     * Money : 120.00
     * Productname : 达芙妮
     * ListImgUrl : /assets/uploads/20170410/201704101855422052.jpg
     * Application : 0
     * Number : 1
     * Merchantname : 静欧平台
     * ProductMoney : 120.00
     * SpecificationName : 尺码
     * Specification : 39
     */

    private List<ModelEntity> model;

    public void setModel(List<ModelEntity> model) {
        this.model = model;
    }

    public List<ModelEntity> getModel() {
        return model;
    }

    public static class ModelEntity {
        private int ReturnsId;
        private int ProductId;
        private String Money;
        private String Productname;
        private String ListImgUrl;
        private int Application;
        private int Number;
        private String Merchantname;
        private String ProductMoney;
        private String SpecificationName;
        private String Specification;
        private double SinglePrice;

        public double getSinglePrice() {
            return SinglePrice;
        }

        public void setSinglePrice(double singlePrice) {
            SinglePrice = singlePrice;
        }

        public void setReturnsId(int ReturnsId) {
            this.ReturnsId = ReturnsId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }

        public void setProductname(String Productname) {
            this.Productname = Productname;
        }

        public void setListImgUrl(String ListImgUrl) {
            this.ListImgUrl = ListImgUrl;
        }

        public void setApplication(int Application) {
            this.Application = Application;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public void setMerchantname(String Merchantname) {
            this.Merchantname = Merchantname;
        }

        public void setProductMoney(String ProductMoney) {
            this.ProductMoney = ProductMoney;
        }

        public void setSpecificationName(String SpecificationName) {
            this.SpecificationName = SpecificationName;
        }

        public void setSpecification(String Specification) {
            this.Specification = Specification;
        }

        public int getReturnsId() {
            return ReturnsId;
        }

        public int getProductId() {
            return ProductId;
        }

        public String getMoney() {
            return Money;
        }

        public String getProductname() {
            return Productname;
        }

        public String getListImgUrl() {
            return ListImgUrl;
        }

        public int getApplication() {
            return Application;
        }

        public int getNumber() {
            return Number;
        }

        public String getMerchantname() {
            return Merchantname;
        }

        public String getProductMoney() {
            return ProductMoney;
        }

        public String getSpecificationName() {
            return SpecificationName;
        }

        public String getSpecification() {
            return Specification;
        }
    }
}
