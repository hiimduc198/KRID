package com.example.krid.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.krid.R;
import com.example.krid.model.Advertiser;

public class Register2Fragment extends Fragment {
    private Spinner spnField;
    private TextView inputName;
    private TextView inputCompanyName;
    private TextView inputWebsite;
    private TextView inputEmail;
    private TextView inputPhone;
    private CheckBox checkIndividual;
    private CheckBox checkWebsite;

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

        adv = (Advertiser)getArguments().getSerializable("adv");

        inputName.setText(adv.getUsername());
        inputCompanyName.setText(adv.getPassword());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.field_register, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnField.setAdapter(adapter);

        getActivity().getActionBar().hide();

        return root;
    }
}
