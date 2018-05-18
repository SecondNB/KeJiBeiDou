package com.example.hasee.myapplication.View.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.hasee.myapplication.Contract.Constant;
import com.example.hasee.myapplication.Presenter.CategryPresenter;
import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.View.fragment.Fragment_faxian;
import com.example.hasee.myapplication.View.fragment.Fragment_jingxuan;
import com.example.hasee.myapplication.View.fragment.Fragment_my;
import com.example.hasee.myapplication.View.fragment.Fragment_zhuanti;
import com.example.hasee.myapplication.View.interfaces.Imain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity  implements Imain, RadioGroup.OnCheckedChangeListener {

    private RadioGroup radiogroup;
    private CategryPresenter categryPresenter;
    private Fragment_jingxuan fragment_jingxuan;
    private FragmentManager fragmentManager;
    private Fragment_zhuanti fragment_zhuanti;
    private Fragment_faxian fragment_faxian;
    private Fragment_my fragment_my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiogroup = findViewById(R.id.radio_group);

        categryPresenter = new CategryPresenter(this);
        //产生关联
        categryPresenter.attachView(this);




        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_jingxuan()).commit();

        //管理者...开启事务(一个事务只能执行一次)....默认的是要显示第一个首页
        fragmentManager = getSupportFragmentManager();
        fragment_jingxuan = new Fragment_jingxuan();

        fragmentManager.beginTransaction().add(R.id.fragment_layout,fragment_jingxuan).commit();

        //监听事件
        radiogroup.setOnCheckedChangeListener(this);
        //getData();
    }


    private void getData() {

        Map<String,String> map = new HashMap<>();
        //获取数据
        categryPresenter.getDataFromNet(Constant.homePage_URL,map);
    }


    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            Log.e("----",responseBody.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("----",throwable.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        categryPresenter.unsubcribe();//解除订阅

        if (categryPresenter.isViewAttached()) {//如果产生关联
            //解除关联
            categryPresenter.detachView();
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        //事务的对象
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        //首先隐藏所有的fragment ...前提是不为空
        //在刚开始点击的时候,先判断fragment是否为空,,,如果不为空先让他隐藏
        if (fragment_jingxuan != null) {
            transaction.hide(fragment_jingxuan);
        }
        if (fragment_zhuanti != null) {
            transaction.hide(fragment_zhuanti);
        }
        if (fragment_faxian != null) {
            transaction.hide(fragment_faxian);
        }
        if (fragment_my != null) {
            transaction.hide(fragment_my);
        }



        switch (checkedId){
            case R.id.bt01:
                if (fragment_jingxuan == null) {
                    fragment_jingxuan = new Fragment_jingxuan();
                    transaction.add(R.id.fragment_layout, fragment_jingxuan);
                }else {
                    transaction.show(fragment_jingxuan);
                }
                break;
            case R.id.bt02:

                if (fragment_zhuanti == null) {
                    fragment_zhuanti = new Fragment_zhuanti();
                    transaction.add(R.id.fragment_layout, fragment_zhuanti);
                }else {
                    transaction.show(fragment_zhuanti);
                }

                break;
            case R.id.bt03:

                if (fragment_faxian == null) {
                    fragment_faxian = new Fragment_faxian();
                    transaction.add(R.id.fragment_layout, fragment_faxian);
                }else {
                    transaction.show(fragment_faxian);
                }

                 break;
            case R.id.bt04:

                if (fragment_my == null) {
                    fragment_my = new Fragment_my();
                    transaction.add(R.id.fragment_layout, fragment_my);
                }else {
                    transaction.show(fragment_my);
                }
                break;
        }
        transaction.commit();
    }
}
