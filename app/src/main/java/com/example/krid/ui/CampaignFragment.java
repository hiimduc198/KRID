package com.example.krid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;
import com.example.krid.adapter.campaignadapter.CampaignListAdapter;
import com.example.krid.adapter.campaignadapter.IntroSlideAdapter;
import com.example.krid.model.Campaign;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

public class CampaignFragment extends Fragment {
    CarouselView carouselView;

    private RecyclerView rcvIntroSlide;
    private RecyclerView rcvCampaign;
    private IntroSlideAdapter introSlideAdapter;
    private CampaignListAdapter campaignListAdapter;

    private ArrayList<String> listImageUrl = new ArrayList<String>();
    private ArrayList<Campaign> listCampaign = new ArrayList<Campaign>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_campaign, container, false);
        rcvIntroSlide = root.findViewById(R.id.rcvSlideIntro);
        rcvCampaign = root.findViewById(R.id.rcvCampaign);
        initIntroSlideAdapter();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initIntroSlideAdapter() {
        listImageUrl = new ArrayList<String>();
        listImageUrl.add("URL1");
        listImageUrl.add("URL2");
        listImageUrl.add("URL3");
        listImageUrl.add("URL4");

        introSlideAdapter = new IntroSlideAdapter(getActivity(), listImageUrl);

        rcvIntroSlide.setAdapter(introSlideAdapter);
        rcvIntroSlide.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

        listCampaign = new ArrayList<Campaign>();
        listCampaign.add(new Campaign("Ship","CAMPAIGN 1", ""));
        listCampaign.add(new Campaign("KO Ship","CAMPAIGN 2", ""));
        listCampaign.add(new Campaign("Ship","CAMPAIGN 3", ""));
        listCampaign.add(new Campaign("KO Ship","CAMPAIGN 4", ""));
        listCampaign.add(new Campaign("Ship","CAMPAIGN 5", ""));
        listCampaign.add(new Campaign("KO Ship","CAMPAIGN 6", ""));
        listCampaign.add(new Campaign("Ship","CAMPAIGN 7", ""));
        listCampaign.add(new Campaign("KO Ship","CAMPAIGN 8", ""));

        campaignListAdapter = new CampaignListAdapter(getActivity(), listCampaign);

        rcvCampaign.setAdapter(campaignListAdapter);
        rcvCampaign.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }
}
