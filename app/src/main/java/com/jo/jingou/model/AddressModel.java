package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dfyu on 2016/12/10.
 */
public class AddressModel extends MyBaseModel implements Serializable {

    /**
     * Member : {"MemberId":29,"Name":null,"Sex":0,"Mobile":"13122222222","Email":null,"Address":null,"Password":"E3CEB5881A0A1FDAAD01296D7554868D","PayPassword":null,"ListImgUrl":null,"Birthday":null,"MerchantName":null,"Introduction":null,"Status":0,"DividendsMoney":0,"DistributionMoney":0,"PartnersMoney":0,"Integral":0,"MeCode":null,"FatherCode":"eR1QDYyMzGZw9Z6OJQHcsQ==","Dividends":0,"IsShop":0,"IsPartner":0,"RealName":"小戚","IdCard":"310109199009273010","IdTree":".13.17.18.19.20.29.","PartnerIdTree":".17.18.19.20.29.","Token":"dd3e210050614c6d82c12491392334d6","PhoneId":"D29ED176478B84A59FC36D1640667948","AddDate":"2016-12-07 14:23:57","ModifyDate":"2016-12-07 14:23:57"}
     * MemberPlaceId : 7
     * MemberId : 29
     * Name : 测试收货人
     * Tel : 123456789
     * Province : 上海
     * City : 上海
     * County : 杨浦区
     * Address : 测试收获地址
     * SortId : 0
     * Type : 0
     * AddDate : 2016-12-09 11:24:23
     * ModifyDate : 2016-12-09 11:24:23
     */

    private List<ModelPlacesEntity> modelPlaces;

    public void setModelPlaces(List<ModelPlacesEntity> modelPlaces) {
        this.modelPlaces = modelPlaces;
    }

    public List<ModelPlacesEntity> getModelPlaces() {
        return modelPlaces;
    }

    public static class ModelPlacesEntity implements Serializable {
        /**
         * MemberId : 29
         * Name : null
         * Sex : 0
         * Mobile : 13122222222
         * Email : null
         * Address : null
         * Password : E3CEB5881A0A1FDAAD01296D7554868D
         * PayPassword : null
         * ListImgUrl : null
         * Birthday : null
         * MerchantName : null
         * Introduction : null
         * Status : 0
         * DividendsMoney : 0.0
         * DistributionMoney : 0.0
         * PartnersMoney : 0.0
         * Integral : 0
         * MeCode : null
         * FatherCode : eR1QDYyMzGZw9Z6OJQHcsQ==
         * Dividends : 0
         * IsShop : 0
         * IsPartner : 0
         * RealName : 小戚
         * IdCard : 310109199009273010
         * IdTree : .13.17.18.19.20.29.
         * PartnerIdTree : .17.18.19.20.29.
         * Token : dd3e210050614c6d82c12491392334d6
         * PhoneId : D29ED176478B84A59FC36D1640667948
         * AddDate : 2016-12-07 14:23:57
         * ModifyDate : 2016-12-07 14:23:57
         */

        private MemberEntity Member;
        private int MemberPlaceId;
        private int MemberId;
        private String Name;
        private String Tel;
        private String Province;
        private String City;
        private String County;
        private String Address;
        private int SortId;
        private int Type;
        private String AddDate;
        private String ModifyDate;

        public void setMember(MemberEntity Member) {
            this.Member = Member;
        }

        public void setMemberPlaceId(int MemberPlaceId) {
            this.MemberPlaceId = MemberPlaceId;
        }

