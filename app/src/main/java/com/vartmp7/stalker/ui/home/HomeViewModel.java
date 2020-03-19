package com.vartmp7.stalker.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.model.FavoritesRepository;
import com.vartmp7.stalker.model.FirebaseFavoritesRepository;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class HomeViewModel extends ViewModel {
    public static final String TAG ="com.vartmp7.stalker.ui.home.HomeViewModel";

    private MutableLiveData<List<Organizzazione>> listOrganizzazione;

    public void initi(List<Organizzazione> list) {
        listOrganizzazione= new MutableLiveData<>(list);
    }
    public HomeViewModel(FavoritesRepository favRepo) {

        //mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }


    public void addTrackingOrganizzazione(Organizzazione org){
        listOrganizzazione.getValue().add(org);
    }

    public LiveData<List<Organizzazione>> getListaOrganizzazione(){
        return listOrganizzazione;
    }
}