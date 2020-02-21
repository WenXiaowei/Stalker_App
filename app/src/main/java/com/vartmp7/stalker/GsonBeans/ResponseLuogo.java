package com.vartmp7.stalker.GsonBeans;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ResponseLuogo {

    private ArrayList<Luogo> places;

    public ArrayList<Luogo> getPlaces() {
        return places;
    }

    public void setLuoghi(ArrayList<Luogo> luoghi) {
        this.places = luoghi;
    }


    public String getDataForSpinner(){
        ArrayList<String> toRet = new ArrayList<>();
        toRet.add("Scegli un'organizzazione1");
        toRet.add("Scegli un'organizzazione2");
        toRet.add("Scegli un'organizzazione3");

        for (Luogo l: getPlaces()) {
            toRet.add(l.getLuogoInfo());
        }

        return toRet.toString();
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Luogo l: getPlaces()) {
            builder.append(l);
        }
        return  builder.toString();
    }
}
