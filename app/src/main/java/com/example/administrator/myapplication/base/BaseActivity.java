package com.example.administrator.myapplication.base;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;


import com.example.administrator.myapplication.APP;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        init();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        APP.activity = this;
        loadData();
    }

    //加载布局
    protected abstract int getLayoutId();

    //初始化
    protected abstract void init();

    //初始化监听
    protected abstract void initListener();

    //加载数据
    protected abstract void loadData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
