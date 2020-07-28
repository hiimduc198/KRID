package com.example.krid.ui.account_detail;

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

public class AccountDetailFragment extends Fragment {
    private AccountDetailViewModel accountDetailViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountDetailViewModel =
                ViewModelProviders.of(this).get(AccountDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account_detail, container, false);
        final TextView textView = root.findViewById(R.id.text_account_detail);
        accountDetailViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
