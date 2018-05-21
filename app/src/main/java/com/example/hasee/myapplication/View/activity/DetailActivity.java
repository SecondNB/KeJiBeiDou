package com.example.hasee.myapplication.View.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hasee.myapplication.Contract.Constant;
import com.example.hasee.myapplication.Model.bean.DetailBean;
import com.example.hasee.myapplication.Presenter.DetailPresenter;
import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.View.adapter.DetailAdapter;
import com.example.hasee.myapplication.View.interfaces.Imain;
import com.example.hasee.myapplication.View.interfaces.OnItemListner;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

public class DetailActivity extends AppCompatActivity implements Imain {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String detailUrl = intent.getStringExtra("detailUrl");
        Log.e("detailUrl", "onCreate: "+detailUrl);
        recyclerView = findViewById(R.id.zhuanti_item_recycel);

        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        DetailPresenter detailPresenter = new DetailPresenter(this);
        Map<String, String> map=new HashMap<>();
        //mediaId=8affeb9df9ea4c609905e5c84f2cc6a6
        map.put("mediaId","8affeb9df9ea4c609905e5c84f2cc6a6");
        detailPresenter.getDataFromNet(Constant.videoDetail_URL,map);

    }


    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            DetailBean detailBean = new Gson().fromJson(string, DetailBean.class);
            recyclerView.setLayoutManager(new GridLayoutManager(DetailActivity.this,3));
            DetailAdapter detailAdapter = new DetailAdapter(DetailActivity.this,detailBean);
            recyclerView.setAdapter(detailAdapter);
            detailAdapter.setOnItemListner(new OnItemListner() {
                @Override
                public void onItemClick(int position) {
                    //跳转到下一个页面....传值过去...webView页面
                    Intent intent = new Intent(DetailActivity.this, ShiPingXiangQingActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(int position) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
