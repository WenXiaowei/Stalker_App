package com.vartmp7.stalker.GsonBeans;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Luogo {
    private String id;

    private String name;
    private String num_max_people;
    private ArrayList<Coordinata> coordinates;

    public Coordinata getCentro() {
        Retta r1 = new Retta(coordinates.get(0), coordinates.get(2));
        return r1.intersezione(new Retta(coordinates.get(1), coordinates.get(3)));
    }


    public boolean isPlace(Coordinata c){
        //todo definire quando una coordinata è all'interno di un luogo.
        return false;
    }
    public float getRadius(){
        return 10f;
    }


    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Coordinata c : coordinates) {
            builder.append(c.toString());
        }

        return "\nid: " + getId() +
                "\nNome: " + getName() +
                "\nNum. Max Persone " + getNum_max_people() +
                "\nCoordinate: " + builder.toString();
    }

    public String getLuogoInfo() {
        return "Nome ";
    }

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

    public Luogo setCoordinate(ArrayList<Coordinata> coordinate) {
        this.coordinates = coordinate;
        return this;
    }

}
