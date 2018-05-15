package com.example.hasee.myapplication.Presenter;

import com.example.hasee.myapplication.Contract.BaseContract;
/**
 * 同时持有module和view，在actdestory的时候释放
 */
public class BasePresenter<V extends BaseContract.IBaseView,M extends BaseContract.IBaseModel>  {
    public V view;
    public M model;
    //绑定model和view
    public void attatchWindow(V view, M model){
        this.model=model;
        this.view=view;
    }
    //解绑
    void detachWindow() {
        this.model = null;
        this.view = null;
    }


}
