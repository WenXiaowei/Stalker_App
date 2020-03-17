package com.vartmp7.stalker.component;

import com.vartmp7.stalker.component.gsonbeans.Organizzazione;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RESTOrganizationsRepository implements OrganizationsRepository {
    private String serverUrl;

    private OkHttpClient httpClient;


    public RESTOrganizationsRepository(OkHttpClient httpClient, String serverUrl) {
        this.httpClient=httpClient;
    }

    @Override
    public List<Organizzazione> getOrganizzazioni() {
        //TODO
        //Request organizzazioniRequest = new Request.Builder().
        return null;
    }
}
