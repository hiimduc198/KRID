package com.example.krid.ui.mycampaign;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCampaignViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MyCampaignViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my campaign fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
