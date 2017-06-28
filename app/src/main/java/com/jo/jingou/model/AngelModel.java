package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2016/12/5.
 */
public class AngelModel extends MyBaseModel {


    /**
     * ProductType : {"ProductTypeId":5,"Angel":1,"TypeName":"小天使产品","TypeDesc":null,"TypeContent":null,"ListImgUrl":null,"Sort":0,"IsShow":1,"Lang":0,"PageTitle":null,"MetaKeywords":null,"MetaDescription":null,"Language":"中文","LeafLevel":0,"ParentId":0,"IdTree":".5.","AddDate":"2016-11-24 15:55:11","ModifyDate":"2016-11-24 15:54:54"}
     * ProductId : 13
     * ProductTypeId : 5
     * Stock : 11111
     * SalesVolume : 3333
     * MemberId : 1
     * Specifications : 小天使1号
     * Originalprice : 222.0
     * Freight : 0.0
     * Delete : 1
     * ListImgUrl2 : null
     * Lang : 0
     * Para : null
     * Paras : 0
     * Canshu : null
     * MainTitle : 小天使4号
     * Subtitle : null
     * Introduction : 小天使1号
     * Contents : null
     * ListImgUrl : null
     * Attachment : null
     * Sort : 0
     * IsShow : 1
     * ViewCount : 0
     * ViewLimit : 0
     * Prop : null
     * PageTitle : null
     * MetaKeywords : null
     * MetaDescription : null
     * PropList : null
     * MultiImgUrl : null
     * MultiImgTitle : null
     * MultiImgLink : null
     * MultiImgTarget : null
     * AddDate : 2016-11-25 11:31:15
     * ModifyDate : 2016-11-25 11:31:06
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        /**
         * ProductTypeId : 5
         * Angel : 1
         * TypeName : 小天使产品
         * TypeDesc : null
         * TypeContent : null
         * ListImgUrl : null
         * Sort : 0
         * IsShow : 1
         * Lang : 0
         * PageTitle : null
         * MetaKeywords : null
         * MetaDescription : null
         * Language : 中文
         * LeafLevel : 0
         * ParentId : 0
         * IdTree : .5.
         * AddDate : 2016-11-24 15:55:11
         * ModifyDate : 2016-11-24 15:54:54
         */

        private ProductTypeEntity ProductType;
        private int ProductId;
        private int ProductTypeId;
        private int Stock;
        private int SalesVolume;
        private int MemberId;
        private String Specifications;
        private double Originalprice;
        private double Freight;
        private int Delete;
        private Object ListImgUrl2;
        private int Lang;
        private Object Para;
        private int Paras;
        private Object Canshu;
        private String MainTitle;
        private Object Subtitle;
        private String Introduction;
        private Object Contents;
        private Object ListImgUrl;
        private Object Attachment;
        private int Sort;
        private int IsShow;
        private int ViewCount;
        private int ViewLimit;
        private Object Prop;
        private Object PageTitle;
        private Object MetaKeywords;
        private Object MetaDescription;
        private Object PropList;
        private Object MultiImgUrl;
        private Object MultiImgTitle;
        private Object MultiImgLink;
        private Object MultiImgTarget;
        private String AddDate;
        private String ModifyDate;

