package com.example.krid.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.krid.R;
import com.example.krid.model.Campaign;


public class CreateCampaignFragment2 extends Fragment {
    private Button btnNext;
    private EditText etDescript;
    private CheckBox cbSendP;
    private CheckBox cbSendL;
    private Campaign campaign;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        campaign = (Campaign)getArguments().getSerializable("param");
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.create_campaign_02, container, false);
//        btnNext = root.findViewById(R.id.btn_next_creatcampaign02);
        etDescript = root.findViewById(R.id.txt_description_creatcampaign);
//        cbSendP = root.findViewById(R.id.cbx_sendgoods_createcampaign);
//        cbSendL = root.findViewById(R.id.cbx_checkin_createcampaign);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                campaign = new Campaign();
                campaign.setDescription(etDescript.getText().toString().trim());

                ((AdvertiserActivity) requireActivity()).navigateToFragmentWithArgs(new CreateCampaignFragment2(), campaign);
            }
        });
                return root;
    }
}