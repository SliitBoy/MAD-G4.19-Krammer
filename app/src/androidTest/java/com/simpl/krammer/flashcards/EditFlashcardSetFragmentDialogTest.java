package com.simpl.krammer.flashcards;

import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Root;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.simpl.krammer.MainActivity;
import com.simpl.krammer.R;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;

/**
 * Created by IT19008042 on 10/3/2020.
 * Test EditFlashcardSetFragment alert dialog
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class EditFlashcardSetFragmentDialogTest {

    @Before
    public void setUp() throws Exception {
        //launch main activity
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testDeleteDialog() {
        //Navigate to view all
        onView(withId(R.id.textViewAll)).perform(click());

        //click on recyclerview item
        onView(withId(R.id.allSetsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //navigate to editFlashcardSetFragment
        onView(withId(R.id.editSetButton)).perform(click());

        //click on delete button to trigger alert dialog
        onView(withId(R.id.action_bar_delete)).perform(click());

        //test if dialog appears
        onView(withText(R.string.delete_set_msg)).check(matches(isDisplayed()));
    }

}