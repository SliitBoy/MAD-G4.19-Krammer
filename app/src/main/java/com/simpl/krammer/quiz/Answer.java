package com.simpl.krammer.quiz;

public class Answer {
    private int id;
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public Answer(int id, int questionid, int answerindex, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Answer() {

    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
//        return "Answer{" +
//                "answer='" + answer + '\'' +
//                '}';
        return answer;
    }
}
