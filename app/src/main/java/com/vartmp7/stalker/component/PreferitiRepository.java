package com.vartmp7.stalker.component;

import androidx.lifecycle.LiveData;

import com.vartmp7.stalker.component.gsonbeans.Organizzazione;

import java.util.List;

public interface PreferitiRepository {
    void addOrganizzazione(Organizzazione organizzazione);
    void removeOrganizzazione(Organizzazione organizzazione);
    LiveData<List<Organizzazione>> getOrganizzazioni();

}
