package com.vartmp7.stalker.gsonbeans;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class ResponseOrganizzazione {
    public static final String TAG ="com.vartmp7.stalker.gsonbeans.ResponseOrganizzazione";

    private ArrayList<Organizzazione> organizations;

    public ArrayList<Organizzazione> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(ArrayList<Organizzazione> organizations) {
        this.organizations = organizations;
    }

    @Deprecated
    public String[] getDataForSpinner() {
        ArrayList<String> toRet = new ArrayList<>();
        toRet.add("Scegli un'organizzazione");

        for (Organizzazione org : organizations) {
            toRet.add(org.getName());
        }

        return toRet.toArray(new String[0]);
    }

    public int getOrganizzationsLength() {
        return organizations.size();
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder= new StringBuilder();
        for (Organizzazione org: organizations)
            builder.append(org.getName());
        return builder.toString();
    }
}
