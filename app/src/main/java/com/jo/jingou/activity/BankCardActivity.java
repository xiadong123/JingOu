package com.jo.jingou.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.CardListModel;
import com.jo.jingou.model.UserInfoModel;
import com.jo.jingou.model.loader.UserInfoLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.view.dialog.SysDialogUtils;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;

import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;

public class BankCardActivity extends MyBaseActivity {

    private static final int GOTOCREATE = 3001;
    public static final int BACKCREATE = 3002;
    private RecyclerView bank_card_rcv;
    private CardListModel cardListModel;
    private RelativeLayout bank_card_submit_rl;
    private int currentBankCardId;
    private List<CardListModel.ListBean> listBeans;
    private CommonRefreshAdapter commonRefreshAdapter;
    private int delPos = -1;
    private UserInfoModel.ModelEntity userInfoModel;

    @Override
    public void initData() {
        layoutId = R.layout.activity_bank_card;
    }

    @Override
    public void findViews() {
        bank_card_rcv = (RecyclerView) findViewById(R.id.bank_card_rcv);
        bank_card_submit_rl = findViewAndSetClick(R.id.bank_card_submit_rl);
        bank_card_rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setupViews() {
        setTopview2("我的银行卡");
        leftimg3.setImageResource(R.drawable.back);
        righttxt.setVisibility(View.GONE);
        rightimg.setVisibility(View.VISIBLE);
        rightimg.setImageDrawable(this.getResources().getDrawable(R.drawable.add_card));
        rightimg.setOnClickListener(this);

        userInfoModel = UserInfoLoader.getUserInfoModel().getModel();
        bank_card_submit_rl.setEnabled(false);
        getCardList();
    }

    private void getCardList() {
        showLoadingDialog();
        utilNetwork.getCardListModel(new OkHttpClientManager.Param[]{});
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
            case R.id.base_topview_right_img:
                Intent intent = new Intent();
                intent.setClass(this, CreateBankCardActivity.class);
                intent.putExtra("isEdit", false);
                startActivityForResult(intent, GOTOCREATE);
                break;
            case R.id.bank_card_submit_rl:
                //等待接口对接
                showLoadingDialog();
                utilNetwork.getDefaultCardModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("cardid",
                        currentBankCardId + "")});
                break;
        }
    }

    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T
            model) {
        super.onNetworkCallBack(status_code, network_code, model);
        switch (network_code) {
            case Constant.CARDLIST_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    bank_card_submit_rl.setEnabled(true);

                    cardListModel = (CardListModel) model;
                    listBeans = cardListModel.getList();

                    commonRefreshAdapter = new CommonRefreshAdapter
                            (BankCardActivity.this, listBeans, R.layout.item_bank_card, true) {
                        @Override
                        protected void convert(ViewHolder holder, Object data) {
                            final int position = holder.getPosition();
                            //设置布局 啥的
                            final CardListModel.ListBean listBean = (CardListModel.ListBean) data;
                            TextView sItem_bankcard_name_tv = holder.getView(R.id.item_bankcard_name_tv);
                            TextView sItem_tel_tv = holder.getView(R.id.item_tel_tv);
                            TextView sActivity_enchashment_cardnum_tv1 = holder.getView(R.id
                                    .activity_enchashment_cardnum_tv1);
                            TextView sActivity_enchashment_bankname_tv = holder.getView(R.id
                                    .activity_enchashment_bankname_tv);
                            TextView sActivity_enchashment_branchname_tv = holder.getView(R.id
                                    .activity_enchashment_branchname_tv);
                            ImageView sItem_rb = holder.getView(R.id.item_rb);
                            LinearLayout sItem_rb_ll = holder.getView(R.id.item_rb_ll);
                            TextView sItem_tv = holder.getView(R.id.item_tv);
                            LinearLayout sItem_edit_ll = holder.getView(R.id.item_edit_ll);
                            LinearLayout sItem_del_ll = holder.getView(R.id.item_del_ll);

                            sItem_tel_tv.setText(userInfoModel.getMobile());

                            sItem_bankcard_name_tv.setText(listBean.getName());
                            sActivity_enchashment_cardnum_tv1.setText(listBean.getBankCardCode());
                            sActivity_enchashment_bankname_tv.setText(listBean.getBank());
                            sActivity_enchashment_branchname_tv.setText(listBean.getBankInformation());

                            int aDefault = listBean.getDefault();
                            if (aDefault == 0) {  //当前为选中的
                                delPos = position;
                                currentBankCardId = listBeans.get(position).getBankCardId();
                                sItem_rb.setImageDrawable(getResources().getDrawable(R.drawable.selected));
                            } else {
                                sItem_rb.setImageDrawable(getResources().getDrawable(R.drawable.unselected));
                            }
                            //勾选按钮的点击事件
                            sItem_rb_ll.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    for (int i = 0; i < listBeans.size(); i++) {
                                        if (i == position) {
                                            listBeans.get(i).setDefault(0);
                                            currentBankCardId = listBeans.get(i).getBankCardId();
                                        } else {
                                            listBeans.get(i).setDefault(1);
                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            });

                            //编辑按钮的点击事件
                            sItem_edit_ll.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent();
                                    intent.setClass(BankCardActivity.this, CreateBankCardActivity.class);
                                    intent.putExtra("isEdit", true);
                                    intent.putExtra("listBean", listBean);
                                    startActivityForResult(intent, GOTOCREATE);
                                }
                            });

                            //删除按钮的点击事件
                            sItem_del_ll.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
//                                    DialogUtils.showTelDialog(BankCardActivity.this, "您确定要删除么？", "删除", new
//                                            DialogUtils.DialogConfirm() {
//                                                @Override
//                                                public void confirm() {
//                                                    delCurrentBank(position);
//                                                }
//                                            });
                                    SysDialogUtils.setAndShowDialog(BankCardActivity.this, "您确定要删除么？", "删除", "点错了",
                                            new SysDialogUtils.DialogConfirm() {
                                                @Override
                                                public void confirm() {
                                                    delCurrentBank(position);
                                                }
                                            });
                                }
                            });
                        }
                    };
                    bank_card_rcv.setAdapter(commonRefreshAdapter);

                    commonRefreshAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(ViewHolder viewHolder, Object data, int position) {

                            Intent intent = new Intent();
                            intent.putExtra("cardum", listBeans.get(position).getBankCardCode());
                            intent.putExtra("bankName", listBeans.get(position).getBank());
                            intent.putExtra("branchname", listBeans.get(position).getBankInformation());
                            setResult(EnchashmentActivity.BACKBANKCARDHASDATA, intent);
                            finish();
                        }
                    });

                } else {
                    if (model != null) {
                        showToast(model.getErrmsg());
                    }
                }
                break;
            case Constant.DELCARD_ID:  //删除银行卡
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("删除成功");
                    if (delPos != -1) {
                        listBeans.remove(delPos);
                        commonRefreshAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (model != null) {
                        showToast(model.getErrmsg());
                    }
                }
                delPos = -1;
                break;
            case Constant.DEFAULTCARD_ID:
                cancelLoadingDialog();
                if (status_code == 0) {
                    showToast("修改成功");
                    Intent intent = new Intent();
                    setResult(EnchashmentActivity.BACKBANKCARD, intent);
                    finish();
                } else {
                    if (model != null) {
                        showToast(model.getErrmsg());
                    }
                }
                break;
        }
    }

    private void delCurrentBank(int position) {
        int bankCardId = listBeans.get(position).getBankCardId();
        showLoadingDialog();
        this.delPos = position;
        utilNetwork.getDelCardModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("cardid",
                bankCardId + "")});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOTOCREATE) {
            switch (resultCode) {
                case BACKCREATE:
                    getCardList();
                    break;
            }
        }
    }
}
