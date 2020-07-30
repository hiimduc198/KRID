package com.example.krid.database;

import androidx.annotation.NonNull;

import com.example.krid.model.InfluencerCampaign;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class InfluencerCampaignDao {
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static CollectionReference collection = db.collection("InfluencerCampaign");

    public static void addNewInfluencerCampaign(InfluencerCampaign ic) {
        collection.add(ic)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        documentReference.update("id", documentReference.getId());
                        documentReference.update("updated_time", FieldValue.serverTimestamp());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}
