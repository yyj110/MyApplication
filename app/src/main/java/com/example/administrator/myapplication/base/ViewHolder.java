package com.example.administrator.myapplication.base;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by xingge on 2017/4/13.
 */

public class ViewHolder {

    private static ViewHolder holder = null;

    private Map<Integer,View> views;
    private Context context;
    private View convertView;

    private ViewHolder(Context context, View convertView){
        this.context = context;
        this.convertView = convertView;
        this.views = new HashMap<>();
    }

    public static ViewHolder getInstance(Context context,View convertView,int layoutId){
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
            holder = new ViewHolder(context,convertView);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();
        return holder;

    }

    public View getConvertView() {
        return convertView;
    }

    private View getView(int viewId){

        View view = views.get(viewId);
        if(view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId,view);
        }

        return view;
    }

    public void setText(int viewId,String text){
        TextView tv = (TextView) getView(viewId);
        if(tv != null)
            tv.setText(text);
    }

    /**
     * 加载本地图片
     * @param imageViewId
     * @param resId
     */
    public void setImageResource(int imageViewId,int resId){
        ImageView img = (ImageView) getView(imageViewId);
        img.setImageResource(resId);
    }

    /**
     * 加载网络图片
     * @param imageViewId
     * @param imgUrl
     */
    public void setNetWorkImg(int imageViewId,String imgUrl){
        ImageView img = (ImageView) getView(imageViewId);
        Glide.with(context).load(imgUrl).into(img);
    }

}
