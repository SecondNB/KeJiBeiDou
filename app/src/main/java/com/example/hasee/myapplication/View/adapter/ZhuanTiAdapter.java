package com.example.hasee.myapplication.View.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.myapplication.Model.bean.ZhuanTiBean;
import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.View.interfaces.OnItemListner;

public class ZhuanTiAdapter extends RecyclerView.Adapter<MyViewHouder>{

    Context context;
    ZhuanTiBean zhuanTiBean;
    private OnItemListner onItemListner;

    public ZhuanTiAdapter(Context context, ZhuanTiBean zhuanTiBean) {
        this.context=context;
        this.zhuanTiBean=zhuanTiBean;
    }

    @NonNull
    @Override
    public MyViewHouder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.zhuan_ti_item_layout,null);
        MyViewHouder myViewHouder = new MyViewHouder(view);
        return myViewHouder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHouder holder, final int position) {
        String pic = zhuanTiBean.getRet().getList().get(position).getPic();
        Glide.with(context).load(pic).into(holder.imageView);
        String title = zhuanTiBean.getRet().getList().get(position).getTitle();
        holder.tv_title.setText(title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemListner.onItemClick(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return zhuanTiBean.getRet().getList().size();
    }

    public void setOnItemListner(OnItemListner onItemListner) {
        this.onItemListner = onItemListner;
    }
}

class MyViewHouder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView tv_title;

    public MyViewHouder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img_video);
        tv_title = itemView.findViewById(R.id.tv_title);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
