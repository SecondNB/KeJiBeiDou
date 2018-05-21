package com.example.hasee.myapplication.View.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.hasee.myapplication.Model.bean.HomeBean;
import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.View.holder.JingxuanHolder;

import java.util.List;

public class JingxuanAdapter extends RecyclerView.Adapter<JingxuanHolder> {
    Context context;
    List<HomeBean.RetBean.ListBean.ChildListBean> tuijian;

    public JingxuanAdapter(Context context, List<HomeBean.RetBean.ListBean.ChildListBean> tuijian) {
        this.context = context;
        this.tuijian = tuijian;
    }

    @NonNull
    @Override
    public JingxuanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_tuijian_layout, null);
        JingxuanHolder jingxuanHolder = new JingxuanHolder(view);
        return jingxuanHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JingxuanHolder holder, int position) {
        Glide.with(context).load(tuijian.get(position).getPic()).into(holder.tuijian_image);
        holder.tuijian_title.setText(tuijian.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return tuijian.size();
    }
}
