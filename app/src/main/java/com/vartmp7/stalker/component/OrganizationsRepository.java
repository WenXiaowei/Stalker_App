package com.vartmp7.stalker.component;

import androidx.lifecycle.LiveData;

import com.vartmp7.stalker.component.gsonbeans.Organizzazione;

import java.util.List;

import io.reactivex.Observable;

public interface OrganizationsRepository {

    LiveData<List<Organizzazione>> getOrganizzazioni();

    /*
    metodi più specifici, se in futuro si rendessero disponibili delle API più specifiche.
    Potrebbero avere senso per occupare meno banda e alleggerire il carico lato server,

    param: lista degli id delle organizzazioni
    List<Organizzazione> getOrganizzazioni(List<String> organizationIds);

    param: id di un'organizzazione
    Organizzazione getOrganizzazione(List<String> organizationId);
    */

}
