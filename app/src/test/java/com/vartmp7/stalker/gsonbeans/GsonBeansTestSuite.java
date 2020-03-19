package com.vartmp7.stalker.gsonbeans;

import com.vartmp7.stalker.gsonbeans.placecomponent.CoordinataTest;
import com.vartmp7.stalker.gsonbeans.placecomponent.PlaceComponentTestSuite;
import com.vartmp7.stalker.gsonbeans.placecomponent.RettaTest;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LuogoPoligonoTest.class,
        RettaTest.class,
        LuogoACirconferenzaTest.class,
        OrganizzazioneTest.class,
        CoordinataTest.class,
        PlaceComponentTestSuite.class,
        ResponseLuogoTest.class,
        ResponseOrganizzazioneTest.class,
        TrackSignalTest.class,
        WrongNumberOfCoordinatesTest.class
})
public class GsonBeansTestSuite {
}
