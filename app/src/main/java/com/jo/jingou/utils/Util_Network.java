package com.jo.jingou.utils;

import android.content.Context;

import com.jo.jingou.MyApplication;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.AddressModel;
import com.jo.jingou.model.AliPayModel;
import com.jo.jingou.model.AngelBuyModel;
import com.jo.jingou.model.AngelModel;
import com.jo.jingou.model.ApkModel;
import com.jo.jingou.model.ApplyinfoModel;
import com.jo.jingou.model.BanrndModel;
import com.jo.jingou.model.CardListModel;
import com.jo.jingou.model.CartModel;
import com.jo.jingou.model.DelCardModel;
import com.jo.jingou.model.Instation0Model;
import com.jo.jingou.model.Instation1Model;
import com.jo.jingou.model.Instation2Model;
import com.jo.jingou.model.Instation30Model;
import com.jo.jingou.model.Instation31Model;
import com.jo.jingou.model.Instation32Model;
import com.jo.jingou.model.InstationCountModel;
import com.jo.jingou.model.IntegralModel;
import com.jo.jingou.model.LoginModel;
import com.jo.jingou.model.MoneyFaceModel;
import com.jo.jingou.model.MyPocketModel;
import com.jo.jingou.model.OrderListModel;
import com.jo.jingou.model.OrderModel;
import com.jo.jingou.model.OrdersModel;
import com.jo.jingou.model.PerticiListModel;
import com.jo.jingou.model.PocketdetailModel;
import com.jo.jingou.model.ProductCommentsModel;
import com.jo.jingou.model.ProductDetailModel;
import com.jo.jingou.model.ProductTypeModel;
import com.jo.jingou.model.ProductTypeSonModel;
import com.jo.jingou.model.RegisterModel;
import com.jo.jingou.model.ReturnDetailModel;
import com.jo.jingou.model.ReturnListModel;
import com.jo.jingou.model.SelectListModel;
import com.jo.jingou.model.SelectModel;
import com.jo.jingou.model.SendCodeModel;
import com.jo.jingou.model.ShareModel;
import com.jo.jingou.model.ShopNumberModel;
import com.jo.jingou.model.ShoppingDModel;
import com.jo.jingou.model.TokenModel;
import com.jo.jingou.model.TradingrecordModel;
import com.jo.jingou.model.UserInfoModel;
import com.squareup.okhttp.Request;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/8/29.
 */
public class Util_Network {

    Context context;


    public Util_Network() {
    }

    public Util_Network(Context context) {
        this.context = context;
    }


