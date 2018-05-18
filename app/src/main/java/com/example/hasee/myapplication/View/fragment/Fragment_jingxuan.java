package com.example.hasee.myapplication.View.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hasee.myapplication.Contract.Constant;
import com.example.hasee.myapplication.Model.bean.HomeBean;
import com.example.hasee.myapplication.Presenter.CategryPresenter;
import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.View.adapter.JingxuanAdapter;
import com.example.hasee.myapplication.View.interfaces.Imain;
import com.example.hasee.myapplication.utils.ChenJinUtil;
import com.example.hasee.myapplication.utils.GlideImageLoader;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

public class Fragment_jingxuan extends Fragment implements Imain {

    @BindView(R.id.jingxuan_banner)
    Banner jingxuanBanner;
    @BindView(R.id.jingxuan_sousuo)
    LinearLayout jingxuanSousuo;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.smartrefresh_Layout)
    SmartRefreshLayout smartrefreshLayout;
    Unbinder unbinder;
    private CategryPresenter categryPresenter;


    private HomeBean homeBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /* View view = View.inflate(getActivity(), R.layout.fragment_jingxuan_layout, null);
        return view;*/
        View view = View.inflate(getContext(), R.layout.fragment_jingxuan_layout, null);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Map<String, String> map = new HashMap<>();
        categryPresenter = new CategryPresenter((Imain) this);
        categryPresenter.getDataFromNet(Constant.homePage_URL, map);
        smartrefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartrefreshLayout.finishRefresh(2000);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        initChenJin();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            initChenJin();
        }
    }


    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), Color.TRANSPARENT);
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            homeBean = new Gson().fromJson(json, HomeBean.class);
            Log.e("sdadasdasd", homeBean.getMsg());
            LunBo();
            Tuijian();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Tuijian() {
        List<HomeBean.RetBean.ListBean.ChildListBean> tuijian = homeBean.getRet().getList().get(4).getChildList();
        JingxuanAdapter jingxuanAdapter = new JingxuanAdapter(getActivity(),tuijian);
        recyclerView.setAdapter(jingxuanAdapter);
    }

    private void LunBo() {
        List<HomeBean.RetBean.ListBean.ChildListBean> childList = homeBean.getRet().getList().get(0).getChildList();
        Log.e("AA", childList.size() + "");
        List<String> imageArray = new ArrayList<>();
        for (int i = 0; i < childList.size(); i++) {
            imageArray.add(childList.get(i).getPic());
        }
        //设置图片加载器
        jingxuanBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        jingxuanBanner.setImages(imageArray);
        //设置轮播时间
        jingxuanBanner.setDelayTime(1500);
        //banner设置方法全部调用完毕时最后调用
        jingxuanBanner.start();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.jingxuan_sousuo)
    public void onViewClicked() {
    }
}
