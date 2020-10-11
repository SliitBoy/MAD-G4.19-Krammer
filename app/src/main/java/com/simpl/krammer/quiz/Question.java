package com.simpl.krammer.quiz;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private String correct;
    private List<Answer> answerlist;

    public Question(String question, String correct, List<Answer> answerlist) {
        answerlist = new ArrayList<>();
        this.question = question;
        this.correct = correct;
        this.answerlist = answerlist;
    }

    public Question(String question, List<Answer> answerlist) {
        answerlist = new ArrayList<>();
        this.question = question;
        this.answerlist = answerlist;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public List<Answer> getAnswerlist() {
        return answerlist;
    }

    public void setAnswerlist(List<Answer> answerlist) {
        this.answerlist = answerlist;
    }

    public Question() {
    }

    public Answer getAnswer(int index){
        return this.answerlist.get(index);
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
