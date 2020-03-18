package com.vartmp7.stalker.gsonbeans;

import com.vartmp7.stalker.gsonbeans.placecomponent.Coordinata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LuogoPoligonoTest {

    private static final Double DELTA = 0.000011d;
    private LuogoPoligono luogo;
    private Coordinata coordinata;

    public LuogoPoligonoTest(final LuogoPoligono l, final Coordinata c) {
        this.luogo = l;
        this.coordinata = c;
    }

    @Test
    public void testProva(){
        assertTrue(luogo.isInLuogo(coordinata));
    }




    @Parameterized.Parameters
    public static Collection luoghi() {
        ArrayList<Coordinata> torreArchimede = new ArrayList<>();
        torreArchimede.add(new Coordinata(45.411555, 11.887476));
        torreArchimede.add(new Coordinata(45.411442, 11.887942));
        torreArchimede.add(new Coordinata(45.411108, 11.887787));
        torreArchimede.add(new Coordinata(45.411222, 11.887319));
        LuogoPoligono t = new LuogoPoligono();
        t.setCoordinate(torreArchimede);


        ArrayList<Coordinata> inail = new ArrayList<>();
        inail.add(new Coordinata(45.411660, 11.887027));
        inail.add(new Coordinata(45.411846, 11.887572));
        inail.add(new Coordinata(45.411730, 11.887650));
        inail.add(new Coordinata(45.411544, 11.887106));
        LuogoPoligono i = new LuogoPoligono();
        i.setCoordinate(inail);


        ArrayList<Coordinata> dsea = new ArrayList<>();
        dsea.add(new Coordinata(45.411660, 11.887957));
        dsea.add(new Coordinata(45.411702, 11.888113));
        dsea.add(new Coordinata(45.411341, 11.888381));
        dsea.add(new Coordinata(45.411284, 11.888224));
        LuogoPoligono d= new LuogoPoligono();
        d.setCoordinate(dsea);

        return Arrays.asList(new Object[][]{
                {t, new Coordinata( 45.411332,11.887631)},
                {i, new Coordinata(45.411695, 11.887339)},
                {d, new Coordinata(45.411502, 11.888165)}
        });

    }



}
