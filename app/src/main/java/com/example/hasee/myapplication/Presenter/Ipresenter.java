package com.example.hasee.myapplication.Presenter;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by Dash on 2018/3/14.
 */
public interface Ipresenter {
    void getDataFromNet(String url, Map<String, String> map);
    void unsubcribe();

    void onSuccess(ResponseBody responseBody);
    void onError(Throwable throwable);
}
