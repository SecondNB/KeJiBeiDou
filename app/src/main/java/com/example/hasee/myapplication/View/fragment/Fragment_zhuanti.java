package com.example.hasee.myapplication.View.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasee.myapplication.Contract.Constant;
import com.example.hasee.myapplication.Model.bean.ZhuanTiBean;
import com.example.hasee.myapplication.Presenter.CategryPresenter;
import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.View.activity.DetailActivity;
import com.example.hasee.myapplication.View.adapter.ZhuanTiAdapter;
import com.example.hasee.myapplication.View.interfaces.Imain;
import com.example.hasee.myapplication.View.interfaces.OnItemListner;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class Fragment_zhuanti extends Fragment implements Imain{

    @BindView(R.id.recycel)
    RecyclerView recyclerView;
    private CategryPresenter categryPresenter;
    @BindView(R.id.smartrefresh_Layout)
    SmartRefreshLayout smartrefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_zhuanti_layout, null);
        recyclerView = view.findViewById(R.id.recycel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categryPresenter = new CategryPresenter(this);
        Map<String, String> map=new HashMap<>();
        //catalogId=402834815584e463015584e539330016&pnum=7
        map.put("catalogId","402834815584e463015584e539330016");
        map.put("pnum","7");
        categryPresenter.getDataFromNet(Constant.getVideoList_URL,map);


        smartrefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartrefreshLayout.finishRefresh(2000);
            }
        });
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {

        try {
            String string = responseBody.string();
            Log.e("string","====="+string);
            final ZhuanTiBean zhuanTiBean = new Gson().fromJson(string, ZhuanTiBean.class);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            ZhuanTiAdapter zhuanTiAdapter = new ZhuanTiAdapter(getActivity(),zhuanTiBean);
            recyclerView.setAdapter(zhuanTiAdapter);
            zhuanTiAdapter.setOnItemListner(new OnItemListner() {
                @Override
                public void onItemClick(int position) {
                    //跳转到下一个页面....传值过去...webView页面
                    Intent intent = new Intent(getActivity(), DetailActivity.class);

                    String detailUrl = zhuanTiBean.getRet().getList().get(position).getDataId();
                    intent.putExtra("detailUrl",detailUrl);
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
        String message = throwable.getMessage();

    }
}
