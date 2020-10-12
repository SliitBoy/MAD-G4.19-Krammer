package com.simpl.krammer.quiz;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simpl.krammer.MainActivity;
import com.simpl.krammer.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateQuizFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private TextInputLayout answers1,answers2,answers3,answers4,Question,Quiz;
    private TextInputEditText answer1input,answer2input,answer3input,answer4input,question,quiztext;
    private RadioGroup group;
    private Quiz quiz=new Quiz();
    private Question current=new Question();
    private DatabaseReference mDatabase;
    private ArrayList<Question> questionlist=new ArrayList<>();
    // TODO: Rename and change types of parameters


    public CreateQuizFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CreateQuizFragment newInstance(Quiz quiz) {
        CreateQuizFragment fragment = new CreateQuizFragment();
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
        View v=inflater.inflate(R.layout.fragment_create_quiz, container, false);
        // Inflate the layout for this fragment
        mDatabase = FirebaseDatabase.getInstance().getReference();
        group=v.findViewById(R.id.group);
//        answers1=(TextInputLayout) v.findViewById(R.id.Answers1);
//        answers2=(TextInputLayout)v.findViewById(R.id.Answers2);
//        answers3=(TextInputLayout)v.findViewById(R.id.Answers3);
//        answers4=(TextInputLayout)v.findViewById(R.id.Answers4);
        Question=v.findViewById(R.id.question);
        Quiz=v.findViewById(R.id.quizName);
        answer1input=v.findViewById(R.id.answer1input);
        answer2input=v.findViewById(R.id.answer2input);
        answer3input=v.findViewById(R.id.answer3input);
        answer4input=v.findViewById(R.id.answer4input);
        question=v.findViewById(R.id.Question);
        quiztext=v.findViewById(R.id.QuizText);
        MaterialButton addQuestionButton= v.findViewById(R.id.addQuestion);
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onAddQueButtonClicked(view);
                }catch (Exception e){
                    Log.w("onAddQueButtonClicked", "onClick: ", e);
                }

            }
        });
        MaterialButton viewQuestionsButton=v.findViewById(R.id.viewQuestions);
        viewQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewQuestionsFragment nextFrag=new ViewQuestionsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "createQuizFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        MaterialButton createQuiz=v.findViewById(R.id.createQuiz);
        createQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewQuiz();
            }
        });
        return v;



    }



    private void writeNewQuiz() {
        quiz= new Quiz(Quiz.getEditText().getText().toString(),0,questionlist);

        mDatabase.child("quiz").push().setValue(this.quiz);
    }



    public void onAddQueButtonClicked(View view)throws NullPointerException {
        if (group.getCheckedRadioButtonId() == -1)
        {
            Toast toast = Toast.makeText(view.getContext(), "select an answer before submitting", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            // one of the radio buttons is checked
            int radioButtonID = group.getCheckedRadioButtonId();
            AppCompatRadioButton radioButton= view.findViewById(radioButtonID);
            if(radioButtonID==R.id.answer1radio){
                current.setCorrect(answer1input.getText().toString());
            }else if(radioButtonID==R.id.answer2radio){
                current.setCorrect(answer2input.getText().toString());
            }else if(radioButtonID==R.id.answer3radio){
                current.setCorrect(answer3input.getText().toString());
            }else if(radioButtonID==R.id.answer4radio){
                current.setCorrect(answer4input.getText().toString());
            }else {
                Toast toast = Toast.makeText(view.getContext(), "An unexpected error occurred", Toast.LENGTH_SHORT);
                toast.show();
            }
            ArrayList<Answer> anslist =new ArrayList<>();
            String ans1=answer1input.getText().toString();
            Log.i("click", "onAddQueButtonClicked: "+ans1);
            String ans2=answer2input.getText().toString();
            Log.i("click", "onAddQueButtonClicked: "+ans2);
            String ans3=answer3input.getText().toString();
            Log.i("click", "onAddQueButtonClicked: "+ans3);
            String ans4=answer4input.getText().toString();
            Log.i("click", "onAddQueButtonClicked: "+ans4);
            anslist.add(new Answer(ans1));
            anslist.add(new Answer(ans2));
            anslist.add(new Answer(ans3));
            anslist.add(new Answer(ans4));
            current.setAnswerlist(anslist);
            questionlist.add(current);



            for (int i = 0; i <4 ; i++) {
                Log.d( "CreatequizAddQue", "onAddQueButtonClicked -- answers:  "+current.getAnswerlist().get(i));
            }
            current=new Question();

        }
    }
}