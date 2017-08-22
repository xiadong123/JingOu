package com.jo.jingou.utils;

/**
 * Created by dfyu on 2016/8/29.
 */
public final class Constant {

    //百度地图key：3wpuaV2H3L1mgOMTzRGS6dGNuGWZOzGs

    public final static String BYTYPE = "byType";

    public final static int CANTOBUY = 111;
    public final static int CANTTOBUY = 222;


    public final static String SHAREDPREFERENCES_NAME = "jingou-cache";

    public final static String PACKAGENAME = "com.idea_source.jingou";

    public final static int IMGCALLBACK = 101;

    public final static int IMGCALLBACKCUT = 102;

    //网络请求失败
    public final static int NETWORKERROR = -2;
    //网络请求成功
    public final static int NETWORKSUCCESS = 0;
    //token超时
    public final static int TOKENTIMEOUT = 40004;
    //登录失效
    public final static int LOGININVAILD = 40841;

    //服务器地址
    //静欧公司正式网址
//    public final static String SERVER_HOST = "http://www.lytx8888.com";

    //原外包测试网址
    //public final static String SERVER_HOST = "http://jingou.idea-source.net";
    //public final static String SERVER_HOST = "http://woerde.idea-source.net";
    //public final static String SERVER_HOST = "http://192.168.0.66:8037";

    //阿里云网址13816910087
    public final static String SERVER_HOST = "http://120.27.240.229:8022";

    //获取请求接口权限token
    public final static int TOKEN_ID = 1001;
    public final static String TOKEN = SERVER_HOST + "/api/auth/apploginauth/token";

    //注册
    public final static int REGISTER_ID = 1002;
    public final static String REGISTER = SERVER_HOST + "/api/app/member/Register?token=";


    //发送验证码
    public final static int SEND_ID0 = 10030;
    public final static int SEND_ID1 = 10031;
    public final static String SEND = SERVER_HOST + "/api/app/member/Send?token=";

    //登录
    public final static int LOGIN_ID = 1004;
    public final static String LOGIN = SERVER_HOST + "/api/app/member/Login?token=";

    //重置密码
    public final static int CHANGEPASSWORD_ID = 1005;
    public final static String CHANGEPASSWORD = SERVER_HOST +
            "/api/app/member/ChangePassWord?token=";


    //完善信息 邀请码+实名认证 AllBind
    public final static int ALLBIND_ID = 1006;
    public final static String ALLBIND = SERVER_HOST + "/api/app/member/AllBind?token=";

    //完善信息 邀请码
    public final static int MEMBERBIND_ID = 10061;
    public final static String MEMBERBIND = SERVER_HOST + "/api/app/member/MemberBind?token=";

    //完善信息 身份证
    public final static int IDCARDBIND_ID = 10062;
    public final static String IDCARDBIND = SERVER_HOST + "/api/app/member/idCardBind?token=";

    //上传头像
    public final static int CNUPLOAD_ID = 10063;
    public final static String CNUPLOAD = SERVER_HOST + "/api/app/member/CnUpload?token=";

    //修改姓名
    public final static int CHANGENAME_ID = 10064;
    public final static String CHANGENAME = SERVER_HOST + "/api/app/member/ChangeName?token=";
    //修改性别
    public final static int CHANGESEX_ID = 10065;
    public final static String CHANGESEX = SERVER_HOST + "/api/app/member/ChangeSex?token=";
    //修改生日
    public final static int CHANGEBIRTHDAY_ID = 10066;
    public final static String CHANGEBIRTHDAY = SERVER_HOST +
            "/api/app/member/ChangeBirthday?token=";
    //修改邮箱
    public final static int CHANGEEMAIL_ID = 10067;
    public final static String CHANGEEMAIL = SERVER_HOST + "/api/app/member/ChangeEmail?token=";

    //首页信息 home/angel
    public final static int HOMEANGEL_ID = 1007;
    public final static String HOMEANGEL = SERVER_HOST + "/api/app/home/angel?token=";

    //首页信息 home/Banrnd
    public final static int HOMEBANRND_ID = 1008;
    public final static String HOMEBANRND = SERVER_HOST + "/api/app/home/Banrnd?token=";

    //购买小天使 product/angelbuy
    public final static int ANGELBUY_ID = 1009;
    public final static String ANGELBUY = SERVER_HOST + "/api/app/product/angelbuy?token=";

    //添加收货地址
    public final static int ADDADDRESS_ID = 10100;
    public final static String ADDADDRESS = SERVER_HOST + "/api/app/product/AddAddress?token=";

    //收货地址列表
    public final static int ADDRESS_ID = 10101;
    public final static String ADDRESS = SERVER_HOST + "/api/app/product/Address?token=";

    //修改收货地址
    public final static int TYPEADDRESS_ID = 10102;
    public final static String TYPEADDRESS = SERVER_HOST + "/api/app/product/TypeAddress?token=";

    //删除收货地址
    public final static int DELADDRESS_ID = 10103;
    public final static String DELADDRESS = SERVER_HOST + "/api/app/product/DelAddress?token=";

