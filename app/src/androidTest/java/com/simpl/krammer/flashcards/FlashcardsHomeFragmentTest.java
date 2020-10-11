package com.simpl.krammer.flashcards;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.simpl.krammer.MainActivity;
import com.simpl.krammer.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;

/**
 * Created by IT19008042 on 10/3/2020.
 * Test FlashcardHomeFragment UI
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class FlashcardsHomeFragmentTest {

    @Before
    public void setUp() throws Exception {
        //activity scenario launches main activity
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    /**
     * Verify home fragment recyclerView is displayed
     * navigate away
     * return and test if recyclerView is still displayed correctly
     */
    @Test
    public void flashcardHomeFragmentRecyclerTest() {
        //Navigate to create flashcard fragment
        onView(withId(R.id.flashcardHomeRecyclerView)).check(matches(isDisplayed()));

        //navigate away
        onView(withId(R.id.textViewAll)).perform(click());

        //return to home fragment
        pressBack();

        //test if recyclerView is still visible
        onView(withId(R.id.flashcardHomeRecyclerView)).check(matches(isDisplayed()));
    }

}