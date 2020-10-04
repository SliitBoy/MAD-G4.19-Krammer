package com.simpl.krammer.quiz;

import java.util.ArrayList;

public class Question {
    private int id;
    private String question;
    private String correct;
    private ArrayList<Answer> answerlist=new ArrayList<>();

    public Question(int id, String question) {
        this.id = id;
        this.question = question;

    }

    public Question(int id, String question, ArrayList answerlist) {
        this.id = id;
        this.question = question;
        this.answerlist = answerlist;
    }

    public Question(int id, String question, ArrayList answerlist,String corrrect) {
        this.id = id;
        this.question = question;
        this.answerlist = answerlist;
        this.correct=correct;
    }

    public Question() {

    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswerlist(ArrayList answerlist) {
        this.answerlist = answerlist;
    }

    public Answer getAnswer(int index){return this.answerlist.get(index);}

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList getAnswerlist() {
        return answerlist;
    }

    public  void addAnswer(Answer ans){
        this.answerlist.add(ans);
    }

    public  void removeAnswer(Answer ans){
        this.answerlist.remove(ans);
    }

    public void removeQuestion(int answerindex){
        this.answerlist.remove(answerindex);
    }
}
