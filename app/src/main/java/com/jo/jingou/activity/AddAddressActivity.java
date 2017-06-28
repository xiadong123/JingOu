package com.jo.jingou.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.AddressModel;
import com.jo.jingou.model.AreaModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_PopupWindow;
import com.jo.jingou.view.switchbutton.SwitchButton;

import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/7.
 */
public class AddAddressActivity extends MyBaseActivity {


    AreaModel areaModel;
    PopupWindow popupWindow_areapicker;

    EditText name_ed, phone_ed;
    View address_layout;
    TextView address_tv;
    EditText address_ed;
    SwitchButton switchButton;


    String address, city, area;

    int updata = 0;
    int memberPlaceId;
    AddressModel.ModelPlacesEntity place;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            cancelLoadingDialog();
            showAreaPicker();
        }
    };

    @Override
    public void initData() {
        layoutId = R.layout.activity_addaddress;
    }

    @Override
    public void findViews() {

        name_ed = findViewAndSetClick(R.id.activity_addaddress_name_ed);
        phone_ed = findViewAndSetClick(R.id.activity_addaddress_phone_ed);

        address_layout = findViewAndSetClick(R.id.activity_addaddress_address_layout);
        address_tv = (TextView) findViewById(R.id.activity_addaddress_address0);

        address_ed = (EditText) findViewById(R.id.activity_addaddress_address1_ed);
        switchButton = (SwitchButton) findViewById(R.id.activity_addaddress_switchbutton);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {

        setTopview2("添加收货地址");
        righttxt.setVisibility(View.VISIBLE);
        righttxt.setText("保存");
        righttxt.setTextColor(getResources().getColor(R.color.gary4));

        updata = getIntent().getIntExtra("updata", 0);

        if (updata == 1) {
            place = (AddressModel.ModelPlacesEntity) getIntent().getSerializableExtra("entity");
            name_ed.setText(place.getName());
            phone_ed.setText(place.getTel());
            address = place.getProvince();
            city = place.getCity();
            area = place.getCounty();
            address_tv.setText(address + " " + city + " " + area);
            address_ed.setText(place.getAddress());
            if (place.getType() == 0) {
                switchButton.setChecked(false);
            } else {
                switchButton.setChecked(true);
            }
            memberPlaceId = place.getMemberPlaceId();
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.base_topview_right:

                if (updata == 0)
                    saveAddress();
                else if (updata == 1)
                    updataAddress();
                break;
            case R.id.activity_addaddress_address_layout:
                showAreaPickPupopWindowOpen();
                break;
        }
    }

    public void showAreaPickPupopWindowOpen() {
        if (areaModel == null) {
            showLoadingDialog("正在加载地区数据，请稍后", false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    areaModel = MyUtils.formatAreaJson(AddAddressActivity.this, handler);
                }
            }).start();
        } else {
            showAreaPicker();
        }
    }

    private void showAreaPicker() {
        if (popupWindow_areapicker == null) {
            popupWindow_areapicker = Util_PopupWindow.setCityPopupWindow(AddAddressActivity.this, areaModel, new Util_PopupWindow.OnCityCallBack() {
                @Override
                public void onCityCallBack(String address, String city, String area) {
                    AddAddressActivity.this.address = address;
                    AddAddressActivity.this.city = city;
                    AddAddressActivity.this.area = area;

                    address_tv.setText(address + " " + city + " " + area);
                }
            }, "城市列表");
        }
        Util_PopupWindow.show(popupWindow_areapicker, AddAddressActivity.this);
    }

    public void saveAddress() {
        String name, phone, address1, def = "0";

        if (!MyUtils.hasValue(name_ed.getText().toString().trim())) {
            showToast("请输入收货人");
            return;
        } else {
            name = name_ed.getText().toString();
        }
        if (!MyUtils.hasValue(phone_ed.getText().toString().trim())) {
            showToast("请输入联系电话");
            return;
        } else {
            phone = phone_ed.getText().toString();
        }
        if (!MyUtils.hasValue(address_tv.getText().toString().trim())) {
            showToast("请选择地区");
            return;
        }
        if (!MyUtils.hasValue(address_ed.getText().toString().trim())) {
            showToast("请输入详细地址");
            return;
        } else {
            address1 = address_ed.getText().toString();
        }


        if (switchButton.isChecked()) {
            def = "1";
        } else {
            def = "0";
        }
        showLoadingDialog();
        utilNetwork.getAddAddressModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("name", name),
                        new OkHttpClientManager.Param("tel", phone),
                        new OkHttpClientManager.Param("province", address),
                        new OkHttpClientManager.Param("city", city),
                        new OkHttpClientManager.Param("county", area),
                        new OkHttpClientManager.Param("address", address1),
                        new OkHttpClientManager.Param("defaults", def)});
    }


    public void updataAddress() {
        String name, phone, address1, def = "0";

        if (!MyUtils.hasValue(name_ed.getText().toString().trim())) {
            showToast("请输入收货人");
            return;
        } else {
            name = name_ed.getText().toString();
        }
        if (!MyUtils.hasValue(phone_ed.getText().toString().trim())) {
            showToast("请输入联系电话");
            return;
        } else {
            phone = phone_ed.getText().toString();
        }
        if (!MyUtils.hasValue(address_tv.getText().toString().trim())) {
            showToast("请选择地区");
            return;
        }
        if (!MyUtils.hasValue(address_ed.getText().toString().trim())) {
            showToast("请输入详细地址");
            return;
        } else {
            address1 = address_ed.getText().toString();
        }

        if (switchButton.isChecked()) {
            def = "1";
        } else {
            def = "0";
        }


        utilNetwork.getTypeAddressModel(
                new OkHttpClientManager.Param[]{
                        new OkHttpClientManager.Param("name", name),
                        new OkHttpClientManager.Param("tel", phone),
                        new OkHttpClientManager.Param("province", address),
                        new OkHttpClientManager.Param("city", city),
                        new OkHttpClientManager.Param("county", area),
                        new OkHttpClientManager.Param("address", address1),
                        new OkHttpClientManager.Param("defaults", def),
                        new OkHttpClientManager.Param("memberplaceid", memberPlaceId + "")});
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.ADDADDRESS_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    this.finish();
                } else {
                    showToast(model.getErrmsg());
                }
                break;
            case Constant.TYPEADDRESS_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    this.finish();
                } else {
                    showToast(model.getErrmsg());
                }
                break;
        }
    }
}
