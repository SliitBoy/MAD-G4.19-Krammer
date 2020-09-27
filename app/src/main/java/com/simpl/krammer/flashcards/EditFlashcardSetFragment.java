package com.simpl.krammer.flashcards;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class EditFlashcardSetFragment extends Fragment {
    private DatabaseReference mDatabase;
    //FlashcardSet to be edited
    private FlashcardSet flashcardSet;
    //Flashcards to be edited
    private List<Flashcard> flashcards;

    // RecyclerView Objects
    private View view;
    private RecyclerView recyclerView;
    private EditFlashcardSetRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EditFlashcardSetFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EditFlashcardSetFragment newInstance(FlashcardSet flashcardSet) {
        EditFlashcardSetFragment fragment = new EditFlashcardSetFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_flashcard_set_list, container, false);

        if (getArguments() != null) {
            //set FlashcardSet object from ViewAllFlashcardsFragment
            flashcardSet = (FlashcardSet) getArguments().getSerializable("selectedSet");
        }

        flashcards = flashcardSet.getCardSet();

        buildRecycler();

        //firebase method to update edited set
        mDatabase = FirebaseDatabase.getInstance().getReference("CardSets");

        return view;
    }

    //method to build RecyclerView
    public void buildRecycler() {
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.editSetRecyclerView);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        //takes List<FlashCard> list as parameter
        mAdapter = new EditFlashcardSetRecyclerViewAdapter(flashcards);
        recyclerView.setAdapter(mAdapter);
    }
}