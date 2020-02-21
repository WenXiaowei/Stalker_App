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

        StringBuilder builder = new StringBuilder();

        for (Luogo l: getPlaces()) {
            builder.append((getPlaces().indexOf(l))+1);
            builder.append(": "+l.getLuogoInfo());
        }

        return builder.toString();
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
