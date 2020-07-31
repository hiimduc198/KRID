package com.example.krid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.krid.R;
import com.example.krid.database.AdvertiserDao;
import com.example.krid.database.InfluencerDao;
import com.example.krid.model.Advertiser;
import com.example.krid.model.Influencer;
import com.example.krid.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginFragment extends Fragment {
    private EditText inputUsername;
    private EditText inputPassword;
    private Button btnLoginInf;
    private Button btnLoginAdv;
    private Button btnRegister;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Login");

        inputUsername = root.findViewById(R.id.inputUsername);
        inputPassword = root.findViewById(R.id.inputPassword);
        btnLoginInf = root.findViewById(R.id.btnLoginInf);
        btnLoginAdv = root.findViewById(R.id.btnLoginAdv);
        btnRegister = root.findViewById(R.id.btnRegister);

        btnLoginInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginInf();
            }
        });

        btnLoginAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAdv();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).navigateToFragmentWithoutArgs(new Register1Fragment());
            }
        });

        return root;
    }

    private void loginInf() {
        final String username = inputUsername.getText().toString();
        final String password = inputPassword.getText().toString();

        if(username == null || username.equals("")) {
            Toast.makeText(requireActivity(),"Please input user name.", Toast.LENGTH_LONG).show();
        } else if(password == null || password.equals("")) {
            Toast.makeText(requireActivity(),"Please input password.", Toast.LENGTH_LONG).show();
        } else {
            InfluencerDao.collection.whereEqualTo("username", username).whereEqualTo("password", password).get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.getResult().isEmpty()) {
                        Toast.makeText(requireActivity(),"Wrong username or password", Toast.LENGTH_LONG).show();
                    } else {
                        Influencer inf = task.getResult().toObjects(Influencer.class).get(0);

                        SharedPreferences pref = getContext().getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(Constants.PREF_KEY_SESSION_ID, inf.getId());
                        editor.putString(Constants.PREF_KEY_SESSION_USERNAME, inf.getUsername());
                        editor.commit();

                        Intent intent = new Intent(getContext(), InfluenceActivity.class);
                        startActivityForResult(intent, Constants.ACTIVITY_GUEST);
                    }
                }
            });
        }
    }

    private void loginAdv() {
        final String username = inputUsername.getText().toString();
        final String password = inputPassword.getText().toString();

        if(username == null || username.equals("")) {
            Toast.makeText(requireActivity(),"Please input user name.", Toast.LENGTH_LONG).show();
        } else if(password == null || password.equals("")) {
            Toast.makeText(requireActivity(),"Please input password.", Toast.LENGTH_LONG).show();
        } else {
            AdvertiserDao.collection.whereEqualTo("username", username).whereEqualTo("password", password).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.getResult().isEmpty()) {
                                Toast.makeText(requireActivity(),"Wrong username or password", Toast.LENGTH_LONG).show();
                            } else {
                                Advertiser adv = task.getResult().toObjects(Advertiser.class).get(0);

                                SharedPreferences pref = getContext().getSharedPreferences(Constants.PREF_NAME_ADVERTISER, Constants.PRIVATE_MODE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString(Constants.PREF_KEY_SESSION_ID, adv.getId());
                                editor.putString(Constants.PREF_KEY_SESSION_USERNAME, adv.getUsername());
                                editor.commit();

                                Intent intent = new Intent(getContext(), AdvertiserActivity.class);
                                startActivityForResult(intent, Constants.ACTIVITY_GUEST);
                            }
                        }
                    });
        }
    }
}