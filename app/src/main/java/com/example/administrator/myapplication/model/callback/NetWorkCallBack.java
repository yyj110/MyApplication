package com.example.administrator.myapplication.model.callback;

/**
 * Created by xingge on 2017/4/12.
 */

public interface NetWorkCallBack<T> {

    /**
     * 网络请求成功的监听
     *
     */
    void onSuccess(T Data);

    /**
     * 网络请求失败的监听
     * @param errorMsg 错误信息
     */
    void onError(String errorMsg, String ErrorCode);

}