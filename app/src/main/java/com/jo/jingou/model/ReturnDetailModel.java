package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/5/18.
 */
public class ReturnDetailModel extends MyBaseModel {


    /**
     * ReturnsId : 15
     * MemberId : 480879
     * Number : 2
     * Type : 1
     * Receiving : 0
     * Application : 0
     * MerchantId : 1
     * Productname : 会稽山绍兴黄酒50年陈兰亭序 镇店之宝
     * Reason : 7天无理由退换货
     * Explain : 9
     * ProductMoney : 48264.00
     * Money : 48264.00
     * Name : 疯鱼
     * Reply :
     * AddDate : 2017-06-02 11:09:05
     * Anonymous : 0
     * Merchantname : 静欧平台
     * ModifyDate : 2017-06-02 11:09:05
     * Specification :
     * SpecificationName :
     */

    private List<ModelEntity> model;
    /**
     * Url : /assets/uploads/photo/20170602/2017060211090606168.jpg
     */

    private List<ModelphotoEntity> modelphoto;

    public void setModel(List<ModelEntity> model) {
        this.model = model;
    }

    public void setModelphoto(List<ModelphotoEntity> modelphoto) {
        this.modelphoto = modelphoto;
    }

    public List<ModelEntity> getModel() {
        return model;
    }

    public List<ModelphotoEntity> getModelphoto() {
        return modelphoto;
    }

    public static class ModelEntity {
        private int ReturnsId;
        private int MemberId;
        private int Number;
        private int Type;
        private int Receiving;
        private int Application;
        private int MerchantId;
        private String Productname;
        private String Reason;
        private String Explain;
        private String ProductMoney;
        private String Money;
        private String Name;
        private String Reply;
        private String AddDate;
        private int Anonymous;
        private String Merchantname;
        private String ModifyDate;
        private String Specification;
        private String SpecificationName;

        public void setReturnsId(int ReturnsId) {
            this.ReturnsId = ReturnsId;
        }

        public void setMemberId(int MemberId) {
            this.MemberId = MemberId;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public void setReceiving(int Receiving) {
            this.Receiving = Receiving;
        }

        public void setApplication(int Application) {
            this.Application = Application;
        }

        public void setMerchantId(int MerchantId) {
            this.MerchantId = MerchantId;
        }

        public void setProductname(String Productname) {
            this.Productname = Productname;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public void setExplain(String Explain) {
            this.Explain = Explain;
        }

        public void setProductMoney(String ProductMoney) {
            this.ProductMoney = ProductMoney;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setReply(String Reply) {
            this.Reply = Reply;
        }

        public void setAddDate(String AddDate) {
            this.AddDate = AddDate;
        }

        public void setAnonymous(int Anonymous) {
            this.Anonymous = Anonymous;
        }

        public void setMerchantname(String Merchantname) {
            this.Merchantname = Merchantname;
        }

        public void setModifyDate(String ModifyDate) {
            this.ModifyDate = ModifyDate;
        }

        public void setSpecification(String Specification) {
            this.Specification = Specification;
        }

        public void setSpecificationName(String SpecificationName) {
            this.SpecificationName = SpecificationName;
        }

        public int getReturnsId() {
            return ReturnsId;
        }

        public int getMemberId() {
            return MemberId;
        }

        public int getNumber() {
            return Number;
        }

        public int getType() {
            return Type;
        }

        public int getReceiving() {
            return Receiving;
        }

        public int getApplication() {
            return Application;
        }

        public int getMerchantId() {
            return MerchantId;
        }

        public String getProductname() {
            return Productname;
        }

        public String getReason() {
            return Reason;
        }

        public String getExplain() {
            return Explain;
        }

        public String getProductMoney() {
            return ProductMoney;
        }

        public String getMoney() {
            return Money;
        }

        public String getName() {
            return Name;
        }

        public String getReply() {
            return Reply;
        }

        public String getAddDate() {
            return AddDate;
        }

        public int getAnonymous() {
            return Anonymous;
        }

        public String getMerchantname() {
            return Merchantname;
        }

        public String getModifyDate() {
            return ModifyDate;
        }

        public String getSpecification() {
            return Specification;
        }

        public String getSpecificationName() {
            return SpecificationName;
        }
    }

    public static class ModelphotoEntity {
        private String Url;

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getUrl() {
            return Url;
        }
    }
}
