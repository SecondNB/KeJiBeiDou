package com.example.hasee.myapplication.Model;



import com.example.hasee.myapplication.Contract.RetrofitUtil;
import com.example.hasee.myapplication.Presenter.Ipresenter;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class CategaryModel implements BaseModel{


    private Ipresenter prensenterInter;
    public Disposable d;

    public CategaryModel(Ipresenter prensenterInter) {
        this.prensenterInter = prensenterInter;
    }

    @Override
    public void getDataFromNet(String url, Map<String, String> map) {

        //观察者与被观察者其实也是互相持有引用,,,也存在内存泄漏的问题

        RetrofitUtil.getService().doGet(url,map)
                .subscribeOn(Schedulers.io())//在io线程获取数据
                .observeOn(AndroidSchedulers.mainThread())//在android主线程梳理数据
                .subscribe(new Observer<ResponseBody>() {


                    @Override
                    public void onSubscribe(Disposable d) {
                        CategaryModel.this.d = d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //代表获取到数据
                        prensenterInter.onSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //发生错误
                        prensenterInter.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        //完成 事件队列已经到最后

                    }
                });

    }

    @Override
    public void unsubcribe() {

        d.dispose();//解除订阅

    }
}
