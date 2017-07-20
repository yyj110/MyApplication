package com.example.administrator.myapplication.utils;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.APP;
import com.example.administrator.myapplication.model.callback.NetWorkCallBack;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xingge on 2017/4/12.
 */

public class OkHttpUtils  {



    //请求成功的状态码
    private static final int SUCCESS = 200;
    //请求失败的状态码
    private static final int ERROR = 100;
    private Callback callback;
    /**
     * 抽取单例的OKHttpClient对象
     * 第一步 构造函数私有化
     * 第二步 提供一个共有的、静态的方法 该方法的返回值BaseOkHttp
     * 第三步 提供私有的静态的BaseOkHttp的对象
     */

    //保证OkHttpClient对象是单例的
    private OkHttpClient okHttpClient;
    private static OkHttpUtils okHttpUtils = null;
    private OkHttpUtils(){
        okHttpClient = new OkHttpClient.Builder().build();
    }
    public static OkHttpUtils getInstace(){
        if(okHttpUtils == null){
            okHttpUtils = new OkHttpUtils();
            return okHttpUtils;
        }
        return null;
    }

    public <T> T getGeneric(String jsonMsg, NetWorkCallBack<T> callback){
        //通过反射获取泛型的实例
        Gson gson = new Gson();
        Type[] types = callback.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        T t = gson.fromJson(jsonMsg, actualTypeArguments[0]);
        return t;
    }
    //文件下载
    // outputStream通过流下载

    //图片上传
    public <T>void uploadFile(String url, Map<String,String> params, final NetWorkCallBack<T> callBack){
        //通过form表单上传文件
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet){
                String value = params.get(key);
                if(value.endsWith(".jpg") || value.endsWith(".png")){
                    String imgName = value.substring(value.lastIndexOf("/")+1);
                    builder.addFormDataPart(key,imgName,MultipartBody.create(MediaType.parse("image/*"),new File(value)));
                }
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                Log.i("abc","-----"+response.body().string());
            }
        });
    }


    public<T> void post(String url, Map<String, String> params, final NetWorkCallBack<T> callBack) {

        FormBody.Builder builder = null;
        if(params != null && params.size() > 0) {
            builder = new FormBody.Builder();

            Set<String> keys = params.keySet();

            for (String key : keys) {
                builder.add(key,params.get(key));
            }

        }


        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString(),"404");
                    }
                });

            }

            @Override

            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final T t = getGeneric(string, callBack);
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(t);
                    }
                });
            }
        });

    }


    public<T> void get(String url, Map<String, String> params, final NetWorkCallBack<T> callBack) {

        if(params !=null && params.size()>0){
            StringBuffer sb = new StringBuffer();
            Set<String> set = params.keySet();
            sb.append("?");
            for(String value:set){
                sb.append(set).append("=").append(params.get(value)).append("&");
            }
            url = sb.toString().substring(0,sb.length()-1);
        }
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString(),"404");
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final T t = getGeneric(string, callBack);
                APP.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(t);
                    }
                });

            }
        });
    }

    public void download() {

    }

    public void loadImage(String imgUrl, ImageView imageView) {

        Glide.with(APP.activity).load(imgUrl).into(imageView);
    }

}
