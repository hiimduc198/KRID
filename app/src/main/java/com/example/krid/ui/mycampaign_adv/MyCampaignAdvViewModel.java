package com.example.krid.ui.mycampaign_adv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCampaignAdvViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MyCampaignAdvViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my campaign advertise fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
