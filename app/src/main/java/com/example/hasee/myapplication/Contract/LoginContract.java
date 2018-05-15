package com.example.hasee.myapplication.Contract;

/**
 * 定义需要交互的数据
 */
public class LoginContract {
    public interface ILoginView extends BaseContract.IBaseView{
        String getName();
        String getPwd();
    }

    public interface  IPresenter extends BaseContract.IBasePresenter{
        void toLogin();
    }

    public interface IModel extends BaseContract.IBaseModel{
        void toLogin(String name, String pwd, CallBack callback);
    }

}
