package com.example.krid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.krid.R;

public class BookingFragment extends Fragment {
    private Button btnBecomeAdv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_booking, container, false);
        ((MainActivity)requireActivity()).getSupportActionBar().setTitle("KOLs");

        btnBecomeAdv = root.findViewById(R.id.btnBecomeAdv);

        btnBecomeAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).navigateToFragmentWithoutArgs(new Register1Fragment());
            }
        });
        return root;
    }
}
