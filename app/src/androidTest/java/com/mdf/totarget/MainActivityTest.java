package com.mdf.totarget;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule public final ActivityRule<MainActivity> main = new ActivityRule<>(MainActivity.class);

    @Test
    public void PointsShouldBeVisible() {
        onView(withId(R.id.status))
            .check(ViewAssertions.matches(isDisplayed()))
            .check(ViewAssertions.matches(withText("0")));
    }

    @Test
    public void DoneTaskShouldIncreasePoints() {
        onView(withId(R.id.new_task)).perform(click());
        onView(withId(R.id.text)).perform(typeText("Task1"));
        // Select 10 points
        onView(withId(R.id.ok)).perform(click());

        onView(withId(R.id.status))
                .check(ViewAssertions.matches(withText("10")));
    }

}
