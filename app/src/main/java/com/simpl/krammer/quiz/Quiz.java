package com.simpl.krammer.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable {

    private String name;
    private float rating;
    private List<Question> questions;
    public Quiz(String name,float rating){
        this.name=name;
        setRating(rating);
    }

    public Quiz() {

    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRating(float rating) {
        if(rating<=5&&rating>=0){
            this.rating = rating;
        }else {
            throw new IllegalArgumentException("Rating should be between 0 and 5");
        }
    }

    public float getRating() {
        return rating;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }

    public void removeQuestion(Question question){
        this.questions.remove(question);
    }

    public void removeQuestion(int questionindex){
        this.questions.remove(questionindex);
    }




}
