package com.example.krid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krid.R;
import com.example.krid.adapter.CampaignAdapter;
import com.example.krid.adapter.KolsAdapter;
import com.example.krid.model.Campaign;
import com.example.krid.model.Kol;

import java.util.ArrayList;

public class BookingAdvFragment extends Fragment {
    private ArrayList<Kol> kolList ;
    private RecyclerView recyclerViewBookingKol;
    private KolsAdapter kolsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_booking_adv, container, false);
        recyclerViewBookingKol = root.findViewById(R.id.recycleBookingKol);

        displayAllKols();
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        displayAllKols();
    }

    private void displayAllKols() {
        kolList = new ArrayList<Kol>();
        kolList.add(new Kol("GetLozzw", "#HotFace #Sporty", "Getlozze"));
        kolList.add(new Kol("GetLozzw", "#HotFace #Sporty", "Getlozze"));
        kolList.add(new Kol("GetLozzw", "#HotFace #Sporty", "Getlozze"));
        kolList.add(new Kol("GetLozzw", "#HotFace #Sporty", "Getlozze"));
        kolList.add(new Kol("GetLozzw", "#HotFace #Sporty", "Getlozze"));
        kolList.add(new Kol("GetLozzw", "#HotFace #Sporty", "Getlozze"));

        kolsAdapter = new KolsAdapter(getActivity(), kolList);

        recyclerViewBookingKol.setAdapter(kolsAdapter);
        recyclerViewBookingKol.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
