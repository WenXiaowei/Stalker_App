package com.vartmp7.stalker.component.gsonbeans;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LuogoQuadrilatero extends LuogoRegolare {

    private final int NUM_COORDINATE=4;
    /**
     * @param id         id del luogo
     * @param name       nome del luogo
     * @param coordinate elenco delle coordinate
     */
    LuogoQuadrilatero(long id, String name, List<Coordinata> coordinate) {
        super(id, name, coordinate);

        if (coordinate.size() != NUM_COORDINATE) throw new WrongNumberOfCoordinates();
    }

    LuogoQuadrilatero(long id, String name, List<Coordinata> coordinate, long num_max_people) {
        super(id, name, coordinate, num_max_people);
        if (coordinate.size() != NUM_COORDINATE) throw new WrongNumberOfCoordinates();
    }


    public Coordinata getCentro() {
        Retta r1 = new Retta(getCoordinate().get(0), getCoordinate().get(2));
        return r1.intersezione(new Retta(getCoordinate().get(1), getCoordinate().get(3)));
    }


    @Override
    boolean isInLuogo(Coordinata c) {
        return false;
    }

    /**
     * metodo usato per il POC per ottenere il raggio del tracciamento
     *
     * @return
     */
    @Deprecated
    public double getRadius() {
        return (getCoordinate().get(0).getDistanceTo(getCoordinate().get(2)) / 2 + getCoordinate().get(0).getDistanceTo(getCoordinate().get(1)) / 2) / 2;
    }


    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Coordinata c : getCoordinate()) {
            builder.append(c.toString());
        }

        return "\nid: " + getId() +
                "\nNome: " + getName() +
                "\nNum. Max Persone " + getNum_max_people() +
                "\nCoordinate: " + builder.toString();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof LuogoQuadrilatero && getCoordinate().size() == ((LuogoQuadrilatero) obj).getCoordinate().size()) {
            LuogoQuadrilatero l = (LuogoQuadrilatero) obj;

            for (int i = 0; i < getCoordinate().size(); i++) {
                if (getCoordinate().get(i) != l.getCoordinate().get(i))
                    return false;
            }
            return l.getId() == getId() && getName().equals(l.getName()) && getNum_max_people() == l.getNum_max_people();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
