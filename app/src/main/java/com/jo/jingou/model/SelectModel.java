package com.jo.jingou.model;

import com.jo.jingou.base.MyBaseModel;

import java.util.List;

/**
 * Created by dfyu on 2017/4/12.
 */
public class SelectModel extends MyBaseModel {

    /**
     * Id : 7
     * Name : 规格
     * Model : [{"ProductparameterId":18,"ProductparameterName":"L"},{"ProductparameterId":19,"ProductparameterName":"M"},{"ProductparameterId":20,"ProductparameterName":"XL"},{"ProductparameterId":21,"ProductparameterName":"XXL"}]
     */

    private List<ListEntity> list;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int Id;
        private String Name;
        /**
         * ProductparameterId : 18
         * ProductparameterName : L
         */

        private List<ModelEntity> Model;

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setModel(List<ModelEntity> Model) {
            this.Model = Model;
        }

        public int getId() {
            return Id;
        }

        public String getName() {
            return Name;
        }

        public List<ModelEntity> getModel() {
            return Model;
        }

        public static class ModelEntity {
            private int ProductparameterId;
            private String ProductparameterName;
            public boolean isChecked = false;//是否选择
            private String sortLetters;//首字母 排序用

            public void setProductparameterId(int ProductparameterId) {
                this.ProductparameterId = ProductparameterId;
            }

            public void setProductparameterName(String ProductparameterName) {
                this.ProductparameterName = ProductparameterName;
            }

            public int getProductparameterId() {
                return ProductparameterId;
            }

            public String getProductparameterName() {
                return ProductparameterName;
            }

            public String getSortLetters() {
                //取首字母，转大写
                return Character.toUpperCase(sortLetters.charAt(0)) + "";
            }

            public void setSortLetters(String sortLetters) {
                this.sortLetters = sortLetters;
            }
        }
    }
}
