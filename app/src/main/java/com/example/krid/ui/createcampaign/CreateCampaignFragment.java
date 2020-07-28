package com.example.krid.ui.createcampaign;

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


public class CreateCampaignFragment extends Fragment {
    private CreateCampaignViewModel createCampaignViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        createCampaignViewModel =
                ViewModelProviders.of(this).get(CreateCampaignViewModel.class);
        View root = inflater.inflate(R.layout.create_campaign_03, container, false);
        final TextView textView = root.findViewById(R.id.title_create_campaign_03);
        createCampaignViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
