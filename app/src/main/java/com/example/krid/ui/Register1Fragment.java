package com.example.krid.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krid.R;
import com.example.krid.model.Advertiser;
import com.example.krid.util.Constants;

public class Register1Fragment extends Fragment {
    private Advertiser adv;
    private TextView inputUserName;
    private TextView inputPassword;
    private Button btnRegister;
    private Button btnLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register_1, container, false);

        adv = new Advertiser();
        inputUserName = root.findViewById(R.id.inputUserName);
        inputPassword = root.findViewById(R.id.inputPassword);
        btnRegister = root.findViewById(R.id.btnRegister);
        btnLogin = root.findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegister2Activity();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((com.example.krid.MainActivity) requireActivity()).navigateToLoginFragment();
            }
        });

        return root;
    }

    private void callRegister2Activity(){
        String userName = inputUserName.getText().toString();
        String password = inputPassword.getText().toString();

        if(userName == null || userName.equals("")) {
            Toast.makeText(requireActivity(),"Please input user name.", Toast.LENGTH_LONG).show();
        } else if(password == null || password.equals("")) {
            Toast.makeText(requireActivity(),"Please input password.", Toast.LENGTH_LONG).show();
        } else if(!userName.matches("^[a-z0-9_-]{4,20}$")) {
            Toast.makeText(requireActivity(),"User name contains: alphabet characters, number, '_' or '-', with size: 4 to 20.", Toast.LENGTH_LONG).show();
        } else {
            adv.setUsername(userName);
            adv.setPassword(password);
            ((com.example.krid.MainActivity) requireActivity()).navigateToRegister2Fragment(adv);
        }
    }
}