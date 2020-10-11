package com.simpl.krammer.flashcards;

import android.os.Bundle;

import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.simpl.krammer.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by IT19008042 on 10/3/2020.
 * Instrumented test to validate...
 * ViewFlashcardSetFragmentTest functionality
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class ViewFlashcardSetFragmentTest {

    @Before
    public void setUp() throws Exception {
        List<Flashcard> flashcards = new ArrayList<>();
        Flashcard flashcard = new Flashcard("Term1", "Def1");
        flashcards.add(flashcard);
        //initialize a flashcardSet object
        FlashcardSet flashcardSet = new FlashcardSet("Title","Description",flashcards);

        //pass selected FlashcardSet to ViewFlashcardSetFragment as bundle
        Bundle args = new Bundle();
        args.putSerializable("selectedSet", flashcardSet);

        //initialize fragmentFactory
        FragmentFactory fragmentFactory = new FragmentFactory();

        //initialize FragmentScenario
        FragmentScenario<ViewFlashcardSetFragment> fragmentScenario =
                FragmentScenario.launchInContainer(ViewFlashcardSetFragment.class,args,R.style.AppTheme, fragmentFactory);

        fragmentScenario.recreate();
    }

    /***
     * assert displayed title & card number in fragment view ...
     * matches parameters flashcardSet values
     */
    @Test
    public void testFragment() {
        onView(withId(R.id.TextViewFlashcardTitle)).check(matches(withText("Title")));
        onView(withId(R.id.TextViewCardNumber)).check(matches(withText("1 Terms")));
    }

}