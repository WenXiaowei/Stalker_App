package com.vartmp7.stalker.GsonBeans;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ResponseLuogo {
    private static final String TAG="com.vartmp7.stalker.GsonBeans.ResponseLuogo";
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
//            Log.d(TAG, "getDataForSpinner: "+l.getName());
            builder.append((getPlaces().indexOf(l))+1);
            builder.append(": ").append(l.getName()).append("\n");
        }

        return builder.toString();
    }
    public int getPlacesLength(){return places.size();}

    // 45.411555, 11.887476
    // 45.411442, 11.887942
    // 45.411108, 11.887787
    // 45.411222, 11.887319
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
