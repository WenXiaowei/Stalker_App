package com.vartmp7.stalker.GsonBeans;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Luogo {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum_max_people() {
        return num_max_people;
    }

    public void setNum_max_people(String num_max_people) {
        this.num_max_people = num_max_people;
    }

    public ArrayList<Coordinata> getCoordinate() {
        return coordinates;
    }

    public void setCoordinate(ArrayList<Coordinata> coordinate) {
        this.coordinates = coordinate;
    }

    private String id;
    private String name;
    private String num_max_people;
    private ArrayList<Coordinata> coordinates;

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder= new StringBuilder();

        for (Coordinata c: coordinates) {
            builder.append(c.toString());
        }


        return "id: "+getId()+
                "\nNome: "+getName()+
                "\nNum. Max Persone "+getNum_max_people()+
                "\nCoordinate: "+builder.toString();
    }

    public String getLuogoInfo() {
        return "Nome ";
    }
}
