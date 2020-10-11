package com.simpl.krammer.flashcards;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.simpl.krammer.MainActivity;
import com.simpl.krammer.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by IT19008042 on 10/3/2020.
 * Test navigation between flashcard fragments
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class FragmentTransactionTest {

    @Before
    public void setup() throws Exception{
        //launch main activity
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);
    }


    /**
     * simulate user navigation
     */
    @Test
    public void testFragmentNavigation() {

        //Navigate to create flashcard fragment
        onView(withId(R.id.button_new_card_set)).perform(click());

        //verify if navigated to createFragment
        onView(withId(R.id.flashcard_create_fragment_parent)).check(matches(isDisplayed()));

        //return to previous fragment(home)
        pressBack();

        //verify if navigated to fragmentHome
        onView(withId(R.id.flashcard_home_fragment_parent)).check(matches(isDisplayed()));

        //Navigate to view all
        onView(withId(R.id.button_view_all_sets)).perform(click());

        //verify if navigated to view all
        onView(withId(R.id.flashcard_view_all_fragment_parent)).check(matches(isDisplayed()));

        //return to previous fragment(home)
        pressBack();

        //Navigate to view all
        onView(withId(R.id.textViewAll)).perform(click());

        //verify if navigated to view all
        onView(withId(R.id.flashcard_view_all_fragment_parent)).check(matches(isDisplayed()));
    }
}
