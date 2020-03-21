package com.vartmp7.stalker.gsonbeans;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class ResponseOrganizzazione {
    public static final String TAG ="com.vartmp7.stalker.gsonbeans.ResponseOrganizzazione";

    private List<Organizzazione> organizations;

    public List<Organizzazione> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organizzazione> organizations) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseOrganizzazione)) return false;
        ResponseOrganizzazione that = (ResponseOrganizzazione) o;
        return getOrganizations().equals(that.getOrganizations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrganizations());
    }
}
