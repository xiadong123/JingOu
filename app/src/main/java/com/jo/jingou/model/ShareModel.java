package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2017/3/24.
 */
public class ShareModel extends MyBaseModel {

    /**
     * invitation : http://www.lytx999.com//home/detail?mecode=RK79473455
     * MeCode : RK79473455
     */

    private String invitation;
    private String MeCode;

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public void setMeCode(String MeCode) {
        this.MeCode = MeCode;
    }

    public String getInvitation() {
        return invitation;
    }

    public String getMeCode() {
        return MeCode;
    }
}
