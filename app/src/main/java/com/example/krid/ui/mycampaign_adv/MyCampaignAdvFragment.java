package com.example.krid.ui.mycampaign_adv;

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


public class MyCampaignAdvFragment extends Fragment {
    private MyCampaignAdvViewModel myCampaignAdvViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myCampaignAdvViewModel =
                ViewModelProviders.of(this).get(MyCampaignAdvViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mycampaignadv, container, false);
        final TextView textView = root.findViewById(R.id.text_my_campaign_adv);
        myCampaignAdvViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
