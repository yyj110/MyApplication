package com.example.administrator.myapplication.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.myapplication.APP;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;


/**
 * Created by xingge on 2017/4/12.
 *
 * 1、容器
 * 2、lastFragment
 * 3、隐藏或者替换
 * 4、传值
 * 5、添加回退栈
 * 6、回退栈管理
 * 7、一级页面管理
 *
 */

public class FragmentBuilder {

    private static FragmentBuilder fragmentBuilder;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BaseFragment fragment;
    private String simpleName;
    private FragmentBuilder(){
        init();
    }

    public synchronized static FragmentBuilder getInstance(){

        if(fragmentBuilder == null)
            fragmentBuilder = new FragmentBuilder();
        return fragmentBuilder;
    }


    private void init(){
        fragmentManager = APP.activity.getSupportFragmentManager();
    }

    public FragmentBuilder start(Class<? extends BaseFragment> fragmentClass){
        transaction = fragmentManager.beginTransaction();
        //使用Fragment类名做Tag

        simpleName = fragmentClass.getSimpleName();
        //根据tag查找fragment 如果能查找到就代表该Fragment已经实例化了，否则去动态实例化

        fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

        //判断Fragment是否为null，为null就自动创建Fragment对象
        if(fragment == null) {
            try {
                //Java的动态代理  动态创建Fragment对象
                fragment = fragmentClass.newInstance();

                transaction.add(R.id.contentGroup,fragment, simpleName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //隐藏上一个fragment
        if(APP.lastFragment != null) {
            transaction.hide(APP.lastFragment);
        }

        //已经添加就调用show方法显示
        transaction.show(fragment);
        transaction.addToBackStack(simpleName);

        return this;
    }

    public FragmentBuilder params(Bundle params){
        //传递参数
        if(params != null)
            fragment.setParams(params);
        return this;
    }

//    public FragmentBuilder isBack(boolean isBack){
//        if (isBack)
//            transaction.addToBackStack(simpleName);
//        return this;
//    }

    public BaseFragment build(){
        APP.lastFragment = fragment;
        transaction.commit();
        return fragment;
    }

    public void setLastFragment(BaseFragment lastFragment) {
        APP.lastFragment = lastFragment;
    }
}
