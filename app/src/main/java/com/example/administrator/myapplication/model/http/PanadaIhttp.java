package com.example.administrator.myapplication.model.http;


import com.example.administrator.myapplication.model.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/10.
 */

public interface PanadaIhttp extends Ihttp{
    void showImage(String url, Map<String, String> params, NetWorkCallBack callBack);
}
