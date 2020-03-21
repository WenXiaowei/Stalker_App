package com.vartmp7.stalker.ui.organizations;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.model.OrganizationsRepository;

import java.util.List;

import javax.annotation.Nullable;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class OrganizationsViewModel extends ViewModel {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationsViewModel";

    private OrganizationsRepository orgRepo;
    private LiveData<List<Organizzazione>> organizationList;


    public void initData(OrganizationsRepository orgRepo) {
        if (organizationList != null) {
            return;
        }
        this.orgRepo = orgRepo;
        //TODO togliere questo casting
        this.organizationList = orgRepo.getOrganizzazioni();

    }

   /* public void updateData() {
        orgRepo.updateOrganizzazioni();
    }*/

    public @Nullable
    LiveData<List<Organizzazione>> getOrganizationList() {
        return organizationList;
    }

    public void aggiungiOrganizzazione(final Organizzazione org) {
        /*List<Organizzazione> l =  organizationList.getValue();
        if (l==null)
            Log.d(TAG, "aggiungiOrganizzazione: Lista = null");
        else
            Log.d(TAG, "aggiungiOrganizzazione: Lista != null");

        l.add(org);*/
        //orgRepo.saveOrganizzazioni(l);
        orgRepo.saveOrganizzazione(org);

    }

    public void refresh() {
        orgRepo.refreshOrganizzazioni();
    }
}