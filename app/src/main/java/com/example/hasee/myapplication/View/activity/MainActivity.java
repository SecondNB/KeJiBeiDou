package com.example.hasee.myapplication.View.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


import com.example.hasee.myapplication.Contract.Constant;
import com.example.hasee.myapplication.Presenter.CategryPresenter;
import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.View.interfaces.Imain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity  implements Imain {

    private CategryPresenter categryPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categryPresenter = new CategryPresenter(this);
        //产生关联
        categryPresenter.attachView(this);

        getData();
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

    private void getData() {

        Map<String,String> map = new HashMap<>();
        //获取数据
        categryPresenter.getDataFromNet(Constant.homePage_URL,map);
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
