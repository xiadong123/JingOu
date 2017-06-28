package com.jo.jingou.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jo.jingou.R;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.adapter.ViewPagerAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.base.MyBaseModel;
import com.jo.jingou.model.Instation30Model;
import com.jo.jingou.model.Instation31Model;
import com.jo.jingou.model.Instation32Model;
import com.jo.jingou.model.loader.MessageLoader;
import com.jo.jingou.utils.Constant;
import com.jo.jingou.utils.MyUtils;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import caesar.feng.framework.net.OkHttpClientManager;
import caesar.feng.framework.utils.Utility;

/**
 * Created by dfyu on 2017/4/24.
 */
public class MessageBonusListActicity extends MyBaseActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    List<String> titles = new ArrayList<>();
    List<View> views = new ArrayList<>();
    View v0, v1, v2;
    SwipeRefreshLayout swipeRefreshLayout0, swipeRefreshLayout1, swipeRefreshLayout2;
    RecyclerView recyclerView0, recyclerView1, recyclerView2;
    CommonRefreshAdapter<Instation30Model.ListEntity> commonRefreshAdapter0;
    CommonRefreshAdapter<Instation31Model.ListEntity> commonRefreshAdapter1;
    CommonRefreshAdapter<Instation32Model.ListEntity> commonRefreshAdapter2;

    boolean setRead;

    @Override
    public void initData() {
        layoutId = R.layout.activity_message_bonuslist;
    }

    @Override
    public void findViews() {


//        List<String> test = new ArrayList<>();
//        test.add("");
//        test.add("");
//        test.add("");
//        test.add("");
//        test.add("");
//        test.add("");


        tabLayout = (TabLayout) findViewById(R.id.activity_message_bonuslist_tablayout);
        viewPager = (ViewPager) findViewById(R.id.activity_message_bonuslist_viewpager);

        v0 = getLayoutInflater().inflate(R.layout.item_messagebonuslist_pager, null);
        swipeRefreshLayout0 = (SwipeRefreshLayout) v0.findViewById(R.id.swiperefreshlayout);
        recyclerView0 = (RecyclerView) v0.findViewById(R.id.recyclerView);
        setNoData_LoadFail_ReLoadViews(v0, "没有搜索到这类消息～", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeRefreshLayout0.setRefreshing(true);
                utilNetwork.getInstation30Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "1")});
            }
        });

        v1 = getLayoutInflater().inflate(R.layout.item_messagebonuslist_pager, null);
        swipeRefreshLayout1 = (SwipeRefreshLayout) v1.findViewById(R.id.swiperefreshlayout);
        recyclerView1 = (RecyclerView) v1.findViewById(R.id.recyclerView);
        setNoData_LoadFail_ReLoadViews(v1, "没有搜索到这类消息～", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeRefreshLayout1.setRefreshing(true);
                utilNetwork.getInstation31Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "2")});
            }
        });

        v2 = getLayoutInflater().inflate(R.layout.item_messagebonuslist_pager, null);
        swipeRefreshLayout2 = (SwipeRefreshLayout) v2.findViewById(R.id.swiperefreshlayout);
        recyclerView2 = (RecyclerView) v2.findViewById(R.id.recyclerView);
        setNoData_LoadFail_ReLoadViews(v2, "没有搜索到这类消息～", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeRefreshLayout2.setRefreshing(true);
                utilNetwork.getInstation32Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "3")});
            }
        });


        commonRefreshAdapter0 = new CommonRefreshAdapter<Instation30Model.ListEntity>(this, new ArrayList<Instation30Model.ListEntity>(), R.layout.item_message_bonus, false) {
            @Override
            protected void convert(ViewHolder holder, Instation30Model.ListEntity data) {
                holder.setText(R.id.item_message_bonus_date, data.getModifydate());
                holder.setText(R.id.item_message_bonus_type, "分红奖励");
                holder.setText(R.id.item_message_bonus_number, data.getContents());
                holder.setText(R.id.item_message_bonus_content, "您有1笔分红奖励已经存入钱包，请查收。");
            }
        };

        commonRefreshAdapter1 = new CommonRefreshAdapter<Instation31Model.ListEntity>(this, new ArrayList<Instation31Model.ListEntity>(), R.layout.item_message_bonus, false) {
            @Override
            protected void convert(ViewHolder holder, Instation31Model.ListEntity data) {
                holder.setText(R.id.item_message_bonus_date, data.getModifydate());
                holder.setText(R.id.item_message_bonus_type, "分销奖励");
                holder.setText(R.id.item_message_bonus_number, data.getContents());
                holder.setText(R.id.item_message_bonus_content, "您有1笔分销奖励已经存入钱包，请查收。");
            }
        };

        commonRefreshAdapter2 = new CommonRefreshAdapter<Instation32Model.ListEntity>(this, new ArrayList<Instation32Model.ListEntity>(), R.layout.item_message_bonus, false) {
            @Override
            protected void convert(ViewHolder holder, Instation32Model.ListEntity data) {
                holder.setText(R.id.item_message_bonus_date, data.getModifydate());
                holder.setText(R.id.item_message_bonus_type, "合伙人奖励");
                holder.setText(R.id.item_message_bonus_number, data.getContents());
                holder.setText(R.id.item_message_bonus_content, "您有1笔合伙人奖励已经存入钱包，请查收。");
            }
        };

        titles.add("分红奖励");
        titles.add("分销奖励");
        titles.add("合伙人奖励");

        views.add(v0);
        views.add(v1);
        views.add(v2);
    }

    @Override
    public void initListener() {
        swipeRefreshLayout0.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                utilNetwork.getInstation30Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "1")});
            }
        });
        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                utilNetwork.getInstation31Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "2")});
            }
        });
        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                utilNetwork.getInstation32Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "3")});
            }
        });

        commonRefreshAdapter0.setOnItemClickListener(new OnItemClickListener<Instation30Model.ListEntity>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, Instation30Model.ListEntity data, int position) {
                MyUtils.setAndStartIntent(MessageBonusListActicity.this, FenhongDetailActivity.class);
            }
        });
        commonRefreshAdapter1.setOnItemClickListener(new OnItemClickListener<Instation31Model.ListEntity>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, Instation31Model.ListEntity data, int position) {
                gotoDetail("分销奖励", "1", "0");
            }
        });
        commonRefreshAdapter2.setOnItemClickListener(new OnItemClickListener<Instation32Model.ListEntity>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, Instation32Model.ListEntity data, int position) {
                gotoDetail("合伙人奖励", "2", "0");
            }
        });


    }

    @Override
    public void setupViews() {
        setTopview2("奖励消息");

        recyclerView0.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        recyclerView0.setAdapter(commonRefreshAdapter0);
        recyclerView1.setAdapter(commonRefreshAdapter1);
        recyclerView2.setAdapter(commonRefreshAdapter2);

        viewPager.setAdapter(new ViewPagerAdapter(views, titles));
        tabLayout.setupWithViewPager(viewPager);

        MyUtils.setIndicator(this, tabLayout, Utility.getScreenWidth(this) / 3 / 5, Utility.getScreenWidth(this) / 3 / 5);

        getData();

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.base_topview_left:
                finish();
                break;
        }
    }

    public void getData() {
        utilNetwork.getInstation30Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "1")});
        utilNetwork.getInstation31Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "2")});
        utilNetwork.getInstation32Model(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("typeid", "4"), new OkHttpClientManager.Param("MoneyType", "3")});
    }


    @Override
    public <T extends MyBaseModel> void onNetworkCallBack(int status_code, int network_code, T model) {
        super.onNetworkCallBack(status_code, network_code, model);

        if (status_code == 0 && setRead == false) {
            utilNetwork.getReadModel(new OkHttpClientManager.Param[]{new OkHttpClientManager.Param("Type", "4"), new OkHttpClientManager.Param("messageid", "0")});//设置消息已读，不分类
            MessageLoader.setMessage(3, 0);
            setRead = true;

        }

        switch (network_code) {
            case Constant.INSTATION_ID30:
                swipeRefreshLayout0.setRefreshing(false);
                if (status_code == 0) {

                    Instation30Model instation30Model = (Instation30Model) model;
                    commonRefreshAdapter0.setNewData(instation30Model.getList());
                    commonRefreshAdapter0.notifyDataSetChanged();


                    if (instation30Model.getList().size() == 0) {
                        setViewOnNoData(v0);
                    } else {
                        setViewOnLoadSuccessHadData(v0);
                    }
                } else {
                    setViewOnLoadFail(v0);
                }

                break;
            case Constant.INSTATION_ID31:
                swipeRefreshLayout1.setRefreshing(false);
                if (status_code == 0) {
                    Instation31Model instation31Model = (Instation31Model) model;
                    commonRefreshAdapter1.setNewData(instation31Model.getList());
                    commonRefreshAdapter1.notifyDataSetChanged();

                    if (instation31Model.getList().size() == 0) {
                        setViewOnNoData(v1);
                    } else {
                        setViewOnLoadSuccessHadData(v1);
                    }
                } else {
                    setViewOnLoadFail(v1);
                }
                break;
            case Constant.INSTATION_ID32:
                swipeRefreshLayout2.setRefreshing(false);
                if (status_code == 0) {
                    Instation32Model instation32Model = (Instation32Model) model;
                    commonRefreshAdapter2.setNewData(instation32Model.getList());
                    commonRefreshAdapter2.notifyDataSetChanged();

                    if (instation32Model.getList().size() == 0) {
                        setViewOnNoData(v2);
                    } else {
                        setViewOnLoadSuccessHadData(v2);
                    }
                } else {
                    setViewOnLoadFail(v2);
                }
                break;
        }
    }


    /**
     * 跳转到奖励详情
     *
     * @param title   奖励详情的title 文案
     * @param type    当前跳转页面的类型
     * @param isBonus
     */
    private void gotoDetail(String title, String type, String isBonus) {
        String[] key = new String[]{"title", "type", "isBonus"};
        String[] value = new String[]{title, type, isBonus};
        MyUtils.setAndStartIntent(this, MyPurseRewardDetail.class, key, value);
    }

}
