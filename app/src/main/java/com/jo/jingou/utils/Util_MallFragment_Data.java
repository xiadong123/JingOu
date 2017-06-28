package com.jo.jingou.utils;

import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.ProductTypeModel;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2017/4/10.
 */
public class Util_MallFragment_Data {

    private static Listener_MallFragment_Data listener_mallFragment_data;
    private static ProductTypeModel productTypeModel;

    private static Util_Network util_network = new Util_Network() {
        @Override
        protected <T extends MyBaseModel> void request(int status_code, int network_code, T model) {
            super.request(status_code, network_code, model);
            switch (network_code) {
                case Constant.PRODUCTTYPE_ID:
                    if (status_code == 0) {
                        productTypeModel = (ProductTypeModel) model;
                        if (listener_mallFragment_data != null) {
                            listener_mallFragment_data.onProductTypeSuccess(productTypeModel);
                        }
                    } else {

                    }
                    break;
            }

        }
    };

    public static void netProductTypeModel() {
        util_network.getProductTypeModel(new OkHttpClientManager.Param[]{});
    }

    public static ProductTypeModel getProductTypeModel() {
        if (productTypeModel != null) {
            return productTypeModel;
        } else {
            netProductTypeModel();
            return null;
        }
    }


    public static void setListener_mallFragment_data(Listener_MallFragment_Data listener_mallFragment_data) {
        Util_MallFragment_Data.listener_mallFragment_data = listener_mallFragment_data;
    }

    public interface Listener_MallFragment_Data {
        public void onProductTypeSuccess(ProductTypeModel productTypeModel);
    }
}
