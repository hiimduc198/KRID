package com.example.krid.ui.createcampaign;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateCampaignViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CreateCampaignViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Create Your Campaign");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
