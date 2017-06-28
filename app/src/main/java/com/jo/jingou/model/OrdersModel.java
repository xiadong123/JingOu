package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2016/12/13.
 */
public class OrdersModel extends MyBaseModel {


    /**
     * MerchantId : 21
     * MerchantorderId : 84
     * MerchantNum : KQ4JNB1H20161215093048
     * Name : 测试2222
     * Tel : 2236555
     * Province : 上海
     * City : 上海
     * County : 黄浦区
     * Adr : 哦多了
     * PayType : null
     * Remarks : ,
     * Piece : 1
     * Postage : 0.00
     * TotalPrice : 600.00
     * OrderState : 3
     * Time : 2016/12/15 9:30:49
     * Payable : 600.00
     * OrderModel : [{"OrderId":84,"ProductId":13,"MainTitle":"小天使4号","Subtitle":"小天使1号","Introduction":"小天使1号","ListImgUrl":"/assets/uploads/20161205/2016120513433873054.jpg","Amount":1,"Originalprice":"600.00"}]
     */

    private ModelEntity model;

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public ModelEntity getModel() {
        return model;
    }

    public static class ModelEntity {
        private int MerchantId;
        private String MerchantTel;
        private int MerchantorderId;
        private String MerchantNum;
        private String Name;
        private String Tel;
        private String Province;
        private String City;
        private String County;
        private String Adr;
        private Object PayType;
        private String Remarks;
        private int Piece;
        private String Postage;
        private String TotalPrice;
        private int OrderState;
        private String Time;
        private String Payable;
        private String Couriernumber;
        private String Express;

        public String getMerchantTel() {
            return MerchantTel;
        }

        public void setMerchantTel(String merchantTel) {
            MerchantTel = merchantTel;
        }

        public String getCouriernumber() {
            return Couriernumber;
        }

        public void setCouriernumber(String couriernumber) {
            Couriernumber = couriernumber;
        }

        public String getExpress() {
            return Express;
        }

        public void setExpress(String express) {
            Express = express;
        }

        /**
         * OrderId : 84
         * ProductId : 13
         * MainTitle : 小天使4号
         * Subtitle : 小天使1号
         * Introduction : 小天使1号
         * ListImgUrl : /assets/uploads/20161205/2016120513433873054.jpg
         * Amount : 1
         * Originalprice : 600.00
         */


        private List<OrderModelEntity> OrderModel;

        public void setMerchantId(int MerchantId) {
            this.MerchantId = MerchantId;
        }

        public void setMerchantorderId(int MerchantorderId) {
            this.MerchantorderId = MerchantorderId;
        }

        public void setMerchantNum(String MerchantNum) {
            this.MerchantNum = MerchantNum;
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

        public void setAdr(String Adr) {
            this.Adr = Adr;
        }

        public void setPayType(Object PayType) {
            this.PayType = PayType;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public void setPiece(int Piece) {
            this.Piece = Piece;
        }

        public void setPostage(String Postage) {
            this.Postage = Postage;
        }

        public void setTotalPrice(String TotalPrice) {
            this.TotalPrice = TotalPrice;
        }

        public void setOrderState(int OrderState) {
            this.OrderState = OrderState;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public void setPayable(String Payable) {
            this.Payable = Payable;
        }

        public void setOrderModel(List<OrderModelEntity> OrderModel) {
            this.OrderModel = OrderModel;
        }

        public int getMerchantId() {
            return MerchantId;
        }

        public int getMerchantorderId() {
            return MerchantorderId;
        }

        public String getMerchantNum() {
            return MerchantNum;
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

        public String getAdr() {
            return Adr;
        }

        public Object getPayType() {
            return PayType;
        }

        public String getRemarks() {
            return Remarks;
        }

        public int getPiece() {
            return Piece;
        }

        public String getPostage() {
            return Postage;
        }

        public String getTotalPrice() {
            return TotalPrice;
        }

        public int getOrderState() {
            return OrderState;
        }

        public String getTime() {
            return Time;
        }

        public String getPayable() {
            return Payable;
        }

        public List<OrderModelEntity> getOrderModel() {
            return OrderModel;
        }

        public static class OrderModelEntity {
            private int OrderId;
            private int ProductId;
            private String MainTitle;
            private String Subtitle;
            private String Introduction;
            private String ListImgUrl;
            private int Amount;
            private String Originalprice;
            private String Paraid;
            private String Modifydate;

            public String getModifydate() {
                return Modifydate;
            }

            public void setModifydate(String modifydate) {
                Modifydate = modifydate;
            }

            public String getParaid() {
                return Paraid;
            }

            public void setParaid(String paraid) {
                Paraid = paraid;
            }

            public void setOrderId(int OrderId) {
                this.OrderId = OrderId;
            }

            public void setProductId(int ProductId) {
                this.ProductId = ProductId;
            }

            public void setMainTitle(String MainTitle) {
                this.MainTitle = MainTitle;
            }

            public void setSubtitle(String Subtitle) {
                this.Subtitle = Subtitle;
            }

            public void setIntroduction(String Introduction) {
                this.Introduction = Introduction;
            }

            public void setListImgUrl(String ListImgUrl) {
                this.ListImgUrl = ListImgUrl;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }

            public void setOriginalprice(String Originalprice) {
                this.Originalprice = Originalprice;
            }

            public int getOrderId() {
                return OrderId;
            }

            public int getProductId() {
                return ProductId;
            }

            public String getMainTitle() {
                return MainTitle;
            }

            public String getSubtitle() {
                return Subtitle;
            }

            public String getIntroduction() {
                return Introduction;
            }

            public String getListImgUrl() {
                return ListImgUrl;
            }

            public int getAmount() {
                return Amount;
            }

            public String getOriginalprice() {
                return Originalprice;
            }
        }
    }
}
