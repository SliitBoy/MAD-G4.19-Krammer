package com.simpl.krammer.flashcards;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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
        setHasOptionsMenu(true);
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

        //add new card button
        MaterialButton addNewCardButton = view.findViewById(R.id.editSetAddCardButton);
        addNewCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add a new cardView to recyclerView in newFlashCardFragment
                Log.d("CardSetTitle", "Inserting new card");
                flashcards.add(new Flashcard());
                mAdapter.notifyItemInserted(flashcards.size() - 1);
            }
        });

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
        Log.d("SaveSet Function", "SavedSet function called");

        //List to store edited set
        List<Flashcard> editedSet = mAdapter.getSavedList();
        String editTitle = flashcardSet.getCardSetTitle();
        String editDescp = flashcardSet.getCardSetDescription();

        final FlashcardSet editFlashCardSet = new FlashcardSet(editTitle, editDescp, editedSet);

        final String checkTitle = title;
        //firebase method to update edited set
        mDatabase = FirebaseDatabase.getInstance().getReference("CardSets");
        //query db for title
        Query query = mDatabase.orderByChild("cardSetTitle") .equalTo(checkTitle);

        //check for title
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //Write to Firebase
                    mDatabase.child(title).setValue(editFlashCardSet).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(view.getContext(), "Data Updated", Toast.LENGTH_LONG).show();
                                viewEditSet(editFlashCardSet);
                            } else if (task.isCanceled()) {
                                Toast.makeText(view.getContext(), "Warning! Data Insertion Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_bar_search).setVisible(false);
        menu.findItem(R.id.action_bar_add).setVisible(false);
        menu.findItem(R.id.action_bar_delete).setVisible(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.action_bar, menu);
        MenuItem deleteItem = menu.findItem(R.id.action_bar_delete);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bar_delete:
            //call delete method
                deleteDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteDialog() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(view.getContext());
        materialAlertDialogBuilder.setTitle(R.string.delete_set_title)
                .setMessage(R.string.delete_set_msg).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteSet();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();

    }

    private void deleteSet() {

        final String checkTitle = title;
        //firebase method to update edited set
        mDatabase = FirebaseDatabase.getInstance().getReference("CardSets");
        //query db for title
        Query query = mDatabase.orderByChild("cardSetTitle") .equalTo(checkTitle);

        //check for title
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //delete from Firebase
                    mDatabase.child(title).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(view.getContext(), title + " Deleted", Toast.LENGTH_LONG).show();
                                backToViewAll();
                            } else if (task.isCanceled()) {
                                Toast.makeText(view.getContext(), "Warning! Data Insertion Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void backToViewAll() {
        FlashcardsHomeFragment flashcardsHomeFragment = new FlashcardsHomeFragment();

        FragmentManager fm = getFragmentManager();

        assert fm != null;
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, flashcardsHomeFragment);

        transaction.commit();
    }

    private void viewEditSet(FlashcardSet editFlashCardSet) {
        ViewFlashcardSetFragment viewFlashcardSetFragment = new ViewFlashcardSetFragment();

        FragmentManager fm = getFragmentManager();

        //pass selected FlashcardSet to ViewFlashcardSetFragment
        Bundle args = new Bundle();
        args.putSerializable("selectedSet", editFlashCardSet);
        viewFlashcardSetFragment.setArguments(args);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, viewFlashcardSetFragment);

        transaction.commit();
    }
}