package com.example.krid.ui;

import android.net.Uri;
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

import com.example.krid.MainActivity;
import com.example.krid.R;
import com.example.krid.adapter.campaignadapter.CampaignGuestAdapter;
import com.example.krid.adapter.campaignadapter.IntroSlideAdapter;
import com.example.krid.database.CampaignDao;
import com.example.krid.model.Campaign;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

public class CampaignFragment extends Fragment {


    private RecyclerView rcvIntroSlide;
    private RecyclerView rcvCampaign;
    private IntroSlideAdapter introSlideAdapter;
    private CampaignGuestAdapter campaignGuestAdapter;

    private ArrayList<String> listImageUrl = new ArrayList<String>();
    private ArrayList<Campaign> listCampaign = new ArrayList<Campaign>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Campaigns.");

        View root = inflater.inflate(R.layout.fragment_campaign, container, false);
        rcvIntroSlide = root.findViewById(R.id.rcvSlideIntro);
        rcvCampaign = root.findViewById(R.id.rcvCampaign);
        initIntroSlide();
        initRsvCampaign();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initIntroSlide() {
        listImageUrl = new ArrayList<String>();
        listImageUrl.add("URL1");
        listImageUrl.add("URL2");
        listImageUrl.add("URL3");
        listImageUrl.add("URL4");

        introSlideAdapter = new IntroSlideAdapter(getActivity(), listImageUrl);

        rcvIntroSlide.setAdapter(introSlideAdapter);
        rcvIntroSlide.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
    }

    private void initRsvCampaign() {
        listCampaign = new ArrayList<Campaign>();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference imgsRef = storage.getReference().child("Campaign");
        CampaignDao.collection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        final Campaign cam = document.toObject(Campaign.class);
                        imgsRef.child(cam.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                cam.setImage(uri.toString());
                                listCampaign.add(cam);
                                campaignGuestAdapter = new CampaignGuestAdapter(getActivity(), listCampaign);
                                rcvCampaign.setAdapter(campaignGuestAdapter);
                                rcvCampaign.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                            }
                        });
                    }
                }
            }
        });
    }
}
