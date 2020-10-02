package com.simpl.krammer.flashcards;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class StudyFlashcardFragmentSet extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private StudyFlashcardSetRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FlashcardSet flashcardSet;
    private List<Flashcard> flashcards;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StudyFlashcardFragmentSet() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StudyFlashcardFragmentSet newInstance(FlashcardSet fcs) {
        StudyFlashcardFragmentSet fragment = new StudyFlashcardFragmentSet();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_study_flashcard_set_list, container, false);

        if (getArguments() != null) {
            //set FlashcardSet from ViewFlashcardsFragment
            flashcardSet = (FlashcardSet) getArguments().getSerializable("selectedSet");
            assert flashcardSet != null;
            flashcards = flashcardSet.getCardSet();
        }

        //build recycler view
        buildRecycler();

        return view;
    }

    public void buildRecycler() {
        // Set the adapter
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.studySetRecyclerView);

        layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(recyclerView);

        //takes List<FlashCard> list as parameter
        mAdapter = new StudyFlashcardSetRecyclerViewAdapter(flashcards);
        recyclerView.setAdapter(mAdapter);
    }
}