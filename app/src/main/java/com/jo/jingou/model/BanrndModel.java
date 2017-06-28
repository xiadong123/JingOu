package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2016/12/6.
 */
public class BanrndModel extends MyBaseModel {

    /**
     * Text : null
     * Value : http://jingou.idea-source.net/assets/uploads/20161205/2016120513444083670.jpg
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private Object Text;
        private String Value;

        public void setText(Object Text) {
            this.Text = Text;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public Object getText() {
            return Text;
        }

        public String getValue() {
            return Value;
        }
    }
}
