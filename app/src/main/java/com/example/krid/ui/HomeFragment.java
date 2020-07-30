package com.example.krid.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.krid.R;
import com.example.krid.util.Constants;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences pref1 = getContext().getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);
        String sessionInfId = pref1.getString(Constants.PREF_KEY_SESSION_ID, "");
        SharedPreferences pref2 = getContext().getSharedPreferences(Constants.PREF_NAME_ADVERTISER, Constants.PRIVATE_MODE);
        String sessionAdvId = pref2.getString(Constants.PREF_KEY_SESSION_ID, "");

        if(!sessionInfId.equals("")) {
            ((InfluenceActivity)getActivity()).getSupportActionBar().setTitle("Home");
        } else if(!sessionAdvId.equals("")) {
            ((AdvertiserActivity)getActivity()).getSupportActionBar().setTitle("Home");
        } else {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Home");
        }
        return root;
    }

}
