package com.simpl.krammer.quiz;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private String id;
    private String name;
    private float rating;
    private ArrayList<Question> questions=new ArrayList<>();
    public Quiz(String id,String name,float rating){
        this.id=id;
        this.name=name;
        setRating(rating);
    }

    public Quiz() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
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
