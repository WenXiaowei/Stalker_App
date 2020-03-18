package com.vartmp7.stalker.ui.cronologia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class CronologiaViewModel extends ViewModel {
    public static final String TAG ="com.vartmp7.stalker.ui.cronologia.CronologiaViewModel";

    private MutableLiveData<String> mText;

    public CronologiaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cronologia fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}