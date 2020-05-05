package com.example.arada.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        Date mDate = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy.MM.dd");
        String getTime = simpleDate.format(mDate);
        mText = new MutableLiveData<>();
        mText.setValue(getTime);
    }

    public LiveData<String> getText() {
        return mText;
    }
}