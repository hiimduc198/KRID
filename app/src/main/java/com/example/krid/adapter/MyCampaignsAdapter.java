package com.example.krid.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.krid.R;
import com.example.krid.model.Campaign;
import com.example.krid.model.CampaignStatus;
import com.example.krid.model.City;
import com.example.krid.model.Influencer;
import com.example.krid.model.InfluencerStatus;
import com.example.krid.ui.CampaignDetailFragment;
import com.example.krid.ui.InfluenceActivity;
import com.example.krid.ui.MainActivity;
import com.example.krid.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyCampaignsAdapter extends RecyclerView.Adapter<MyCampaignsAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Campaign> campaignList;
    private ArrayList<String> infStatusIds;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private Button btnCamStatus;
        private Button btnInfStatus;
        private ImageView imgCampaign;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            btnCamStatus = itemView.findViewById(R.id.btnCamStatus);
            btnInfStatus = itemView.findViewById(R.id.btnInfStatus);
            imgCampaign = itemView.findViewById(R.id.imgCampaign);
        }
    }

    public MyCampaignsAdapter(Activity activity, ArrayList<Campaign> campaignList, ArrayList<String> infStatusIds) {
        this.activity = activity;
        this.campaignList = campaignList;
        this.infStatusIds = infStatusIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View campaignItemView = inflater.inflate(R.layout.item_my_campaigns_inf, parent, false);
        ViewHolder viewHolder = new ViewHolder(campaignItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int po) {
        final int position = po;
        final Campaign cam = campaignList.get(position);
        holder.txtTitle.setText(cam.getTitle());

        FirebaseFirestore.getInstance().collection("CampaignStatus").document(cam.getCamStatusId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            CampaignStatus status = task.getResult().toObject(CampaignStatus.class);
            holder.btnCamStatus.setText(status.getName());
            FirebaseFirestore.getInstance().collection("InfluencerStatus").document(infStatusIds.get(position)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    InfluencerStatus status = task.getResult().toObject(InfluencerStatus.class);
                    holder.btnInfStatus.setText(status.getName());

                    Glide.with(activity)
                            .load(cam.getImage())
                            .fitCenter()
                            .into(holder.imgCampaign);

                    SharedPreferences pref = activity.getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);
                    final String sessionInfId = pref.getString(Constants.PREF_KEY_SESSION_ID, "");

                    holder.imgCampaign.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((InfluenceActivity)activity).navigateToFragmentWithArgs(new CampaignDetailFragment(), cam);
                        }
                    });
                }
            });
            }
        });
    }

    @Override
    public int getItemCount() {
        return campaignList.size();
    }
}
