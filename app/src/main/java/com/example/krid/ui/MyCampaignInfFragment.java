package com.example.krid.ui;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;
import com.example.krid.adapter.AllCampaignsAdapter;
import com.example.krid.adapter.MyCampaignsAdapter;
import com.example.krid.database.CampaignDao;
import com.example.krid.model.Campaign;
import com.example.krid.model.InfluencerCampaign;
import com.example.krid.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MyCampaignInfFragment extends Fragment {
    private RecyclerView rsvMyCampaigns;
    private ArrayList<Campaign> campaigns;
    private ArrayList<String> infStatusIds;
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_campaigns_inf, container, false);
        ((InfluenceActivity)getActivity()).getSupportActionBar().setTitle("My campaigns");
        rsvMyCampaigns = root.findViewById(R.id.rsvMyCampaigns);

        campaigns = new ArrayList<Campaign>();
        infStatusIds = new ArrayList<String>();
        SharedPreferences pref = getContext().getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);
        String sessionInfId = pref.getString(Constants.PREF_KEY_SESSION_ID, "");

        final StorageReference imgsRef = FirebaseStorage.getInstance().getReference().child("Campaign");
        FirebaseFirestore.getInstance().collection("InfluencerCampaign").whereEqualTo("infId", sessionInfId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        final InfluencerCampaign ic = document.toObject(InfluencerCampaign.class);
                        final String camId = ic.getCamId();
                        infStatusIds.add(ic.getStatus());

                        FirebaseFirestore.getInstance().collection("Campaign").document(camId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    final Campaign cam = task.getResult().toObject(Campaign.class);

                                    imgsRef.child(cam.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            cam.setImage(uri.toString());
                                            campaigns.add(cam);
                                            MyCampaignsAdapter camAdapter = new MyCampaignsAdapter(getActivity(), campaigns, infStatusIds);
                                            rsvMyCampaigns.setAdapter(camAdapter);
                                            rsvMyCampaigns.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });

        return root;
    }
}
