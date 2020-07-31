package com.example.krid.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.krid.R;
import com.example.krid.adapter.CitySpinnerAdapter;
import com.example.krid.adapter.JobSpinnerAdapter;
import com.example.krid.database.InfluencerDao;
import com.example.krid.model.City;
import com.example.krid.model.Influencer;
import com.example.krid.model.Job;
import com.example.krid.util.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BookingDetailFragment extends Fragment {
    private EditText txtUserName;
    private EditText txtName;
    private EditText txtAddress;
    private Spinner spnLocation;
    private EditText txtWebsite;
    private EditText txtEmail;
    private EditText txtPhone;
    private EditText txtFacebook;
    private EditText txtFacebookFollower;
    private EditText txtInteraction;
    private RadioGroup rdgGender;
    private Spinner spnJob;
    private Button btnSave;
    private Button btnCancel;

    private Influencer inf;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_account_detail, container, false);

        txtUserName = root.findViewById(R.id.txtUserName);
        txtName = root.findViewById(R.id.txtName);
        txtAddress = root.findViewById(R.id.txtAddress);
        spnLocation = root.findViewById(R.id.spnLocation);
        txtWebsite = root.findViewById(R.id.txtWebsite);
        txtEmail = root.findViewById(R.id.txtEmail);
        txtPhone = root.findViewById(R.id.txtPhone);
        txtFacebook = root.findViewById(R.id.txtFacebook);
        txtFacebookFollower = root.findViewById(R.id.txtFacebookFollower);
        txtInteraction = root.findViewById(R.id.txtInteraction);
        rdgGender = root.findViewById(R.id.rdgGender);
        spnJob = root.findViewById(R.id.spnJob);
        btnSave = root.findViewById(R.id.btnSave);
        btnCancel = root.findViewById(R.id.btnCancel);

        SharedPreferences pref1 = getContext().getSharedPreferences(Constants.PREF_NAME_INFLUENCE, Constants.PRIVATE_MODE);
        String sessionInfId = pref1.getString(Constants.PREF_KEY_SESSION_ID, "");

        FirebaseFirestore.getInstance().collection("Influencer").document(sessionInfId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                inf = task.getResult().toObject(Influencer.class);

                txtUserName.setText((inf.getUsername()!=null)?inf.getUsername():"");
                txtName.setText(inf.getName());
                txtAddress.setText(inf.getAddress());
                txtEmail.setText(inf.getEmail());
                txtPhone.setText(inf.getPhone());
                txtFacebook.setText(inf.getFacebook());
                txtFacebookFollower.setText(inf.getFacebookFollowers()+"");
                txtInteraction.setText(inf.getNumOfInteractions()+"");

                final List<Job> list = new ArrayList<>();
                FirebaseFirestore.getInstance().collection("Job").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            list.add(document.toObject(Job.class));
                        }
                    }
                    ArrayAdapter adapter = new JobSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, list);
                    spnJob.setAdapter(adapter);

                    int index = 0;
                    for(index = 0; index < list.size(); index++) {
                        if(list.get(index).getId().equals(inf.getJobId())) {
                            break;
                        }
                    }
                    spnJob.setSelection(index);

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

                final List<City> listLo = new ArrayList<>();
                FirebaseFirestore.getInstance().collection("City").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listLo.add(document.toObject(City.class));
                            }
                        }
                        ArrayAdapter adapter = new CitySpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, listLo);
                        spnLocation.setAdapter(adapter);

                        int index = 0;
                        for(index = 0; index < list.size(); index++) {
                            if(listLo.get(index).getId().equals(inf.getCityId())) {
                                break;
                            }
                        }
                        spnLocation.setSelection(index);

                        spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                inf.setCityId(((City) spnLocation.getSelectedItem()).getId());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                            }
                        });
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InfluenceActivity) requireActivity()).navigateToFragmentWithoutArgs(new HomeFragment());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEdit(root);
            }
        });

        return root;
    }

    private void saveEdit(View root) {
        inf.setUsername(txtUserName.getText().toString());
        inf.setName(txtName.getText().toString());
        inf.setAddress(txtAddress.getText().toString());
        inf.setCityId(((City)spnLocation.getSelectedItem()).getId());
        inf.setEmail(txtEmail.getText().toString());
        inf.setPhone(txtPhone.getText().toString());
        inf.setFacebook(txtFacebook.getText().toString());
        inf.setFacebookFollowers(Integer.parseInt(txtFacebookFollower.getText().toString()));
        inf.setNumOfInteractions(Integer.parseInt(txtInteraction.getText().toString()));
        inf.setGender(((RadioButton)root.findViewById(rdgGender.getCheckedRadioButtonId())).getText().toString());
        inf.setJobId(((Job)spnJob.getSelectedItem()).getId());

        InfluencerDao.editInfluencer(inf);
        Toast.makeText(requireContext(), "Update account information success", Toast.LENGTH_LONG).show();

        ((InfluenceActivity) requireActivity()).navigateToFragmentWithoutArgs(new HomeFragment());
    }
}