    //确认订单
    public final static int ORDER_ID = 1011;
    public final static String ORDER = SERVER_HOST + "/api/app/product/order?token=";

    //支付宝支付数据获取
    public final static int ALIPAY_ID = 1012;
    public final static String ALIPAY = SERVER_HOST + "/api/app/participation/Signature?token=";


    //取消订单
    public final static int ORDERTYPE_ID = 1013;
    public final static String ORDERTYPE = SERVER_HOST + "/api/app/product/OrderType?token=";


    //获取会员model
    public final static int GETMODEL_ID = 1014;
    public final static String GETMODEL = SERVER_HOST + "/api/app/member/GetModel?token=";

    //订单列表
    public final static int MEMORDER_ID = 1015;
    public final static String MEMORDER = SERVER_HOST + "/api/app/product/MemOrder?token=";

    //订单详情
    public final static int ORDERS_ID = 1016;
    public final static String ORDERS = SERVER_HOST + "/api/app/product/Orders?token=";

    //修改密码  不是忘记密码
    public final static int MODIFYPASSWORD_ID = 1017;
    public final static String MODIFYPASSWORD = SERVER_HOST +
            "/api/app/member/ModifyPassWord?token=";

    //修改手机号--修改账号
    public final static int CHANGEMOBILE_ID = 1018;
    public final static String CHANGEMOBILE = SERVER_HOST + "/api/app/member/ChangeMobile?token=";

    //订单确认收货
    public final static int CONFIRM_ID = 1019;
    public final static String CONFIRM = SERVER_HOST + "/api/app/product/Confirm?token=";

    //提现申请 WithdrawMoney
    public final static int WITHDRAWMOINEY_ID = 1020;
    public final static String WITHDRAWMOINEY = SERVER_HOST +
            "/api/app/member/WithdrawMoney?token=";

    //分红详情
    public final static int PARTICILIST_ID = 1021;
    public final static String PARTICILIST = SERVER_HOST +
            "/api/app/participation/ParticiList?token=";

    //删除订单
    public final static int OREDETELE_ID = 1022;
    public final static String OREDETELE = SERVER_HOST + "/api/app/product/OreDelete?token=";

    //评价接口
    public final static int COMMENT_ID = 1023;
    public final static String COMMENT = SERVER_HOST + "/api/app/product/Comment?token=";

    //分享
    public final static int SHARE_ID = 1024;
    public final static String SHARE = SERVER_HOST + "/api/app/product/Share?token=";

    //会员退换货
    public final static int RETURNS_ID = 1025;
    public final static String RETURNS = SERVER_HOST + "/api/app/product/Returns?token=";

    //产品分类——1级 /api/app/product/producttype?token
    public final static int PRODUCTTYPE_ID = 1026;
    public final static String PRODUCTTYPE = SERVER_HOST + "/api/app/product/producttype?token=";

    //产品分类——2级 /api/app/product/producttypeson?token
    public final static int PRODUCTTYPESON_ID = 1027;
    public final static String PRODUCTTYPESON = SERVER_HOST +
            "/api/app/product/producttypeson?token=";

    //产品分类——2级详细 /api/app/product/productlist?token
    public final static int PRODUCTLIST_ID = 1028;
    public final static String PRODUCTLIST = SERVER_HOST + "/api/app/product/productlist?token=";

    //产品详情 /api/app/product/productdetail?token
    public final static int PRODUCTDETAIL_ID = 1029;
    public final static String PRODUCTDETAIL = SERVER_HOST +
            "/api/app/product/productdetail?token=";

    //产品分类——2级筛选 /api/app/product/selectlist?token
    public final static int SELECTLIST_ID = 1030;
    public final static String SELECTLIST = SERVER_HOST + "/api/app/product/selectlist?token=";

    //产品分类——2级筛选条件/api/app/product/select?token
    public final static int SELECT_ID = 1031;
    public final static String SELECT = SERVER_HOST + "/api/app/product/select?token=";

    //产品评价列表 /api/app/product/productcomments?token
    public final static int PRODUCTCOMMENTS_ID = 1032;
    public final static String PRODUCTCOMMENTS = SERVER_HOST +
            "/api/app/product/productcomments?token=";

    //购物车列表 /api/app/product/Cart?token
    public final static int PRODUCTCART_ID = 1033;
    public final static String PRODUCTCART = SERVER_HOST + "/api/app/product/Cart?token=";

    //我的积分列表 /api/app/participation/Integral?token
    public final static int INTEGRAL_ID = 1034;
    public final static String INTEGRAL = SERVER_HOST + "/api/app/participation/Integral?token=";

    //积分兑换 /api/app/participation/IntegralExchange?token
    public final static int INTEGRALEXCHAGE_ID = 1035;
    public final static String INTEGRALEXCHAGE = SERVER_HOST +
            "/api/app/participation/IntegralExchange?token=";

    //购物车修改产品数量 /api/app/product/ShopNumber?token
    public final static int SHOPNUMBER_ID = 1036;
    public final static String SHOPNUMBER = SERVER_HOST + "/api/app/product/ShopNumber?token=";

