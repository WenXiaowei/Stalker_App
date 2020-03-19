package com.vartmp7.stalker.ui.organizations;

import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.gsonbeans.ResponseOrganizzazione;
import com.vartmp7.stalker.model.RESTOrganizationsRepository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class OrganizationsViewModel extends ViewModel {
    public static final String TAG ="com.vartmp7.stalker.ui.organizations.OrganizationsViewModel";

    private RESTOrganizationsRepository restOrganizationsRepository;
    private MutableLiveData<List<Organizzazione>> organizationList;



    public void initData(RESTOrganizationsRepository orgRepo){
        this.restOrganizationsRepository=orgRepo;
        organizationList = restOrganizationsRepository.getOrganizzazioni();
    }

    public void updateData(){
        restOrganizationsRepository.updateOrganizzazioni();
    }

    public @Nullable MutableLiveData<List<Organizzazione>> getOrganizationList() {
        return organizationList;
    }

    public void aggiungiOrganizzazione(Organizzazione org){
        List<Organizzazione> l = organizationList.getValue();
        l.add(org);
        organizationList.postValue(l);
    }

}