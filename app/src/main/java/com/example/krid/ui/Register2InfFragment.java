package com.example.krid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.krid.R;
import com.example.krid.adapter.JobSpinnerAdapter;
import com.example.krid.database.InfluencerDao;
import com.example.krid.model.Influencer;
import com.example.krid.model.Job;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Register2InfFragment extends Fragment {
    private TextView inputName;
    private TextView inputPhone;
    private TextView inputEmail;
    private TextView inputFacebook;
    private Spinner spnJob;
    private Button btnComplete;

    private Influencer inf;
    private static FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register_2_inf, container, false);

        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Register influence (2)");

        inputName = root.findViewById(R.id.inputName);
        inputPhone = root.findViewById(R.id.inputPhone);
        inputEmail = root.findViewById(R.id.inputEmail);
        inputFacebook = root.findViewById(R.id.inputFacebook);
        spnJob = root.findViewById(R.id.spnJob);
        btnComplete = root.findViewById(R.id.btnComplete);

        inf = (Influencer)getArguments().getSerializable("param");
        db = FirebaseFirestore.getInstance();

        final List<Job> list = new ArrayList<>();

        db.collection("Job").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.toObject(Job.class));
                    }
                }
                ArrayAdapter adapter = new JobSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, list);
                spnJob.setAdapter(adapter);
                spnJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        inf.setJobId(((Job) spnJob.getSelectedItem()).getId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });
            }
        });

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inf.setName(inputName.getText().toString());
                inf.setFacebook(inputFacebook.getText().toString());
                inf.setPhone(inputPhone.getText().toString());
                inf.setEmail(inputEmail.getText().toString());
                InfluencerDao.addNewInfluencer(inf);

                Toast.makeText(getContext(), "Register success.", Toast.LENGTH_LONG).show();

                ((MainActivity) requireActivity()).navigateToFragmentWithoutArgs(new HomeFragment());
            }
        });

        return root;
    }
}