    //交易记录 /api/app/apperic/tradingrecord?token
    public final static int TRADINGRECORD_ID = 2001;
    public final static String TRADINGRECORD = SERVER_HOST +
            "/api/app/apperic/tradingrecord?token=";

    //钱包详情 /api/app/apperic/pocketdetail?token
    public final static int POCKETDETAIL_ID = 2002;
    public final static String POCKETDETAIL = SERVER_HOST +
            "/api/app/apperic/pocketdetail?token=";

    //银行卡列表  /api/app/member/Card?token
    public final static int CARDLIST_ID = 2003;
    public final static String CARDLIST = SERVER_HOST +
            "/api/app/member/Card?token=";

    //删除银行卡  /api/app/member/DelCard?token
    public final static int DELCARD_ID = 2004;
    public final static String DELCARD = SERVER_HOST +
            "/api/app/member/DelCard?token=";

    //添加银行卡  /api/app/member/AddCard?token
    public final static int ADDCARD_ID = 2005;
    public final static String ADDCARD = SERVER_HOST +
            "/api/app/member/AddCard?token=";

    //修改银行卡  /api/app/member/EditCard?token
    public final static int EDITCARD_ID = 2006;
    public final static String EDITCARD = SERVER_HOST +
            "/api/app/member/EditCard?token=";

    //切换默认银行卡 /api/app/member/DefaultCard?token
    public final static int DEFAULTCARD_ID = 2007;
    public final static String DEFAULTCARD = SERVER_HOST +
            "/api/app/member/DefaultCard?token=";

    //8.9 提现页面 /api/app/member/MoneyFace?token
    public final static int MONEYFACE_ID = 2008;

    public final static String MONEYFACE = SERVER_HOST +
            "/api/app/member/MoneyFace?token=";

    //  商家申请接口 /api/app/apperic/applymerchant?token
    public final static int APPLYMERCHANT_ID = 2009;
    public final static String APPLYMERCHANT = SERVER_HOST +
            "/api/app/apperic/applymerchant?token=";

    //  商家申请修改接口 /api/app/apperic/editapply?token
    public final static int EDITAPPLY_ID = 2012;
    public final static String EDITAPPLY = SERVER_HOST +
            "/api/app/apperic/editapply?token=";

    //我的钱包接口 /api/app/apperic/mypocket?token
    public final static int MYPOCKET_ID = 2010;
    public final static String MYPOCKET = SERVER_HOST +
            "/api/app/apperic/mypocket?token=";

    //商家申请详情 /api/app/apperic/getapplyinfo?token
    public final static int APPLYINFO_ID = 2011;
    public final static String APPLYINFO = SERVER_HOST +
            "/api/app/apperic/getapplyinfo?token=";

    //购物车结算 /api/app/product/Settlement?token
    public final static int SETTLEMENT_ID = 1037;
    public final static String SETTLEMENT = SERVER_HOST + "/api/app/product/Settlement?token=";

    //产品详情加入购物车 /api/app/product/Join?token
    public final static int JOIN_ID = 1038;
    public final static String JOIN = SERVER_HOST + "/api/app/product/Join?token=";

    //未读消息数量接口ALL /api/app/member/instationcount?token
    public final static int INSTATIONCOUNT_ID = 1039;
    public final static String INSTATIONCOUNT = SERVER_HOST + "/api/app/member/instationcount?token=";

    //消息列表 /api/app/member/instation?token
    public final static int INSTATION_ID0 = 10400;//订单消息
    public final static int INSTATION_ID1 = 10401;//提现消息
    public final static int INSTATION_ID2 = 10402;//系统消息
    public final static int INSTATION_ID30 = 104030;//奖励——分红
    public final static int INSTATION_ID31 = 104031;//奖励——分销
    public final static int INSTATION_ID32 = 104032;//奖励——合伙人
    public final static String INSTATION = SERVER_HOST + "/api/app/member/instation?token=";

    //购物车产品删除 /api/app/product/Shoppingd?token
    public final static int SHOPPINGD_ID = 1041;
    public final static String SHOPPINGD = SERVER_HOST + "/api/app/product/Shoppingd?token=";

    //消息已读 /api/app/member/Read?token
    public final static int READ_ID = 1042;
    public final static String READ = SERVER_HOST + "/api/app/member/Read?token=";

    //会员退换货列表
    public final static int RETURNLIST_ID = 1043;
    public final static String RETURNLIST = SERVER_HOST + "/api/app/product/returnlist?token=";

    //会员退换货列表 /api/app/product/returndetail?token
    public final static int RETURNDETAIL_ID = 1044;
    public final static String RETURNDETAIL = SERVER_HOST + "/api/app/product/returndetail?token=";


    //apk 查询 /api/app/Home/GetVersion?token
    public static final int REQUEST_APK_ID=1045;
    public static final String REQUEST_APK=SERVER_HOST+"/api/app/home/GetVersion?token=";

    //测试支付接口 /api/app/participation/AddCeshiAlipay
    public final static int ADDCESHIALIPAY_ID = 2000;
    public final static String ADDCESHIALIPAY = SERVER_HOST + "/api/app/participation/WeiXinPay?token=";


}
