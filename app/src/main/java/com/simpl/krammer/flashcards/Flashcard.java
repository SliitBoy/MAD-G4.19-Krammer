package com.simpl.krammer.flashcards;

/***
 * Created by IT19008042, N.H. Thiranjaya
 * Flashcard helper class
 */
public class Flashcard {
    //TODO remove temp id
    private Integer index;
    private String term;
    private String definition;

    public Flashcard(){}

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
