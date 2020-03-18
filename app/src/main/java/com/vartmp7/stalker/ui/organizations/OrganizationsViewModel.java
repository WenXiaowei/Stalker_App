package com.vartmp7.stalker.ui.organizations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vartmp7.stalker.gsonbeans.Organizzazione;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class OrganizationsViewModel extends ViewModel {
    public static final String TAG ="com.vartmp7.stalker.ui.organizations.OrganizationsViewModel";

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Organizzazione>> listOrganizzazione;

    public OrganizationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}