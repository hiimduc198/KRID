package com.example.krid.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.krid.R;
import com.example.krid.database.AdvertiserDao;
import com.example.krid.model.Advertiser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AccountDetailFragment extends Fragment {
    private static EditText username;
    private static EditText companyName;
    private static EditText email;
    private static EditText name;
    private static  EditText phone;
    private static EditText website;
    private static CheckBox sale;
    private static CheckBox branding;
    private static CheckBox checkin;
    private static CheckBox viral;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account_detail_adv, container, false);
        displayCampaign("5JORX9EDu8UjUnW08V2w");
//        AdvertiserDao.collection.whereEqualTo("fieldID","5JORX9EDu8UjUnW08V2w");

        return root;
    }
    private static Advertiser advertiser;
    private static void displayCampaign(String id){
        advertiser = new Advertiser();
        AdvertiserDao.collection.whereEqualTo("id",id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
               advertiser = (Advertiser) queryDocumentSnapshots.toObjects(Advertiser.class);
               username.setText(advertiser.getUsername());
            }
        });
    }

}
