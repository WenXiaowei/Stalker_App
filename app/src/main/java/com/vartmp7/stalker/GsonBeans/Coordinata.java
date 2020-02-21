package com.vartmp7.stalker.GsonBeans;

import androidx.annotation.NonNull;

public class Coordinata {
    private float latitude;
    private float longitude;

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
}
