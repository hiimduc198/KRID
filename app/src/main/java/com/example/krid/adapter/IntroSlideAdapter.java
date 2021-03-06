package com.example.krid.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.krid.R;

import java.util.ArrayList;

public class IntroSlideAdapter extends RecyclerView.Adapter<IntroSlideAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<String> listImage;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }

    public IntroSlideAdapter(Activity activity, ArrayList<String> listImage) {
        this.activity = activity;
        this.listImage = listImage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View campaignItemView = inflater.inflate(R.layout.item_slide_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(campaignItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(activity)
                .load(listImage.get(position))
                .fitCenter()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }
}

