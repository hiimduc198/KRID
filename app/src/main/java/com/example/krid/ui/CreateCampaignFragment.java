package com.example.krid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.krid.R;
import com.example.krid.database.AdvertiserDao;
import com.example.krid.database.CampaignDao;
import com.example.krid.model.Campaign;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateCampaignFragment extends Fragment {
    private EditText title;
    private EditText infNum;
    private EditText datePost;
    private EditText aplTime;
    private EditText rvTime;
    private EditText aprvTime;
    private Button btnNext;
    private  Campaign campaign ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_campaign, container, false);
        btnNext = root.findViewById(R.id.btn_createcampaign_next);
        title = root.findViewById(R.id.txt_title);
        infNum = root.findViewById(R.id.txt_number_inf);
        datePost = root.findViewById(R.id.txt_date_post);

        aplTime = root.findViewById(R.id.txt_apply_time);
        rvTime = root.findViewById(R.id.txt_review_time);
        aprvTime = root.findViewById(R.id.txt_approval_time);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CampaignDao.collection.whereEqualTo("","5JORX9EDu8UjUnW08V2w")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(!task.getResult().isEmpty()) {
                                    Toast.makeText(requireActivity(),"This username is already taken. Please enter another username", Toast.LENGTH_LONG).show();
                                } else {
                                    campaign = new Campaign();
                                    campaign.setTitle(title.getText().toString().trim());
                                    campaign.setNumOfInf(infNum.getText().toString());
                                    campaign.setPostTime(parseDate(datePost.getText().toString()));
                                    campaign.setApplyTime(parseDate(aplTime.getText().toString()));
                                    campaign.setReviewTime(parseDate(rvTime.getText().toString()));
                                    ((AdvertiserActivity) requireActivity()).navigateToFragmentWithArgs(new CreateCampaignFragment2(), campaign);
                                }
                            }
                        });


            }
        });

        return root;
    }
    private static Date parseDate(String date){
        Date d = new Date();
        try {
        d =  new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            Toast.makeText(null, "Not correct date format", Toast.LENGTH_LONG).show();
        }
        return d;
    }


}
