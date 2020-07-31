package com.example.krid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.krid.util.Constants;

public class LogoutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        SharedPreferences pref1 = getContext().getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);
        String sessionInfId = pref1.getString(Constants.PREF_KEY_SESSION_ID, "");
        SharedPreferences pref2 = getContext().getSharedPreferences(Constants.PREF_NAME_ADVERTISER, Constants.PRIVATE_MODE);
        String sessionAdvId = pref2.getString(Constants.PREF_KEY_SESSION_ID, "");

        if(!sessionInfId.equals("")) {
            SharedPreferences.Editor editor = pref1.edit();
            editor.remove(Constants.PREF_KEY_SESSION_ID);
            editor.remove(Constants.PREF_KEY_SESSION_NAME);
            editor.clear();
            editor.commit();
            Intent intent = new Intent(requireContext(), MainActivity.class);
            requireActivity().setResult(Constants.ACTIVITY_INFLUENCE, intent);
        } else {
            SharedPreferences.Editor editor = pref2.edit();
            editor.remove(Constants.PREF_KEY_SESSION_ID);
            editor.remove(Constants.PREF_KEY_SESSION_NAME);
            editor.clear();
            editor.commit();
            Intent intent = new Intent(requireContext(), MainActivity.class);
            requireActivity().setResult(Constants.ACTIVITY_ADVERTISER, intent);
        }
        requireActivity().finish();

        return root;
    }
}