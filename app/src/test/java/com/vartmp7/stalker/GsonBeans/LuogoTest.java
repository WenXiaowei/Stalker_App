package com.vartmp7.stalker.GsonBeans;

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


    private static final Coordinata C1=new Coordinata(0,0);
    private static final Coordinata C2=new Coordinata(1,1);

    private Luogo luogo;
    private Coordinata coordinata;

    public LuogoTest(final Luogo l, final Coordinata c){
        this.luogo = l;
        this.coordinata = c;
    }
    @Test
    public void testY(){

    }

//
//    @Parameterized.Parameter
//    public static Collection luoghi(){
//        return Arrays.asList(
//                // todo aggiungere altre rette e punti da calcolare
//                new Luogo().setCoordinate(
//                        new ArrayList<Coordinata>().add(new Coordinata(0,0))));
//
//    }


//    @Test
//    public void testCentro(){
//
//        assertEquals(coordinata, luogo.getCentro());
//
//    }
}
