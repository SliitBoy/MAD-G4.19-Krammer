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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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

    private TextInputLayout textLayoutSetTitle;
    private TextInputLayout textLayoutSetDescription;

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

        //initialize newFlashCardSet
        newFlashCardSet = new ArrayList<Flashcard>();
        //add an item
        newFlashCardSet.add(new Flashcard());

        //initialize title and description
        textLayoutSetTitle = view.findViewById(R.id.textInputLayoutNewTitle);
        textLayoutSetDescription = view.findViewById(R.id.textInputLayoutNewDescp);

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
                Log.i("clickedSave", "Clicked saved set");
                if (!validateTitle()) {
                    return;
                } else {
                    Log.i("clickedSave", "Calling check set");
                    checkSet();
                }
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
            textInputTerm.setErrorEnabled(false);
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
            textInputTerm.setErrorEnabled(false);
            return true;
        }
    }

    //TODO Validate flashCard Title in a textChanged listener
    private boolean validateTitle() {
        //get entered definition
        String title = textLayoutSetTitle.getEditText().getText().toString();

        if (title.isEmpty()) {
            //if input field is empty
            textLayoutSetTitle.setError("Tile field cannot be empty");
            return false;
            //if input exceeds max length
        } else if (title.length() > 100) {
            textLayoutSetTitle.setError("Tile field cannot exceed 100 characters");
            return false;
        } else {
            textLayoutSetTitle.setError(null);
            textLayoutSetTitle.setErrorEnabled(false);
            return true;

        }
    }

    //check if title already exists in DB
    private void checkSet(){
        //get user entered title
        final String checkTitle = textLayoutSetTitle.getEditText().getText().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference("CardSets");
        //query db for title
        Query query = mDatabase.orderByChild("cardSetTitle").equalTo(checkTitle);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //clear error
                    textLayoutSetTitle.setError(null);
                    textLayoutSetTitle.setErrorEnabled(false);

                    String dbTitle = snapshot.child(checkTitle).child("cardSetTitle").getValue(String.class);

                    if (dbTitle.equals(checkTitle)) {
                        Log.i("clickedSave", "title does exist, not calling saveSet()");
                        Toast.makeText(view.getContext(), "Title already exists", Toast.LENGTH_LONG).show();
                        textLayoutSetTitle.setError("Title already exists");
                        textLayoutSetTitle.requestFocus();
                    } else {
                        //if title does not already exist, save this set
                        Log.i("clickedSave", "title does not exist, Calling saveSet()");
                    }
                } else {
                    //if title does not already exist, save this set
                    Log.i("clickedSave", "title does not exist, Calling saveSet()");
                    saveSet();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //method to save set to firebase
    public void saveSet() {
        //TODO validate
        //TODO change view to CardSetFragment
        //TODO change view to Home page if no new cards
        Log.d("SaveSet Function", "SavedSet function called");

        //get Firebase reference
        mDatabase = FirebaseDatabase.getInstance().getReference("CardSets");

        String title = textLayoutSetTitle.getEditText().getText().toString();
        String description = textLayoutSetDescription.getEditText().getText().toString();

        //get List<CardSet>cardSet from adapter class
        List<Flashcard> savedCards = ((CreateFlashcardRecyclerViewAdapter) recyclerView.getAdapter()).getSavedList();

        //new FlashcardSet object
        FlashcardSet flashCardSet = new FlashcardSet(title, description, savedCards);

        //TODO: Remove logs
        Log.i("CardSetTitle", flashCardSet.getCardSetTitle());

        for (Flashcard flashCard : flashCardSet.getCardSet()) {
            Log.i("CardTerm", flashCard.getTerm());
            Log.i("CardDef", flashCard.getDefinition());
        }

        //Write to Firebase
        mDatabase.child(title).setValue(flashCardSet).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(view.getContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                } else if (task.isCanceled()) {
                    Toast.makeText(view.getContext(), "Warning! Data Insertion Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}