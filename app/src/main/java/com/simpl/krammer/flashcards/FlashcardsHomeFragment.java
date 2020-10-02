package com.simpl.krammer.flashcards;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simpl.krammer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Flashcards home fragment
 * @author IT19008042
 * create an instance of this fragment.
 */
public class FlashcardsHomeFragment extends Fragment {
    private DatabaseReference mDatabase;

    //List to hold all card sets
    private List<FlashcardSet> flashcardSets;

    // RecyclerView Objects
    private View view;
    private RecyclerView recyclerView;
    private ViewAllSetsRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button buttonNewCardSet;
    private Button buttonViewAllCardSets;
    private TextView buttonAllCardSet;

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
        view = inflater.inflate(R.layout.fragment_flashcards_home, container, false);

        //Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("CardSets");
        flashcardSets = new ArrayList<FlashcardSet>();
        //get cardsets from firebase
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("Count " ,""+snapshot.getChildrenCount());
                //get all children under "CardSets"
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FlashcardSet flashcardSetTemp = dataSnapshot.getValue(FlashcardSet.class);
                    //add to List<FlashcardSet> flashcardsSet
                    flashcardSets.add(flashcardSetTemp);
                }
                //Build RecyclerView
                buildRecycler();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //call newFlashcardSetFragment
        buttonNewCardSet = view.findViewById(R.id.button_new_card_set);
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

        //call viewAllSetsFragment
        buttonAllCardSet = view.findViewById(R.id.textViewAll);
        buttonAllCardSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllSets();
            }
        });

        //call viewAllSetsFragment
        buttonViewAllCardSets = view.findViewById(R.id.button_view_all_sets);
        buttonViewAllCardSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllSets();
            }
        });

        return view;
    }

    public void buildRecycler() {
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.flashcardHomeRecyclerView);

        layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        //SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setLayoutManager(layoutManager);
        //snapHelper.attachToRecyclerView(recyclerView);

        //takes List<FlashCardSet> list as parameter
        mAdapter = new ViewAllSetsRecyclerViewAdapter(flashcardSets);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ViewAllSetsRecyclerViewAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(int position, FlashcardSet fcs) {
                ViewFlashcardSetFragment viewFlashcardSetFragment = new ViewFlashcardSetFragment();

                FragmentManager fm = getFragmentManager();

                //pass selected FlashcardSet to ViewFlashcardSetFragment
                Bundle args = new Bundle();
                FlashcardSet flashcardSet = fcs;
                args.putSerializable("selectedSet", flashcardSet);
                viewFlashcardSetFragment.setArguments(args);

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, viewFlashcardSetFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
    }

    private void viewAllSets() {
        ViewAllSetsFragment viewAllSetsFragment = new ViewAllSetsFragment();

        FragmentManager fm = getFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, viewAllSetsFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}