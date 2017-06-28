package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/4/10.
 */
public class ProductTypeModel extends MyBaseModel {


    /**
     * ProductTypeId : 5
     * TypeName : 小天使产品
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int ProductTypeId;
        private String TypeName;

        public void setProductTypeId(int ProductTypeId) {
            this.ProductTypeId = ProductTypeId;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public int getProductTypeId() {
            return ProductTypeId;
        }

        public String getTypeName() {
            return TypeName;
        }
    }
}
