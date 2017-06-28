package com.jo.jingou.utils.pinyinpaixu;

public class SortModel {

    private String name;   //显示的数据  
    private String sortLetters;  //显示数据拼音的首字母  


    public SortModel() {
    }

    public SortModel(String name, String sortLetters) {
        this.name = name;
        this.sortLetters = sortLetters;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        //取首字母，转大写
        return Character.toUpperCase(sortLetters.charAt(0)) + "";
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}  