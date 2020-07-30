package com.example.krid.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bumptech.glide.Glide;
import com.example.krid.R;
import com.example.krid.adapter.ViewPager;
import com.example.krid.model.Advertiser;
import com.example.krid.model.Campaign;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class CampaignDetailGuestFragment extends Fragment {
    private ViewPager viewPager;
    private ImageView imgCampaign;
    private TextView txtTitle;
    private TextView txtLocation;
    private TextView txtCompanyName;
    private Button btnJoin;
    private Button btnApplyTime;
    private Button btnApprovalTime;
    private Button btnPostTime;
    private Button btnReviewTime;
    private TextView txtDescription;
    private TextView txtReward;
    private TextView txtHastag;
    private TextView txtCash;
    private TextView txtAge;
    private TextView txtGender;
    private TextView txtNumOfInf;
    private TextView txtTodo;
    private TextView txtAnother;
    private TextView txtWebsite;

    private Campaign cam;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_campaign_detail, container, false);

        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Campaign detail");

        imgCampaign = root.findViewById(R.id.imgCampaign);
        txtTitle = root.findViewById(R.id.txtTitle);
        txtLocation = root.findViewById(R.id.txtLocation);
        txtCompanyName = root.findViewById(R.id.txtCompanyName);
        btnJoin = root.findViewById(R.id.btnJoin);
        btnApplyTime = root.findViewById(R.id.btnApplyTime);
        btnApprovalTime = root.findViewById(R.id.btnApprovalTime);
        btnPostTime = root.findViewById(R.id.btnPostTime);
        btnReviewTime = root.findViewById(R.id.btnReviewTime);
        txtDescription = root.findViewById(R.id.txtDescription);
        txtReward = root.findViewById(R.id.txtReward);
        txtHastag = root.findViewById(R.id.txtHastag);
        txtCash = root.findViewById(R.id.txtCash);
        txtAge = root.findViewById(R.id.txtAge);
        txtGender = root.findViewById(R.id.txtGender);
        txtNumOfInf = root.findViewById(R.id.txtNumOfInf);
        txtTodo = root.findViewById(R.id.txtTodo);
        txtAnother = root.findViewById(R.id.txtAnother);
        txtWebsite = root.findViewById(R.id.txtWebsite);

        cam = (Campaign) getArguments().getSerializable("param");
        Glide.with(getContext())
                .load(cam.getImage())
                .fitCenter()
                .into(imgCampaign);

        FirebaseFirestore.getInstance().collection("Advertiser").document(cam.getAdvId()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Advertiser adv = task.getResult().toObject(Advertiser.class);
                        txtCompanyName.setText(adv.getCompanyName());
                    }
                });

        txtTitle.setText(cam.getTitle());
        txtLocation.setText(cam.getLocation());
        btnApplyTime.setText("Apply time: " + new SimpleDateFormat("dd/MM/yyyy hh:mm").format(cam.getApplyTime()));
        btnApprovalTime.setText("Approval time: " + new SimpleDateFormat("dd/MM/yyyy hh:mm").format(cam.getApprovalTime()));
        btnPostTime.setText("Potting time: " + new SimpleDateFormat("dd/MM/yyyy hh:mm").format(cam.getPostTime()));
        btnReviewTime.setText("Review time: " + new SimpleDateFormat("dd/MM/yyyy hh:mm").format(cam.getReviewTime()));
        txtDescription.setText(cam.getDescription());
        txtReward.setText(cam.getReward());
        txtHastag.setText(cam.getHastag());
        txtCash.setText(cam.getCash());
        txtAge.setText(cam.getInfAgeRange());
        txtGender.setText(cam.getInfGender());
        txtNumOfInf.setText(cam.getNumOfInf());
        txtTodo.setText(cam.getToDo());
        txtAnother.setText(cam.getOtherInfo());
        txtWebsite.setText(cam.getWebsite());

        if(!cam.getCamStatusId().equals("ULAjPlDVOTh7IZgAPW5N")) {
            btnJoin.setEnabled(false);
            btnJoin.setText("Can not join");
            btnJoin.setBackgroundColor(Color.GRAY);
        }

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).navigateToFragmentWithoutArgs(new LoginFragment());
            }
        });

        return root;
    }

    private void initViewPager(){
        viewPager = new ViewPager(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }
}
