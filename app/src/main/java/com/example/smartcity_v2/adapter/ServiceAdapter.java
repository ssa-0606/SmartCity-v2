package com.example.smartcity_v2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_v2.R;
import com.example.smartcity_v2.beans.ServiceList;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<ServiceList> serviceLists;
    private RecyclerView.ViewHolder holder;

    public ServiceAdapter(int resourceId, List<ServiceList> serviceLists) {
        this.resourceId = resourceId;
        this.serviceLists = serviceLists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(resourceId, null);
        holder = new RecyclerView.ViewHolder(inflate) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ServiceList serviceList = serviceLists.get(position);
        View itemView = holder.itemView;
        ImageView imageView = (ImageView) itemView.findViewById(R.id.service_img);
        TextView viewById = (TextView) itemView.findViewById(R.id.service_name);
        Glide.with(itemView).load(serviceList.getImgUrl()).into(imageView);
        viewById.setText(serviceList.getServiceName());
    }

    @Override
    public int getItemCount() {
        return serviceLists.size();
    }
}
