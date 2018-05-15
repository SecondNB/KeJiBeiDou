package com.example.hasee.myapplication.Model;

import com.example.hasee.myapplication.Base.BaseModel;
import com.example.hasee.myapplication.Contract.CallBack;
import com.example.hasee.myapplication.Contract.LoginContract;

public class LoginModel  extends BaseModel implements LoginContract.IModel{
    @Override
    public void toLogin(String name, String pwd, CallBack callback) {

    }
}
