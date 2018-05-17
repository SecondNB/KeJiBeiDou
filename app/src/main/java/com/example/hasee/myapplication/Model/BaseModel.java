package com.example.hasee.myapplication.Model;

import java.util.Map;

/**
 * Created by Dash on 2018/3/14.
 */
public interface BaseModel {
    void getDataFromNet(String url, Map<String, String> map);
    void unsubcribe();
}
