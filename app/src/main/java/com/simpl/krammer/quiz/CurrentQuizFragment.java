package com.simpl.krammer.quiz;



import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.FlashcardSet;
//import com.simpl.krammer.quiz.dummy.quizList;

import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentQuizFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView Question;
    MaterialButton answer1,answer2,answer3,answer4,next;
    private Quiz quiz;
    Iterator<Question> queit;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CurrentQuizFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentQuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentQuizFragment newInstance(String param1, String param2) {
        CurrentQuizFragment fragment = new CurrentQuizFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_current_quiz, container, false);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            quiz = (Quiz) bundle.getSerializable("quiz");
            quiz = (Quiz) bundle.get("quiz");
        }else {
            Question =v.findViewById(R.id.Question);
            setNewQuestion(v);
        }
        //quiz= (Quiz) bundle.getSerializable("quiz");
        queit = quiz.getQuestions().iterator();
        Question =v.findViewById(R.id.Question);
        answer1=v.findViewById(R.id.answer1);
        answer2=v.findViewById(R.id.answer2);
        answer3=v.findViewById(R.id.answer3);
        answer4=v.findViewById(R.id.answer4);
        next=v.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQue();
            }
        });
        //Question.setText(quiz.getQuestions().size());
        return v;
    }

    private void nextQue() {
        Question que=queit.next();

        Question.setText(que.getQuestion());
//        Iterator<Answer> ansIt= que.getAnswerlist().iterator();
//        Answer ans = ansIt.next();
//        answer1.setText(ans.getAnswer());
//        ans = ansIt.next();
//        answer2.setText(ans.getAnswer());
//        ans = ansIt.next();
//        answer3.setText(ans.getAnswer());
//        ans = ansIt.next();
//        answer4.setText(ans.getAnswer());
    }

    public void setNewQuestion(View view){
        Question.setText((CharSequence) queit.next());
    }
    public void answerSelect(View view){
        //view.
    }
}