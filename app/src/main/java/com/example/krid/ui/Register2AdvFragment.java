package com.example.krid.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krid.R;
import com.example.krid.adapter.FieldSpinnerAdapter;
import com.example.krid.database.AdvertiserDao;
import com.example.krid.model.Advertiser;
import com.example.krid.model.Field;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Register2AdvFragment extends Fragment {
    private TextView inputName;
    private TextView inputCompanyName;
    private TextView inputWebsite;
    private TextView inputEmail;
    private TextView inputPhone;
    private CheckBox checkIndividual;
    private CheckBox checkWebsite;
    private Spinner spnField;
    private Button btnComplete;

    private Advertiser adv;
    private static FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register_2_adv, container, false);

        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Register (2)");

        inputName = root.findViewById(R.id.inputName);
        inputCompanyName = root.findViewById(R.id.inputCompanyName);
        inputWebsite = root.findViewById(R.id.inputWebsite);
        inputEmail = root.findViewById(R.id.inputEmail);
        inputPhone = root.findViewById(R.id.inputPhone);
        checkIndividual = root.findViewById(R.id.checkIndividual);
        checkWebsite = root.findViewById(R.id.checkWebsite);
        spnField = root.findViewById(R.id.spnField);
        btnComplete = root.findViewById(R.id.btnComplete);

        adv = (Advertiser)getArguments().getSerializable("param");
        db = FirebaseFirestore.getInstance();

        checkIndividual.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                       if(isChecked) {
                           inputCompanyName.setText(" ");
                           inputCompanyName.setEnabled(false);
                       } else {
                           inputCompanyName.setHint("Company name");
                           inputCompanyName.setText("");
                           inputCompanyName.setEnabled(true);
                       }
                   }
                }
        );

        checkWebsite.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            inputWebsite.setText(" ");
                            inputWebsite.setEnabled(false);
                        } else {
                            inputWebsite.setHint("Website");
                            inputWebsite.setText("");
                            inputWebsite.setEnabled(true);
                        }
                    }
                }
        );

        final List<Field> list = new ArrayList<>();

        db.collection("Field").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.toObject(Field.class));
                    }
                }
                FieldSpinnerAdapter adapter = new FieldSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, list);
                spnField.setAdapter(adapter);
                spnField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        adv.setFieldId(((Field) spnField.getSelectedItem()).getId());
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
                adv.setName(inputName.getText().toString());
                adv.setCompanyName(inputCompanyName.getText().toString());
                adv.setWebsite(inputWebsite.getText().toString());
                adv.setPhone(inputPhone.getText().toString());
                adv.setEmail(inputEmail.getText().toString());
                AdvertiserDao.addNewAdvertiser(adv);

                Toast.makeText(getContext(), "Register success.", Toast.LENGTH_LONG).show();

                ((MainActivity) requireActivity()).navigateToFragmentWithoutArgs(new HomeFragment());
            }
        });

        return root;
    }
}
