package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class MoneyFaceModel extends MyBaseModel {
    /**
     * Bank : 中国银行
     * BankCardCode : 622545464654645464646546
     * BankCardId : 13
     * BankInformation : 砖业支行
     * Default : 0
     * Member : 测试
     * MemberId : 2061
     * Name : 测试
     * Tel : 13701657342
     */
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String Bank;
        private String BankCardCode;
        private int BankCardId;
        private String BankInformation;
        private int Default;
        private String Member;
        private int MemberId;
        private String Name;
        private String Tel;

        public String getBank() {
            return Bank;
        }

        public void setBank(String Bank) {
            this.Bank = Bank;
        }

        public String getBankCardCode() {
            return BankCardCode;
        }

        public void setBankCardCode(String BankCardCode) {
            this.BankCardCode = BankCardCode;
        }

        public int getBankCardId() {
            return BankCardId;
        }

        public void setBankCardId(int BankCardId) {
            this.BankCardId = BankCardId;
        }

        public String getBankInformation() {
            return BankInformation;
        }

        public void setBankInformation(String BankInformation) {
            this.BankInformation = BankInformation;
        }

        public int getDefault() {
            return Default;
        }

        public void setDefault(int Default) {
            this.Default = Default;
        }

        public String getMember() {
            return Member;
        }

        public void setMember(String Member) {
            this.Member = Member;
        }

        public int getMemberId() {
            return MemberId;
        }

        public void setMemberId(int MemberId) {
            this.MemberId = MemberId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }
    }
}
