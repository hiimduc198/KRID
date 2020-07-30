package com.example.krid.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krid.MainActivity;
import com.example.krid.R;
import com.example.krid.database.AdvertiserDao;
import com.example.krid.model.Advertiser;
import com.example.krid.model.Influencer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public class Register1Fragment extends Fragment {
    private Advertiser adv;
    private Influencer inf;
    private TextView inputUserName;
    private TextView inputPassword;
    private Button btnRegisterAdv;
    private Button btnRegisterInf;
    private Button btnLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register_1, container, false);

        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Register (1)");

        inputUserName = root.findViewById(R.id.inputUserName);
        inputPassword = root.findViewById(R.id.inputPassword);
        btnRegisterAdv = root.findViewById(R.id.btnRegisterAdv);
        btnRegisterInf = root.findViewById(R.id.btnRegisterInf);
        btnLogin = root.findViewById(R.id.btnLogin);

        btnRegisterAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegister2AdvActivity();
            }
        });

        btnRegisterInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegister2InfActivity();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((com.example.krid.MainActivity) requireActivity()).navigateToFragmentWithoutArgs(new LoginFragment());
            }
        });

        return root;
    }

    private void callRegister2AdvActivity(){
        adv = new Advertiser();
        final String username = inputUserName.getText().toString();
        final String password = inputPassword.getText().toString();

        if(username == null || username.equals("")) {
            Toast.makeText(requireActivity(),"Please input user name.", Toast.LENGTH_LONG).show();
        } else if(password == null || password.equals("")) {
            Toast.makeText(requireActivity(),"Please input password.", Toast.LENGTH_LONG).show();
        } else if(!username.matches("^[a-z0-9_-]{4,20}$")) {
            Toast.makeText(requireActivity(),"User name contains: alphabet characters, number, '_' or '-', with size: 4 to 20.", Toast.LENGTH_LONG).show();
        } else {
            AdvertiserDao.collection.whereEqualTo("username", username).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(!task.getResult().isEmpty()) {
                            Toast.makeText(requireActivity(),"This username is already taken. Please enter another username", Toast.LENGTH_LONG).show();
                        } else {
                            adv.setUsername(username);
                            adv.setPassword(password);
                            ((com.example.krid.MainActivity) requireActivity()).navigateToFragmentWithArgs(new Register2AdvFragment(), adv);
                        }
                    }
                });
        }
    }

    private void callRegister2InfActivity(){
        inf = new Influencer();
        final String username = inputUserName.getText().toString();
        final String password = inputPassword.getText().toString();

        if(username == null || username.equals("")) {
            Toast.makeText(requireActivity(),"Please input user name.", Toast.LENGTH_LONG).show();
        } else if(password == null || password.equals("")) {
            Toast.makeText(requireActivity(),"Please input password.", Toast.LENGTH_LONG).show();
        } else if(!username.matches("^[a-z0-9_-]{4,20}$")) {
            Toast.makeText(requireActivity(),"User name contains: alphabet characters, number, '_' or '-', with size: 4 to 20.", Toast.LENGTH_LONG).show();
        } else {
            AdvertiserDao.collection.whereEqualTo("username", username).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(!task.getResult().isEmpty()) {
                                Toast.makeText(requireActivity(),"This username is already taken. Please enter another username", Toast.LENGTH_LONG).show();
                            } else {
                                inf.setUsername(username);
                                inf.setPassword(password);
                                ((com.example.krid.MainActivity) requireActivity()).navigateToFragmentWithArgs(new Register2InfFragment(), inf);
                            }
                        }
                    });
        }
    }
}


