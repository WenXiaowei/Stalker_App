package com.vartmp7.stalker;


import android.location.LocationManager;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    @Test
    public void testStartTrackingWithUniPD(){

        onView(withId(R.id.s_scegliOrganizzazione)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.s_scegliOrganizzazione)).check(matches(withSpinnerText(containsString(
                "UniPD"))));

        onView(withId(R.id.btnShowLoginDialog)).check(matches(isDisplayed()));
        onView(withId(R.id.btnShowLoginDialog)).perform(click());

        onView(withText(R.string.conferma)).inRoot(isDialog()) // <---
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.btnStartTracking)).check(matches(isDisplayed()));
        onView(withId(R.id.btnStartTracking)).check(matches(isClickable())).perform(click());
        onView(withId(R.id.tvCurrentStatus)).check(matches(isDisplayed()));

    }

}
