package com.example.krid.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;
import com.example.krid.model.Campaign;

import java.util.ArrayList;

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Campaign> campaignList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtId;
        private TextView txtTitle;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.id);
            txtTitle = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }

    public CampaignAdapter(Activity activity, ArrayList<Campaign> campaignList) {
        this.activity = activity;
        this.campaignList = campaignList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View campaignItemView = inflater.inflate(R.layout.home_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(campaignItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Campaign Campaign = campaignList.get(position);
        holder.txtId.setText(Campaign.getId());
        holder.txtTitle.setText(Campaign.getTitle());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            }
        });
    }

    @Override
    public int getItemCount() {
        return campaignList.size();
    }
}
