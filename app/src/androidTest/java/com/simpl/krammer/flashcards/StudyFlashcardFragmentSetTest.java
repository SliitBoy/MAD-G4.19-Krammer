package com.simpl.krammer.flashcards;

import android.os.Bundle;

import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.simpl.krammer.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;

/**
 * Created by IT19008042 on 10/4/2020.
 * Test StudyFlashcardFragmentSet fragment
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class StudyFlashcardFragmentSetTest {

    @Before
    public void setUp() throws Exception {
        List<Flashcard> flashcards = new ArrayList<>();
        Flashcard flashcard = new Flashcard("Term1", "Def1");
        Flashcard flashcard2 = new Flashcard("Term2", "Def2");
        flashcards.add(flashcard);
        flashcards.add(flashcard2);
        //initialize a flashcardSet object
        FlashcardSet flashcardSet = new FlashcardSet("Title","Description",flashcards);

        //pass selected FlashcardSet to ViewFlashcardSetFragment as bundle
        Bundle args = new Bundle();
        args.putSerializable("selectedSet", flashcardSet);

        //initialize fragmentFactory
        FragmentFactory fragmentFactory = new FragmentFactory();

        //initialize FragmentScenario
        FragmentScenario<StudyFlashcardFragmentSet> fragmentScenario =
                FragmentScenario.launchInContainer(StudyFlashcardFragmentSet.class,args, R.style.AppTheme, fragmentFactory);

        fragmentScenario.recreate();
    }

    /**
     * simulate user actions
     */
    @Test
    public void testReviewSet() {
        //test if front of card displays correct term
        onView(withId(R.id.flashcardFrontTextView)).check(matches(withText("Term1")));

        //click on card to flip
        onView(withId(R.id.studyEasyFlip)).perform(click());

        //test if back of card displays correct term
        onView(withId(R.id.flashcardBackTextView)).check(matches(withText("Def1")));

        //scroll to second card
        onView(withId(R.id.studySetRecyclerView)).perform(RecyclerViewActions.scrollToPosition(1));

        //test if front of card displays correct term
        onView(withId(R.id.flashcardFrontTextView)).check(matches(withText("Term2")));

        //click on card to flip
        onView(withId(R.id.studyEasyFlip)).perform(click());

        //test if back of card displays correct term
        onView(withId(R.id.flashcardBackTextView)).check(matches(withText("Def2")));
    }
}