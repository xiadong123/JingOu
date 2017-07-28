package com.jo.jingou.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jo.jingou.R;
import com.jo.jingou.base.MyBaseActivity;
import com.jo.jingou.model.Area2Model;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class CitySelectAdapter extends BaseAdapter {

    private MyBaseActivity actSelectBrand;
    List<Area2Model.AreaListEntity> citydata;


    public CitySelectAdapter(MyBaseActivity actSelectBrand) {
        this.actSelectBrand = actSelectBrand;
    }


    @Override
    public int getCount() {
        return citydata.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = View.inflate(actSelectBrand, R.layout.item_selectcity_citylist, null);
            myViewHolder.sCatalog = (TextView) convertView.findViewById(R.id.catalog);
            myViewHolder.sSelect_brand_item_tv = (TextView) convertView.findViewById(R.id.select_brand_item_tv);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }



        Area2Model.AreaListEntity cityBean = citydata.get(position);
        myViewHolder.sCatalog.setText(cityBean.getFirst_letter());
        myViewHolder.sSelect_brand_item_tv.setText(cityBean.getArea_name());

        // 根据position获取分类的首字母的Char ASCII值
        int section = getAsciiForPosition(position);

        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionFroAscii(section)) {
            myViewHolder.sCatalog.setVisibility(View.VISIBLE);
            myViewHolder.sCatalog.setText(citydata.get(position).getFirst_letter());
        } else {
            myViewHolder.sCatalog.setVisibility(View.GONE);
        }

//        myViewHolder.sSelect_brand_ll.setOnClickListener(new MyItemClickListener(position));

        return convertView;
    }

    public void setData(List<Area2Model.AreaListEntity> citydata) {
        this.citydata = citydata;
    }


    class MyViewHolder {
        //字母
        private TextView sCatalog;
        //
        private TextView sSelect_brand_item_tv;

    }

    /**
     * 根据位置转成首字母的ASCII
     *
     * @param position
     * @return
     */
    public int getAsciiForPosition(int position) {
        int a = citydata.get(position).getFirst_letter().charAt(0);
        return a;
    }

    /**
     * 根据字母的ASCII获取位置
     *
     * @param ascii
     * @return
     */
    public int getPositionFroAscii(int ascii) {
        for (int i = 0; i < citydata.size(); i++) {
            String abc = citydata.get(i).getFirst_letter();
            char firstChar = abc.toUpperCase().charAt(0);
            if ((int) firstChar == ascii)
                return i;
        }
        return -1;
    }


}
