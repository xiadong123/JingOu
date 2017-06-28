package com.jo.jingou.utils.pinyinpaixu;

import com.jo.jingou.model.Area2Model;

import java.util.Comparator;

public class PinyinComparator2 implements Comparator<Area2Model.AreaListEntity> {

    public int compare(Area2Model.AreaListEntity o1, Area2Model.AreaListEntity o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序  
        if (o2.getFirst_letter().equals("#")) {
            return -1;
        } else if (o1.getFirst_letter().equals("#")) {
            return 1;
        } else {
            return o1.getFirst_letter().compareTo(o2.getFirst_letter());
        }
    }
}  