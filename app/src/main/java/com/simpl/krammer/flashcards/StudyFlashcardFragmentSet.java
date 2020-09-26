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
import android.widget.Toast;

import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class StudyFlashcardFragmentSet extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private StudyFlashcardSetRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StudyFlashcardFragmentSet() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StudyFlashcardFragmentSet newInstance(int columnCount) {
        StudyFlashcardFragmentSet fragment = new StudyFlashcardFragmentSet();
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
        view = inflater.inflate(R.layout.fragment_study_flashcard_set_list, container, false);

        buildRecycler();

        return view;
    }

    public void buildRecycler() {
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.studySetRecyclerView);

        layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //takes List<FlashCard> list as parameter
        mAdapter = new StudyFlashcardSetRecyclerViewAdapter(DummyContent.ITEMS);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new StudyFlashcardSetRecyclerViewAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(view.getContext(), "Item CLicked" +position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}