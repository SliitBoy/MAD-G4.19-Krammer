package com.simpl.krammer.flashcards;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
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
    private ViewAllSetsRecyclerViewAdapter mAdapter;
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
        setHasOptionsMenu(true);

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

    //method to call CreateFlashcardSetFragment
    public void addNewSet() {
        CreateFlashcardSetFragment newFlashCardSetFragment = new CreateFlashcardSetFragment();

        FragmentManager fm = getFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, newFlashCardSetFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.action_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_bar_search);
        MenuItem addSet = menu.findItem(R.id.action_bar_add);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bar_add:
                //call CreateFlashcardSetFragment
                addNewSet();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}