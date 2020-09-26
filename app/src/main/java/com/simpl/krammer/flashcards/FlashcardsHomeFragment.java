package com.simpl.krammer.flashcards;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.simpl.krammer.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlashcardsHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlashcardsHomeFragment extends Fragment {

    private Button buttonNewCardSet;
    private Button buttonViewCardSet;
    private Button buttonStudyCardSet;
    private Button buttonAllCardSet;

    public FlashcardsHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlashcardsHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlashcardsHomeFragment newInstance(String param1, String param2) {
        FlashcardsHomeFragment fragment = new FlashcardsHomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_flashcards_home, container, false);

        //call newFlashcardSetFragment
        buttonNewCardSet = v.findViewById(R.id.button_new_card_set);
        buttonNewCardSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateFlashcardSetFragment newFlashCardSetFragment = new CreateFlashcardSetFragment();

                FragmentManager fm = getFragmentManager();

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, newFlashCardSetFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        //call newFlashcardSetFragment
        buttonViewCardSet = v.findViewById(R.id.button_view_card_set);
        buttonViewCardSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewFlashcardSetFragment viewFlashcardSetFragment = new ViewFlashcardSetFragment();

                FragmentManager fm = getFragmentManager();

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, viewFlashcardSetFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        //call StudyFlashcardSetFragment
        buttonStudyCardSet = v.findViewById(R.id.button_study_card_set);
        buttonStudyCardSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudyFlashcardFragmentSet studyFlashcardFragmentSet = new StudyFlashcardFragmentSet();

                FragmentManager fm = getFragmentManager();

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, studyFlashcardFragmentSet);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        //call viewAllSetsFragment
        buttonAllCardSet = v.findViewById(R.id.button_all_card_set);
        buttonAllCardSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewAllSetsFragment viewAllSetsFragment = new ViewAllSetsFragment();

                FragmentManager fm = getFragmentManager();

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, viewAllSetsFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return v;
    }
}