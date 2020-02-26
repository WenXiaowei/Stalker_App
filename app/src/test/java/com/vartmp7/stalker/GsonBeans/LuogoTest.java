package com.vartmp7.stalker.GsonBeans;

import android.graphics.CornerPathEffect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class LuogoTest {


    public static final Double DELTA = 1d;
    private Luogo luogo;
    private Coordinata coordinata;

    public LuogoTest(final Luogo l, final Coordinata c) {
        this.luogo = l;
        this.coordinata = c;
    }

    @Test
    public void testY() {

    }
//

    @Test
    public void provaGetCentroTorre() {
        Luogo l = new Luogo();
        ArrayList<Coordinata> coordinate = new ArrayList<>();
        coordinate.add(new Coordinata(45.411555, 11.887476));
        coordinate.add(new Coordinata(45.411442, 11.887942));
        coordinate.add(new Coordinata(45.411108, 11.887787));
        coordinate.add(new Coordinata(45.411222, 11.887319));
        l.setCoordinate(coordinate);


//        assertEquals(new Coordinata(), l.getCentro(),DELTA);
    }

    //
//    @Parameterized.Parameter
    public static Collection luoghi() {
        ArrayList<Coordinata> torreArchimede = new ArrayList<>();
        torreArchimede.add(new Coordinata(45.411555, 11.887476));
        torreArchimede.add(new Coordinata(45.411442, 11.887942));
        torreArchimede.add(new Coordinata(45.411108, 11.887787));
        torreArchimede.add(new Coordinata(45.411222, 11.887319));
        Luogo t = new Luogo();
        t.setCoordinate(torreArchimede);


        ArrayList<Coordinata> inail = new ArrayList<>();
        inail.add(new Coordinata(45.411660, 11.887027));
        inail.add(new Coordinata(45.411846, 11.887572));
        inail.add(new Coordinata(45.411730, 11.887650));
        inail.add(new Coordinata(45.411544, 11.887106));
        Luogo i = new Luogo();
        i.setCoordinate(inail);
        ArrayList<Coordinata> dsea = new ArrayList<>();
        dsea.add(new Coordinata(45.411660, 11.887957));
        dsea.add(new Coordinata(45.411702, 11.888113));
        dsea.add(new Coordinata(45.411341, 11.888381));
        dsea.add(new Coordinata(45.411284, 11.888224));


        return Arrays.asList(
                t, new Coordinata(),
                i, new Coordinata(),
                dsea, new Coordinata());

    }


//    @Test
//    public void testCentro(){
//
//        assertEquals(coordinata, luogo.getCentro());
//
//    }
}
