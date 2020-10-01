package com.simpl.krammer.flashcards;

import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.simpl.krammer.R;
import com.simpl.krammer.flashcards.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EditFlashcardSetRecyclerViewAdapter extends RecyclerView.Adapter<EditFlashcardSetRecyclerViewAdapter.ViewHolder> {

    private final List<Flashcard> mValues;

    public EditFlashcardSetRecyclerViewAdapter(List<Flashcard> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_edit_flashcard_set, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mTerm.getEditText().setText(mValues.get(position).getTerm());
        holder.mDefinition.getEditText().setText(mValues.get(position).getDefinition());

        holder.mTerm.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String term = holder.mTerm.getEditText().getText().toString();
                if (term.isEmpty()) {
                    holder.mTerm.setError("Required field");
                } else if (term.length() >= 150) {
                    holder.mTerm.setError("Term field too long. Max 100 characters");
                } else {
                    holder.mTerm.setError(null);
                    //hide error field
                    holder.mTerm.setErrorEnabled(false);
                    //set values
                    mValues.get(position).setTerm(term);
                    Log.e("Edited def " ,""+ mValues.get(position).getTerm());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.mDefinition.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String def = holder.mDefinition.getEditText().getText().toString();
                if (def.isEmpty()) {
                    holder.mDefinition.setError("Required field");
                } else if (def.length() >= 150) {
                    holder.mDefinition.setError("Definition field too long. Max 150 characters");
                } else {
                    holder.mDefinition.setError(null);
                    //hide error field
                    holder.mDefinition.setErrorEnabled(false);
                    //set values
                    mValues.get(position).setDefinition(def);
                    Log.e("Edited def " ,""+ mValues.get(position).getTerm());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //remove card on click
        holder.removeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mValues.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(position, mValues.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    //return current list of flashcards
    public List<Flashcard> getSavedList() {
        return mValues;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextInputLayout mTerm;
        public final TextInputLayout mDefinition;
        public ImageButton removeCardButton;
        public Flashcard mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTerm = (TextInputLayout) view.findViewById(R.id.InputLayoutEditCardTerm);
            mDefinition = (TextInputLayout) view.findViewById(R.id.InputLayoutEditCardDef);
            removeCardButton = (ImageButton) view.findViewById(R.id.editDeleteCardButton);
        }
    }
}