package com.simpl.krammer.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.FlashcardSet;
import com.simpl.krammer.flashcards.ViewFlashcardSetFragment;
import com.simpl.krammer.quiz.dummy.quizList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartQuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartQuizFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Quiz quiz= new quizList().quizlist.get(0);
    TextView questionnum;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartQuizFragment(Quiz quiz) {
        // Required empty public constructor
        this.quiz=quiz;
        //remove the line after this
        this.quiz= new quizList().quizlist.get(0);
    }

    public StartQuizFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartQuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartQuizFragment newInstance(String param1, String param2) {
        StartQuizFragment fragment = new StartQuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_start_quiz, container, false);
        MaterialButton button = v.findViewById(R.id.startquiz);
        questionnum=v.findViewById(R.id.Questionnum);
        questionnum.setText(quiz.getQuestions().size()+" Questions");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CurrentQuizFragment nextFrag= new CurrentQuizFragment();
//                getActivity().getSupportFragmentManager().beginTransaction()
//                Bundle bundle = new Bundle();
//                Quiz q = quiz;
//                bundle.putSerializable("quiz", q);
//                nextFrag.setArguments(bundle);
//                nextFrag.replace(R.id.fragment_container, nextFrag, "createQuizFragment")
//                nextFrag.addToBackStack(null)
//                nextFrag.commit();
                CurrentQuizFragment nextFrag = new CurrentQuizFragment();

                FragmentManager fm = getFragmentManager();

                Bundle args = new Bundle();
                Quiz q=quiz;

                args.putSerializable("quiz", q);

                nextFrag.setArguments(args);
                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.replace(R.id.fragment_container, nextFrag);
                ft.addToBackStack(null);

                ft.commit();
//                FragmentTransaction transaction = fm.beginTransaction();
//                transaction.replace(R.id.fragment_container, nextFrag);
//                transaction.addToBackStack(null);
//
//                transaction.commit();
            }
        });
        return v;
    }
}