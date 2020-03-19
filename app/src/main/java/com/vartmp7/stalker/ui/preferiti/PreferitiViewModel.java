package com.vartmp7.stalker.ui.preferiti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.vartmp7.stalker.gsonbeans.Organizzazione;
import com.vartmp7.stalker.model.FavoritesRepository;

import java.util.List;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class PreferitiViewModel extends ViewModel {
    public static final String TAG ="com.vartmp7.stalker.ui.preferiti.PreferitiViewModel";
    private FavoritesRepository favRepo;
    private LiveData<List<Organizzazione>> liveDataOrganizzazioni;


    public PreferitiViewModel(FavoritesRepository favRepo) {
        this.favRepo=favRepo;

        //mText = new MutableLiveData<>();
        //mText.setValue("This is notifications fragment");

    }

    public void init(){
        if(liveDataOrganizzazioni!=null){
            return;
        }
        //this.favRepo = new FirebaseFavoritesRepository();
        this.favRepo.updateOrganizzazioni();
        this.liveDataOrganizzazioni = favRepo.getOrganizzazioni();
    }

    public LiveData<List<Organizzazione>> getOrganizzazioni() {
        return this.liveDataOrganizzazioni;
    }
}