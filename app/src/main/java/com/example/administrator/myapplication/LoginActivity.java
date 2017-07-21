package com.example.administrator.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.fragment.PanadaFragment;

/**
 * Created by Administrator on 2017/7/20.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private Button mBlog;

    @Override
    protected int getLayoutId() {
        return R.layout.actiivty_login;
    }

    @Override
    protected void initView() {
        mBlog = (Button) findViewById(R.id.mLogin_bun);
        mBlog.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mLogin_bun:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
