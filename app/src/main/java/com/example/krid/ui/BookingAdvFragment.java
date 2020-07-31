package com.example.krid.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;

import com.example.krid.adapter.BookKolsAdvAdapter;
import com.example.krid.model.Kol;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BookingAdvFragment extends Fragment {
    private ArrayList<Kol> listKol ;
    private RecyclerView rcvKol;
    private BookKolsAdvAdapter advAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_booking_adv, container, false);
        rcvKol = root.findViewById(R.id.recycleBookingKol);

        displayAllKols();
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        displayAllKols();
    }

    private void displayAllKols() {
        listKol = new ArrayList<Kol>();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference imgsRef = storage.getReference().child("Kols");
        FirebaseFirestore.getInstance().collection("Kol").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        final Kol kol = document.toObject(Kol.class);
                        imgsRef.child(kol.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                kol.setImage(uri.toString());
                                listKol.add(kol);
                                advAdapter = new BookKolsAdvAdapter(getActivity(), listKol);
                                rcvKol.setAdapter(advAdapter);
                                rcvKol.setLayoutManager(new GridLayoutManager(getActivity(), 1));
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
