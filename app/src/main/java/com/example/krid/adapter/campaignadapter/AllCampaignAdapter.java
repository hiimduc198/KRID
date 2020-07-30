package com.example.krid.adapter.campaignadapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.krid.ui.InfluenceActivity;
import com.example.krid.ui.MainActivity;
import com.example.krid.R;
import com.example.krid.model.Campaign;
import com.example.krid.model.CampaignStatus;
import com.example.krid.model.City;
import com.example.krid.ui.CampaignDetailFragment;
import com.example.krid.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AllCampaignAdapter extends RecyclerView.Adapter<AllCampaignAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Campaign> campaignList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtCity;
        private TextView txtStatus;
        private ImageView imgCampaign;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtCity = itemView.findViewById(R.id.txtCity);
            imgCampaign = itemView.findViewById(R.id.imgCampaign);
        }
    }

    public AllCampaignAdapter(Activity activity, ArrayList<Campaign> campaignList) {
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
        final Campaign cam = campaignList.get(position);
        holder.txtTitle.setText(cam.getTitle());

        FirebaseFirestore.getInstance().collection("CampaignStatus").document(cam.getCamStatusId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                CampaignStatus status = task.getResult().toObject(CampaignStatus.class);
                holder.txtStatus.setText(status.getName());
                if(!cam.getCamStatusId().equals("ULAjPlDVOTh7IZgAPW5N")) {
                    holder.txtStatus.setBackgroundColor(Color.GRAY);
                }
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collection = db.collection("City");
        collection.document(cam.getCityId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    City city = task.getResult().toObject(City.class);
                    holder.txtCity.setText(city.getName());
                    cam.setLocation(city.getName());
                }
            }
        });

        Glide.with(activity)
                .load(cam.getImage())
                .fitCenter()
                .into(holder.imgCampaign);

        SharedPreferences pref = activity.getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);
        final String sessionInfId = pref.getString(Constants.PREF_KEY_SESSION_ID, "");

        holder.imgCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sessionInfId.equals("")) {
                    ((MainActivity)activity).navigateToFragmentWithArgs(new CampaignDetailFragment(), cam);
                } else {
                    ((InfluenceActivity)activity).navigateToFragmentWithArgs(new CampaignDetailFragment(), cam);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return campaignList.size();
    }


}
