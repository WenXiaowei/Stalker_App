package com.vartmp7.stalker.GsonBeans;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Coordinata {
    private float latitude;//y
    private float longitude; //x

    public  Coordinata(){}
    public Coordinata(float latitudine, float longitude){
        this.latitude=latitudine;
        this.longitude=longitude;
    }
    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longoitude) {
        this.longitude = longoitude;
    }

    @NonNull
    @Override
    public String toString() {
        return "Latitude: "+getLatitude()+
                "\nLongitude: "+getLongitude();
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