    //获取请求接口权限token
    public void getTokenModel(final int netWork_Id, final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.TOKEN, new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("username", "admin"), new OkHttpClientManager.Param
                ("password", "123456")
        }, new OkHttpClientManager.ResultCallback<TokenModel>() {
            public void onError(Request request, Exception e) {
                request(Constant.NETWORKERROR, Constant.TOKEN_ID, null);
            }

            public void onResponse(TokenModel o) {
                if (o.getErrcode() == 0) {
                    MyApplication.setToken(o.getAccess_token());
                    request(Constant.NETWORKSUCCESS, Constant.TOKEN_ID, o);
                    onTokenTimeOut(netWork_Id, params);
                } else {
                }
            }
        });
    }


    //注册
    public void getRegisterModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.REGISTER + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<RegisterModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.REGISTER_ID, null);
                    }

                    public void onResponse(RegisterModel o) {
                        onNetWorkCallBack(o, Constant.REGISTER_ID, params);
                    }
                });
    }


    //发送验证码--注册
    public void getSendCode0Model(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.SEND + MyApplication.getToken() + ",,",
                params, new OkHttpClientManager.ResultCallback<SendCodeModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.SEND_ID0, null);
                    }

                    public void onResponse(SendCodeModel o) {
                        onNetWorkCallBack(o, Constant.SEND_ID0, params);
                    }
                });
    }

    //发送验证码--修改密码
    public void getSendCode1Model(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.SEND + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<SendCodeModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.SEND_ID1, null);
                    }

                    public void onResponse(SendCodeModel o) {
                        onNetWorkCallBack(o, Constant.SEND_ID1, params);
                    }
                });
    }

    //登录
    public void getLoginModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.LOGIN + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<LoginModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.LOGIN_ID, null);
                    }

                    public void onResponse(LoginModel o) {
                        onNetWorkCallBack(o, Constant.LOGIN_ID, params);
                    }
                });
    }

    //获取用户model
    public void getUserInfoModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.GETMODEL + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<UserInfoModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.GETMODEL_ID, null);
                    }

                    public void onResponse(UserInfoModel o) {
                        onNetWorkCallBack(o, Constant.GETMODEL_ID, params);
                    }
                });
    }


    //重置密码
    public void getChangePassWordModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.CHANGEPASSWORD + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.CHANGEPASSWORD_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.CHANGEPASSWORD_ID, params);
                    }
                });
    }

    //绑定验证码+实名认证
    public void getAllBindModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ALLBIND + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ALLBIND_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.ALLBIND_ID, params);
                    }
                });
    }

    //绑定邀请码
    public void getMemberBindModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.MEMBERBIND + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.MEMBERBIND_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.MEMBERBIND_ID, params);
                    }
                });
    }

    //实名认证
    public void getIdCardBindModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.IDCARDBIND + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.IDCARDBIND_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.IDCARDBIND_ID, params);
                    }
                });
    }


    //修改姓名
    public void getChangeNameModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.CHANGENAME + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.CHANGENAME_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.CHANGENAME_ID, params);
                    }
                });
    }

    //修改性别
    public void getChangeSexModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.CHANGESEX + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.CHANGESEX_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.CHANGESEX_ID, params);
                    }
                });
    }

    //修改生日
    public void getChangeBirthdayModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.CHANGEBIRTHDAY + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.CHANGEBIRTHDAY_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.CHANGEBIRTHDAY_ID, params);
                    }
                });
    }

    //修改邮箱
    public void getChangeEmailModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.CHANGEEMAIL + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.CHANGEEMAIL_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.CHANGEEMAIL_ID, params);
                    }
                });
    }


    //首页信息--小天使
    public void getHomeAngelModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.HOMEANGEL + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<AngelModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.HOMEANGEL_ID, null);
                    }

                    public void onResponse(AngelModel o) {
                        onNetWorkCallBack(o, Constant.HOMEANGEL_ID, params);
                    }
                });
    }


    //首页信息--广告轮播图
    public void getHomeBanrndModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.HOMEBANRND + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<BanrndModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.HOMEBANRND_ID, null);
                    }

                    public void onResponse(BanrndModel o) {
                        onNetWorkCallBack(o, Constant.HOMEBANRND_ID, params);
                    }
                });
    }


    //计算小天使订单
    public void getAngelBuyModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ANGELBUY + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<AngelBuyModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ANGELBUY_ID, null);
                    }

                    public void onResponse(AngelBuyModel o) {
                        onNetWorkCallBack(o, Constant.ANGELBUY_ID, params);
                    }
                });
    }

    //添加收货地址
    public void getAddAddressModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ADDADDRESS + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<AngelBuyModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ADDADDRESS_ID, null);
                    }

                    public void onResponse(AngelBuyModel o) {
                        onNetWorkCallBack(o, Constant.ADDADDRESS_ID, params);
                    }
                });
    }

    //收货地址列表
    public void getAddressModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ADDRESS + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<AddressModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ADDRESS_ID, null);
                    }

                    public void onResponse(AddressModel o) {
                        onNetWorkCallBack(o, Constant.ADDRESS_ID, params);
                    }
                });
    }

    //修改收货地址
    public void getTypeAddressModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.TYPEADDRESS + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.TYPEADDRESS_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.TYPEADDRESS_ID, params);
                    }
                });
    }

    //删除收货地址
    public void getDelAddressModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.DELADDRESS + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.DELADDRESS_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.DELADDRESS_ID, params);
                    }
                });
    }


    //确认订单
    public void getOrderModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ORDER + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<OrderModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ORDER_ID, null);
                    }

                    public void onResponse(OrderModel o) {
                        onNetWorkCallBack(o, Constant.ORDER_ID, params);
                    }
                });
    }

    //支付宝支付数据获取
    public void getAliPayModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ALIPAY + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<AliPayModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ALIPAY_ID, null);
                    }

                    public void onResponse(AliPayModel o) {
                        onNetWorkCallBack(o, Constant.ALIPAY_ID, params);
                    }
                });
    }

    //取消订单
    public void getOrderType(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ORDERTYPE + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<AliPayModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ORDERTYPE_ID, null);
                    }

                    public void onResponse(AliPayModel o) {
                        onNetWorkCallBack(o, Constant.ORDERTYPE_ID, params);
                    }
                });
    }

    //订单列表
    public void getMemOrderModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.MEMORDER + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<OrderListModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.MEMORDER_ID, null);
                    }

                    public void onResponse(OrderListModel o) {
                        onNetWorkCallBack(o, Constant.MEMORDER_ID, params);
                    }
                });
    }

    //订单详情
    public void getOrdersModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ORDERS + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<OrdersModel>() {
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                        request(Constant.NETWORKERROR, Constant.ORDERS_ID, null);
                    }

                    public void onResponse(OrdersModel o) {
                        onNetWorkCallBack(o, Constant.ORDERS_ID, params);
                    }
                });
    }

    //修改密码
    public void getModifyPasswordModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.MODIFYPASSWORD + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.MODIFYPASSWORD_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.MODIFYPASSWORD_ID, params);
                    }
                });
    }


    //修改手机号
    public void getChangeMobileModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.CHANGEMOBILE + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.CHANGEMOBILE_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.CHANGEMOBILE_ID, params);
                    }
                });
    }

    //确认订单
    public void getConfirmModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.CONFIRM + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.CONFIRM_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.CONFIRM_ID, params);
                    }
                });
    }

    //提现申请
    public void getWithDrawMoneyModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.WITHDRAWMOINEY + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.WITHDRAWMOINEY_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.WITHDRAWMOINEY_ID, params);
                    }
                });
    }


    //测试支付
    public void getAddCeshiAlipayModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ADDCESHIALIPAY + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ADDCESHIALIPAY_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.ADDCESHIALIPAY_ID, params);
                    }
                });
    }

    //分红详情
    public void getParticiListModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.PARTICILIST + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<PerticiListModel>() {
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                        request(Constant.NETWORKERROR, Constant.PARTICILIST_ID, null);
                    }

                    public void onResponse(PerticiListModel o) {
                        onNetWorkCallBack(o, Constant.PARTICILIST_ID, params);
                    }
                });
    }

    //删除订单
    public void getOreDeteleModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.OREDETELE + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.OREDETELE_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.OREDETELE_ID, params);
                    }
                });
    }

    //分享
    public void getShareModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.SHARE + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ShareModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.SHARE_ID, null);
                    }

                    public void onResponse(ShareModel o) {
                        onNetWorkCallBack(o, Constant.SHARE_ID, params);
                    }
                });
    }

    //会员退换货
    public void getReturnsModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.RETURNS + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.RETURNS_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.RETURNS_ID, params);
                    }
                });
    }

    //会员退换货列表
    public void getReturnListModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.RETURNLIST + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ReturnListModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.RETURNLIST_ID, null);
                    }

                    public void onResponse(ReturnListModel o) {
                        onNetWorkCallBack(o, Constant.RETURNLIST_ID, params);
                    }
                });
    }

    //会员退换货详情
    public void getReturnDetailModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.RETURNDETAIL + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ReturnDetailModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.RETURNDETAIL_ID, null);
                    }

                    public void onResponse(ReturnDetailModel o) {
                        onNetWorkCallBack(o, Constant.RETURNDETAIL_ID, params);
                    }
                });
    }


    //产品分类列表--1级
    public void getProductTypeModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.PRODUCTTYPE + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ProductTypeModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.PRODUCTTYPE_ID, null);
                    }

                    public void onResponse(ProductTypeModel o) {
                        onNetWorkCallBack(o, Constant.PRODUCTTYPE_ID, params);
                    }
                });
    }


    //产品分类列表--2级
    public void getProductListSonModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.PRODUCTTYPESON + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ProductTypeSonModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.PRODUCTTYPESON_ID, null);
                    }

                    public void onResponse(ProductTypeSonModel o) {
                        onNetWorkCallBack(o, Constant.PRODUCTTYPESON_ID, params);
                    }
                });
    }

    //产品分类列表--2级详细
    public void getProductListModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.PRODUCTLIST + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ProductTypeModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.PRODUCTLIST_ID, null);
                    }

                    public void onResponse(ProductTypeModel o) {
                        onNetWorkCallBack(o, Constant.PRODUCTLIST_ID, params);
                    }
                });
    }

    //产品详情
    public void getProductDetailModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.PRODUCTDETAIL + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ProductDetailModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.PRODUCTDETAIL_ID, null);
                    }

                    public void onResponse(ProductDetailModel o) {
                        onNetWorkCallBack(o, Constant.PRODUCTDETAIL_ID, params);
                    }
                });
    }

    //产品分类列表--2级筛选
    public void getSelectListModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.SELECTLIST + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<SelectListModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.SELECTLIST_ID, null);
                    }

                    public void onResponse(SelectListModel o) {
                        onNetWorkCallBack(o, Constant.SELECTLIST_ID, params);
                    }
                });
    }

    //产品分类列表--2级筛选条件
    public void getSelectModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.SELECT + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<SelectModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.SELECT_ID, null);
                    }

                    public void onResponse(SelectModel o) {
                        onNetWorkCallBack(o, Constant.SELECT_ID, params);
                    }
                });
    }

    //产品评价列表
    public void getProductCommentsModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.PRODUCTCOMMENTS + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ProductCommentsModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.PRODUCTCOMMENTS_ID, null);
                    }

                    public void onResponse(ProductCommentsModel o) {
                        onNetWorkCallBack(o, Constant.PRODUCTCOMMENTS_ID, params);
                    }
                });
    }

    //购物车
    public void getProductCartModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.PRODUCTCART + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<CartModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.PRODUCTCART_ID, null);
                    }

                    public void onResponse(CartModel o) {
                        onNetWorkCallBack(o, Constant.PRODUCTCART_ID, params);
                    }
                });
    }

    //我的积分列表
    public void getIntegralModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INTEGRAL + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<IntegralModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INTEGRAL_ID, null);
                    }

                    public void onResponse(IntegralModel o) {
                        onNetWorkCallBack(o, Constant.INTEGRAL_ID, params);
                    }
                });
    }

    //兑换积分
    public void getIntegralExchangeModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INTEGRALEXCHAGE + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<CartModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INTEGRALEXCHAGE_ID, null);
                    }

                    public void onResponse(CartModel o) {
                        onNetWorkCallBack(o, Constant.INTEGRALEXCHAGE_ID, params);
                    }
                });
    }

    //购物车产品数量修改
    public void getShopNumberModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.SHOPNUMBER + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ShopNumberModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.SHOPNUMBER_ID, null);
                    }

                    public void onResponse(ShopNumberModel o) {
                        onNetWorkCallBack(o, Constant.SHOPNUMBER_ID, params);
                    }
                });
    }

    //交易记录
    public void getTradingrecordModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.TRADINGRECORD + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<TradingrecordModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.TRADINGRECORD_ID, null);
                    }

                    public void onResponse(TradingrecordModel o) {
                        onNetWorkCallBack(o, Constant.TRADINGRECORD_ID, params);
                    }
                });
    }

    //钱包详情
    public void getPocketdetailModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.POCKETDETAIL + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<PocketdetailModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.POCKETDETAIL_ID, null);
                    }

                    public void onResponse(PocketdetailModel o) {
                        onNetWorkCallBack(o, Constant.POCKETDETAIL_ID, params);
                    }
                });
    }

    //银行卡列表
    public void getCardListModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.CARDLIST + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<CardListModel>() {
                    public void onError(Request request, Exception e) {

                        request(Constant.NETWORKERROR, Constant.CARDLIST_ID, null);
                    }

                    public void onResponse(CardListModel o) {
                        onNetWorkCallBack(o, Constant.CARDLIST_ID, params);
                    }
                });
    }

    //删除银行卡
    public void getDelCardModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.DELCARD + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<DelCardModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.DELCARD_ID, null);
                    }

                    public void onResponse(DelCardModel o) {
                        onNetWorkCallBack(o, Constant.DELCARD_ID, params);
                    }
                });
    }

    //添加银行卡
    public void getAddCardModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.ADDCARD + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<DelCardModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.ADDCARD_ID, null);
                    }

                    public void onResponse(DelCardModel o) {
                        onNetWorkCallBack(o, Constant.ADDCARD_ID, params);
                    }
                });
    }

    //修改银行卡
    public void getEditCardModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.EDITCARD + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.EDITCARD_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.EDITCARD_ID, params);
                    }
                });
    }

    //切换默认银行卡
    public void getDefaultCardModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.DEFAULTCARD + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.DEFAULTCARD_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.DEFAULTCARD_ID, params);
                    }
                });
    }


    //提现页面
    public void getMoneyFaceModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.MONEYFACE + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MoneyFaceModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.MONEYFACE_ID, null);
                    }

                    public void onResponse(MoneyFaceModel o) {
                        onNetWorkCallBack(o, Constant.MONEYFACE_ID, params);
                    }
                });
    }

    //我的钱包接口
    public void getMypocketModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.MYPOCKET + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyPocketModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.MYPOCKET_ID, null);
                    }

                    public void onResponse(MyPocketModel o) {
                        onNetWorkCallBack(o, Constant.MYPOCKET_ID, params);
                    }
                });
    }

    //我的钱包接口
    public void getApplyinfoModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.APPLYINFO + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ApplyinfoModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.APPLYINFO_ID, null);
                    }

                    public void onResponse(ApplyinfoModel o) {
                        onNetWorkCallBack(o, Constant.APPLYINFO_ID, params);
                    }
                });
    }

    //商家申请修改接口
    public void getEditapplyModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.EDITAPPLY + MyApplication.getToken() + "," +
                        MyApplication.getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.EDITAPPLY_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.EDITAPPLY_ID, params);
                    }
                });
    }

    //购物车结算
    public void getSettlementModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.SETTLEMENT + MyApplication.getToken() + "," + MyApplication
                        .getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<AngelBuyModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.SETTLEMENT_ID, null);
                    }

                    public void onResponse(AngelBuyModel o) {
                        onNetWorkCallBack(o, Constant.SETTLEMENT_ID, params);
                    }
                });
    }

    //产品详情加入购物车
    public void getJoinModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.JOIN + MyApplication.getToken() + "," + MyApplication.getSoleToken() +
                        "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.JOIN_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.JOIN_ID, params);
                    }
                });
    }

    //未读消息数量ALL
    public void getInstationCountModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INSTATIONCOUNT + MyApplication.getToken() + "," + MyApplication
                        .getSoleToken() + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<InstationCountModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INSTATIONCOUNT_ID, null);
                    }

                    public void onResponse(InstationCountModel o) {
                        onNetWorkCallBack(o, Constant.INSTATIONCOUNT_ID, params);
                    }
                });
    }

    //消息列表 订单消息
    public void getInstation0Model(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INSTATION + MyApplication.getToken() + "," + MyApplication.getSoleToken
                        () + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<Instation0Model>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INSTATION_ID0, null);
                    }

                    public void onResponse(Instation0Model o) {
                        onNetWorkCallBack(o, Constant.INSTATION_ID0, params);
                    }
                });
    }

    //消息列表 提现消息
    public void getInstation1Model(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INSTATION + MyApplication.getToken() + "," + MyApplication.getSoleToken
                        () + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<Instation1Model>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INSTATION_ID1, null);
                    }

                    public void onResponse(Instation1Model o) {
                        onNetWorkCallBack(o, Constant.INSTATION_ID1, params);
                    }
                });
    }


    //消息列表 系统消息
    public void getInstation2Model(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INSTATION + MyApplication.getToken() + "," + MyApplication.getSoleToken
                        () + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<Instation2Model>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INSTATION_ID2, null);
                    }

                    public void onResponse(Instation2Model o) {
                        onNetWorkCallBack(o, Constant.INSTATION_ID2, params);
                    }
                });
    }


    //消息列表 奖励——分红
    public void getInstation30Model(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INSTATION + MyApplication.getToken() + "," + MyApplication.getSoleToken
                        () + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<Instation30Model>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INSTATION_ID30, null);
                    }

                    public void onResponse(Instation30Model o) {
                        onNetWorkCallBack(o, Constant.INSTATION_ID30, params);
                    }
                });
    }


    //消息列表 奖励——分销
    public void getInstation31Model(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INSTATION + MyApplication.getToken() + "," + MyApplication.getSoleToken
                        () + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<Instation31Model>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INSTATION_ID31, null);
                    }

                    public void onResponse(Instation31Model o) {
                        onNetWorkCallBack(o, Constant.INSTATION_ID31, params);
                    }
                });
    }


    //消息列表 奖励——合伙人
    public void getInstation32Model(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.INSTATION + MyApplication.getToken() + "," + MyApplication.getSoleToken
                        () + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<Instation32Model>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.INSTATION_ID32, null);
                    }

                    public void onResponse(Instation32Model o) {
                        onNetWorkCallBack(o, Constant.INSTATION_ID32, params);
                    }
                });
    }

    //购物车删除产品
    public void getShoppingDModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.SHOPPINGD + MyApplication.getToken() + "," + MyApplication.getSoleToken
                        () + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<ShoppingDModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.SHOPPINGD_ID, null);
                    }

                    public void onResponse(ShoppingDModel o) {
                        onNetWorkCallBack(o, Constant.SHOPPINGD_ID, params);
                    }
                });
    }

    //消息读取
    public void getReadModel(final OkHttpClientManager.Param[] params) {
        OkHttpClientManager.postAsyn(Constant.READ + MyApplication.getToken() + "," + MyApplication.getSoleToken
                        () + "," + MyApplication.getUser_id(),
                params, new OkHttpClientManager.ResultCallback<MyBaseModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.READ_ID, null);
                    }

                    public void onResponse(MyBaseModel o) {
                        onNetWorkCallBack(o, Constant.READ_ID, params);
                    }
                });
    }
    //获取APK 信息
    public void getNewApk(final OkHttpClientManager.Param[] params){
        OkHttpClientManager.postAsyn(Constant.REQUEST_APK
                        +MyApplication.getToken()
                        + "," + MyApplication.getSoleToken()
                        + "," + MyApplication.getUser_id(),
                new OkHttpClientManager.Param[]{},
                new OkHttpClientManager.ResultCallback<ApkModel>() {
                    public void onError(Request request, Exception e) {
                        request(Constant.NETWORKERROR, Constant.REQUEST_APK_ID, null);
                    }

                    @Override
                    public void onResponse(ApkModel o) {
                        onNetWorkCallBack(o, Constant.REQUEST_APK_ID,params);
                    }
                });
    }

    protected void onNetWorkCallBack(MyBaseModel o, final int netWork_Id, final OkHttpClientManager.Param[] params) {
        switch (o.getErrcode()) {
            case Constant.TOKENTIMEOUT:
                getTokenModel(netWork_Id, params);
                break;
            default:
                request(o.getErrcode(), netWork_Id, o);
        }
    }

    private void onTokenTimeOut(int netWork_Id, OkHttpClientManager.Param[] params) {
        switch (netWork_Id) {
            case Constant.TOKEN_ID://1
                break;
            case Constant.REGISTER_ID://2
                getRegisterModel(params);
                break;
            case Constant.SEND_ID0://30
                getSendCode0Model(params);
                break;
            case Constant.SEND_ID1://31
                getSendCode1Model(params);
                break;
            case Constant.LOGIN_ID://4
                getLoginModel(params);
                break;
            case Constant.GETMODEL_ID:
                getUserInfoModel(params);
                break;
            case Constant.CHANGEPASSWORD_ID:
                getChangePassWordModel(params);
                break;
            case Constant.ALLBIND_ID:
                getAllBindModel(params);
                break;
            case Constant.MEMBERBIND_ID:
                getMemberBindModel(params);
                break;
            case Constant.IDCARDBIND_ID:
                getIdCardBindModel(params);
                break;
            case Constant.CHANGENAME_ID:
                getChangeNameModel(params);
                break;
            case Constant.CHANGESEX_ID:
                getChangeSexModel(params);
                break;
            case Constant.CHANGEBIRTHDAY_ID:
                getChangeBirthdayModel(params);
                break;
            case Constant.CHANGEEMAIL_ID:
                getChangeEmailModel(params);
                break;
            case Constant.HOMEANGEL_ID:
                getHomeAngelModel(params);
                break;
            case Constant.HOMEBANRND_ID:
                getHomeBanrndModel(params);
                break;
            case Constant.ANGELBUY_ID:
                getAngelBuyModel(params);
                break;
            case Constant.ADDADDRESS_ID:
                getAddAddressModel(params);
                break;
            case Constant.ADDRESS_ID:
                getAddressModel(params);
                break;
            case Constant.TYPEADDRESS_ID:
                getTypeAddressModel(params);
                break;
            case Constant.DELADDRESS_ID:
                getDelAddressModel(params);
                break;
            case Constant.ORDER_ID:
                getOrderModel(params);
                break;
            case Constant.ALIPAY_ID:
                getAliPayModel(params);
                break;
            case Constant.ORDERTYPE_ID:
                getOrderType(params);
                break;
            case Constant.MEMORDER_ID:
                getMemOrderModel(params);
                break;
            case Constant.ORDERS_ID:
                getOrdersModel(params);
                break;
            case Constant.MODIFYPASSWORD_ID:
                getModifyPasswordModel(params);
                break;
            case Constant.CHANGEMOBILE_ID:
                getChangeMobileModel(params);
                break;
            case Constant.WITHDRAWMOINEY_ID:
                getWithDrawMoneyModel(params);
                break;
            case Constant.PARTICILIST_ID:
                getParticiListModel(params);
                break;
            case Constant.OREDETELE_ID:
                getOreDeteleModel(params);
                break;
            case Constant.SHARE_ID:
                getShareModel(params);
                break;
            case Constant.RETURNS_ID:
                getReturnsModel(params);
                break;
            case Constant.PRODUCTTYPE_ID:
                getProductTypeModel(params);
                break;
            case Constant.PRODUCTTYPESON_ID:
                getProductListSonModel(params);
                break;
            case Constant.PRODUCTLIST_ID:
                getProductListModel(params);
                break;
            case Constant.PRODUCTDETAIL_ID:
                getProductDetailModel(params);
                break;
            case Constant.SELECTLIST_ID:
                getSelectListModel(params);
                break;
            case Constant.SELECT_ID:
                getSelectModel(params);
                break;
            case Constant.PRODUCTCOMMENTS_ID:
                getProductCommentsModel(params);
                break;
            case Constant.PRODUCTCART_ID:
                getProductCartModel(params);
                break;
            case Constant.INTEGRAL_ID:
                getIntegralModel(params);
                break;
            case Constant.INTEGRALEXCHAGE_ID:
                getIntegralExchangeModel(params);
                break;
            case Constant.SHOPNUMBER_ID:
                getShopNumberModel(params);
                break;
            case Constant.POCKETDETAIL_ID:
                getPocketdetailModel(params);
                break;
            case Constant.CARDLIST_ID:
                getCardListModel(params);
                break;
            case Constant.DELCARD_ID:
                getDelCardModel(params);
                break;
            case Constant.ADDCARD_ID:
                getAddCardModel(params);
                break;
            case Constant.EDITCARD_ID:
                getEditCardModel(params);
                break;
            case Constant.DEFAULTCARD_ID:
                getDefaultCardModel(params);
                break;
            case Constant.MONEYFACE_ID:
                getMoneyFaceModel(params);
                break;
            case Constant.SETTLEMENT_ID:
                getSettlementModel(params);
                break;
            case Constant.JOIN_ID:
                getJoinModel(params);
                break;
            case Constant.INSTATIONCOUNT_ID:
                getInstationCountModel(params);
                break;
            case Constant.SHOPPINGD_ID:
                getShoppingDModel(params);
                break;
            case Constant.MYPOCKET_ID:
                getMypocketModel(params);
                break;
            case Constant.APPLYINFO_ID:
                getApplyinfoModel(params);
                break;
            case Constant.EDITAPPLY_ID:
                getEditapplyModel(params);
                break;
            case Constant.READ_ID:
                getReadModel(params);
                break;
            case Constant.RETURNLIST_ID:
                getReturnListModel(params);
                break;
            case Constant.RETURNDETAIL_ID:
                getReturnDetailModel(params);
                break;
        }
    }

    /**
     * status_code状态：
     * -2网络请求失败
     * 0请求成功
     */
    protected <T extends MyBaseModel> void request(int status_code, int network_code, T model) {
    }


}