        public void setProductType(ProductTypeEntity ProductType) {
            this.ProductType = ProductType;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public void setProductTypeId(int ProductTypeId) {
            this.ProductTypeId = ProductTypeId;
        }

        public void setStock(int Stock) {
            this.Stock = Stock;
        }

        public void setSalesVolume(int SalesVolume) {
            this.SalesVolume = SalesVolume;
        }

        public void setMemberId(int MemberId) {
            this.MemberId = MemberId;
        }

        public void setSpecifications(String Specifications) {
            this.Specifications = Specifications;
        }

        public void setOriginalprice(double Originalprice) {
            this.Originalprice = Originalprice;
        }

        public void setFreight(double Freight) {
            this.Freight = Freight;
        }

        public void setDelete(int Delete) {
            this.Delete = Delete;
        }

        public void setListImgUrl2(Object ListImgUrl2) {
            this.ListImgUrl2 = ListImgUrl2;
        }

        public void setLang(int Lang) {
            this.Lang = Lang;
        }

        public void setPara(Object Para) {
            this.Para = Para;
        }

        public void setParas(int Paras) {
            this.Paras = Paras;
        }

        public void setCanshu(Object Canshu) {
            this.Canshu = Canshu;
        }

        public void setMainTitle(String MainTitle) {
            this.MainTitle = MainTitle;
        }

        public void setSubtitle(Object Subtitle) {
            this.Subtitle = Subtitle;
        }

        public void setIntroduction(String Introduction) {
            this.Introduction = Introduction;
        }

        public void setContents(Object Contents) {
            this.Contents = Contents;
        }

        public void setListImgUrl(Object ListImgUrl) {
            this.ListImgUrl = ListImgUrl;
        }

        public void setAttachment(Object Attachment) {
            this.Attachment = Attachment;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public void setIsShow(int IsShow) {
            this.IsShow = IsShow;
        }

        public void setViewCount(int ViewCount) {
            this.ViewCount = ViewCount;
        }

        public void setViewLimit(int ViewLimit) {
            this.ViewLimit = ViewLimit;
        }

        public void setProp(Object Prop) {
            this.Prop = Prop;
        }

        public void setPageTitle(Object PageTitle) {
            this.PageTitle = PageTitle;
        }

        public void setMetaKeywords(Object MetaKeywords) {
            this.MetaKeywords = MetaKeywords;
        }

        public void setMetaDescription(Object MetaDescription) {
            this.MetaDescription = MetaDescription;
        }

        public void setPropList(Object PropList) {
            this.PropList = PropList;
        }

        public void setMultiImgUrl(Object MultiImgUrl) {
            this.MultiImgUrl = MultiImgUrl;
        }

        public void setMultiImgTitle(Object MultiImgTitle) {
            this.MultiImgTitle = MultiImgTitle;
        }

        public void setMultiImgLink(Object MultiImgLink) {
            this.MultiImgLink = MultiImgLink;
        }

        public void setMultiImgTarget(Object MultiImgTarget) {
            this.MultiImgTarget = MultiImgTarget;
        }

        public void setAddDate(String AddDate) {
            this.AddDate = AddDate;
        }

        public void setModifyDate(String ModifyDate) {
            this.ModifyDate = ModifyDate;
        }

        public ProductTypeEntity getProductType() {
            return ProductType;
        }

        public int getProductId() {
            return ProductId;
        }

        public int getProductTypeId() {
            return ProductTypeId;
        }

        public int getStock() {
            return Stock;
        }

        public int getSalesVolume() {
            return SalesVolume;
        }

        public int getMemberId() {
            return MemberId;
        }

        public String getSpecifications() {
            return Specifications;
        }

        public double getOriginalprice() {
            return Originalprice;
        }

        public double getFreight() {
            return Freight;
        }

        public int getDelete() {
            return Delete;
        }

        public Object getListImgUrl2() {
            return ListImgUrl2;
        }

        public int getLang() {
            return Lang;
        }

        public Object getPara() {
            return Para;
        }

        public int getParas() {
            return Paras;
        }

        public Object getCanshu() {
            return Canshu;
        }

        public String getMainTitle() {
            return MainTitle;
        }

        public Object getSubtitle() {
            return Subtitle;
        }

        public String getIntroduction() {
            return Introduction;
        }

        public Object getContents() {
            return Contents;
        }

        public Object getListImgUrl() {
            return ListImgUrl;
        }

        public Object getAttachment() {
            return Attachment;
        }

        public int getSort() {
            return Sort;
        }

        public int getIsShow() {
            return IsShow;
        }

        public int getViewCount() {
            return ViewCount;
        }

        public int getViewLimit() {
            return ViewLimit;
        }

        public Object getProp() {
            return Prop;
        }

        public Object getPageTitle() {
            return PageTitle;
        }

        public Object getMetaKeywords() {
            return MetaKeywords;
        }

        public Object getMetaDescription() {
            return MetaDescription;
        }

        public Object getPropList() {
            return PropList;
        }

        public Object getMultiImgUrl() {
            return MultiImgUrl;
        }

        public Object getMultiImgTitle() {
            return MultiImgTitle;
        }

        public Object getMultiImgLink() {
            return MultiImgLink;
        }

        public Object getMultiImgTarget() {
            return MultiImgTarget;
        }

        public String getAddDate() {
            return AddDate;
        }

        public String getModifyDate() {
            return ModifyDate;
        }

        public static class ProductTypeEntity {
            private int ProductTypeId;
            private int Angel;
            private String TypeName;
            private Object TypeDesc;
            private Object TypeContent;
            private Object ListImgUrl;
            private int Sort;
            private int IsShow;
            private int Lang;
            private Object PageTitle;
            private Object MetaKeywords;
            private Object MetaDescription;
            private String Language;
            private int LeafLevel;
            private int ParentId;
            private String IdTree;
            private String AddDate;
            private String ModifyDate;

            public void setProductTypeId(int ProductTypeId) {
                this.ProductTypeId = ProductTypeId;
            }

            public void setAngel(int Angel) {
                this.Angel = Angel;
            }

            public void setTypeName(String TypeName) {
                this.TypeName = TypeName;
            }

            public void setTypeDesc(Object TypeDesc) {
                this.TypeDesc = TypeDesc;
            }

            public void setTypeContent(Object TypeContent) {
                this.TypeContent = TypeContent;
            }

            public void setListImgUrl(Object ListImgUrl) {
                this.ListImgUrl = ListImgUrl;
            }

            public void setSort(int Sort) {
                this.Sort = Sort;
            }

            public void setIsShow(int IsShow) {
                this.IsShow = IsShow;
            }

            public void setLang(int Lang) {
                this.Lang = Lang;
            }

            public void setPageTitle(Object PageTitle) {
                this.PageTitle = PageTitle;
            }

            public void setMetaKeywords(Object MetaKeywords) {
                this.MetaKeywords = MetaKeywords;
            }

            public void setMetaDescription(Object MetaDescription) {
                this.MetaDescription = MetaDescription;
            }

            public void setLanguage(String Language) {
                this.Language = Language;
            }

            public void setLeafLevel(int LeafLevel) {
                this.LeafLevel = LeafLevel;
            }

            public void setParentId(int ParentId) {
                this.ParentId = ParentId;
            }

            public void setIdTree(String IdTree) {
                this.IdTree = IdTree;
            }

            public void setAddDate(String AddDate) {
                this.AddDate = AddDate;
            }

            public void setModifyDate(String ModifyDate) {
                this.ModifyDate = ModifyDate;
            }

            public int getProductTypeId() {
                return ProductTypeId;
            }

            public int getAngel() {
                return Angel;
            }

            public String getTypeName() {
                return TypeName;
            }

            public Object getTypeDesc() {
                return TypeDesc;
            }

            public Object getTypeContent() {
                return TypeContent;
            }

            public Object getListImgUrl() {
                return ListImgUrl;
            }

            public int getSort() {
                return Sort;
            }

            public int getIsShow() {
                return IsShow;
            }

            public int getLang() {
                return Lang;
            }

            public Object getPageTitle() {
                return PageTitle;
            }

            public Object getMetaKeywords() {
                return MetaKeywords;
            }

            public Object getMetaDescription() {
                return MetaDescription;
            }

            public String getLanguage() {
                return Language;
            }

            public int getLeafLevel() {
                return LeafLevel;
            }

            public int getParentId() {
                return ParentId;
            }

            public String getIdTree() {
                return IdTree;
            }

            public String getAddDate() {
                return AddDate;
            }

            public String getModifyDate() {
                return ModifyDate;
            }
        }
    }
}
