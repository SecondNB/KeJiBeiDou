package com.example.hasee.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hasee.myapplication.Base.BaseActivity;
import com.example.hasee.myapplication.Base.BaseModel;
import com.example.hasee.myapplication.Contract.LoginContract;
import com.example.hasee.myapplication.Model.LoginModel;
import com.example.hasee.myapplication.Presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<LoginContract.ILoginView, LoginPresenter> implements LoginContract.ILoginView {


    @BindView(R.id.text_name)
    TextView textName;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }


    @Override
    public BaseModel initModel() {
        return new LoginModel();
    }


    @Override
    public void successBack(Object o) {

    }

    @Override
    public void errorBack(String error) {
        showToats(error);
    }

    //请求参数
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPwd() {
        return null;
    }


}
