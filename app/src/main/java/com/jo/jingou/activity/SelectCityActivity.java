package com.jo.jingou.activity;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.adapter.CitySelectAdapter;
import com.jo.jingou.adapter.CommonRefreshAdapter;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.model.Area2Model;
import com.jo.jingou.model.loader.CityLoader;
import com.jo.jingou.utils.MyUtils;
import com.jo.jingou.utils.Util_CharacterParser;
import com.jo.jingou.view.SideBar;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfyu on 2017/5/5.
 */
public class SelectCityActivity extends MyBaseActivity {

    View cancel;
    EditText searchcity_ed;
    ListView citylist_lv;
    RecyclerView searchlist_rv;

    View headerview;
    TextView headerview_city;
    RecyclerView headerview_rv;

    SideBar sideBar;
    TextView sideBar_tv;


    // CommonAdapter<String> commonAdapter;
    CitySelectAdapter citySelectAdapter_citylist;
    CommonRefreshAdapter commonRefreshAdapter_hotcity;
    CommonRefreshAdapter commonRefreshAdapter_searchcity;
    List<Area2Model.AreaListEntity> arealist;
    final List<String> test = new ArrayList<String>();

    @Override
    public void initData() {
        layoutId = R.layout.activity_selectcity;
    }

    @Override
    public void findViews() {
        cancel = findViewAndSetClick(R.id.cancel);
        searchcity_ed = (EditText) findViewById(R.id.activity_selectcity_searchcity_ed);
        citylist_lv = (ListView) findViewById(R.id.activity_selectcity_citylist_listview);
        searchlist_rv = (RecyclerView) findViewById(R.id.activity_selectcity_searchcity_recyclerview);

        sideBar = (SideBar) findViewById(R.id.select_brand_sidrbar);
        sideBar_tv = (TextView) findViewById(R.id.select_brand_dialog);
        sideBar.setTextView(sideBar_tv);

        headerview = getLayoutInflater().inflate(R.layout.item_selectcity_headerview, null);
        headerview_city = (TextView) headerview.findViewById(R.id.item_selectcity_headerview_city);
        headerview_rv = (RecyclerView) headerview.findViewById(R.id.item_selectcity_headerview_rv);


        test.add("上海市");
        test.add("广州市");
        test.add("北京市");
        test.add("苏州市");
        test.add("南京市");
        test.add("深证市");
        test.add("杭州市");
        test.add("厦门市");

        citySelectAdapter_citylist = new CitySelectAdapter(this);

        commonRefreshAdapter_hotcity = new CommonRefreshAdapter<String>(this, test, R.layout.item_hotcity, false) {
            @Override
            protected void convert(com.othershe.baseadapter.ViewHolder holder, String data) {
                holder.setText(R.id.text, data);
            }
        };

        commonRefreshAdapter_searchcity = new CommonRefreshAdapter<String>(this, new ArrayList<String>(), R.layout.item_selectcity_citylist, false) {
            @Override
            protected void convert(com.othershe.baseadapter.ViewHolder holder, String data) {
                holder.getView(R.id.catalog).setVisibility(View.GONE);
                holder.setText(R.id.select_brand_item_tv, data);
            }
        };

    }

    @Override
    public void initListener() {
        searchcity_ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    searchcity_ed.setBackground(null);
                    searchlist_rv.setVisibility(View.GONE);
                } else {
                    searchcity_ed.setBackgroundColor(getResources().getColor(R.color.white));
                    searchlist_rv.setVisibility(View.VISIBLE);
                    queryCity(charSequence.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        headerview_city.setOnClickListener(this);

        commonRefreshAdapter_hotcity.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, Object data, int position) {
                setCityAndFinish(test.get(position));
            }
        });

        citylist_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1)
                    setCityAndFinish(arealist.get(i - 1).getArea_name());
            }
        });


        commonRefreshAdapter_searchcity.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewHolder viewHolder, Object data, int position) {
                setCityAndFinish((String) commonRefreshAdapter_searchcity.getData().get(position));

            }
        });

        sideBar.setOnSelectChangeListener(new SideBar.OnSelectChangeListener() {
            @Override
            public void onSelectChange(String letter) {
                int ascii = letter.charAt(0);
                int position = citySelectAdapter_citylist.getPositionFroAscii(ascii);
                if (position != -1)
                    citylist_lv.setSelection(position + 1);
            }
        });

    }

    @Override
    public void setupViews() {
        //searchcity_ed.setHint(transferBiaoQing("请输入城市", R.drawable.search));


        citylist_lv.addHeaderView(headerview);
        headerview_rv.setLayoutManager(new GridLayoutManager(this, 4));
        headerview_rv.setAdapter(commonRefreshAdapter_hotcity);

        Area2Model area2Model = MyUtils.formatArea2Json(this, new Handler());
        arealist = MyUtils.comparatorByPinyin2(area2Model.getArea_list());
        citySelectAdapter_citylist.setData(arealist);

        citylist_lv.setAdapter(citySelectAdapter_citylist);

        searchlist_rv.setLayoutManager(new LinearLayoutManager(this));
        searchlist_rv.setAdapter(commonRefreshAdapter_searchcity);

    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.item_selectcity_headerview_city:
                setCityAndFinish(((TextView) view).getText().toString());
                break;
        }
    }

    private void setCityAndFinish(String city) {
        CityLoader.setCity(city);
        finish();
    }

    public void queryCity(String keyword) {

        List<String> searchlist = new ArrayList<>();

        for (Area2Model.AreaListEntity entity : arealist) {
            String citytxt = entity.getArea_name();
            String citypingy = Util_CharacterParser.getInstance().getSelling(entity.getArea_name());

            if (citytxt.indexOf(keyword) >= 0 || citypingy.startsWith(keyword)) {
                searchlist.add(entity.getArea_name());
            }
        }

        commonRefreshAdapter_searchcity.setNewData(searchlist);
    }


    /**
     * 将富文本转成CharSequence
     *
     * @param commonStr 普通内容
     * @param bqId      表情图片
     * @return
     */
    public CharSequence transferBiaoQing(String commonStr, int bqId) {
        return Html.fromHtml("<img src=\"" + bqId + "\">    " + commonStr, imageGetter, null);
    }

    /**
     * 获取本地图片资源
     */
    private Html.ImageGetter imageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            int id = Integer.parseInt(source);
            // 根据id从资源文件中获取图片对象
            Drawable d = SelectCityActivity.this.getApplicationContext().getResources().getDrawable(id);
            // 以此作为标志位，方便外部取出对应的资源id
            d.setState(new int[]{id});
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            return d;
        }
    };
}
