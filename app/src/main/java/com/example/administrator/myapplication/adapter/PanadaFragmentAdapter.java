package com.example.administrator.myapplication.adapter;

import android.content.Context;


import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseAdapter;
import com.example.administrator.myapplication.base.ViewHolder;
import com.example.administrator.myapplication.model.entity.PanadaBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */

public class PanadaFragmentAdapter extends BaseAdapter<PanadaBean.ListBean> {
    public PanadaFragmentAdapter(Context context, List<PanadaBean.ListBean> datas) {
        super(context, R.layout.panadalistview_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, PanadaBean.ListBean listBean) {
        holder.setText(R.id.mPanada_listView_title,listBean.getTitle());
       // holder.setImageResource(R.id.mPanada_listView_image,listBean.getImage());
        holder.setNetWorkImg(R.id.mPanada_listView_image,listBean.getImage());
    }
}
