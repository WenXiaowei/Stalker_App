package com.vartmp7.stalker.GsonBeans;

import com.vartmp7.stalker.GsonBeans.Retta;

import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class RettaTest {

    private static final float DELTA=0;
    public Retta r;

    @Test
    public  void testRettaOrizzontale(){
        r= new Retta(0,0);

        assertEquals(r.calcoloY(0),0,DELTA);
    }

    @Test
    public void testRetta(){
        r = new Retta(3,1);

        assertEquals(r.calcoloY(1),4,DELTA);
    }


    @Test
    public void testIntersezione(){
        r= new Retta(3,1);
        Retta r1 = new Retta(-1,2);
        Coordinata c= r.intersezione(r1);
//        assertEquals(0.25,c.getLatitude(),DELTA);
//        assertEquals(1.75,c.getLongitude(),DELTA);

        assertEquals(c,new Coordinata(0.25f,1.75f));
    }

    @Test
    public void testIntersezioneRettaNulla(){
        r= new Retta(3,1);
        Retta r2= new Retta(0,1);
        Coordinata c = r.intersezione(r2);
        assertEquals(c, new Coordinata(0,1));
    }
    @Test
    public void testIntersezioneRetteNulla(){
        r= new Retta(1,0);
        Retta r2= new Retta(0,1);
        Coordinata c = r.intersezione(r2);
        assertEquals(c, new Coordinata(1,1));
    }
}
