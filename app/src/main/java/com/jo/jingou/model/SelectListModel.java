package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/4/11.
 */
public class SelectListModel extends MyBaseModel {

    /**
     * total : 3
     * pageSize : 20
     * page : 1
     * list : [{"ProductId":60,"ProductTypeId":0,"MainTitle":"九牧王西服","ListImgUrl":"/assets/uploads/20170410/201704101853057632.png","Specifications":"123","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"200.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null},{"ProductId":59,"ProductTypeId":0,"MainTitle":"西装1","ListImgUrl":"/assets/uploads/20170410/2017041018504641618.jpg","Specifications":"123","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"100.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null},{"ProductId":56,"ProductTypeId":0,"MainTitle":"阿玛尼","ListImgUrl":"/assets/uploads/20170410/201704101518528041.jpg","Specifications":"123","Subtitle":"","Introduction":"","Amount":0,"Originalprice":"123.00","TotalPrice":null,"Merchantsum":0,"SalesVolume":0,"Contents":null}]
     */

    private int total;
    private int pageSize;
    private int page;
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
     * SalesVolume : 0
     * Contents : null
     */

    private List<ListEntity> list;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }

    public List<ListEntity> getList() {
        return list;
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
        private Object Contents;

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

        public void setContents(Object Contents) {
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

        public Object getContents() {
            return Contents;
        }
    }
}
