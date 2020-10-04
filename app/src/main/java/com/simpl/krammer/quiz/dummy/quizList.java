package com.simpl.krammer.quiz.dummy;

import com.simpl.krammer.quiz.Answer;
import com.simpl.krammer.quiz.Question;
import com.simpl.krammer.quiz.Quiz;

import java.util.ArrayList;

public class quizList {
    public ArrayList<Quiz> quizlist=new ArrayList<>();
    public quizList(){
        Quiz q= new Quiz();


        q.setName("test");
        Answer ans1=new Answer("a1");
        Answer ans2=new Answer("a2");
        Answer ans3=new Answer("a3");
        Answer ans4=new Answer("a4");
        ArrayList <Answer> answerlist=new ArrayList<>();
        answerlist.add(ans1);
        answerlist.add(ans2);
        answerlist.add(ans3);
        answerlist.add(ans4);
        q.addQuestion(new Question(1,"q1",answerlist,"a1"));



        ans1=new Answer("a1");
        ans2=new Answer("a2");
        ans3=new Answer("a3");
        ans4=new Answer("a4");
        answerlist=new ArrayList<>();
        answerlist.add(ans1);
        answerlist.add(ans2);
        answerlist.add(ans3);
        answerlist.add(ans4);
        q.addQuestion(new Question(1,"q2",answerlist,"a1"));



        ans1=new Answer("a1");
        ans2=new Answer("a2");
        ans3=new Answer("a3");
        ans4=new Answer("a4");
        answerlist=new ArrayList<>();
        answerlist.add(ans1);
        answerlist.add(ans2);
        answerlist.add(ans3);
        answerlist.add(ans4);
        q.addQuestion(new Question(1,"q3",answerlist,"a1"));



        ans1=new Answer("a1");
        ans2=new Answer("a2");
        ans3=new Answer("a3");
        ans4=new Answer("a4");
        answerlist=new ArrayList<>();
        answerlist.add(ans1);
        answerlist.add(ans2);
        answerlist.add(ans3);
        answerlist.add(ans4);
        q.addQuestion(new Question(1,"q4",answerlist,"a1"));
        quizlist.add(q);
    }

}
