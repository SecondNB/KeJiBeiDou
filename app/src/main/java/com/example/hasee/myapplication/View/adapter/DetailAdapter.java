package com.example.hasee.myapplication.View.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.myapplication.Model.bean.DetailBean;
import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.View.interfaces.OnItemListner;

public class DetailAdapter extends RecyclerView.Adapter<MyDetailViewHouder>{

    Context context;
    DetailBean detailBean;
    private OnItemListner onItemListner;

    public DetailAdapter(Context context,DetailBean detailBean) {
        this.context=context;
        this.detailBean=detailBean;
    }

    @NonNull
    @Override
    public MyDetailViewHouder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.zhuan_ti_item_layout,null);
        MyDetailViewHouder myViewHouder = new MyDetailViewHouder(view);
        return myViewHouder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyDetailViewHouder holder, final int position) {
        String pic = detailBean.getRet().getList().get(position).getChildList().get(position).getPic();
        Glide.with(context).load(pic).into(holder.imageView);
        String title = detailBean.getRet().getList().get(position).getChildList().get(position).getTitle();
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
        return detailBean.getRet().getList().size();
    }

    public void setOnItemListner(OnItemListner onItemListner) {
        this.onItemListner = onItemListner;
    }
}

class MyDetailViewHouder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView tv_title;

    public MyDetailViewHouder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img_video);
        tv_title = itemView.findViewById(R.id.tv_title);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}

