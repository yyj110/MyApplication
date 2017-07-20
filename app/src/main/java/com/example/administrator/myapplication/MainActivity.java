package com.example.administrator.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.fragment.FragmentOne;
import com.example.administrator.myapplication.fragment.FragmentThree;
import com.example.administrator.myapplication.fragment.FragmentTwo;
import com.example.administrator.myapplication.fragment.PanadaFragment;
import com.example.administrator.myapplication.utils.FragmentBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.searchImg)
    ImageView searchImg;
    @BindView(R.id.topGroup)
    RelativeLayout topGroup;
    @BindView(R.id.contentGroup)
    FrameLayout contentGroup;
    @BindView(R.id.zongHeBtn)
    RadioButton zongHeBtn;
    @BindView(R.id.dongTanBtn)
    RadioButton dongTanBtn;
    @BindView(R.id.discoverBtn)
    RadioButton discoverBtn;
    @BindView(R.id.mineBtn)
    RadioButton mineBtn;
    @BindView(R.id.bottomGroup)
    RadioGroup bottomGroup;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        checkPermission();
        FragmentBuilder.getInstance().start(PanadaFragment.class).build();
    }
    private void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            }
        }
    }

    @OnClick({R.id.zongHeBtn, R.id.dongTanBtn, R.id.discoverBtn, R.id.mineBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zongHeBtn:
                FragmentBuilder.getInstance().start(PanadaFragment.class).build();
                break;
            case R.id.discoverBtn:
                FragmentBuilder.getInstance().start(FragmentOne.class).build();
                break;
            case R.id.mineBtn:
                FragmentBuilder.getInstance().start(FragmentTwo.class).build();
                break;
            case R.id.dongTanBtn:
                FragmentBuilder.getInstance().start(FragmentThree.class).build();
                break;
        }
    }


    public View getTopGroup() {
        return topGroup;
    }

    public View getBottomGroup() {
        return bottomGroup;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"授权成功",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"拒绝授权",Toast.LENGTH_LONG).show();
        }
    }
}
