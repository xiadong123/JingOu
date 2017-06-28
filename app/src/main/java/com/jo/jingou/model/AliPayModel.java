package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2016/12/9.
 */
public class AliPayModel extends MyBaseModel {


    /**
     * data : _input_charset="utf-8"&notify_url="http://jingou.idea-source.net/cn/alipay/notifyurl"&out_trade_no=",HFGIN7CV20161209172940,"&partner="2088421552051944"&payment_type="1"&seller_id="2088421552051944"&service="create_direct_pay_by_user"&subject="静欧平台订单"&total_fee="600.00"&sign="AdEZhQ%2fOe5%2bEGjNX1ItOvdThYoxC5K4ZBWghxaSSkYh7UjO%2fGoYBgwXfUgnR88B7PAU%2fm6xZ8enJDdjNkl5%2frukK9685NLSTW5MiJ%2bJ8TRdwK7TmDBP5QX0bfaeckAOLgmEJy3odI2iiCX69rht6L75eeF%2fk2p6AfPeZHGG%2fMS8%3d"&sign_type="MD5"
     */

    private String data;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
