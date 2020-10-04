package com.simpl.krammer.flashcards;

import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.simpl.krammer.MainActivity;
import com.simpl.krammer.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;

/**
 * Created by IT19008042 on 10/4/2020.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class CreateFlashcardSetFragmentTest {

    @Before
    public void setUp() throws Exception {
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testCreate() {
        //Navigate to view all
        onView(withId(R.id.textViewAll)).perform(click());

        //got to create menu
        //openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withId(R.id.action_bar_add)).perform(click());

        //verify if navigated to createFragment
        onView(withId(R.id.flashcard_create_fragment_parent)).check(matches(isDisplayed()));
    }
}