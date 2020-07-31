package com.example.krid.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.krid.R;
import com.example.krid.adapter.FieldSpinnerAdapter;
import com.example.krid.database.AdvertiserDao;
import com.example.krid.model.Advertiser;
import com.example.krid.model.Field;
import com.example.krid.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

public class AccountDetailFragment extends Fragment {
    private static EditText username ;
    private static EditText companyName;
    private static EditText email;
    private static EditText name;
    private static EditText phone;
    private static EditText website;
    private static EditText pass;
    private static EditText newPass;
    private static EditText newPasscf;
    private static Button btnSaveInfo;
    private static Button btnSavePass;
    private static Spinner spnField;

    private static Advertiser advertiser;
    private static FirebaseFirestore db;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences pref = getContext().getSharedPreferences(Constants.PREF_NAME_ADVERTISER, Constants.PRIVATE_MODE);
        final String  id = pref.getString(Constants.PREF_KEY_SESSION_ID,"6EW3nUADm67Vd8H20rGn");

        View root = inflater.inflate(R.layout.fragment_account_detail_adv, container, false);
        username=root.findViewById(R.id.ed_advertiser_username);
        companyName=root.findViewById(R.id.ed_advertiser_comp_name);
        email=root.findViewById(R.id.ed_advertiser_email);
        name=root.findViewById(R.id.ed_advertiser_name);
        phone=root.findViewById(R.id.ed_advertiser_phone);
        website=root.findViewById(R.id.ed_advertiser_website);
        btnSaveInfo=root.findViewById(R.id.btn_save_adv_info);
        spnField = root.findViewById(R.id.spnField);
        btnSavePass = root.findViewById(R.id.btn_save_password);
        newPass =root.findViewById(R.id.ed_advertiser_new_password);
        pass=root.findViewById(R.id.ed_advertiser_password);
        newPasscf =root.findViewById(R.id.ed_advertiser_new_password_cf);

        displayAccount(id);

        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                advertiser.setEmail(email.getText().toString().trim());
                advertiser.setCompanyName(companyName.getText().toString());
                advertiser.setWebsite(website.getText().toString());
                advertiser.setPhone(phone.getText().toString());
                advertiser.setName(name.getText().toString());
                AdvertiserDao.collection.document("5JORX9EDu8UjUnW08V2w")
                        .set(advertiser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "update 1pGHRhqKTmPuxseIB3ZL done", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
                displayAccount(id);

            }
        });


        btnSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = pass.getText().toString().trim();
                String npw = newPass.getText().toString().trim();
                String npwcf = newPasscf.getText().toString().trim();
                if(!advertiser.getPassword().equals(pw)){
                    Toast.makeText(getContext(), "incorrect password", Toast.LENGTH_LONG).show();
                }else if(pw.equalsIgnoreCase(npw)){
                    Toast.makeText(getContext(), "new password cannot be identical to the old password", Toast.LENGTH_LONG).show();
                }else if(!npw.trim().equalsIgnoreCase(npwcf.trim())){
                    Toast.makeText(getContext(), "confirm password must me identical to new password", Toast.LENGTH_LONG).show();
                }else{
                    advertiser.setPassword(npw);

                    AdvertiserDao.collection.document(id)
                            .set(advertiser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "update password done", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });
                    displayAccount(id);
                }
            }
        });


        return root;
    }

    private static void displayAccount(String id){
        advertiser = new Advertiser();
        AdvertiserDao.collection.whereEqualTo("id",id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
               advertiser = (Advertiser) queryDocumentSnapshots.toObjects(Advertiser.class).get(0);
                username.setText(advertiser.getUsername());
                companyName.setText(advertiser.getCompanyName());
                email.setText(advertiser.getEmail());
                name.setText(advertiser.getName());
                phone.setText(advertiser.getPhone());
                website.setText(advertiser.getWebsite());

            }
        });
    }

}
