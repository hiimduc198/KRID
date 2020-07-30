package com.example.krid.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krid.R;
import com.example.krid.database.AdvertiserDao;
import com.example.krid.database.FieldDao;
import com.example.krid.model.Advertiser;
import com.example.krid.model.Field;

public class Register2Fragment extends Fragment {
    private Spinner spnField;
    private TextView inputName;
    private TextView inputCompanyName;
    private TextView inputWebsite;
    private TextView inputEmail;
    private TextView inputPhone;
    private CheckBox checkIndividual;
    private CheckBox checkWebsite;
    private Button btnComplete;

    private Advertiser adv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register_2, container, false);

        inputName = root.findViewById(R.id.inputName);
        inputCompanyName = root.findViewById(R.id.inputCompanyName);
        inputWebsite = root.findViewById(R.id.inputWebsite);
        inputEmail = root.findViewById(R.id.inputEmail);
        inputPhone = root.findViewById(R.id.inputPhone);
        checkIndividual = root.findViewById(R.id.checkIndividual);
        checkWebsite = root.findViewById(R.id.checkWebsite);
        spnField = root.findViewById(R.id.spinField);
        btnComplete = root.findViewById(R.id.btnComplete);

        adv = (Advertiser)getArguments().getSerializable("adv");

        checkIndividual.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                       if(isChecked) {
                           inputCompanyName.setText(" ");
                           inputCompanyName.setEnabled(false);
                       } else {
                           inputCompanyName.setHint("Company name");
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
                            inputWebsite.setEnabled(true);
                        }
                    }
                }
        );

        ArrayAdapter < Field > adapter = new ArrayAdapter<Field>(getContext(), android.R.layout.simple_spinner_item, FieldDao.getAll());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnField.setAdapter(adapter);
        spnField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                adv.setFieldId(((Field) spnField.getSelectedItem()).getId());
                Toast.makeText(getContext(), "Field: " + ((Field) spnField.getSelectedItem()).getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
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

                Toast.makeText(getContext(), "Register success.", Toast.LENGTH_LONG).show();

                ((com.example.krid.MainActivity) requireActivity()).navigateToFragmentWithoutArgs(new HomeFragment());
            }
        });

        return root;
    }
}
