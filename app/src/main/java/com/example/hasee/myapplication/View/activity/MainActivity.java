package com.example.hasee.myapplication.View.activity;

import android.app.Application;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity  implements Imain {

    private RadioGroup radiogroup;
    private CategryPresenter categryPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiogroup = findViewById(R.id.radio_group);

        categryPresenter = new CategryPresenter(this);
        //产生关联
        categryPresenter.attachView(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_jingxuan()).commit();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.bt01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_jingxuan()).commit();
                        break;
                    case R.id.bt02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_zhuanti()).commit();
                        break;
                    case R.id.bt03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_faxian()).commit();
                        break;
                    case R.id.bt04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Fragment_my()).commit();
                        break;
                }
            }
        });
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
}
