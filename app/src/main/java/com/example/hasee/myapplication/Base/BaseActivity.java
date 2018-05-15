package com.example.hasee.myapplication.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hasee.myapplication.Contract.BaseContract;
import com.example.hasee.myapplication.Presenter.BasePresenter;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BaseContract.IBaseView,P extends BasePresenter> extends AppCompatActivity implements BaseContract.IBaseView{

    public P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        ButterKnife.bind(this);
        presenter=initPresenter();
        if(presenter!=null){
            presenter.attatchWindow(this,initModel());
        }
    }
    //初始化布局文件
    public abstract int bindLayout();
    //初始化数据
    public abstract void initData();
    //初始化P层
    public abstract P initPresenter();
    //Toast
    public void showToats(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    //初始化M层
    public abstract BaseModel initModel();
    //初始化跳转
    public void startActivity(Class<?> clz){
        Intent intent=new Intent(this,clz);

        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
