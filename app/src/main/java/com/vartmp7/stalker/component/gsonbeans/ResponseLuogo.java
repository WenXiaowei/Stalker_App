package com.vartmp7.stalker.component.gsonbeans;

import java.util.ArrayList;

public class ResponseLuogo {
    private static final String TAG="com.vartmp7.stalker.GsonBeans.ResponseLuogo";
    private ArrayList<? extends AbstractLuogo> places;

    public void setLuoghi(ArrayList<LuogoQuadrilatero> luoghi) {
        this.places = luoghi;
    }
    public int getPlacesLength(){return places.size();}

}
