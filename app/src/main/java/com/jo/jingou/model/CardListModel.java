package com.jo.jingou.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.jo.jingou.base.MyBaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class CardListModel extends MyBaseModel implements Parcelable {

    /**
     * Bank : 招商银行
     * BankCardCode : 54654646546
     * BankCardId : 9
     * BankInformation :
     * MemberId : 2061
     * Name : 测试
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        private String Bank;
        private String BankCardCode;
        private int BankCardId;
        private String BankInformation;
        private int MemberId;
        private String Name;
        private int Default;

        public int getDefault() {
            return Default;
        }

        public void setDefault(int aDefault) {
            Default = aDefault;
        }

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.Bank);
            dest.writeString(this.BankCardCode);
            dest.writeInt(this.BankCardId);
            dest.writeString(this.BankInformation);
            dest.writeInt(this.MemberId);
            dest.writeString(this.Name);
            dest.writeInt(this.Default);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.Bank = in.readString();
            this.BankCardCode = in.readString();
            this.BankCardId = in.readInt();
            this.BankInformation = in.readString();
            this.MemberId = in.readInt();
            this.Name = in.readString();
            this.Default = in.readInt();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.list);
    }

    public CardListModel() {
    }

    protected CardListModel(Parcel in) {
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CardListModel> CREATOR = new Parcelable.Creator<CardListModel>() {
        @Override
        public CardListModel createFromParcel(Parcel source) {
            return new CardListModel(source);
        }

        @Override
        public CardListModel[] newArray(int size) {
            return new CardListModel[size];
        }
    };
}
