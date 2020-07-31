package com.example.krid.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.krid.R;
import com.example.krid.database.CampaignDao;
import com.example.krid.model.Campaign;


public class CreateCampaignFragment2 extends Fragment {
    private Button btnDone;
    private EditText etDescript;
    private EditText edReward;
    private EditText txt_cash;
    private EditText txt_hashtag;
    private EditText txt_work;
    private EditText ed_anotherinfo;
    private EditText txt_website;
    private EditText txt_age_range;
    private RadioButton checkBox_allsex;
    private RadioButton checkBox_female;
    private RadioButton checkBox_male;
    private CheckBox checkBox6;
    private CheckBox checkBox5;
    private CheckBox checkBox4;
    private CheckBox checkBox3;
    private CheckBox checkBox9;
    private CheckBox checkBox8;
    private Campaign campaign;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        campaign = (Campaign)getArguments().getSerializable("param");
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.create_campaign_02, container, false);
        btnDone = root.findViewById(R.id.btn_createcampaign_next);
        etDescript = root.findViewById(R.id.txt_description_creatcampaign);
        checkBox6  = root.findViewById(R.id.checkBox6);
        checkBox5  = root.findViewById(R.id.checkBox5);
        checkBox4  = root.findViewById(R.id.checkBox4);
        checkBox3  = root.findViewById(R.id.checkBox3);
        checkBox9  = root.findViewById(R.id.checkBox9);
        checkBox8  = root.findViewById(R.id.checkBox8);
        edReward  = root.findViewById(R.id.edReward);
        txt_cash  = root.findViewById(R.id.txt_cash);
        txt_hashtag  = root.findViewById(R.id.txt_hashtag);
        txt_work  = root.findViewById(R.id.txt_work);
        ed_anotherinfo  = root.findViewById(R.id.ed_anotherinfo);
        txt_website  = root.findViewById(R.id.txt_website);
        txt_age_range  = root.findViewById(R.id.txt_age_range);
        checkBox_allsex  = root.findViewById(R.id.checkBox_allsex);
        checkBox_female  = root.findViewById(R.id.checkBox_female);
        checkBox_male  = root.findViewById(R.id.checkBox_male);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campaign.setDescription(etDescript.getText().toString().trim());

                CampaignDao.addNewCampaign(campaign);
                Toast.makeText(getContext(), "Register success.", Toast.LENGTH_LONG).show();

                ((AdvertiserActivity) requireActivity()).navigateToFragmentWithoutArgs(new HomeFragment());
            }
        });
                return root;
    }
}