package com.simpl.krammer.flashcards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by IT19008042, N.H. Thiranjaya
 * CreateFlashcardSetFragmentTest Unit tests
 */
public class CreateFlashcardSetFragmentTest {
    private CreateFlashcardSetFragment createFlashcardSetFragment;
    private List<String> dbTitles = new ArrayList<>();

    @Before
    public void setup() {
        createFlashcardSetFragment = new CreateFlashcardSetFragment();
    }

    /***
     * method to test term.
     * return false if...
     * ...title is empty
     * ...title length exceeds 20
     */
    //test case null
    @Test
    public void nullTerm() {
        boolean result = createFlashcardSetFragment.validateTerm("");
        assertFalse(result);
    }

    //test case length > 20
    @Test
    public void lengthTerm() {
        boolean result = createFlashcardSetFragment.validateTerm("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertFalse(result);
    }

    //test case null
    @Test
    public void nullDef() {
        boolean result = createFlashcardSetFragment.validateDef("");
        assertFalse(result);
    }

    //test case length > 20
    @Test
    public void definitionLengthTest() {
        boolean result = createFlashcardSetFragment.validateDef("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertFalse(result);
    }
}