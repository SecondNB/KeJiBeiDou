package com.example.hasee.myapplication.Contract;

/**
 * BaseContract虽然没有什么共有的因素，还是留一个base，万一以后有需求
 */

public class BaseContract {
    public interface IBaseModel{

    }
    public interface IBasePresenter{

    }
    public interface IBaseView{
        void successBack(Object o);
        void errorBack(String error);
    }
}
