package com.example.krid.ui.booking;

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

import com.example.krid.R;

public class BookingFragment extends Fragment {
    private BookingViewModel bookingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookingViewModel =
                ViewModelProviders.of(this).get(BookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_booking, container, false);

        return root;
    }
}
