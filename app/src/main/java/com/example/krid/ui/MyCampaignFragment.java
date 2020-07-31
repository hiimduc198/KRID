package com.example.krid.ui;

import android.net.Uri;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;
import com.example.krid.adapter.AllCampaignsAdapter;
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

import java.util.ArrayList;

public class MyCampaignFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AllCampaignsAdapter campaignGuestAdapter;

    private RecyclerView rcvCampaign;
    private ArrayList<Campaign> listCampaign = new ArrayList<Campaign>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mycampaigninf, container, false);
        rcvCampaign = root.findViewById(R.id.rcvCampaign_inf);

        return root;
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
                                campaignGuestAdapter = new AllCampaignsAdapter(getActivity(), listCampaign);
                                rcvCampaign.setAdapter(campaignGuestAdapter);
                                rcvCampaign.setLayoutManager(new GridLayoutManager(getActivity(), 1));
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
