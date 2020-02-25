package com.vartmp7.stalker.GsonBeans;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Coordinata {
    private double latitude;//y
    private double longitude; //x

    public  Coordinata(){}
    public Coordinata(double latitudine, double longitude){
        this.latitude=latitudine;
        this.longitude=longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longoitude) {
        this.longitude = longoitude;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nLongitude(x): "+getLongitude()+
                "\nLatitude(y): "+getLatitude();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Coordinata){
            Coordinata c= (Coordinata) obj;
            return c.longitude== this.longitude && c.latitude == this.latitude;
        }
        return false;
    }
}
