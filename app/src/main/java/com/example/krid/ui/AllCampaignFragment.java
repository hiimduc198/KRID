package com.example.krid.ui;

import android.content.SharedPreferences;
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

import com.example.krid.R;
import com.example.krid.adapter.campaignadapter.AllCampaignAdapter;
import com.example.krid.adapter.campaignadapter.IntroSlideAdapter;
import com.example.krid.database.CampaignDao;
import com.example.krid.model.Campaign;
import com.example.krid.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

public class AllCampaignFragment extends Fragment {
    CarouselView carouselView;

    private RecyclerView rcvIntroSlide;
    private RecyclerView rcvCampaign;
    private IntroSlideAdapter introSlideAdapter;
    private AllCampaignAdapter campaignGuestAdapter;

    private ArrayList<String> listImage = new ArrayList<String>();
    private ArrayList<Campaign> listCampaign = new ArrayList<Campaign>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences pref = getContext().getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);
        String sessionInfId = pref.getString(Constants.PREF_KEY_SESSION_ID, "");

        if(sessionInfId.equals("")) {
            ((MainActivity)getActivity()).getSupportActionBar().setTitle("Campaigns");
        } else {
            ((InfluenceActivity)getActivity()).getSupportActionBar().setTitle("Campaigns");
        }

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
        final ArrayList<String> listImageUrl = new ArrayList<>();

        listImage = new ArrayList<String>();
        listImage.add("hotcampaign1.PNG");
        listImage.add("hotcampaign2.PNG");
        listImage.add("hotcampaign3.PNG");
        listImage.add("hotcampaign4.PNG");

        for(String image:listImage) {
            FirebaseStorage.getInstance().getReference().child("HotCampaign").child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    listImageUrl.add(uri.toString());
                    introSlideAdapter = new IntroSlideAdapter(getActivity(), listImageUrl);
                    rcvIntroSlide.setAdapter(introSlideAdapter);
                    rcvIntroSlide.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
                }
            });
        }
    }

    private void initRsvCampaign() {
        listCampaign = new ArrayList<Campaign>();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference imgsRef = storage.getReference().child("Campaign");
        CampaignDao.collection.orderBy("applyTime", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                                campaignGuestAdapter = new AllCampaignAdapter(getActivity(), listCampaign);
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
