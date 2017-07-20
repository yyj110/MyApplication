package com.example.administrator.myapplication.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xingge on 2017/4/13.
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    private List<T> datas;
    private Context context;
    private int layoutId;
    public BaseAdapter(Context context, int layoutId, List<T> datas){
        this.context = context;
        this.layoutId = layoutId;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        if (datas == null)
            return 0;
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = ViewHolder.getInstance(context, convertView, layoutId);
        convert(holder,datas.get(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder,T t);
}
