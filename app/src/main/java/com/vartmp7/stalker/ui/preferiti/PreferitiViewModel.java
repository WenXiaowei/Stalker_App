package com.vartmp7.stalker.ui.preferiti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class PreferitiViewModel extends ViewModel {
    public static final String TAG ="com.vartmp7.stalker.ui.preferiti.PreferitiViewModel";

    private MutableLiveData<String> mText;

    public PreferitiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}