package com.vartmp7.stalker;

import com.vartmp7.stalker.gsonbeans.GsonBeansTestSuite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;

import static junit.framework.TestCase.assertEquals;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GsonBeansTestSuite.class
})
public class ExampleTest {
}
