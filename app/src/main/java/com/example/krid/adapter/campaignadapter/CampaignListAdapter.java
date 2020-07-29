package com.example.krid.adapter.campaignadapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;
import com.example.krid.model.Campaign;

import java.util.ArrayList;

public class CampaignListAdapter extends RecyclerView.Adapter<CampaignListAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Campaign> campaignList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtDescription;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            txtDescription = itemView.findViewById(R.id.tvShip);
            image = itemView.findViewById(R.id.image);
        }
    }

    public CampaignListAdapter(Activity activity, ArrayList<Campaign> campaignList) {
        this.activity = activity;
        this.campaignList = campaignList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View campaignItemView = inflater.inflate(R.layout.item_campaign, parent, false);
        ViewHolder viewHolder = new ViewHolder(campaignItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Campaign Campaign = campaignList.get(position);
        holder.txtTitle.setText(Campaign.getTitle());
        holder.txtDescription.setText(Campaign.getId());


    }

    @Override
    public int getItemCount() {
        return campaignList.size();
    }
}
