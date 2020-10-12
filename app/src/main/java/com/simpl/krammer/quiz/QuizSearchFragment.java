package com.simpl.krammer.quiz;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.FlashcardSet;
import com.simpl.krammer.quiz.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class QuizSearchFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private DatabaseReference mDatabase;
    private View view;
    List<Quiz> quizlist;
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuizSearchFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static QuizSearchFragment newInstance(int columnCount) {
        QuizSearchFragment fragment = new QuizSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_quiz_search_list, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("quiz");
        quizlist=new ArrayList<>();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("Count ", "" + snapshot.getChildrenCount());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Quiz quizTemp = dataSnapshot.getValue(Quiz.class);
                    quizlist.add(quizTemp);
                }
                if (view instanceof RecyclerView) {
                    Context context = view.getContext();
                    RecyclerView recyclerView = (RecyclerView) view;
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(layoutManager);

//                   if (mColumnCount <= 1) {
//                      recyclerView.setLayoutManager(new LinearLayoutManager(context));
//                  } else {
//                      recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//                   }
                    recyclerView.setAdapter(new MyItemRecyclerViewAdapter(quizlist));
                    }
//                  Log.e("getTest ", "" + quizlist.get(0).getName());
//                  numOfSets.setText(flashcardsSet.size() + " Sets");
//                  buildRecycler();
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
                });
            // Set the adapter

        return view;
    }
}