package com.example.krid.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;
import com.example.krid.adapter.CampaignAdapter;
import com.example.krid.model.Campaign;

import java.util.ArrayList;


public class MyCampaignAdvFragment extends Fragment {
    private ArrayList<Campaign> campaignsList ;
    private RecyclerView recyclerViewCampaigns;
    private CampaignAdapter campaignAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_mycampaignadv, container, false);
        recyclerViewCampaigns = root.findViewById(R.id.recycleMyCampaign);

        displayAllCampaigns();
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        displayAllCampaigns();
    }

    private void displayAllCampaigns() {
        campaignsList = new ArrayList<Campaign>();
        campaignsList.add(new Campaign("1", "cam 1", "alo"));
        campaignsList.add(new Campaign("21", "cam 4", "alo"));
        campaignsList.add(new Campaign("13", "cam 6", "alo"));
        campaignsList.add(new Campaign("14", "cam 2", "alo"));

        campaignAdapter = new CampaignAdapter(getActivity(), campaignsList);

        recyclerViewCampaigns.setAdapter(campaignAdapter);
        recyclerViewCampaigns.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
