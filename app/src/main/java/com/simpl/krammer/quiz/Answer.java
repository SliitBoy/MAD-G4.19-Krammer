package com.simpl.krammer.quiz;

public class Answer {
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }



    public Answer() {

    }



    public String getAnswer() {
        return answer;
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
