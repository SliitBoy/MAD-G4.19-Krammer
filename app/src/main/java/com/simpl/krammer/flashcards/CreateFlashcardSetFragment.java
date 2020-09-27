package com.simpl.krammer.flashcards;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class CreateFlashcardSetFragment extends Fragment {

    private DatabaseReference mDatabase;

    // RecyclerView Objects
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //New FlashCardSet List
    private List<Flashcard> newFlashCardSet;
    private int num;

    private MaterialButton addNewCardButton;
    private MaterialTextView saveSetButton;

    private TextInputEditText textInputSetTitle;
    private TextInputEditText textInputSetDescription;

    //TODO: Remove/move these to adapter?
    private TextInputLayout textInputTerm;
    private TextInputLayout textInputDef;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CreateFlashcardSetFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
//    public static CreateFlashcardSetFragment newInstance(int columnCount) {
//        CreateFlashcardSetFragment fragment = new CreateFlashcardSetFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_flashcard_set_list, container, false);

        //Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CardSets");

        //initialize newFlashCardSet
        newFlashCardSet = new ArrayList<Flashcard>();
        //add an item
        newFlashCardSet.add(new Flashcard());

        //create recyclerView
        buildRecycler();

        //add new card button
        addNewCardButton = view.findViewById(R.id.newCardRecyclerButton);
        addNewCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add a new cardView to recyclerView in newFlashCardFragment
                Log.d("CardSetTitle", "Inserting new card");
                newFlashCardSet.add(new Flashcard());
                mAdapter.notifyItemInserted(newFlashCardSet.size() - 1);
            }
        });

        //save set button
        saveSetButton = view.findViewById(R.id.buttonSaveSet);
        saveSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("CardSetTitle", "Calling saved set");
                saveSet();
            }
        });

        return view;
    }

    public void buildRecycler() {
        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.newSetRecyclerView);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        //takes List<FlashCard> list as parameter
        mAdapter = new CreateFlashcardRecyclerViewAdapter(newFlashCardSet);
        recyclerView.setAdapter(mAdapter);

    }

    //Validate flashCard term
    private boolean validateTerm() {
        //get entered term
        String termInput = textInputTerm.getEditText().getText().toString().trim();

        if (termInput.isEmpty()) {
            //if input field is empty
            textInputTerm.setError("Term field cannot be empty");
            return false;
        } else {
            textInputTerm.setError(null);
            return true;
        }
    }

    //Validate flashCard Definition
    private boolean validateDef() {
        //get entered definition
        String defInput = textInputDef.getEditText().getText().toString().trim();

        if (defInput.isEmpty()) {
            //if input field is empty
            textInputDef.setError("Definition field cannot be empty");
            return false;
            //if input exceeds max length
        } else if (defInput.length() > 100) {
            textInputDef.setError("Definition field cannot exceed 150 characters");
            return false;
        } else {
            textInputTerm.setError(null);
            return true;
        }
    }

    //method to save set to firebase
    public void saveSet() {
        //TODO create new FlashCardSet with title and description
        //TODO insert savedSet into new FlashCardSet
        //TODO validate
        //TODO change view to CardSetFragment
        //TODO change view to Home page if no new cards
        Log.d("SaveSet Function", "SavedSet function called");
        FlashcardSet flashCardSet;
        List<Flashcard> savedSet = new ArrayList<>();

        textInputSetTitle = view.findViewById(R.id.TextInputEditTextNewTitle);
        textInputSetDescription = view.findViewById(R.id.TextInputEditTextNewDescp);

        String title = textInputSetTitle.getText().toString();
        String description = textInputSetDescription.getText().toString();


        savedSet = ((CreateFlashcardRecyclerViewAdapter) recyclerView.getAdapter()).getSavedList();

        flashCardSet = new FlashcardSet(title, description, savedSet);
        Log.i("CardSetTitle", flashCardSet.getCardSetTitle());
        Log.i("CardSetDescp", flashCardSet.getCardSetDescription());

        for (Flashcard flashCard : flashCardSet.getCardSet()) {
            Log.i("CardTerm", flashCard.getTerm());
            Log.i("CardDef", flashCard.getDefinition());
        }

        //Write to Firebase
        mDatabase.push().setValue(flashCardSet);
        Toast.makeText(view.getContext(), "Data Inserted", Toast.LENGTH_LONG).show();
    }
}