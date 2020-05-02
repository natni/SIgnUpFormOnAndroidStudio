package com.example.form;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.signup.MainActivity;
import com.example.signup.R;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasTextOnScreen() {
        onView(withId(R.id.developedDate))
                .check(matches(withText("App was developed on 04/24/2020 by Natnael Bekele")));

    }

}