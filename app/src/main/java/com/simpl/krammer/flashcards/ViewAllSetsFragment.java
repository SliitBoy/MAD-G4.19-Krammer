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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ViewAllSetsFragment extends Fragment {

    private DatabaseReference mDatabase;

    // RecyclerView Objects
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //List to hold all card sets
    private List<FlashcardSet> flashcardsSet;

    private TextView numOfSets;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ViewAllSetsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ViewAllSetsFragment newInstance(int columnCount) {
        ViewAllSetsFragment fragment = new ViewAllSetsFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_all_sets_list, container, false);

        //Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CardSets");

        flashcardsSet = new ArrayList<FlashcardSet>();
        numOfSets = view.findViewById(R.id.TextViewSetNumber);

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

                Log.e("getTest " ,""+flashcardsSet.get(0).getCardSetTitle());
                numOfSets.setText(flashcardsSet.size() + " Sets");
                //Build RecyclerView
                buildRecycler();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Set the adapter

        return view;
    }

    public void buildRecycler() {
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.allSetsRecyclerView);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        //takes List<FlashCardSet> list as parameter
        mAdapter = new ViewAllSetsRecyclerViewAdapter(flashcardsSet);
        recyclerView.setAdapter(mAdapter);

    }
}