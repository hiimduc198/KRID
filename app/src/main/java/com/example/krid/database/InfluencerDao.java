package com.example.krid.database;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.krid.model.Influencer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class InfluencerDao {
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static CollectionReference collection = db.collection("Influencer");

    public static void addNewInfluencer(Influencer inf) {
        collection.add(inf)
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

    public static void editInfluencer(Influencer inf) {
        collection.document(inf.getId()).set(inf)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            });
    }
}
