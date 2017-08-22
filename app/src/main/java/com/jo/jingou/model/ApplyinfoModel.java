package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class ApplyinfoModel extends MyBaseModel {


    /**
     * AlipayAccount : 123
     * ApplyState : 0
     * BusinessApplyId : 3
     * StoreMobile : 13917865493
     * StoreName : 呵呵
     * Upload : [{"Type":0,"UploadId":8,"Url":"http://jingou.idea-source
     * .net/assets/uploads/20170512/201705121045448361.jpg"},{"Type":1,"UploadId":9,"Url":"http://jingou.idea-source
     * .net/assets/uploads/20170512/2017051210454484030.jpg"},{"Type":2,"UploadId":10,"Url":"http://jingou
     * .idea-source.net/assets/uploads/20170512/2017051210454484216.jpg"}]
     */

    private ModelBean model;

    public ModelBean getModel() {
        return model;
    }

    public void setModel(ModelBean model) {
        this.model = model;
    }

    public static class ModelBean {
        private String AlipayAccount;
        private int ApplyState;
        private int BusinessApplyId;
        private String StoreMobile;
        private String StoreName;

        private String Name;
        private String MerchantTel;
        private String MerchantCode;
        private String MerchantType;
        /**
         * Type : 0
         * UploadId : 8
         * Url : http://jingou.idea-source.net/assets/uploads/20170512/201705121045448361.jpg
         */

        private List<UploadBean> Upload;

        public String getAlipayAccount() {
            return AlipayAccount;
        }

        public void setAlipayAccount(String AlipayAccount) {
            this.AlipayAccount = AlipayAccount;
        }

        public int getApplyState() {
            return ApplyState;
        }

        public void setApplyState(int ApplyState) {
            this.ApplyState = ApplyState;
        }

        public int getBusinessApplyId() {
            return BusinessApplyId;
        }

        public void setBusinessApplyId(int BusinessApplyId) {
            this.BusinessApplyId = BusinessApplyId;
        }

        public String getStoreMobile() {
            return StoreMobile;
        }

        public void setStoreMobile(String StoreMobile) {
            this.StoreMobile = StoreMobile;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }


        //后加入
        public String getMerchantName() {
            return Name;
        }

        public void setMerchantName(String MerchantName) {
            this.Name = MerchantName;
        }

        public String getMerchantTel() {
            return MerchantTel;
        }

        public void setMerchantTel(String MerchantTel) {
            this.MerchantTel = MerchantTel;
        }

        public String getMerchant() {
            return MerchantCode;
        }

        public void setMerchant(String Merchant) {
            this.MerchantCode = Merchant;
        }

        public String getMerchantType(){return MerchantType;}

        public void setMerchantType(String MerchantType) {
            this.MerchantType = MerchantType;
        }


        public List<UploadBean> getUpload() {
            return Upload;
        }

        public void setUpload(List<UploadBean> Upload) {
            this.Upload = Upload;
        }

        public static class UploadBean {
            private int Type;
            private int UploadId;
            private String Url;

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public int getUploadId() {
                return UploadId;
            }

            public void setUploadId(int UploadId) {
                this.UploadId = UploadId;
            }

            public String getUrl() {
                return Url;
            }

            public void setUrl(String Url) {
                this.Url = Url;
            }
        }
    }
}
