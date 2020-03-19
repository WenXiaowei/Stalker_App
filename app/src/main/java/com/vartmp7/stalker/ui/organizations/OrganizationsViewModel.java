package com.vartmp7.stalker.ui.organizations;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.gsonbeans.ResponseOrganizzazione;
import com.vartmp7.stalker.model.OrganizationsRepository;
import com.vartmp7.stalker.model.RESTOrganizationsRepository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class OrganizationsViewModel extends ViewModel {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationsViewModel";

    private OrganizationsRepository restOrganizationsRepository;
    private MutableLiveData<List<Organizzazione>> organizationList;


    public void initData(RESTOrganizationsRepository orgRepo) {
        if (organizationList != null) {
            return;
        }
        this.restOrganizationsRepository = orgRepo;
        organizationList = restOrganizationsRepository.getOrganizzazioni();

    }

    public void updateData() {
        restOrganizationsRepository.updateOrganizzazioni();
    }

    public @Nullable
    MutableLiveData<List<Organizzazione>> getOrganizationList() {
        return organizationList;
    }

    public void aggiungiOrganizzazione(final Organizzazione org) {
        List<Organizzazione> l = organizationList.getValue();
        if (l==null)
            l= new ArrayList<>();
        l.add(org);
        organizationList.setValue(l);
    }

}