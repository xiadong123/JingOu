package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/11.
 */

public class UserInfoModel extends MyBaseModel implements Serializable {


    /**
     * Mobile : 13701657342
     * Name : 疯鱼
     * Sex : 0
     * Email :
     * ListImgUrl : /assets/uploads/20170416/2017041610475096058.jpg
     * Birthday : 1990-09-27
     * Introduction :
     * Status : 0
     * Dividends : 0
     * IsShop : 0
     * IsPartner : 0
     * DividendsMoney : 0.0
     * DistributionMoney : 0.0
     * PartnersMoney : 0.0
     * ReturnsMoney : 0.0
     * MeCode : VP18892918
     * RealName : 测试
     * CardType : 0
     * IdCard : 310109199009273010
     * ApplyStatus : 0
     */

    private ModelEntity model;
    /**
     * model : {"Mobile":"13701657342","Name":"疯鱼","Sex":0,"Email":"","ListImgUrl":"/assets/uploads/20170416/2017041610475096058.jpg","Birthday":"1990-09-27","Introduction":"","Status":0,"Dividends":0,"IsShop":0,"IsPartner":0,"DividendsMoney":0,"DistributionMoney":0,"PartnersMoney":0,"ReturnsMoney":0,"MeCode":"VP18892918","RealName":"测试","CardType":0,"IdCard":"310109199009273010","ApplyStatus":0}
     * servicetel : 4009986698
     */

    private String servicetel;

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public void setServicetel(String servicetel) {
        this.servicetel = servicetel;
    }

    public ModelEntity getModel() {
        return model;
    }

    public String getServicetel() {
        return servicetel;
    }

    public static class ModelEntity {
        private String Mobile;
        private String Name;
        private int Sex;
        private String Email;
        private String ListImgUrl;
        private String Birthday;
        private String Introduction;
        private int Status;
        private int Dividends;
        private int IsShop;
        private int IsPartner;
        private double DividendsMoney;
        private double DistributionMoney;
        private double PartnersMoney;
        private double ReturnsMoney;
        private String MeCode;
        private String RealName;
        private int CardType;
        private String IdCard;
        private int ApplyStatus;

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setSex(int Sex) {
            this.Sex = Sex;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public void setListImgUrl(String ListImgUrl) {
            this.ListImgUrl = ListImgUrl;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }

        public void setIntroduction(String Introduction) {
            this.Introduction = Introduction;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public void setDividends(int Dividends) {
            this.Dividends = Dividends;
        }

        public void setIsShop(int IsShop) {
            this.IsShop = IsShop;
        }

        public void setIsPartner(int IsPartner) {
            this.IsPartner = IsPartner;
        }

        public void setDividendsMoney(double DividendsMoney) {
            this.DividendsMoney = DividendsMoney;
        }

        public void setDistributionMoney(double DistributionMoney) {
            this.DistributionMoney = DistributionMoney;
        }

        public void setPartnersMoney(double PartnersMoney) {
            this.PartnersMoney = PartnersMoney;
        }

        public void setReturnsMoney(double ReturnsMoney) {
            this.ReturnsMoney = ReturnsMoney;
        }

        public void setMeCode(String MeCode) {
            this.MeCode = MeCode;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public void setCardType(int CardType) {
            this.CardType = CardType;
        }

        public void setIdCard(String IdCard) {
            this.IdCard = IdCard;
        }

        public void setApplyStatus(int ApplyStatus) {
            this.ApplyStatus = ApplyStatus;
        }

        public String getMobile() {
            return Mobile;
        }

        public String getName() {
            return Name;
        }

        public int getSex() {
            return Sex;
        }

        public String getEmail() {
            return Email;
        }

        public String getListImgUrl() {
            return ListImgUrl;
        }

        public String getBirthday() {
            return Birthday;
        }

        public String getIntroduction() {
            return Introduction;
        }

        public int getStatus() {
            return Status;
        }

        public int getDividends() {
            return Dividends;
        }

        public int getIsShop() {
            return IsShop;
        }

        public int getIsPartner() {
            return IsPartner;
        }

        public double getDividendsMoney() {
            return DividendsMoney;
        }

        public double getDistributionMoney() {
            return DistributionMoney;
        }

        public double getPartnersMoney() {
            return PartnersMoney;
        }

        public double getReturnsMoney() {
            return ReturnsMoney;
        }

        public String getMeCode() {
            return MeCode;
        }

        public String getRealName() {
            return RealName;
        }

        public int getCardType() {
            return CardType;
        }

        public String getIdCard() {
            return IdCard;
        }

        public int getApplyStatus() {
            return ApplyStatus;
        }
    }
}
