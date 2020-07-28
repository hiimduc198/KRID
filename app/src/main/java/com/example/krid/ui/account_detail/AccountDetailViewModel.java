package com.example.krid.ui.account_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountDetailViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AccountDetailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is booking fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
