package com.example.administrator.myapplication;

import android.app.Application;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.BaseFragment;


/**
 * Created by xingge on 2017/4/12.
 */

public class APP extends Application {

    public static BaseActivity activity;
    public static BaseFragment lastFragment;

}
