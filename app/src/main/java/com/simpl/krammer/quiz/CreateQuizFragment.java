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

    private TextInputEditText answer1,answer2,answer3,answer4,question,quiztext;
    private RadioGroup group;
    private Quiz quiz=new Quiz();
    private Question current=new Question();
    private DatabaseReference mDatabase;
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
        answer1=v.findViewById(R.id.answer1);
        answer2=v.findViewById(R.id.answer2);
        answer3=v.findViewById(R.id.answer3);
        answer4=v.findViewById(R.id.answer4);
        question=v.findViewById(R.id.Question);
        quiztext=v.findViewById(R.id.QuizText);
        MaterialButton addQuestionButton= v.findViewById(R.id.addQuestion);
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddQueButtonClicked(view);
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
        
        mDatabase.child("quiz").push().setValue(this.quiz);
    }


//    public void onRadioButtonClicked(View view) {
//    answer1=view.findViewById(R.id.answer1);
//    answer2=view.findViewById(R.id.answer2);
//    answer3=view.findViewById(R.id.answer3);
//    answer4=view.findViewById(R.id.answer4);
//        // Is the button now checked?
//
//        boolean checked = ((AppCompatRadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.answer1radio:
//                if (checked){
//                    current.setCorrect(answer1.getText().toString());
//                }
//                    break;
//            case R.id.answer2radio:
//                if (checked)
//                    current.setCorrect(answer2.getText().toString());
//                    break;
//            case R.id.answer3radio:
//                if (checked)
//                    current.setCorrect(answer3.getText().toString());
//                    break;
//            case R.id.answer4radio:
//                if (checked)
//                    current.setCorrect(answer4.getText().toString());
//                    break;
//        }
//    }
    public void onAddQueButtonClicked(View view) {
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
            current.setCorrect(radioButton.getText().toString());
            Answer ans = new Answer();
            ans.setAnswer(answer1.getText().toString());
            current.addAnswer(ans);
            Log.d("addQueOnclickAnswer1", ans.getAnswer());
            ans.setAnswer(answer2.getText().toString());
            current.addAnswer(ans);
            Log.d("addQueOnclickAnswer1", ans.getAnswer());
            ans.setAnswer(answer3.getText().toString());
            current.addAnswer(ans);
            Log.d("addQueOnclickAnswer1", ans.getAnswer());
            ans.setAnswer(answer4.getText().toString());
            current.addAnswer(ans);
            Log.d("addQueOnclickAnswer1", ans.getAnswer());
            current.setQuestion(question.getText().toString());
            quiz.addQuestion(current);
            for (int i = 0; i <4 ; i++) {
                Log.d( "CreatequizAddQue", "onAddQueButtonClicked:  "+current.getAnswerlist().get(i));
            }


        }
    }
}