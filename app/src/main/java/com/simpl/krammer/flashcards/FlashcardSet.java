package com.simpl.krammer.flashcards;

import java.util.List;

public class FlashcardSet {
    //List to hold Card objects
    private List<Flashcard> cardSet;
    private String cardSetTitle;
    private String cardSetDescription;

    //Default Constructor
    public FlashcardSet() {
    }

    // TODO: Validate if list already exists and all that
    public FlashcardSet(String title, String description, List<Flashcard> newSet) {
        //initialize List
        cardSet = newSet;
        cardSetTitle = title;
        cardSetDescription = description;
    }

    public List<Flashcard> getCardSet() {
        return cardSet;
    }

    public void setCardSet(List<Flashcard> cardSet) {
        this.cardSet = cardSet;
    }

    public int getSetSize() {return this.cardSet.size();}

    public String getCardSetTitle() {
        return cardSetTitle;
    }

    public void setCardSetTitle(String cardSetTitle) {
        this.cardSetTitle = cardSetTitle;
    }

    public String getCardSetDescription() {
        return cardSetDescription;
    }

    public void setCardSetDescription(String cardSetDescription) {
        this.cardSetDescription = cardSetDescription;
    }
}
