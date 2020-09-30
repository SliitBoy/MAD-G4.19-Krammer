package com.simpl.krammer.quiz;

public class Answer {
    private int id;
    private int questionid;
    private int answerindex;
    private String answer;

    public Answer(int id, int questionid, int answerindex) {
        this.id = id;
        this.questionid = questionid;
        this.answerindex = answerindex;
    }

    public Answer(int id, int questionid, int answerindex, String answer) {
        this.id = id;
        this.questionid = questionid;
        this.answerindex = answerindex;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public int getQuestionid() {
        return questionid;
    }

    public int getAnswerindex() {
        return answerindex;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public void setAnswerindex(int answerindex) {
        this.answerindex = answerindex;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
