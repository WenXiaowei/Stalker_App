package com.vartmp7.stalker.GsonBeans;

import java.util.ArrayList;

public class ResponseOrganizzazione {

    private ArrayList<Organizzazione> organizations;

    public ArrayList<Organizzazione> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(ArrayList<Organizzazione> organizations) {
        this.organizations = organizations;
    }


    public String[] getDataForSpinner(){
        ArrayList<String> toRet = new ArrayList<>();
        toRet.add("Scegli un'organizzazione");

        for (Organizzazione org: organizations) {
            toRet.add(org.getName());
        }

        return toRet.toArray(new String[0]);
    }
}
