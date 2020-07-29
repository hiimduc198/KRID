package com.example.krid.ui.campaign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.appcompat.widget.Toolbar;
import com.example.krid.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class CampaignFragment extends Fragment {
    private CampaignViewModel campaignViewModel;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.booking03, R.drawable.home07, R.drawable.home14};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        campaignViewModel =
                ViewModelProviders.of(this).get(CampaignViewModel.class);
        View root = inflater.inflate(R.layout.fragment_campaign, container, false);
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        carouselView = getView().findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        return root;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}
