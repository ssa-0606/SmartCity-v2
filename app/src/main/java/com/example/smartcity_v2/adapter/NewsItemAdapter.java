package com.example.smartcity_v2.adapter;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_v2.R;
import com.example.smartcity_v2.beans.ItemNewsList;


import java.util.List;

public class NewsItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private int resourceId;
    private List<ItemNewsList> itemNewsLists;
    private RecyclerView.ViewHolder holder;

    public NewsItemAdapter(int resourceId, List<ItemNewsList> itemNewsLists) {
        this.resourceId = resourceId;
        this.itemNewsLists = itemNewsLists;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(resourceId, null);
        holder = new RecyclerView.ViewHolder(view) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemNewsList itemNewsList = itemNewsLists.get(position);
        View itemView = holder.itemView;
        ImageView imageView = itemView.findViewById(R.id.img_data);
        TextView title = itemView.findViewById(R.id.title_data);
        TextView content = itemView.findViewById(R.id.content_data);
        TextView publishDate = itemView.findViewById(R.id.publishDate_data);
        TextView like = itemView.findViewById(R.id.likeNum_data);
        TextView read = itemView.findViewById(R.id.viewNum_data);

        Glide.with(itemView).load(itemNewsList.getCover()).into(imageView);
        title.setText(itemNewsList.getTitle());
        Log.d("TAG4", "onBindViewHolder: "+Html.fromHtml(itemNewsList.getContent()));
        content.setText(Html.fromHtml(itemNewsList.getContent()));
        publishDate.setText(itemNewsList.getPublishDate());
        like.setText(String.valueOf(itemNewsList.getLikeNum()));
        read.setText(String.valueOf(itemNewsList.getReadNum()));

    }

    @Override
    public int getItemCount() {
        return itemNewsLists.size();
    }
}
