package com.jo.jingou.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.AddressModel;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.adapter.CommonAdapter;
import caesar.feng.framework.adapter.ViewHolder;
import caesar.feng.framework.net.OkHttpClientManager;

/**
 * Created by dfyu on 2016/12/9.
 */
public class AddressListActivity extends MyBaseActivity {


    ListView listView;
    CommonAdapter<AddressModel.ModelPlacesEntity> commonAdapter;
    List<AddressModel.ModelPlacesEntity> places = new ArrayList<>();

    View submit;

    Intent intent_forresult = new Intent();

    @Override
    public void initData() {
        layoutId = R.layout.activity_addresslist;
    }

    @Override
    public void findViews() {
        listView = (ListView) findViewById(R.id.activity_addresslist_addresslv);
        commonAdapter = new CommonAdapter<AddressModel.ModelPlacesEntity>(this, places, R.layout.item_addresslist) {
            @Override
            public void convert(ViewHolder viewHolder, final AddressModel.ModelPlacesEntity entity) {

                viewHolder.setText(R.id.item_addresslist_name, entity.getName());
                viewHolder.setText(R.id.item_addresslist_phone, entity.getTel());
                viewHolder.setText(R.id.item_addresslist_address, entity.getAddress());

                if (entity.getType() == 1) {
                    viewHolder.setImageResource(R.id.item_addresslist_defaddress_img, R.drawable.selected);
                    viewHolder.setTextColor(R.id.item_addresslist_defaddress_tv, getResources().getColor(R.color.red));
                } else {
                    viewHolder.setImageResource(R.id.item_addresslist_defaddress_img, R.drawable.unselected);
                    viewHolder.setTextColor(R.id.item_addresslist_defaddress_tv, getResources().getColor(R.color.gary5));
                }

                viewHolder.setClickLisener(R.id.item_addresslist_content, new View.OnClickListener() {
                    public void onClick(View view) {
                        checkAddress(entity);
                    }
                });


                viewHolder.setClickLisener(R.id.item_addresslist_defaddress_layout, new View.OnClickListener() {
                    public void onClick(View view) {
                        defAddress(entity);
                    }
                });
                viewHolder.setClickLisener(R.id.item_addresslist_resetaddress_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resetAddress(entity);
                    }
                });
                viewHolder.setClickLisener(R.id.item_addresslist_deladdress_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delAddress(entity);
                    }
                });
            }
        };
        submit = findViewAndSetClick(R.id.activity_addresslist_submit);
    }

    //选择收货地址
    private void checkAddress(AddressModel.ModelPlacesEntity entity) {
        intent_forresult.putExtra("type", "checkaddress");
        intent_forresult.putExtra("memberplaceid", entity.getMemberPlaceId());
        setResult(1, intent_forresult);
        AddressListActivity.this.finish();
    }


    //修改默认地址
    private void defAddress(AddressModel.ModelPlacesEntity entity) {
        if (entity.getType() == 0) {
            showLoadingDialog();
            utilNetwork.getTypeAddressModel(
                    new OkHttpClientManager.Param[]{
                            new OkHttpClientManager.Param("name", entity.getName()),
                            new OkHttpClientManager.Param("tel", entity.getTel()),
                            new OkHttpClientManager.Param("province", entity.getProvince()),
                            new OkHttpClientManager.Param("city", entity.getCity()),
                            new OkHttpClientManager.Param("county", entity.getCounty()),
                            new OkHttpClientManager.Param("address", entity.getAddress()),
                            new OkHttpClientManager.Param("defaults", "1"),
                            new OkHttpClientManager.Param("memberplaceid", entity.getMemberPlaceId() + "")});
        }
    }

    //修改地址详情
    private void resetAddress(AddressModel.ModelPlacesEntity entity) {
        Intent i = new Intent(this, AddAddressActivity.class);
        i.putExtra("updata", 1);
        i.putExtra("entity", entity);
        startActivityForResult(i, 0);
    }


    //删除地址
    private void delAddress(final AddressModel.ModelPlacesEntity entity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认删除该地址吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showLoadingDialog();
                utilNetwork.getDelAddressModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("memberplaceid", entity.getMemberPlaceId() + "")});
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    @Override
    public void initListener() {
        listView.setAdapter(commonAdapter);
    }

    @Override
    public void setupViews() {
        setTopview2("收货地址");
        setResult(1);
        showLoadingDialog();
        utilNetwork.getAddressModel(new OkHttpClientManager.Param[]{});
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        showLoadingDialog();
        utilNetwork.getAddressModel(new OkHttpClientManager.Param[]{});
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.activity_addresslist_submit:
                MyUtils.setAndStartIntent(this, AddAddressActivity.class);
                break;
            case R.id.base_topview_left:
                this.finish();
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.ADDRESS_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    AddressModel addressModel = (AddressModel) model;
                    places = addressModel.getModelPlaces();
                    commonAdapter.setList(places);
                    commonAdapter.notifyDataSetChanged();

                    if (places.size() == 0) showToast("还没有添加过收货地址哦～");
                } else {
                    showToast(model.getErrmsg());
                }
                break;
            case Constant.TYPEADDRESS_ID:
                if (status_code == 0) {
                    utilNetwork.getAddressModel(new OkHttpClientManager.Param[]{});
                } else {
                    showToast(model.getErrmsg());
                }
                break;
            case Constant.DELADDRESS_ID:
                if (status_code == 0) {
                    utilNetwork.getAddressModel(new OkHttpClientManager.Param[]{});
                } else {
                    showToast(model.getErrmsg());
                }
                break;
        }
    }
}
