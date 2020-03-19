package com.vartmp7.stalker.gsonbeans;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LuogoPoligonoTest.class,
        RettaTest.class,
        LuogoACirconferenzaTest.class,
        OrganizzazioneTest.class,
        CoordinataTest.class})
@Categories.ExcludeCategory
public class GsonBeansTestSuite {
}
