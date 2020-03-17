package com.vartmp7.stalker.component;

import com.vartmp7.stalker.component.gsonbeans.Organizzazione;

import java.util.List;

public interface OrganizationsRepository {
    List<Organizzazione> getOrganizzazioni();


    /*
    metodi più specifici, che potrebbero occupare meno banda e alleggerire il carico lato server

    param: lista degli id delle organizzazioni
    List<Organizzazione> getOrganizzazioni(List<String> organizationIds);

    param: id di un'organizzazione
    Organizzazione getOrganizzazione(List<String> organizationId);
    */
}
