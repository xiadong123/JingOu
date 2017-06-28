package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

/**
 * Created by dfyu on 2017/5/3.
 */
public class InstationCountModel extends MyBaseModel {

    /**
     * count1 : 0
     * count2 : 0
     * count3 : 0
     * count4 : 0
     */

    private int count1;
    private int count2;
    private int count3;
    private int count4;

    public void setCount1(int count1) {
        this.count1 = count1;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public void setCount3(int count3) {
        this.count3 = count3;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }

    public int getCount1() {
        return count1;
    }

    public int getCount2() {
        return count2;
    }

    public int getCount3() {
        return count3;
    }

    public int getCount4() {
        return count4;
    }
}
