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
                callRegister2Activity(v);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLoginFragment(v);
            }
        });

        return root;
    }

    private void callRegister2Activity(View view){
        String userName = inputUserName.getText().toString();
        String password = inputPassword.getText().toString();

        if(userName == null || userName.equals("")) {
            Toast.makeText(getActivity(),"Please input user name.", Toast.LENGTH_SHORT);
        } else if(password == null || password.equals("")) {
            Toast.makeText(getActivity(),"Please input password.", Toast.LENGTH_SHORT);
        } else if(!userName.matches("^[a-z0-9_-]{4,20}$")) {
            Toast.makeText(getActivity(),"User name contains: alphabet characters, number, '_' and '-', with size: 4 to 20.", Toast.LENGTH_SHORT);
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("adv", adv);

            Fragment register2 = new Register2Fragment();
            register2.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.container, register2);
            ft.addToBackStack("");
            ft.commit();
        }
    }

    private void callLoginFragment(View view) {
        Fragment login  = new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, login);
        ft.addToBackStack("");
        ft.commit();
    }
}
