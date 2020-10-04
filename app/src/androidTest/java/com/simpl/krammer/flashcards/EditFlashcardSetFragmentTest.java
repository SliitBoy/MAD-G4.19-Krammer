package com.simpl.krammer.flashcards;

import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowManager;

import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
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

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by IT19008042 on 10/3/2020.
 * Test EditFlashcardSetFragment
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class EditFlashcardSetFragmentTest {

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
        FragmentScenario<EditFlashcardSetFragment> fragmentScenario =
                FragmentScenario.launchInContainer(EditFlashcardSetFragment.class,args, R.style.AppTheme, fragmentFactory);

        fragmentScenario.recreate();
    }

    @Test
    public void testEditFragment() {
        onView(withId(R.id.editTextEditTitle)).check(matches(withText("Title")));
        onView(withId(R.id.editTextEditDescp)).check(matches(withText("Description")));
    }


}