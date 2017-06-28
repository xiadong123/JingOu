package com.jo.jingou.utils.pinyinpaixu;

import com.jo.jingou.model.SelectModel;

import java.util.Comparator;

public class PinyinComparator implements Comparator<SelectModel.ListEntity.ModelEntity> {

    public int compare(SelectModel.ListEntity.ModelEntity o1, SelectModel.ListEntity.ModelEntity o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序  
        if (o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}  