package com.example.hasee.myapplication.View.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.myapplication.R;

public class JingxuanHolder extends RecyclerView.ViewHolder {

    public ImageView tuijian_image;
    public TextView tuijian_title;

    public JingxuanHolder(View itemView) {
        super(itemView);
        tuijian_image = itemView.findViewById(R.id.tuijian_image);
        tuijian_title = itemView.findViewById(R.id.tuijian_title);
    }
}