        public void setMemberId(int MemberId) {
            this.MemberId = MemberId;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public void setCounty(String County) {
            this.County = County;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public void setSortId(int SortId) {
            this.SortId = SortId;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public void setAddDate(String AddDate) {
            this.AddDate = AddDate;
        }

        public void setModifyDate(String ModifyDate) {
            this.ModifyDate = ModifyDate;
        }

        public MemberEntity getMember() {
            return Member;
        }

        public int getMemberPlaceId() {
            return MemberPlaceId;
        }

        public int getMemberId() {
            return MemberId;
        }

        public String getName() {
            return Name;
        }

        public String getTel() {
            return Tel;
        }

        public String getProvince() {
            return Province;
        }

        public String getCity() {
            return City;
        }

        public String getCounty() {
            return County;
        }

        public String getAddress() {
            return Address;
        }

        public int getSortId() {
            return SortId;
        }

        public int getType() {
            return Type;
        }

        public String getAddDate() {
            return AddDate;
        }

        public String getModifyDate() {
            return ModifyDate;
        }

        public static class MemberEntity implements Serializable {
            private int MemberId;
            private Object Name;
            private int Sex;
            private String Mobile;
            private Object Email;
            private Object Address;
            private String Password;
            private Object PayPassword;
            private Object ListImgUrl;
            private Object Birthday;
            private Object MerchantName;
            private Object Introduction;
            private int Status;
            private double DividendsMoney;
            private double DistributionMoney;
            private double PartnersMoney;
            private int Integral;
            private Object MeCode;
            private String FatherCode;
            private int Dividends;
            private int IsShop;
            private int IsPartner;
            private String RealName;
            private String IdCard;
            private String IdTree;
            private String PartnerIdTree;
            private String Token;
            private String PhoneId;
            private String AddDate;
            private String ModifyDate;

            public void setMemberId(int MemberId) {
                this.MemberId = MemberId;
            }

            public void setName(Object Name) {
                this.Name = Name;
            }

            public void setSex(int Sex) {
                this.Sex = Sex;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public void setEmail(Object Email) {
                this.Email = Email;
            }

            public void setAddress(Object Address) {
                this.Address = Address;
            }

            public void setPassword(String Password) {
                this.Password = Password;
            }

            public void setPayPassword(Object PayPassword) {
                this.PayPassword = PayPassword;
            }

            public void setListImgUrl(Object ListImgUrl) {
                this.ListImgUrl = ListImgUrl;
            }

            public void setBirthday(Object Birthday) {
                this.Birthday = Birthday;
            }

            public void setMerchantName(Object MerchantName) {
                this.MerchantName = MerchantName;
            }

            public void setIntroduction(Object Introduction) {
                this.Introduction = Introduction;
            }

            public void setStatus(int Status) {
                this.Status = Status;
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

            public void setIntegral(int Integral) {
                this.Integral = Integral;
            }

            public void setMeCode(Object MeCode) {
                this.MeCode = MeCode;
            }

            public void setFatherCode(String FatherCode) {
                this.FatherCode = FatherCode;
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

            public void setRealName(String RealName) {
                this.RealName = RealName;
            }

            public void setIdCard(String IdCard) {
                this.IdCard = IdCard;
            }

            public void setIdTree(String IdTree) {
                this.IdTree = IdTree;
            }

            public void setPartnerIdTree(String PartnerIdTree) {
                this.PartnerIdTree = PartnerIdTree;
            }

            public void setToken(String Token) {
                this.Token = Token;
            }

            public void setPhoneId(String PhoneId) {
                this.PhoneId = PhoneId;
            }

            public void setAddDate(String AddDate) {
                this.AddDate = AddDate;
            }

            public void setModifyDate(String ModifyDate) {
                this.ModifyDate = ModifyDate;
            }

            public int getMemberId() {
                return MemberId;
            }

            public Object getName() {
                return Name;
            }

            public int getSex() {
                return Sex;
            }

            public String getMobile() {
                return Mobile;
            }

            public Object getEmail() {
                return Email;
            }

            public Object getAddress() {
                return Address;
            }

            public String getPassword() {
                return Password;
            }

            public Object getPayPassword() {
                return PayPassword;
            }

            public Object getListImgUrl() {
                return ListImgUrl;
            }

            public Object getBirthday() {
                return Birthday;
            }

            public Object getMerchantName() {
                return MerchantName;
            }

            public Object getIntroduction() {
                return Introduction;
            }

            public int getStatus() {
                return Status;
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

            public int getIntegral() {
                return Integral;
            }

            public Object getMeCode() {
                return MeCode;
            }

            public String getFatherCode() {
                return FatherCode;
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

            public String getRealName() {
                return RealName;
            }

            public String getIdCard() {
                return IdCard;
            }

            public String getIdTree() {
                return IdTree;
            }

            public String getPartnerIdTree() {
                return PartnerIdTree;
            }

            public String getToken() {
                return Token;
            }

            public String getPhoneId() {
                return PhoneId;
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
