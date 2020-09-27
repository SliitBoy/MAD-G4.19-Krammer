package com.simpl.krammer.flashcards;

import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
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
        holder.mTerm.setText(mValues.get(position).getTerm());
        holder.mDefinition.setText(mValues.get(position).getDefinition());

        holder.mTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String term = holder.mTerm.getText().toString();
                if (!term.isEmpty()) {
                    mValues.get(position).setTerm(term);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.mDefinition.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String def = holder.mDefinition.getText().toString();
                if (!def.isEmpty()) {
                    mValues.get(position).setTerm(def);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextInputEditText mTerm;
        public final TextInputEditText mDefinition;
        public ImageButton removeCardButton;
        public Flashcard mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTerm = (TextInputEditText) view.findViewById(R.id.TextInputEditTextEditCardTerm);
            mDefinition = (TextInputEditText) view.findViewById(R.id.TextInputEditTextEditCardDef);
            removeCardButton = (ImageButton) view.findViewById(R.id.editDeleteCardButton);
        }
    }
}