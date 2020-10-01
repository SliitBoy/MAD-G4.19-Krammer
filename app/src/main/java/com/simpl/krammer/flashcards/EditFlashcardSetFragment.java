package com.simpl.krammer.flashcards;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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
public class EditFlashcardSetFragment extends Fragment {
    private DatabaseReference mDatabase;
    //FlashcardSet to be edited
    private FlashcardSet flashcardSet;
    private String title;
    //Flashcards to be edited
    private List<Flashcard> flashcards;

    // RecyclerView Objects
    private View view;
    private RecyclerView recyclerView;
    private EditFlashcardSetRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView saveEditSetButton;
    private TextInputLayout editTextTitle;
    private TextInputLayout editTextDescription;

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
        view = inflater.inflate(R.layout.fragment_edit_flashcard_set_list, container, false);

        if (getArguments() != null) {
            //set FlashcardSet object from ViewAllFlashcardsFragment
            flashcardSet = (FlashcardSet) getArguments().getSerializable("selectedSet");
            //get List<Flashcards> from FlashcardSet
            flashcards = flashcardSet.getCardSet();
            title = flashcardSet.getCardSetTitle();
        }
        Log.e("EditflashcardsTest ", "" + flashcards.get(0).getTerm());

        //Set set title and description from flashcardSet
        editTextTitle = (TextInputLayout) view.findViewById(R.id.textInputLayoutEditTitle);
        editTextTitle.getEditText().setText(flashcardSet.getCardSetTitle());

        editTextDescription = (TextInputLayout) view.findViewById(R.id.textInputLayoutEditDescp);
        editTextDescription.getEditText().setText(flashcardSet.getCardSetDescription());

        //build RecyclerView
        buildRecycler();

        //TODO: get and validate title
        //listeners to check changes to title and description
        editTextTitle.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String title = editTextTitle.getEditText().getText().toString();
                if (validateTitle(title)) {
                    flashcardSet.setCardSetTitle(title);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextDescription.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String description = editTextDescription.getEditText().getText().toString();
                if (validateTitle(description)) {
                    flashcardSet.setCardSetDescription(description);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //save edited set to firebase
        saveEditSetButton = view.findViewById(R.id.buttonSaveEditSet);
        saveEditSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEditSet();
            }
        });


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

    //Validate Title
    public Boolean validateTitle(String editedTitle) {
        String title = editedTitle;
        if (title.isEmpty()) {
            editTextTitle.setError("Required field");
            return false;
        } else {
            editTextTitle.setError(null);
            //hide error field
            editTextTitle.setErrorEnabled(false);
            return true;
        }
    }

    //Validate Description
    public Boolean validateDescription(String editedDescription) {
        String description = editedDescription;
        if (description.length() >= 150) {
            editTextTitle.setError("Description too long. Max 150 characters. ");
            return false;
        } else {
            editTextTitle.setError(null);
            //hide error field
            editTextTitle.setErrorEnabled(false);
            return true;
        }
    }


    //method to save set to firebase
    public void saveEditSet() {
        //TODO create new FlashCardSet with title and description
        //TODO insert savedSet into new FlashCardSet
        //TODO change view to CardSetFragment
        //TODO change view to Home page if no new cards
        Log.d("SaveSet Function", "SavedSet function called");

        //firebase method to update edited set
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CardSets");
        //List to store edited set
        List<Flashcard> editedSet;

        //TODO Validate set
        editedSet = mAdapter.getSavedList();
        String editTitle = flashcardSet.getCardSetTitle();
        String editDescp = flashcardSet.getCardSetDescription();
        //TODO alternative set/replace instead of add
        final FlashcardSet newflashCardSet = new FlashcardSet(editTitle, editDescp, editedSet);

        //TODO: Update Set instead of new CardSetTitle

        //TODO: Update firebase by child
//        //String key = mDatabase.child("CardSets").push().getKey();
//        mDatabase.orderByChild("cardSetTitle").equalTo(title)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                   String key2 = dataSnapshot.getKey();
//                   //update firebase
//                    mDatabase.child("MI2H4tRfVbq6h9qWkDm").updateChildren();
//
//                    Toast.makeText(view.getContext(), "Data Updated", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


    }
}