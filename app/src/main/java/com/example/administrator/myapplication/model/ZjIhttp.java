package com.example.administrator.myapplication.model;

import android.widget.ImageView;


import com.example.administrator.myapplication.model.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/10.
 */

public interface ZjIhttp {
    void post(String url, Map<String, String> params, NetWorkCallBack callBack);

    void get(String url, Map<String, String> params, NetWorkCallBack callBack);

    void uploadFile();

    void download();

    void loadImage(String imgUrl, ImageView imageView);
}
