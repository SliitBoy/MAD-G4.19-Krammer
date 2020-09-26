package com.simpl.krammer.flashcards;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 */
public class ViewFlashcardSetFragment extends Fragment {

    private DatabaseReference mDatabase;

    // RecyclerView Objects
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<FlashcardSet> flashcardsSet;
    private List<Flashcard> flashcards;
    private FlashcardSet flashcardSetObj;

    private MaterialButton viewSetButton;
    private MaterialButton studySetButton;

    private TextView setTitle;
    private TextView setDescription;
    private TextView numOfTerms;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ViewFlashcardSetFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ViewFlashcardSetFragment newInstance(int columnCount) {
        ViewFlashcardSetFragment fragment = new ViewFlashcardSetFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
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
        view = inflater.inflate(R.layout.fragment_view_flashcard_set_list, container, false);


        //Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CardSets");

        flashcardsSet = new ArrayList<FlashcardSet>();
        flashcards = new ArrayList<Flashcard>();

        //get cardsets from firebase
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("Count " ,""+snapshot.getChildrenCount());
                //get all children under "CardSets"
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FlashcardSet flashcardSetTemp = dataSnapshot.getValue(FlashcardSet.class);
                    //add to List<FlashcardSet> flashcardsSet
                    flashcardsSet.add(flashcardSetTemp);
                }
                flashcards = flashcardsSet.get(0).getCardSet();
                flashcardSetObj = flashcardsSet.get(0);
                Log.e("getTest " ,""+flashcards.get(0).getTerm());
                Log.e("getTest " ,""+flashcards.get(1).getTerm());
                Log.e("fcsObj " ,""+flashcardSetObj.getCardSetTitle());


                //Set Title and Description from flashcardSet
                setTitle = view.findViewById(R.id.TextViewFlashcardTitle);
                setTitle.setText(flashcardSetObj.getCardSetTitle());

                //set Number of Terms
                numOfTerms = view.findViewById(R.id.TextViewCardNumber);
                numOfTerms.setText(flashcards.size() + " Terms");

                //Build RecyclerView
                buildRecycler();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w( "loadPost:onCancelled", error.toException());
            }
        });

        return view;
    }

    public void buildRecycler() {
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.viewSetRecyclerView);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        //takes List<FlashCard> list as parameter
        mAdapter = new ViewFlashcardSetRecyclerViewAdapter(flashcardSetObj);
        recyclerView.setAdapter(mAdapter);

    }
}