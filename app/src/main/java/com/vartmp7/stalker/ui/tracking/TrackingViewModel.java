package com.vartmp7.stalker.ui.tracking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vartmp7.stalker.gsonbeans.Organizzazione;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class TrackingViewModel extends ViewModel {
    public static final String TAG ="com.vartmp7.stalker.ui.home.HomeViewModel";

    private MutableLiveData<List<Organizzazione>> listOrganizzazione;

    public void initi(List<Organizzazione> list) {
        listOrganizzazione = new MutableLiveData<>(list);
    }

    public TrackingViewModel() {
    }


    public void addTrackingOrganizzazione(Organizzazione org){
        List<Organizzazione> l = listOrganizzazione.getValue();
        l.add(org);
        listOrganizzazione.setValue(l);
    }

    public LiveData<List<Organizzazione>> getListaOrganizzazione(){
        return listOrganizzazione;
    }
}