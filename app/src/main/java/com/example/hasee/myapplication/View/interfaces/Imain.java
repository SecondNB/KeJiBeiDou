package com.example.hasee.myapplication.View.interfaces;

import okhttp3.ResponseBody;

/**
 * Created by Dash on 2018/3/14.
 */
public interface Imain {

    void onSuccess(ResponseBody responseBody);
    void onError(Throwable throwable);

}
