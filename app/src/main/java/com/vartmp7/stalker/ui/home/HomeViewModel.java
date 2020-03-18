package com.vartmp7.stalker.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class HomeViewModel extends ViewModel {
    public static final String TAG ="com.vartmp7.stalker.ui.home.HomeViewModel";

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}